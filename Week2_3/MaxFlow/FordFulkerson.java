import java.util.LinkedList;
public class FordFulkerson
{
    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double value;

    public FordFulkerson(FlowNetwork G, int s, int t)
    {
        value = 0.0;
        while ( hasAugmentingPathTo(G,s,t) )
        {
            double bottleneck = Double.POSITIVE_INFINITY;
            int v = t;
            while ( v != s )
            {
                FlowEdge e = edgeTo[v];
                if ( bottleneck > e.residualCapacityTo(v) )
                    bottleneck = e.residualCapacityTo(v);
                v = e.other(v);
            }

            v = t;
            while ( v != s )
            {
                FlowEdge e = edgeTo[v];
                e.addResidualFlowTo(v,bottleneck);
                v = e.other(v);
            }
            value += bottleneck;
        }
    }

    private boolean hasAugmentingPathTo(FlowNetwork G, int s, int t)
    {
        marked = new boolean[G.V()];
        edgeTo = new FlowEdge[G.V()];

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.addLast(s);
        marked[s] = true;

        while ( !queue.isEmpty() )
        {
            int v = queue.pollFirst();
            for ( FlowEdge e: G.adj(v) )
            {
                int w = e.other(v);
                if ( e.residualCapacityTo(w) > 0 && !marked[w] )
                {
                    edgeTo[w] = e;
                    marked[w] = true;
                    queue.addLast(w);
                }
            }
        }
        return marked[t];
    }

    public double value() { return value; }
    public boolean inCut(int v) { return marked[v]; }
}
