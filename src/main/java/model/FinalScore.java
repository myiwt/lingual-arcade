/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 * A FinalScore represents the final score after completing a game. A timestamp is
 * taken at time of game completion. A FinalScore also contains the type of game
 * played e.g. Card Game or Quiz Game.
 * @author Megan
 */
public class FinalScore implements Comparable<FinalScore> {
    private Timestamp timestamp;
    private int score;
    private String gameType;
    
    public FinalScore(Timestamp timestamp, int score, String gameType) {
        this.timestamp = timestamp;
        this.score = score;
        this.gameType = gameType;
    }

    public int getScore() {
        return score;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getGameType() {
        return gameType;
    }
    
    // Sort scores by timestamp in descending order, so that they display from 
    // the latest to earliest timestamp when viewing the Scoreboard in the GUI.
    @Override
    public int compareTo(FinalScore o) {
        return this.timestamp.compareTo(o.getTimestamp())*-1;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof FinalScore) {
            FinalScore fso = (FinalScore) o;
            if (fso.gameType.equals(this.gameType) && fso.score == this.score && 
                    fso.timestamp.equals(this.timestamp)) {
                return true;
            }
        }
        return false;
    }
}
