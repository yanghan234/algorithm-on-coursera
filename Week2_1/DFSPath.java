import java.lang.IllegalArgumentException;
import java.util.Stack;
public class DFSPath
{
    private Graph g;
    private int s;
    public boolean[] marked;
    public int[] prev;

    public DFSPath( Graph g, int s )
    {
        if ( g == null || s < 0 || s > g.V() )
            throw new IllegalArgumentException("Illegal argument to DFSPath constructor!!");
        this.g = g;
        this.s = s;
        marked = new boolean[g.V()];
        prev = new int[g.V()];
        for ( int i = 0; i < marked.length; i++ )
            marked[i] = false;
    }

    public boolean hasPathTo(int t)
    {
        return marked[t];
    }

    public Iterable<Integer> pathTo(int t)
    {
        if ( !hasPathTo(t) )
            return null;
        Stack<Integer> d = new Stack<Integer>();

        while ( t != prev[t] )
        {
            d.add(t);
            t = prev[t];
        }
        d.add(t);
        return d;
    }

    // Recursive DFS
    public void DFS(Graph g, int src)
    {
        marked[src] = true;
        for ( int i : g.adj(src) )
        {
            if ( marked[i] == false )
            {
                prev[i] = src;
                this.DFS(g, i);
            }
        }
    }

    // DFS with stack, not recursive
    public void StackDFS( )
    {
        Stack<Integer> st = new Stack<Integer>();
        st.push(s);
        while ( !st.isEmpty() )
        {
            int v = st.peek();
            st.pop();
            if ( marked[v] == true )
                continue;
            marked[v] = true;
            for ( int i : g.adj(v) )
                if ( marked[i] == false )
                {
                    st.push(i);
                    prev[i] = v;
                }
        }
    }

    public static void main(String[] args)
    {
        Graph g = new Graph(7);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,6);
        g.addEdge(0,5);
        g.addEdge(4,6);
        g.addEdge(3,4);
        g.addEdge(3,5);
        g.addEdge(4,5);

        g.display();

        DFSPath paths = new DFSPath(g,0);
        paths.StackDFS();
        System.out.printf("%10s,%10s,%10s\n","Index", "Marked", "Prev");
        for ( int i = 0; i < g.V(); i++ )
            System.out.printf("%10d,%10b,%10d\n",i,paths.marked[i],paths.prev[i]);

        System.out.println(paths.pathTo(4));
    }
}
