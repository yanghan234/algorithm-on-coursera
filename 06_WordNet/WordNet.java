import edu.princeton.cs.algs4.Digraph;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.In;
public class WordNet
{
    public WordNet(String synsets, String hypernyms)
    {
        if ( synsets == null || hypernyms == null )
            throw new IllegalArgumentException("File not given");
        this.readHypernyms(hypernyms);

    }

    private void readHypernyms(String filename)
    {
        In hyper = new In(filename);
        while ( !hyper.isEmpty() )
        {
            String line = hyper.readLine();
            String[] strs = line.split(",");
            for ( String str: strs )
                System.out.println(str);
        }
    }

    public static void main(String[] args)
    {
        String synsetFile = "./synsets.txt";
        String hypernymFile = "./hypernyms.txt";

        WordNet wn = new WordNet(synsetFile,hypernymFile);
    }

}
