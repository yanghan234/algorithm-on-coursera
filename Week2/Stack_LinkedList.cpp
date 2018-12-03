/*
 * Implement stack data structure with linked list.
 * Works only for data types with = operation defined.
 * You may also overload = operator in order to use stack defined here.
 */
#include <iostream>
using namespace std;

template < typename valType >
class StackIterator;

template < typename valType >
class Node
{
    public:
        valType val;
        Node<valType> *next;
};

template < typename valType >
class Stack
{
    friend class StackIterator<valType>;
    public:
        Stack();
        void push( valType );
        valType pop();
        bool isEmpty() const { return _count == 0; };
        int size() const { return _count; };

        typedef StackIterator<valType> iterator;

        iterator begin() { return iterator( _head ); };
        iterator end() { return iterator( _tail ); };

    private:
        Node<valType> *_head, *_tail;;
        unsigned _count;
};

template < typename valType >
Stack<valType>::Stack()
{
    _head = _tail = NULL;
    _count = 0;
}

template < typename valType >
void Stack<valType>::push( valType elem )
{
    Node<valType> *ptr = new Node<valType>;
    ptr->val = elem;
    ptr->next = _head;
    _head = ptr;
    _count++;
}

template < typename valType >
valType Stack<valType>::pop( )
{
    Node<valType> *ptr = _head;
    _head = _head->next;
    valType tmp = ptr->val;
    delete ptr;
    return tmp;
}

template < typename valType >
class StackIterator
{
    public:
        StackIterator( Node<valType> *ptr ) : _nodePtr(ptr) {} ;

        valType operator*() const { return _nodePtr->val; };
        StackIterator<valType> operator++(int)
        {
            _nodePtr = _nodePtr->next;
            return *this;
        };
        bool operator==( StackIterator<valType> &rhs )
        { return _nodePtr == rhs._nodePtr; };
        bool operator!=( StackIterator<valType> &rhs )
        { return !(*this==rhs); }

    private:
        Node<valType> *_nodePtr;

};


int main()
{
    Stack<int> s;
    s.push(1);
    s.push(2);

    Stack<int>::iterator it1 = s.begin();
    Stack<int>::iterator it2 = s.end();
    Stack<int>::iterator it3 = s.end();
    for ( ; it1 != it2; it1++ )
        cout << *it1 << endl;
}
