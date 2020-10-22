/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import languages.Phrase;
import model.QuizGameModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Megan
 */
public class TestQuizAndScoreboard {
    
    private QuizGameModel quizGameModel;
    private QuestionSet questionSet;
    private List<Phrase> allPossibleTestPhrases;
    private String url="jdbc:derby:scoreboard;create=true"; 
    private Statement SQLstatement = null;
    private String username = "pdc";
    private String password = "pdc";
    private Connection conn;
    
    public TestQuizAndScoreboard() {
    }
    
    @Before
    public void setUp() {
        this.quizGameModel = new QuizGameModel();
        this.questionSet = this.quizGameModel.getQuestionSet();
        allPossibleTestPhrases = this.questionSet.getAllPossibleTestPhrases();
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            SQLstatement = conn.createStatement();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Test the correct number of possible phrases are used as possible phrases for quiz selection.
     */
    @Test
    public void testAllPhrasesToTestFrom() {
        System.out.println("Testing all phrases to choose from");
        assertEquals(allPossibleTestPhrases.size(),199);
    }
    
    /**
     * Test all languages are included as possible phrases for quiz selection.
     */
    @Test
    public void testAllLanguagesAreIncludedInQuiz() {
        System.out.println("Checking all languages are included for possible questions in a Quiz");
        HashSet<String> languages = new HashSet();
        for (Phrase phrase: allPossibleTestPhrases) {
            languages.add(phrase.getLanguage());
        }
        assertTrue(languages.contains("French"));
        assertTrue(languages.contains("Spanish"));
        assertTrue(languages.contains("German"));
    }
    
    
    /**
     * Test that all questions in a quiz are unique.
     */
    @Test
    public void testUniqueQuizQuestions() {
        System.out.println("Testing all questions in a Quiz are unique");
        HashSet<String> uniqueQuestions = new HashSet<>();
        for (MultiChoiceQuestion mcq: questionSet.getMultiChoiceQuestions()) {
            Question quizQuestion = mcq.getTestQuestion();
            uniqueQuestions.add(quizQuestion.toString());
        }
        assertEquals(uniqueQuestions.size(),20);
    }
    
            
    /**
     * Tests correct number of questions are generated for the quiz
     */
    @Test
    public void testGenerateQuestionSet() {
        System.out.println("Checking number of questions created for Quiz");
        assertEquals(this.questionSet.getMultiChoiceQuestions().size(), 20);
    }
    
    /**
     * Tests Scoreboard DB columns are correct
     */
    @Test
    public void testScoreboardDB() {
        System.out.println("Checking columns in Scoreboard DB");
        ArrayList<String> expectedColumns = new ArrayList<>();
        expectedColumns.add("DATETIME");
        expectedColumns.add("GAME_TYPE");
        expectedColumns.add("SCORE"); 
        ArrayList<String> columnsRetrieved = new ArrayList<>();
         try {
            ResultSet rs = SQLstatement.executeQuery("SELECT * FROM SCOREBOARD");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnsRetrieved.add(rsmd.getColumnName(i));
            }
        } catch (SQLException ex) {
             System.err.println(ex);
        }
        assertEquals(expectedColumns,columnsRetrieved);
    }
    
    
}
