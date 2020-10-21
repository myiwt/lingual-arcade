package main;
 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import model.Score;

/**
 * COMP603: Program Design and Construction: Assessment 1
 * 1 September 2020
 * Semester 2 2020
 * 
 * Manages the Scoreboard which saves game results and displays the score history.
 * The score information is read from, and written to a text file.
 * 
 * @author ghq8692 Megan Teh
 */
public class Scoreboard extends Observable {
    private HashSet<Score> scores;
    private static ArrayList<String[]> scoreboardList;
    private static final String[] columns = new String[]{"Datetime", "Game type", "Score"};
    
    /**
     * The scores are stored as a HashMap with the date/time and the type of game
     * played as the keys and the game scores as the values.
     */
    public Scoreboard() {
        scoreboardList = new ArrayList<String[]>();
        scores = new HashSet<Score>();
    }
    
    /**
     * Stores scores into a 2D array so that it can be accessed to create a table in the GUI.
     * @param dateTime
     * @param game
     * @param score 
     */
    private static void saveScoreToTable(String dateTime, String game, int score) {
        scoreboardList.add(new String[] {dateTime, game, String.valueOf(score)});
    }
    
    public static ArrayList<String[]> getScoreboardList() {
        return scoreboardList;
    }
    
    public static String[] getColumns() {
        return columns;
    }
}