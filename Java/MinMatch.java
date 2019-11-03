package com.edu.algs4;
import java.util.Scanner;
// 最短包含子串的长度
public class strmatch {
	   public static String solution(String longString, String alphabet) {
		   
	        char[] chas_long = longString.toCharArray();
	        char[] chas_alpha = alphabet.toCharArray();
	        String substr = null;
	        
	        //滑动窗
	        int left = 0;
	        int right = 0;
	        int match = chas_alpha.length;   //match表示滑动窗缺少alphabet中多少个字符，初始值为alphabet的长度
	        int minLen = Integer.MAX_VALUE;
	        
	        int[] map = new int [256];
	        for(int i =0; i!=chas_alpha.length; i++) {
	        	map[chas_alpha[i]]++;    //	哈希表 :滑动窗缺少alphabet的数量
	        	// 如果map[chas_alpha[i]]>0,说明滑动窗缺少map[chas_alpha[i]]个chas_alpha[i]
	        	// 如果map[chas_alpha[i]]=0,说明滑动窗不缺也不多chas_alpha[i]
	        	// 如果map[chas_alpha[i]]<0,说明滑动窗多了map[chas_alpha[i]]个chas_alpha[i]
	        }
	        

	        while(right != chas_long.length) {
	        	map[chas_long[right]]--;
	        	if(map[chas_long[right]]>=0) {
	        		match--;
	        	}
	        	if(match==0) {    // match==0表示滑动窗已经不缺alphabet的字符。但需要从左侧开始判断是否滑动窗是否可以继续缩小。
	        		while(map[chas_long[left]]<0) {   //从左侧开始测试滑动窗是否有多的alphabet字符，有则滑动窗左侧开始移动
	        			map[chas_long[left++]]++;     //left++表示滑动窗移动（删除滑动窗的左侧的一个元素），因此对于此元素的缺少数量就应该+1.
	        		}
	        		if (minLen > right-left+1) {
	        			//记录最优的滑动窗
	        			substr = longString.substring(left,right+1);
	        			minLen = right - left + 1;
	        		}
	        		//滑动窗的右侧开始扩展，之后的滑动窗如果有更小的，那么也是滑动窗的左侧也是从现在的left之后的位置开始		
	        		map[chas_long[left++]]++;
	        		match++;	//已经符合要求的滑动窗的左侧的字符一定是alphabet的字符，因此left++后滑动窗一定会缺少alphabet中的字符。
	        	}
	        	right++;
	        }
	        
//			//另一种写法
//			  while(right < chas_long.length){ 
//				  if(map[chas_long[right++]]-->0) { 
//					  --match;
//				  } 
//				  while(match==0) { 
//					  if(right-left<minLen) { 
//						  minLen = right- left; 
//						  substr = longString.substring(left,right);
//					  } 
//					  if(map[chas_long[left++]]++ == 0) {
//				  ++match; 
//					  } 
//				} 
//			}
		 
	        return substr;
	    }
	    public static void main(String[] args) {
	            Scanner in = new Scanner(System.in);
	            String longString = in.next();
	            String alphabet = in.next();
	            System.out.println(solution(longString, alphabet));
	    }
}
