package pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HighScoreGUI extends JPanel {

    private JLabel title;
    private JButton back;
    private JTable scores;
    private JFrame topFrame;

    public HighScoreGUI() {
        title = new JLabel("Highscores");
        title.setFont(new Font("Courier New", 1, 24));
        
        back = new JButton("Back to Main menu");
        back.setFont(new Font("Courier New", 1, 14));
        
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                                
                topFrame.dispose();
                Maze m = new Maze();
                m.setCurrentGame(new Game());

                GUI g = new GUI(m);
                
            }
        });

        scores = new JTable();
        
        loadScoreData();
        JScrollPane tableHolder=new JScrollPane(scores);
        scores.setFillsViewportHeight(true);
        tableHolder.setMinimumSize(new Dimension(250, 100));
        
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
        
        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.decode("#40E0D0"));
    }

    private void loadScoreData() {
        HighScoreModel hiscoreData=new HighScoreModel();
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
