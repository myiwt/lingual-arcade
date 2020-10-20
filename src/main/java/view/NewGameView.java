package view;

import control.Controller;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author ghq8692 Megan Teh
 */
public class NewGameView extends JPanel implements ItemListener {
    private JLabel title, selectLanguagesLabel;
    private JButton mainMenuButton, startGameButton;
    private Checkbox standardGameCheckbox, customGameCheckbox, cardGameCheckBox;
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
        cardGameCheckBox = new Checkbox("Card Matching Game", gameOptionsGroup, false);
        gameOptions[0] = quizCheckBox;
        gameOptions[1] = cardGameCheckBox;
        quizCheckBox.addItemListener(this);
        cardGameCheckBox.addItemListener(this);
        
        // Panel to store language quiz game option information
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        
        // Inner panel to display language quiz game options 
        JPanel quizOptionsPanel = new JPanel();
        
        // Group the 2 quiz game options together so only one can be selected at a time within this group
        quizOptionsGroup = new CheckboxGroup(); 
        quizOptions = new Checkbox[2];
        standardGameCheckbox = new Checkbox("Standard game", quizOptionsGroup, true);
        customGameCheckbox = new Checkbox("Load game from file", quizOptionsGroup, false);
        quizOptions[0] = standardGameCheckbox;
        quizOptions[1] = customGameCheckbox;
        standardGameCheckbox.addItemListener(this);
        customGameCheckbox.addItemListener(this);
        quizOptionsPanel.add(standardGameCheckbox);
        quizOptionsPanel.add(customGameCheckbox);
        quizPanel.add(quizCheckBox);
        quizPanel.add(quizOptionsPanel);
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
    
    // Helper function to set enabled language checkboxes for new game setup
    private void setEnabledLanguageCheckBoxes(boolean enabled) {
        selectLanguagesLabel.setEnabled(enabled);
        for (JCheckBox cb: languageCheckBoxes) {
                cb.setEnabled(enabled);
            }
    }
    
    private void setEnableQuizOptions(boolean enabled) {
        standardGameCheckbox.setEnabled(enabled);
            customGameCheckbox.setEnabled(enabled);
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
     * Sets the conditions for game setup - make options available or grey them out according to
     * whether game settings are valid or not. For a standard Quiz game or a card matching game,
     * a game cannot be started unless a language is selected.
     * @param e 
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        String checkboxName;
        
        if (e.getSource() instanceof JCheckBox) {
            JCheckBox checkbox = (JCheckBox) e.getSource();
            checkboxName = checkbox.getName();
        } else {
            checkboxName = String.valueOf(e.getItem());
        }
        if (checkboxName.equals("Load game from file")) {
            setEnabledLanguageCheckBoxes(false);
            startGameButton.setEnabled(true);
        } else if (checkboxName.equals("Card Matching Game")) {
            setEnableQuizOptions(false);
            setEnabledLanguageCheckBoxes(true);
        } else if (checkboxName.equals("Language Quiz")) {
            if (customGameCheckbox.isEnabled()) {
                setEnabledLanguageCheckBoxes(false);
            }
            setEnableQuizOptions(true);
        } else {
            setEnabledLanguageCheckBoxes(true);
        }

        if (noLanguageSelected()) {
            if (standardGameCheckbox.isEnabled() && standardGameCheckbox.getState()) {
                startGameButton.setEnabled(false);
                startGameButton.setActionCommand("Quiz Game");
            } 
            else if (customGameCheckbox.isEnabled() && customGameCheckbox.getState()) {
                startGameButton.setEnabled(true);
                startGameButton.setActionCommand("Quiz Game");
            }
            else if (cardGameCheckBox.isEnabled() && cardGameCheckBox.getState()) {
                startGameButton.setEnabled(false);
                startGameButton.setActionCommand("Card Matching Game");
            }
        } else {
            if (standardGameCheckbox.isEnabled() && standardGameCheckbox.getState()) {
                startGameButton.setEnabled(true);
                startGameButton.setActionCommand("Quiz Game");
            } 
            else if (customGameCheckbox.isEnabled() && customGameCheckbox.getState()) {
                startGameButton.setEnabled(true);
                startGameButton.setActionCommand("Quiz Game");
            }
            else if (cardGameCheckBox.isEnabled() && cardGameCheckBox.getState()) {
                startGameButton.setEnabled(true);
                startGameButton.setActionCommand("Card Matching Game");
            }
        }
    }
}
