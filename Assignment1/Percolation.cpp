#include <iostream>
#include <exception>
#include "Percolation.h"
using namespace std;

Percolation::Percolation( int num )
{
    if ( num <= 0 )
        throw out_of_range("OutOfRangeException: N must be > 0!!");
    _N = num;
    _status.reserve(_N*_N);
    _id.reserve(_N*_N+1);
    for ( int i = 0; i < _N*_N; i++ )
        _status[i] = 0;
    for ( int i = 0; i < _N*_N+1; i++ )
        _id[i] = i;
}

bool Percolation::open( int row, int col )
{
    if ( row <= 0 || row > _N || col <= 0 || col > _N )
        throw out_of_range("OutOfRangeException: row and col must be within [1,N]!!");
    if ( isOpen( row, col ) )
    {
        cout << " Block ( " << row << ", "<<col<<" ) is already open" << endl;
        return false;
    }

    int index, index2;
    if ( row == 1 )
    {
        index = ( row - 1 ) * _N + ( col - 1 );
        _id[index+1] = 0;
    }
    for ( int irow = row - 1; irow <= row+1; irow++ )
    {
        for ( int icol = col - 1; icol <= col+1; icol++ )
        {
            if ( (row-irow)*(row-irow)+(col-icol)*(col-icol)==1
                && check_integrity( irow, icol ) )
            {
                index2 = ( irow - 1 ) * _N + ( icol - 1 );
                connect(index,index2);
            }
        }
    }
    return true;
}

bool Percolation::isOpen( int row, int col )
{
    if ( row <= 0 || row > _N || col <= 0 || col > _N )
        throw out_of_range("OutOfRangeException: row and col must be within [1,N]!!");
    int index = row * _N + col;
    return _status[index];
}

int main(int argc, char** argv)
{
    int N = atoi( argv[1] );
    cout << N << endl;
    try {
        Percolation prc(N);
        cout << prc.isOpen(1,-1);
    }
    catch ( exception &e ) {
        cout << e.what() << endl;
    }

}
