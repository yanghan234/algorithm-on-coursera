import java.util.HashSet;
public class FlowNetwork
{
    private final int numOfEdge;
    private HashSet<FlowEdge>[] adj;
    public FlowNetwork(int V)
    {
        this.numOfEdge = V;
        adj = (HashSet<FlowEdge>[]) new HashSet[this.numOfEdge];
        for ( int i = 0; i < numOfEdge; i++ )
            adj[i] = new HashSet<FlowEdge>();
    }

    public void addEdge(FlowEdge e)
    {
        int from = e.from();
        int to = e.to();
        adj[from].add(e);
        adj[to].add(e);
    }

    public Iterable<FlowEdge> adj(int v)
    {
        return adj[v];
    }

    public int V() { return this.numOfEdge; }

    public static void main(String[] args)
    {
        FlowNetwork G = new FlowNetwork(10);
    }
}
