#!/usr/bin/env python3

import numpy as np

def generate(dim):
    f = open(str(dim)+"x"+str(dim)+".txt","w")
    f.write("%d\n"%(dim*dim))
    f.write("%d\n"%(dim*(dim-1)*2))
    for i in range(dim):
        for j in range(dim-1):
            v = i*dim+j
            w = i*dim+j+1
            f.write("%5d %5d %6.2f\n"%(v,w,np.random.random()))
    for i in range(dim-1):
        for j in range(dim):
            v = i*dim+j
            w = (i+1)*dim+j
            f.write("%5d %5d %6.2f\n"%(v,w,np.random.random()))


if __name__ == "__main__":
        generate(20)
