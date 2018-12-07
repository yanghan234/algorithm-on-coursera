#ifndef SORT_H
#define SORT_H
#include <iostream>
#include <vector>
#include <string>
using namespace std;
class Sort
{
    protected:
        vector<int> _elems;
        unsigned _N;
        void swap( int &a, int &b );

    public:
        Sort(unsigned size) : _N(size) { _elems.reserve(_N); }
        void initialize( );
        virtual void sort( ) = 0;
        int elems( int i ) const { return _elems[i]; };
        unsigned size() const { return _N; };
        void displayme( ostream &os = cout ) const;
        ~Sort() { _elems.clear(); };
};
#endif
