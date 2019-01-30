public class MSD
{
    public static void sort(String[] strings)
    {
        String[] aux = new String[strings.length];
        sort(strings,aux,0,strings.length-1,0);
    }

    private static void sort(String[] strings, String[] aux, int lo, int hi, int d)
    {
        if ( lo >= hi ) return;
        final int R = 256;
        int[] counts = new int[R+2];
        for ( int i = lo; i <= hi; i++ )
            counts[charAt(strings[i],d)+2]++;

        for ( int r = 0; r < R+1; r++ )
            counts[r+1] += counts[r];

        for ( int i = lo; i <= hi; i++ )
            aux[counts[charAt(strings[i],d)+1]++] = strings[i];

        for ( int i = lo; i <= hi; i++ )
            strings[i] = aux[i-lo];

        for ( int r = 0; r < R; r++ )
            sort(strings,aux,lo+counts[r],lo+counts[r+1]-1,d+1);
    }

    private static int charAt(String str, int index)
    {
        if ( index < str.length() ) return str.charAt(index);
        else return -1;
    }

    public static void main(String[] args)
    {
        String[] strings = {"xyz","123","1xy","1,2"};

        MSD.sort(strings);

        for ( int i = 0; i < strings.length; i++ )
            System.out.println(strings[i]);
    }
}
