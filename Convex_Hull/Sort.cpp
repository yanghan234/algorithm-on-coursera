#include "Sort.h"
#include <cstdlib>
#include <iostream>
using namespace std;

template < typename T>
void Sort<T>::initialize()
{
    srand(time(NULL));
    for ( unsigned i = 0; i < _N; i++ )
    {
        T randint = rand()/(RAND_MAX/_N)+1; // generate random numbers from [1,_N]
        _elems.push_back(randint);
    }
}

template < typename T>
void Sort<T>::swap( T &a, T &b )
{
    T tmp = a;
    a = b;
    b = tmp;
}

template < typename T>
void Sort<T>::displayme( ostream &os ) const
{
    for ( unsigned i = 0; i < _N; i++ )
        os << _elems[i] << ", ";
    os << endl;
}

template < typename T>
bool Sort<T>::isSorted( ) const
{
    for ( unsigned i = 0; i < _N-1; i++ )
        if ( _elems[i] > _elems[i+1] )
            return false;
    return true;
}
