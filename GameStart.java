public class GameStart {

    public boolean isGameStart=false;
    private final int gamePlayingBackSpeed=2000;
    private final int pipeMoveSpeed=1000;
    private int playerVibrationTime=0;
    private int playerUpOrDown=3;
    private int playerVibrationSwitch=0;
    //private int pipeCreatTime=0;
    
    public void witchLevel(int levelNumber){
        if (levelNumber==1) {
            firstLevel();    
        }
        else if (levelNumber==3) {
            thirdLevel();
        }
        

    }

    private void firstLevel(){
        
        long startTime=0;//获取到初始的时间戳
        long temTime=0;
        long endTime=0;
        
        //GameMenu.window.selectImage(1);
        GameMenu.window.witchPaint=1;
        GameMenu.playerImageSwitchNumber=10;

        while (isGameStart==true) {
            startTime=System.currentTimeMillis();
            temTime=System.currentTimeMillis();
            while (endTime-startTime<1000) {
                endTime=System.currentTimeMillis();//执行结束时候的时间戳
                if(endTime-temTime>=1000/GameMenu.fps){
                    if (GameMenu.playerMoveUP==true) {
                        //向上移动
                        //playerMoveStep=playerMoveSpeed/(1000/(endTime-temTime));
                        if (GameMenu.window.playerY>=0)
                        GameMenu.window.playerY-=GameMenu.playerMoveSpeed/(1000/(endTime-temTime));
                        if(GameMenu.playerMoveLeft==true){
                            //向左移动
                            if(GameMenu.window.playerX>=0)
                                GameMenu.window.playerX-=GameMenu.playerMoveSpeed/(1000/(endTime-temTime));
                        }
                        else if (GameMenu.playerMoveRight==true) {
                            //向右移动
                            if(GameMenu.window.playerX<=1180)
                                GameMenu.window.playerX+=GameMenu.playerMoveSpeed/(1000/(endTime-temTime));
                        }
                    }
                    else if (GameMenu.playerMoveDown==true){
                        //向下移动
                        if(GameMenu.window.playerY<=670)
                        GameMenu.window.playerY+=GameMenu.playerMoveSpeed/(1000/(endTime-temTime));
                        if(GameMenu.playerMoveLeft==true){
                            //向左移动
                            if(GameMenu.window.playerX>=0)
                                GameMenu.window.playerX-=GameMenu.playerMoveSpeed/(1000/(endTime-temTime));
                        }
                        else if (GameMenu.playerMoveRight==true) {
                            //向右移动
                            if(GameMenu.window.playerX<=1180)
                                GameMenu.window.playerX+=GameMenu.playerMoveSpeed/(1000/(endTime-temTime));
                        }
                    }
                    else if(GameMenu.playerMoveLeft==true){
                        //向左移动
                        if(GameMenu.window.playerX>=0)
                            GameMenu.window.playerX-=GameMenu.playerMoveSpeed/(1000/(endTime-temTime));
                    }
                    else if (GameMenu.playerMoveRight==true) {
                        //向右移动
                        if(GameMenu.window.playerX<=1230)
                            GameMenu.window.playerX+=GameMenu.playerMoveSpeed/(1000/(endTime-temTime));
                    }

                    if (GameMenu.window.londingAnimeStart==true) {
                        //如果加载动画的开关是开着的，那就继续操作动画
                        GameMenu.window.londingBackgroundX+=GameMenu.londingBackSpeed/(1000/(endTime-temTime));
                    }
                    else{
                        //否则就来操作水管
                        GameMenu.window.pipeImageX[0]-=pipeMoveSpeed/(1000/(endTime-temTime));
                        GameMenu.window.pipeImageX[1]-=pipeMoveSpeed/(1000/(endTime-temTime));
                        GameMenu.window.pipeImageX[2]-=pipeMoveSpeed/(1000/(endTime-temTime));
                        GameMenu.window.pipeImageX[3]-=pipeMoveSpeed/(1000/(endTime-temTime));
                    }
                    GameMenu.window.playingBackgroundX-=gamePlayingBackSpeed/(1000/(endTime-temTime));
                    
                    GameMenu.playerImageSwitchCount++;
                    playerVibrationTime++;
    
                    GameMenu.window.repaint();
                    GameMenu.fpsSum++;//记录每秒的帧数

                    //让管子归位的神奇代码
                    if (GameMenu.window.pipeImageX[0]<=-52) {
                        GameMenu.window.pipeImageX[0]=1300;
                    }
                    else if (GameMenu.window.pipeImageX[1]<=-52) {
                        GameMenu.window.pipeImageX[1]=1300;
                    }
                    else if (GameMenu.window.pipeImageX[2]<=-52) {
                        GameMenu.window.pipeImageX[2]=1300;
                    }
                    else if (GameMenu.window.pipeImageX[3]<=-52) {
                        GameMenu.window.pipeImageX[3]=1300;
                    }

                    
                    //player独立动画
                    if (GameMenu.playerImageSwitchCount>=15) {
                        //System.out.println("换");
                        GameMenu.playerSwitchNumber+=GameMenu.biggerOrSmaller;
                        GameMenu.playerImageSwitchCount=0;//重置
                        if (GameMenu.playerSwitchNumber>=2||GameMenu.playerSwitchNumber<=0){
                            GameMenu.biggerOrSmaller*=-1;//超过二或者小于0就反过来
                        }
                    }

                    //player抖动的独立动画,原理和上边扇翅膀动画的原理一样
                    if (playerVibrationTime>=10) {
                        GameMenu.window.playerY+=playerUpOrDown;
                        playerVibrationSwitch++;
                        playerVibrationTime=0;
                        if (playerVibrationSwitch>=2) {
                            playerUpOrDown*=-1;
                            playerVibrationSwitch=0;
                        }
                    }

                    //等到整个图都出去了就把动画给关掉
                    if (GameMenu.window.londingBackgroundX>=1280){
                        GameMenu.window.londingAnimeStart=false;
                    }

                    temTime=System.currentTimeMillis();//刷新时间戳，用于计算每一帧的时间
                }
                
            }
            
            GameMenu.window.fpssString="fps:"+GameMenu.fpsSum;//刷新显示在界面上的fps的值
            GameMenu.fpsSum=0;//刷新fps的值
            // if (GameMenu.window.londingBackgroundX>=1280) {
            //     isGameStart=true;
            // }
            
        }
        //System.out.println("第一关启动");
        //gameStarting();
        gameEnding();
        
    }

    private void thirdLevel(){
        System.out.println("退出");
        System.exit(0);
    }
    private void gameEnding(){
        System.out.println("游戏结束了");
        //结束的时候把所有的东西全都恢复到初始状态
        GameMenu.londingAnimeStart=false;
        GameMenu.window.witchPaint=GameMenu.window.selectImage(0);
        GameMenu.window.londingBackgroundX=-1280;
        //pipeCreatTime=0;
        GameMenu.playerImageSwitchNumber=20;
        //pipeImageX[]={1280,1600,1920,2240}
        GameMenu.window.pipeImageX[0]=1280;
        GameMenu.window.pipeImageX[1]=1600;
        GameMenu.window.pipeImageX[2]=1920;
        GameMenu.window.pipeImageX[3]=2240;

    }
}