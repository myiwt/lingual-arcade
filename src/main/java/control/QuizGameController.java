package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import model.Model;
import model.QuizGameModel;
import view.NewGameView;
import view.QuizGameView;

/**
 * This is a controller class that follows the MVC (Model View Controller) design
 * pattern. This controller manages Quiz Games by handling user events in the 
 * GUI in the QuizGameView class and by manipulating the Quiz data in the
 * QuizGameModel class.
 * 
 * @author ghq8692
 */
public class QuizGameController implements ActionListener, ItemListener {
    QuizGameModel quizGameModel;
    QuizGameView quizGameView;
    NewGameView newGameView;
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
    
    public void addNewGameView(NewGameView view) {
        this.newGameView = view;
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

    @Override
    public void itemStateChanged(ItemEvent e) {
        String checkboxName;
        if (e.getSource() instanceof JCheckBox) {
            JCheckBox checkbox = (JCheckBox) e.getSource();
            checkboxName = checkbox.getName();
            if (e.getStateChange() == ItemEvent.SELECTED) {
                quizGameModel.addLanguage(checkboxName);
            }
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                quizGameModel.removeLanguage(checkboxName);
            }
        }
        
    }
}
