#include <vector>
#include <iostream>
using namespace std;

class Percolation
{
  friend ostream& operator<<(ostream &os, const Percolation &rhs);
  public:
    Percolation( int num );
    bool open( int row, int col );
    bool isOpen( int row, int col );
    bool isFull( int row, int col );
    bool isPercolate();
    int  numOfOpenSites() const { return _numOfOpenSites; };

  private:
    int _N,_numOfOpenSites;
    vector<int> _status;
    vector<int> _id;
    bool check_integrity( int row, int col );
    void connect( int p, int q );
    int  root( int p );
};
