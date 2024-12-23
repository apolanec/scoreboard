package scoreboard.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import scoreboard.Constants;
import scoreboard.Scoreboard;
import scoreboard.interfaces.ScoreBoardAPI;
import scoreboard.vao.Match;

public class ScoreboardTest
{
    public static final String TEAM_ONE = "1";
    public static final String TEAM_TWO = "2";
    public static final String TEAM_THREE = "3";
    public static final String TEAM_FOUR = "4";
    public static final String TEAM_FIVE = "5";
    public static final String TEAM_SIX = "6";

    @Test
    public void testStartNewMatch()
    {
        ScoreBoardAPI api = new Scoreboard();
        String matchId = api.startNewMatch(TEAM_ONE, TEAM_TWO);
        List<Match> summary = api.getSummary();
        assertEquals(1, summary.size());// excpect exactly one match
        Match match = summary.get(0);
        assertEquals(0, match.getHomeScore());// must be nill nill
        assertEquals(0, match.getAwayScore());
        assertEquals(matchId, match.getMatchId());
        try
        {
            api.startNewMatch(TEAM_ONE, TEAM_TWO);
        }
        catch (RuntimeException e)
        {
            assertEquals(Constants.TEAM_ALLREADY_ACTIVE_IN_ANOTHER_MATCH, e.getMessage());
        }
        try
        {
            api.startNewMatch(TEAM_TWO, TEAM_ONE);
        }
        catch (RuntimeException e)
        {
            assertEquals(Constants.TEAM_ALLREADY_ACTIVE_IN_ANOTHER_MATCH, e.getMessage());
        }
    }

    @Test
    public void testUpdateScore()
    {
        ScoreBoardAPI api = new Scoreboard();
        String matchId = api.startNewMatch(TEAM_ONE, TEAM_TWO);
        assertEquals(null, api.updateScore(1, 0, matchId));
        assertEquals(null, api.updateScore(1, 1, matchId));
        assertEquals(null, api.updateScore(1, 0, matchId));
        assertEquals(Constants.MATCH_NOT_EXISTS, api.updateScore(0, 1, UUID.randomUUID().toString()));
        assertEquals(Constants.NEGATIVE_SCORE, api.updateScore(-1, 0, matchId));
        assertEquals(Constants.NEGATIVE_SCORE, api.updateScore(0, -1, matchId));
        assertEquals(Constants.NEGATIVE_SCORE, api.updateScore(-1, -1, matchId));
    }

    @Test
    public void testFinishMatch()
    {
        ScoreBoardAPI api = new Scoreboard();
        String matchId = api.startNewMatch(TEAM_ONE, TEAM_TWO);
        assertEquals(null, api.finishMatch(matchId));
        assertEquals(Constants.MATCH_NOT_EXISTS, api.finishMatch(UUID.randomUUID().toString()));
    }

    @Test
    public void testGetSummary() 
    {
        ScoreBoardAPI api = new Scoreboard();
        List<Match> summary = api.getSummary();
        assertEquals(0,summary.size());
        String matchId1 = api.startNewMatch(TEAM_ONE, TEAM_TWO);
        String matchId2=api.startNewMatch(TEAM_THREE, TEAM_FOUR);
        String matchId3=api.startNewMatch(TEAM_FIVE, TEAM_SIX);
        api.updateScore(1, 1, matchId3);
        summary = api.getSummary();
        assertEquals(3, summary.size());
        for(Match m:summary) 
        {
            System.out.println(m);
        }
        assertEquals(matchId3, summary.get(0));//match3 must be first (highest score)
        assertEquals(matchId2, summary.get(1));//match2 must be second (highest score)
        assertEquals(matchId1, summary.get(1));//match2 must be second (highest score)
        api.finishMatch(matchId1);
        assertEquals(0,summary.size());
    }

}
