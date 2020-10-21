package view;

/**
 *
 * @author ghq8692 Megan Teh
 */

import control.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
public class GameInfoView extends JPanel {
    
    private JLabel title;
    private JTextArea gameInfo;
    private JScrollPane scrollPane;
    private JButton mainMenuButton;
    
    public GameInfoView() {
        super(new BorderLayout());
        title = new JLabel("Game Info", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        gameInfo = new JTextArea(content);
        gameInfo.setEditable(false);
        gameInfo.setMaximumSize(new Dimension(200,200));
        

        gameInfo.setWrapStyleWord(true);
        gameInfo.setLineWrap(true);
        gameInfo.setColumns(50);
        gameInfo.setRows(20);
        scrollPane = new JScrollPane(gameInfo);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setMaximumSize(new Dimension(200,200));
        mainMenuButton = new JButton("Main Menu");
        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(mainMenuButton, BorderLayout.SOUTH);
    }

    private final static String content = "This application contains three minigames: \n\n"
                + "1) QUIZ GAME\n\n"
                + "This is a game that helps you to learn basic phrases in different languages. The game is "
            + "a quiz with 20 questions and the user can select which languages to test. You can create a "
            + "custom quiz to focus your learning on selected phrases by providing a text file with the English phrases to choose the test questions.\n\n\n"
                        
                + "2) CARD MATCHING GAME - coming soon\n\n"
                + "Match the foreign phrase to the English translations. The fewer matches made the higher the score. \n\n"
            + "3) NZ SIGN LANGUAGE GAME - coming soon\n\n"
                + "Requires a webcam to play this game. You must sign as many letter as possible in 30 seconds."
                + "For best results, ensure that you have a white background and that your hands are located within "
            + "the outlined box within the webcam view.\n\n";
    
    public void addController(Controller controller) {
        //need a controller before adding it as a listener 
        mainMenuButton.addActionListener(controller);
    }
}
