#include <iostream>
#include <vector>
#include <fstream>
#include "Sort.h"
using namespace std;

class Shell_Sort : public Sort
{
    public:
        Shell_Sort( unsigned size ) : Sort(size) {};
        virtual void sort();
};

void Shell_Sort::sort()
{
    int h = 1;
    while ( h < _N/3 ) h = h*3 + 1;  // h series: 1, 4, 13, 40, 121

    while ( h >= 1 )
    {
        for ( unsigned i = h; i < _N; i++ )
            for ( int j = i; j>=h && _elems[j] < _elems[j-h]; j = j - h )
                swap( _elems[j], _elems[j-h] );
        h = h / 3;
    }

}

int main()
{

    unsigned Ntest = 4;
    unsigned N = 1;
    ofstream outFile("Shell_Sort_runtime.csv");
    outFile << "N,t" << endl;
    for ( unsigned itest = 0; itest < Ntest; itest++ )
    {
        clock_t begin = clock();

        N = N*2;
        Sort *ss = new Shell_Sort(N);
        ss->initialize();

        ss->displayme();
        ss->sort();
        ss->displayme();

        clock_t end = clock();

        cout << "RunTime: " <<  end-begin  << endl;
        outFile << N << ", " << end-begin << endl;
    }
}
