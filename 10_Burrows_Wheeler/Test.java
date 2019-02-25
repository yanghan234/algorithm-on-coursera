public class Test
{
    public static void testMoveToFront()
    {
        MoveToFront.decode();
    }

    public static void testCircularSuffixArray()
    {
        String s = new String("ABRACADABRA!");
        CircularSuffixArray suffix = new CircularSuffixArray(s);
    }

    public static void main(String[] args)
    {
        Test.testCircularSuffixArray();
    }
}
