public class LSD
{
    public static void sort(String[] strings, int W)
    {
        // Works only for strings of the same width W.
        final int BASE = 256;
        final int N = strings.length;
        String[] aux = new String[N];

        for ( int iw = W-1; iw >= 0; iw-- )
        {
            int[] counts = new int[BASE+1];
            for ( int istr = 0; istr < N; istr++ )
                counts[strings[istr].charAt(iw)+1]++;

            for ( int i = 1; i < BASE; i++ )
                counts[i] = counts[i] + counts[i-1];

            for ( int i = 0; i < N; i++ )
                aux[counts[strings[i].charAt(iw)]++] = strings[i];

            for ( int i = 0; i < N; i++ )
                strings[i] = aux[i];
        }
    }

    public static void main(String[] args)
    {
        String[] strings = {"xyz","123","1xy","1,2"};

        LSD.sort(strings,3);

        for ( int i = 0; i < strings.length; i++ )
            System.out.println(strings[i]);

    }
}
