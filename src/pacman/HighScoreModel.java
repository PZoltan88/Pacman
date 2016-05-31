/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import static java.nio.file.Files.lines;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 604772006
 */
public class HighScoreModel {

    public static final String SCOREFILENAME = "highscores.bin";
    private ArrayList<Score> hiScores;

    public void writeFile() {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(SCOREFILENAME, true);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(hiScores);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(HighScoreModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateHiScore(String name, int score) {
        hiScores.add(new Score(0, name, score));
        writeFile();
    }

    public void readFile() {
        /*
         Charset utf8 = StandardCharsets.UTF_8;
         hiScores = new ArrayList<>();
         try {
         if(Files.notExists(Paths.get(SCOREFILENAME), LinkOption.NOFOLLOW_LINKS))
         {
         Files.createFile(Paths.get(SCOREFILENAME));
         }
         for ( String s :Files.readAllLines(Paths.get(SCOREFILENAME), utf8))  
         {
         System.out.println(s);
         }
        
         } catch (IOException ex) {
         Logger.getLogger(HighScoreModel.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
        hiScores = new ArrayList<>();
        ObjectInputStream objectinputstream = null;
        try {
            FileInputStream streamIn = new FileInputStream(SCOREFILENAME);
            objectinputstream = new ObjectInputStream(streamIn);
            ArrayList<Score> readCase = (ArrayList<Score>) objectinputstream.readObject();
            hiScores = readCase;
            //System.out.println(recordList.get(i));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectinputstream != null) {
                try {
                    objectinputstream.close();
                } catch (IOException ex) {
                    Logger.getLogger(HighScoreModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public DefaultTableModel getHiScoreData() {
        return null;
    }

    private class Score implements Serializable {

        private int rank;
        private String playerName;
        private int score;

        public Score(int rank, String playerName, int score) {
            this.rank = rank;
            this.playerName = playerName;
            this.score = score;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        @Override
        public String toString() {
            return "Score{" + "rank=" + rank + ", playerName=" + playerName + ", score=" + score + '}';
        }

    }
}
