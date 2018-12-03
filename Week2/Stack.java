public class Stack<Item>
{
    int count;
    Node<Item> head;
    Stack() { head = null; count = 0; };
    void push( Item elem )
    {
        Node<Item> n = new Node<Item>();
        n.val = elem;
        n.next = null;
        if ( head == null )
            head = n;
        else
        {
            n.next = head;
            head = n;
        }
        count++;
    }

    Item pop( )
    {
        if ( head == null )

    }


    private static class Node <Item>
    {
        Item val;
        Node next;
    }

    public static void main(String[] args)
    {
        Stack<Integer> st = new Stack<Integer>();
        Node<Integer> n = new Node<Integer>(1);
        st.head = n;
    }

}

