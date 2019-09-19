package com.sort_algs;

import edu.princeton.cs.algs4.StdRandom;
//快速排序
public class Quicksort {
	public static void sort(int[] a) {
		StdRandom.shuffle(a); 	//打乱
		sort(a,0,a.length -1);
	}
	
	private static void sort(int[] a,int lo, int hi) {
		if (hi <= lo) return;
		int j = partition1(a,lo,hi);   //切分
		sort(a, lo, j-1);				  //递归调用将左半部分a[lo,...,j-1]   排序 
		sort(a, j+1, hi);   			  //递归调用将右半部分a[j+1,...,hi] 排序
	}
	
	//pivot随着每次交换
	public static int partition(int[] a,int lo, int hi) {
		int i = lo, j = hi + 1;
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
	public static int partition1(int[] a,int lo, int hi) {  
		int i = lo, j = hi;
		int v = a[lo];    //pivot, 切分元素
		while(i < j) {
			while (i < j && a[j] > v) {
				j--;
			}
			while(i < j && a[i] < v) {
				i++;
			}
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;

		} 
		int temp = a[i];
		a[i] = v;
		a[lo] = temp;
		return i;
	}
	
	public static void main(String[] args) {
		int[] a = {6,1,4,9,0,3,3,5,2,7,8};
		Sort.sort(a);
		for(int i = 0;i<a.length;i++) {
			System.out.println(a[i]);
		}
	}

}
