package scoreboard.vao;

public class Match implements Comparable<Match>
{
    private final Long startTimestamp;
    private final Long nanoStartTime;
    private final String matchId;
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;

    public Match(String matchId, String homeTeam, String awayTeam)
    {
        super();
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startTimestamp = System.currentTimeMillis();
        this.nanoStartTime=System.nanoTime();
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public Long getStartTimestamp()
    {
        return startTimestamp;
    }

    public String getMatchId()
    {
        return matchId;
    }

    public String getHomeTeam()
    {
        return homeTeam;
    }

    public String getAwayTeam()
    {
        return awayTeam;
    }

    public int getHomeScore()
    {
        return homeScore;
    }

    public void setHomeScore(int homeScore)
    {
        this.homeScore = homeScore;
    }

    public int getAwayScore()
    {
        return awayScore;
    }

    public void setAwayScore(int awayScore)
    {
        this.awayScore = awayScore;
    }

    public Integer getTotalScore()
    {
        return homeScore + awayScore;
    }

    public Long getNanoStartTime()
    {
        return nanoStartTime;
    }

    @Override
    public int compareTo(Match o)
    {
        if (o == null)
        {
            return 1;
        }
        int i =-1* getTotalScore().compareTo(o.getTotalScore());
        if (i == 0)
        {
            int compareTo = startTimestamp.compareTo(o.getStartTimestamp());
            if(compareTo==0) 
            {
                return -1*nanoStartTime.compareTo(o.getNanoStartTime());
            }
            return compareTo;
        }
        else
        {
            return i;
        }
    }

    @Override
    public String toString()
    {
        return "Match [startTimestamp=" + startTimestamp + ", nanoStartTime=" + nanoStartTime + ", matchId=" + matchId + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", homeScore=" + homeScore + ", awayScore=" + awayScore + "]";
    }

}
