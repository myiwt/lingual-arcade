package view;


/**
 *
 * @author ghq8692 Megan Teh
 */

import control.Controller;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import main.Scoreboard;

public class ScoreboardView extends JPanel implements Observer {
    private JLabel title;
    private JScrollPane scrollPane;
    private JTable scoreboardTable;
    private JButton mainMenuButton;
    private ArrayList<String[]> scoreboardList;
    private String[][] data;
    private String[] columns;
    
    public ScoreboardView() {
        super(new BorderLayout());
        title = new JLabel("Scoreboard", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        
        scoreboardList = Scoreboard.getScoreboardList();
        columns = Scoreboard.getColumns();
        data = new String[scoreboardList.size()][columns.length];
        for (int i = 0; i < scoreboardList.size(); i++) {
            data[i] = scoreboardList.get(i);
        }
        
        scoreboardTable = new JTable(data, columns);
        scrollPane = new JScrollPane(scoreboardTable);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        mainMenuButton = new JButton("Main Menu");
        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(mainMenuButton, BorderLayout.SOUTH);
        
    }
    
    public void addController(Controller controller) {
        //need a controller before adding it as a listener 
        mainMenuButton.addActionListener(controller);
    }
    
    public JButton getMainMenuButton() {
        return mainMenuButton;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Scoreboard) {
            scoreboardList = Scoreboard.getScoreboardList();
            
        }
    }
}
