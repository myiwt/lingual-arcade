package game;

import languages.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
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
 * @author ghq8692
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
        this.multiChoiceQuestions = new ArrayList<MultiChoiceQuestion>();
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
     * Returns the list of all MultiChoiceQuestion objects belonging to a 
     * QuestionSet.
     * @return Returns the list of all MultiChoiceQuestion objects belonging to a 
     * QuestionSet
     */
    public List<MultiChoiceQuestion> getMultiChoiceQuestions() {
        return multiChoiceQuestions;
    }
    
    public List<Phrase> getAllPossibleTestPhrases() {
        return allPossibleTestPhrases;
    }
}