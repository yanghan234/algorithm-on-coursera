#include "Sort.h"
#include "Point2D.h"
#include <vector>

#ifndef MERGE_SORT_H
#define MERGE_SORT_H
using namespace std;

template < typename T >
class MergeSort : public Sort<T>
{
    public:
        MergeSort(unsigned size) : Sort<T>(size) {};
        virtual void sort();
        void sort( vector<T> &arr, unsigned begin, unsigned end );
        void merge( vector<T> &arr, unsigned begin, unsigned mid, unsigned end);
};

#endif
