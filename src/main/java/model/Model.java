package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
import main.Scoreboard;
import view.ScoreboardView;

/**
 *
 * @author ghq8692 Megan Teh
 */
public class Model extends Observable {
    private String viewName;
    public static Scoreboard scoreboard;
    public static HashSet<Score> scoresSet;
    public static ArrayList<Score> scoresList;
    private Connection conn=null;
    //private String url="jdbc:derby://localhost:1527/scoreboard;   create=true";  //url of the DB host
    private String url="jdbc:derby:scoreboard;create=true";  //url of the DB host
    private String username="pdc";
    private String password="pdc";
    private Statement SQLstatement = null;
    private DatabaseMetaData dbm;
    private ResultSet ScoreboardResultSet;
    
    public Model() {
        connectToDB();
        scoresSet = new HashSet<Score>();
        scoresList = new ArrayList<Score>();
        updateScoreboard();
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
        try {
            ResultSet rs = SQLstatement.executeQuery("SELECT * FROM SCOREBOARD order by DateTime");
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("DateTime");
                int score = rs.getInt("Score");
                String gameType = rs.getString("GAME_TYPE");
                scoresSet.add(new Score(timestamp, score, gameType));
            }
            scoresList.addAll(scoresSet);
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
