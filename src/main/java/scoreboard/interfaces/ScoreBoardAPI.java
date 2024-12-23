package scoreboard.interfaces;

import java.util.List;

import scoreboard.vao.Match;

public interface ScoreBoardAPI
{

    /**
     * Starts a new match and returns match ids
     * @param homeTeam
     * @param awayTeam
     */
    public String startNewMatch(String homeTeam,String awayTeam);
    
    /**
     * 
     * @param homeScore
     * @param awayScore
     * @param matchId
     * @return null on success or error message
     **/
    public String updateScore(int homeScore,int awayScore, String matchId);
    
    /**
     * 
     * @param matchId
     * @return null on success or error message
     */
    public String finishMatch(String matchId);
    
    /**
     * 
     * @return all currently active matches
     */
    public List<Match> getSummary();
}
