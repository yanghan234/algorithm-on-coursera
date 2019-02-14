/**
 *  Implement Boyer-Moore algorithm
 */
import java.lang.Math;
public class BoyerMooreSubString
{
    public static int search(String txt, String pat)
    {
        int skip = 0;
        int M = txt.length();
        int N = pat.length();
        int[] rightMost = compRightMost(pat);
        for ( int i = 0; i <= M-N; i += skip )
        {
            skip = 0;
            for ( int j = N-1; j >= 0; j-- )
            {
                if ( txt.charAt(i+j) != pat.charAt(j) )
                {
                    skip = Math.max(1,j-rightMost[txt.charAt(i+j)]);
                    break;
                }
            }
            if ( skip == 0 ) return i;
        }
        return -1;
    }

    private static int[] compRightMost(String pat)
    {
        // Compute the right most position of R characters in the pattern string,
        // if it is not in the pattern,
        // the value will be set as -1.
        final int R = 256;
        int[] rightMost = new int[R];
        for ( int c = 0; c < R; c++ )
            rightMost[c] = -1;
        for ( int c = 0; c < pat.length(); c++ )
            rightMost[pat.charAt(c)] = c;
        return rightMost;
    }
}
