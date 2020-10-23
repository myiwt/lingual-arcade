package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.ScoreboardView;

/**
 * This is a model class that follows the MVC (Model View Controller) design
 * pattern. A Model holds: 1) data to indicate the current view of the GUI, 
 * 2) the database connection and SQL operations information to manage the
 * scoreboard history. The Model class is used to update the MainView class and
 * is manipulated by the Controller class.
 * 
 * This class saves game results to the database and reads the database to update
 * the score history display. This class is also used to manage which panel is
 * being displayed in the GUI (which uses a CardLayout layout).
 * 
 * @author ghq8692
 */
public class Model extends Observable {
    private String viewName;
    public static HashSet<FinalScore> scoresSet;
    public static ArrayList<FinalScore> scoresList;
    private Connection conn=null;
    private String url="jdbc:derby:scoreboard;create=true";  //url of the DB host
    private String username="pdc";
    private String password="pdc";
    private Statement SQLstatement = null;
    
    public Model() {
        connectToDB();
        scoresSet = new HashSet<FinalScore>();
        scoresList = new ArrayList<FinalScore>();
    }
    
    public void connectToDB() {
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            SQLstatement = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ScoreboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveScoreToDB(int score, String gameType) {
        Timestamp sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
        try {
            String insertStatement = "INSERT INTO SCOREBOARD VALUES ('" + sqlDate+ "', '" + gameType +"'," + score + " )";
            this.SQLstatement.addBatch(insertStatement);
            this.SQLstatement.executeBatch();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    public void updateScoreboard() {
        scoresSet = new HashSet<FinalScore>();
        scoresList = new ArrayList<FinalScore>();
        try {
            ResultSet rs = SQLstatement.executeQuery("SELECT * FROM SCOREBOARD order by DateTime");
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("DateTime");
                int score = rs.getInt("Score");
                String gameType = rs.getString("GAME_TYPE");
                scoresSet.add(new FinalScore(timestamp, score, gameType));
            }
            scoresList.addAll(scoresSet);
            Collections.sort(scoresList);
            setChanged();
            notifyObservers(scoresList);
        } catch (SQLException ex) {
            Logger.getLogger(ScoreboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeView(String actionCommand) {
        this.viewName = actionCommand;
        setChanged();
        notifyObservers(viewName);
    }

}
