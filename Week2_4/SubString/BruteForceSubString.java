public class BruteForceSubString
{
    public static int search(String txt, String pat)
    {
        // Search pattern pat in text txt
        int N = txt.length();
        int M = pat.length();
        for ( int i = 0; i <= N-M; i++ )
        {
            int j = 0;
            for ( ; j < M; j++ )
                if ( txt.charAt(i+j) != pat.charAt(j) )
                    break;
            if ( j == M ) return i;
        }
        return -1;
    }

    public static int search2(String txt, String pat)
    {
        int i, N = txt.length();
        int j, M = pat.length();
        for ( i = 0, j = 0; i < N && j < M; i++ )
        {
            if ( txt.charAt(i) == pat.charAt(j) ) j++;
            else { i -= j; j = 0; }
        }

        if ( j == M ) return i - M;
        else return -1;
    }
}
