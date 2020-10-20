package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Observable;
import main.Scoreboard;

/**
 *
 * @author ghq8692 Megan Teh
 */
public class Model extends Observable {
    
    private String viewName;
    public static Scoreboard scoreboard;
    Connection conn=null;
    String url="jdbc:derby://localhost:1527/scoreboard;   create=true";  //url of the DB host
    String username="pdc";
    String password="pdc";
    Statement SQLstatement = null;
    DatabaseMetaData dbm;
    
    public Model() {
        scoreboard = new Scoreboard();
//        setScoreboard();
        connectToDB();
    }
    
    public void connectToDB() {
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    public void saveScoreToDB(int score, String gameType) {
        Timestamp sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
        
        try {
            SQLstatement = conn.createStatement();
            String insertStatement = "INSERT INTO SCOREBOARD VALUES ('" + sqlDate+ "', '" + gameType +"'," + score + " )";
            this.SQLstatement.addBatch(insertStatement);
            this.SQLstatement.executeBatch();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    public void setScoreboard() {
        try {
            ResultSet resultSet = SQLstatement.executeQuery("SELECT * FROM SCOREBOARD");
            while (resultSet.next()) {
                
            }    
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        setChanged();
        notifyObservers(scoreboard);
    }
    
    public void changeView(String actionCommand) {
        this.viewName = actionCommand;
        setChanged();
        notifyObservers(viewName);
    }

}
