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
}

int main()
{

    unsigned Ntest = 15;
    unsigned N = 1;
    ofstream outFile("Insertion_Sort_runtime.csv");
    outFile << "N,t" << endl;
    for ( unsigned itest = 0; itest < Ntest; itest++ )
    {
        clock_t begin = clock();

        N = N*2;
        Sort *ss = new Insertion_Sort(N);
        ss->initialize();

        ss->sort();

        clock_t end = clock();

        cout << "RunTime: " <<  end-begin  << endl;
        outFile << N << ", " << end-begin << endl;
    }
}
