/*
 * This is a naive implementation, where we loop over the id array
 * and the the id of connected points to be the same.
 * So, to say if two points are connected or not, we just need to
 * compare their corresponding id's.
 * Union Complexity: O(N)
 * isConnect complexity: O(1)
 */
public class QuickFind
{
    public int N;
    public int[] id;

    // Constructor
    public QuickFind(int num)
    {
        N = num;
        id = new int[N];
        for ( int i = 0; i < N; i++ )
            id[i] = i;
    }

    public void union(int p, int q)
    {
        int idp = id[p];
        for ( int i=0; i < N; i++ )
            if ( id[i] == idp )
                id[i] = id[q];
    }

    public boolean isConnect( int p, int q )
    {
        return id[p] == id[q];
    }

    public void displayme( )
    {
        for ( int i = 0; i < N; i++ )
            System.out.println("index: "+i+", id="+id[i]);
            System.out.print('\n');
    }

    public static void main(String[] args)
    {
        QuickFind qf;
        qf = new QuickFind(10);
        qf.displayme();

        qf.union(3,4);
        qf.displayme();
        qf.union(3,9);
        qf.displayme();

        System.out.println("1 and 2 ?: "+qf.isConnect(1,2));
        System.out.println("3 and 4 ?: "+qf.isConnect(3,4));
        System.out.println("3 and 9 ?: "+qf.isConnect(3,9));
    }
}

