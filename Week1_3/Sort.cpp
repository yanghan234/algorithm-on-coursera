#include "Sort.h"
#include <cstdlib>
#include <iostream>
using namespace std;

void Sort::initialize()
{
    srand(time(NULL));
    for ( unsigned i = 0; i < _N; i++ )
    {
        int randint = rand()/(RAND_MAX/_N)+1; // generate random numbers from [1,_N]
        _elems.push_back(randint);
    }
}

void Sort::swap( int &a, int &b )
{
    int tmp = a;
    a = b;
    b = tmp;
}

void Sort::displayme( ostream &os ) const
{
    for ( unsigned i = 0; i < _N; i++ )
        os << _elems[i] << ", ";
    os << endl;
}

bool Sort::isSorted( ) const
{
    for ( unsigned i = 0; i < _N-1; i++ )
        if ( _elems[i] > _elems[i+1] )
            return false;
    return true;
}
