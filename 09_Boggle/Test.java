import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;
public class Test
{
    public static void main(String[] args) throws Exception
    {
        BoggleBoard bb = new BoggleBoard("test/board4x4.txt");
        System.out.println(bb.toString());

        BufferedReader bf =
            new BufferedReader(
                new FileReader(
                    new File("test/dictionary-algs4.txt")));

        String line;
        ArrayList<String> strs = new ArrayList<>();
        while ( ( line = bf.readLine() ) != null )
            strs.add(line);

        String[] arr = new String[strs.size()];
        for ( int i = 0; i < strs.size(); i++ )
            arr[i] = strs.get(i);
        BoggleSolver sol = new BoggleSolver(arr);
        int score = 0;
        for ( String s: sol.getAllValidWords(bb) )
        {
            System.out.println(s);
            score += sol.scoreOf(s);
        }
        System.out.println("Score = "+score);
    }
}
