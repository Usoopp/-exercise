/*题目描述
小易觉得高数课太无聊了，决定睡觉。不过他对课上的一些内容挺感兴趣，所以希望你在老师讲到有趣的部分的时候叫醒他一下。你知道了小易对一堂课每分钟知识点的感兴趣程度，并以分数量化，以及他在这堂课上每分钟是否会睡着，你可以叫醒他一次，这会使得他在接下来的k分钟内保持清醒。你需要选择一种方案最大化小易这堂课听到的知识点分值。
输入描述:
第一行 n, k (1 <= n, k <= 105) ，表示这堂课持续多少分钟，以及叫醒小易一次使他能够保持清醒的时间。
第二行 n 个数，a1, a2, ... , an(1 <= ai <= 104) 表示小易对每分钟知识点的感兴趣评分。
第三行 n 个数，t1, t2, ... , tn 表示每分钟小易是否清醒, 1表示清醒。
输出描述:
小易这堂课听到的知识点的最大兴趣值。
示例1
输入

6 3
1 3 5 2 5 4
1 1 0 1 0 0
输出

16
*/

package com.zeros_onesPackage;
import java.util.Scanner;

public class Gaoshu_Sleep {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();   	// 6 这堂课持续的时间 
		int k = scan.nextInt();   	// 3 小易保持清醒的时间 
		int[] val = new int[n];		//每分钟知识点的感兴趣评分
		int[] state = new int[n];	//每分钟是否清醒
		
		//保存瞌睡时的累计评分
		int sleep = 0;
		int[] sleepval = new int[n];
		for(int i = 0; i< n;i++) {
			val[i] = scan.nextInt();   //输入
		}
		for(int i = 0;i<n;i++) {
			state[i] = scan.nextInt();
			if(state[i] == 0) {
				sleep += val[i];  		
			}
			sleepval[i] = sleep;  		//叫醒后累加的评分
		}
		
		Gaoshu_Sleep ma = new Gaoshu_Sleep();
		int res = ma.getMaxVal(val,state,n,k,sleepval);
		System.out.println(res);
	}
	
	public int getMaxVal(int[] val,int[] state,int n,int k,int[] sleepval ) {
		int res = 0;
		int addval = 0;
		for(int i=0;i<n;i++) {
			if(state[i]==1) res += val[i]; 	//清醒时的得分
			else {
				int wakeval = 0;
				if(i+k-1>=n) {
					//	超出界限的情况
					wakeval = (i>0)?(sleepval[n-1]-sleepval[i-1]):sleepval[n-1];
				}else {
					wakeval = (i>0)?(sleepval[i+k-1] - sleepval[i-1]):sleepval[i+k-1];
					// i=0时，sleepval[k-1]
				}
				addval = addval>=wakeval?addval:wakeval;
			}
			
		}
		return res+addval;
	}
}
