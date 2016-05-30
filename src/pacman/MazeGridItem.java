/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Kornél
 */
public class MazeGridItem extends JPanel {

    private Point point;
    private BufferedImage img;
    public static final int FIELDSIZE = 10;

    public MazeGridItem() {
        //point= new Point(0,0);
        draw();
    }

    
    public void draw() {
        //repaint();
        setSize(FIELDSIZE, FIELDSIZE);
        setBorder(new LineBorder(Color.BLACK));
        setVisible(true);
    }

    public void loadImage(String imgName) {

        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
        }
    }
/*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        int cellX = FIELDSIZE + (point.x * FIELDSIZE);
        int cellY = FIELDSIZE + (point.y * FIELDSIZE);
        g.setColor(Color.RED);
        g.fillRect(cellX, cellY, FIELDSIZE, FIELDSIZE);
        g.setColor(Color.BLACK);
            g.drawRect(0,0,50,50);
    }
*/
}
