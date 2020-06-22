/*

project name: Fly Speed
version: 1.0.0(beta)
start time: 06.17.2020
end time: null

项目名：Fly Speed
当前版本：1.0.0(测试版)
开始时间：2020年6月17日
结束时间：还没结束呢

*/
import java.awt.event.*;


public class GameMenu{

    public static GameWindow window=new GameWindow("FlySpeed");
    
    public static int playerSwitchNumber=0;//角色动画选择
    public static int playerImageSwitchNumber=20;
    public static int playerImageSwitchCount=0;//player图片切换的计数器
    public static int biggerOrSmaller=1;//用来进行反复横跳

    public static int playerMoveSpeed=1500;
    
    public static boolean playerMoveUP=false;//检测player状态的开关
    public static boolean playerMoveDown=false;
    public static boolean playerMoveLeft=false;
    public static boolean playerMoveRight=false;

    private static boolean enterKeyDown=false;

    public static final int londingBackSpeed=1500;

    public static int fpsSum = 0;//帧数监控
    public static final int fps = 120;//帧数锁定

    public static boolean londingAnimeStart=false;//加载动画状态检测

    public static void main(String[] args) {
        
        GameStart game=new GameStart();

        long startTime = 0, endTime = 0,temTime = 0;//动画的计时器

        boolean gameExit=false;
        
        /////////分割线////////

        
        window.selectImage(0);//初始化
        window.addKeyListener(new KeyListener(){

            @Override
            public void keyPressed(KeyEvent e) {
                //按钮按下时的动作
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                    //按了向上键时候的动作
                    //如果同时按下了另一个方向键，那就把那个方向的开关也打开
                        if (e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A) {
                            playerMoveLeft=true;
                        }
                        else if (e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D) {
                            playerMoveRight=true;
                        }
                        playerMoveUP=true;//把向上的开关打开
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                    //按了向下键时候的动作
                        if (e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A) {
                            playerMoveLeft=true;
                        }
                        else if (e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D) {
                            playerMoveRight=true;
                        }
                        playerMoveDown=true;
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                    //按了向左键时候的动作
                        if (e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S) {
                            playerMoveLeft=true;
                        }
                        else if (e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W) {
                            playerMoveRight=true;
                        }
                        playerMoveLeft=true;
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                    //按了向右键时候的动作
                        if (e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S) {
                            playerMoveLeft=true;
                        }
                        else if (e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W) {
                            playerMoveRight=true;
                        }
                        playerMoveRight=true;
                        break;
                    case KeyEvent.VK_ENTER:
                        enterKeyDown=true;
                        break;
                    case KeyEvent.VK_SPACE:
                        playerMoveSpeed+=1000;
                        break;

                    case KeyEvent.VK_ESCAPE:
                        window.selectImage(0);
                        System.out.println("返回菜单");
                        game.isGameStart=false;
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // 按钮放开时的动作
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                    //向上键松开时候的动作
                        playerMoveUP=false;//把相应的开关关掉
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        playerMoveDown=false;
                        //松开向下
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        playerMoveLeft=false;
                        //松开向左
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        playerMoveRight=false;
                        //松开向右
                        break;
                    case KeyEvent.VK_ENTER:
                        enterKeyDown=false;
                        break;
                    case KeyEvent.VK_SPACE:
                        playerMoveSpeed-=1000;
                        break;
                    
                    
                    default:
                        break;
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                //按一下某个按键时候的动作
            }
            
        });
        
        while (gameExit==false) {
            startTime=System.currentTimeMillis();//获取到初始的时间戳
            temTime=System.currentTimeMillis();
            while (endTime-startTime<1000) {
                endTime=System.currentTimeMillis();//执行结束时候的时间戳
                if (enterKeyDown==true) {
                    
                    if (window.playerX<=200&&window.playerY<=200) {
                        londingAnimeStart=true;
                        window.londingAnimeStart=londingAnimeStart;
                        //game.witchLevel(1);
                    }
                    else if (window.playerX>=980&&window.playerY<=200){
                        
                        window.witchPaint=window.selectImage(2);
                        //game.witchLevel(2);
                    }
                    else if (window.playerX<=200&&window.playerY>=420) {
                        
                        window.witchPaint=window.selectImage(4);
                        //game.witchLevel(4);
                    }
                    else if (window.playerX>=980&&window.playerY>=420) {
                        
                        window.witchPaint=window.selectImage(3);
                        game.witchLevel(3);//结束游戏
                    }
                }
                //当执行时间>=每一帧需要的时间时，就开始画图
                if(endTime-temTime>=1000/fps){
                    //执行每一帧要执行的操作
                    if (playerMoveUP==true) {
                        //向上移动
                        //playerMoveStep=playerMoveSpeed/(1000/(endTime-temTime));
                        if (window.playerY>=0)
                            window.playerY-=playerMoveSpeed/(1000/(endTime-temTime));
                        //同时按下另一个方向键时候的操作
                        if(playerMoveLeft==true){
                            //向左移动
                            if(window.playerX>=0)
                                window.playerX-=playerMoveSpeed/(1000/(endTime-temTime));
                        }
                        else if (playerMoveRight==true) {
                            //向右移动
                            if(window.playerX<=1180)
                                window.playerX+=playerMoveSpeed/(1000/(endTime-temTime));
                        }
                    }
                    else if (playerMoveDown==true){
                        //向下移动
                        if(window.playerY<=620)
                            window.playerY+=playerMoveSpeed/(1000/(endTime-temTime));
                        //同时按下另一个方向键时的操作
                        if(playerMoveLeft==true){
                            //向左移动
                            if(window.playerX>=0)
                                window.playerX-=playerMoveSpeed/(1000/(endTime-temTime));
                        }
                        else if (playerMoveRight==true) {
                            //向右移动
                            if(window.playerX<=1180)
                                window.playerX+=playerMoveSpeed/(1000/(endTime-temTime));
                        }
                    }
                    else if(playerMoveLeft==true){
                        //向左移动
                        if(window.playerX>=0)
                            window.playerX-=playerMoveSpeed/(1000/(endTime-temTime));
                    }
                    else if (playerMoveRight==true) {
                        //向右移动
                        if(window.playerX<=1180)
                            window.playerX+=playerMoveSpeed/(1000/(endTime-temTime));
                    }

                    if (londingAnimeStart==true) {
                        window.londingBackgroundX+=londingBackSpeed/(1000/(endTime-temTime));
                        if (window.londingBackgroundX>=0) {
                            window.playerX=170;
                            window.playerY=310;
                            game.isGameStart=true;
                            game.witchLevel(1);//那就开始游戏
                        }
                    }

                    window.repaint();
                    fpsSum++;//记录每秒的帧数
                    playerImageSwitchCount++;
                    //player独立动画
                    if (playerImageSwitchCount==playerImageSwitchNumber) {
                        playerSwitchNumber+=biggerOrSmaller;
                        playerImageSwitchCount=0;//重置
                        if (playerSwitchNumber>=2||playerSwitchNumber<=0){
                            biggerOrSmaller*=-1;//超过三或者小于0就反过来
                        }
                    }
                    temTime=System.currentTimeMillis();//刷新时间戳，用于计算每一帧的时间
                }

            }
            window.fpssString="fps:"+fpsSum;//刷新显示在界面上的fps的值
            fpsSum=0;//刷新fps的值
        }
    }
}