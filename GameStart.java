//import java.util.Random;

public class GameStart {

    public boolean isGameStart = false;
    private final int gamePlayingBackSpeed = 2000;
    private final int pipeMoveSpeed = 1000;
    private int playerVibrationTime = 0;
    private int playerUpOrDown = 3;
    private int playerVibrationSwitch = 0;
    private long startTime = 0;// 获取到初始的时间戳
    private long temTime = 0;
    private long endTime = 0;
    private int score = 0;// 得分
    private long flyStartTime = 0;
    // private int pipeCreatTime=0;

    public void witchLevel(int levelNumber) {
        if (levelNumber == 1) {
            score = 0;
            firstLevel();
        } else if (levelNumber == 3) {
            thirdLevel();
        }

    }

    private void firstLevel() {

        boolean setPipeY[] = { true, true, true, true };// 用四个开关操作也是属是无奈
        flyStartTime = System.currentTimeMillis();
        // GameMenu.window.selectImage(1);

        GameMenu.window.witchPaint = 1;
        GameMenu.playerImageSwitchNumber = 10;
        // 开始游戏
        while (isGameStart == true) {
            startTime = System.currentTimeMillis();
            temTime = System.currentTimeMillis();

            while (endTime - startTime < 1000 && isGameStart == true) {
                endTime = System.currentTimeMillis();// 执行结束时候的时间戳
                if (endTime - temTime >= 1000 / GameMenu.fps) {
                    if (GameMenu.playerMoveUP == true) {
                        // 向上移动
                        // playerMoveStep=playerMoveSpeed/(1000/(endTime-temTime));
                        if (GameMenu.window.playerY >= 0)
                            GameMenu.window.playerY -= (GameMenu.playerMoveSpeed + 300) / (1000 / (endTime - temTime));
                        if (GameMenu.playerMoveLeft == true) {
                            // 向左移动
                            if (GameMenu.window.playerX >= 0)
                                GameMenu.window.playerX -= GameMenu.playerMoveSpeed / (1000 / (endTime - temTime));
                        } else if (GameMenu.playerMoveRight == true) {
                            // 向右移动
                            if (GameMenu.window.playerX <= 1180)
                                GameMenu.window.playerX += GameMenu.playerMoveSpeed / (1000 / (endTime - temTime));
                        }
                    } else if (GameMenu.playerMoveDown == true) {
                        // 向下移动
                        if (GameMenu.window.playerY <= 670)
                            GameMenu.window.playerY += (GameMenu.playerMoveSpeed + 300) / (1000 / (endTime - temTime));
                        if (GameMenu.playerMoveLeft == true) {
                            // 向左移动
                            if (GameMenu.window.playerX >= 0)
                                GameMenu.window.playerX -= GameMenu.playerMoveSpeed / (1000 / (endTime - temTime));
                        } else if (GameMenu.playerMoveRight == true) {
                            // 向右移动
                            if (GameMenu.window.playerX <= 1180)
                                GameMenu.window.playerX += GameMenu.playerMoveSpeed / (1000 / (endTime - temTime));
                        }
                    } else if (GameMenu.playerMoveLeft == true) {
                        // 向左移动
                        if (GameMenu.window.playerX >= 0)
                            GameMenu.window.playerX -= GameMenu.playerMoveSpeed / (1000 / (endTime - temTime));
                    } else if (GameMenu.playerMoveRight == true) {
                        // 向右移动
                        if (GameMenu.window.playerX <= 1230)
                            GameMenu.window.playerX += GameMenu.playerMoveSpeed / (1000 / (endTime - temTime));
                    }

                    if (GameMenu.window.londingAnimeStart == true) {
                        // 如果加载动画的开关是开着的，那就继续操作动画
                        GameMenu.window.londingBackgroundX += GameMenu.londingBackSpeed / (1000 / (endTime - temTime));
                    } else {
                        // 否则就来操作水管
                        GameMenu.window.pipeImageX[0] -= pipeMoveSpeed / (1000 / (endTime - temTime));
                        GameMenu.window.pipeImageX[1] -= pipeMoveSpeed / (1000 / (endTime - temTime));
                        GameMenu.window.pipeImageX[2] -= pipeMoveSpeed / (1000 / (endTime - temTime));
                        GameMenu.window.pipeImageX[3] -= pipeMoveSpeed / (1000 / (endTime - temTime));
                    }
                    GameMenu.window.playingBackgroundX -= gamePlayingBackSpeed / (1000 / (endTime - temTime));

                    // 在这里把管子的开口位置决定出来
                    if (setPipeY[0] == true) {
                        // 用Math.random()*10来产生0到10的随机数
                        GameMenu.window.pipeDownImageY[0] -= (Math.random() * 30) * 20;
                        // GameMenu.window.pipeUpImageY[0]=GameMenu.window.pipeDownImageY[0]+620+220;
                        setPipeY[0] = false;
                    }
                    if (setPipeY[1] == true) {
                        GameMenu.window.pipeDownImageY[1] -= (Math.random() * 30) * 20;
                        // GameMenu.window.pipeUpImageY[1]=GameMenu.window.pipeDownImageY[1]+620+220;
                        setPipeY[1] = false;
                    }
                    if (setPipeY[2] == true) {
                        GameMenu.window.pipeDownImageY[2] -= (Math.random() * 30) * 20;
                        // GameMenu.window.pipeUpImageY[2]=GameMenu.window.pipeDownImageY[2]+620+220;
                        setPipeY[2] = false;
                    }
                    if (setPipeY[3] == true) {
                        GameMenu.window.pipeDownImageY[3] -= (Math.random() * 30) * 20;
                        // GameMenu.window.pipeUpImageY[3]=GameMenu.window.pipeDownImageY[3]+620+220;
                        setPipeY[3] = false;
                    }
                    
                    GameMenu.playerImageSwitchCount++;
                    playerVibrationTime++;
                    GameMenu.window.scoreString = "" + score;
                    GameMenu.window.repaint();
                    collisionDetection(GameMenu.window.pipeImageX[0], GameMenu.window.pipeDownImageY[0]);
                    collisionDetection(GameMenu.window.pipeImageX[1], GameMenu.window.pipeDownImageY[1]);
                    collisionDetection(GameMenu.window.pipeImageX[2], GameMenu.window.pipeDownImageY[2]);
                    collisionDetection(GameMenu.window.pipeImageX[3], GameMenu.window.pipeDownImageY[3]);
                    GameMenu.fpsSum++;// 记录每秒的帧数

                    // 让管子归位的神奇代码
                    if (GameMenu.window.pipeImageX[0] <= -52) {
                        GameMenu.window.pipeImageX[0] = 1300;
                        GameMenu.window.pipeDownImageY[0] = 0;
                        setPipeY[0] = true;
                        score++;
                    } else if (GameMenu.window.pipeImageX[1] <= -52) {
                        GameMenu.window.pipeImageX[1] = 1300;
                        GameMenu.window.pipeDownImageY[1] = 0;
                        setPipeY[1] = true;
                        score++;

                    } else if (GameMenu.window.pipeImageX[2] <= -52) {
                        GameMenu.window.pipeImageX[2] = 1300;
                        GameMenu.window.pipeDownImageY[2] = 0;
                        setPipeY[2] = true;
                        score++;
                    } else if (GameMenu.window.pipeImageX[3] <= -52) {
                        GameMenu.window.pipeImageX[3] = 1300;
                        GameMenu.window.pipeDownImageY[3] = 0;
                        setPipeY[3] = true;
                        score++;
                    }

                    // player独立动画
                    if (GameMenu.playerImageSwitchCount >= 15) {
                        // System.out.println("换");
                        GameMenu.playerSwitchNumber += GameMenu.biggerOrSmaller;
                        GameMenu.playerImageSwitchCount = 0;// 重置
                        if (GameMenu.playerSwitchNumber >= 2 || GameMenu.playerSwitchNumber <= 0) {
                            GameMenu.biggerOrSmaller *= -1;// 超过二或者小于0就反过来
                        }
                    }

                    // player抖动的独立动画,原理和上边扇翅膀动画的原理一样
                    if (playerVibrationTime >= 10) {
                        GameMenu.window.playerY += playerUpOrDown;
                        playerVibrationSwitch++;
                        playerVibrationTime = 0;
                        if (playerVibrationSwitch >= 2) {
                            playerUpOrDown *= -1;
                            playerVibrationSwitch = 0;
                        }
                    }

                    // 等到整个图都出去了就把动画给关掉
                    if (GameMenu.window.londingBackgroundX >= 1280) {
                        GameMenu.window.londingAnimeStart = false;
                        GameMenu.window.londingBackgroundX = -1280;
                    }

                    temTime = System.currentTimeMillis();// 刷新时间戳，用于计算每一帧的时间
                }

            }

            GameMenu.window.fpssString = "fps:" + GameMenu.fpsSum;// 刷新显示在界面上的fps的值
            GameMenu.fpsSum = 0;// 刷新fps的值
            // if (GameMenu.window.londingBackgroundX>=1280) {
            // isGameStart=true;
            // }

        }
        // System.out.println("第一关启动");
        // gameStarting();
        printScore();
        gameEnding();
    }

    private void collisionDetection(int pipeX, int pipeY) {
        // 这个数组表示player的矩形
        int playerLocation[] = { GameMenu.window.playerX + 5, // 尾部X
                GameMenu.window.playerX + 35, // 头部X
                GameMenu.window.playerY + 8, // 顶部Y
                GameMenu.window.playerY + 37// 底部Y
        };
        // 当player进入指定范围的时候就开始操作
        if (playerLocation[1] >= pipeX && playerLocation[0] <= pipeX + 52) {
            if (playerLocation[2] <= pipeY + 620 || playerLocation[3] >= pipeY + 620 + 220) {
                isGameStart = false;
                GameMenu.window.flyTime = (System.currentTimeMillis() - flyStartTime) / 1000;
            }
        }
    }

    private void printScore() {

        boolean endAnimePlay = true;

        GameMenu.window.londingAnimeStart = true;

        GameMenu.window.witchPaint = 5;

        while (endAnimePlay == true) {
            startTime = System.currentTimeMillis();
            temTime = System.currentTimeMillis();
            while (endTime - startTime < 1000 == true && endAnimePlay == true) {
                endTime = System.currentTimeMillis();// 执行结束时候的时间戳
                if (endTime - temTime >= 1000 / GameMenu.fps) {

                    GameMenu.window.londingBackgroundX += GameMenu.londingBackSpeed / (1000 / (endTime - temTime));// 操作背景

                    GameMenu.window.repaint();
                    if (GameMenu.window.londingBackgroundX >= 0 && GameMenu.window.londingBackgroundX <= 10) {
                        GameMenu.window.witchPaint = 0;// 换界面
                        GameMenu.window.playerX=370;//重新设置player的位置
                        GameMenu.window.playerY=315;
                        GameMenu.window.selectImage(3);
                    }
                    if (GameMenu.window.londingBackgroundX >= 1028) {
                        endAnimePlay = false;
                    }
                    temTime = System.currentTimeMillis();// 刷新时间戳，用于计算每一帧的时间
                }

            }
            GameMenu.window.fpssString = "fps:" + GameMenu.fpsSum;// 刷新显示在界面上的fps的值
            GameMenu.fpsSum = 0;// 刷新fps的值
            // if (GameMenu.window.londingBackgroundX>=1280) {
            // isGameStart=true;
            // }
        }

    }

    private void thirdLevel() {
        //System.out.println("退出");
        System.exit(0);
    }

    private void gameEnding(){
        //System.out.println("游戏结束了");
        //结束的时候把所有的东西全都恢复到初始状态
        GameMenu.londingAnimeStart=false;
        //GameMenu.window.witchPaint=GameMenu.window.selectImage(0);
        GameMenu.window.londingBackgroundX=-1280;
        //pipeCreatTime=0;
        GameMenu.playerImageSwitchNumber=20;
        //pipeImageX[]={1280,1600,1920,2240}
        GameMenu.window.pipeImageX[0]=1280;
        GameMenu.window.pipeImageX[1]=1600;
        GameMenu.window.pipeImageX[2]=1920;
        GameMenu.window.pipeImageX[3]=2240;
        GameMenu.window.pipeDownImageY[0]=0;
        GameMenu.window.pipeDownImageY[1]=0;
        GameMenu.window.pipeDownImageY[2]=0;
        GameMenu.window.pipeDownImageY[3]=0;
    }
}