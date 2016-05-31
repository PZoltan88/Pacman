/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.JFrame.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kornél
 */
public class GUI extends JPanel {

    //private Maze maze;
    public GUI(Maze maze) {

        JButton newGame = new JButton("New game");
        JButton highScores = new JButton("Highscores");
        JButton exit = new JButton("Exit");

        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component component : getComponents()) {
                    component.setVisible(false);
                }
                MazeGUI mgui = new MazeGUI(maze);
                //removeAll();
                add(mgui);
                setVisible(true);
//revalidate();
                //repaint();
            }

        });

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });

        add(newGame);
        add(highScores);
        add(exit);
        initFrame();

    }

    private void initFrame() {
        JFrame topFrame = new JFrame();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        topFrame.add(this);
        //topFrame.addKeyListener(mgui);
        topFrame.setTitle("Pacman");
        topFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        topFrame.setSize(800, 600);
        topFrame.setLocationRelativeTo(null);
        topFrame.setVisible(true);
    }

    private class MazeGUI extends JPanel {

        private MazeGrid grid;
        private Maze model;
        JLabel lifesLbl;
        JLabel lifes;

        JLabel scoreLbl;
        JLabel score;

        JLabel levelLbl;
        JLabel level;
        JLabel dummy;

        public MazeGUI(Maze maze) {
            lifesLbl = new JLabel("Lifes");
            lifes = new JLabel();

            scoreLbl = new JLabel("Score");
            score = new JLabel();

            levelLbl = new JLabel("Level");
            level = new JLabel();

            dummy = new JLabel("");
            this.model = maze;
            setStatusBar();
            grid = new MazeGrid(maze);
            setFocusable(true);
            requestFocusInWindow();
            //addKeyListener(this);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            //gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(lifesLbl, gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            add(lifes, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(scoreLbl, gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            add(score, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(levelLbl, gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            add(level, gbc);
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.insets = new Insets(30, 0, 0, 0);
            gbc.anchor = GridBagConstraints.CENTER;
            add(grid, gbc);
            //add (dummy, gbc);

            final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {

                    model.moveGhost();
                    redraw();
                }
            }, 0, 1, TimeUnit.SECONDS);
            setKeyBindings();
        }

        public void setStatusBar() {

            lifes.setText(model.getCurrentGame().getLife() + "");

            score.setText(model.getCurrentGame().getScore() + model.getSessionScore() + "");
            level.setText(model.getCurrentGame().getLevel() + "");
        }

        public synchronized void redraw() {
            /*
             JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
             //grid.invalidate();
             remove(grid);
             grid.revalidate();
             grid.draw(model);
             //add(grid);
             validate();
             topFrame.repaint();
             */
            //removeAll();
            // Orig content!!
            grid.removeAll();
            //grid.revalidate();
            grid.draw(model);
            setStatusBar();

            grid.revalidate();
            grid.repaint();
            
            /*
            for (Component component : getComponents()) {
                    component.setVisible(false);
                }
            grid.draw(model);
            add(grid);
            grid.setVisible(true);
            setVisible(true);
            */
            /*
            GridBagConstraints gbc = new GridBagConstraints();
            remove(grid);
            grid= new MazeGrid(model);
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.insets = new Insets(30, 0, 0, 0);
            gbc.anchor = GridBagConstraints.CENTER;
            add(grid, gbc);
            //dummy.setText("");
            validate();
            repaint();
            */
            if (!model.getCurrentGame().isGameActive()) {
                JOptionPane.showMessageDialog(null, "Game over", "game over", JOptionPane.ERROR_MESSAGE);
            }
        }

        /*
         public void redraw()
         {
         //this.remove(grid);
         grid = new MazeGrid(model);
         add(grid);
         setVisible(true);
         //return redrawn;
         }
         */
        public Maze getModel() {
            return model;
        }

        public void setModel(Maze model) {
            this.model = model;
        }

        private void setKeyBindings() {
            ActionMap actionMap = getActionMap();
            int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
            InputMap inputMap = getInputMap(condition);

            String vkLeft = "VK_LEFT";
            String vkRight = "VK_RIGHT";
            String vkUp = "VK_UP";
            String vkDown = "VK_DOWN";
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), vkLeft);
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), vkRight);
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), vkUp);
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), vkDown);

            actionMap.put(vkLeft, new KeyAction(vkLeft));
            actionMap.put(vkRight, new KeyAction(vkRight));
            actionMap.put(vkUp, new KeyAction(vkUp));
            actionMap.put(vkDown, new KeyAction(vkDown));

        }

        private class KeyAction extends AbstractAction {

            public KeyAction(String actionCommand) {
                putValue(ACTION_COMMAND_KEY, actionCommand);
            }

            @Override
            public void actionPerformed(ActionEvent actionEvt) {
                //System.out.println(actionEvt.getActionCommand() + " pressed");
                switch (actionEvt.getActionCommand()) {
                    case "VK_LEFT":
                        System.out.println("left pressed");
                        model.movePac(MazeItem.direction.WEST);
                        redraw();
                        break;
                    case "VK_RIGHT":
                        System.out.println("right pressed");
                        model.movePac(MazeItem.direction.EAST);
                        redraw();
                        break;
                    case "VK_UP":
                        System.out.println("left pressed");
                        model.movePac(MazeItem.direction.NORTH);
                        redraw();
                        break;
                    case "VK_DOWN":
                        System.out.println("right pressed");
                        model.movePac(MazeItem.direction.SOUTH);
                        redraw();
                        break;
                }
            }
        }
    }

}
