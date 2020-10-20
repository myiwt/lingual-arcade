package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
import java.util.ArrayList;
import java.util.Scanner;

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
public class Scoreboard {
    private static String scoreText;
    private static final String filePath = "scoreboard.txt";
    private static ArrayList<String[]> scoreboardList;
    private static final String[] columns = new String[]{"Datetime", "Game type", "Score"};
    
    /**
     * The scores are stored as a HashMap with the date/time and the type of game
     * played as the keys and the game scores as the values.
     */
    public Scoreboard() {
        scoreboardList = new ArrayList<String[]>();
        
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
    
    /**
     * Saves the current date and time when a game has ended and the score to the
     * scores HashMap.
     * @param game The name of the game type e.g. Quiz Game or Card Matching Game
     * @param score The number of points scored for a game.
     */
    public static void saveScore(String game, int score) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDateTime = dateTimeFormatter.format(now);
        saveScoreToTable(currentDateTime, game, score);
        String currentScore;
        currentScore = currentDateTime + "\n"
                       + game + "\n"
                       + score+"\n";
        try {
            FileWriter fw = new FileWriter(filePath,true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(currentScore);
            pw.flush();
            fw.close();
        } catch (IOException e) {
            System.err.println("IO Error with local file. " + e);
        }
    }
    
    public static ArrayList<String[]> getScoreboardList() {
        return scoreboardList;
    }
    
    public static String[] getColumns() {
        return columns;
    }
    
    /**
     * Returns a string representation of the Scoreboard. It is used to display 
     * the score history in the CUI.
     * @return Returns a String representation of the Scoreboard.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("High scores table\n\n");
        sb.append(scoreText);
        // If no scores available then let the user know that they need to play games in order to create a score history
        if (scoreText.equals("")) {
            sb.append("No scores available. Please complete a game to display scores.\n");
        }
        return sb.toString();
    }
}