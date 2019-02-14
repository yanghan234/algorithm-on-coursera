/**
 * Implement Knuth-Morris-Pratt algorithm
 */
public class KnuthMorrisPrattSubString
{
    private static final int R = 256;
    public static int search(String txt, String pat)
    {
        int[][] dfa = compDFA(pat);

        final int M = txt.length();
        final int N = pat.length();

        int i = 0, j = 0;
        for ( i = 0, j = 0; i < M && j < N; i++ )
            j = dfa[txt.charAt(i)][j];

        if ( j == N ) return i-N;
        else return -1;
    }

    private static int[][] compDFA(String pat)
    {
        int[][] dfa = new int[R][pat.length()];
        dfa[pat.charAt(0)][0] = 1;
        for ( int x = 0, j = 1; j < pat.length(); j++ )
        {
            for ( int c = 0; c < R; c++ )
                dfa[c][j] = dfa[c][x];
            dfa[pat.charAt(j)][j] = j+1;
            x = dfa[pat.charAt(j)][x];
        }
        return dfa;
    }
}

