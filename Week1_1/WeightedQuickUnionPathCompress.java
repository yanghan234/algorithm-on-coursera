/* We further optimize weighted quick union by applying path compression,
 * so that every node in the tree is directly connected to root
 * Union Complexity: almost O(1)
 * isConnect Complexity: almost O(1)
 */
public class WeightedQuickUnionPathCompress extends WeightedQuickUnion
{
    public WeightedQuickUnionPathCompress(int num)
    { super(num); }

    public int root( int p )
    {
        /* When we try to find the root of a tree,
         * we also check if nodes are directly connected to root.
         * If not, we connect the nodes to the root.
         */
        int rootp = p;
        while ( rootp != id[rootp] )
            rootp = id[rootp];

        while ( p != rootp )
        {
            int newp = id[p];
            id[p] = rootp;
            p = newp;
        }
        return rootp;
    }

    public static void main(String[] args)
    {
        WeightedQuickUnion wqupc;
        wqupc = new WeightedQuickUnion(10);

        wqupc.displayme();

        wqupc.union(0,2);
        wqupc.union(2,4);
        wqupc.union(6,8);
        wqupc.union(1,3);
        wqupc.union(3,5);
        wqupc.union(5,7);
        wqupc.union(7,9);
        wqupc.union(2,6);

        wqupc.displayme();

        System.out.println("1 and 2 ?: "+wqupc.isConnect(1,2));
        System.out.println("1 and 3 ?: "+wqupc.isConnect(1,3));
    }

}
