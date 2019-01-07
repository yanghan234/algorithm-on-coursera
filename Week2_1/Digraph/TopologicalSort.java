import java.util.LinkedList;
public class TopologicalSort
{
    private Digraph dg;
    private boolean[] marked;
    private int[] prev;
    private LinkedList<Integer> postOrder,reverseOrder,topoOrder;
    // topoOrder = reverseOrder
    private boolean hasCycle;

    public TopologicalSort(Digraph dg)
    {
        this.dg = dg;
        CheckDAG checker = new CheckDAG(dg);
        hasCycle = checker.hasCycle();
        //if ( !hasCycle ) compTopSort();
        compTopSort();
    }

    public void compTopSort()
    {
        postOrder = new LinkedList<Integer>();
        reverseOrder = new LinkedList<Integer>();
        topoOrder = new LinkedList<Integer>();
        marked = new boolean[dg.V()];
        prev = new int[dg.V()];
        for ( int i = 0; i < dg.V(); i++ )
        {
            marked[i] = false;
            prev[i] = 0;
        }
        for ( int i = 0; i < dg.V(); i++ )
            if ( !marked[i] )
            {
                marked[i] = true;
                prev[i] = i;
                DFS(dg,i);
            }
    }

    public void DFS( Digraph g, int s )
    {
        for ( int i: g.adj(s) )
        {
            if ( !marked[i] )
            {
                prev[i] = s;
                marked[i] = true;
                DFS(g,i);
            }
        }
        postOrder.addLast(s);
        reverseOrder.addFirst(s);
        topoOrder.addFirst(s);
    }

    public Iterable<Integer> postOrder()
    {
        return postOrder;
    }

    public Iterable<Integer> reverseOrder()
    {
        return reverseOrder;
    }

    public void display()
    {
        System.out.printf("%10s,%10s,%10s\n","Index", "Marked", "Prev");
        for ( int i = 0; i < dg.V(); i++ )
            System.out.printf("%10d,%10b,%10d\n",i,marked[i],prev[i]);
        System.out.println("Topo order:"+topoOrder.toString());
        System.out.println("Post order:"+postOrder.toString());
    }

    public static void main(String[] args) throws Exception
    {
        Digraph dg = new Digraph(args[0]);
        dg.display();

        TopologicalSort topo = new TopologicalSort(dg);
        topo.display();
    }
}

