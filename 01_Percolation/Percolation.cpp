#include <iostream>
#include <exception>
#include <random>
#include <time.h>
#include "Percolation.h"
using namespace std;

Percolation::Percolation( int num )
{
  if ( num <= 0 )
    throw out_of_range("OutOfRangeException: N must be > 0!!");
  _N = num;
  _numOfOpenSites = 0;
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
    //cout << " Block ( " << row << ", "<<col<<" ) is already open" << endl;
    return false;
  }
  int index, index2;
  index = ( row - 1 ) * _N + ( col - 1 );
  _status[index] = 1;
  //cout << "Opening " << row << ", " << col << ", index = " << index << endl;
  if ( row == 1 )
    _id[index+1] = 0;
  for ( int irow = row - 1; irow <= row+1; irow++ )
  {
    for ( int icol = col - 1; icol <= col+1; icol++ )
    {
      index2 = ( irow - 1 ) * _N + ( icol - 1 );
      if ( (row-irow)*(row-irow)+(col-icol)*(col-icol)==1
        && check_integrity( irow, icol ) && isOpen(irow,icol) )
      {
        connect(index+1,index2+1);
      }
    }
  }
  _numOfOpenSites++;
  return true;
}

bool Percolation::isOpen( int row, int col )
{
  if ( row <= 0 || row > _N || col <= 0 || col > _N )
    throw out_of_range("OutOfRangeException: row and col must be within [1,N]!!");
  int index = ( row - 1 ) * _N + ( col - 1 );
  return _status[index]==1;
}

bool Percolation::isFull( int row, int col )
{
  int index = ( row - 1 ) * _N + col - 1;
  return root(index+1) == 0;
}

bool Percolation::isPercolate()
{
  int row = _N;
  for ( int col = 1; col <=_N; col++ )
  {
    int index = ( row - 1 ) * _N + ( col - 1);
    if ( root(index+1) == 0 )
      return true;
  }
  return false;
}


bool Percolation::check_integrity( int row, int col )
{
  if ( row <= 0 || row > _N || col <= 0 || col > _N )
    return false;
  else
    return true;
}

void Percolation::connect( int p, int q )
{
  int rootp = root(p);
  int rootq = root(q);
  if ( rootp < rootq )
    _id[rootq] = rootp;
  else
    _id[rootp] = rootq;
}

int Percolation::root( int p )
{
  int rootp = p;
  while ( rootp != _id[rootp] )
    rootp = _id[rootp];

  while ( p != rootp )
  {
    int newp = _id[p];
    _id[p] = rootp;
    p = newp;
  }

  return rootp;
}

ostream& operator<<( ostream &os, const Percolation &rhs )
{
  os << "ID matrix: ";
  for ( int row = 1; row <= rhs._N; row++ )
  {
    os << endl;
    for ( int col = 1; col <= rhs._N; col++ )
    {
      int index = ( row - 1 ) * rhs._N + ( col - 1 );
      os << rhs._id[index+1] << "\t";
    }
  }
  os << endl;
  os << "Status matrix:";
  for ( int row = 1; row <= rhs._N; row++ )
  {
    os << endl;
    for ( int col = 1; col <= rhs._N; col++ )
    {
      int index = ( row - 1 ) * rhs._N + ( col - 1 );
      os << rhs._status[index] << "\t";
    }
  }
  return os;
}

int main(int argc, char** argv)
{
  if ( argc <= 1 )
    throw out_of_range("No input parameter!!");
  int N = atoi( argv[1] );
  int nTests = atoi( argv[2] );
  double ratios[nTests];
  try {
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<int> dis(1,N);

    for ( int itest = 0; itest < nTests; itest++ )
    {
      Percolation *prc = new Percolation(N);
      while ( !prc->isPercolate() )
      {
        int row = dis(gen);
        int col = dis(gen);
        prc->open(row,col);
      }
      ratios[itest] = prc->numOfOpenSites()*1.0/(N*N);
      // cout << *prc << endl;
      // cout << prc->numOfOpenSites()*1.0/(N*N) << endl;
      // cout << "Percolate? : " << prc->isPercolate() << endl;
    }

    double mean = 0.0, stddev = 0.0;
    for ( int itest = 0; itest < nTests; itest++ )
      mean += ratios[itest];
    mean = mean/nTests;

    for ( int itest = 0; itest < nTests; itest++ )
      stddev += (ratios[itest]-mean)*(ratios[itest]-mean);
    stddev = sqrt(stddev/(nTests-1));

    cout << "Average: " << mean << endl;
    cout << "Std Dev: " << stddev << endl;
    cout << "95%    : [" << mean-stddev*1.96/sqrt(nTests);
    cout <<", "<<mean+stddev*1.96/sqrt(nTests) << "]"<<endl;

  }
  catch ( exception &e ) {
    cout << e.what() << endl;
  }

}
