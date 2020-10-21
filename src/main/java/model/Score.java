/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Megan
 */
public class Score implements Comparable<Score> {
    private Timestamp timestamp;
    private int score;
    private String gameType;
    
    public Score(Timestamp timestamp, int score, String gameType) {
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
    public int compareTo(Score o) {
        return this.timestamp.compareTo(o.getTimestamp());
    }
    
    
}
