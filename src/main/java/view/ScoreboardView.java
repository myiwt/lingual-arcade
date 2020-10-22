package view;


/**
 * This is a view class that follows the MVC (Model View Controller) design
 * pattern. The ScoreboardView displays the score history which is sourced from an
 * embedded Derby database. The ScoreboardView displays score information received
 * from the Model class, and these updates are managed by the Controller class.
 * 
 * @author ghq8692
 */

import control.Controller;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.FinalScore;

public class ScoreboardView extends JPanel implements Observer {
    private JLabel title;
    private JScrollPane scrollPane;
    private JTable scoreboardTable;
    private JButton mainMenuButton;
    private String[][] data;
    private final String[] columns = new String[] {"DateTime", "Game Type", "Score"};
    private ArrayList scoresList;
    private DefaultTableModel tableModel;
    
    public ScoreboardView() {
        super(new BorderLayout());
        title = new JLabel("Scoreboard", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 20));
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
        if (arg instanceof ArrayList) {
            tableModel.setRowCount(0);
            scoresList = (ArrayList<FinalScore>) arg;
            Iterator scoresIter = scoresList.iterator();
            
            while (scoresIter.hasNext()) {
                FinalScore score = (FinalScore) scoresIter.next();
                String[] row = new String[] {String.valueOf(score.getTimestamp()), score.getGameType(), 
                    String.valueOf(score.getScore())};
                tableModel.addRow(row);
            }
        }
    }
}
