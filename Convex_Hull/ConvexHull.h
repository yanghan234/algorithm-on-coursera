#include "Point2D.h"
#include <iostream>
#include <vector>

#ifndef CONVEXHULL_H
#define CONVEXHULL_H
using namespace std;

class ConvexHull
{
    public:
        ConvexHull(unsigned size) : _N(size) { _elems.reserve(_N); };
        void initialize();
        void displayme( );
        unsigned size() const { return _N; };
        Point2D elems( int ind ) const { return _elems[ind]; };

    private:
        unsigned _N;
        vector<Point2D> _elems;
};

#endif
