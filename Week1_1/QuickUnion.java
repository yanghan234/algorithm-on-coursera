/* This codes optimizes QuickFind.java and this is called
 * quick-union method.
 * In this method, we develop a tree structure for connected points.
 * So, to say if two points are connected or not, we need compare
 * if they have the same roots.
 */
public class QuickUnion extends QuickFind
{
    public QuickUnion( int num )
    {
        super(num);
    }

    public int root( int p )
    {
        if ( p == id[p] )
            return p;
        while ( p != id[p] )
            p = id[p];
        return p;
    }

    public void union( int p, int q )
    {
        p = root(p);
        id[p] = q;
    }

    public boolean isConnect( int p, int q )
    {
        p = root(p);
        q = root(q);
        return p == q;
    }

    public static void main(String[] args)
    {
        QuickUnion qu;
        qu = new QuickUnion(10);

        qu.displayme();

        qu.union(0,2);
        qu.union(2,4);
        qu.union(4,6);
        qu.union(6,8);
        qu.union(1,3);
        qu.union(3,5);
        qu.union(5,7);
        qu.union(7,9);
        qu.displayme();

        System.out.println("1 and 2 ?: "+qu.isConnect(1,2));
        System.out.println("1 and 3 ?: "+qu.isConnect(1,3));

    }

}
