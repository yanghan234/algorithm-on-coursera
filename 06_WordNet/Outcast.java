import java.lang.IllegalArgumentException;
public class Outcast
{
    private final WordNet wordnet;
    public Outcast(WordNet wordnet)
    {
        if ( wordnet == null )
            throw new IllegalArgumentException("Argument to Outcast constructor cannot be null!!");
        this.wordnet = wordnet;
    }

    public String outcast(String[] nouns)
    {
        if ( nouns == null )
            throw new IllegalArgumentException("Argument to outcast() method cannot be null!!");
        int[] distance = new int[nouns.length];
        int imax = 0;
        for ( int i = 0; i < nouns.length; i++ )
        {
            distance[i] = 0;
            for ( int j = 0; j < nouns.length; j++ )
            {
                if ( i == j ) continue;
                else
                    distance[i] += wordnet.distance(nouns[i],nouns[j]);
            }
            if ( distance[i] > distance[imax] )
                imax = i;
        }
        return nouns[imax];
    }
}
