import edu.princeton.cs.algs4.Digraph;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.In;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
public class WordNet
{
    private int numNode;
    private int[] prev;
    private ArrayList<Node> nodes;
    private HashSet<Integer>[] adj;
    private ArrayList<String> nouns;
    private class Node
    {
        public int index;
        public String synset;

        public Node(int index, String str)
        {
            this.index = index;
            synset = str;
        }
    }

    public WordNet(String synsets, String hypernyms)
    {
        if ( synsets == null || hypernyms == null )
            throw new IllegalArgumentException("File not given");
        numNode = 0;
        this.nodes = new ArrayList<Node>();
        this.nouns = new ArrayList<String>();
        this.readSynsets(synsets);
        adj = (HashSet<Integer>[]) new HashSet[numNode];
        for ( int i = 0; i < numNode; i++ )
            adj[i] = new HashSet<Integer>();
        this.readHypernyms(hypernyms);
    }

    public Iterable<String> nouns()
    {
        return nouns;
    }

    public boolean isNoun(String word)
    {
        return nouns.contains(word);
    }

    public String sap(String nounA, String nounB)
    {
        if ( !isNoun(nounA) )
            System.out.println("nounA not in nouns");
        if ( !isNoun(nounB) )
            System.out.println("nounB not in nouns");
        if ( !(isNoun(nounA) && isNoun(nounB)) )
            return null;
        if ( nounA.equals(nounB) )
            return nounA;
        int indexA = -1;
        int indexB = -1;
        HashSet<Integer> setA = new HashSet<Integer>();
        HashSet<Integer> setB = new HashSet<Integer>();
        for ( Node n : nodes )
        {
            if ( n.synset.equals(nounA) )
                indexA = n.index;
            if ( n.synset.equals(nounB) )
                indexB = n.index;
            if ( indexA >= 0 && indexB >= 0 )
                break;
        }
        System.out.println("indexA = "+indexA+", indexB = "+indexB);

        LinkedList<Integer> qA = new LinkedList<Integer>();
        LinkedList<Integer> qB = new LinkedList<Integer>();

        qA.addLast(indexA);
        setA.add(indexA);
        while ( !qA.isEmpty() )
        {
            int v = qA.pollFirst();
            for ( int i : adj[v] )
                if ( !setA.contains(i) )
                {
                    setA.add(i);
                    qA.addLast(i);
                    prev[i] = v;
                }
        }

        qB.addLast(indexB);
        setB.add(indexB);
        while ( !qB.isEmpty() )
        {
            int v = qB.pollFirst();
            for ( int i : adj[v] )
                if ( !setB.contains(i) )
                {
                    setB.add(i);
                    qB.addLast(i);
                    if ( setA.contains(i) )
                    {
                        qB.addLast(i);
                        Node n = nodes.get(i);
                        return n.synset;
                    }
                }
        }
        return null;
    }

    private void readSynsets(String filename)
    {
        if ( filename == null )
            throw new IllegalArgumentException("Argument to readSynsets cannot be null!!");
        In syn = new In(filename);
        while ( !syn.isEmpty() )
        {
            String line = syn.readLine();
            String[] strs = line.split(",");
            assert strs.length >= 2;
            Node n = new Node(Integer.valueOf(strs[0]),strs[1]);
            this.nouns.add(strs[1]);
            this.nodes.add(n);
            numNode++;
        }
        prev = new int[numNode];
    }

    private void readHypernyms(String filename)
    {
        if ( filename == null )
            throw new IllegalArgumentException("Argument to readHypernyms cannot be null!!");
        In hyper = new In(filename);
        while ( !hyper.isEmpty() )
        {
            String line = hyper.readLine();
            String[] strs = line.split(",");
            int index = Integer.valueOf(strs[0]);
            if ( strs.length > 1 )
                for ( int i = 1; i < strs.length; i++ )
                    this.adj[index].add(Integer.valueOf(strs[i]));
        }
    }

    public void glance()
    {
        for ( int i = 0; i < 35; i++ )
        {
            Node n = nodes.get(i);
            System.out.printf("%d,",n.index);
            System.out.printf("%s\n",n.synset);
            System.out.println(this.adj[i].toString());
        }
    }

    public static void main(String[] args)
    {
        String synsetFile = "./synsets.txt";
        String hypernymFile = "./hypernyms.txt";

        WordNet wn = new WordNet(synsetFile,hypernymFile);
//        wn.glance();

        System.out.println(wn.isNoun("ALGOL"));
        System.out.println(wn.sap("ALGOL","APC"));
    }

}
