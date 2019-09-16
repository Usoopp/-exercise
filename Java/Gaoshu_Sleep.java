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
