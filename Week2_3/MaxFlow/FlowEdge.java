public class FlowEdge
{
    private final int from, to;
    private final double capacity;
    private double flow;

    public FlowEdge(int from, int to, double capacity)
    {
        this.from     = from;
        this.to       = to;
        this.capacity = capacity;
        this.flow     = 0;
    }

    public double capacity() { return this.capacity; }
    public double flow() { return this.flow; }
    public int from() { return this.from; }
    public int to() { return this.to; }

    public int other(int v)
    {
        if ( this.from == v )
            return this.from;
        else
            return this.to;
    }

    public double residualCapacityTo(int v)
    {
        // how much capacity left
        // depending on forward or backward
        if ( this.to == v )
            return this.capacity - this.flow;
        else
            return this.flow;
    }

    public void addResidualFlowTo(int v, double deltaFlow)
    {
        if ( this.to == v )
            this.flow += deltaFlow;
        else
            this.flow -= deltaFlow;
        assert this.flow >= 0 && this.flow <= this.capacity;
    }

}
