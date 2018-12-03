/*
 * Implement queue data structure with array
 */
#include <iostream>
#include <string.h>
using namespace std;

template < typename valType >
class QueueIterator;

template < typename valType >
class Queue
{
    friend class QueueIterator<valType>;
    public:
        Queue( unsigned size ) : _size(size), _count(0)
        {
            _elems = new valType[_size];
            _head = _elems;
            _last = _elems;
        };

        bool isEmpty( ) const { return _count == 0; };
        bool isFull( ) const { return _last == _elems + _size; };

        void enqueue( valType &elem );
        valType dequeue( );

        // resizing
        void expand( );
        void shrink( );

        typedef QueueIterator<valType> iterator;

        iterator begin() { return iterator(_head); };
        iterator end() { return iterator(_last); };

    private:
        unsigned _size;
        unsigned _count;
        valType *_elems;
        valType *_head;
        valType *_last;
};

template < typename valType >
void Queue<valType>::enqueue( valType &elem )
{
    _elems[_count] = elem;
    _count++;
    _last = _elems+_count;
    if ( isFull() )
        expand();
}

template < typename valType >
valType Queue<valType>::dequeue( )
{
    valType elem;
    elem = *_head;
    _head++;
    _count--;
    if ( _count == _size/4 )
        shrink( );
    return elem;
}

template < typename valType >
void Queue<valType>::expand( )
{
    valType *ptr = new valType[2*_size];
    memcpy(ptr,_head,_count*sizeof(valType));
    delete[] _elems;
    _elems = ptr;
    _head = _elems;
    _last = _elems + _count;
}

template < typename valType >
void Queue<valType>::shrink( )
{
    valType *ptr = new valType[_size/2];
    memcpy(ptr,_head,_count*sizeof(valType));
    delete[] _elems;
    _elems = ptr;
    _head = _elems;
    _last = _elems + _count;
}

template < typename valType >
class QueueIterator
{
    public:
        QueueIterator<valType>( valType *ptr ) : _valPtr(ptr) {};

        valType operator*() const { return *_valPtr; };
        QueueIterator operator++(int) { _valPtr++; return *this; };
        bool operator==( QueueIterator<valType> &rhs ) const { return this->_valPtr == rhs._valPtr; };
        bool operator!=( QueueIterator<valType> &rhs ) const { return !(*this == rhs); };

    private:
        valType *_valPtr;
};

int main()
{
    Queue<int> q(2);
    int i = 1;
    q.enqueue(i);
    q.enqueue(i);
    q.enqueue(i);
    q.enqueue(i);

    cout << "Using dequeue: " << endl;
    cout << q.dequeue() << endl;
    cout << q.dequeue() << endl;

    cout << "Using queue iterator * operator:"<< endl;
    Queue<int>::iterator it = q.begin(), iter = q.end();
    while ( it != iter )
    {
      cout << *it << endl;
      it++;
    }
}
