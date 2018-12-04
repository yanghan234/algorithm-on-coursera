import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack_LinkedList<Item> implements Iterable<Item>
{
    private class Node<Item>
    {
        private Item val;
        private Node next;
        private Node ( ) { next = null; };
        private Node ( Item item ) { val = item; next = null; };
    }

    private class ListIterator<Item> implements Iterator<Item>
    {
        private Node<Item> current;
        public ListIterator( Node<Item> node ) { current = node; };

        public boolean hasNext( ) { return current != null; };

        public Item next()
        {
            if ( !hasNext() ) throw new NoSuchElementException();
            Node<Item> n = current;
            current = current.next;
            return n.val;
        }
    }

    Node<Item> head;
    int count;
    public Stack_LinkedList ()
    {
        head = null;
        count = 0;
    }

    public void push( Item elem )
    {
        Node<Item> newhead = new Node<Item>( elem );
        newhead.next = head;
        head = newhead;
        count++;
    }

    public Item pop()
    {
        if ( isEmpty() )
            throw new NoSuchElementException("Stack_LinkedList underflow");
        Node<Item> n = head;
        head = head.next;
        count--;
        return n.val;
    }

    public boolean isEmpty()
    {
        return head == null;
    }

    public Iterator<Item> iterator()
    {
        return new ListIterator<Item>( head );
    }

    public static void main(String[] args)
    {
        Stack_LinkedList<Integer> s = new Stack_LinkedList<>();
        s.push(1);
        s.push(2);
        s.push(4);
        s.push(6);

        for ( int i : s )
            System.out.println(i);
    }
}
