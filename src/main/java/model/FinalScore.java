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

    @Override
    public int compareTo(FinalScore o) {
        return this.timestamp.compareTo(o.getTimestamp());
    }
    
    
}
