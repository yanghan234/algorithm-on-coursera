#include <vector>
#include <iostream>
using namespace std;

class Percolation
{
    public:
        Percolation( int num );
        bool open( int row, int col );
        bool isOpen( int row, int col );

    private:
        int _N;
        vector<int> _status;
        vector<int> _id;
        bool check_integrity( int row, int col );
        void connect( int p, int q );
        int  root( int p );
};
