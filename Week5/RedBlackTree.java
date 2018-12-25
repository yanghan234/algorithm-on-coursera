public class RedBlackTree<Key extends Comparable<Key>, Val>
{
    private static final int BLACK = 0;
    private static final int RED = 1;
    public Node root;
    public int count;

    public RedBlackTree()
    {
        root = null;
        count = 0;
    };
    public RedBlackTree( Key key, Val val )
    {
        root = new Node(key,val,BLACK);
        count++;
    };

    private class Node
    {
        public Key key;
        public Val val;
        public int color; // 0: balck, 1: red
        public Node left;
        public Node right;
        public Node(Key k, Val v, int c)
        {
            key = k;
            val = v;
            color = c;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args)
    {
        RedBlackTree<String,Integer> rbt = new RedBlackTree<String,Integer>();
    }
}
