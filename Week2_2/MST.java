public abstract class MST
{
    protected EdgeWeightedGraph graph;
    protected double totalWeight;
    public MST(EdgeWeightedGraph g)
    {
        this.graph = g;
    }

    public abstract Iterable<Edge> edges(); // iterate through MST
    public double weight(){ return totalWeight; }
}
