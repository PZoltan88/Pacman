/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.EventQueue;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kornél
 */
public class Main {
    public static void main(String[] args) {
        
        Maze m=new Maze();
        m.setCurrentGame(new Game());
         SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            GUI g = new GUI(m);
         }
      });
         //hi score model test
         /*
        HighScoreModel m1=new HighScoreModel();
        m1.updateHiScore("playa", 1000);
        m1.updateHiScore("playa", 2000);
        m1.updateHiScore("playa", 3000);
        m1.writeFile();
        System.out.println(m1.toString());
        
        HighScoreModel m2=new HighScoreModel();
        m2.readFile();
        System.out.println(m2.toString());
        //HighScoreModel.updateFile();
        //HighScoreModel.readFile();
                 */
    }
    
}
