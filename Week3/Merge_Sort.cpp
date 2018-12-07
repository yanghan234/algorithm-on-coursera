#include "Sort.h"
#include <iostream>
#include <vector>
#include <fstream>
using namespace std;

class Merge_Sort : public Sort
{
    public:
        Merge_Sort(int size) : Sort(size) {};
        virtual void sort();
        void sort( vector<int> &arr, unsigned begin, unsigned end );
        void merge( vector<int> &arr, unsigned begin, unsigned mid, unsigned end);
};

void Merge_Sort::sort()
{
    unsigned begin = 0;
    unsigned end = _elems.size()-1;
    sort( _elems, begin, end );
}

void Merge_Sort::sort( vector<int> &arr, unsigned begin, unsigned end )
{
    if ( begin == end )
    {  return; }
    else if ( begin + 1 == end )
    {
        if ( arr[begin] > arr[end] )
            swap( arr[begin], arr[end] );
    }
    else
    {
        unsigned mid = ( begin + end ) / 2;
        sort( arr, begin, mid );
        sort( arr, mid+1, end );
        merge( arr, begin, mid, end);
    }
}

void Merge_Sort::merge( vector<int> &arr, unsigned begin, unsigned mid, unsigned end )
{
    unsigned i = begin;
    unsigned j = mid + 1;
    vector<int> aux;
    aux.reserve(end-begin+1);
    while ( i <= mid || j <= end )
    {
        if ( i > mid ) aux.push_back(arr[j++]);
        else if ( j > end ) aux.push_back(arr[i++]);
        else if ( arr[i] > arr[j] ) aux.push_back(arr[j++]);
        else aux.push_back(arr[i++]);
    }

    unsigned k = 0;
    while ( k < aux.size() )
    {
        arr[begin+k] = aux[k];
        k++;
    }
}


int main()
{
    unsigned Ntest = 20;
    unsigned N = 1;
    ofstream outFile("Merge_Sort_runtime.csv");
    outFile << "N,t" << endl;
    for ( unsigned itest = 0; itest < Ntest; itest++ )
    {
        N = N*2;
        Sort *ss = new Merge_Sort(N);
        ss->initialize();

        clock_t begin = clock();
        // ss->displayme();
        ss->sort();
        // ss->displayme();

        clock_t end = clock();

        cout << "N = " << ss->size() << ", RunTime: " <<  end-begin << ", isSorted?: " << ss->isSorted()  << endl;
        outFile << N << ", " << end-begin << endl;
    }
}
