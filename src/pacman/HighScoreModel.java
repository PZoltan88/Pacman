/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import static java.nio.file.Files.lines;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 604772006
 */
public class HighScoreModel {
    public static final String SCOREFILENAME="highscores.txt";
    private static ArrayList<Score> hiScores;
    
        
    public static void updateFile()
    {
        Charset utf8 = StandardCharsets.UTF_8;
        List<String> lines = Arrays.asList("1st line", "2nd line");
        try {
            Files.write(Paths.get(SCOREFILENAME), lines, utf8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(HighScoreModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void readFile()
    {
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
    }
    
    private class Score{
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
        
        
    }
}
