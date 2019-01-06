import java.util.HashSet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.IllegalArgumentException;
public class Digraph
{
    private int numOfVertex;
    private int numOfEdge;
    private HashSet<Integer>[] adj;
    public Digraph( int v )
    {
        if ( v <= 0 )
            throw new IllegalArgumentException("Illegal argument to Digraph constructor");
        numOfVertex = v;
        adj = (HashSet<Integer>[]) new HashSet[numOfVertex];
        for ( int i = 0; i < numOfVertex; i++ )
        {
            adj[i] = new HashSet<Integer>();
        }
        numOfEdge = 0;
    }

    public Digraph(String filename) throws Exception
    {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        st = br.readLine();
        numOfVertex = Integer.valueOf(st);
        numOfEdge = 0;
        adj = (HashSet<Integer>[]) new HashSet[numOfVertex];
        for ( int i = 0; i < numOfVertex; i++ )
            adj[i] = new HashSet<Integer>();

        while ((st = br.readLine())!=null)
        {
            String[] strs = st.split("\\s+");
            int v = Integer.valueOf(strs[0]);
            int w = Integer.valueOf(strs[1]);
            addEdge(v,w);
        }

        br.close();
    }

    public void addEdge(int v, int w)
    {
        numOfEdge -= adj[v].size();
        adj[v].add(w);
        numOfEdge += adj[v].size();
    }

    public int V()
    {
        return numOfVertex;
    }

    public int E()
    {
        return numOfVertex;
    }

    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }

    public void display()
    {
        System.out.printf("%10s,%20s\n","Index","ConnectedTo");
        for ( int i = 0; i < numOfVertex; i++ )
        {
            System.out.printf("%10d,",i);
            System.out.printf("%20s",adj(i).toString());
            System.out.printf("\n");
        }
    }

    public static void main(String[] args) throws Exception
    {
        Digraph dg = new Digraph(args[0]);
        dg.display();
    }

}
