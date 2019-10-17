//去哪儿编程题
//二项式系数求解

import java.io.IOException;
import java.util.Scanner;

public class Bicoff {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int n = Integer.parseInt(str);
		if (n==0) {
			System.out.print(1);
		}else {
			long tmp=1;
			for(int i =1;i<n+1;i++) {		
				System.out.print(tmp+" ");
				tmp = tmp*(n-i+1);
				tmp = tmp /i;
			}
			System.out.print(tmp+" ");
		}
			
		//System.out.println(n);

	}
}
