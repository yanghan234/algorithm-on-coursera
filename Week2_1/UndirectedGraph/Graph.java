/*
 * Here, we implement a Graph with adjacency-list representation
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
public class Graph
{
    private int numOfVertex;
    private int numOfEdge;
    private HashSet<Integer>[] adj;

    public Graph(int V)
    {
        if ( V <= 0)
            throw new IllegalArgumentException("Illegal number of vertices!!");
        numOfVertex = V;
        numOfEdge = 0;
        adj = (HashSet<Integer>[]) new HashSet[numOfVertex];
        for ( int i = 0; i < numOfVertex; i++ )
            adj[i] = (HashSet<Integer>) new HashSet();
    }

    public void addEdge(int v, int w)
    {
        if ( v < 0 || w < 0
                || v >= numOfVertex || w >= numOfVertex )
            throw new IllegalArgumentException("Illegal argument to addEdge() method");
        adj[v].add(w);
        adj[w].add(v);
        numOfEdge++;
    }

    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }

    public int V() { return numOfVertex; }
    public int E() { return numOfEdge; }

    public void display()
    {
        System.out.printf("%10s,%20s\n","Index","ConnectedTo");
        for ( int i = 0; i < numOfVertex; i++ )
        {
            System.out.printf("%10d,",i);
            System.out.print(adj(i).toString());
            System.out.printf("\n");
        }
    }

    public static void main(String[] args)
    {
        int numOfVertex = 100;
        Random rand = new Random();
        ArrayList<Point2D> points = new ArrayList<Point2D>(numOfVertex);
        Graph g = new Graph(numOfVertex);
        for ( int i = 0; i < numOfVertex*0.75; i++ )
        {
            int v = rand.nextInt(numOfVertex);
            int w = rand.nextInt(numOfVertex);
            g.addEdge(v,w);
        }

        for ( int i = 0; i < numOfVertex; i++ )
        {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            points.add(new Point2D(x,y));
        }

        for ( int i = 0; i < numOfVertex; i++ )
        {
            Point2D thisPoint = points.get(i);
            StdDraw.setPenRadius(0.02);
            thisPoint.draw();
            StdDraw.setPenRadius(0.002);
            for ( Integer j : g.adj(i) )
                if ( i < j )
                    thisPoint.drawTo(points.get(j));
        }
    }
}
