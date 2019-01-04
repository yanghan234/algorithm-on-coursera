import java.lang.IllegalArgumentException;
public class SeparateChainingHashST<Key,Val>
{
    private static final int CAPACITY = 101;
    private SequentialSearchST[] st;
    private int count;
    public SeparateChainingHashST()
    {
        st = (SequentialSearchST[]) new SequentialSearchST[CAPACITY];
        for ( int i = 0; i < CAPACITY; i++ )
            st[i] = new SequentialSearchST();
    }

    private class Node<Key,Val>
    {
        public Key key;
        public Val val;
        public Node<Key,Val> next;
        public Node(Key k, Val v)
        {
            key = k;
            val = v;
            next = null;
        }
    }

    private class SequentialSearchST<Key,Val>
    {
        public Node<Key,Val> root;
        public int count;
        public SequentialSearchST()
        {
            root = null;
            count = 0;
        }
    }

    public void put(Key key,Val val)
    {
        if ( key == null )
            throw new IllegalArgumentException("First argument of put() method cannot be null!!");
        int hash = hash(key);
        if ( st[hash].root == null )
        {
            st[hash].root = new Node<Key,Val>(key,val);
            count++;
        }
        else
        {
            Node<Key,Val> current = st[hash].root;
            while ( current != null )
            {
                if ( key.equals( current.key ) == true )
                {
                    current.val = val;
                    count++;
                    return;
                }
                current = current.next;
            }
            current = new Node<Key,Val>(key,val);
            current.next = st[hash].root;
            st[hash].root = current;
            count++;
        }
    }

    public Val get(Key key)
    {
        if ( key == null )
            throw new IllegalArgumentException("Argument of get() method cannot be null!!");
        int hash = hash(key);
        if ( st[hash].root == null )
            return null;
        else
        {
            Node current = st[hash].root;
            while ( current != null )
            {
                if ( key.equals(current.key) == true )
                    return ( (Val) current.val );
                current = current.next;
            }
            return null;
        }
    }

    public boolean contains(Key key)
    {
        return get(key) != null;
    }

    public void delete(Key key)
    {
        if ( key == null )
            throw new IllegalArgumentException("Argument of delete() method cannot be null!!");
        int hash = hash(key);
        st[hash].root = deleteFrom(st[hash].root,key);
    }

    private Node<Key,Val> deleteFrom(Node<Key,Val> where, Key key)
    {
        if ( where == null )
            where = null;
        else if ( key.equals(where.key) == true )
            where = where.next;
        else
            where.next = deleteFrom(where.next,key);
        return where;
    }

    public void displayme()
    {
        for ( int i = 0; i < CAPACITY; i++ )
        {
            if ( st[i].root == null )
                continue;
            Node current = st[i].root;
            while ( current.next != null )
            {
                System.out.printf("( %s, %d ) -> ",current.key,current.val);
                current = current.next;
            }
            System.out.printf("( %s, %d )\n",current.key,current.val);
        }
    }

    private int hash(Key key)
    {
        if ( key == null )
            throw new IllegalArgumentException("Argument of hash() method cannot be null!!");
        return ( key.hashCode() & 0x7fffffff ) % CAPACITY;
    }

    public static void main(String[] args)
    {
        SeparateChainingHashST<String,Integer> hash = new SeparateChainingHashST<String,Integer>();
        hash.put("a",1);
        hash.put("b",2);
        hash.put("c",3);
        hash.put("d",4);
        hash.put("e",5);
        hash.put("aa",1);
        hash.put("bb",2);
        hash.put("cc",3);
        hash.put("dd",4);
        hash.put("ee",5);
        hash.displayme();

        hash.put("e",6);
        hash.displayme();

        System.out.println(hash.get("ee"));
        System.out.println(hash.contains("ee"));
        System.out.println(hash.get("eee"));
        System.out.println(hash.contains("eee"));

        hash.delete("ee");
        hash.displayme();
    }
}
