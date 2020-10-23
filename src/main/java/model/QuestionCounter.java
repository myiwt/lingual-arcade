/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Megan
 */
public class QuestionCounter {
    private int questionNum;
    
    public QuestionCounter() {
        questionNum = 0;
    }
    
    public void setQuestionNum(int n) {
        questionNum = n;
    }
    
    public int getQuestionNum() {
        return questionNum;
    }
    
    public void increaseCounter() {
        questionNum++;
    }
}
