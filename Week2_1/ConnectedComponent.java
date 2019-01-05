import java.util.LinkedList;
public class ConnectedComponent
{
    private Graph g;
    private boolean[] marked;
    private int[] cc;
    private int[] prev;
    private int numOfComponent;
    public ConnectedComponent( Graph g )
    {
        this.g = g;
        marked = new boolean[g.V()];
        cc = new int[g.V()];
        prev = new int[g.V()];
        int icc = 0;
        for ( int i = 0; i < g.V(); i++ )
        {
            if ( marked[i] == true )
                continue;
            BFS(g, i, icc);
            icc++;
        }
        numOfComponent = icc;
    }

    public int numOfComponent()
    {
        return numOfComponent;
    }

    public boolean isConnected(int v, int w)
    {
        return cc[v] == cc[w];
    }

    public int id(int v)
    {
        return cc[v];
    }

    private void BFS(Graph g, int src, int icc )
    {
        LinkedList<Integer> q = new LinkedList<Integer>();
        prev[src] = src;
        cc[src] = icc;
        q.addLast(src);
        marked[src] = true;
        while ( !q.isEmpty() )
        {
            int v = q.poll();
            for ( int i : g.adj(v) )
            {
                if ( marked[i] == true )
                    continue;
                q.addLast(i);
                marked[i] = true;
                prev[i] = v;
                cc[i] = icc;
            }
        }
    }

    public void display()
    {
        System.out.printf("Number of components in the graph:%d\n",numOfComponent);
        System.out.printf("%10s,%10s,%10s,%10s\n","Index", "Marked", "Prev", "CC");
        for ( int i = 0; i < g.V(); i++ )
            System.out.printf("%10d,%10b,%10d,%10d\n",i,marked[i],prev[i],cc[i]);

    }

    public static void main(String[] args)
    {
        Graph g = new Graph(11);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(4,6);
        g.addEdge(3,4);
        g.addEdge(3,5);
        g.addEdge(4,5);
        g.addEdge(6,7);
        g.addEdge(8,9);
        g.addEdge(8,10);

        g.display();

        ConnectedComponent cc = new ConnectedComponent(g);

        cc.display();
    }
}
