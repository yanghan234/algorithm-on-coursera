/* ******************************************************************
 * This implementation of symbol table uses an ordered array.
 * Complexity:
 *      Search: Worst: lgN, Average: lgN ( Binary search )
 *      Insert: Worst: N,   Average: N/2
 *      Ordered iterator: Yes
 *      Interface: compareTo()
 * ******************************************************************
 */
 
import java.util.TreeMap;
import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
public class OrderedArraySymbolTable<Key extends Comparable<Key>,Val> implements Iterable<Key>
{
    private TreeMap<Key,Val> st;

    public OrderedArraySymbolTable()
    {
        st = new TreeMap<Key,Val>();
    }

    public Val get(Key key)
    {
        if ( key == null )
            throw new IllegalArgumentException("Argument of get() method cannot be null!!");
        return st.get(key);
    }

    public void put(Key key, Val val)
    {
        if ( key == null )
            throw new IllegalArgumentException("First argument of put() method cannot be null!!");
        if ( val == null ) st.remove(key);
        else st.put(key,val);
    }

    public void delete(Key key)
    {
        if ( key == null )
            throw new IllegalArgumentException("Argument of delete() method cannot be null!!");
        st.remove(key);
    }

    public boolean contains(Key key)
    {
        if ( key == null )
            throw new IllegalArgumentException("Argument of contains() method cannot be null!!");
        return st.containsKey(key);
    }

    public int size()
    {
        return st.size();
    }

    public boolean isEmpty()
    {
        return st.isEmpty();
    }

    public Iterable<Key> keys()
    {
        return st.keySet();
    }

    public Iterator<Key> iterator()
    {
        return st.keySet().iterator();
    }

    public Key min()
    {
        if ( isEmpty() )
            throw new NoSuchElementException("min() not applicable to empty table");
        return st.firstKey();
    }

    public Key max()
    {
        if ( isEmpty() )
            throw new NoSuchElementException("max() not applicable to empty table");
        return st.lastKey();
    }

    public Key ceiling(Key key)
    {
        if ( key == null )
            throw new IllegalArgumentException("Argument of ceiling() method cannot be null!!");
        Key k = st.ceilingKey(key);
        if ( k == null )
            throw new NoSuchElementException("all keys are less than "+key);
        return k;
    }

    public Key floor(Key key)
    {
        if ( key == null )
            throw new IllegalArgumentException("Argument of floor() method cannot be null!!");
        Key k = st.floorKey(key);
        if ( k == null )
            throw new NoSuchElementException("all keys are greater than " + key );
        return k;
    }

    public static void main(String[] args)
    {
        OrderedArraySymbolTable<String,Integer> st = new OrderedArraySymbolTable<String,Integer>();
        st.put("a",1);
        st.put("b",2);
        st.put("c",3);

        for ( String k : st.keys() )
            System.out.println(st.get(k));
    }
}
