#include "Sort.h"
#include <iostream>
#include <vector>
#include <fstream>
using namespace std;

class Insertion_Sort : public Sort
{
    public:
        Insertion_Sort( unsigned size ) : Sort(size) {};
        virtual void sort();

};

void Insertion_Sort::sort()
{
    for ( unsigned i = 1; i < _N; i++ )
    {
        int val = _elems[i];
        int j = i - 1;
        for ( ; j >= 0; j-- )
            if ( _elems[j] > val )
                _elems[j+1] = _elems[j];
            else
                break;
        _elems[j+1] = val;
    }
}

ostream& operator<<( ostream &os, Insertion_Sort &rhs )
{
    for ( unsigned i = 0; i < rhs.size(); i++ )
        os << rhs.elems(i) << ", ";
    return os;
}

int main()
{

    unsigned Ntest = 18;
    unsigned N = 1;
    ofstream outFile("Insertion_Sort_runtime.csv");
    outFile << "N,t" << endl;
    for ( unsigned itest = 0; itest < Ntest; itest++ )
    {

        N = N*2;
        Insertion_Sort *ss = new Insertion_Sort(N);
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
