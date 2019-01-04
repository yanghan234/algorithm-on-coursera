/* This code optimized QuickUnion.java by applying weighted union.
 * When we unify two trees, we always merge the smaller one two the large one.
 * Union Complexity: O(logN)
 * isConnect Complexity: O(logN)
 */
public class WeightedQuickUnion extends QuickUnion
{
    int[] weights;
    public WeightedQuickUnion(int num)
    {
        super(num);
        weights = new int[N];
        for ( int i = 0; i < N; i++ )
            weights[i] = 1;
    }

    public void union( int p, int q)
    {
        p = root(p);
        q = root(q);

        if ( weights[p] > weights[q] ) {
            id[q] = p;
            weights[p] += weights[q];
        } else {
            id[p] = q;
            weights[q] += weights[p];
        }
    }

    public void displayme( )
    {
        for ( int i = 0; i < N; i++ )
            System.out.println("index: "+i+", id="+id[i]+", weights: "+weights[i]);
            System.out.print('\n');
    }

    public static void main(String[] args)
    {
        WeightedQuickUnion wqu;
        wqu = new WeightedQuickUnion(10);

        wqu.displayme();

        wqu.union(0,2);
        wqu.union(2,4);
        wqu.union(6,8);
        wqu.union(1,3);
        wqu.union(3,5);
        wqu.union(5,7);
        wqu.union(7,9);
        wqu.union(2,6);

        wqu.displayme();

        System.out.println("1 and 2 ?: "+wqu.isConnect(1,2));
        System.out.println("1 and 3 ?: "+wqu.isConnect(1,3));

    }
}
