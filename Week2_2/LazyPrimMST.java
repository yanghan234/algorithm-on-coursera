import java.util.ArrayList;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
public class LazyPrimMST extends MST
{
    private boolean[] marked;
    private ArrayList<Edge> MSTedges;
    private int numInMST;
    public LazyPrimMST(EdgeWeightedGraph g)
    {
        super(g);
        marked = new boolean[g.V()];
        numInMST = 0;
        MSTedges = new ArrayList<Edge>();
        for ( int i = 0; i < g.V(); i++ )
            marked[i] = false;
        this.computeMST();
    }

    public Iterable<Edge> edges()
    {
        return MSTedges;
    }

    private void computeMST()
    {
        MinPQ<Edge> qE = new MinPQ<Edge>();
        for ( Edge e: this.graph.adj(0) )
            qE.insert(e);

        while ( !qE.isEmpty() && numInMST < this.graph.V()-1 )
        {
            Edge e = qE.delMin();
            int v = e.either();
            int w = e.other(v);
            double weight = e.weight();
            if ( !marked[v] || !marked[w] )
            {
                MSTedges.add(e);
                if ( !marked[v] )
                    for ( Edge ee: this.graph.adj(v) )
                        qE.insert(ee);
                if ( !marked[w] )
                    for ( Edge ee: this.graph.adj(w) )
                        qE.insert(ee);
                marked[v] = true;
                marked[w] = true;
                this.totalWeight += weight;
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


        EdgeWeightedGraph g = new EdgeWeightedGraph(filename);
        StdDraw.setPenRadius(0.005);
        for ( Edge e: g.edges() )
        {
            int v = e.either();
            int w = e.other(v);
        }
        StdDraw.show();

        LazyPrimMST mst = new LazyPrimMST(g);
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
