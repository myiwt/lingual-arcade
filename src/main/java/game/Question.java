package game;

import languages.Phrase;

/**
 * COMP603: Program Design and Construction: Assessment 1
 * 1 September 2020
 * Semester 2 2020
 * 
 * This class manages Question objects which contain a Phrase representing the
 * question/answer pair information. 4 Question objects make up a MultiChoiceQuestion
 * (with 1 'correct' answer and 3 'incorrect' answers).
 * 
 * @author ghq8692 Megan Teh
 */
public class Question {
    private Phrase phrase;
    private boolean isTestQuestion;
    
    /**
     * Question objects are instantiated with the question content (foreign phrase
     * and English translation) and a boolean flag. Questions are used to build 
     * MultiChoiceQuestions and the boolean flag indicates whether the question 
     * is the 'correct' question/answer pair or an 'incorrect' question/answer pair,
     * within the MultiChoiceQuestion.
     *    
     * @param phrase A Phrase value containing the foreign phrase, English 
     * translation and pronounication information.
     * @param isTestQuestion A boolean flag indicating whether the question is 
     * the 'correct' test question for a MultiChoiceQuestion object or not (i.e.
     * an 'incorrect' multi-choice answer).
     */
    public Question(Phrase phrase, boolean isTestQuestion) {
        this.phrase = phrase;
        this.isTestQuestion = isTestQuestion;
    }
    
    /**
     * Returns a String representation of the question and is used for display in
     * the CUI when a Game is running.
     * @return Returns a String representation of the question and is used for
     * display in the CUI when a Game is running.
     */
    @Override
    public String toString() {
        String questionMessage = String.format("<html>How do you say this in %s? <br/>\"%s\"</html>", 
                this.phrase.getClass().getSimpleName(),this.getPhrase());
        return questionMessage;
    }
    
    /**
     * Returns the Phrase enum value belonging to a Question object.
     * @return Returns the Phrase enum value belonging to a Question object.
     */
    public Phrase getPhrase() {
        return phrase;
    }

    /**
     * Sets the Phrase enum value for a Question object.
     * @param phrase Sets the Phrase enum value for a Question object.
     */
    public void setPhrase(Phrase phrase) {
        this.phrase = phrase;
    }

    /**
     * Returns a boolean value indicating whether the Question object is the 
     * 'correct' test question or not. If this value is false, this means that this
     * object is meant to be an 'incorrect' multiple choice answer.
     * @return Returns a boolean value indicating whether the Question object is the 
     * 'correct' test question or not.
     */
    public boolean getIsTestQuestion() {
        return isTestQuestion;
    }

    /**
     * If this value is set to True, it indicates that the Question object is the 
     * 'correct' test question. If this value is set to false, this means that
     * this object is meant to be an 'incorrect' multiple choice answer.
     * @param isTestQuestion A boolean value indicating whether the Question object is the 
     * 'correct' test question or not.
     */
    public void setIsTestQuestion(boolean isTestQuestion) {
        this.isTestQuestion = isTestQuestion;
    }

}
