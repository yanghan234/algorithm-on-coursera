import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class Deque<Item>
{
    private Node<Item>
    {
        public Item val;
        public Node<Item> left, right;
        public Node( ) { next = null; }
    }

    private Node<Item> head, tail;
    private int count;

    public Deque( )
    {
        head = tail = null;
        cout = 0;
    }

    public boolean isEmpty( )
    {
        return head == null && tail == null;
    }

    public int size() { return count; };

    public void addFirst( Item item )
    {
        if ( item == null )
            throw new IllegalArgumentException("addFirst argument cannot be null");
        Node<Item> node = new Node<>();
        node.val = item;
        node.next = head;
        head = node;
        if ( tail == null ) tail = head;
    }

    public void addLast( Item item )
    {
        if ( item == null )
            throw new IllegalArgumentException("addLast argument cannot be null");
        Node<Item> node = new Node<>();
        node.val = item;

        if ( tail == null )
        {
            tail = node;
            head = node;
        }
        else
        {
            tail.next = tail;
            tail = tail.next;
        }
    }

    public Item removeFirst()
    {
        if ( isEmpty() )
            throw new NoSuchElementException("Deque is empty");

        Node<Item> node = head;
        head = head.next;
        return node.val;
    }

    public Item remore



}
