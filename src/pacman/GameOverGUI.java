package pacman;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JPanel;

public class GameOverGUI extends JPanel {

    private JFrame topFrame;

    public GameOverGUI(int score, JFrame frame) {
        this.topFrame=frame;
        JLabel title = new JLabel("Game over");
        title.setFont(new Font("Courier New", 1, 14));
        
        JLabel nameLbl = new JLabel("Enter player name:");
        nameLbl.setFont(new Font("Courier New", 1, 14));
        
        JLabel scoreLbl=new JLabel("Your score:");
        scoreLbl.setFont(new Font("Courier New", 1, 14));
        
        JLabel playerScore = new JLabel(score+"");
        playerScore.setFont(new Font("Courier New", 1, 14));
        
        JTextField name = new JTextField("");
        name.setFont(new Font("Courier New", 1, 14));
        
        JButton okBtn = new JButton("OK");
        okBtn.setFont(new Font("Courier New", 1, 14));
        
        setLayout(new GridBagLayout());
        okBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please fill in a name", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                HighScoreModel hiscoreData = new HighScoreModel();
                hiscoreData.updateHiScore(name.getText(), score);
                HighScoreGUI hgui = new HighScoreGUI();
                hgui.setTopFrame(topFrame);
                removeAll();
                add(hgui);
                setVisible(true);
                revalidate();
                repaint();
                }
            }

        });
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(title, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(scoreLbl,gbc );
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(playerScore, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.insets = new Insets(30, 0, 0, 0);
        add(nameLbl, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(name, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        

        gbc.insets = new Insets(30, 0, 0, 0);
        add(okBtn, gbc);

    }

}
