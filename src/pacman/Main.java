package pacman;

import pacman.buslogic.Music;
import pacman.buslogic.Maze;
import pacman.buslogic.Game;
import pacman.gui.GUI;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        
//      Music.music();
        
        Maze m=new Maze();
        m.setCurrentGame(new Game());
         SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            GUI g = new GUI(m);
         }
      });
    } 
}
    
