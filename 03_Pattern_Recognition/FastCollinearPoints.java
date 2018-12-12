import java.util.Arrays;
import edu.princeton.cs.algs4.In;
public class FastCollinearPoints
{
    private Point[] _ps;
    private int _n = 0;
    private int capacity = 2;
    private LineSegment[] _ls = new LineSegment[capacity];
    public int numberOfSegments() { return _n; };
    public LineSegment[] segments() { return _ls; };

    public FastCollinearPoints( Point[] points )
    {
        _n = 0;
        _ps = points;

        for ( int i = 0; i < _ps.length; i++ )
        {
            Arrays.sort( _ps, _ps[i].slopeOrder() );
            int j = 0;
            int k = 0;
            while ( j < _ps.length && k < _ps.length )
            {
                if ( ( _ps[i].slopeTo(_ps[j]) - _ps[i].slopeTo(_ps[k]) ) < 1e-6 &&
                     ( _ps[i].slopeTo(_ps[j]) - _ps[i].slopeTo(_ps[k]) ) > -1e-6 )
                {
                    k++;
                }
                else if ( k - j > 3 )
                {
                    _ls[_n] = new LineSegment( _ps[j], _ps[k-1] );
                    _n++;
                    j = k;
                    if ( _n >= _ls.length )
                        resize( 2*_ls.length );
                }
                else
                    j = k;
            }
        }

    }

    private void resize( int newCapacity )
    {
        assert _n >= capacity;
        capacity = newCapacity;
        LineSegment[] temp = new LineSegment[newCapacity];
        for ( int i = 0; i < _n; i++ )
            temp[i] = _ls[i];
        _ls = temp;
    }

    public static void main(String[] args)
    {
       In in = new In(args[0]);
       int n = in.readInt();
       Point[] points = new Point[n];
       for ( int i = 0; i < n; i++ )
       {
           int x = in.readInt();
           int y = in.readInt();
           points[i] = new Point(x,y);
       }

       FastCollinearPoints fcp = new FastCollinearPoints(points);

       LineSegment[] segs = fcp.segments();
       for ( int i = 0; i < fcp.numberOfSegments(); i++ )
           System.out.println(segs[i].toString());
    }
}
