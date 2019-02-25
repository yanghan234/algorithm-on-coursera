import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
public class MoveToFront
{
    private static final int R = 256;
    private static int[] seq = new int[R];
    public static void encode()
    {
        for ( int r = 0; r < R; r++ )
            seq[r] = r;
        while ( !BinaryStdIn.isEmpty() )
        {
            char c = BinaryStdIn.readChar();
            int r = 0;
            for ( r = 0; r < R; r++ )
                if ( seq[r] == c )
                    break;
            BinaryStdOut.write((byte) r);
            if ( r != 0 )
            {
                for ( int i = r; i >= 1; i-- )
                    seq[i] = seq[i-1];
                seq[0] = c;
            }
        }
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }

    public static void decode()
    {
        for ( int r = 0; r < R; r++ )
            seq[r] = r;
        while ( !BinaryStdIn.isEmpty() )
        {
            int c = BinaryStdIn.readInt(8);
            if ( c == 0 )
                BinaryStdOut.write((char) seq[0]);
            else
            {
                int tmp = seq[c];
                BinaryStdOut.write((char) tmp);
                for ( int i = c; i >= 1; i-- )
                    seq[i] = seq[i-1];
                seq[0] = tmp;
            }
        }
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }

    public static void main(String[] args)
    {
        if ( args[0].equals("-") )
            encode();
        else if ( args[0].equals("+") )
            decode();
    }
}
