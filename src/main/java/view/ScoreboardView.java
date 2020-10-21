package view;


/**
 *
 * @author ghq8692 Megan Teh
 */

import control.Controller;
import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.Scoreboard;
import model.Score;

public class ScoreboardView extends JPanel implements Observer {
    private JLabel title;
    private JScrollPane scrollPane;
    private JTable scoreboardTable;
    private JButton mainMenuButton;
    private ArrayList<String[]> scoreboardList;
    private String[][] data;
    private final String[] columns = new String[] {"DateTime", "Game Type", "Score"};
    private ResultSet scoreboardResultSet;
    private ArrayList scoresList;
    private DefaultTableModel tableModel;
    
    public ScoreboardView() {
        super(new BorderLayout());
        title = new JLabel("Scoreboard", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        
        scoreboardList = Scoreboard.getScoreboardList();
        tableModel = new DefaultTableModel(columns,0);
        scoreboardTable = new JTable(tableModel);
        scoreboardTable.setDefaultEditor(Object.class, null);
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
        System.out.println(arg.getClass().getName());
        if (arg instanceof ArrayList) {
            tableModel.setRowCount(0);
            scoresList = (ArrayList<Score>) arg;
            System.out.println("size: " + scoresList.size());
            Iterator scoresIter = scoresList.iterator();
            
            while (scoresIter.hasNext()) {
                Score score = (Score) scoresIter.next();
                String[] row = new String[] {String.valueOf(score.getTimestamp()), score.getGameType(), 
                    String.valueOf(score.getScore())};
                tableModel.addRow(row);
                System.out.println(score.getTimestamp());
                System.out.println(score.getGameType());
                System.out.println(score.getScore());
            }
        }
    }
}
