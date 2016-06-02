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

public class HighScoreModel implements Serializable {

    public static final String SCOREFILENAME = "highscores.bin";
    private ArrayList<Score> hiScores;

    public HighScoreModel() {
        hiScores = new ArrayList<>();
    }

    public void writeFile() {
        try {
            FileOutputStream fout = new FileOutputStream(SCOREFILENAME);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(hiScores);
            oos.close();
            fout.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }       
    }

    public void updateHiScore(String name, int score) {
        readFile();
        hiScores.add(new Score(name, score));
        writeFile();
    }

    public void readFile() {

        if (Files.notExists(Paths.get(SCOREFILENAME), LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createFile(Paths.get(SCOREFILENAME));
            } catch (IOException ex) {
                Logger.getLogger(HighScoreModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        long size = 0;
        try {
            size = Files.size(Paths.get(SCOREFILENAME));
        } catch (IOException ex) {
            Logger.getLogger(HighScoreModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (size > 0) {

            try {
                FileInputStream streamIn = new FileInputStream(SCOREFILENAME);
                ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
                hiScores = (ArrayList<Score>) objectinputstream.readObject();
                objectinputstream.close();
                streamIn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public DefaultTableModel getHiScoreData() {
        String[] columnName = {"Rank", "Player name", "Score"};
        DefaultTableModel eredmeny = new DefaultTableModel(columnName, 0);
        
        Collections.sort(hiScores, new Comparator<Score>() {

            @Override
            public int compare(Score o1, Score o2) {
                if (o1.getScore() < o2.getScore()) {
                    return 1;
                }
                if (o1.getScore() > o2.getScore()) {
                    return -1;
                }
                return 0;

            }

        });

        for (int i = 0; i < hiScores.size(); i++) {
            Object[] obj = {i + 1, hiScores.get(i).getPlayerName(), hiScores.get(i).getScore()};
            eredmeny.addRow(obj);

        }
        return eredmeny;
    }

    @Override
    public String toString() {
        String result = "";
        for (Score s : hiScores) {
            result += s.toString();
        }
        return result;
    }

    private void readObject(
            ObjectInputStream aInputStream
    ) throws ClassNotFoundException, IOException {
        aInputStream.defaultReadObject();

    }

    private void writeObject(
            ObjectOutputStream aOutputStream
    ) throws IOException {
        aOutputStream.defaultWriteObject();
    }

    private class Score implements Serializable {

        private String playerName;
        private int score;

        public Score(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Score{" + "playerName=" + playerName + ", score=" + score + '}';
        }

        private void readObject(
                ObjectInputStream aInputStream
        ) throws ClassNotFoundException, IOException {
            aInputStream.defaultReadObject();

        }

        private void writeObject(
                ObjectOutputStream aOutputStream
        ) throws IOException {
            aOutputStream.defaultWriteObject();
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }

    }
}
