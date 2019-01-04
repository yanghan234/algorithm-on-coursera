/* ******************************************************************
 * This implementation of symbol table uses an unordered linked list.
 * Complexity:
 *      Search: Worst: N, Average N/2
 *      Insert: Worst: N, Average N
 *      Ordered iterator: No
 *      Interface: equals()
 * ******************************************************************
 */
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
public class UnorderedLinkedListSymbolTable<Key,Val>
{
    public Node<Key,Val> head;
    public int count;
    UnorderedLinkedListSymbolTable()
    {
        head = null;
    }

    public UnorderedLinkedListSymbolTable(Key k, Val v)
    {
        head = new Node<Key,Val>(k,v);
    }

    public int size()
    {
        return count;
    }

    public boolean empty()
    {
        return count == 0;
    }

    public void put(Key key, Val val)
    {
        Node newNode = new Node<Key,Val>(key,val);
        if ( head == null )
            head = newNode;
        else
        {
            Node current = head;
            while ( current != null )
            {
                if ( current.equals( newNode ) )
                {
                    current.val = newNode.val;
                    break;
                }
                current = current.next;
            }
            if ( current == null )
            {
                newNode.next = head;
                head = newNode;
                count++;
            }
        }
    }

    public Val get( Key key )
    {
        if ( key == null )
            throw new IllegalArgumentException("Argument of get() method cannot be null!!");

        Node<Key,Val> current = head;
        while ( current != null )
        {
            if ( key.equals(current.key) )
                return current.val;
            current = current.next;
        }

        return null;
    }

    public void delete(Key key)
    {
        if ( key == null )
            throw new IllegalArgumentException("Argument of delete() method cannot be null!!");
        head = delete(head, key);
    }

    public Node delete(Node x, Key key)
    {
        if ( x == null ) return null;
        if ( key.equals(x.key) )
        {
            count--;
            return x.next;
        }
        x.next = delete(x.next,key);
        return x;
    }

    public void displayme()
    {
        if ( head == null )
            System.out.println("Empty list!!");
        else
        {
            Node current = head;
            while ( current != null )
            {
                System.out.printf("( %s, %d )\n",current.key,current.val);
                current = current.next;
            }
        }
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
        public boolean equals( Object y )
        {
            if ( y == null )
                return false;
            if ( this.getClass() != y.getClass() )
                return false;

            Node that = (Node) y;

            return this.key == that.key;
        }
    }

    public Iterable<Key> keys()
    {
        ArrayList<Key> arr = new ArrayList<>();
        Node current = head;
        while ( current != null )
        {
            arr.add((Key) current.key);
            current = current.next;
        }
        return arr;
    }

    public static void main(String[] args)
    {
        UnorderedLinkedListSymbolTable<String,Integer> st;
        st = new UnorderedLinkedListSymbolTable<String,Integer>("abc",3);
        st.put("xyz",4);
        st.displayme();

        st.put("abc",5);
        st.displayme();

        //System.out.println(st.get("xyz"));

//        st.delete("xyz");
//        st.displayme();

        for ( String k : st.keys() )
            System.out.println(st.get(k));

    }
}
