/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

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

/**
 *
 * @author Kornél
 */
public class GUI extends JPanel implements KeyListener {

    //private Maze maze;
    private MazeGrid grid;
    private Maze model;

    public GUI(Maze maze) {
        this.model = maze;
        grid = new MazeGrid(maze);        
        add(grid);

        initFrame();

        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                
                model.moveGhost();
                redraw();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void initFrame() {
        JFrame topFrame = new JFrame();

        topFrame.add(this);
        topFrame.addKeyListener(this);
        topFrame.setTitle("Pacman");
        topFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        topFrame.setSize(800, 600);
        topFrame.setLocationRelativeTo(null);
        topFrame.setVisible(true);
    }

    public void redraw() {
        grid.removeAll();
        grid.draw(model);
        grid.revalidate();
        grid.repaint();
    }

    public Maze getModel() {
        return model;
    }

    public void setModel(Maze model) {
        this.model = model;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key pressed");
            model.movePac(MazeItem.direction.EAST);
            redraw();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key pressed");
            model.movePac(MazeItem.direction.WEST);
            redraw();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Up key pressed");
            model.movePac(MazeItem.direction.NORTH);
            redraw();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Down key pressed");
            model.movePac(MazeItem.direction.SOUTH);
            redraw();
        }
        //System.out.println("key pressed");

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //System.out.println("key typed");
    }

}
