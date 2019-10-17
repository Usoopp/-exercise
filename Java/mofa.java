//去哪儿编程题
//过桥问题
import java.util.Scanner;

public class mofa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int n = Integer.parseInt(str);
		String str1 = sc.nextLine();
		String str2 = str1.toString().substring(0,str1.length());
        String[] str3=str2.split(" ");
        int[] data=new int[str3.length];
        for(int i=0;i<str3.length;i++) {
           data[i]=Integer.parseInt(str3[i]);
        //   System.out.print(data[i]+" ");
        }
        sort(data,0,data.length-1);
        int time;
        time = magicEscape(data,data.length);
        System.out.println(time);
        
        //动态规划
        int[] ans = new int[data.length+1];
        ans[0] = 0;
        ans[1] = data[0];//初始状态
        ans[2] = data[1];
        for(int i=3;i<data.length+1;i++) {
            ans[i] = Math.min(ans[i-1] + data[0] + data[i-1], ans[i-2] + data[0] + data[i-1] + 2*data[1]);
        }
        System.out.println(ans[data.length]);
    
		
	}
	public static int magicEscape(int[] data,int n) {
		int time;
		if(n==1) {
			time = data[0];
			return time;
		}else if(n==2) {
			return data[1];
		}else if(n==3) {
			return data[0]+data[1]+data[2];
		}else {  
			if(2*data[1]>data[0]+data[n-2]) {  //每次都是最快的过去
	            time = 2*data[0]+data[n-1]+data[n-2];   

			}else {  //先 次快+最快，返回最快，再次慢+最慢，返回次快；
				time = data[0]+data[1]+data[n-1]+data[1];  //送了两个慢的过去
			}
			//这里的效果是用最短的时间把最慢的两个家伙给送过去。为什么要比较，比如
			
			return time + magicEscape(data,n-2);
		}
		
	}
	private static  void sort(int[] a,int lo, int hi) {
		if (hi <= lo) return;
		int j = partition(a,lo,hi);   //切分
		sort(a, lo, j-1);				  //递归调用将左半部分a[lo,...,j-1]   排序 
		sort(a, j+1, hi);   			  //递归调用将右半部分a[j+1,...,hi] 排序
	}
	public static  int partition(int[] a,int lo, int hi) {
		int i = lo, j = hi;
		int v = a[lo];    //pivot, 切分元素
		while(i < j) {
			while (i < j && a[j] > v) {
				j--;
			}
			if(i < j) {
				a[i] = a[j];  //v跟a[j]交换
				i++;
			}
			while(i < j && a[i] < v) {
				i++;
			}
			if(i < j) {
				a[j] = a[i];  //i
				j--;
			}	
		} 
		a[i] = v;
		return i;
	}

}
