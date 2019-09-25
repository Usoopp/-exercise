#include"stdio.h"
#include"conio.h"
#include"string.h"
#include"stdlib.h"
#include "function.h"


void main()                                            //主函数，操作界面
{
	int flag = 0;                                    //局部变量
	int b;


	while (1)                                       //循环输出一下信息
	{
		system("CLS");
		printf("\n\n\n\t\t\t欢 迎 来 到 我 的 个 人 系 统\n");
		printf("\n\t\t\t\t作者：  xiaozhang\n");
		printf("\t\t\t-------------------------------\n");
		printf("\t\t\t1.用户登录\n\n");
		printf("\t\t\t2.新用户注册\n\n");
		printf("\t\t\t3.我的风采\n\n");

		printf("\t\t\t4.管理员登录\n\n");

		printf("\t\t\t5.留言板\n\n");
		printf("\t\t\t0.退出\n\n");
		printf("\t\t\t请输入你的选择:");
		scanf("%d", &b);
		switch (b)
		{
		case 1:            log();  break;               //调用登录函数
		case 2:         regist();  break;               //调用注册函数
		case 3:        welcome();  break;               //游客直接访问
		case 4:       adminlog();  break;               //管理员登录
		case 5:   view_message();  break;               //留言功能
		case 6:	        modify();  break;	            //修改个人风采
		case 0:          exit(1);  break;               //直接退出
		default:printf("错误!");

		}
	}
}
