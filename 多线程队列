# -*- coding: utf-8 -*-
"""
Created on Fri Sep 20 17:12:08 2019

@author: shen
"""
# https://www.jianshu.com/p/f58ff94ec92b
from time import ctime,sleep
import time
import threading
import queue
exitFlag = 0

class myThread(threading.Thread):
    def __init__(self,threadID,name,q):
        threading.Thread.__init__(self)
        self.threadID = threadID
        self.name = name
        self.q = q
    def run(self):
        print("开始线程：" + self.name)
        process_data(self.name,self.q)
        print("退出线程："+self.name)
        

def process_data(threadName,q):
    while not exitFlag:
        queueLock.acquire()         # 获取锁，用于线程同步
        if not workqueue.empty():   # when workQueue.empty() is 0
            data = q.get()          # q为队列，获取队列
            queueLock.release()
            print ("%s processing %s" % (threadName, data))
        else:  #如果workqueue为空
            queueLock.release()
        time.sleep(1)
            
threadlist = ["Thread-1","Thread-2","Thread-3"]
namelist = ["one","two","three","four","five"]
queueLock = threading.Lock()
workqueue = queue.Queue(10)   # 创建先入先出队列
threads = []
threadID = 1
            
 #创建新线程
for tName in threadlist:
    thread = myThread(threadID,tName,workqueue)
    thread.start()
    threads.append(thread)
    threadID += 1
#填充队列
queueLock.acquire()      # 获取锁，用于线程同步
for word in namelist:
    workqueue.put(word) # 写入队列
queueLock.release()     # 释放锁，开启下一个线程

# 等待队列清空
while not workqueue.empty(): # empty()为空返回真，not 0 为真，当空之后才跳出循环
    pass
    
# 通知线程是时候退出
exitFlag = 1

# 等待所有线程完成
for t in threads:
    t.join()
print("退出主线程")

