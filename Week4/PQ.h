#include <iostream>
#include <exception>
using namespace std;

#ifndef PQ_H
#define PQ_H
// base class of priority queue
class PQ
{
    protected:
        int *_elems;
        int _size;            // how many elems in the heap
        int _capacity;        // capacity of the heap
        void swap( int i, int j );
        void resize( );       // once it is full, double the capacity

    public:
        PQ(int capacity) : _capacity( capacity ) { _elems = new int[capacity]; };

        int size() const { return _size;};
        int capacity() const { return _capacity; };
        bool isEmpty() const { return _size == 0; };
        bool isFull() const { return _size == _capacity; };
        int elem( int ind ) const;

        virtual void add( int ) = 0;
        virtual int get() = 0;

        void displayme( ostream &os = cout ) const;
};
#endif
