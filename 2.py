import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import h5py
def sigmoid(z):
    s = 1/(1 + np.exp(-z))
    return s
    
w = np.zeros(shape=(2,1))
learning_rate = 0.009
print(w)
b = 0
x = np.array([[1,2], [3,4]]) 
print(x)
y = np.array([[1,0]])
print(y)

for i in range(2):
    
    #正向传播
    z = np.dot(w.T,x) + b
    A = sigmoid(z) 
    #反向传播
    dz = A - y
    dw = 1/2*x*dz.T
    db = 1/2*np.sum(dz)
    
    w = w - learning_rate * dw
    b = b - learning_rate * db  
    
