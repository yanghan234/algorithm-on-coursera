public class Test
{
    public static void main(String[] args)
    {
        Test.testBruteForce();
        Test.testKnuthMorrisPratt();
        Test.testBoyerMoore();
        Test.testRabinKarp();
    }
    public static void testBruteForce()
    {
        System.out.println("**********************************");
        System.out.println("Testing brute force substring");
        String txt = "abacadabrabracabracadabrabrabracad";
        String pat = "abracadabra";
        int ind = BruteForceSubString.search(txt, pat);
        System.out.println("First occurrence of the pattern: "+ind);

        int ind2 = BruteForceSubString.search2(txt, pat);
        System.out.println("First occurrence of the pattern: "+ind2);
        System.out.println("**********************************");
    }

    public static void testKnuthMorrisPratt()
    {
        System.out.println("**********************************");
        System.out.println("Testing Knuth-Morris-Pratt substring");
        String txt = "abacadabrabracabracadabrabrabracad";
        String pat = "abracadabra";
        int ind = KnuthMorrisPrattSubString.search(txt, pat);
        System.out.println("First occurrence of the pattern: "+ind);
        System.out.println("**********************************");
    }

    public static void testBoyerMoore()
    {
        System.out.println("**********************************");
        System.out.println("Testing Boyer-Moore substring");
        String txt = "abacadabrabracabracadabrabrabracad";
        String pat = "abracadabra";
        int ind = KnuthMorrisPrattSubString.search(txt, pat);
        System.out.println("First occurrence of the pattern: "+ind);
        System.out.println("**********************************");
    }

    public static void testRabinKarp()
    {
        System.out.println("**********************************");
        System.out.println("Testing Rabin-Karp substring");
        String txt = "abacadabrabracabracadabrabrabracad";
        String pat = "abracadabra";
        int ind = KnuthMorrisPrattSubString.search(txt, pat);
        System.out.println("First occurrence of the pattern: "+ind);
        System.out.println("**********************************");

    }
}
