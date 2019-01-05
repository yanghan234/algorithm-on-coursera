import java.lang.IllegalArgumentException;
import java.util.Stack;
import java.util.LinkedList;
public class BFSPath
{
    private Graph g;
    private int s;
    public boolean[] marked;
    public int[] prev;

    public BFSPath( Graph g, int s )
    {
        if ( g == null || s < 0 || s > g.V() )
            throw new IllegalArgumentException("Illegal argument to BFSPath constructor!!");
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

    // implement BFS with queue
    public void BFS( )
    {
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(s);
        marked[s] = true;
        prev[s] = s;
        while ( !q.isEmpty() )
        {
            int v = q.poll();
            for ( int i : g.adj(v) )
            {
                if ( marked[i] == true )
                    continue;
                marked[i] = true;
                prev[i] = v;
                q.addLast(i);
            }
        }
    }

    public static void main(String[] args)
    {
        Graph g = new Graph(8);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,6);
        g.addEdge(0,5);
        g.addEdge(4,6);
        g.addEdge(3,4);
        g.addEdge(3,5);
        g.addEdge(4,5);
        g.addEdge(6,7);

        g.display();

        BFSPath paths = new BFSPath(g,0);
        paths.BFS();
        System.out.printf("%10s,%10s,%10s\n","Index", "Marked", "Prev");
        for ( int i = 0; i < g.V(); i++ )
            System.out.printf("%10d,%10b,%10d\n",i,paths.marked[i],paths.prev[i]);

        System.out.println(paths.pathTo(1));
        System.out.println(paths.pathTo(2));
        System.out.println(paths.pathTo(3));
        System.out.println(paths.pathTo(4));
        System.out.println(paths.pathTo(5));
        System.out.println(paths.pathTo(6));
        System.out.println(paths.pathTo(7));
    }
}
