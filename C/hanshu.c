#include "hanshu.h"
#include "stdio.h"
#include "conio.h"
#include "string.h"
#include "stdlib.h"
#include "windows.h"                                   //Sleep延时

typedef struct
{
	char name[20];
	char password[18];
}User;


int visit_flag = 0;                                       //声明一个全局变量，判断是否有游客登录
int admin_flag = 0;                                       //声明一个全局变量，判断是否是管理员登录
User* zhizhen = NULL;		                              //定义全局指针，指向登录用户名

void modify()                                           //打开txt函数
{
	system("C:\\Users\\zhangxiao\\Desktop\\kcsj自主设计\\info.txt");    //dos命令函数
	printf("按任意键返回界面");
	getch();
}

void welcome()                                //我的个人信息函数
{
	FILE* fp;								  //声明fp是指向文件类型的指针
	char c;
	int a;

	system("CLS");		                      //清屏

	//打印个人风采
	fp = fopen("info.txt", "rt+");				  //打开个人信息文件
	while ((c = fgetc(fp)) != EOF)                 //从文件中读字符存放到变量c 中
		putchar(c);                               //把变量c 的内容输出到屏幕上

	printf("1.修改个人信息  2.查看访客   3.返回主界面");
	fclose(fp);								  //关闭文件

	scanf("%d", &a);

	if (a == 1)                                 // 修改个人信息
	{
		if (admin_flag == 1)                    //判断管理员是否登录
		{
			modify();
		}
		else
		{
			system("cls");
			printf("对不起，你不是管理员\n");
			printf("请先登录管理员账号\n");
			getch();
		}
	}
	else if (a == 2)                            //查看访客
	{
		if (admin_flag == 1)
		{
			read_user();
		}
		else
		{
			system("cls");
			printf("\t对不起，你不是管理员\n\n");
			printf("\t请先登录管理员账号\n\n");
			printf("\t请按任意键返回主界面登录\n");
			getch();
		}

	}
	else                                      //返回主界面
	{
		;
	}
}



void regist()                                  //注册函数           
{
	User user, temp;
	FILE* fp;								   //声明fp是指向文件类型的指针  
	int fr;

	system("CLS");

	printf("\n\n\n\t\t\t  用户名注册\n\n");
	printf("\t\t\t用户名：");
	scanf("%s", user.name);
	printf("\t\t\t密码：");
	scanf("%s", user.password);


	if ((fp = fopen("user.txt", "rt+")) == NULL)     //打开存储用户密码的txt文件
	{
		printf("cannot open file \n");
		exit(0);
	}

	while (1)
	{
		fr = fread(&temp, sizeof(User), 1, fp);       //读取用户信息到temp
		if (fr == 0)
		{
			break;
		}
		if (strcmp(temp.name, user.name) == 0)       //判断是否重复注册
		{
			printf("\n\n\t\t用户已经存在,请重新注册！\n");
			printf("\n\t\t请重新注册        \n");
			printf("\n\t\t请按任意键注册\n");
			fclose(fp);
			getch();
			regist();         //在函数里调用函数自己好吗？
		//	getch();

		}

	}
	fclose(fp);


	//存储数据  
	fp = fopen("user.txt", "at+");
	if (fwrite(&user, sizeof(User), 1, fp) != 1)
	{
		printf("file write error");
	}

	fclose(fp);
	system("CLS");
	printf("注册成功！\n");
	log();                                                 //注册完后登录

}


void log()                                                 //登录函数
{
	User user, temp;
	int fr;
	FILE* fp;											   //声明fp是指向文件类型的指针 
	int a, i = 0;

	system("CLS");

	printf("\n\n\t\t\t  用户登录\n\n");
	printf("\t\t\t用户名：");
	scanf("%s", user.name);

	printf("\t\t\t密码：");
	scanf("%s", user.password);

	zhizhen = &user;                                      //全局指针指向现有登录账号

	if ((fp = fopen("user.txt", "rt+")) == NULL)              //打开文件
	{
		printf("cannot open this file");
	}


	while (1)                                           	//把文件里的数据读出来
	{

		fr = fread(&temp, sizeof(User), 1, fp);              //读取txt里的用户信息到结构体变量中

		if (fr == 0)                                      //判断是否读完文件
		{
			break;
		}

		if ((strcmp(temp.name, user.name) == 0) && (strcmp(temp.password, user.password) == 0))   //边读文件边匹配账户密码,用户信息正确
		{

			system("CLS");
			visit_flag = 1;                                 //标记访客已登录

			printf("\n\t恭喜你，你已登录!\n你当前的账号是：      %s\n", zhizhen->name);
			printf("\n");
			printf("1.访问个人信息\t\t2.留言\t\t3.按任意键返回主界面\n");
			scanf("%d", &a);

			if (a == 1)
			{
				// fclose(fp);
				welcome();						     	//访问个人信息
			}

			if (a == 2)
			{
				///fclose(fp);
				add_message();							//留言
			}
			else
			{
				//	 fclose(fp);
				break;                                 //按其余任意键跳出循环
			}
		}

	}
	if (visit_flag == 0)
	{
		printf("你的账户或密码不正确，请重新登录！\n");
		printf("3秒后请重新登录\n");
		Sleep(1000);
		printf("2秒后请重新登录\n");
		Sleep(1000);
		printf("1秒后请重新登录\n");
		Sleep(1000);
		log();
	}
	fclose(fp);
}



void read_user()                                           //查看所有的用户账号和密码
{
	FILE* fp;											   //声明fp是指向文件类型的指针
	int fr;
	User temp;

	system("CLS");

	if ((fp = fopen("user.txt", "rt+")) == NULL)                 //打开文件
	{
		printf("cannot open this file");
	}

	printf("用户名          密码\n");

	while (1)
	{
		fr = fread(&temp, sizeof(User), 1, fp);
		if (fr == 0)
		{
			break;
		}
		printf("%s\t\t%s\n", temp.name, temp.password);    //打印出所有的用户信息
	}
	printf("按任意键返回主界面");
	getch();

}

void view_message()
{
	int num, num1;
	char c;
	FILE* fp;

	system("cls");                                    //清屏

	if ((fp = fopen("message.txt", "at+")) == 0)            //以读取/追加方式打开文件，并判断其返回值
	{
		printf("Can't open this file\n");
		exit(0);
	}

	printf("                             留言信息如下\n");
	while ((c = fgetc(fp)) != EOF)                        //从文件中读字符存放到变量c中
	{
		putchar(c);                                  //把变量c 的内容输出到屏幕上
	}

	printf("\n                           你想留言吗?\n\n\t\t\tl.留言\t2.返回主界面\n");

	scanf("%d", &num);
	if (num == 1)							             //判断游客是否登录
	{
		if (visit_flag == 1)
		{
			add_message();                           //登录即调用留言函数
		}
		else
		{
			system("cls");
			printf("\n\t\t            你还没有登录，请先登录：\n\n");
			printf("\t\t1.登录\t\t2.注册\t\t3.按其他键返回主界面\n");
			scanf("%d", &num1);

			if (num1 == 1)                            //选择登录
			{
				log();
			}
			else if (num1 == 2)			            //选择注册
			{
				regist();
			}
			else
			{
				;
			}
		}

	}
	else
	{
		//getch();	//获取回车符
	}
	fclose(fp);
}

void add_message()
{
	char c;
	FILE* fp;

	system("cls");
	if ((fp = fopen("message.txt", "at+")) == 0)            //以读取/追加方式打开文件，并判断其返回值
	{
		printf("Can't open this file\n");
		exit(0);
	}

	printf("请输入你的留言:\n");
	fprintf(fp, "%s:", zhizhen->name);
	getchar();
	while ((c = getchar()) != '\n')                      //接收一个从键盘输入的字符并赋给变量c,输入回车符则循环结束		
	{
		fprintf(fp, "%c", c);
	}



	printf("留言结束，请按任意键返回主界面！");

	getchar();   //
	fprintf(fp, "\n");
	fclose(fp);

}

void adminlog()                                      //管理员登录功能
{

	int i, j = 0;

	char a[10] = "admin";
	char username[15], pwd[10];
	while (1)
	{

		system("cls");
		printf("\n\n                              管理员登陆");
		printf("\n\n         请输入账号:");
		fflush(stdin);                              //清空输入缓冲区
		gets(username);

		printf("\n         请输入密码:");
		fflush(stdin);
		gets(pwd);

		if (admin_flag == 1)
		{
			printf("\n\n\t你已登录\n");
			printf("\n\t按任意键返回主界面");
			getch();
			break;
		}
		if ((strcmp(username, a) == 0) && (strcmp(pwd, a) == 0))
		{

			admin_flag = 1;
			welcome();
			break;
		}
		else
		{
			system("cls");
			printf("\n 用户名密码错误!请重新输入账号密码!\n");
		}


		printf("1.继续登录\t\t2.返回主界面\n");

		scanf("%d", &i);

		if (i == 1)
		{
			adminlog();
		}
		else
		{
			break;
		}
	}

}
