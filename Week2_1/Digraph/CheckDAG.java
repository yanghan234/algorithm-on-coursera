/*
 * check if a given digraph has cycles in it
 */
import java.util.Stack;
public class CheckDAG
{
    private Digraph dg;
    private boolean[] marked;
    private boolean[] onStack;
    private boolean hasCycle;
    private int[] prev;

    public CheckDAG( Digraph dg )
    {
        this.dg = dg;
        marked = new boolean[dg.V()];
        onStack = new boolean[dg.V()];
        prev = new int[dg.V()];
        hasCycle = false;
        for ( int i = 0; i < dg.V(); i++ )
        {
            marked[i] = false;
            prev[i] = 0;
        }

        for ( int i = 0; i < dg.V(); i++ )
            if ( !marked[i] )
                StackDFS( dg, i );
    }

    public boolean isDAG( )
    {
        return !hasCycle;
    }

    public boolean hasCycle()
    {
        return hasCycle;
    }

    public void StackDFS( Digraph dg, int src )
    {
        for ( int i =0; i < dg.V(); i++ )
            onStack[i] = false;
        Stack<Integer> toVisit = new Stack<Integer>();
        toVisit.push(src);
        marked[src] = true;
        prev[src] = src;
        while ( !toVisit.isEmpty() )
        {
            int v = toVisit.pop();
            onStack[v] = true;
            for ( int i : dg.adj(v) )
            {
                if ( onStack[i] )
                    hasCycle = true;
                if ( !marked[i] )
                {
                    toVisit.push(i);
                    marked[i] = true;
                    prev[i] = v;
                }
            }
        }
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

        CheckDAG checker = new CheckDAG(dg);
        checker.display();
        System.out.println(checker.hasCycle());
    }

}
