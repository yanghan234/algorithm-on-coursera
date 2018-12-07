#!/usr/bin/env python3

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("Points.csv")

plt.plot(df['x'],df['y'],'o')
plt.show()
