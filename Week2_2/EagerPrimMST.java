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
        public Node( int me, double lastDistToMe )
        {
            this.me = me;
            this.lastDistToMe = lastDistToMe;
        }
        public int compareTo(Node that)
        {
            if ( this.lastDistToMe < that.lastDistToMe )
                return -1;
            else if ( this.lastDistToMe > that.lastDistToMe )
                return 1;
            else
                return 0;
        }
        public boolean equals(Object that)
        {
            if ( that == null ) return false;
            else if ( that.getClass() != this.getClass() ) return false;
            else
            {
                Node tmp = (Node) that;
                return this.me == tmp.me;
            }
        }
        public String toString()
        {
            String s = "";
            s += "( me : "+me+", dist : "+lastDistToMe+" )";
            return s;
        }
    }

    public Iterable<Edge> edges()
    {
        return MSTEdges;
    }

    //public void computeMST(int s)
    //{
    //    MinPQ<Node> pq = new MinPQ<Node>();
    //    pq.add(new Node(0,0.1));
    //    pq.add(new Node(1,1.1));
    //    pq.add(new Node(5,0.6));
    //    pq.add(new Node(3,1.2));
    //    pq.add(new Node(12,0.8));
    //    pq.add(new Node(8,0.7));
    //    pq.add(new Node(10,0.9));
    //    for ( Node n: pq )
    //        System.out.printf("me = %d, weight = %f\n",n.me,n.lastDistToMe);
    //    System.out.printf("\n");
    //    pq.delete(new Node(12,1.1));
    //    for ( Node n: pq )
    //        System.out.printf("me = %d, weight = %f\n",n.me,n.lastDistToMe);
    //}

    public void computeMST(int s)
    {
        MinPQ<Node> pq = new MinPQ<Node>();
        pq.add(new Node(s,0.0));
        distTo[s] = 0.0;
        edgeTo[s] = null;
        numInMST = 0;

        while ( !pq.isEmpty() && numInMST < graph.V() )
        {
            Node n = pq.delMin();
            if ( marked[n.me] ) continue;
            marked[n.me] = true;
            numInMST++;
            if ( edgeTo[n.me] != null ) MSTEdges.add(edgeTo[n.me]);
            for ( Edge e: graph.adj(n.me) )
            {
                int m = e.other(n.me);
                if ( !marked[m] )
                {
                    if ( edgeTo[m] == null )
                    {
                        distTo[m] = e.weight();
                        edgeTo[m] = e;
                    }
                    else if ( distTo[m] > e.weight() )
                    {
                        pq.delete(new Node(m,distTo[m]));
                        distTo[m] = e.weight();
                        edgeTo[m] = e;
                    }
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


        EdgeWeightedGraph g = new EdgeWeightedGraph(filename);
        StdDraw.setPenRadius(0.005);
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
