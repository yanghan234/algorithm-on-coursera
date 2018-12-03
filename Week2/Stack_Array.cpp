/*
 * Implement stack data structure with resizing array
 */
#include <iostream>
#include <vector>
using namespace std;

template < typename valType >
class Stack
{
    public:
        Stack(unsigned size) : _size(size)
        { _elems.reserve(_size); };

        void push( valType &elem ) { _elems.push_back(elem);};

    private:
        unsigned _size;
        vector<valType> _elems;
};

int main()
{
    int i = 1;
    Stack<int> s(10);
    s.push(i);



}

