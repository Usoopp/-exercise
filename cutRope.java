package com.zeros_onesPackage;

public class cutRope {
	public static void main(String[] args) {
		int n = 1000;
		
//		long starttime = System.currentTimeMillis();
//		System.out.println("recur results without memo: "+P_cutRope_recur_flag(n,0));
//		long endtime = System.currentTimeMillis();
//		System.out.println("recur time: "+ (endtime - starttime)+" ms");
		
		long starttime1 = System.currentTimeMillis();
		int[] r = new int[n+1];
		for (int i =0;i<=n;i++) {
			r[i] = -1;
		}
		System.out.println("recur results with memo: "+P_cutRope_recur_flag_memo(n,0,r));
		long endtime1 = System.currentTimeMillis();
		System.out.println("recur_memo time: "+(endtime1 - starttime1)+" ms");
		
		long starttime2 = System.currentTimeMillis();
		System.out.println("recur results with bottomUp: "+P_cutRope_recur_flag_bottomUp(n));
		long endtime2 = System.currentTimeMillis(); 
		System.out.println("recur_memo time: "+(endtime2 - starttime2)+" ms");
		
		
	}
	
	
	
	//System.out.print(process1(i,0)+" ");

	public static int P_cutRope_recur(int n) {		//wrong！
		//recur resolve
		int maxvalue = 0;
		if (n < 2)
            return 0;
        else if (n == 2)
            return 1;
//        else if (n == 3)
//            return 2;
		for(int i =1; i<n; i++) {
			maxvalue = Math.max(maxvalue,i*P_cutRope_recur(n-i));
		}
		return maxvalue;
	}
	public static int P_cutRope_recur_flag(int n,int flag) {	//递归求解
		//recur resolve
		if (flag ==0) { //单纯的求n=2
			if(n==2) {
				return 1;
			}else if(n==3){
				return 2;
			}
		}
		int maxvalue = 0;
		
        if (n < 2) {
            return 1;
        }else if(n==2){
        	return 2;
        }else if(n==3) {
        	return 3;
        }
		for(int i =1; i<n; i++) {
			maxvalue = Math.max(maxvalue,i*P_cutRope_recur_flag(n-i,1));
		}
		return maxvalue;
	}
	public static int P_cutRope_recur_flag_memo(int n,int flag,int[] r) {	//自顶向下的备忘录法	
		//recur resolve
		if (flag ==0) { //单纯的求n=2
			if(n==2) {
				return 1;
			}else if(n==3){
				return 2;
			}
		}
		int maxvalue = 0;
	
		if(r[n]>=0) {
			return r[n];
		}
        if (n < 2) {
            return 1;
        }else if(n==2){
        	return 2;
        }else if(n==3) {
        	return 3;
        }
		for(int i =1; i<n; i++) {
			maxvalue = Math.max(maxvalue,i*P_cutRope_recur_flag_memo(n-i, 1, r));
		}
		r[n] = maxvalue;
		return maxvalue;
	}
	public static int P_cutRope_recur_flag_bottomUp(int n) { //自底向上的动态规划
		int[] P = new int[n+1];
		for (int i =0;i<=n;i++) {
			P[i] = -1;
		}
		if(n<2) {
			P[1] = 1;
			return P[1];
		}else if(n==2) {
			P[2] = 1;
			return P[2];
		}else if(n==3) {
			P[3] =2;
			return P[3] ;
		}
		for(int i = 1; i<4; i++) {
			P[i] = Math.max(i, P[i]);
		}
		for(int i =4; i<n+1; i++) {
			for(int j =1;j<=i/2; j++) {
				P[i] = Math.max(P[i],j*P[i-j]);   //先记住更小的子问题的求解
			}
		}
		return P[n];
	}

	

	
	
}
