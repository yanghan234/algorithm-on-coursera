import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
public class TopologicalSort
{
    private int src;
    private boolean[] marked;
    private EdgeWeightedDigraph graph;
    private LinkedList<Integer> postOrder,topoOrder;
    public TopologicalSort(EdgeWeightedDigraph G, int s)
    {
        graph = G;
        src = s; // in case the graph is not partially connected, we need know where to start
        marked = new boolean[graph.V()];
        postOrder = new LinkedList<Integer>();
        topoOrder = new LinkedList<Integer>();
        for ( int i = 0; i < graph.V(); i++ )
            marked[i] = false;

        this.computeTopo();
    }

    public void computeTopo()
    {
        this.dfs(this.src);
    }

    public void dfs(int s)
    {
        for ( DirectedEdge e: graph.adj(s) )
        {
            int v = e.to();
            if ( !marked[v] )
            {
                marked[v] = true;
                dfs(v);
            }
        }
        postOrder.addLast(s);
        topoOrder.addFirst(s);
    }

    public Iterable<Integer> topoOrder()
    {
        return topoOrder;
    }
}
