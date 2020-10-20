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
import view.QuizGameView;

/**
 *
 * @author ghq8692 Megan Teh
 */
public class RunMainGUI {
    private static Model mainModel;
    private static MainView mainView;
    private static Controller mainController;
    private static QuizGameView quizGameView;
    private static QuizGameController quizGameController;
    
    public RunMainGUI()  {
    }
    
    private static void setup() {
        // create instances of quiz model, view and controller
        QuizGameModel quizGameModel = new QuizGameModel();
        quizGameView = new QuizGameView();
        quizGameController = new QuizGameController();
        // create instances of main model, view and controller
        mainModel = new Model();
        mainView = new MainView(quizGameView);
        mainController = new Controller();
        // pass the reference of model and view to the controller
        mainModel.addObserver(mainView);
        mainController.addModel(mainModel);
        mainController.addView(mainView);
        mainView.addController(mainController);
        // pass the reference of model and view to the controller
        quizGameModel.addObserver(quizGameView);
        quizGameController.addModel(quizGameModel);
        quizGameController.addMainModel(mainModel);
        mainController.addQuizModel(quizGameModel);
        quizGameController.addView(quizGameView);
        quizGameView.addController(quizGameController);
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
