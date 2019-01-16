import java.util.ArrayList;
public abstract class SP
{
    protected int src;
    protected double[] distTo;
    protected DirectedEdge[] edgeTo;
    protected ArrayList<DirectedEdge> SPedges;
    protected EdgeWeightedDigraph graph;
    public abstract double distanceTo(int v);
    public abstract boolean hasPathTo(int v);
    public abstract Iterable<DirectedEdge> pathTo(int v);
}
