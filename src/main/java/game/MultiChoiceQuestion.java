package game;

import languages.Phrase;
import languages.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * COMP603: Program Design and Construction: Assessment 1
 * 1 September 2020
 * Semester 2 2020
 * 
 * MultiChoiceQuestion objects consist of one (correct) Question which represents
 * the test question, and three Questions representing the incorrect multi-choice
 * answers. MultiChoiceQuestions are used to build QuestionSets to make up the full
 * set of MultiChoiceQuestions that are used in a QuizGame.
 * 
 * @author ghq8692 Megan Teh
 */
public class MultiChoiceQuestion {
    private List<Question> questions;
    private Question testQuestion;
    private String correctAnswerChar;
    private static List<Phrase> allPhrases = new ArrayList<>();
    private final int nMultiChoiceAnswers = 4;

    /**
     * When a MultiChoiceQuestion is initialized all phrases from all languages
     * are added to allPhrases. allPhrases is used to build the 'incorrect' answers 
     * that make up a MultiChoiceQuestion.
     */
    public MultiChoiceQuestion() {
        this.questions = new ArrayList<>();
        allPhrases.addAll(Spanish.getAllPhrases());
        allPhrases.addAll(French.getAllPhrases());
        allPhrases.addAll(German.getAllPhrases());
    }
    
    /**
     * Returns the text representation of the MultiChoiceQuestion for displaying
     * in the CUI.
     * @return The String format representation of the question and multi-choice
     * answers.
     */
    public String MultiChoiceToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.printQuestion());
        sb.append("\n");
        sb.append(this.printAnswers());
        return sb.toString();
    }
    
    /**
     * Returns the text representation of the test question for a MultiChoiceQuestion.
     * Used to display the test question in the CUI when running a QuizGame.
     * @return The String format representation of the test question of a 
     * MultiChoiceQuestion object.
     */
    public String printQuestion() {
        return this.testQuestion.toString();
    }
    
    /**
     * Returns the text representation of the multiple choice answers in a 
     * MultiChoiceQuestion. Used to display the answers in the CUI when a QuizGame is
     * run. Each multiple-choice answer is labelled alphabetically and the correct
     * answer is stored into the correctAnswerChar attribute for this MultiChoiceQuestion 
     * object.
     * @return The String format representation of the multiple choice answers for a 
     * MultiChoiceQuestion object.
     */
    public String printAnswers() {
        String answers = "";
        int choice = 65;
        for (Question q: this.questions) {
            String choiceChar = String.valueOf((char) choice);
            answers += choiceChar +") " + q.getPhrase().getForeignPhrase() +"\n";
            if (q.getIsTestQuestion()) {
                this.correctAnswerChar = choiceChar;
            }
            choice++;
        }
        return answers;
    }
    
    /**
     * Returns the text representation of the correct answer belonging to a 
     * MultiChoiceQuestion. It is used for displaying in the CUI when a QuizGame is run
     * to educate the user with more information about the phrase after the user has 
     * answered the MultiChoiceQuestion. This additional information includes the 
     * pronounication of the Phrase and also its correct English translation.
     * @return The String format representation of the correct answer to a 
     * MultiChoiceQuestion object.
     */
    public String printCorrectAnswer() {
        Phrase testPhrase = this.testQuestion.getPhrase();
        String correctAnswerStr = String.format("\"%s\" in %s is \"%s\"\n(pronounication: \"%s\")",
                testPhrase.toString(), testPhrase.getLanguage(), testPhrase.getForeignPhrase(), testPhrase.getPronounciation());
        return correctAnswerStr;
    }
    
    /**
     * Adds a new Question to the MultiChoiceQuestion object. If this Question
     * represents the 'correct' test question, a boolean flag will be set to indicate
     * this.
     * @param question Question to be added to the list of Questions in the 
     * MultiChoiceQuestion object.
     */
    public void addQuestion(Question question) {
        this.questions.add(question);
        if (question.getIsTestQuestion()) {
            this.testQuestion = question;
        }
    }
    
    /**
     * Builds the MultiChoiceQuestion object by randomly selecting incorrect 
     * multiple choice answers from all available Phrases in all languages. 
     * All answers are shuffled to randomise the order they appear in when 
     * displayed (i.e. the A,B,C or D order).
     */
    public void setIncorrectAnswers() {
        Collections.shuffle(allPhrases);
        while (this.questions.size() < nMultiChoiceAnswers) {
            for (Phrase phrase: allPhrases) {
                if (!this.testQuestion.getPhrase().equals(phrase)) {
                    this.addQuestion(new Question(phrase,false));
                }
            }
        }
    }
    
    /**
     * Returns a list of Question objects in a MultiChoiceQuestion (which consist
     * of the one test question and the multiple 'incorrect' answers).
     * @return Returns a list of Question objects stored in the MultiChoiceQuestion
     * object.
     */
    public List<Question> getQuestions() {
        return questions;
    }
    
    /**
     * Sets the list of Questions in a MultiChoiceQuestion. A boolean flag is used
     * to discern the correct test question from the incorrect multiple choice answers.
     * @param questions An ArrayList of Question objects.
     */
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
        for (Question q: questions) {
            if (q.getIsTestQuestion()) {
                this.testQuestion = q;
            }
        }
    }

    /**
     * Returns the test question of a MutliChoiceQuestion object.
     * @return Returns the Question object to be tested for this MultiChoiceQuestion object.
     */
    public Question getTestQuestion() {
        return this.testQuestion;
    }
    
    /**
     * Adds the test question to a MultiChoiceQuestion. A boolean flag is used to
     * identify the question as the test question in a MultiChoiceQuestion.
     */
    public void setTestQuestion(Question testQuestion) {
        testQuestion.setIsTestQuestion(true);
        this.testQuestion = testQuestion;
    }
    
    /**
     * Returns the letter representing the correct answer to the test question 
     * for a MultiChoiceQuestion (i.e. A, B, C or D).
     * @return Returns the letter representing the correct answer i.e. A, B, C or D.
     */
    public String getCorrectAnswerChar() {
        return correctAnswerChar;
    }

    /**
     * Sets the letter representing the correct answer to the testQuestion for a
     * MultiChoiceQuestion (i.e. A, B, C or D).
     */
    public void setCorrectAnswerChar() {
        int choice = 65; // Ascii value for "A"
        for (Question q: this.questions) {
            if (q.getIsTestQuestion()) {
                this.correctAnswerChar = String.valueOf((char)choice);
                break;
                }
                // Iterate through the alphabetical letters via ascii integer values
                choice++; 
        }
    }
}