#include "PQ.h"
#include "Heap.h"
#include <iostream>

void Heap::add( int item )
{
    if ( isFull() )
        resize( );
    _elems[_size] = item;
    moveup(_size-1);
    _size++;
}

int Heap::get( )
{
    return this->get(0);
}

int Heap::get( int index )
{
    int item = _elems[index];
    swap(index,_size-1);
    _size--;
    movedown(index, _size);
    return item;
}

void Heap::moveup( int index )
{
    int me = index;
    int father = ( index - 1 ) / 2;
    while ( me > 0 && _elems[me] > _elems[father] )
    {
        swap(father,me);
        me = father;
        father = ( me - 1 ) / 2;
    }
}

void Heap::movedown( int index, int N )
{
    int me = index;
    while ( me < N / 2 )
    {
        int leftKid = 2*me+1;
        int rightKid = 2*me+2;
        if ( rightKid >= N )
        {
            if ( _elems[leftKid] > _elems[me] )
                swap(leftKid,me);
            break;
        }
        if ( _elems[me] >= _elems[leftKid] && _elems[me] >= _elems[rightKid] )
            break;
        if ( _elems[leftKid] > _elems[rightKid] )
        {
            swap(leftKid,me);
            me = leftKid;
        }
        else
        {
            swap(rightKid,me);
            me = rightKid;
        }
    }
}

