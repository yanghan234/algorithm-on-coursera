import edu.princeton.cs.algs4.Digraph;
import java.lang.IllegalArgumentException;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Arrays;
public class SAP
{
    private Digraph graph;
    private LinkedList<Node> vAncestor;
    private LinkedList<Node> wAncestor;
    public SAP(Digraph G)
    {
        if ( G == null )
            throw new IllegalArgumentException("Argument to SAP constructor cannot be null!!");
        this.graph = G;
    }

    private class Node
    {
        public int me;
        public int prev;
        public Node(int m, int p)
        {
            this.me = m;
            this.prev = p;
        }
        public String toString()
        {
            return "( me: "+this.me+", prev: "+this.prev+" )\n";
        }
    }

    public int length(int v, int w)
    {
        int common = this.ancestor(v,w);
        if ( common == -1 )
            return -1;
        int numOfNodes = 0;
        for ( Node n: vAncestor )
        {
            if ( n.me != common )
                numOfNodes++;
            else
                break;
        }
        for ( Node n: wAncestor )
        {
            if ( n.me != common )
                numOfNodes++;
            else
                break;
        }
        return numOfNodes;
    }

    public int ancestor(int v, int w)
    {
        vAncestor = new LinkedList<Node>();
        wAncestor = new LinkedList<Node>();
        vAncestor = this.bfs(graph, v);
        wAncestor = this.bfs(graph, w);
        HashSet<Integer> visited = new HashSet<Integer>();

        for ( Node n: vAncestor )
        {
            System.out.print(n.toString());
            visited.add(n.me);
        }

        int common = -1;
        for ( Node n: wAncestor )
        {
            System.out.println(n.toString());
            if ( visited.contains(n.me) )
            {
                common = n.me;
                break;
            }
        }
        return common;
    }

    public LinkedList<Node> bfs(Digraph dg, int v)
    {
        HashSet<Integer> visited = new HashSet<Integer>();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        LinkedList<Node> ancestor = new LinkedList<Node>();

        visited.add(v);
        queue.addLast(v);
        ancestor.addLast(new Node(v,v));
        while ( !queue.isEmpty() )
        {
            int w = queue.pollFirst();
            for ( int i: dg.adj(w) )
            {
                if ( !visited.contains(i) )
                {
                    visited.add(i);
                    queue.addLast(i);
                    ancestor.addLast(new Node(i,w));
                }
            }
        }
        return ancestor;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        int vhead = this.ancestorOfGroup(this.graph,v);
        int whead = this.ancestorOfGroup(this.graph,w);
        return this.ancestor(vhead,whead);
    }

    //public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    //{

    //}

    //public int ancestorOfGroup(int[] v)
    //{
    //    return this.ancestorOfGroup(this.graph,v);
    //}
    private int ancestorOfGroup(Digraph dg, int[] v)
    {
        if ( v.length == 1 )
            return v[0];
        else if ( v.length == 2 )
            return this.ancestor(v[0],v[1]);
        else
        {
            int left = this.ancestorOfGroup(dg,Arrays.copyOfRange(v,0,v.length/2));
            int right = this.ancestorOfGroup(dg,Arrays.copyOfRange(v,v.length/2,v.length));
            return this.ancestor(left,right);
        }
    }

//    public static void main(String[] args)
//    {
//
//    }
}
