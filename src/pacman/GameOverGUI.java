/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JPanel;

/**
 *
 * @author 604772006
 */
public class GameOverGUI extends JPanel {

    private JFrame topFrame;

    public GameOverGUI(int score, JFrame frame) {
        this.topFrame=frame;
        JLabel title = new JLabel("Game over");
        JLabel nameLbl = new JLabel("Enter player name:");
        JTextField name = new JTextField("test",20);
        JButton okBtn = new JButton("OK");
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
//        gbc.insets = new Insets(30, 0, 0, 0);
        add(nameLbl, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 30, 0, 0);
        add(name, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(okBtn, gbc);

    }

}
