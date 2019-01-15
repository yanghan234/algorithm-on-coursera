public class Edge implements Comparable<Edge>
{
    private int beg;
    private int end;
    private double weight;

    public Edge(int beg, int end, double weight)
    {
        this.beg = beg;
        this.end = end;
        this.weight = weight;
    }

    public int either()
    {
        return beg;
    }

    public int other(int v)
    {
        if ( v == beg ) return end;
        else return beg;
    }

    public double weight()
    {
        return weight;
    }

    public String toString()
    {
        String s = new String();
        s = "[ "+this.beg+", "+this.end+", "+this.weight+" ]";
        return s;
    }

    public int compareTo(Edge that)
    {
        if ( this.weight < that.weight )
            return -1;
        else if ( this.weight > that.weight )
            return 1;
        else
            return 0;
    }

    public static void main(String[] args)
    {
        Edge e1 = new Edge(0,1,1.5);
        Edge e2 = new Edge(0,1,0.5);

        System.out.println(e1.compareTo(e2));
    }
}
