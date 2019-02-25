import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
public class BurrowsWheeler
{
    public static void transform()
    {
        String str = BinaryStdIn.readString();
        CircularSuffixArray suffix = new CircularSuffixArray(str);
        int N = str.length();
        for ( int i = 0; i < N; i++ )
            if ( suffix.index(i) == 0 )
            {
                BinaryStdOut.write(i);
                break;
            }

        for ( int i = 0; i < N; i++ )
            BinaryStdOut.write(str.charAt((suffix.index(i)-1+N)%N));

        BinaryStdOut.close();
    }

    public static void inverseTransform()
    {
        int first = BinaryStdIn.readInt();
        String str = BinaryStdIn.readString();

        int N = str.length();
        char[] t = str.toCharArray();
        char[] sorted = new char[N];
        int[] next = new int[N];

        final int R = 256;
        int[] counts = new int[R+1];
        for ( int i = 0; i < N; i++ )
            counts[t[i]+1]++;

        for ( int r = 0; r < R; r++ )
            counts[r+1] += counts[r];

        for ( int i = 0; i < N; i++ )
        {
            sorted[counts[t[i]]] = t[i];
            next[counts[t[i]]++] = i;
        }

        int n = 0;
        while ( n < N )
        {
            BinaryStdOut.write(sorted[first]);
            first = next[first];
            n++;
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args)
    {
        if ( args[0].equals("-") )
            BurrowsWheeler.transform();
        else if ( args[0].equals("+") )
            BurrowsWheeler.inverseTransform();
    }
}
