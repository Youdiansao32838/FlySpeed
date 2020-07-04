/*
	游戏编译成类文件后直接用java命令启动
	为了方便用户，所以直接用c写一个启动器
*/
#include <windows.h>
#include <stdio.h>
#include <io.h>

int main()
{
	printf("该程序要使用JDK，如果无法启动，请检查您的JDK是否安装正确\n");
	//主动提醒
	printf("游戏正在启动\n");
	if (_access("GameMenu.class",0))//如果class文件不存在，那就先编译一下
	{
		printf("正在为第一次启动做准备\n");
		system("javac -encoding utf-8 GameMenu.java");
		Sleep(1000);//给编译器一点时间
	}
	//system("javac -encoding utf-8 GameMenu.java");
	system("java GameMenu");
	printf("游戏已经关闭\n按下回车键结束");
	getchar();
	return 0;
}