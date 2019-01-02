import java.util.Random;
import java.util.Set;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.RectHV;
public class PointSET
{
    private SET<Point2D> points;
    public PointSET()
    {
        points = new SET<Point2D>();
    }

    public boolean isEmpty() { return points.isEmpty(); }
    public int size() { return points.size(); }

    public void insert(Point2D p)
    {
        if ( p == null )
            throw new IllegalArgumentException("Argument to insert() method cannot be null!!");
        points.add(p);
    }

    public boolean contains(Point2D p)
    {
        if ( p == null )
            throw new IllegalArgumentException("Argument to contains() method cannot be null!!");
        return points.contains(p);
    }

    public void draw()
    {
        for ( Point2D p : points )
            p.draw();
    }

    public Iterable<Point2D> range(RectHV rect)
    {
        if ( rect == null )
            throw new IllegalArgumentException("Argument to range() method cannot be null!!");
        if ( isEmpty() )
            return null;
        SET<Point2D> ps = new SET<Point2D>();
        //System.out.printf("[ %3.1f, %3.1f ] x [ %3.1f, %3.1f ]\n",rect.xmin(),rect.xmax(),rect.ymin(),rect.ymax());
        for ( Point2D p : points )
        {
            //System.out.printf("Point: ( %6.3f, %6.3f )\n",p.x(),p.y());
            if ( p.x() >= rect.xmin()
                    && p.x() <= rect.xmax()
                    && p.y() >= rect.ymin()
                    && p.y() <= rect.ymax() )
                ps.add(p);
        }
        return ps;
    };

    public Point2D nearest(Point2D p)
    {
        if ( p == null )
            throw new IllegalArgumentException("Argument to nearest() method cannot be null!!");
        if ( isEmpty() )
            return null;
        double distance = Double.POSITIVE_INFINITY;
        Point2D nearestPoint = new Point2D(0,0);
        for ( Point2D ps : points )
        {
            double tmpDis = p.distanceTo(ps);
            if ( tmpDis < distance )
            {
                distance = tmpDis;
                nearestPoint = ps;
            }
        }
        return nearestPoint;
    };

    public static void main(String[] args)
    {
        PointSET ps = new PointSET();
        RectHV rect = new RectHV(0.1,0.1,0.3,0.4);
        int numOfPoints = 50;
        Random rand = new Random();
        for ( int i = 0; i < numOfPoints; i++ )
        {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            ps.insert(new Point2D(x,y));
        }

        StdDraw.setPenRadius(0.005);
        StdDraw.line(-0.1,0.0,1.1,0.0);
        StdDraw.line(0.0,-0.1,0.0,1.1);

        StdDraw.setPenColor(StdDraw.BLUE);
        rect.draw();
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.BLACK);
        ps.draw();
        StdDraw.setPenColor(StdDraw.BLUE);
        for ( Point2D p: ps.range(rect) )
            p.draw();

        Point2D targePoint = new Point2D(0.5,0.5);
        Point2D nearestPoint;
        nearestPoint = ps.nearest(targePoint);
        StdDraw.setPenColor(StdDraw.RED);
        nearestPoint.draw();
    }
}
