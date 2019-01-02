import java.util.Random;
import java.lang.Math;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.RectHV;
public class KdTree
{
    private int count;
    private double nearestDis = Double.POSITIVE_INFINITY;
    private Point2D nearestPoint;
    private SET<Point2D> rangePoints;
    private Node root;

    private class Node
    {
        public Point2D point;
        public int depth;
        public Node left;
        public Node right;

        public Node(Point2D p, int d)
        {
            point = p;
            depth = d;
            left = null;
            right = null;
        }
    }

    public KdTree()
    {
        root = null;
        count = 0;
    }

    public boolean isEmpty() { return count == 0; }
    public int size() { return count; }

    public void insert(Point2D p)
    {
        if ( p == null )
            throw new IllegalArgumentException("Argument to insert() method cannot be null!!");
        root = insertTo(root,p,0);
    }

    private Node insertTo(Node where, Point2D p, int d)
    {
        // d is the depth of last level
        if ( where == null )
        {
            count++;
            return new Node(p,d);
        }
        if ( d%2 == 1 ) // this level has odd depth, compare y coordinate
        {
            if ( p.y() > where.point.y() )
                where.right = insertTo(where.right,p,d+1);
            else if ( p.y() < where.point.y() )
                where.left = insertTo(where.left,p,d+1);
            else if ( p.y() == where.point.y() && p.x() != where.point.x() )
                where.left = insertTo(where.left,p,d+1);
        }
        else // this level has even depth, compare x coordinate
        {
            if ( p.x() > where.point.x() )
                where.right = insertTo(where.right,p,d+1);
            else if ( p.x() < where.point.x() )
                where.left = insertTo(where.left,p,d+1);
            else if ( p.x() == where.point.x() && p.y() != where.point.y() )
                where.left = insertTo(where.left,p,d+1);
        }
        return where;
    }

    public boolean contains(Point2D p)
    {
        if ( p == null )
            throw new IllegalArgumentException("Argument to contains method cannot be null!!");
        if ( root == null )
            return false;
        return contains(root,p);
    }

    private boolean contains(Node where,Point2D p)
    {
        boolean flag = false;
        if ( where == null )
        {
            flag = false;
        }
        else if ( p.equals(where.point) )
        {
            flag = true;
        }
        else
        {
            if ( where.depth%2 != 0 )
            {
                if ( p.y() > where.point.y() )
                    flag = contains(where.right,p);
                else if ( p.y() <= where.point.y() )
                    flag = contains(where.left,p);
            }
            else
            {
                if ( p.x() > where.point.x() )
                    flag = contains(where.right,p);
                else if ( p.x() <= where.point.x() )
                    flag = contains(where.left,p);
            }
        }
        return flag;
    }

    public void draw()
    {
        drawme(root);
    }

    private void drawme(Node where)
    {
        if ( where == null )
            return;
        where.point.draw();
        drawme(where.left);
        drawme(where.right);
    }

    public Iterable<Point2D> range(RectHV rect)
    {
        rangePoints = new SET<Point2D>();
        findRange( root, rect );
        return rangePoints;
    }

    private void findRange( Node where, RectHV rect )
    {
        if ( where == null )
            return;
        if ( rect.contains( where.point ) )
        {
            rangePoints.add( where.point );
            findRange( where.left, rect );
            findRange( where.right, rect );
        }
        else
        {
            if ( where.depth%2 == 0 )
            {
                if ( where.point.x() > rect.xmax() )
                    findRange( where.left, rect );
                else if ( where.point.x() < rect.xmin() )
                    findRange( where.right, rect );
                else
                {
                    findRange( where.left, rect );
                    findRange( where.right, rect );
                }
            }
            else
            {
                if ( where.point.y() < rect.ymin() )
                    findRange( where.right, rect );
                else if ( where.point.y() > rect.ymax() )
                    findRange( where.left, rect );
                else
                {
                    findRange( where.left, rect );
                    findRange( where.right, rect );
                }
            }
        }
    }

    public Point2D nearest(Point2D p)
    {
        findNearest(root,p);
        return nearestPoint;
    }

    private void findNearest(Node where, Point2D target)
    {
        if ( where == null )
            return;
        double disToMe = target.distanceTo(where.point);
        if ( disToMe < nearestDis )
        {
            nearestDis = disToMe;
            nearestPoint = where.point;
        }
        double verticalDis;
        if ( where.depth%2 == 0 )
            verticalDis = Math.abs(where.point.x()-target.x());
        else
            verticalDis = Math.abs(where.point.y()-target.y());
        if ( where.depth%2 == 0 )
        {
            if ( target.x() <= where.point.x() )
                findNearest(where.left,target);
            else
                findNearest(where.right,target);
        }
        else
        {
            if ( target.y() <= where.point.y() )
                findNearest(where.left,target);
            else
                findNearest(where.right,target);
        }
        if ( where.depth%2 == 0 )
        {
            if ( target.x() <= where.point.x() && verticalDis < nearestDis )
                findNearest(where.right,target);
            else if ( target.x() > where.point.x() && verticalDis < nearestDis )
                findNearest(where.left,target);
        }
        else
        {
            if ( target.y() <= where.point.y() && verticalDis < nearestDis )
                findNearest(where.right,target);
            else if ( target.y() > where.point.y() && verticalDis < nearestDis )
                findNearest(where.left,target);
        }
    }

    private void displayme()
    {
        displayme(root);
    }

    private void displayme(Node where)
    {
        if ( where == null )
            return;
        System.out.println(where.point.toString()+" "+where.depth);
        displayme(where.left);
        displayme(where.right);
    }

    public static void main(String[] args)
    {
        KdTree tree = new KdTree();
        int numOfPoints = 100;
        Random rand = new Random();
        for ( int i = 0; i < numOfPoints; i++ )
        {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            tree.insert(new Point2D(x,y));
        }

        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.BLACK);
        tree.draw();

        Point2D target = new Point2D(rand.nextDouble(),rand.nextDouble());
        Point2D nearestPoint = tree.nearest(target);

        StdDraw.setPenColor(StdDraw.RED);
        target.draw();

        StdDraw.setPenColor(StdDraw.BLUE);
        nearestPoint.draw();

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.001);
        StdDraw.circle(target.x(),target.y(),target.distanceTo(nearestPoint));

        double xmin = rand.nextDouble();
        double ymin = rand.nextDouble();
        double xmax = rand.nextDouble()*(1-xmin)+xmin;
        double ymax = rand.nextDouble()*(1-ymin)+ymin;
        RectHV rect = new RectHV(xmin,ymin,xmax,ymax);

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.001);
        rect.draw();

        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.BLUE);
        for ( Point2D p : tree.range(rect) )
        {
//            System.out.println(p.toString());
            p.draw();
        }



    }
}
