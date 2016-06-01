/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author 604772006
 */
public class HighScoreGUI extends JPanel {

    private JLabel title;
    private JButton back;
    private JTable scores;
    private JFrame topFrame;

    public HighScoreGUI() {
        title = new JLabel("Highscores");
        //title.setPreferredSize(new Dimension(100,80));
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 32));
        back = new JButton("Back to Main menu");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                                
                //(JFrame)(getRootPane().getParent()).dispose();
                topFrame.dispose();
                Maze m = new Maze();
                m.setCurrentGame(new Game());

                GUI g = new GUI(m);
                //this.dispose();

            }
        });

        scores = new JTable();
        
        loadScoreData();
        JScrollPane tableHolder=new JScrollPane(scores);
        scores.setFillsViewportHeight(true);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(title, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 40;
        gbc.weighty = 1.0; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(tableHolder,gbc);
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(back, gbc);
    }

    private void loadScoreData() {
        //String[] columnNames = {"Rank", "Player name", "Score"};
//        Object[][] rowData= {{"","",""},{"","",""}};
        HighScoreModel hiscoreData=new HighScoreModel();
//        test
//        hiscoreData.readFile();
//        hiscoreData.updateHiScore("playa", 1000);
//        hiscoreData.updateHiScore("playa", 2000);
//        hiscoreData.updateHiScore("playa", 3000);
//        hiscoreData.updateHiScore("playa", 1000);
//        hiscoreData.writeFile();
        hiscoreData.readFile();
        scores= new JTable();
        scores.setModel(hiscoreData.getHiScoreData());
    }

    public JFrame getTopFrame() {
        return topFrame;
    }

    public void setTopFrame(JFrame topFrame) {
        this.topFrame = topFrame;
    }
    
}
