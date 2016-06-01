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
        
        /*
         //hi score model test
         HighScoreModel hiscoreData=new HighScoreModel();
//        test
        hiscoreData.readFile();
        hiscoreData.updateHiScore("playa", 1000);
        hiscoreData.updateHiScore("playa", 2000);
        hiscoreData.updateHiScore("playa", 3000);
        hiscoreData.updateHiScore("playa", 1000);
        hiscoreData.writeFile();
        System.out.println("from mem");
        System.out.println(hiscoreData.toString());
        hiscoreData.readFile();
        System.out.println("from file");
        System.out.println(hiscoreData.toString());
        
//        HighScoreModel m2=new HighScoreModel();
//        m2.readFile();
//        m2.updateHiScore("playa", 1500);
//        System.out.println(m2.toString());
        //HighScoreModel.updateFile();
        //HighScoreModel.readFile();
                */ 
    }
    
}
