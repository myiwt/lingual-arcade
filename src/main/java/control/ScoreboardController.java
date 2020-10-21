/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Model;
import view.ScoreboardView;

/**
 *
 * @author Megan
 */
public class ScoreboardController implements ActionListener {
    private Model model;
    private ScoreboardView view;
    
    public ScoreboardController() {
        
    }
    
    public void addModel(Model model) {
        this.model = model;
    }
    
    public void addView(ScoreboardView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
