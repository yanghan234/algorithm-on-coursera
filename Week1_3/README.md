---
title: 排序算法(Sorting Algorithms)
date: 2018-12-21 21:02:29
tags: 算法 algorithms
---

# 几种常用的排序算法 #

<!-- more -->
## 复杂度为$O(N^2)$的几种算法 ##
符合此种复杂度的排序算法最为直观。
以下介绍冒泡排序(Bubble sort)，选择排序(Selection sort), 插入排序(Insertion sort)
### 冒泡排序(Bubble sort) ###
两个指针同时扫，每当右指针数值小于左指针，则交换之。
```c++
// vector<int> arr has been declared and initialized;
for ( unsigned i = 0; i < arr.size()-1; i++ )
{
    for ( unsigned j = i+1; j < arr.size(); j++ )
    {
        if ( arr[j] < arr[i] )
            // swap has been defined else where
            swap( arr[i], arr[j] );
    }
}
```

### 选择排序(Selection sort) ###
两个指针i, j同时扫遍数组，右侧指针始终查找i之后所有元素中的最小值。若该最小值小于i位上的值，则交换之。
```c++
// vector<int> arr
for ( unsigned i = 0; i < arr.size()-1; i++ )
{
    unsigned minIndex = i+1;
    for ( unsigned j = 0; j < arr.size(); j++ )
    {
        if ( arr[j] < arr[minIndex] )
            minIndex = j;
    }
    swap(arr[i], arr[minIndex]);
}
```

### 插入排序(Insertion sort) ###
两个指针从右向做扫遍数组。对于每一个右指针，左指针向前寻找到存放右指针数值的正确位置，并插入之。
```c++
// vector<int> arr;
for ( int j = arr.size()-1; j >= 1; j-- )
{
    int val = arr[j];
    for ( int i = j-1; i >= 0; i-- )
    {
        // i could be -1, so use int not unsigned
        if ( a[i] > val )
            a[i+1] = a[i];
    }
    a[i+1] = val;
}

```
对于已经大致排序好的数组，插入排序的复杂度可能达到接近O(N)的水平。但是，如果是已经完全逆向排序好的数组，插入排序的表现甚至比选择排序要差。


## 复杂度为O(NlogN)的几种算法 ##
常见的NlogN算法有合并排序(Merge sort)，快速排序(Quick sort)和堆排序(Heapsort)。此处介绍合并排序和快速排序，堆排序将在优先队列中提及。合并排序和快速排序都有分治法的思想(Divid-and-conquer)。合并排序的优点在于稳定性，缺点在于非原位，因而需要额外内存。 快排不需要额外内存，原位地(in-place)排序。但快排丢失了稳定性。就效率来说，虽然二者都是NlogN的复杂度，但是平均来说快排还是要比合并排序和堆排序要快一些。

### 合并排序 ###
合并排序将数组不断划分为左右两个部分，然后对左右两部分再进行划分直至当前处理的数组只包含一到两个数值，此时可以直接返回，或者交换数值再返回。一旦左右两部分排序完成并返回后，此时对左右两侧进行合并。这正是合并排序名称的由来。
假定数组中存在N个元素，则进行上述划分的操作至多不超过log2(N)次。

代码详见：
[Merge sort in C++](https://github.com/yanghan234/algorithm-on-coursera/blob/master/Week3/Merge_Sort.cpp)

### 快速排序 ###
快速排序首先从数组中选择一个数值作为参照物，然后对当前数组进行调整，使得参照物回到正确位置上，即该数左侧所有数值都应小于参照物，右侧所有数值都应大于参照物。但此时左右两个部分内部的顺序依然没有排定，因此对左右两个部分分别再进行上述操作。

代码详见：
[Quick sort in C++](https://github.com/yanghan234/algorithm-on-coursera/blob/master/Week3/Quick_Sort.cpp)

## 复杂度介于O(N^2)和O(NlogN)之间的排序 ##
### 希尔排序(Shell sort) ###
此种排序采用了与插入排序类似的思想。上面提到，插入排序对于已经大致排好序的数组效率较高。同时插入排序可以视作是一种间距为1的排序，即每个数与其相聚为1的数进行比较。Shell排序采用如下策略，首先对用一个大间隔进行排序，然后逐步缩小间隔至1，此时就蜕化成了普通的插入排序。由于此时数组已经大致排好序，因此插入排序的表现也会比较好。

然后，采用何种间隔进行希尔排序以达到最好效果还难以明确。常用的是下面的序列，
1, 4, 13, 40, 121, ...
f(n) = 3f(n-1) + 1

代码详见
[Shell sort in C++](https://github.com/yanghan234/algorithm-on-coursera/blob/master/Week3/Shell_Sort.cpp)
