import edu.princeton.cs.algs4.Digraph;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
public final class WordNet
{
    private ArrayList<HashSet<String>> nounsSplitted;
    private HashSet<String> allNouns;
    private ArrayList<String> nouns;
    private ArrayList<Integer> ids;
    private final Digraph graph;
    private SAP sap;

    public WordNet(String synsets, String hypernyms)
    {
        if ( synsets == null || hypernyms == null )
            throw new IllegalArgumentException("File not given");
        this.nounsSplitted= new ArrayList<HashSet<String>>();
        this.allNouns = new HashSet<String>();
        this.nouns = new ArrayList<String>();
        this.ids = new ArrayList<Integer>();
        this.readSynsets(synsets);

        graph = new Digraph(nouns.size());
        this.readHypernyms(hypernyms);

        sap = new SAP(graph);
    }

    private void readSynsets(String filename)
    {
        if ( filename == null )
            throw new IllegalArgumentException("Argument to readSynsets cannot be null!!");
        In syn = new In(filename);
        while ( !syn.isEmpty() )
        {
            HashSet<String> h = new HashSet<String>();
            String line = syn.readLine();
            String[] strs = line.split(",");
            this.ids.add(Integer.valueOf(strs[0]));
            this.nouns.add(strs[1]);
            for ( String s: strs[1].trim().split("\\s+") )
            {
                h.add(s);
                allNouns.add(s);
            }
            this.nounsSplitted.add(h);
        }
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
                    this.graph.addEdge(index,Integer.valueOf(strs[i]));
        }
    }

    public Iterable<String> nouns()
    {
        return allNouns;
    }

    public boolean isNoun(String word)
    {
        if ( word == null )
            throw new IllegalArgumentException();
        return allNouns.contains(word);
    }

    public int distance(String nounA, String nounB)
    {
        if ( !isNoun(nounA) || !isNoun(nounB) )
            throw new IllegalArgumentException();
        if ( nounA.equals(nounB) )
            return 0;

        ArrayList<Integer> indexA = new ArrayList<Integer>();
        ArrayList<Integer> indexB = new ArrayList<Integer>();
        for ( int i = 0; i < nounsSplitted.size(); i++ )
        {
            if ( nounsSplitted.get(i).contains(nounA) )
                indexA.add(i);
            if ( nounsSplitted.get(i).contains(nounB) )
                indexB.add(i);
        }

        return sap.length(indexA,indexB);
    }

    public String sap(String nounA, String nounB)
    {
        if ( !isNoun(nounA) || !isNoun(nounB) )
            throw new IllegalArgumentException();

        ArrayList<Integer> indexA = new ArrayList<Integer>();
        ArrayList<Integer> indexB = new ArrayList<Integer>();
        for ( int i = 0; i < nounsSplitted.size(); i++ )
        {
            if ( nounsSplitted.get(i).contains(nounA) )
                indexA.add(i);
            if ( nounsSplitted.get(i).contains(nounB) )
                indexB.add(i);
        }

        int head = sap.ancestor(indexA,indexB);
        return nouns.get(head);
    }

//    public static void main(String[] args)
//    {
//        In in = new In(args[0]);
//        Digraph G = new Digraph(in);
//        SAP sap = new SAP(G);
//        while (!StdIn.isEmpty())
//        {
//            int v = StdIn.readInt();
//            int w = StdIn.readInt();
//            int length   = sap.length(v, w);
//            int ancestor = sap.ancestor(v, w);
//            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//        }
//    }
    public static void main(String[] args)
    {
        WordNet wn = new WordNet("./test/synsets.txt","./test/hypernyms.txt");
        //String nounA = "immunoglobulin_G";
        //String nounB = "immunoglobulin_G";
        //System.out.println(wn.sap(nounA,nounB));
        //System.out.println(wn.distance(nounA,nounB));
        wn.nouns();
    }
}
