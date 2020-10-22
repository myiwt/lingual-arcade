package game;

import java.util.Scanner;

/**
 * Abstract class that defines a Game as having a score, a boolean flag to indicate
 * if the game is in progress or not and a Scanner (for user input).
 *
 * @author ghq8692
 */
public abstract class Game {
    protected boolean inProgress;
    protected int score;
    protected Scanner keyboard;
    
    public Game() {
        this.inProgress = true;
        this.score = 0;
    }
    
    /**
     * Returns the final score at the end of a Game.
     * @return Returns the final score at the end of a Game.
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Sets the Game score.
     * @param score integer representing the Game score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns true if a Game is in progress and false if it is not.
     * @return Returns true if a Game is in progress and false if it is not.
     */
    public boolean getInProgress() {
        return inProgress;
    }
    
    /**
     * Sets the inProgress boolean flag representing whether or not a Game is in progress.
     * @param inProgress Sets the InProgress boolean flag. A true value represents
     * that a Game session is still in progress and false if it is not.
     */
    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }
}
