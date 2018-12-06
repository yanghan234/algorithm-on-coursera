#include "Sort.h"
#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <exception>
#include <fstream>
using namespace std;

class Selection_Sort : public Sort
{
    public:
        Selection_Sort( unsigned size ) : Sort(size) { };
        void sort( );
};

void Selection_Sort::sort()
{
    for ( unsigned i = 0; i < _N-1; i++ )
    {
        unsigned minInd = i;
        for ( unsigned j = i+1; j < _N; j++ )
            if ( _elems[j] < _elems[minInd] )
                minInd = j;
        if ( i != minInd )
            swap( _elems[i], _elems[minInd] );
    }
}

int main()
{
    unsigned Ntest = 15;
    unsigned N = 1;
    ofstream outFile("Selection_Sort_runtime.csv");
    outFile << "N,t" << endl;
    for ( unsigned itest = 0; itest < Ntest; itest++ )
    {
        clock_t begin = clock();

        N = N*2;
        Selection_Sort *ss = new Selection_Sort(N);
        ss->initialize();

        // cout << *ss << endl;
        ss->sort();
        // cout << *ss << endl;

        clock_t end = clock();

        cout << "RunTime: " <<  end-begin  << endl;
        outFile << N << ", " << end-begin << endl;
    }
    outFile.close();
}
