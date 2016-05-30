/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.JFrame.*;

/**
 *
 * @author Kornél
 */
public class GUI extends JPanel {

    private Maze maze;
    private MazeGrid grid;

    public GUI() {
        grid=new MazeGrid();
        //grid.draw();
        add(grid);
        //MazeGridItem gridItemTest=new MazeGridItem();
        //gridItemTest.draw();
        //add(gridItemTest);
        initFrame();
    }

    private void initFrame() {
        JFrame topFrame = new JFrame();

        topFrame.add(this);
        topFrame.setTitle("Pacman");
        topFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        topFrame.setSize(380, 420);
        topFrame.setLocationRelativeTo(null);
        topFrame.setVisible(true);
    }

    public void drawMaze() {

    }

    

}
