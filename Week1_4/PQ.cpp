#include "PQ.h"
#include <iostream>
using namespace std;

void PQ::swap( int i, int j )
{
    if ( !( i >= 0 && i < _size ) )
        throw out_of_range("Index out of range");
    if ( !( j >= 0 && j < _size ) )
        throw out_of_range("Index out of range");
    int tmp = _elems[i];
    _elems[i] = _elems[j];
    _elems[j] = tmp;
}

int PQ::elem( int ind ) const
{
    if ( !( ind >= 0 && ind < _size ) )
        throw out_of_range("Index out of range");
    return _elems[ind];
}

void PQ::resize( )
{
    int *newarr = new int[2*_capacity];
    for ( int i = 0; i < _capacity; i++ )
        newarr[i] = _elems[i];
    _elems = newarr;
    _capacity = _capacity*2;
}

void PQ::displayme( ostream &os ) const
{
    for ( int i = 0; i < _size; i++ )
        os << _elems[i] << ", ";
    os << endl;
}

