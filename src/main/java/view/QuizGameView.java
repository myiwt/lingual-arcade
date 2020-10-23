package view;

import control.Controller;
import control.QuizGameController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * This is a view class that follows the MVC (Model View Controller) design
 * pattern. The QuizGameView displays a question for a quiz and the 4 multi-choice
 * answers. It receives question information from the QuizGameModel class, which is
 * managed by the QuizGameController class.
 * 
 * @author ghq8692
 */
public class QuizGameView extends JPanel implements Observer {
    private JLabel title, questionLabel, questionNumber, scoreLabel, quitQuizMessage;
    private JButton buttonA, buttonB, buttonC, buttonD, mainMenuOption, continueQuizOption;
    private JButton[] multiChoiceButtons;
    private JButton mainMenuButton;
    private JPanel quizPanel;
    private JDialog quitQuizDialog;
    
    
    public QuizGameView() {
        super(new BorderLayout());
        title = new JLabel("Quiz Game", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        questionLabel = new JLabel();
        
        quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        quizPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel = new JLabel("Score: 0 out of 20", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        scoreLabel.setAlignmentX(SwingConstants.CENTER);
        JPanel scoreLabelPanel = new JPanel();
        scoreLabelPanel.add(scoreLabel);
        questionNumber = new JLabel("Question 1 out of 20", SwingConstants.CENTER); 
        questionNumber.setFont(new Font("Dialog", Font.PLAIN, 13));
        JPanel questionNumberPanel = new JPanel();
        questionNumberPanel.add(questionNumber);
        questionLabel = new JLabel("Quiz question", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Dialog", Font.PLAIN, 17));
        questionLabel.setAlignmentX(SwingConstants.CENTER);
        JPanel questionLabelPanel = new JPanel();
        questionLabelPanel.add(questionLabel);
        // Multi-choice question buttons
        buttonA = new JButton();
        buttonB = new JButton();
        buttonC = new JButton();
        buttonD = new JButton();
        
        Dimension buttonDimension = new Dimension(350,30); // Button dimensions
        Dimension fillerDimension = new Dimension(20, 40); // filler space between components in the GUI
            
        multiChoiceButtons = new JButton[] {buttonA, buttonB, buttonC, buttonD};
        mainMenuButton = new JButton("Return to Main Menu");
        
        quizPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        quizPanel.add(scoreLabelPanel);
        quizPanel.add(questionNumberPanel);
        quizPanel.add(questionLabelPanel);
        
        JPanel buttonAPanel = new JPanel();
        JPanel buttonBPanel = new JPanel();
        JPanel buttonCPanel = new JPanel();
        JPanel buttonDPanel = new JPanel();
        
        JPanel[] multiChoicePanels = {buttonAPanel, buttonBPanel, buttonCPanel, buttonDPanel};
        
        for (JButton b: multiChoiceButtons) {
            b.setFocusable(false);
            b.setMaximumSize(buttonDimension);
        }
        
        for (JPanel panel: multiChoicePanels) {
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        
        buttonAPanel.add(buttonA);
        buttonBPanel.add(buttonB);
        buttonCPanel.add(buttonC);
        buttonDPanel.add(buttonD);
        
        quizPanel.add(buttonAPanel);
        quizPanel.add(buttonBPanel);
        quizPanel.add(buttonCPanel);
        quizPanel.add(buttonDPanel);
        
        // Create spacing in the Quiz game so that the multi-choice questions are not placed close to the bottom of the GUI.
        quizPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        quizPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        quizPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        
        add(title, BorderLayout.NORTH);
        add(quizPanel, BorderLayout.CENTER);
        add(mainMenuButton, BorderLayout.SOUTH);
        
        // Quit quiz popup dialog
        quitQuizDialog = new JDialog();
        quitQuizDialog.setTitle("Quit to main menu?");
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JPanel southPanel = new JPanel();
        quitQuizMessage = new JLabel("Are you sure you want to quit to the main menu?", SwingConstants.CENTER);
        mainMenuOption = new JButton("Quit to Main Menu");
        continueQuizOption = new JButton("Continue quiz");
        dialogPanel.add(quitQuizMessage, BorderLayout.NORTH);
        southPanel.add(mainMenuOption);
        southPanel.add(continueQuizOption);
        dialogPanel.add(southPanel,BorderLayout.SOUTH);
        quitQuizDialog.add(dialogPanel);
        quitQuizDialog.setSize(300,100);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String[]) {
            String[] updatedQuestionInfo = (String[]) arg;
            questionLabel.setText(updatedQuestionInfo[0]);
            questionNumber.setText("Question " + updatedQuestionInfo[1] + " out of 20");
            buttonA.setText(updatedQuestionInfo[2]);
            buttonB.setText(updatedQuestionInfo[3]);
            buttonC.setText(updatedQuestionInfo[4]);
            buttonD.setText(updatedQuestionInfo[5]);
        } else if (arg instanceof int[]) {
            int[] updatedQuestionInfo = (int[]) arg;
            scoreLabel.setText("Score: " + updatedQuestionInfo[0] +" out of 20");
            questionNumber.setText("Question " + updatedQuestionInfo[1] + " out of 20");
        } else if (arg instanceof String) {
            String argStr = (String) arg;
            if (argStr.equals("Open quit quiz dialog"))
                showQuitOptionPane(); 
            else if (argStr.equals("Continue quiz") || argStr.equals("Quit to Main Menu")) {
                quitQuizDialog.dispose();
            }
            mainMenuButton.setSelected(false);
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
        mainMenuOption.addActionListener(controller);
        continueQuizOption.addActionListener(controller);
    }
    
    public void showResult() {
        String message = "Game ended. " + scoreLabel.getText() + ". Saved to database.";
        JOptionPane.showMessageDialog(this, message, "Game finished", JOptionPane.OK_OPTION);
    }
    
    public void showQuitOptionPane() {
        quitQuizDialog.setLocationRelativeTo(this);
        quitQuizDialog.setVisible(true);
    }
}
