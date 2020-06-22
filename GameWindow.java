import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;

public class GameWindow extends JFrame{

    private static final long serialVersionUID = 1L;//编译器要求加的，那就加吧

    private final int screenWidth=1280,screenHeight=720;//窗口的大小

    //JLabel hello=new JLabel("Hello Word");//测试文本
    //JLabel fpsNum=new JLabel("222");

    private final String[] playerImageFilePath={"./data/Image/bird0_0.png","./data/Image/bird0_1.png","./data/Image/bird0_2.png"};

    //private final String playerImageFilePath="./data/Image/bird0_1.png";
    private final String[] backgroundImagefFilePath={
        "./data/Image/menu_background.jpg",
        "./data/Image/leve01_back.jpg",
        "./data/Image/leve02_back.jpg",
        "./data/Image/leve03_back.jpg",
        "./data/Image/leve04_back.jpg",
        "./data/Image/londing.jpg",
        "./data/Image/playing_back.jpg"
    };
    private final String[] pipeImageFilePath={"./data/Image/pipe_up.png","./data/Image/pipe_down.png"};

    private final File playerImageFile0_0=new File(playerImageFilePath[0]);//读取文件
    private final File playerImageFile0_1=new File(playerImageFilePath[1]);//读取文件
    private final File playerImageFile0_2=new File(playerImageFilePath[2]);//读取文件

    private final File menuBackgroundImagefFile=new File(backgroundImagefFilePath[0]);//菜单背景
    private final File firstLevelBackgroundFile=new File(backgroundImagefFilePath[1]);//开始背景
    private final File secondLevelBackgroundFile=new File(backgroundImagefFilePath[2]);//设置背景
    private final File thirdLevelBackgroundFile=new File(backgroundImagefFilePath[3]);//关于
    private final File fourthLevelBackgroundFile=new File(backgroundImagefFilePath[4]);//没了
    private final File londingBackgroundFile=new File(backgroundImagefFilePath[5]);//londing的背景

    private final File playingBackFile=new File(backgroundImagefFilePath[6]);//玩游戏时候的背景
    private final File pipeUpFile=new File(pipeImageFilePath[0]);//水管
    private final File pipeDownFile=new File(pipeImageFilePath[1]);

    private BufferedImage[] playerImage=new BufferedImage[3];//用来放图片
    private BufferedImage backgroundImage;
    private BufferedImage londingImage;
    private BufferedImage playingBackImage;
    private BufferedImage[] pipeImage=new BufferedImage[2];//两根水管

    private Image temImage;//缓冲区
    private Graphics temImagePaint;//缓冲区绘制
    public String fpssString="fps:0";//帧数监视器
    public String scoreString="0";
    private Graphics2D fpsGraphics2d;//帧数画笔

    Font fpsFont=new Font(null,1,30);
    Font scoreFont=new Font(null,0,20);
    //GameMenu mainObject=new GameMenu();

    //这是各种东西的坐标
    public int playerX=170,playerY=310;//player初始坐标
    public int londingBackgroundX=-1280,londingBackgroundY=0;//加载时候的坐标背景初始坐标
    public int playingBackgroundX=0;

    //最开始的管子坐标应该是按间隙排好的
    public int pipeImageX[]={1280,1620,1940,2260};//四根管子用四个坐标属实为无奈之举
    public int pipeDownImageY[]={0,0,0,0};
    //public int pipeUpImageY[]={0,0,0,0};


    public int witchPaint=0;//画笔挑选
    public boolean londingAnimeStart=false;
    public boolean createPipe=false;
    
    public GameWindow(String gameTitle){
        setTitle(gameTitle);//游戏标题
        setBounds(10, 10, this.screenWidth, this.screenHeight);//设置初始位置和大小
        setDefaultCloseOperation(EXIT_ON_CLOSE);//设置关闭窗口的同时关闭程序
        setLayout(null);//使用手动布局，所以不需要布局器
        setResizable(false);//游戏窗口的大小应该是不可改变的
        try{
            //读取player的图片
            playerImage[0]=ImageIO.read(playerImageFile0_0);
            playerImage[1]=ImageIO.read(playerImageFile0_1);
            playerImage[2]=ImageIO.read(playerImageFile0_2);
            //初始的background应该是菜单
            backgroundImage=ImageIO.read(menuBackgroundImagefFile);
            //这玩意儿是固定的
            londingImage=ImageIO.read(londingBackgroundFile);
            

        }catch(Exception e){
            System.out.println("菜单初始化出问题了");
            e.printStackTrace();
        }
        try {
            playingBackImage=ImageIO.read(playingBackFile);
            pipeImage[0]=ImageIO.read(pipeDownFile);//水管0是上边的水管，水管1是下边的水管
            pipeImage[1]=ImageIO.read(pipeUpFile);
        } catch (Exception e) {
            System.out.println("第二个地方出问题了");
            e.printStackTrace();
        }

        //对标签进行设置
        // hello.setFont(new Font(null,0,20));
        // hello.setBounds(20,20,100,100);
        // add(hello);
        // //把fps的信息放到左上角
        // fpsNum.setBounds(5, 5, 50, 20);
        // add(fpsNum);
        
        setVisible(true);
    }

    //拉跨区域，逻辑重写后没有修改一些变量的名字
    public int selectImage(int levelNumber){
        if (levelNumber==0){
            try{
                //0是菜单
                backgroundImage=ImageIO.read(menuBackgroundImagefFile);//加载菜单的背景
            }catch(Exception e){
                e.printStackTrace();
            }
            return 0;
        }
        else if (levelNumber==1) {
            try {
                //开始游戏
                backgroundImage=ImageIO.read(firstLevelBackgroundFile);//加载第一关的背景
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 1;
        }
        else if (levelNumber==2) {
            try {
                //设置
                backgroundImage=ImageIO.read(secondLevelBackgroundFile);//加载设置的背景
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 2;
        }
        else if (levelNumber==3) {
            try {
                //退出
                backgroundImage=ImageIO.read(thirdLevelBackgroundFile);//加载第三关的背景
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 3;
        }
        else if (levelNumber==4) {
            try {
                //about
                backgroundImage=ImageIO.read(fourthLevelBackgroundFile);//加载关于的背景
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 4;
        }
        return -1;
        
    }
    //这个是菜单状态的图层
    private void paintMenu(Graphics g){
        if (temImage==null) {
            temImage=createImage(1280,720);//如果缓冲区是空的，那就创建一张空的图像
            temImagePaint=temImage.getGraphics();//获取绘图
            fpsGraphics2d=(Graphics2D)temImagePaint;//转换
            fpsGraphics2d.setFont(fpsFont);//设置fps文字
            fpsGraphics2d.setColor(Color.GREEN);
        }

        temImagePaint.drawImage(backgroundImage, 0, 0, 1280,720,null);//画背景
        temImagePaint.drawImage(playerImage[GameMenu.playerSwitchNumber], playerX, playerY, 100, 100, null);//画player

        //如果把菜单动画的开关打开了，那就开始播放动画
        if (this.londingAnimeStart==true) {
            temImagePaint.drawImage(londingImage, londingBackgroundX, 0, null);
        }
        
        
        fpsGraphics2d.drawString(fpssString, 50, 100);//把fps信息画到缓冲区
        
        g.drawImage(temImage, 0, 0, this);//把缓冲区里的image画到面板上
        // g.drawImage(backgroundImage, 0, 0, 1280, 720, null);
        // g.drawImage(playerImage, playerX, 100, 100,100,null);

    }

    private void paintGamePlaying(Graphics g){
        //如果加载的动画还没结束，那就继续画
        if (playingBackgroundX<=-1280) {
            playingBackgroundX=0;
        }
        
        temImagePaint.drawImage(playingBackImage, playingBackgroundX, 0 ,null);//画背景
        temImagePaint.drawImage(playingBackImage, playingBackgroundX+1280, 0, null);//画另外一个背景
        temImagePaint.drawImage(playerImage[GameMenu.playerSwitchNumber], playerX, playerY,50, 50, null);//画player
        //分数的字体和fps信息的字体不一样，颜色也不一样，所以要先切换
        fpsGraphics2d.setFont(scoreFont);
        fpsGraphics2d.setColor(Color.BLACK);
        fpsGraphics2d.drawString(scoreString,playerX+10, playerY+70);
       
        if (this.londingAnimeStart==true) {
            //如果longding动画的开关时开着的，那就继续放送动画
            temImagePaint.drawImage(londingImage, londingBackgroundX, 0, null);
        }
        else{
            //要等到londing的动画完全结束了，才能开始游戏
            temImagePaint.drawImage(pipeImage[0],pipeImageX[0],pipeDownImageY[0],null);
            temImagePaint.drawImage(pipeImage[1], pipeImageX[0], pipeDownImageY[0]+620+220, null);

            temImagePaint.drawImage(pipeImage[0], pipeImageX[1], pipeDownImageY[1], null);
            temImagePaint.drawImage(pipeImage[1], pipeImageX[1], pipeDownImageY[1]+620+220, null);

            temImagePaint.drawImage(pipeImage[0], pipeImageX[2], pipeDownImageY[2], null);
            temImagePaint.drawImage(pipeImage[1], pipeImageX[2], pipeDownImageY[2]+620+220, null);

            temImagePaint.drawImage(pipeImage[0], pipeImageX[3], pipeDownImageY[3], null);
            temImagePaint.drawImage(pipeImage[1], pipeImageX[3], pipeDownImageY[3]+620+220, null);

        }
        //把fps的字体换回来
        fpsGraphics2d.setFont(fpsFont);
        fpsGraphics2d.setColor(Color.green);
        fpsGraphics2d.drawString(fpssString, 50, 100);//写上每一秒的帧数

        g.drawImage(temImage, 0, 0, this);//把画完的一帧贴到窗口里面去
    }
    
    @Override

    public void paint(Graphics g){
        if (witchPaint==1) {
            paintGamePlaying(g);
        }
        //这个地方的操作逻辑有点小问题，但是我已经懒得改了
        else if (witchPaint==0 ||witchPaint==2||witchPaint==4) {
            paintMenu(g);
        }
        // else if (witchPaint==1) {
        //     paintLondingAnime(g);
        // }
        else{
            System.out.println("paint()出问题了");
            System.exit(0);
        }
        
    }

    public void update(Graphics scr){
        //重写update()，让它什么事都不做，这样的话repaint的时候就不会闪屏了
    }

}