package view;

import control.Controller;
import control.QuizGameController;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * This is a view class that follows the MVC (Model View Controller) design
 * pattern. The NewGameView class displays languages to select as check boxes so
 * that the user can choose which languages to use to start a Quiz game. The 
 * QuizGameController receives the language selection from the NewGameView and
 * updates the QuizGameModel so that the correct languages are used to set up the
 * quiz game.
 * 
 * @author ghq8692
 */
public class NewGameView extends JPanel implements ItemListener {
    private JLabel title, selectLanguagesLabel;
    private JButton mainMenuButton, startGameButton;
    private Checkbox cardGameCheckBox;
    private Checkbox[] gameOptions, quizOptions;
    private CheckboxGroup gameOptionsGroup, quizOptionsGroup;
    private JCheckBox[] languageCheckBoxes;
    
    public NewGameView() {
        super(new BorderLayout());
        // Add title at top of GUI
        title = new JLabel("New Game Selection", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);
        
        // Add new game options in center of GUI
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        
        // Main game type selection
        gameOptionsGroup = new CheckboxGroup();
        gameOptions = new Checkbox[2];
        Checkbox quizCheckBox = new Checkbox("Language Quiz", gameOptionsGroup, true);
        cardGameCheckBox = new Checkbox("Card Matching Game - new game coming soon!", gameOptionsGroup, false);
        cardGameCheckBox.setEnabled(false);
        gameOptions[0] = quizCheckBox;
        gameOptions[1] = cardGameCheckBox;
        quizCheckBox.addItemListener(this);
        cardGameCheckBox.addItemListener(this);
        
        // Panel to store language quiz game option information
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        
        // Group the 2 quiz game options together so only one can be selected at a time within this group
        quizOptionsGroup = new CheckboxGroup(); 
        quizOptions = new Checkbox[2];
        quizPanel.add(quizCheckBox);
        centerPanel.add(quizPanel);
        
        // Add option to select card matching game
        centerPanel.add(cardGameCheckBox);
        
        // Add panel to select languages to include in the game
        JPanel selectLanguagesPanel = new JPanel();
        // Create two panels to display language selection, stacked on top each other. The top
        // panel contains text info, the bottom panel contains checkboxes for language selection
        selectLanguagesPanel.setLayout(new BoxLayout(selectLanguagesPanel, BoxLayout.Y_AXIS));
        selectLanguagesLabel = new JLabel("Languages to include");
        selectLanguagesPanel.add(selectLanguagesLabel);
        
        JPanel languageCheckBoxesPanel = new JPanel();
        languageCheckBoxesPanel.setLayout(new BoxLayout(languageCheckBoxesPanel, BoxLayout.X_AXIS));
        languageCheckBoxesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JCheckBox spanishCheckBox = new JCheckBox("Spanish", true);
        JCheckBox frenchCheckBox = new JCheckBox("French", true);
        JCheckBox germanCheckBox = new JCheckBox("German", true);
        
        spanishCheckBox.setName("Spanish");
        frenchCheckBox.setName("French");
        germanCheckBox.setName("German");
        
        spanishCheckBox.addItemListener(this);
        frenchCheckBox.addItemListener(this);
        germanCheckBox.addItemListener(this);
        
        languageCheckBoxes = new JCheckBox[] {spanishCheckBox,frenchCheckBox, germanCheckBox};
        
        for (JCheckBox cb: languageCheckBoxes) {
            languageCheckBoxesPanel.add(cb);
        }
        
        selectLanguagesPanel.add(languageCheckBoxesPanel);
        
        centerPanel.add(selectLanguagesPanel);
        
        // Add all game option GUI components to middle of center of main GUI
        add(centerPanel, BorderLayout.CENTER);
        
        // Add buttons at bottom of GUI
        JPanel southPanel = new JPanel();
        mainMenuButton = new JButton("Main Menu");
        startGameButton = new JButton("Start New Game");
        startGameButton.setActionCommand("Quiz Game");
        southPanel.add(startGameButton);
        southPanel.add(mainMenuButton);
        add(southPanel, BorderLayout.SOUTH);
    }
    
    public JButton getMainMenuButton() {
        return mainMenuButton;
    }
    
    public JButton getStartGameButton() {
        return startGameButton;
    }
    
    private boolean noLanguageSelected() {
        boolean noLanguageSelected = true;
        for (JCheckBox c: languageCheckBoxes) {
            if (c.isSelected()) {
                noLanguageSelected = false;
                break;
            }
        }
        return noLanguageSelected;
    }
    
    public void addController(Controller controller) {
        startGameButton.addActionListener(controller);
        mainMenuButton.addActionListener(controller);
    }
    
    /**
     * Makes the start game button enabled or greyed out according to whether 
     * the game settings are valid or not. Cannot start a game unless at least
     * one language is selected.
     * @param e 
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (noLanguageSelected()) {
            startGameButton.setEnabled(false);
        }
        
        if (!noLanguageSelected()) {
            startGameButton.setEnabled(true);
        }
    }
    
    public void addQuizGameController(QuizGameController controller) {
        for (JCheckBox cb: languageCheckBoxes) {
                cb.addItemListener(controller);
        }
    }
}
