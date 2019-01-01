import java.util.Random;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.RectHV;
public class KdTree
{
    private int count;
    public Node root;

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

    public void insert(Point2D p)
    {
        if ( p == null )
            throw new IllegalArgumentException("Argument to insert() method cannot be null!!");
        count++;
        if ( root == null )
            root = new Node(p,0);
        root = insertTo(root,p,0);
    }

    private Node insertTo(Node where, Point2D p, int d)
    {
        // d is the depth of last level
        if ( where == null )
            return new Node(p,d);
        if ( d%2 == 1 ) // this level has even depth, compare y coordinate
        {
            if ( p.y() > where.point.y() )
                where.right = insertTo(where.right,p,d+1);
            else if ( p.y() < where.point.y() )
                where.left = insertTo(where.left,p,d+1);
            else
                where.point = p;
        }
        else // this level has odd depth, compare x coordinate
        {
            if ( p.x() > where.point.x() )
                where.right = insertTo(where.right,p,d+1);
            else if ( p.x() < where.point.x() )
                where.left = insertTo(where.left,p,d+1);
            else
                where.point = p;
        }
        return where;
    }

    public boolean contains(Point2D p)
    {
        if ( p == null )
            throw new IllegalArgumentException("Argument to contains() method cannot be null!!");
        return isIn(root,p);
    }

    private boolean isIn(Node where, Point2D p)
    {
        if ( where == null )
            return false;
        if ( p.distanceTo(where.point) <= 1E-8 )
            return true;
        else
            return isIn(where.left,p)||isIn(where.right,p);
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

    // public Iterable<Point2D> range(RectHV rect);

    // public Point2D nearest(Point2D p);

    public void displayme()
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
        KdTree ps = new KdTree();
        RectHV rect = new RectHV(0.1,0.1,0.3,0.4);
        int numOfPoints = 7;
        Random rand = new Random();
        for ( int i = 0; i < numOfPoints; i++ )
        {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            ps.insert(new Point2D(x,y));
        }

        ps.displayme();

        StdDraw.setPenRadius(0.005);
        StdDraw.line(-0.1,0.0,1.1,0.0);
        StdDraw.line(0.0,-0.1,0.0,1.1);

        StdDraw.setPenColor(StdDraw.BLUE);
        rect.draw();
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.BLACK);
        ps.draw();
        //StdDraw.setPenColor(StdDraw.BLUE);
        //for ( Point2D p: ps.range(rect) )
        //    p.draw();
    }
}
