import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
public class BinarySearchTree<Key extends Comparable<Key>,Val>
{
    public Node root;
    private int count = 0;

    public BinarySearchTree(){ root = null; count = 0; }
    public BinarySearchTree(Key k, Val v)
    {
        root = new Node(k,v);
        count++;
    }

    public int size()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    // If a key is already in the tree, we update the value associated to the key.
    // i.e. the tree does not support duplicate keys.
    public void put(Key key, Val val)
    {
        root = putTo(root, key, val);
        count++;
    }

    private Node putTo(Node where, Key key, Val val)
    {
        if ( where == null )
            return new Node(key,val);
        if ( key.compareTo(where.key) > 0 )
            where.right = putTo(where.right,key,val);
        else if ( key.compareTo(where.key) < 0 )
            where.left = putTo(where.left,key,val);
        else if ( key.compareTo(where.key) == 0 )
            where.val = val;
        return where;
    }

    public Val get(Key key)
    {
        if ( key == null )
            throw new IllegalArgumentException("Argument of get() method cannot be null!!");
        if ( root == null )
            throw new NoSuchElementException("Cannot get from empty tree!!");
        return getFrom(root, key);
    }

    private Val getFrom(Node where, Key key)
    {
        if ( where == null )
            return null;
        if ( key.compareTo(where.key) < 0 )
            return getFrom(where.left, key);
        else if ( key.compareTo(where.key) > 0 )
            return getFrom(where.right, key);
        else
            return (where.val);
    }

    public boolean contains(Key key)
    {
        return get(key) != null;
    }

    public void delete(Key key)
    {
        root = deleteFrom(root,key);
        count--;
    }

    private Node deleteFrom(Node where, Key key)
    {
        if ( where == null )
            return null;
        if ( key.compareTo(where.key) < 0 )
            where.left = deleteFrom(where.left,key);
        else if ( key.compareTo(where.key) > 0 )
            where.right = deleteFrom(where.right,key);
        else
        {
            Node rightSmallest = smallestNode(where.right);
            if ( rightSmallest == null )
                where = where.left;
            else
            {
                swap( where, rightSmallest );
                where.right = deleteFrom(where.right, key);
            }
        }
        return where;
    }

    private Node smallestNode(Node where)
    {
        if ( where == null )
            return null;
        else
        {
            Node current = where;
            while ( current.left != null )
                current = current.left;
            return current;
        }
    }

    private void swap( Node a, Node b )
    {
        Node tmp = new Node();

        tmp.key = a.key;
        a.key = b.key;
        b.key = tmp.key;

        tmp.val = a.val;
        a.val = b.val;
        b.val = tmp.val;
    }

    public void displayme()
    {
        displayme(root);
    }

    private void displayme(Node where)
    {
        if ( where == null )
            return;
        displayme(where.left);
        System.out.printf("( %s, %d )\n",where.key,where.val);
        displayme(where.right);
    }

    public Val delMin()
    {
        if ( root == null )
            throw new NoSuchElementException("Cannot delete from empty tree!!");
        Node current = root;
        while ( current.left != null )
            current = current.left;
        Val val = current.val;
        delete(current.key);
        count--;
        return val;
    }

    public Val delMax()
    {
        if ( root == null )
            throw new NoSuchElementException("Cannot delete from empty tree!!");
        Node current = root;
        while ( current.right != null )
            current = current.right;
        Val val = current.val;
        delete(current.key);
        count--;
        return val;
    }

    private class Node
    {
        public Key key;
        public Val val;
        public Node left;
        public Node right;
        public Node(){};
        public Node(Key k, Val v)
        {
            key = k;
            val = v;
            left = null;
            right = null;
        }
    }


    public static void main(String[] args)
    {
        BinarySearchTree<String,Integer> bst = new BinarySearchTree<String,Integer>();

        bst.put("m",1);
        bst.put("i",2);
        bst.put("o",3);
        bst.put("a",4);
        bst.put("j",5);
        bst.put("n",6);
        bst.put("y",7);
        bst.put("b",8);
        bst.put("x",9);
        bst.put("z",10);

        bst.displayme();

        bst.delete("y");
        bst.displayme();

        System.out.println(bst.delMin());
        bst.displayme();

        System.out.println(bst.delMax());
        bst.displayme();

    }
}
