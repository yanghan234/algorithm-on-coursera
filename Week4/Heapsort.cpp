#include <iostream>
#include "PQ.h"
#include "Heap.h"
#include <cstdlib>
using namespace std;

class Heapsort : public Heap
{
    public:
        Heapsort( int *arr, int size )
        {
            _elems = arr;
            _size = size;
            _capacity = _size;
        }

        void sort()
        {
            int i = _size/2 - 1;
            while ( i >= 0 )
                movedown(i--, _size);

            int N = _size;
            while ( N > 0 )
            {
                swap(0, N-1);
                movedown(0, N-1);
                N--;
            }
        }
};


int main()
{
    int capacity = 50;
    int arr[capacity];
    srand(time(NULL));
    for ( int i = 0; i < capacity; i++ )
    {
        int randint = rand()/(RAND_MAX/capacity)+1; // generate random numbers from [1,_N]
        arr[i] = randint;
    }

    Heapsort *hs = new Heapsort( arr, capacity );

    hs->displayme();
    hs->sort();
    hs->displayme();

}
