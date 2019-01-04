#include <iostream>
#include "PQ.h"

class OrderedMaxPQ : public PQ
{
    public:
        OrderedMaxPQ(int capacity) : PQ(capacity) { };

        virtual void add( int );
        virtual int get( );
};

void OrderedMaxPQ::add( int item )
{
    _size++;
    if ( this->isFull() )
        this->resize();

    int i = _size-2;
    while ( i >= 0 &&  _elems[i] > item )
    {
        _elems[i+1] = _elems[i];
        i--;
    }
    _elems[i+1] = item;
}

int OrderedMaxPQ::get()
{
    int item = _elems[_size-1];
    _size--;
    return item;
}

int main()
{
    OrderedMaxPQ *upq = new OrderedMaxPQ(2);
    upq->add(1);
    upq->add(2);
    upq->add(3);
    upq->add(1);
    upq->add(0);
    upq->add(3);
    upq->displayme();

    while ( upq->size() > 0 )
        cout << upq->get() << endl;
}
