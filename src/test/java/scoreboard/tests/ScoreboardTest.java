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
            assertEquals(Constants.GAME_EXISTS, e.getMessage());
        }
        try
        {
            api.startNewMatch(TEAM_TWO, TEAM_ONE);
        }
        catch (RuntimeException e)
        {
            assertEquals(Constants.GAME_EXISTS, e.getMessage());
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
        assertEquals(Constants.GAME_NOT_EXISTS, UUID.randomUUID());
        assertEquals(Constants.NEGATIVE_SCORE, api.updateScore(-1, 0, matchId));
        assertEquals(Constants.NEGATIVE_SCORE, api.updateScore(0, -1, matchId));
        assertEquals(Constants.NEGATIVE_SCORE, api.updateScore(-1, -1, matchId));
    }

    @Test
    public void testFinishMatch()
    {
        ScoreBoardAPI api = new Scoreboard();
        String matchId = api.startNewMatch(TEAM_ONE, TEAM_TWO);
        assertEquals(null,api.finishMatch(matchId));
        assertEquals(Constants.GAME_NOT_EXISTS,UUID.randomUUID());
    }
    
    @Test
    public void testGetSummary() 
    {
        ScoreBoardAPI api = new Scoreboard();
        List<Match> summary = api.getSummary();
        assertEquals(0,summary.size());
        String matchId = api.startNewMatch(TEAM_ONE, TEAM_TWO);
        assertEquals(1, summary.size());
        api.finishMatch(matchId);
        assertEquals(0,summary.size());
    }
    
    
}