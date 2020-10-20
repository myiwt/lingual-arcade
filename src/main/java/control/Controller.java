package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Model;
import model.QuizGameModel;
import view.MainView;

/**
 *
 * @author ghq8692 Megan Teh
 */
public class Controller implements ActionListener{
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
            quizGameModel.getNextQuestion();
        }
        else {
            model.changeView(actionCommand);
        }
    }
    
    public void addView(MainView view) {
        this.view = view;
    }

    
}
