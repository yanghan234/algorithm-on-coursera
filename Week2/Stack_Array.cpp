/*
 * Implement stack data structure with resizing array
 */
#include <iostream>
#include <vector>
#include <exception>
#include <string.h>
using namespace std;

template < typename valType >
class Stack
{
    public:
        Stack(unsigned size) : _size(size), _count(0)
        { _elem = new valType[size]; };

        void push( valType &elem );
        valType pop( );
        void expand( );
        void shrink( );
        bool isEmpty( ) const { return _count == 0; };
        bool isFull( ) const { return _count == _size; };

    private:
        unsigned _size;
        unsigned _count;
        valType *_elem;
};

template < typename valType >
void Stack<valType>::push(valType &elem)
{
    if ( isFull() )
        throw new out_of_range("Cannot push to full stack");
    _elem[_count] = elem;
    _count++;
    if ( isFull( ) )
        expand( );
}

template < typename valType >
valType Stack<valType>::pop( )
{
    if ( isEmpty() )
        throw new out_of_range("Cannot pop from empty stack");
    _count--;
    valType elem = _elem[_count];
    if ( _count == _size / 4 )
        shrink( );
    return elem;
}

template < typename valType >
void Stack<valType>::expand( )
{
    valType *ptr = new valType[2*_size];
    memcpy(ptr,_elem,_size*sizeof(valType));
    delete[] _elem;
    _elem = ptr;
    _size = _size*2;
}

template < typename valType >
void Stack<valType>::shrink( )
{
    valType *ptr = new valType[_size/2];
    memcpy(ptr,_elem,_size/4*sizeof(valType));
    delete[] _elem;
    _elem = ptr;
    _size = _size/2;
}

int main()
{
    int i = 1;
    Stack<int> s(5);

    for ( i = 0 ; i < 10; i++ )
      s.push(i);

    for ( i = 0; i < 10; i++ )
      cout << s.pop() << endl;


}

