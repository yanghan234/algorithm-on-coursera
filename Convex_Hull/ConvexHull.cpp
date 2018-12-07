#include <iostream>
#include <vector>
#include "ConvexHull.h"
#include <cstdlib>
using namespace std;

void ConvexHull::initialize( )
{
    srand(time(NULL));
    for ( unsigned i = 0; i < _N; i++ )
    {
        int randx = rand()/(RAND_MAX/(2*_N))+1;
        int randy = rand()/(RAND_MAX/(2*_N))+1;
        Point2D p(randx, randy);

        _elems.push_back(p);
    }
}

void ConvexHull::displayme( )
{
    for ( unsigned i = 0; i < _N; i++ )
        cout << "( " << _elems[i].x << ", " << _elems[i].y << " )" << endl;
}

