package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Model;
import model.QuizGameModel;
import view.MainView;

/**
 * This is a controller class that follows the MVC (Model View Controller) design
 * pattern. This controller manages the GUI by handling user events in the 
 * MainView class by manipulating the data in the Model class. This class handles 
 * the updating and setup of Model data and handles the GUI view updates.
 * 
 * @author ghq8692
 */
public class Controller implements ActionListener {
    Model model;
    MainView view;
    QuizGameModel quizGameModel;

    public Controller() {
    }
    
    public void addModel(Model model) {
        this.model = model;
    }
    
    public void addQuizModel(QuizGameModel quizGameModel) {
        this.quizGameModel = quizGameModel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Quit")) {
            System.exit(0);
        }
        else if (actionCommand.equals("Quiz Game")) {
            model.changeView(actionCommand);
            quizGameModel.setupGame();
            quizGameModel.getNextQuestion();
        }
        else if (actionCommand.equals("Scoreboard")) {
            model.changeView(actionCommand);
            model.updateScoreboard();
        }
        else if (actionCommand.equals("Return to Main Menu")) {
            quizGameModel.openQuitQuizDialog();
        }
        else if (actionCommand.equals("Continue quiz")) {
            quizGameModel.continueQuiz();
        }
        else if (actionCommand.equals("Quit to Main Menu")) {
            quizGameModel.stopQuiz();
            model.changeView("Main Menu");
            quizGameModel.resetQuizGame();
        }
        else {
            model.changeView(actionCommand);
        }
    }
    
    public void addView(MainView view) {
        this.view = view;
    }
}
