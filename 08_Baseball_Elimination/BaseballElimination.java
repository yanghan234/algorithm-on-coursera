import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
public class BaseballElimination
{
    private int numOfTeams;
    private int numOfPairs;
    private String[] teams;
    private int[] win;
    private int[] loss;
    private int[] left;
    private int[][] g;
    private int maxWin,maxWinIndex;
    private HashMap<String,Integer> teamIndex;
    private HashSet<String> certificates;
    private FordFulkerson ff;
    public BaseballElimination(String filename)
    {
        if ( filename == null )
            throw new IllegalArgumentException();
        In in = new In(filename);
        if ( !in.exists() )
            throw new IllegalArgumentException();

        maxWin = 0;
        teamIndex = new HashMap<String,Integer>();

        while ( !in.isEmpty() )
        {
            numOfTeams = in.readInt();
            teams = new String[numOfTeams];
            win = new int[numOfTeams];
            loss = new int[numOfTeams];
            left = new int[numOfTeams];
            g = new int[numOfTeams][numOfTeams];
            for ( int iteam = 0; iteam < numOfTeams; iteam++ )
            {
                teams[iteam] = in.readString();
                teamIndex.put(teams[iteam],iteam);
                win[iteam] = in.readInt();
                if ( win[iteam] > maxWin )
                {
                    maxWin = win[iteam];
                    maxWinIndex = iteam;
                }
                loss[iteam] = in.readInt();
                left[iteam] = in.readInt();
                for ( int iteam2 = 0; iteam2 < numOfTeams; iteam2++ )
                    g[iteam][iteam2] = in.readInt();
            }
        }
        numOfPairs = numOfTeams*(numOfTeams-1)/2;
    }

    public int numberOfTeams() { return numOfTeams; }

    public Iterable<String> teams()
    {
        return new Iterable<String>()
        {
            public Iterator<String> iterator()
            {
                return new Iterator<String>()
                {
                    int current = 0;
                    public boolean hasNext()
                    {
                        return current < numOfTeams;
                    }

                    public String next()
                    {
                        if ( !hasNext() ) throw new IllegalArgumentException();
                        return teams[current++];
                    }
                };
            }
        };
    }

    public int wins(String team)
    {
        if ( team == null )
            throw new IllegalArgumentException();

        if ( !teamIndex.containsKey(team) )
            throw new IllegalArgumentException();

        return win[teamIndex.get(team)];
    }

    public int losses(String team)
    {
        if ( team == null )
            throw new IllegalArgumentException();

        if ( !teamIndex.containsKey(team) )
            throw new IllegalArgumentException();

        return loss[teamIndex.get(team)];
    }

    public int remaining(String team)                 // number of remaining games for given team
    {
        if ( team == null )
            throw new IllegalArgumentException();

        if ( !teamIndex.containsKey(team) )
            throw new IllegalArgumentException();

        return left[teamIndex.get(team)];
    }

    public int against(String team1, String team2)    // number of remaining games between team1 and team2
    {
        if ( team1 == null || team2 == null )
            throw new IllegalArgumentException();

        if ( !teamIndex.containsKey(team1) || !teamIndex.containsKey(team2) )
            throw new IllegalArgumentException();

        return g[teamIndex.get(team1)][teamIndex.get(team2)];
    }

    public boolean isEliminated(String team)              // is given team eliminated?
    {
        if ( team == null )
            throw new IllegalArgumentException();

        if ( !teamIndex.containsKey(team) )
            throw new IllegalArgumentException();

        int myIndex = teamIndex.get(team);
        certificates = new HashSet<String>();

        if ( numOfTeams == 1 )
            return false;
        else
        {
            if ( maxWin > win[myIndex] + left[myIndex] )
            {
                certificates.add(teams[maxWinIndex]);
                return true;
            }
            int totalGames = 0;
            FlowNetwork net = new FlowNetwork(1+1+numOfPairs+numOfTeams);
            int iPair = 0;
            for ( int i = 0; i < numOfTeams-1; i++ )
                for ( int j = i+1; j < numOfTeams; j++ )
                {
                    iPair++;
                    if ( i != myIndex && j != myIndex )
                    {
                        totalGames += g[i][j];
                        net.addEdge(new FlowEdge(0,iPair,g[i][j]));
                        net.addEdge(new FlowEdge(iPair,1+numOfPairs+i,Double.POSITIVE_INFINITY));
                        net.addEdge(new FlowEdge(iPair,1+numOfPairs+j,Double.POSITIVE_INFINITY));
                    }
                }
            for ( int i = 0; i < numOfTeams; i++ )
                if ( i != myIndex )
                {
                    net.addEdge(new FlowEdge(1+numOfPairs+i,1+numOfPairs+numOfTeams,win[myIndex]+left[myIndex]-win[i]));
                }
            ff = new FordFulkerson(net,0,1+numOfPairs+numOfTeams);
            for ( int i = 0; i < numOfTeams; i++ )
                if ( ff.inCut(1+numOfPairs+i) )
                    certificates.add(teams[i]);
            if ( totalGames == (int) ff.value() )
                return false;
            else
                return true;
        }
    }

    public Iterable<String> certificateOfElimination(String team)
    {

        if ( team == null )
            throw new IllegalArgumentException();

        if ( !teamIndex.containsKey(team) )
            throw new IllegalArgumentException();

        this.isEliminated(team);
        return certificates;
    }

    public static void main(String[] args)
    {
        String filename = "./tests/teams5b.txt";
        BaseballElimination be = new BaseballElimination(filename);
        //be.display();

        for ( String s: be.teams() )
        {
            System.out.println(s);
            System.out.println(be.certificateOfElimination(s));
        }

    }
}
