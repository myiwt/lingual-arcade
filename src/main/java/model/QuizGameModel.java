package model;

import game.MultiChoiceQuestion;
import game.QuestionSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 * This is a model class that follows the MVC (Model View Controller) design
 * pattern. A QuizGameModel holds data for a Quiz Game, which consists of
 * the quiz multiple choice questions, current game score, question counter,
 * languages selected for the quiz game and answer selection.
 * 
 * The QuizGameModel produces 20 random questions from a selection of languages
 * as selected by the user in the GUI. This class also has a method to check if
 * the user has given the correct answer to a question and increases the score
 * accordingly.
 * 
 * @author ghq8692
 */
public class QuizGameModel extends Observable implements GameInterface {
    
    private QuestionSet questionSet;
    private Iterator mcqIter;
    private MultiChoiceQuestion nextQuestion;
    private int questionCounter, maxQuestions;
    private int score;
    private String answerA, answerB, answerC, answerD;
    private ArrayList<String> languagesSelected;
    

    public QuizGameModel() {
        this.score = 0;
        this.questionCounter = 1;
        this.questionSet = new QuestionSet();
        this.languagesSelected = new ArrayList<>();
        languagesSelected.add("German");
        languagesSelected.add("Spanish");
        languagesSelected.add("French");
    }
    
    public void setupGame() {
        for (String language: languagesSelected) {
            this.questionSet.addLanguagePhrases(language);
        }
        this.questionSet.generateQuestionSet();
        this.maxQuestions = questionSet.getMultiChoiceQuestions().size();
        List<MultiChoiceQuestion> multiChoiceQuestions = this.questionSet.getMultiChoiceQuestions();
        this.mcqIter = multiChoiceQuestions.iterator();
    }
    
    public ArrayList<String> getLanguagesSelected() {
        return this.languagesSelected;
    }
    
    public void stopQuiz() {
        setChanged();
        notifyObservers("Quit to Main Menu");
    }
    
    public void continueQuiz() {
        setChanged();
        notifyObservers("Continue quiz");
    }
    
    public void openQuitQuizDialog() {
        setChanged();
        notifyObservers("Open quit quiz dialog");
    }
    
    public boolean getNextQuestion() {
        boolean reachedEnd = false;
        if (questionCounter >= maxQuestions) {
            reachedEnd = true;
            return reachedEnd;
        }
        nextQuestion = null;
        if (this.mcqIter.hasNext()) {
            nextQuestion = (MultiChoiceQuestion) this.mcqIter.next();
            String questionString = nextQuestion.getTestQuestion().toString();
            answerA = "A: " + nextQuestion.getQuestions().get(0).getPhrase().getForeignPhrase();
            answerB = "B: " + nextQuestion.getQuestions().get(1).getPhrase().getForeignPhrase();
            answerC = "C: " + nextQuestion.getQuestions().get(2).getPhrase().getForeignPhrase();
            answerD = "D: " + nextQuestion.getQuestions().get(3).getPhrase().getForeignPhrase();
            String[] answers = new String[] {questionString, String.valueOf(questionCounter), answerA, answerB, answerC, answerD};
            setChanged();
            notifyObservers(answers);
        }
        return reachedEnd;
    }
    
    public void checkAnswer(String answer) {
        questionCounter++;
        if (answer.equalsIgnoreCase(nextQuestion.getCorrectAnswerChar())) {
            score++;
            updateScoreAndQuestionCounter();
        } 
    }
    
    private void updateScoreAndQuestionCounter() {
        int[] scoreAndQuestionCounter = {score, questionCounter};
        setChanged();
        notifyObservers(scoreAndQuestionCounter);
    }
    
    public void addLanguage(String language) {
        languagesSelected.add(language);
    }
    
    public void removeLanguage(String language) {
        languagesSelected.remove(language);
    }
    
    public void resetQuizGame() {
        this.score = 0;
        this.questionSet = new QuestionSet();
        this.questionCounter = 1;
        updateScoreAndQuestionCounter();
    }
    
    @Override
    public void runGame() {
        setupGame();
        getNextQuestion();
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }
    
    public QuestionSet getQuestionSet() {
        return questionSet;
    }
}
