import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
public class EagerPrimMST extends MST
{
    private double[] distTo;
    private Edge[] edgeTo;
    private boolean[] marked;
    private int numInMST;
    private ArrayList<Edge> MSTEdges;
    public EagerPrimMST(EdgeWeightedGraph g)
    {
        super(g);
        numInMST = 0;
        distTo = new double[g.V()];
        edgeTo = new Edge[g.V()];
        marked = new boolean[g.V()];
        MSTEdges = new ArrayList<Edge>();
        for ( int i = 0; i < g.V(); i++ )
        {
            distTo[i] = Double.POSITIVE_INFINITY;
            edgeTo[i] = null;
            marked[i] = false;
        }
        computeMST(0);
    }

    private class Node implements Comparable<Node>
    {
        private int me;
        private double lastDistToMe;
        private Edge lastEdgeToMe;
        public Node( int me, double lastDistToMe, Edge lastEdgeToMe )
        {
            this.me = me;
            this.lastDistToMe = lastDistToMe;
            this.lastEdgeToMe = lastEdgeToMe;
        }

        public Node( int me, double lastDistToMe )
        {
            this.me = me;
            this.lastDistToMe = lastDistToMe;
            this.lastEdgeToMe = new Edge(0,0,0.0);
        }

        public int compareTo(Node that)
        {
            if ( this.lastEdgeToMe.weight() < that.lastEdgeToMe.weight() )
                return -1;
            else if ( this.lastEdgeToMe.weight() > that.lastEdgeToMe.weight() )
                return 1;
            else
                return 0;
        }
    }

    public Iterable<Edge> edges()
    {
        return MSTEdges;
    }

    public void computeMST(int s)
    {
        MinPQ<Node> pq = new MinPQ<Node>(graph.V());
        pq.add(new Node(s,s));

        while ( !pq.isEmpty() && numInMST < graph.V() )
        {
            Node n = pq.delMin();
            if ( marked[n.me] ) continue;
            marked[n.me] = true;
            numInMST++;
            MSTEdges.add(n.lastEdgeToMe);
            for ( Edge e: graph.adj(n.me) )
            {
                int m = e.other(n.me);
                if ( !marked[m] )
                {
                    if ( distTo[m] > e.weight() )
                    {

                    }
                    pq.add(new Node(m,e.weight(),e));
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


        EdgeWeightedGraph g = new EdgeWeightedGraph(filename);
        StdDraw.setPenRadius(0.005);
        for ( Edge e: g.edges() )
        {
            int v = e.either();
            int w = e.other(v);
        }
        StdDraw.show();

        EagerPrimMST mst = new EagerPrimMST(g);
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
//            StdDraw.save("./plots/Snapshot_"+counter+".jpg");
            counter++;
        }
    }
}
