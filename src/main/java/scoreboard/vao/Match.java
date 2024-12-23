package scoreboard.vao;

public class Match
{
    private Long startTimestamp;
    private String matchId;
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    
    public Long getStartTimestamp()
    {
        return startTimestamp;
    }
    public void setStartTimestamp(Long startTimestamp)
    {
        this.startTimestamp = startTimestamp;
    }
    public String getMatchId()
    {
        return matchId;
    }
    public void setMatchId(String matchId)
    {
        this.matchId = matchId;
    }
    public String getHomeTeam()
    {
        return homeTeam;
    }
    public void setHomeTeam(String homeTeam)
    {
        this.homeTeam = homeTeam;
    }
    public String getAwayTeam()
    {
        return awayTeam;
    }
    public void setAwayTeam(String awayTeam)
    {
        this.awayTeam = awayTeam;
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
    
    
}
