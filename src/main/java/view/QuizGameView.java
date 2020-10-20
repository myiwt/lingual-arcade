package view;

import control.Controller;
import control.QuizGameController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author ghq8692 Megan Teh
 */
public class QuizGameView extends JPanel implements Observer {
    private JLabel title, questionText, questionNumber, scoreLabel;
    private JButton buttonA, buttonB, buttonC, buttonD;
    private JButton[] multiChoiceButtons;
    private JButton mainMenuButton;
    private JPanel quizPanel;
    
    
    public QuizGameView() {
        super(new BorderLayout());
        title = new JLabel("Quiz Game", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        questionText = new JLabel();
        
        quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        
        scoreLabel = new JLabel("Score: 0 out of 20", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        questionNumber = new JLabel("Question 1 out of 20"); 
        questionNumber.setFont(new Font("Dialog", Font.PLAIN, 13));
        questionText = new JLabel();
        questionText.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        buttonA = new JButton();
        buttonB = new JButton();
        buttonC = new JButton();
        buttonD = new JButton();
        
        Dimension buttonDimension = new Dimension(300,30);
        Dimension fillerDimension = new Dimension(5, 10); // filler space between components in the GUI
            
        multiChoiceButtons = new JButton[] {buttonA, buttonB, buttonC, buttonD};
        mainMenuButton = new JButton("Main Menu");
        
        quizPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        quizPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        quizPanel.add(scoreLabel);
        quizPanel.add(questionNumber);
        quizPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        quizPanel.add(questionText);
        quizPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        quizPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        
        
        for (JButton b: multiChoiceButtons) {
            b.setFocusable(false);
            b.setMaximumSize(buttonDimension);
            quizPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
            quizPanel.add(b);
        }
        add(title, BorderLayout.NORTH);
        add(quizPanel, BorderLayout.CENTER);
        add(mainMenuButton, BorderLayout.SOUTH);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String[]) {
            String[] updatedQuestionInfo = (String[]) arg;
            questionText.setText(updatedQuestionInfo[0]);
            questionNumber.setText("Question " + updatedQuestionInfo[1] + " out of 20");
            buttonA.setText(updatedQuestionInfo[2]);
            buttonB.setText(updatedQuestionInfo[3]);
            buttonC.setText(updatedQuestionInfo[4]);
            buttonD.setText(updatedQuestionInfo[5]);
        } else if (arg instanceof Integer) {
            scoreLabel.setText("Score: " + arg +" out of 20");
        }
    }

    public void addController(QuizGameController controller) {
        buttonA.addActionListener(controller);
        buttonB.addActionListener(controller);
        buttonC.addActionListener(controller);
        buttonD.addActionListener(controller);
    }
    
    public void addMainController(Controller controller) {
        mainMenuButton.addActionListener(controller);
    }
    
    public void showResult() {
        String message = "Game ended. " + scoreLabel.getText() + ". Saved to database.";
        JOptionPane.showMessageDialog(this, message, "Game finished", JOptionPane.OK_OPTION);
    }
}
