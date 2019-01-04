#!/usr/bin/env python3

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("runtime.csv")

res = np.polyfit(df['N'],df['t'],2)
print(res)
p = np.poly1d(res)

xx = np.logspace(1,20)
yy = p(xx)

plt.loglog(df['N'],df['t'],'o')
plt.loglog(xx,yy)
plt.show()
