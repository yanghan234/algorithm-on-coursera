public class TernaryTrie<Value>
{
    private static final int R = 256;
    private Node root;
    private int count;

    private static class Node
    {
        public Object val;
        public int me;
        public Node next;
        public Node left;
        public Node right;
        public Node()
        {
            val = null;
            me = -1;
            next = null;
            left = null;
            right = null;
        }
    }

    public TernaryTrie()
    {
        root = null;
        count = 0;
    }

    public void put(String key, Value val)
    {
        root = put(root, key, val, 0);
        count++;
    }

    private Node put(Node where, String key, Value val, int depth)
    {
        int c = key.charAt(depth);
        if ( where == null ) { where = new Node(); where.me = c; }
        if ( where.me == -1 ) where.me = c;
        if ( where.me == c && depth < key.length()-1 ) where.next = put(where.next, key, val, depth+1);
        else if ( where.me > c ) where.left = put(where.left, key, val, depth);
        else if ( where.me < c ) where.right = put(where.right, key, val, depth);
        else where.val = val;
        return where;
    }

    public boolean isEmpty() { return count == 0; }

    public Value get(String key)
    {
        return (Value) get(root, key, 0);
    }

    private Object get(Node where, String key, int depth)
    {
        int c = key.charAt(depth);
        if ( where == null ) return null;
        if ( where.me == c && depth < key.length()-1 ) return get(where.next, key, depth+1);
        else if ( where.me > c ) return get(where.left, key, depth);
        else if ( where.me < c ) return get(where.right, key, depth);
        else return where.val;
    }

    public boolean contains(String key)
    {
        return get(root, key, 0) != null;
    }

    public void delete(String key)
    {
        root = delete(root, key, 0);
    }

    public Node delete(Node where, String key, int depth)
    {
        int c = key.charAt(depth);
        if ( where == null ) return null;
        if ( where.me == c && depth < key.length()-1 ) where.next = delete(where.next, key, depth+1);
        else if ( where.me > c ) where.left = delete(where.left, key, depth);
        else if ( where.me < c ) where.right = delete(where.right, key, depth);
        else where.val = null;

        if ( where.val != null || where.next != null || where.left != null || where.right != null ) return where;
        return null;
    }
}
