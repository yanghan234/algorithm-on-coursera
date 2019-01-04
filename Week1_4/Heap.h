#include <iostream>
#include "PQ.h"
using namespace std;

#ifndef HEAP_H
#define HEAP_H
class Heap : public PQ
{
    public:
        Heap(){};
        Heap( int capacity ) : PQ(capacity) { };
        virtual void add( int item );
        virtual int get( );
        int get( int index );

        void moveup( int index );
        void movedown( int index, int N );
};
#endif
