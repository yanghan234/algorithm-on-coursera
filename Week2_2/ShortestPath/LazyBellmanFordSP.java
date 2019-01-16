import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
public class LazyBellmanFordSP extends SP
{
    public LazyBellmanFordSP(EdgeWeightedDigraph G, int src)
    {
        this.src = src;
        this.graph = G;
        this.distTo = new double[graph.V()];
        this.edgeTo = new DirectedEdge[graph.V()];
        this.SPedges = new ArrayList<DirectedEdge>();
        for ( int i = 0; i < graph.V(); i++ )
        {
            distTo[i] = Double.POSITIVE_INFINITY;
            edgeTo[i] = null;
        }
        distTo[src] = 0.0;
        this.computeSP(src);
    }

    public double distanceTo(int v)
    {
        if ( !hasPathTo(v) )
            throw new IllegalArgumentException();
        return distTo[v];
    }

    public boolean hasPathTo(int v)
    {
        return edgeTo[v] != null;
    }

    public Iterable<DirectedEdge> pathTo(int v)
    {
        if ( !hasPathTo(v) )
            throw new IllegalArgumentException();
        LinkedList<DirectedEdge> q = new LinkedList<DirectedEdge>();
        while ( edgeTo[v] != null )
        {
            q.addFirst(edgeTo[v]);
            v = edgeTo[v].from();
        }
        return q;
    }

    private void computeSP(int src)
    {
        for ( int pass = 0; pass < graph.V(); pass++ )
            for ( int v = 0; v < graph.V(); v++ )
                for ( DirectedEdge e: graph.adj(v) )
                {
                    int w = e.to();
                    if ( distTo[w] > distTo[v] + e.weight() )
                    {
                        distTo[w] = distTo[v] + e.weight();
                        edgeTo[w] = e;
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

        LazyBellmanFordSP sp = new LazyBellmanFordSP(g,0);
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
