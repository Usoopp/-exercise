# -*- coding: utf-8 -*-
"""
Created on Sun Aug 25 23:11:55 2019

@author: XiaoZh
"""
import numpy as np
import time

def fib0(n):
    if (n <= 0):
        return 0
    if (n == 1):
        return 1;
    return fib0(n-1) + fib0(n-2)





#自顶向下的备忘录法   
def fib1_c(n, Memo):
    if(Memo[n] != -1):
        return Memo[n]
    if(n <= 2):
        Memo[n] = 1
    else: Memo[n] = fib1_c(n-1, Memo) + fib1_c(n-2, Memo)
    return Memo[n]

def fib1(n):
    if (n<=0): 
        return 0;
    Memo = np.zeros(n+1)
    for i in range(0,n+1):
        Memo[i] = -1
    return fib1_c(n, Memo)

# 自底向上的动态规划
def fib2(n):
    if(n <= 0):
        return n;
    
    memo_0,memo_1 = 0, 1
    for i in range(2, n+1):
        memo = memo_0 + memo_1
        memo_0 = memo_1
        memo_1 = memo
        
    return memo


def fib(n):
    pre_1, pre_2, cur = 0, 1, n
    for i in range(1, n+1):
        cur = pre_1 + pre_2
        pre_2 = pre_1
        pre_1 = cur
        
        #pre_1, pre_2 = cur, pre_1
    return cur
n = 8
y1= fib0(n)
y2 = fib1(n)
y3 = fib2(n)


def cut(p,n):
    if(n==0):
        return 0
    q = -1
    for i in range(1, n+1):
        q = max(q,p[i-1]+cut(p,n-i))
    return q;


def cut_c(p, n, r):
    q = -1
    if(r[n] >= 0):
        return r[n]
    if(n == 0): 
        q = 0
    else:
        for i in range(1, n+1):
            q = max(q, p[i-1] + cut_c(p, n-i, r))
    r[n] = q
    return q 

def cutMemo(p, n):
    r = np.zeros(len(p)+1) - 1
    return cut_c(p,n,r)


    
#自底向上的动态规划
def buttom_up_cut(p, n):
    r = np.zeros(n + 1)
    for i in range(1, n+1):
        q = -1
        for j in range(1,i+1):
            q = max(q,p[j-1] + r[i-j])
        r[i] = q
    return r[n]


p=[1,5,8,9,10,17,17,20,24,30]   
t00 = time.clock()
y4 = cut(p, 10)
t01 = time.clock()
t0 = t01 - t00
print(t0)
y5 = cutMemo(p, 5)
t10 = time.clock()
y6 = buttom_up_cut(p, 10)
t11 = time.clock()
t1 = t11 - t10
print(t1)

