import edu.princeton.cs.algs4.Digraph;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
public class WordNet
{
    private int numSynset;
    private ArrayList<String> nouns;
    private ArrayList<Integer> ids;
    public Digraph graph;

    public WordNet(String synsets, String hypernyms)
    {
        if ( synsets == null || hypernyms == null )
            throw new IllegalArgumentException("File not given");
        this.nouns = new ArrayList<String>();
        this.ids = new ArrayList<Integer>();
        this.readSynsets(synsets);
        this.numSynset = nouns.size();

        graph = new Digraph(this.numSynset);
        this.readHypernyms(hypernyms);
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
            this.ids.add(Integer.valueOf(strs[0]));
            this.nouns.add(strs[1]);
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
        return nouns;
    }

    public boolean isNoun(String word)
    {
        return nouns.contains(word);
    }

    public int distance(String nounA, String nounB)
    {
        if ( !isNoun(nounA) || !isNoun(nounB) )
            return -1;
        if ( nounA.equals(nounB) )
            return 0;

        int indexA = -1;
        int indexB = -1;
        for ( int i = 0; i < nouns.size(); i++ )
        {
            if ( nounA.equals(nouns.get(i)) )
                indexA = i;
            if ( nounB.equals(nouns.get(i)) )
                indexB = i;
            if ( indexA >= 0 && indexB >= 0 )
                break;
        }
        if ( indexA < 0 || indexB < 0 )
            return -1;
        return 0;
    }

//    public String sap(String nounA, String nounB)
//    {
//        if ( !isNoun(nounA) )
//            System.out.println("nounA not in nouns");
//        if ( !isNoun(nounB) )
//            System.out.println("nounB not in nouns");
//        if ( !(isNoun(nounA) && isNoun(nounB)) )
//            return null;
//        if ( nounA.equals(nounB) )
//            return nounA;
//        int indexA = -1;
//        int indexB = -1;
//        HashSet<Integer> setA = new HashSet<Integer>();
//        HashSet<Integer> setB = new HashSet<Integer>();
//        for ( Node n : nodes )
//        {
//            if ( n.synset.equals(nounA) )
//                indexA = n.index;
//            if ( n.synset.equals(nounB) )
//                indexB = n.index;
//            if ( indexA >= 0 && indexB >= 0 )
//                break;
//        }
//        System.out.println("indexA = "+indexA+", indexB = "+indexB);
//
//        return null;
//    }
//
//    public void glance()
//    {
//        for ( int i = 0; i < 35; i++ )
//        {
//            Node n = nodes.get(i);
//            System.out.printf("%d,",n.index);
//            System.out.printf("%s\n",n.synset);
//            System.out.println(this.adj[i].toString());
//        }
//    }

    public static void main(String[] args)
    {
        String synsetFile = "./synsets.txt";
        String hypernymFile = "./hypernyms.txt";

        WordNet wn = new WordNet(synsetFile, hypernymFile);

        SAP sap = new SAP(wn.graph);

        System.out.println(sap.ancestor(1,2));
        System.out.println(sap.length(1,2));

        int[] arr = {0,1,2,3};
        //System.out.println(sap.commonAncestor(arr));

    }

}
