public class CircularSuffixArray
{
    private final int R = 256;
    private final int N;
    private int[] indices;
    private final String original;
    public CircularSuffixArray(String s)
    {
        if ( s == null ) throw new IllegalArgumentException();
        this.N = s.length();
        this.indices = new int[N];
        this.original = s;

        for ( int i = 0; i < N; i++ )
            indices[i] = i;

        this.MSD(0,N-1,0);
    }

    private void MSD(int lo, int hi, int d)
    {
        if ( lo >= hi || d == N ) return;
        int[] counts = new int[R+2];
        int[] aux = new int[hi-lo+1];
        for ( int i = lo; i <= hi; i++ )
            counts[original.charAt((indices[i]+d)%N)+2]++;

        for ( int r = 0; r < R+1; r++ )
            counts[r+1] += counts[r];

        for ( int i = lo; i <= hi; i++ )
            aux[counts[original.charAt((indices[i]+d)%N)+1]++] = indices[i];

        for ( int i = lo; i <= hi; i++ )
            indices[i] = aux[i-lo];

        for ( int r = 0; r < R; r++ )
            if ( counts[r+1]-1 >= counts[r] )
                MSD(lo+counts[r],lo+counts[r+1]-1,d+1);
    }

    public int length() { return N; }

    public int index(int i)
    {
        if ( i < 0 || i >= N )
            throw new IllegalArgumentException();
        return indices[i];
    }

    public static void main(String[] args)
    {
        String s = new String("CADABRA!ABRA");
        CircularSuffixArray suffix = new CircularSuffixArray(s);
        for ( int i = 0; i < s.length(); i++ )
            System.out.println(suffix.index(i));

        System.out.println(suffix.length());
    }
}
