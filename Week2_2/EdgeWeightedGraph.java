import java.util.HashSet;
import java.lang.IllegalArgumentException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class EdgeWeightedGraph
{
    private int numOfVertex;
    private int numOfEdge;
    private HashSet<Edge>[] adj;

    public EdgeWeightedGraph(int v)
    {
        if ( v <= 0 )
            throw new IllegalArgumentException("Illegal argument to the constructor");
        this.numOfVertex = v;
        adj = (HashSet<Edge>[]) new HashSet[numOfVertex];
        for ( int i = 0; i < numOfVertex; i++ )
            adj[i] = new HashSet<Edge>();
        numOfEdge = 0;
    }

    public EdgeWeightedGraph(String fname) throws Exception
    {
        File f = new File(fname);
        BufferedReader br = new BufferedReader(new FileReader(f));

        String st;
        st = br.readLine();
        this.numOfVertex = Integer.valueOf(st);

        adj = (HashSet<Edge>[]) new HashSet[numOfVertex];
        for ( int i = 0; i < numOfVertex; i++ )
            adj[i] = new HashSet<Edge>();
        numOfEdge = 0;
        st = br.readLine(); // read numOfEdge and drop it

        while ( (st = br.readLine()) != null )
        {
            String[] strs = st.trim().split("\\s+");
            int v = Integer.valueOf(strs[0]);
            int w = Integer.valueOf(strs[1]);
            double weight = Double.valueOf(strs[2]);
            addEdge(new Edge(v,w,weight));
        }
    }

    public void addEdge(Edge e)
    {
        int v = e.either();
        int w = e.other(v);
        if ( v < 0 || v >= numOfVertex || w < 0 || w >= numOfVertex )
            throw new IllegalArgumentException("Illegal argument to addEdge() method");
        adj[v].add(e);
        adj[w].add(e);
        numOfEdge++;
    }

    public Iterable<Edge> adj(int v)
    {
        return adj[v];
    }

    public Iterable<Edge> edges( )
    {
        HashSet<Edge> allEdge = new HashSet<Edge>();
        for ( int i = 0; i < numOfVertex; i++ )
            for ( Edge e : adj[i] )
                allEdge.add(e);
        return allEdge;
    }

    public int V() { return numOfVertex; }
    public int E() { return numOfEdge; }

    public void display()
    {
        System.out.printf("%10s,%s\n","Vertex","Edges");
        for ( int i = 0; i < numOfVertex; i++ )
        {
            System.out.printf("%10d,",i);
            for ( Edge e: adj[i] )
                System.out.printf("%s,",e.toString());
            System.out.printf("\n");
        }
    }

    public static void main(String[] args) throws Exception
    {
        EdgeWeightedGraph g = new EdgeWeightedGraph(args[0]);

        for ( Edge e: g.edges() )
            System.out.println(e.toString());

        g.display();
    }
}
