public class ThreeWay
{
    public static void sort(String[] strings)
    {
        sort(strings,0,strings.length-1,0);
    }

    private static void sort(String[] strings, int lo, int hi, int d)
    {
        if ( lo >= hi ) return;
        int lptr = lo, hptr = hi;
        int ref = charAt(strings[lo],d);
        int ptr = lo;
        while ( ptr <= hi )
        {
            int v = charAt(strings[ptr],d);
            if ( v > ref )
                exch(strings,hptr--,ptr);
            else if ( v < ref )
                exch(strings,lptr++,ptr);
            else
                ptr++;
        }
        sort(strings,lo,lptr-1,d);
        if ( ref >= 0 ) sort(strings,lptr,hptr,d+1);
        sort(strings,hptr+1,hi,d);
    }

    private static void exch(String[] str, int i, int j)
    {
        String tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    private static int charAt(String str, int index)
    {
        if ( index < str.length() ) return str.charAt(index);
        else return -1;
    }

    public static void main(String[] args)
    {

        String[] strings = {"xyz","123","1xy","1,2"};

        ThreeWay.sort(strings);

        for ( int i = 0; i < strings.length; i++ )
            System.out.println(strings[i]);
    }
}
