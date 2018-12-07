#include "ConvexHull.h"
#include <iostream>
#include <fstream>
using namespace std;

ostream& operator<<( ostream &os, ConvexHull &rhs )
{
    os << "x,y" << endl;
    for ( unsigned i = 0; i < rhs.size(); i++ )
        os << rhs.elems(i).x << ", " << rhs.elems(i).y << endl;
    return os;
}

int main()
{
    ConvexHull *ch = new ConvexHull(10);

    ch->initialize();
    ch->displayme();

    ofstream outFile("Points.csv");

    outFile << *ch << endl;
}
