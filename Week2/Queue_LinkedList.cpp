/*
 * Implement queue data structure with linked list.
 * Works only for data types with = operation defined.
 * You may also overload = operator in order to use stack defined here.
 */
#include <iostream>
#include <exception>
using namespace std;

template < typename valType >
class QueueIterator;

template < typename valType >
class Node
{
    public:
        valType val;
        Node<valType> *next;
};

template < typename valType >
class QueueIterator
{
    public:
        QueueIterator( Node<valType> *ptr ) : _nodePtr(ptr) {};

        valType operator*() const { return _nodePtr->val; };
        QueueIterator operator++(int)
        {
            _nodePtr = _nodePtr->next;
            return *this;
        }
        bool operator==( QueueIterator &rhs ) const { return _nodePtr == rhs._nodePtr; };
        bool operator!=( QueueIterator &rhs ) const { return !(*this == rhs ); };

    private:
        Node<valType> *_nodePtr;
};

template < typename valType >
class Queue
{
    friend class QueueIterator<valType>;
    public:
        Queue(){ _head = _last =  NULL; };
        void enqueue( valType &elem );
        valType dequeue( );
        bool isEmpty( ) const { return _head == NULL; };
        unsigned size( );

        void display();

        typedef QueueIterator<valType> iterator;

        iterator begin() { return iterator(_head); };
        iterator end() { return iterator(_last->next);};


    private:
        Node<valType> *_head;
        Node<valType> *_last; // last node in the linked list
};

template < typename valType >
void Queue<valType>::enqueue( valType &elem )
{
    Node<valType> *ptr = new Node<valType>;
    ptr->val = elem;
    ptr->next = NULL;
    if ( _head == NULL )
    {
        _head = ptr;
        _last = _head;
    }
    else
    {
        _last->next = ptr;
        _last = _last->next;
    }
}

template < typename valType >
valType Queue<valType>::dequeue( )
{
    if ( isEmpty() )
      throw new out_of_range("Cannot dequeue from empty queue.");
    Node<valType> *ptr = _head;
    valType elem = ptr->val;
    _head = _head->next;

    delete[] ptr;
    return elem;
}

template < typename valType >
void Queue<valType>::display()
{
    Node<valType> *ptr = _head;
    while ( ptr != NULL )
    {
      cout << ptr->val << endl;
      ptr = ptr->next;
    }
}

int main()
{
    Queue<int> q;
    for ( int i = 0; i < 5; i++ )
        q.enqueue(i);

    cout << "dequeue: " << q.dequeue() << endl;

    Queue<int>::iterator it = q.begin();
    Queue<int>::iterator iter = q.end();

    for ( ; it != iter; it++ )
        cout << *it << endl;

}
