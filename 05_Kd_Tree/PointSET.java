import java.util.Random;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.RectHV;
public class PointSET
{
    private int count;
    private SET<Point2D> points;
    public PointSET()
    {
        points = new SET<Point2D>();
        count = 0;
    }

    public boolean isEmpty() { return count == 0; }

    public void insert(Point2D p)
    {
        points.add(p);
    }

    public boolean contains(Point2D p)
    {
        return points.contains(p);
    }

    public void draw()
    {
        StdDraw.setPenRadius(0.02);
        StdDraw.setXscale(-0.1,1.1);
        StdDraw.setYscale(-0.1,1.1);
        for ( Point2D p : points )
            p.draw();
        //StdDraw.show();

    }

    public Iterable<Point2D> range(RectHV rect)
    {
        SET<Point2D> ps = new SET<Point2D>();
        for ( Point2D p : points )
        {
            if ( p.x() >= rect.xmin()
                    && p.x() <= rect.xmax()
                    && p.y() >= rect.ymin()
                    && p.y() <= rect.ymax() )
                ps.add(p);
        }
        return ps;
    };

    //public Point2D nearest(Point2D p){};

    public static void main(String[] args)
    {
        PointSET ps = new PointSET();
        RectHV rect = new RectHV(0.1,0.2,0.3,0.4);
        int numOfPoints = 20;
        Random rand = new Random();
        for ( int i = 0; i < numOfPoints; i++ )
        {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            ps.insert(new Point2D(x,y));
        }
        rect.draw();
        ps.draw();
    }
}
