import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
public class DijkstraSP extends SP
{
    public DijkstraSP(EdgeWeightedDigraph G, int src)
    {
        this.src = src;
        this.graph = G;
        distTo = new double[graph.V()];
        edgeTo = new DirectedEdge[graph.V()];
        SPedges = new ArrayList<DirectedEdge>();

        for ( int i = 0; i < graph.V(); i++ )
        {
            distTo[i] = Double.POSITIVE_INFINITY;
            edgeTo[i] = null;
        }
        computeSP(src);
    }

    private class Node implements Comparable<Node>
    {
        private int me;
        private double distToMe;
        public Node(int me, double distToMe)
        {
            this.me = me;
            this.distToMe = distToMe;
        }

        public boolean equals(Object that)
        {
            if ( that == null ) return false;
            else if ( this.getClass() != that.getClass() ) return false;
            else
            {
                Node tmp = (Node) that;
                return this.me == tmp.me;
            }
        }

        public int compareTo(Node that)
        {
            if ( this.distToMe < that.distToMe ) return -1;
            else if ( this.distToMe > that.distToMe ) return 1;
            else return 0;
        }
    }

    public double distanceTo(int v)
    {
        if ( edgeTo[v] == null )
            throw new IllegalArgumentException();
        return distTo[v];
    }

    public boolean hasPathTo(int v)
    {
        return edgeTo[v] != null;
    }

    public Iterable<DirectedEdge> pathTo(int v)
    {
        if ( edgeTo[v] == null )
            throw new IllegalArgumentException();
        LinkedList<DirectedEdge> s = new LinkedList<DirectedEdge>();
        while ( edgeTo[v] != null )
        {
            s.addFirst( edgeTo[v] );
            v = edgeTo[v].from();
        }
        return s;
    }

    public Iterable<DirectedEdge> edges()
    {
        return SPedges;
    }

    private void computeSP(int s)
    {
        MinPQ<Node> pq = new MinPQ<Node>();
        pq.add(new Node(s,0.0));
        distTo[s] = 0.0;
        edgeTo[s] = null;

        while ( !pq.isEmpty() )
        {
            Node n = pq.delMin();
            if ( edgeTo[n.me] != null ) SPedges.add(edgeTo[n.me]);
            for ( DirectedEdge e: graph.adj(n.me) )
            {
                int m = e.to();
                if ( distTo[m] > distTo[n.me] + e.weight() )
                {
                    pq.delete(new Node(m,0.0));
                    edgeTo[m] = e;
                    distTo[m] = distTo[n.me] + e.weight();
                    pq.add(new Node(m,distTo[m]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        int dim = 15;
        String filename = String.valueOf(dim)+"x"+String.valueOf(dim)+".txt";
        System.out.println(filename);

        Point2D[] points = new Point2D[dim*dim];
        StdDraw.disableDoubleBuffering();
        StdDraw.setScale(-1,dim);
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for ( int i = 0; i < dim*dim; i++ )
            {
                points[i] = new Point2D(i/dim,i%dim);
                points[i].draw();
            }

        EdgeWeightedDigraph g = new EdgeWeightedDigraph(filename);
        StdDraw.setPenRadius(0.005);
        StdDraw.show();

        DijkstraSP sp = new DijkstraSP(g,0);
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setPenColor(StdDraw.BLACK);
//        int counter = 0;
//        for ( DirectedEdge e: sp.edges() )
//        {
//            int v = e.from();
//            int w = e.to();
//            StdDraw.setPenRadius(0.02);
//            points[v].draw();
//            points[w].draw();
//            StdDraw.setPenRadius(0.005);
//            points[v].drawTo(points[w]);
//            StdDraw.show();
//            StdDraw.pause(20);
////            StdDraw.save("./plots/Snapshot_"+counter+".jpg");
//            counter++;
//        }

        int target = g.V()-1;
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.RED);
        points[0].draw();
        points[target].draw();
        int counter = 0;
        for ( DirectedEdge e: sp.pathTo(target) )
        {
            int v = e.from();
            int w = e.to();
            StdDraw.setPenRadius(0.005);
            points[v].drawTo(points[w]);
            StdDraw.show();
            StdDraw.pause(20);
            //StdDraw.save("./plots/Snapshot_"+counter+".jpg");
            counter++;
        }

        System.out.println("distTo:"+sp.distanceTo(target));
    }
}
