#include <iostream>
#include "Sort.h"

using namespace std;

class Quick_Sort : public Sort
{
    public:
        Quick_Sort( int size ) : Sort(size) {};
        virtual void sort();

    private:
        void sort_kernel(int,int);

};

void Quick_Sort::sort()
{
    int lo = 0;
    int hi = _N - 1;
    sort_kernel(lo,hi);
}

void Quick_Sort::sort_kernel(int lo, int hi)
{
    if ( lo == hi )
    {
        return;
    }
    else if ( lo + 1 == hi && _elems[lo] <= _elems[hi] )
    {
        return;
    }
    else if ( lo + 1 == hi && _elems[lo] > _elems[hi] )
    {
        swap( _elems[lo], _elems[hi]);
        return;
    }

    int pi = lo;
    int i = lo + 1;
    int j = hi;
    while ( true )
    {
        while ( i <= hi && _elems[i] <= _elems[pi] )
            i++;
        while ( j >= lo + 1 && _elems[j] > _elems[pi] )
            j--;
        if ( j <= i )
            break;
        swap( _elems[i], _elems[j] );
    }
    swap( _elems[pi], _elems[j] );
    pi = j;

    if ( pi == lo )
        sort_kernel(pi+1,hi);
    else if ( pi == hi )
        sort_kernel(lo,pi-1);
    else
    {
        sort_kernel(lo,pi-1);
        sort_kernel(pi+1,hi);
    }
}


int main()
{
    unsigned Ntest = 20;
    unsigned N = 1;
    for ( unsigned itest = 0; itest < Ntest; itest++ )
    {
        N = N*2;
        Sort *qs = new Quick_Sort(N);
        qs->initialize();

        clock_t begin = clock();
        // qs->displayme();
        qs->sort();
        // qs->displayme();

        clock_t end = clock();

        cout << "N = " << qs->size() << ", RunTime: " <<  end-begin << ", isSorted?: " << qs->isSorted()  << endl;
    }
}
