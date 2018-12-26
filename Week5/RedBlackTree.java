import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
public class RedBlackTree<Key extends Comparable<Key>, Val>
{
    private static final boolean BLACK=false;
    private static final boolean RED=true;
    public Node root;
    public int count;

    public RedBlackTree()
    {
        root = null;
        count = 0;
    }

    public RedBlackTree( Key key, Val val )
    {
        root = new Node(key,val,BLACK);
        count++;
    }

    public void put(Key key, Val val)
    {
        if ( key == null )
            throw new IllegalArgumentException("First argument of put() method cannot be null!!");
        if ( root == null )
            root = new Node(key,val,BLACK);
        else
            root = putTo(root,key,val);
        if ( root.color == RED )
            root.color = BLACK;
        count++;
    }

    private Node putTo(Node where, Key key, Val val)
    {
        if ( where == null )
            where = new Node(key,val,RED);
        else if ( key.compareTo(where.key) > 0 )
            where.right = putTo(where.right,key,val);
        else if ( key.compareTo(where.key) < 0 )
            where.left = putTo(where.left,key,val);
        else
            where.val = val;
        if ( where.right != null && where.right.color == RED ) where = rotateLeft(where);
        if ( where.left != null
                && where.left.left != null
                && where.left.color == RED
                && where.left.left.color == RED ) where = rotateRight(where);
        if ( where.left != null
                && where.right != null
                && where.left.color == RED
                && where.right.color == RED ) where = flipColor(where);
        return where;
    }

    public Val get(Key key)
    {
        if ( key == null )
            throw new IllegalArgumentException("Argument of get() method cannot be null!!");
        if ( root == null )
            throw new NoSuchElementException("Cannot get from an empty tree!!");
        return getFrom(root,key);
    }

    public boolean contains(Key key)
    {
        return getFrom(root,key) != null;
    }

    private Val getFrom(Node where, Key key)
    {
        if ( where == null )
            return null;
        else
        {
            if ( key.compareTo(where.key) < 0 )
                return getFrom(where.left,key);
            else if ( key.compareTo(where.key) > 0 )
                return getFrom(where.right,key);
            else
                return where.val;
        }
    }

    //public void delete(Key key)
    //{
    //    deleteFrom(root,key);
    //}

    //private Node deleteFrom(Node where, Key key)
    //{
    //    if ( where == null )
    //        return null;
    //    if ( key.compareTo(where.key) < 0 )
    //        where.left = deleteFrom(where.left,key);
    //    else if ( key.compareTo(where.key) > 0 )
    //        where.right = deleteFrom(where.right,key);
    //    else
    //    {
    //        if ( where.right == null )
    //            where = where.left;
    //        else
    //        {
    //            Node smallestRight = minIn(where.right,key);

    //            Key tmpk = smallestRight.key;
    //            smallestRight.key = where.key;
    //            where.key = tmpk;

    //            Val tmpv = smallestRight.val;
    //            smallestRight.val = where.val;
    //            where.val = tmpv;

    //            where.right = deleteFrom(where.right,key);
    //        }
    //    }
    //    if ( where.right != null && where.right.color == RED ) where = rotateLeft(where);
    //    if ( where.left != null
    //            && where.left.left != null
    //            && where.left.color == RED
    //            && where.left.left.color == RED ) where = rotateRight(where);
    //    if ( where.left != null
    //            && where.right != null
    //            && where.left.color == RED
    //            && where.right.color == RED ) where = flipColor(where);
    //    return where;
    //}

    private Node minIn(Node where,Key key)
    {
        Node current = where;
        while ( current.left != null )
            current = current.left;
        return current;
    }

    public void displayme()
    {
        displayme(root);
    }

    private void displayme(Node where)
    {
        if ( where == null )
            return;
        else
        {
            displayme(where.left);
            System.out.printf("( %s, %d, %b )\n",where.key,where.val,where.color);
            displayme(where.right);
        }
    }

    private Node rotateLeft( Node where )
    {
        assert where != null;
        assert where.right != null;
        assert where.right.color == RED;
        Node x = where;
        Node y = where.right;
        x.right = y.left;
        y.left = x;
        where = y;
        y.color = x.color;
        x.color = RED;
        return where;
    }

    private Node flipColor( Node where )
    {
        assert where != null;
        assert where.left  != null;
        assert where.right != null;
        assert where.color == BLACK;
        assert where.left.color  == RED;
        assert where.right.color == RED;
        where.left.color = BLACK;
        where.right.color = BLACK;
        where.color = RED;
        return where;
    }

    private Node rotateRight( Node where )
    {
        assert where != null;
        assert where.left != null;
        Node x = where;
        Node y = where.left;
        x.left = y.right;
        y.right = x;
        where = y;
        y.color = x.color;
        x.color = RED;
        return where;
    }

    private class Node
    {
        public Key key;
        public Val val;
        public boolean color; // black = false; red = true;
        public Node left;
        public Node right;
        public Node(Key k, Val v, boolean c)
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
        rbt.put("e",5);
        rbt.put("j",10);
        rbt.put("n",15);
        rbt.put("l",12);
        rbt.put("m",14);
        rbt.put("h",8);
        rbt.put("a",1);
        rbt.put("g",6);
        rbt.put("i",9);
        rbt.put("x",24);
        rbt.put("y",25);
        rbt.put("z",26);

    //    rbt.delete("z");
        rbt.displayme();

        System.out.println(rbt.get("m"));
    }
}
