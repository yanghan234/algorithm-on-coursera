public class Test
{
    public static void main(String[] args)
    {
//        Test.testRWayTrie();
        Test.testTernaryTrie();
    }

    private static void testRWayTrie()
    {
        System.out.println("Test R-way Trie");
        RWayTrie<Integer> tr = new RWayTrie<>();
        tr.put("abc",1);
        tr.put("abd",2);

        System.out.println(tr.get("abc"));
        System.out.println(tr.get("abd"));
        System.out.println(tr.get("abe"));
        System.out.println(tr.contains("abc"));
        System.out.println(tr.contains("abd"));
        System.out.println(tr.contains("abe"));
        tr.delete("abc");
        System.out.println(tr.contains("abc"));
        System.out.println(tr.get("abd"));
        System.out.println(tr.contains("abd"));
    }

    private static void testTernaryTrie()
    {
        System.out.println("Test Ternary Trie");
        TernaryTrie<Integer> tr = new TernaryTrie<>();
        tr.put("abc",1);
        tr.put("abc",3);
        tr.put("abd",2);

        System.out.println(tr.get("abc"));
        System.out.println(tr.get("abd"));
        System.out.println(tr.get("abe"));
        System.out.println(tr.contains("abc"));
        System.out.println(tr.contains("abd"));
        System.out.println(tr.contains("abe"));
        tr.delete("abc");
        System.out.println(tr.contains("abc"));
        System.out.println(tr.get("abd"));
        System.out.println(tr.contains("abd"));
    }
}
