import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;

public class Permutation
{
    public static void main( String[] args )
    {
        int n = new Integer(args[0]).intValue();
        RandomizedQueue<String> r = new RandomizedQueue<>();

        int i = 0;
        while ( !StdIn.isEmpty() )
        {
            String item = StdIn.readString();
            r.enqueue(item);
            i++;
            if ( i >= n ) break;
        }

        if ( n > r.size() ) throw new NoSuchElementException("Index out of range");

        i = 0;
        for ( String s : r )
        {
            if ( i < n )
            {
                System.out.println(s);
                i++;
            }
            else
            {
                break;
            }
        }

    }

}
