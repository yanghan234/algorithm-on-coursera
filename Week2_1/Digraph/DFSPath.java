import java.util.Stack;
public class DFSPath
{
    private Digraph dg;
    private int src;  // starting point/src of the DFS path
    private boolean[] marked;
    private int[] prev;
    public DFSPath(Digraph dg, int src)
    {
        this.dg = dg;
        this.src = src;
        marked = new boolean[dg.V()];
        prev = new int[dg.V()];
        for ( int i = 0; i < marked.length; i++ )
            marked[i] = false;
        prev[src] = src;
        marked[src] = true;
        // DFS(dg,src);
        StackDFS();
    }

    // recursively run dfs
    public void DFS(Digraph g, int s)
    {
        for ( int i : g.adj(s) )
            if ( !marked[i] )
            {
                marked[i] = true;
                prev[i] = s;
                DFS(g,i);
            }
    }

    // non-recursively run dfs with stack
    public void StackDFS( )
    {
        Stack<Integer> toVisit = new Stack<Integer>();
        toVisit.push(src);
        while ( !toVisit.isEmpty() )
        {
            int v = toVisit.pop();
            for ( int i : dg.adj(v) )
                if ( !marked[i] )
                {
                    toVisit.push(i);
                    marked[i] = true;
                    prev[i] = v;
                }
        }
    }

    public boolean hasPathTo(int t)
    {
        return marked[t];
    }

    public Iterable<Integer> pathTo(int t)
    {
        if ( !hasPathTo(t) )
            return null;
        Stack<Integer> path = new Stack<Integer>();
        while ( t != prev[t] )
        {
            path.push(t);
            t = prev[t];
        }
        path.push(t);
        return path;
    }

    public void display()
    {
        System.out.printf("%10s,%10s,%10s\n","Index", "Marked", "Prev");
        for ( int i = 0; i < dg.V(); i++ )
            System.out.printf("%10d,%10b,%10d\n",i,marked[i],prev[i]);
    }

    public static void main(String[] args) throws Exception
    {
        Digraph dg = new Digraph(args[0]);
        dg.display();

        DFSPath path = new DFSPath(dg,0);
        path.display();

    }
}
