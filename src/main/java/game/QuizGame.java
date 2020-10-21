package game;

import java.util.*;
import main.Scoreboard;

/**
 * COMP603: Program Design and Construction: Assessment 1
 * 1 September 2020
 * Semester 2 2020
 * 
 * A QuizGame object is used to run a Quiz Game in the application.
 * This class uses interactive input to manage the game. 
 * After the user answers each question, the game notifies the user if the answer
 * was correct or not. When a QuizGame ends the score is saved into the ScoreBoard.
 * 
 * @author ghq8692 Megan Teh
 */
public class QuizGame extends Game {
    
    private QuestionSet questionSet;
    private int questionCounter = 1;
    private String filePath = "";
    private int nQuestions;
    
    /**
     * This (overloaded) constructor is used to set up a custom game by reading a
     * text file.
     * 
     * @param keyboard Scanner object used for the main CUI
     * @param filePath String path to text file containing Phrases and languages
     */
    public QuizGame(Scanner keyboard, String filePath) {
        super();
        this.questionSet = new QuestionSet();
        this.keyboard = keyboard;
        this.filePath = filePath;
    }
    
    /**
     * This (overloaded) constructor is used to set up a standard game (it does
     * not require a text file path)
     * @param keyboard Scanner object used for the main CUI
     */
    public QuizGame(Scanner keyboard) {
        super();
        this.questionSet = new QuestionSet();
        this.keyboard = keyboard;
    }
    
    // Constructor for GUI (standard game, no file) - no keyboard scanner for command line required
    public QuizGame() {
        super();
        this.questionSet = new QuestionSet();
    }
    
    // Constructor for GUI + custom file - no keyboard scanner for command line required
    public QuizGame(String filePath) {
        super();
        this.questionSet = new QuestionSet();
        this.filePath = filePath;
    }
    
    public void runGUIGame() {
        this.questionSet.addLanguagePhrases("Spanish");
        this.questionSet.addLanguagePhrases("German");
        this.questionSet.addLanguagePhrases("French");
        this.questionSet.generateQuestionSet();
        nQuestions = this.questionSet.getMultiChoiceQuestions().size();
        
    }
}
