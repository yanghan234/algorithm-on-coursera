public abstract class SP
{
    protected EdgeWeightedDigraph graph;
    public abstract double distanceTo(int v);
    public abstract boolean hasPathTo(int v);
    public abstract Iterable<DirectedEdge> pathTo(int v);
}
