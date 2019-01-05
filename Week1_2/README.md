# Basic Data Structures: Stack and Queue

**Stacks** and **Queues** are basic data structures that have been widely used. The former has last-in-first-out (LIFO) accessing order while the latter has first-in-first-out (FIFO) order.

We will discuss the APIs that these data structures should at least have and how to implement them.

## Stack
public class Stack<Item>

| return type | function name & argument list|description       |
|-------------|:-------------------------:|:--------------:     |
|             |Stack()                    | default constructor |
|void         |push(Item)   | push/add one element to the top |
|Item         |top()          | access the element on the top   |
|Item/void    |pop()          | pop out the element on the top  |
|boolean      |isEmpty()   | if the stack is empty      |
|int          |size()   | return size of the stack   |

Detailed descriptions are:
* Stack(): The detailed constructor will create a stack object and initialize the container. As for the container, we may either use linked lists or resizing arrays, which will be discussed later.
* push(): The push() method add one more element to the top of the stack. This is the same as in C++ STL stack and java.util.Stack.
* top(): Access the element on the top of the stack. This is the function name as in C++ STL, but in java.util.Stack, the method is called peek().
* pop(): Remove the element on the top. It can either return the element or return nothing. Stack in C++ STL returns nothing while java.util.Stack returns the element on the top.
* isEmpty(): Tell if the stack is empty.
* size(): Return the number of elemenets in the stack.

To implement the stack, we must have an underlying container to hold the elemenets in the stack. Usually, people pick up the container from linked list and resizing array. Either has pros and cons.
### Linked list Stack
A typical linked list looks like the following in Java
```Java
public class Node<Item>
{
    public Item val;
    public Node<Item> next;
    public Node(Item elem)
    {
        val = elem;
        next = null;
    }
}
```
In a stack, we will have a root node. Each time adding or removing one element from the stack, we add or remove from the begining by modifying the root node.
```Java
public class Stack<Item>
{
// ...
    private Node<Item> root;
    public void push(Item elem)
    {
        Node<Item> newroot = new Node<Item>(elem);
        newroot.next = root;
        root = newroot;
    }
    public Item pop()
    {
        // make sure the stack is not empty
        Item elem = root.val;
        root = root.next;
        return elem;
    }
// ...
}
```
By doing so, the push() and pop() methods are done at constant cost. However, linked list implementation always requires extra memory to contain the data. For data set with every large size, available memory might not be enough.

### Resizing Array Stack
To solve the problem mentioned above, an alternative way is to implement stacks with resizing arrays. When declare arrays, we have to specify the length of the array. So, we implement the following technique.

1. When the current array is full, we double the size of the array.
2. When the number of elements in the array is smaller than 1/4 of the capacity of the array, we reduce the capacity to its half.

With the technique described above, the amortized cost to contain N elements will be logN.
When pushing elements to the stack, we push to the end; when poping from the stack, we pop from the end.
```Java
public class Stack<Item>
{
    private Item[] elems;
    private int capacity; // capacity of the stack for now
    private int count;    // number of elements in the stack for now
    // ...
    public void push(Item elem)
    {
        elems[count++] = elem;
        // if full, double capacity
        if ( count == capacity )
            this.resize(2*capacity);
    }

    public Item pop()
    {
        Item elem = elems[--count];
        if ( count <= capacity/4 )
            this.resize(capacity/2);
        return elem;
    }

    // resize the array
    private void resize(int newcapacity)
    {
        // allocate generic array
        Item[] newelems = (Item[]) new Object[newcapacity];
        for ( int i = 0; i < count; i++ )
            newelems[i] = elems[i];
        elems = newelems;
        capacity = newcapacity;
    }
    // ...
}
```

## Queue
public class Queue<Item>

| return type | function name & argument list|description       |
|-------------|:-------------------------:|:--------------:     |
|             |Queue()                    | default constructor |
|void         |enqueue(Item)   | add one element to the end |
|Item         |top()          | access the first element int the queue  |
|Item/void    |dequeue()          | pop out the element on the top  |
|boolean      |isEmpty()   | if the stack is empty      |
|int          |size()   | return size of the stack   |

A queue is very similar to a stack except that queues have different accessing order. We can also implement queues with linked lists and resizing arrays. If linked lists are used, we push to the front, while pop from the end. In this case, adding elements still has constant cost while dequeue() method has O(N) complexity. If arrays are used, we push to the end, while pop from the front. The complexity of array queue is the same as array stacks.

The codes are not shown here, but can be found in [linked-list queue](https://github.com/yanghan234/algorithm-on-coursera/blob/master/Week1_2/Queue_LinkedList.java) and [resizing-array queue](https://github.com/yanghan234/algorithm-on-coursera/blob/master/Week1_2/Queue_Array.java).
