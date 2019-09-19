package com.sort_algs;
import java.util.PriorityQueue;
import java.util.Scanner;
import static java.lang.Integer.parseInt;
import static java.lang.System.in;

import edu.princeton.cs.algs4.StdRandom;
//快速排序
public class Quicksort {
	public static void sort(int[] a) {
	StdRandom.shuffle(a); 	//打乱
		sort(a,0,a.length -1);
	}
	
	private static void sort(int[] a,int lo, int hi) {
		if (hi <= lo) return;          //只有一个元素时
		int j = partition1(a,lo,hi);   //切分
		sort(a, lo, j-1);				  //递归调用将左半部分a[lo,...,j-1]   排序 
		sort(a, j+1, hi);   			  //递归调用将右半部分a[j+1,...,hi] 排序
	}
	
	//pivot随着每次交换
	public static int partition(int[] a,int lo, int hi) {
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
	
	//pivot先固定不动，最后交换
	public static int partition1(int[] a,int low, int high) {  
		int i = low, j = high+1;   //因为pivot选的左侧第一个数，因此从右到左时，最后一个数不能忽略，所以是j=high+1
		int v = a[low];    //pivot, 切分元素
		while(true) {
			//跟上面的partition区别是不用判断i<j,如果判断了[0,2,1]这样的情况就无法正确切分
			//
			while(a[++i] < v) {  
				if(i == high) {
					break;
				}
			}
			while(a[--j] > v) {
				if(j == low) {
					break;
				}
			}
			//
			if(i >= j) {   
				break;
			}
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;

		} 
		
		int temp = a[j];
		a[j] = v;
		a[low] = temp;
			
		return j;
	}
	
	public static void main(String[] args) {
//		Scanner sc = new Scanner(in);    
//        String[] str = sc.nextLine().replace("[", "").replace("]", "").split(",");
//        int[] data = new int[str.length];
//        for (int i = 0; i < data.length; i++) {
//            data[i] = parseInt(str[i]);    
//        }
	int[] a = {6,8,1,4,9,0,3,4,5,2,7};
     
        Quicksort.sort(a);  
	for(int i = 0;i<a.length;i++) {
		System.out.println(a[i]);
	}       
//        sc.close();
	}
}
