#ifndef SORT_H
#define SORT_H
#include <iostream>
#include <vector>
#include <string>
using namespace std;

template < typename T >
class Sort
{
    protected:
        vector<T> _elems;
        unsigned _N;
        void swap( T &a, T &b );

    public:
        Sort(unsigned size) : _N(size) { _elems.reserve(_N); }
        void initialize( );
        virtual void sort( ) = 0;
        T elems( int i ) const { return _elems[i]; };
        unsigned size() const { return _N; };
        void displayme( ostream &os = cout ) const;
        bool isSorted( ) const;
        ~Sort() { _elems.clear(); };
};
#endif
