package model;

import game.MultiChoiceQuestion;
import game.QuestionSet;
import game.QuizGame;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author ghq8692 Megan Teh
 */
public class QuizGameModel extends Observable implements GameInterface {
    
    public static QuizGame quizGame;
    private QuestionSet questionSet;
    private Iterator mcqIter;
    private MultiChoiceQuestion nextQuestion;
    private int questionCounter, maxQuestions;
    private int score;
    private final String filePath = "customgame.txt";
    private String answerA, answerB, answerC, answerD;
    

    public QuizGameModel() {
        this.score = 0;
        this.questionCounter = 0;
        this.questionSet = new QuestionSet();
        this.questionSet.addLanguagePhrases("Spanish");
        this.questionSet.addLanguagePhrases("German");
        this.questionSet.addLanguagePhrases("French");
        setupGame();
    }
    
    private void setupGame() {
        this.questionSet.generateQuestionSet();
        this.maxQuestions = questionSet.getMultiChoiceQuestions().size();
        List<MultiChoiceQuestion> multiChoiceQuestions = this.questionSet.getMultiChoiceQuestions();
        this.mcqIter = multiChoiceQuestions.iterator();
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
            questionCounter++;
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
        if (answer.equalsIgnoreCase(nextQuestion.getCorrectAnswerChar())) {
            score++;
            setChanged();
            notifyObservers(score);
        } 
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

    @Override
    public boolean getInProgress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setInProgress(boolean inProgress) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveScoreToDB(int score) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
