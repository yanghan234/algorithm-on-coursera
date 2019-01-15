import java.util.HashSet;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
public class EdgeWeightedDigraph
{
    private int numOfVertex;
    private int numOfEdge;
    private HashSet<DirectedEdge>[] adj;
    public EdgeWeightedDigraph(int V)
    {
        this.numOfVertex = V;
        this.numOfEdge = 0;
        adj = (HashSet<DirectedEdge>[]) new HashSet[numOfVertex];
        for ( int i = 0; i < numOfVertex; i++ )
            adj[i] = new HashSet<DirectedEdge>();
    }

    public EdgeWeightedDigraph(String filename) throws Exception
    {
        File f = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(f));

        String st;
        st = br.readLine();
        this.numOfVertex = Integer.valueOf(st);

        adj = (HashSet<DirectedEdge>[]) new HashSet[numOfVertex];
        for ( int i = 0; i < numOfVertex; i++ )
            adj[i] = new HashSet<DirectedEdge>();
        numOfEdge = 0;
        st = br.readLine(); // read numOfEdge and drop it

        while ( (st = br.readLine()) != null )
        {
            String[] strs = st.trim().split("\\s+");
            int v = Integer.valueOf(strs[0]);
            int w = Integer.valueOf(strs[1]);
            double weight = Double.valueOf(strs[2]);
            addEdge(new DirectedEdge(v,w,weight));
        }
    }

    public void addEdge(DirectedEdge e)
    {
        int from = e.from();
        adj[from].add(e);
        numOfEdge++;
    }

    public Iterable<DirectedEdge> adj(int i)
    {
        return adj[i];
    }

    public int V() { return numOfVertex; }
    public int E() { return numOfEdge; }

    public static void main(String[] args) throws Exception
    {
        String filename = "15x15.txt";
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(filename);

        for ( int i = 0; i < g.V(); i++ )
            for ( DirectedEdge e: g.adj(i) )
                System.out.println(e.toString());
    }
}
