package model;


/**
 * Interface for create a game model class (e.g. quiz game or card game).
 * 
 * @author ghq8692 Megan Teh
 */
public interface GameInterface {
    
    /**
     * Method used to start running a game (as prompted by user input in the CUI).
     */
    public abstract void runGame();
    
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
}
