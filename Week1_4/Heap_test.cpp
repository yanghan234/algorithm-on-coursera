#include <iostream>
#include "Heap.h"
using namespace std;

int main()
{
    PQ *hp = new Heap(10);

    hp->add(1);
    hp->add(2);
    hp->add(0);
    hp->add(-1);
    hp->displayme();

    cout << hp->get() << endl;
    hp->displayme();
    cout << hp->get() << endl;
    hp->displayme();
    cout << hp->get() << endl;
    cout << hp->get() << endl;

    hp->displayme();

}
