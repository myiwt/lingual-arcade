package view;

/**
 * This is a view class that follows the MVC (Model View Controller) design
 * pattern. The MainMenuView is the first screen displayed in the GUI and is used
 * to navigate to other parts of the application. It contains the buttons: 
 * New Game, Game Info, Scoreboard and Quit.
 * 
 * @author ghq8692 Megan Teh
 */
import control.Controller;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.*;

public class MainMenuView extends JPanel {

    private JButton newGameButton, gameInfoButton, viewScoreboardButton, quitButton;
    private JPanel boxPanel;
    
    //Components initialization
    public MainMenuView() {
        super(new BorderLayout());
        ImageIcon titleImage = new ImageIcon("data/LingualArcadeTitle.png");
        JLabel titleImageLabel = new JLabel("", titleImage, JLabel.CENTER);
        add(titleImageLabel, BorderLayout.NORTH);
        
        boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        
        newGameButton = new JButton("New Game");
        gameInfoButton = new JButton("Game Info");
        viewScoreboardButton = new JButton("Scoreboard");
        quitButton = new JButton("Quit");
        JButton[] buttons = new JButton[] 
        {newGameButton, gameInfoButton, viewScoreboardButton, quitButton};
        
        Dimension buttonDimension = new Dimension(150,30);
        Dimension fillerDimension = new Dimension(5, 10); // filler space between buttons/main title
        boxPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        boxPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        for (JButton b: buttons) {
            b.setFocusable(false);
            b.setMaximumSize(buttonDimension);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            boxPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
            boxPanel.add(b);
        }
        add(boxPanel, BorderLayout.CENTER);
    }

    //What is the reason for NOT registering controllor in the constructor?
    public void addController(Controller controller) {
        //need a controller before adding it as a listener 
        newGameButton.addActionListener(controller);
        gameInfoButton.addActionListener(controller);
        viewScoreboardButton.addActionListener(controller);
        quitButton.addActionListener(controller);
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getGameInfoButton() {
        return gameInfoButton;
    }

    public JButton getViewScoreboardButton() {
        return viewScoreboardButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }
}


