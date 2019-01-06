public class StrongComponent
{
    private Digraph dg;
    private Digraph rdg;    // reversed digraph

    private boolean[] marked;
    private int[] scc;
    private int[] prev;

    public StrongComponent(Digraph dg)
    {
        this.dg = dg;
        rdg = reverseGraph(dg);

        marked = new boolean[dg.V()];
        scc = new int[dg.V()];
        prev = new int[dg.V()];

        for ( int i = 0; i < dg.V(); i++ )
        {
            marked[i] = false;
            scc[i] = 0;
            prev[i] = 0;
        }

        TopologicalSort topo = new TopologicalSort(rdg);
        topo.compTopSort();
        System.out.println("Reverse order of reverse digraph"+topo.reverseOrder().toString());

        int icc = 0;
        for ( int i: topo.reverseOrder() )
            if ( !marked[i] )
            {
                marked[i] = true;
                prev[i] = i;
                DFS(dg,i,icc++);
            }
    }

    public Digraph reverseGraph(Digraph dg)
    {
        Digraph rdg = new Digraph(dg.V());
        for ( int i = 0; i < dg.V(); i++ )
            for ( int j: dg.adj(i) )
                rdg.addEdge(j,i);
        return rdg;
    }

    public void DFS(Digraph g, int s, int icc)
    {
        for ( int i: g.adj(s) )
        {
            if ( !marked[i] )
            {
                marked[i] = true;
                prev[i] = s;
                scc[i] = icc;
            }
        }
    }

    public void display()
    {
        System.out.printf("%10s,%10s,%10s,%10s\n","Index", "Marked", "Prev", "SCC");
        for ( int i = 0; i < dg.V(); i++ )
            System.out.printf("%10d,%10b,%10d,%10d\n",i,marked[i],prev[i],scc[i]);
    }

    public static void main(String[] args) throws Exception
    {
        Digraph dg = new Digraph(args[0]);
        dg.display();

        StrongComponent scc = new StrongComponent(dg);
        scc.display();
    }

}
