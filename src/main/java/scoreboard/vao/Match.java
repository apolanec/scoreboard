package scoreboard.vao;

public class Match implements Comparable<Match>
{
    private final Long startTimestamp;
    private final String matchId;
    private final String homeTeam;
    private final String awayTeam;
    private Integer homeScore;
    private Integer awayScore;

    public Match(String matchId, String homeTeam, String awayTeam)
    {
        super();
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startTimestamp = System.currentTimeMillis();
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

    @Override
    public int compareTo(Match o)
    {
        if (o == null)
        {
            return 1;
        }
        int i = getTotalScore().compareTo(o.getTotalScore());
        if (i == 0)
        {
            return startTimestamp.compareTo(o.getStartTimestamp());
        }
        else
        {
            return i;
        }
    }

}
