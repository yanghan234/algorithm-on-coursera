import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
public class DAGSP extends SP
{
    public DAGSP(EdgeWeightedDigraph G, int src)
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
        distTo[src] = 0.0;
        this.computeSP(src);
    }

    private void computeSP(int s)
    {
        TopologicalSort topo = new TopologicalSort(this.graph,s);
        for ( int i: topo.topoOrder() )
            for ( DirectedEdge e: graph.adj(i) )
            {
                int v = e.from();
                int w = e.to();
                if ( distTo[w] > distTo[v]+e.weight() )
                {
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                }
            }
    }

    public boolean hasPathTo(int v)
    {
        return edgeTo[v] != null;
    }

    public double distanceTo(int v)
    {
        return distTo[v];
    }

    public Iterable<DirectedEdge> edges()
    {
        for ( int i = 0; i < graph.V(); i++ )
            if ( i != this.src )
                SPedges.add(edgeTo[i]);
        return SPedges;
    }

    public Iterable<DirectedEdge> pathTo(int v)
    {
        if ( !hasPathTo(v) )
            throw new IllegalArgumentException();
        LinkedList<DirectedEdge> s = new LinkedList<DirectedEdge>();
        while ( edgeTo[v] != null )
        {
            s.addFirst( edgeTo[v] );
            v = edgeTo[v].from();
        }
        return s;
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

        DAGSP sp = new DAGSP(g,0);
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
    }
}
