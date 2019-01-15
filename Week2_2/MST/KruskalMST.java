import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
public class KruskalMST extends MST
{
    private int[] id;
    private MinPQ<Edge> qE;
    private ArrayList<Edge> MSTedges;
    public KruskalMST( EdgeWeightedGraph g )
    {
        super(g);
        id = new int[g.V()];
        qE = new MinPQ<Edge>();
        for ( int i = 0; i < g.V(); i++ )
            id[i] = i;
        for ( Edge e: graph.edges() )
            qE.insert(e);
        this.computeMST();
    }

    public Iterable<Edge> edges()
    {
        return MSTedges;
    }

    private void computeMST()
    {
        int numInMST = 0;
        MSTedges = new ArrayList<Edge>();
        while ( !qE.isEmpty() && numInMST < graph.V()-1 )
        {
            Edge e = qE.delMin();
            int v = e.either();
            int w = e.other(v);
            double weight = e.weight();
            if ( root(v) != root(w) )
            {
                MSTedges.add(e);
                id[root(v)] = root(w);
                numInMST++;
                totalWeight += e.weight();
            }
        }
    }

    private int root( int v )
    {
        int rootv = v;
        while ( rootv != id[rootv] )
            rootv = id[rootv];
        while ( v != rootv )
        {
            int newv = id[v];
            id[v] = rootv;
            v = newv;
        }
        return rootv;
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

        EdgeWeightedGraph g = new EdgeWeightedGraph(filename);
        StdDraw.setPenRadius(0.005);
        for ( Edge e: g.edges() )
        {
            int v = e.either();
            int w = e.other(v);
        }
        StdDraw.show();

        KruskalMST mst = new KruskalMST(g);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(StdDraw.BLACK);
        int counter = 0;
        for ( Edge e: mst.edges() )
        {
            int v = e.either();
            int w = e.other(v);
            StdDraw.setPenRadius(0.02);
            points[v].draw();
            points[w].draw();
            StdDraw.setPenRadius(0.005);
            points[v].drawTo(points[w]);
            StdDraw.show();
            StdDraw.pause(20);
            StdDraw.save("./plots/Snapshot_"+counter+".jpg");
            counter++;
        }
    }
}
