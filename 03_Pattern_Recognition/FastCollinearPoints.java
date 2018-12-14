import java.util.Arrays;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

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
        if ( points == null )
            throw new IllegalArgumentException();
        for ( int i = 0; i < points.length - 1; i++ )
        {
            if ( points[i] == null )
                throw new IllegalArgumentException();
            for ( int j = i+1; j < points.length; j++ )
            {
                if ( points[i].compareTo(points[j]) == 0 )
                    throw new IllegalArgumentException();
            }
        }
        _n = 0;
        _ps = new Point[points.length];
        for ( int i = 0; i < points.length; i++ )
        {
            for ( int j = 0; j < _ps.length; j++ )
                _ps[j] = points[j];
            Arrays.sort( _ps, points[i].slopeOrder() );
            // for ( int j = 0; j < _ps.length; j++ )
                // System.out.println(_ps[j].toString());
            int j = 1;
            int k = 1;
            int imax = 0;
            boolean flag = true;
            while ( j < _ps.length && k < _ps.length )
            {
                // System.out.println("imax = "+imax);
                if ( _ps[0].slopeTo(_ps[j]) == _ps[0].slopeTo(_ps[k]) )
                {
                    if ( _ps[0].compareTo(_ps[k]) > 0 )
                        flag = false;
                    if ( _ps[imax].compareTo(_ps[k]) < 0 )
                        imax = k;
                    k++;
                }
                else if ( k-j >= 3 && flag == true )
                {
                    _ls[_n] = new LineSegment( _ps[0], _ps[imax] );
                    _n++;
                    if ( _n >= capacity )
                    {
                        resize(2*capacity);
                        // System.out.println("Now the length is "+_ls.length);
                    }
                    j = k;
                    imax = 0;
                }
                else
                {
                    j = k;
                    imax = 0;
                    flag = true;
                }
            }
            if ( k - j >= 3  && flag == true )
            {
                _ls[_n] = new LineSegment( _ps[0], _ps[imax] );
                _n++;
                if ( _n >= capacity )
                {
                    resize(2*capacity);
                    // System.out.println("Now the length is "+_ls.length);
                }
            }
        }
        LineSegment[] temp = new LineSegment[_n];
        for ( int i = 0; i < _n; i++ )
            temp[i] = _ls[i];
        _ls = temp;
    }

    private void resize( int newCapacity )
    {
        //assert _n >= capacity;
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
       System.out.println("num of segs:"+fcp.numberOfSegments());

       for ( LineSegment seg : segs )
           System.out.println(seg.toString());
       // draw the points
       StdDraw.enableDoubleBuffering();
       StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);
       for ( int i = 0; i < fcp.numberOfSegments(); i++)
       {
           segs[i].draw();
       }
       StdDraw.show();
    }
}
