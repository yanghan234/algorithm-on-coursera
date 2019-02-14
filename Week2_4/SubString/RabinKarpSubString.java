/**
 *  Implement Rabin-Karp algorithm
 *  It is possible that two distinct strings have the same hash code.
 */
public class RabinKarpSubString
{
    public static int search(String txt, String pat)
    {
        int M = txt.length();
        int N = txt.length();
        int patHash = hashCode(pat);
        if ( M < N ) return -1;
        for ( int i = 0; i <= M-N; i++ )
            if ( patHash == hashCode(txt,i,N) )
                return i;
        return -1;
    }

    private static int hashCode(String s)
    {
        return hashCode(s,0,s.length());
    }

    private static int hashCode(String s, int ind, int M)
    {
        final int Q = 1987; // large prime numbers
        final int R = 256;  // Radix
        int hash = 0;
        assert M <= s.length();
        for ( int i = ind; i < M+ind; i++ )
        {
            hash = hash * R + s.charAt(i);
            hash /= Q;
        }
        return hash;
    }
}
