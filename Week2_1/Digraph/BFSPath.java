import java.util.LinkedList;
import java.util.Stack;
public class BFSPath
{
    private Digraph dg;
    private int src;  // starting point/src of the BFS path
    private boolean[] marked;
    private int[] prev;
    public BFSPath(Digraph dg, int src)
    {
        this.dg = dg;
        this.src = src;
        marked = new boolean[dg.V()];
        prev = new int[dg.V()];
        for ( int i = 0; i < marked.length; i++ )
            marked[i] = false;
        prev[src] = src;
        marked[src] = true;
        BFS( );
    }

    // non-recursively run bfs with queue
    public void BFS( )
    {
        LinkedList<Integer> toVisit = new LinkedList<Integer>();
        toVisit.addLast(src);
        while ( !toVisit.isEmpty() )
        {
            int v = toVisit.poll();
            for ( int i : dg.adj(v) )
                if ( !marked[i] )
                {
                    toVisit.addLast(i);
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

        BFSPath path = new BFSPath(dg,0);
        path.display();
    }
}
