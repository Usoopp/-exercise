# -*- coding: utf-8 -*-
"""
Created on Sun Sep  8 16:00:45 2019

@author: XiaoZh
"""
#
#链接：https://www.nowcoder.com/questionTerminal/46e837a4ea9144f5ad2021658cb54c4d
#来源：牛客网
# 链接：https://www.nowcoder.com/questionTerminal/46e837a4ea9144f5ad2021658cb54c4d
#来源：牛客网
#
#为了找到自己满意的工作，牛牛收集了每种工作的难度和报酬。牛牛选工作的标准是在难度不超过自身能力值的情况下，牛牛选择报酬最高的工作。在牛牛选定了自己的工作后，牛牛的小伙伴们来找牛牛帮忙选工作，牛牛依然使用自己的标准来帮助小伙伴们。牛牛的小伙伴太多了，于是他只好把这个任务交给了你。
#
#输入描述:
#每个输入包含一个测试用例。
#每个测试用例的第一行包含两个正整数，分别表示工作的数量N(N<=100000)和小伙伴的数量M(M<=100000)。
#接下来的N行每行包含两个正整数，分别表示该项工作的难度Di(Di<=1000000000)和报酬Pi(Pi<=1000000000)。
#接下来的一行包含M个正整数，分别表示M个小伙伴的能力值Ai(Ai<=1000000000)。
#保证不存在两项工作的报酬相同。
#
#
#输出描述:
#对于每个小伙伴，在单独的一行输出一个正整数表示他能得到的最高报酬。一个工作可以被多个人选择。
#示例1
#输入
#3 3 
#1 100 
#10 1000 
#1000000000 1001 
#9 10 1000000000
#输出
#100 
#1000 
#1001




import sys
import bisect  #bisect是个二分法排序模块
import numpy as np

# no pass and don't know why
def forjob1():
#    lines = sys.stdin.readlines()                   
    lines = ['3 4\n', '1 100\n', '10 1000\n', '1000000000  1001\n', '9 10 8 1000000000 \n']
    lines = [l.strip().split() for l in lines if l.strip()]
    n, m = int(lines[0][0]), int(lines[0][1])
    Di_Pi = []
    for index, l in enumerate(lines[1:-1]): # {0: ['1','100'], 1:['10','1000'], 2:['100000','1001']}
        Di_Pi.append([int(l[0]), int(l[1])])  # l[1] [难度，报酬]，方便以难度排序
    Ai = list(map(int, lines[-1]))
    Di_Pi.sort() # or sorted(Di_Pi)按工作的难度排序，复杂度为O(nlgn)
    Ai.sort()    # 按员工的能力排序，复杂度为O(nlgn)
        #因为员工要选的工作是可以重复的，因此员工要选的工作就是他能力范围内最大报酬的工作
        #因此工作的报酬可以根据难度 更新为其难度范围内最大 报酬
    mx = 0
    for i in range(n):
        mx = max(mx, Di_Pi[i][1])
        Di_Pi[i][1] = mx  #更新报酬
    #更新完毕的报酬值可以跟能力值相互对应了
    
    # 如果员工p的能力大于当前工作i的难度，那么员工p只能胜任i-1的工作，
    # 下一个员工p+1因为能力大于等于p，因此他就可以从i-1开始挑。
    left = 0
    index = 0
    temp = np.zeros([m,1])
    while(left < m and index < n):
        if(Ai[left] >= Di_Pi[index][0]): #
            index =index +1
        else:
            temp[left] = Di_Pi[index-1][1]
            left = left + 1
        
        
        
    for i in range(left,m):
        temp[i] = Di_Pi[n-1][1]
        
    print(temp)
   
def forjob2():
    lines = sys.stdin.readlines()
    lines = [l.strip().split() for l in lines if l.strip()]
    n, m = int(lines[0][0]), int(lines[0][1])
    res = [0] * (n + m)
    abilities = list(map(int, lines[-1]))
    maps = dict()
    for index, l in enumerate(lines[1:-1]):
        d, s = int(l[0]), int(l[1])
        maps[d] = s
        res[index] = d
    for index, ability in enumerate(abilities):
        res[index + n] = ability
        if ability not in maps:
            maps[ability] = 0
    res.sort()
    maxSalary = 0
    for index in range(n + m):
        maxSalary = max(maxSalary, maps[res[index]])
        maps[res[index]] = maxSalary
    for index in range(m):
        print(maps[abilities[index]])

    
def forjob3():
    task = {}
#    lines = sys.stdin.readlines()
    lines = ['3 3\n', '1 100\n', '10 1000\n', '1000000000  1001\n', '9 10  1000000000 \n']

    n, m = map(int, lines[0].strip().split())
    for line in lines[1:-1]:
        if not line.strip().split():
            continue
        a, b = map(int, line.strip().split()) # 获得难度和报酬
        
        task[a] = max(task.get(a, 0), b)        # get(key, default = 0) if key 不存在，返回default
    # 按难度 排序，arr只是难度
    arr = sorted(task.keys()) 
    # 更新报酬 使报酬在难度范围内是最高的
    for i in range(1, len(arr)):               
        if task[arr[i]] < task[arr[i -1]]:
            task[arr[i]] = task[arr[i -1]]
            
    skills = map(int, lines[-1].strip().split())
    for skill in skills:
        if skill in task:
            print(task[skill])
        else:
            ind = bisect.bisect(arr, skill)  #bisect(arr,skill)返回skill在arr的索引
            # 如果skill在arr不存在，返回离稍大于目标最近的一次。
            if ind == 0:
                print(0)
            else:
                print(task[arr[ind -1]])
    
if __name__ == '__main__':
    forjob3()