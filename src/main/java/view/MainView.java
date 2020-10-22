package view;

import control.Controller;
import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * This is a view class that follows the MVC (Model View Controller) design
 * pattern. The MainView holds multiple panels in a CardLayout layout to display:
 * - The main menu screen
 * - The game information description screen
 * - The score history display screen
 * - The new game selection screen
 * - The quiz game screen
 * 
 * The MainView observes updates from the Model class to switch panels (screens),
 * managed by the Controller class.
 * 
 * @author ghq8692
 */
public class MainView extends JPanel implements Observer {
    
    private static MainMenuView mainMenuView;
    private static GameInfoView gameInfoView;
    private static ScoreboardView scoreboardView;
    private static NewGameView newGameView;
    private static QuizGameView quizGameView;
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    
    public MainView(QuizGameView quizView, ScoreboardView scoreboardView, NewGameView newGameView) {
        super();
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainMenuView = new MainMenuView();
        gameInfoView = new GameInfoView();
        this.scoreboardView = scoreboardView;
        this.newGameView = newGameView;
        quizGameView = quizView;
        mainPanel.setLayout(cardLayout);
        initCardLayoutView();
    }
    
    private void initCardLayoutView() {
        mainPanel.add(mainMenuView, "Main Menu");
        mainPanel.add(gameInfoView, "Game Info");
        mainPanel.add(scoreboardView, "Scoreboard");
        mainPanel.add(newGameView, "New Game");
        mainPanel.add(quizGameView, "Quiz Game");
        cardLayout.show(mainPanel, "Main Menu");
        add(mainPanel);
    }
    

    @Override
    public void update(Observable obs, Object obj) {
        if (obj instanceof String)
            cardLayout.show(mainPanel, (String) obj);
    }
    
    //What is the reason for NOT registering controllor in the constructor?
    public void addController(Controller controller) {
        //need a controller before adding it as a listener
        mainMenuView.addController(controller);
        gameInfoView.addController(controller);
        scoreboardView.addController(controller);
        quizGameView.addMainController(controller);
        newGameView.addController(controller);
    }
    
}
