/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.EventQueue;

/**
 *
 * @author Kornél
 */
public class Main {
    public static void main(String[] args) {
         EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Maze m=new Maze();
                m.setCurrentGame(new Game());
                GUI g = new GUI(m);
            }
        });
    }
    
}
