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
    /**
     * Runs a QuizGame by displaying questions and multi-choice answers, receiving 
     * answers from user input and saving scores onto the ScoreBoard when the QuizGame has ended.
     */
    @Override
    public void runCUIGame() {
        /* If a file path is specified, use the file to define the list of possible
        questions to use to create the QuestionSet */
        if (!this.filePath.equals("")) {
            this.questionSet.setPhrasesFromFile(filePath);
        } else {
            setLanguagesToTestForCUI();
        }
        this.questionSet.generateQuestionSet();

        nQuestions = this.questionSet.getMultiChoiceQuestions().size();
        
        // Iterate through all questions in the Question Set for the game
        for (MultiChoiceQuestion mcq: questionSet.getMultiChoiceQuestions()) {
            System.out.println("Question " + questionCounter + " out of " + nQuestions +":");
            // Display multichoice answers
            System.out.println(mcq.MultiChoiceToString());
            String response = keyboard.nextLine();
            if (mcq.getCorrectAnswerChar().equalsIgnoreCase(response)) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect");
            }
            // Display info for the test phrase, translation and pronounication
            System.out.println(mcq.printCorrectAnswer()+"\n");
            questionCounter++;
        }
        saveScoreForCUI(score);
        System.out.println("Press any key to return to the main menu");
        keyboard.nextLine();
    }
    
    /**
     * Saves the game score to the Scoreboard.
     * @param score integer representing the number of questions answered correctly in the quiz.
     */
    @Override
    protected void saveScoreForCUI(int score) {
        System.out.println("Game ended. Your score is: " + score + " out of " + nQuestions);
        Scoreboard.saveScore("Quiz Game", score);
        System.out.println("This score has been saved to the scoreboard");
    }
    
    /**
     * Sets the languages that will be tested on in a QuizGame derived from user input.
     */
    public void setLanguagesToTestForCUI() {
        boolean languageIsSet = false;
        String languagesChosenMessage = "\nLanguages selected: ";
        System.out.println("Please select the languages to include in this game separated by commas");
        System.out.println("e.g. if you would like to include Spanish and French, please enter 1,2");
        System.out.println("1) Spanish, 2) French, 3) German");
        String response = keyboard.nextLine().trim();
        
        String[] fileLineArr = response.split(",[ ]*");
        ArrayList<String> languageSelection = new ArrayList<>();
        for (String s: fileLineArr) {
            languageSelection.add(s.replace(" ", "").trim());
        }
        
        if (languageSelection.contains("1")) {
            this.questionSet.addLanguagePhrases("spanish");
            languageIsSet = true;
            languagesChosenMessage+="\nSpanish";
        }
       
        if (languageSelection.contains("2")) {
            this.questionSet.addLanguagePhrases("french");
            languageIsSet = true;
            languagesChosenMessage+="\nFrench";
        }
        
        if (languageSelection.contains("3")) {
            this.questionSet.addLanguagePhrases("german");
            languageIsSet = true;
            languagesChosenMessage+="\nGerman";
        }
        
        if (!languageIsSet) {
            languagesChosenMessage = "No valid language selections made - question set is set to default to test all languages";
            this.questionSet.addLanguagePhrases("german");
            this.questionSet.addLanguagePhrases("french");
            this.questionSet.addLanguagePhrases("spanish");
        }
        System.out.println(languagesChosenMessage+"\n");
    }
}
