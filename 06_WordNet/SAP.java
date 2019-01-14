import edu.princeton.cs.algs4.Digraph;
import java.lang.IllegalArgumentException;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.TreeMap;
public final class SAP
{
    private final Digraph graph;
    private TreeMap<Integer,Node> vAncestor;
    private TreeMap<Integer,Node> wAncestor;
    public SAP(Digraph G)
    {
        if ( G == null )
            throw new IllegalArgumentException("Argument to SAP constructor cannot be null!!");
        this.graph = new Digraph(G);
    }

    private class Node
    {
        public int me;
        public int prev;
        public int layer;
        public Node(int m, int p, int l)
        {
            this.me = m;
            this.prev = p;
            this.layer = l;
        }
        public String toString()
        {
            return "( me: "+this.me+", prev: "+this.prev+", "+this.layer+" )\n";
        }
    }

    public int length(int v, int w)
    {
        int common = this.ancestor(v,w);
        if ( common == -1 )
            return -1;
        Node nv = vAncestor.get(common);
        Node nw = wAncestor.get(common);
        return nv.layer+nw.layer;
    }

    public int ancestor(int v, int w)
    {
        vAncestor = new TreeMap<Integer,Node>();
        wAncestor = new TreeMap<Integer,Node>();
        vAncestor = this.bfs(graph, v);
        wAncestor = this.bfs(graph, w);

        int totalLayer = -1;
        int common = -1;
        for ( int i: vAncestor.keySet() )
            if ( wAncestor.containsKey(i) )
            {
                if ( totalLayer < 0 )
                {
                    common = i;
                    totalLayer = vAncestor.get(i).layer+wAncestor.get(i).layer;
                }
                else if ( totalLayer > vAncestor.get(i).layer+wAncestor.get(i).layer )
                {
                    common = i;
                    totalLayer = vAncestor.get(i).layer+wAncestor.get(i).layer;
                }
            }

        return common;
    }

    private TreeMap<Integer,Node> bfs(Digraph dg, int v)
    {
        HashSet<Integer> visited = new HashSet<Integer>();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        TreeMap<Integer,Node> ancestor = new TreeMap<Integer,Node>();

        visited.add(v);
        queue.addLast(v);
        ancestor.put(v,new Node(v,v,0));
        while ( !queue.isEmpty() )
        {
            int w = queue.pollFirst();
            for ( int i: dg.adj(w) )
            {
                if ( !visited.contains(i) )
                {
                    visited.add(i);
                    queue.addLast(i);
                    Node nw = ancestor.get(w);
                    ancestor.put(i,new Node(i,w,nw.layer+1));
                }
            }
        }
        return ancestor;
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        if ( v == null || w == null )
            throw new IllegalArgumentException();
        int shortDistance = -1;
        int shortCommon = -1;
        for ( Object vv: v )
        {
            if ( vv == null )
                throw new IllegalArgumentException();
            for ( Object ww: w )
            {
                if ( ww == null )
                    throw new IllegalArgumentException();
                int distance = this.length((int) vv,(int) ww);
                if ( shortDistance < 0 )
                {
                    shortDistance = distance;
                    shortCommon = this.ancestor((int) vv,(int) ww);
                }
                else if ( shortDistance > distance )
                {
                    shortDistance = distance;
                    shortCommon = this.ancestor((int) vv,(int) ww);
                }
            }
        }
        return shortCommon;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        if ( v == null || w == null )
            throw new IllegalArgumentException();
        int shortDistance = -1;
        for ( Object vv: v )
        {
            if ( vv == null )
                throw new IllegalArgumentException();
            for ( Object ww: w )
            {
                if ( ww == null )
                    throw new IllegalArgumentException();
                int distance = this.length((int) vv,(int) ww);
                if ( shortDistance < 0 )
                    shortDistance = distance;
                else if ( shortDistance > distance )
                    shortDistance = distance;
            }
        }
        return shortDistance;
    }

    //private int ancestorOfGroup(int[] v)
    //{
    //    if ( v.length == 1 )
    //        return v[0];
    //    else if ( v.length == 2 )
    //        return this.ancestor(v[0],v[1]);
    //    else
    //    {
    //        int left = this.ancestorOfGroup(Arrays.copyOfRange(v,0,v.length/2));
    //        int right = this.ancestorOfGroup(Arrays.copyOfRange(v,v.length/2,v.length));
    //        return this.ancestor(left,right);
    //    }
    //}
}
