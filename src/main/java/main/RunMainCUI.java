package main;

import game.CardMatchingGame;
import game.QuizGame;
import java.util.Scanner;

/**
 * COMP603: Program Design and Construction: Assessment 1
 * 1 September 2020
 * Semester 2 2020
 * 
 * This is the main method for running this CUI application.
 * This application is a language learning software it includes games to help
 * users learn words and phrases in different languages.
 * 
 * Features of this application:
 * 
 * - Includes two games: a multi-choice quiz and a card matching game.
 * - Users can choose which languages to be tested on when playing a Quiz Game.
 * - A custom quiz can be built by providing phrases from a text file.
 * - Language phrases are always randomised for each game so that gameplay is not
 *   repetitive.
 * - Includes a game description to provide instructions on how to play the games.
 * - Stores past score history from previous games played so that it can be viewed.
 *
 * @author ghq8692 Megan Teh
 */
public class RunMainCUI {

    public static Scanner keyboard;
    private static Scoreboard scoreboard;
    private static boolean run;
    private static QuizGame questionnaireGame;
    private static CardMatchingGame cardMatchingGame;
    
    /**
     * Main CUI object instantiates a Scoreboard object to track scores and a 
     * Scanner object to process user input.
     */
    public RunMainCUI() {
        System.out.println("Welcome to Lingual Arcade! (GUI version coming soon)");
        keyboard = new Scanner(System.in);
        scoreboard = new Scoreboard();
        run = true;
    }

    /**
     * The main menu for the CUI
     */
    public static void mainMenu() {
        System.out.println("\nMain menu\n\n"
                + "1) New Game\n"
                + "2) About this game\n"
                + "3) View scoreboard\n"
                + "4) Quit\n\n"
                + "Please type in a menu option number and press Enter:");
        String userInput = keyboard.nextLine();
        switch (userInput) {
            case "1":
                gameSetup();
                break;
            case "2":
                gameDescription();
                break;
            case "3":
                viewScoreBoard();
                break;
            case "4":
                run = false;
                break;
            default:
                System.out.println("\nInvalid input, please try again");
                break;
        }
    }

    /**
     * The questionnaireGame menu for the CUI. Users can select whether to play a 
     * standard quiz or a custom quiz. In a standard quiz the user selects 
     * which languages to test on from which the quiz questions will be chosen from. 
     * In a custom quiz, the specific phrases to use for questions are defined in a
     * text file.
     */
    public static void gameSetup() {
        boolean gameSetupRun = true;
        String gameSetupMessage = "Select game type:\n"
                + "1) Standard quiz game\n"
                + "2) Custom quiz game (load from text file)\n"
                + "3) Card matching game\n"
                + "4) Go back to main menu\n"
                + "Please type in a menu option number and press Enter:";
        System.out.println(gameSetupMessage);

        
        while (gameSetupRun) {
            String gameSelection = keyboard.nextLine();
            if (gameSelection.equals("1")) {
                questionnaireGame = new QuizGame(keyboard);
                System.out.println("Starting a new standard game...\n");
                questionnaireGame.runCUIGame();
                gameSetupRun = false;
            } else if (gameSelection.equals("2")) {
                System.out.println("Load game from file");
                questionnaireGame = new QuizGame(keyboard,"customgame.txt");
                questionnaireGame.runCUIGame();
                gameSetupRun = false;
            } else if (gameSelection.equals("3")) {
                cardMatchingGame = new CardMatchingGame(keyboard);
                cardMatchingGame.runCUIGame();
                gameSetupRun = false;
            } else if (gameSelection.equals("4")) {
                gameSetupRun = false;
            } else {
                System.out.println("Invalid input, try again\n");
                System.out.println(gameSetupMessage);
            }
        }
        mainMenu();
        }
    
    /**
     * Displays game information and instructions for the user.
     */
    public static void gameDescription() {
        System.out.println
                ( "This application contains two minigames: \n\n"
                + "1) QUIZ GAME\n\n"
                + "This is a game that helps you to learn basic phrases in different\n"
                + "languages. The game is a quiz with 20 questions and the user can select which.\n"
                + "languages to test. Type in the multichoice letters and press enter to answer the\n"
                + "questions. You can create a custom quiz to focus your learning on selected phrases\n"
                + "by providing a text file with the English phrases to choose the test questions.\n\n\n"
                        
                + "2) CARD MATCHING GAME\n\n"
                + "Match the foreign phrase to the English translations. Flip the cards by entering\n"
                + "the card grid location when prompted in the CUI (e.g. A2 or D4). The fewer matches\n"
                + "made the higher the score. Cards that have not been flipped are displayed as: [o].\n"
                + "Cards that have already been flipped/matched are displayed as: [x]\n\n"
                        
                + "Press any key to return to the main menu.");
        keyboard.nextLine();
    }
    
    /**
     * Displays the score history, showing the type of game played, the date/time the
     * game was played and the score received.
     */
    public static void viewScoreBoard() {
        System.out.println(scoreboard);
        System.out.println("Press any key to return to the main menu.");
        keyboard.nextLine();
    }

    /**
     * This is the main method that executes the application
     */
    public static void main(String[] args) {
        RunMainCUI runMain = new RunMainCUI();
        while (run) {
            mainMenu();
        }

        System.out.println("Program exited.");
        System.exit(0);
    }
}
