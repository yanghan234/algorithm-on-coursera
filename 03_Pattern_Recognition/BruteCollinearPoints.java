import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints
{
    private Point[] _ps;
    private int _n;
    private int capacity = 2;
    private LineSegment[] _ls = new LineSegment[capacity];
    public BruteCollinearPoints(Point[] points)
    {
        _n = 0;
        _ps = points;
        _ls = new LineSegment[points.length];
        System.out.println("max size : "+_ls.length+"\n");
        for ( int i = 0; i < _ps.length-3; i++ )
        {
            int imax = i;
            int imin = i;
            for ( int j = i+1; j < _ps.length-2; j++ )
            {
                if ( _ps[imax].compareTo(_ps[j]) < 0 )
                    imax = j;
                if ( _ps[imin].compareTo(_ps[j]) > 0 )
                    imin = j;
                for ( int k = j+1; k < _ps.length-1; k++)
                {
                    if ( _ps[imax].compareTo(_ps[k]) < 0 )
                        imax = k;
                    if ( _ps[imin].compareTo(_ps[k]) > 0 )
                        imin = k;
                    for ( int l = k+1; l < _ps.length; l++ )
                    {
                        if ( _ps[imax].compareTo(_ps[l]) < 0 )
                            imax = l;
                        if ( _ps[imin].compareTo(_ps[l]) > 0 )
                            imin = l;
                        Point p = _ps[i];
                        Point q = _ps[j];
                        Point r = _ps[k];
                        Point s = _ps[l];
                        if ( p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s) )
                        {
                            _ls[_n] = new LineSegment(_ps[imin],_ps[imax]);
                            _n++;
                            if ( _n == capacity )
                                resize(2*capacity);
                        }
                    }
                }
            }
        }
    }
    public int numberOfSegments() { return _n; };
    public LineSegment[] segments() { return _ls; };

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
       StdDraw.enableDoubleBuffering();
       StdDraw.setXscale(0,32768);
       StdDraw.setYscale(0,32768);
       for ( Point p : points )
       {
           p.draw();
       }
       StdDraw.show();

       BruteCollinearPoints ls = new BruteCollinearPoints(points);

       LineSegment[] segs = ls.segments();
       for ( int i = 0; i < ls.numberOfSegments(); i++ )
           System.out.println(segs[i].toString());
    }
}
