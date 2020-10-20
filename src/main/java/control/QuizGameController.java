/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Model;
import model.QuizGameModel;
import view.QuizGameView;

/**
 *
 * @author Megan
 */
public class QuizGameController implements ActionListener{
    QuizGameModel quizGameModel;
    QuizGameView quizGameView;
    Model model;
    
    public QuizGameController() {   
    }
    
    public void addModel(QuizGameModel model) {
        this.quizGameModel = model;
    }

    public void addMainModel(Model model) {
        this.model = model;
    }
    
    public void addView(QuizGameView view) {
        this.quizGameView = view;
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        String answerChar = e.getActionCommand().substring(0, 1);
        quizGameModel.checkAnswer(answerChar);
        if (quizGameModel.getNextQuestion()) {
            model.saveScoreToDB(quizGameModel.getScore(), "Quiz Game");
            quizGameView.showResult();
            model.changeView("Main Menu");
            quizGameModel.setScore(0);
        }
        
    }
    
}
