public class SeparateChainingHashST<Key,Val>
{
    private static final int CAPACITY = 101;
    private SequentialSearchST[] st;
    public SeparateChainingHashST()
    {
        st = (SequentialSearchST[]) new Object[CAPACITY];
    }

    private class Node
    {
        public Key key;
        public Val val;
        public Node next;
        public Node(Key k, Val v)
        {
            key = k;
            val = v;
            next = null;
        }
    }

    private class SequentialSearchST
    {
        public Node root;
        public int count;
        public SequentialSearchST()
        {
            root = null;
            count = 0;
        }
        public SequentialSearchST(Key key,Val val)
        {
            root = new Node(key,val);
            count = 1;
        }
    }

    public void put(Key key,Val val)
    {
        int hash = hash(key);
        SequentialSearchST current;
    }

    public void displayme()
    {
        for ( int i = 0; i < CAPACITY; i++ )
        {
            if ( st[i].root == null )
                continue;
            Node current = st[i].root;
            while ( current.next != null )
                System.out.printf("( %s, %d ) -> ",current.key,current.val);
            System.out.printf("( %s, %d )\n",current.key,current.val);
        }
    }

    private int hash(Key key)
    {
        assert key.getClass() == String;
        int tmp = 0;
        for ( int i = 0; i < key.length(); i++ )
            tmp += key.charAt(i)-'0';
        return tmp%CAPACITY;
    }

    public static void main(String[] args)
    {
        SeparateChainingHashST<String,Integer> hash = new SeparateChainingHashST<String,Integer>();
        hash.put("a",1);
        hash.displayme();
    }
}
