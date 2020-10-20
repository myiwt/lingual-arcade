package view;

import control.Controller;
import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
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
    
    public MainView(QuizGameView quizView) {
        super();
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainMenuView = new MainMenuView();
        gameInfoView = new GameInfoView();
        scoreboardView = new ScoreboardView();
        newGameView = new NewGameView();
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
