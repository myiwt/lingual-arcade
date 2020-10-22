package main;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import control.Controller;
import control.QuizGameController;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Model;
import model.QuizGameModel;
import view.MainView;
import view.NewGameView;
import view.QuizGameView;
import view.ScoreboardView;

/**
 * This is the main method to execute the GUI. This application follows the MVC
 * design pattern (Model View Controller). For the main method setup, instances
 * of the model, view and controller classes are created and the Observers are 
 * added to the Observables so that notifications so that the MVC classes can
 * send and receive updates as required.
 * 
 * This application is called Lingual Arcade and it is a quiz game where you are
 * given a phrase in a foreign language and the user must guess the correct 
 * translation. The user can select the languages to be tested on and a random 
 * multi-choice quiz is generated each time a game is played. Scores are stored
 * in an embedded Derby database and the user can view the score history table.
 * The GUI also includes descriptions that provides game instructions for users.
 * 
 * @author ghq8692
 */
public class RunMainGUI {
    private static Model mainModel;
    private static MainView mainView;
    private static Controller mainController;
    private static QuizGameView quizGameView;
    private static QuizGameController quizGameController;
    private static NewGameView newGameView;
    private static ScoreboardView scoreboardView;
    
    public RunMainGUI()  {
    }
    
    private static void setup() {
        // create instances of quiz model, view and controller
        QuizGameModel quizGameModel = new QuizGameModel();
        quizGameView = new QuizGameView();
        scoreboardView = new ScoreboardView();
        newGameView = new NewGameView();
        quizGameController = new QuizGameController();
        // create instances of main model, view and controller
        mainModel = new Model();
        mainView = new MainView(quizGameView, scoreboardView, newGameView);
        mainController = new Controller();
        // pass the reference of model and view to the controller
        mainModel.addObserver(mainView);
        mainModel.addObserver(scoreboardView);
        mainController.addModel(mainModel);
        mainController.addView(mainView);
        mainView.addController(mainController);
        // pass the reference of model and view to the controller
        quizGameModel.addObserver(quizGameView);
        quizGameController.addModel(quizGameModel);
        quizGameController.addNewGameView(newGameView);
        quizGameController.addMainModel(mainModel);
        mainController.addQuizModel(quizGameModel);
        quizGameController.addView(quizGameView);
        quizGameView.addController(quizGameController);
        newGameView.addController(mainController);
        newGameView.addQuizGameController(quizGameController);
    }
    
    public static void main(String[] args) {
        try {
            FlatCyanLightIJTheme.install();
            UIManager.setLookAndFeel(new FlatCyanLightIJTheme());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println( "Failed to initialize Look and Feel - check dependencies are properly built" );
            System.err.println(e);
        }
        RunMainGUI.setup();
        JFrame frame = new JFrame("Lingual Arcade");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainView);
        frame.setSize(500, 500);
        frame.pack();
        frame.setVisible(true);
    }
}
