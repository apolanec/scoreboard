package scoreboard;

import java.util.List;

import scoreboard.interfaces.ScoreBoardAPI;
import scoreboard.vao.Match;

public class Scoreboard implements ScoreBoardAPI
{

    @Override
    public String startNewMatch(String homeTeam, String awayTeam)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String updateScore(int homeScore, int awayScore, String matchId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String finishMatch(String matchId)
    {
        return null;
    }

    @Override
    public List<Match> getSummary()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
