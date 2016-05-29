/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import javax.swing.*;
import static javax.swing.JFrame.*;

/**
 *
 * @author Kornél
 */
public class GUI extends JPanel{

    public GUI() {
        initFrame();
    }
    
    
    private void initFrame() {
        JFrame topFrame = new JFrame();
        
        topFrame.add(this);
        topFrame.setTitle("Pacman");
        topFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        topFrame.setSize(380, 420);
        topFrame.setLocationRelativeTo(null);
        topFrame.setVisible(true);        
    }
}
