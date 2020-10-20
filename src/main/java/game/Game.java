package game;

import java.util.Scanner;

/**
 * COMP603: Program Design and Construction: Assessment 1
 * 1 September 2020
 * Semester 2 2020
 * 
 * Abstract class that defines a Game as having a score, a boolean flag to indicate
 * if the game is in progress or not and a Scanner (for user input).
 *
 * @author ghq8692 Megan Teh
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
     * Method used to start running a game (as prompted by user input in the CUI).
     */
    public abstract void runCUIGame();
    
    /**
     * At the end of each Game, the final score is saved into the Scoreboard and it
     * will be viewable to the user.
     * @param score integer representing the Game final score
     */
    protected abstract void saveScoreForCUI(int score);
    
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
