import edu.princeton.cs.algs4.TST;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;
import java.util.HashSet;
public final class BoggleSolver
{
    private TST<Integer> tr;
    private BoggleBoard board;
    private HashSet<String> wordSet;
    public BoggleSolver(String[] dict)
    {
        tr = new TST<>();
        for ( int i = 0; i < dict.length; i++ )
            tr.put(dict[i],i);
    }

    public Iterable<String> getAllValidWords(BoggleBoard board)
    {
        this.board = board;
        int nrows = board.rows();
        int ncols = board.cols();
        wordSet = new HashSet<String>();

        for ( int irow = 0; irow < nrows; irow++ )
            for ( int icol = 0; icol < ncols; icol++ )
            {
                String word = new String();
                boolean[] marked = new boolean[nrows*ncols];
                dfs(marked.clone(), irow*ncols+icol, word);
            }

        System.out.println(wordSet.size());
        return wordSet;
    }

    private void dfs(boolean[] marked, int start, String word)
    {
        int i = start/board.cols();
        int j = start%board.cols();
        char c = board.getLetter(i,j);
        if ( c == 'Q' ) word += "QU";
        else word += String.valueOf(c);
        marked[start] = true;

        if ( !tr.keysWithPrefix(word).iterator().hasNext() ) return;
        if ( word.length() >= 3 && tr.contains(word) ) wordSet.add(word);
        for ( int next: adj(start) )
        {
            if ( !marked[next] )
            {
                dfs(marked.clone(), next, word);
            }
        }
    }

    private Iterable<Integer> adj(int me)
    {
        ArrayList<Integer> res = new ArrayList<>();
        int irow = me/board.cols();
        int icol = me%board.cols();
        if ( check(irow-1,icol-1) ) res.add(xy2ind(irow-1,icol-1) );
        if ( check(irow-1,icol)   ) res.add(xy2ind(irow-1,icol)   );
        if ( check(irow-1,icol+1) ) res.add(xy2ind(irow-1,icol+1) );
        if ( check(irow,  icol-1) ) res.add(xy2ind(irow,  icol-1) );
        if ( check(irow,  icol+1) ) res.add(xy2ind(irow,  icol+1) );
        if ( check(irow+1,icol-1) ) res.add(xy2ind(irow+1,icol-1) );
        if ( check(irow+1,icol)   ) res.add(xy2ind(irow+1,icol)   );
        if ( check(irow+1,icol+1) ) res.add(xy2ind(irow+1,icol+1) );
        return res;
    }

    private int xy2ind(int x, int y) { return x*board.cols()+y; }

    private boolean check(int i, int j) { return i >= 0 && i < board.rows() && j >= 0 && j < board.cols(); }

    public int scoreOf(String word)
    {
        if ( !tr.contains(word) ) return 0;
        int N = word.length();
        int score = 0;
        if ( N <= 2 ) score = 0;
        else if ( N == 3 || N == 4 ) score = 1;
        else if ( N == 5 ) score = 2;
        else if ( N == 6 ) score = 3;
        else if ( N == 7 ) score = 5;
        else score = 11;
        return score;
    }
}
