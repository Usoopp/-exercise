# -*- coding: utf-8 -*-
# 20190907
# 背包问题
# https://blog.csdn.net/mu399/article/details/7722810
# https://www.cnblogs.com/Christal-R/p/Dynamic_programming.html
import numpy as np

name = ['a','b','c','d','e'];
#w = [0,4,5,6,2,2]
## 假色我们有5件物品，每件物品的重量为w[1:end]
#value = [0,6,4,5,3,6]
## 假色每件物品的价值为value[1:end]


w = [0,2,3,4,5]
value = [0,3,4,5,6]

y = np.zeros([len(w),9])
# 初始化
item = np.zeros([len(w),1])

def findMax(w, value, y): #动态规划 填表
    for i in range(1,len(w)): # 第i件物品
        for j in range(1,np.size(y,1)): #j所代表的是承重j的情况
            if(j < w[i]):   # 第i件装不进
                #y[i][j]代表前i件物品在承重j的情况下的最大价值
                y[i][j] = y[i-1][j] #y[1][1]=y[0][1]
            else:  # 能装
                #不装价值大
                if(y[i-1][j] > y[i-1][j-w[i]] + value[i]):  
                    y[i][j] = y[i-1][j]
                #装了价值大
                else:
                    y[i][j] = y[i-1][j-w[i]] + value[i]  
    #一直遍历到i=0结束为止，所有解的组成都会找到
               
 
# 递归查找
def findWhat(i, j, w, value):  #寻找有i件可选的情况下承重为j的时候 解的组成方式
    if(i >= 0):
        if(y[i][j] == y[i-1][j]):
            item[i] = 0     # 标记物品未被选中
            findWhat(i-1, j, w, value)  #
        elif(j-w[i]>=0 and y[i][j] == y[i-1][j-w[i]] + value[i]):
            item[i] = 1     # 标记物品已被选中
            findWhat(i-1, j-w[i], w, value) #回到装第i个物品之前的位置
    return item
            

# 利用状态转移公式简化空间，对j逆序访问，不然的话后面会被前面的已经修改的干扰
def findMaxBetter(w, value, y): 
    B = np.zeros([np.size(y,1)+1,1])
#    B = y
    for i in range(1,len(w) ):
        #ｊ需要逆序，因为不然的话
        for j in range(np.size(y,1) ,0,-1):
            if(j-w[i]>=0 and B[j] <= B[j - w[i]] + value[i] ):
                B[j] = B[j - w[i]] + value[i]
                # B(j)= max{B(j), B(j-w(i))+v(i)}；
                # 只考虑前i个物品的情况下的 不同的承重j对应的最优价值
    return B
    


findMax(w, value, y)
print(y)

i=4
j=8
items = findWhat( i, j,w, value)
print(items)

y = np.zeros([len(w),8])
B = findMaxBetter(w, value, y)
print(B)
