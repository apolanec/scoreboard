package scoreboard;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import scoreboard.interfaces.ScoreBoardAPI;
import scoreboard.vao.Match;

public final class Scoreboard implements ScoreBoardAPI
{
    private Map<String, Match> matches = new HashMap<>();

    @Override
    public synchronized String startNewMatch(String homeTeam, String awayTeam)
    {
        for (Entry<String, Match> e : matches.entrySet())
        {
            Match tmp = e.getValue();
            if (tmp.getAwayTeam().equals(homeTeam) || tmp.getAwayTeam().equals(awayTeam))
            {
                return Constants.TEAM_ALLREADY_ACTIVE_IN_ANOTHER_MATCH;
            }
            if (tmp.getHomeTeam().equals(homeTeam) || tmp.getHomeTeam().equals(awayTeam))
            {
                return Constants.TEAM_ALLREADY_ACTIVE_IN_ANOTHER_MATCH;
            }
        }
        String newMatchId = UUID.randomUUID().toString();
        Match match = new Match(newMatchId, homeTeam, awayTeam);
        matches.put(newMatchId, match);
        return newMatchId;
    }

    @Override
    public synchronized String updateScore(int homeScore, int awayScore, String matchId)
    {
        Match m = matches.get(matchId);
        if (m == null)
        {
            return Constants.MATCH_NOT_EXISTS;
        }
        if(homeScore<0 || awayScore<0) 
        {
            return Constants.NEGATIVE_SCORE;
        }
        m.setAwayScore(awayScore);
        m.setHomeScore(awayScore);
        return null;
    }

    @Override
    public synchronized String finishMatch(String matchId)
    {
        Match m = matches.get(matchId);
        if (m == null)
        {
            return Constants.MATCH_NOT_EXISTS;
        }
        matches.remove(matchId);
        return null;
    }

    @Override
    public synchronized List<Match> getSummary()
    {
        List<Match> summary = new LinkedList<>(matches.values());
        Collections.sort(summary);
        return summary;
    }

}
