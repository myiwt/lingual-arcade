package model;


/**
 * Interface for standard/custom quiz game, card game and vision detection game
 * to implement.
 * @author ghq8692 Megan Teh
 */
public interface GameInterface {
    
    /**
     * Method used to start running a game (as prompted by user input in the CUI).
     */
    public abstract void runGame();
    
    /**
     * At the end of each Game, the final score is saved into the Scoreboard and it
     * will be viewable to the user.
     * @param score integer representing the Game final score
     */
    public void saveScoreToDB(int score);
    
    /**
     * Returns the final score at the end of a Game.
     * @return Returns the final score at the end of a Game.
     */
    public int getScore();
    
    /**
     * Sets the Game score.
     * @param score integer representing the Game score.
     */
    public void setScore(int score);

    /**
     * Returns true if a Game is in progress and false if it is not.
     * @return Returns true if a Game is in progress and false if it is not.
     */
    public boolean getInProgress();
    
    /**
     * Sets the inProgress boolean flag representing whether or not a Game is in progress.
     * @param inProgress Sets the InProgress boolean flag. A true value represents
     * that a Game session is still in progress and false if it is not.
     */
    public void setInProgress(boolean inProgress);
}
