#include <iostream>
#include <exception>
#include "PQ.h"
using namespace std;


class UnorderedMaxPQ : public PQ
{
    public:
        UnorderedMaxPQ( int capacity = 1024 ) : PQ(capacity) {};

        virtual void add( int item );
        virtual int get( );

};

void UnorderedMaxPQ::add( int item )
{
    // In this implementation,
    // if you try to add more items into a full priority queue,
    // I will resize the array.
    if ( this->isFull() )
        this->resize();
    this->_elems[this->_size] = item;
    this->_size++;
}

int UnorderedMaxPQ::get( )
{
    if ( this->isEmpty() )
        throw underflow_error("Cannot read from empty priority queue");
    int imax = 0;
    for ( int i = 0; i < this->_size; i++ )
    {
        if ( this->_elems[imax] < this->_elems[i] )
            imax = i;
    }
    int item = this->_elems[imax];
    this->swap( imax, this->_size-1 );
    this->_size--;
    return item;
}

int main()
{
    UnorderedMaxPQ *upq = new UnorderedMaxPQ(2);
    upq->add(1);
    upq->add(2);
    upq->add(3);
    upq->add(1);
    upq->displayme();

    while ( upq->size() > 0 )
        cout << upq->get() << endl;
}
