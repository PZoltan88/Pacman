/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.EventQueue;
import javax.swing.SwingUtilities;
import sun.audio.*;
import java.io.*;

/**
 *
 * @author Kornél
 */
public class Main {
    public static void main(String[] args) {
        
      //  music();
        
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
    
  /* public static void music() 
    {       
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream("C:\\Music.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(loop);
    }

 */   
}
