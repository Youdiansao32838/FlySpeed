/*
	游戏编译成类文件后直接用java命令启动
	为了方便用户，所以直接用c写一个启动器
*/
#include <windows.h>
#include <stdio.h>
int main()
{
	printf("该程序要使用JDK13，如果无法启动，请检查您的JDK版本\n");
	//主动提醒
	printf("游戏正在启动\n");
	system("java GameMenu");
	printf("游戏已经关闭");
	return 0;
}