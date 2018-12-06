#include "Sort.h"
#include <cstdlib>

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

ostream& operator<<( ostream &os, Sort &rhs )
{
    for ( unsigned i = 0; i < rhs.size(); i++ )
        os << rhs.elems(i) << ", ";
    return os;
}


