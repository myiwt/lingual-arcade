package game;

import languages.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * COMP603: Program Design and Construction: Assessment 1
 * 1 September 2020
 * Semester 2 2020
 * 
 * QuestionSet objects consist of MultiChoiceQuestions that make up a Quiz
 * Game. 
 * 
 * The questions that make up the QuestionSet are selected at random so 
 * that games are not repetitive. There are two methods for defining the set of
 * questions to be chosen from to build the QuestionSet:
 * 
 * 1) Language selection by user input OR
 * 2) Reading a text file containing a custom list of Phrases to test
 * 
 * @author ghq8692 Megan Teh
 */
public class QuestionSet {
    private List<MultiChoiceQuestion> multiChoiceQuestions;
    private List<Phrase> phraseBank; // The phraseBank represents all phrases available for all languages
    private final List<Phrase> allPossibleTestPhrases; // Represents the phrases available for testing, based on languages selected or phrases derived from file
    private final int nQuestions = 20; // Total number of questions in a quiz
    private final int nMultiChoiceQs = 4; // Total number of multi-choice answers provided per question
    private Scanner scanReader;
    

    public QuestionSet() {
        this.multiChoiceQuestions = new ArrayList<MultiChoiceQuestion>();
        allPossibleTestPhrases = new ArrayList<>();
        phraseBank = new ArrayList<>();
        addAllPhrases();
    }
    
    /**
     * Compiles the phraseBank which represents the Phrases that are used to create
     * 'incorrect' multiple choice answers. The phraseBank will consist of all Phrases
     * available of all languages.
     */
    public void addAllPhrases() {
        phraseBank.addAll(Arrays.asList(Spanish.values()));
        phraseBank.addAll(Arrays.asList(French.values()));
        phraseBank.addAll(Arrays.asList(German.values()));
    }

    /**
     * Reads a text file to derive a custom list of Phrases that are used to create
     * the questions that will be tested in the QuestionSet.
     * 
     * The text file must contain one phrase per line, with the Enum value first 
     * then the language, separated by comma. This method will handle incorrectly
     * formatted data in the text file.
     * @param filePath The file path to the text file input in a String format.
     */
    public void setPhrasesFromFile(String filePath) {
        
        HashSet<Phrase> phraseSet = new HashSet<>();
        
        try {
            scanReader = new Scanner(new FileReader(filePath));
            while (scanReader.hasNextLine()) {
                String fileLineStr = scanReader.nextLine().toUpperCase();
                String[] fileLineArr = fileLineStr.split(",[ ]*");
                String className = "";
                try {
                    className = fileLineArr[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue; // Line is not comma delimited so it is invalid - skip to next line
                }
                
                if (isClass(className)) {
                    String foreignPhrase = fileLineArr[0];
                    if (className.equalsIgnoreCase("SPANISH")) {   
                        try {
                            if (phraseBank.contains(Spanish.valueOf(foreignPhrase))) {
                                phraseSet.add(Spanish.valueOf(foreignPhrase));
                            }
                        } catch (IllegalArgumentException e) {
                            // Invalid phrase, do nothing, do not add to phrase bank
                        }
                    }
                    else if (className.equalsIgnoreCase("FRENCH")) {   
                        try {
                            if (phraseBank.contains(French.valueOf(foreignPhrase))) {
                                phraseSet.add(French.valueOf(foreignPhrase));
                            }
                        } catch (IllegalArgumentException e) {
                            // Invalid phrase, do nothing, do not add to phrase bank
                        }
                    }
                    else if (className.equalsIgnoreCase("GERMAN")) {   
                        try {
                            if (phraseBank.contains(German.valueOf(foreignPhrase))) {
                                phraseSet.add(German.valueOf(foreignPhrase));
                            }
                        } catch (IllegalArgumentException e) {
                            // Invalid phrase, do nothing, do not add to phrase bank
                        }
                    }
                }
            }
            // The phraseBank contains a list of unique Phrases
            this.allPossibleTestPhrases.addAll(phraseSet);
        } catch (FileNotFoundException e) {
            System.err.println("Error: check local file path: " + e);
        } finally {
            scanReader.close();
        }
    }
    
    /**
     * Helper function used to determine whether or not an input String is a valid
     * language available in this application.
     * @param className A String input representing a language class name
     * @return Returns true if the language does exist in the language package and 
     * false if it does not.
     */
    private boolean isClass(String className) {
        try {
            Class.forName("languages." + className.substring(0,1).toUpperCase()+className.substring(1).toLowerCase());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    
    /**
     * Adds Phrases by language to include in the random selection of test questions
     * in the QuestionSet. This is used when the user plays a standard QuizGame 
     * and the user can select the languages that want to be tested on.
     * 
     * @param language The language to add Phrases from in String format
     */
    public void addLanguagePhrases(String language) {
        if (language.equalsIgnoreCase("french")) {
            this.allPossibleTestPhrases.addAll(Arrays.asList(French.values()));
        }
        if (language.equalsIgnoreCase("spanish")) {
            this.allPossibleTestPhrases.addAll(Arrays.asList(Spanish.values()));
        }
        if (language.equalsIgnoreCase("german")) {
            this.allPossibleTestPhrases.addAll(Arrays.asList(German.values()));
        }
    }
    
    /**
     * Creates the QuestionSet object including all randomly selected questions 
     * and multiple choice answers so that it is ready to be used for a QuizGame.
     */
    public void generateQuestionSet() {
        addLanguagePhrases("french");
        addLanguagePhrases("spanish");
        addLanguagePhrases("german");
        generateTestQuestions();
        // For each test question, for each multiChoice set, randomly select the
        // 'incorrect' answers from all phrases (not limited to just the phrase bank)
        Iterator<MultiChoiceQuestion> mcqIter = this.multiChoiceQuestions.iterator();
        while(mcqIter.hasNext()) {
            Collections.shuffle(allPossibleTestPhrases);
            MultiChoiceQuestion multiChoiceQuestion = mcqIter.next();
            
            Question testQuestion = multiChoiceQuestion.getTestQuestion();
            Iterator<Phrase> allPhrasesIter = allPossibleTestPhrases.iterator();
            
            // Add the 'incorrect' multichoice question-answers
            while (multiChoiceQuestion.getQuestions().size() < nMultiChoiceQs) {
                Phrase nextPhrase = allPhrasesIter.next();
                // Only add 'incorrect' choices if they are not identical to the 'correct' test question
                if (!nextPhrase.equals(testQuestion.getPhrase())) {
                    multiChoiceQuestion.addQuestion(new Question(nextPhrase,false));
                }
            }
            // Shuffle the populated multichoice questions so that they are presented at random
            Collections.shuffle(multiChoiceQuestion.getQuestions());
            // Set the correct multichoice answer char/letter
            multiChoiceQuestion.setCorrectAnswerChar();
        }
    }
    
    /**
    * Helper function that creates all the test questions only for a QuestionSet
    * (by random selection). 
    */
    private void generateTestQuestions() {
        // Shuffle phraseBank to randomise question order
        Collections.shuffle(this.allPossibleTestPhrases);
        
        // A game must have 20 questions. If the phraseBank has fewer than 20 phrases, 
        // these phrases will be repeated until the quiz contains 20 questions. 
        // If the phraseBank has greater than 20 Phrases then only 20 Phrases will be
        // randomly selected from the phraseBank
        int questionCounter = 0;
        while (questionCounter < nQuestions ) {
            for (Phrase phrase: this.allPossibleTestPhrases) {
                MultiChoiceQuestion multiChoiceQuestion = this.generateNewMultiChoiceQ(phrase);
                this.multiChoiceQuestions.add(multiChoiceQuestion);
                questionCounter++;
                if (questionCounter == nQuestions) {
                    break;
                }   
            }
        }
    }
    
    /**
     * Helper function that generates a MultiChoiceQuestion and sets the 'correct'
     * test question from the input Phrase. It returns a new MultiChoiceQuestion
     * with the 'correct' test question set and does not set the 'incorrect'
     * answers, then adds this MultiChoiceQuestion to the QuestionSet.
     * 
     * @param testPhrase Phrase value representing the Phrase used for the
     * test Question for a MultiChoiceQuestion object
     * @return Returns a MultiChoiceQuestion object with the test question defined.
     */
    private MultiChoiceQuestion generateNewMultiChoiceQ(Phrase testPhrase) {
        MultiChoiceQuestion multiChoiceQuestion = new MultiChoiceQuestion();
        // Sets boolean variable true to represent that this it the correct question-answer pair
        multiChoiceQuestion.addQuestion(new Question(testPhrase, true));
        return multiChoiceQuestion;
    }
    
    /**
     * Returns a list of Strings which represent the printed form of all MultiChoiceQuestions
     * in a QuestionSet. This is used for display in the CUI when running a QuizGame. Each
     * String element in the list displays both the question and multi-choice answers for
     * one MultiChoiceQuestion object.
     * 
     * @return A List of String values represent the printed form of all MultiChoiceQuestions
     * in a QuestionSet. This is used for display in the CUI when running a Game. Each
     * String element in the list displays the question and multi-choice answers for
     * one MultiChoiceQuestion object.
     */
    public List<String> generateQuestionSetDisplay() {
        List<String> questionStringList = new ArrayList<>();
        
        this.multiChoiceQuestions.forEach((m) -> {
            questionStringList.add(m.printQuestion()+"\n"+m.printAnswers());
        });
        return questionStringList;
    }
    
    /**
     * Returns the list of all MultiChoiceQuestion objects belonging to a 
     * QuestionSet.
     * @return Returns the list of all MultiChoiceQuestion objects belonging to a 
     * QuestionSet
     */
    public List<MultiChoiceQuestion> getMultiChoiceQuestions() {
        return multiChoiceQuestions;
    }
    
}