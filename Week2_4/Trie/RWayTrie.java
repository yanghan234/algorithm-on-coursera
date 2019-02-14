import java.lang.IllegalArgumentException;
public class RWayTrie<Value>
{
    private static final int R = 256;
    private Node root;
    private int count;

    private static class Node
    {
        public Object val;
        public Node[] next = new Node[R];
    }

    public RWayTrie()
    {
        root = null;
        count = 0;
    }

    public void put(String key, Value val)
    {
        if ( key == null ) throw new IllegalArgumentException();
        root = put(root, key, val, 0);
        count++;
    }

    private Node put(Node where, String key, Value val, int depth)
    {
        if ( where == null ) where = new Node();
        if ( depth < key.length() ) where.next[key.charAt(depth)] = put(where.next[key.charAt(depth)],key,val,depth+1);
        else where.val = val;
        return where;
    }

    public Value get(String key)
    {
        if ( key == null ) throw new IllegalArgumentException();
        return (Value) get(root,key,0);
    }

    public Object get(Node where, String key, int depth)
    {
        if ( where == null ) return null;
        if ( depth < key.length() ) return get(where.next[key.charAt(depth)],key,depth+1);
        else return where.val;
    }

    public boolean contains(String key)
    {
        return get(key) != null;
    }

    public boolean isEmpty() { return count == 0; }

    public void delete(String key)
    {
        if ( key == null ) throw new IllegalArgumentException();
        root = delete(root,key,0);
    }

    private Node delete(Node where, String key, int depth)
    {
        if ( depth < key.length() )
            where.next[key.charAt(depth)] = delete(where.next[key.charAt(depth)],key,depth+1);
        else
        {
            if ( where.val != null ) count--;
            where.val = null;
        }
        if ( where.val != null ) return where;
        for ( int c = 0; c < R; c++ )
            if ( where.next[c] != null )
                return where;
        return null;
    }
}
