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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import pacman.MazeItem.content;

/**
 *
 * @author Kornél
 */
public class MazeGridItem extends JPanel {

    private BufferedImage img;
    public static final int FIELDSIZE = 20;
    private int topBorder;
    private int bottomBorder;
    private int rightBorder;
    private int leftBorder;

    private content payload;

    public MazeGridItem() {
        //point= new Point(0,0);
        this(1, 1, 1, 1, content.EMPTY);

    }

    public MazeGridItem(int topBorder, int bottomBorder, int rightBorder, int leftBorder, content payload) {
        this.topBorder = topBorder;
        this.bottomBorder = bottomBorder;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.payload = payload;
        draw();
    }

    public void draw() {
        //repaint();
        setSize(FIELDSIZE, FIELDSIZE);
        //setBorder(new LineBorder(Color.BLACK));
        setBorder(BorderFactory.createMatteBorder(topBorder, leftBorder, bottomBorder, rightBorder, Color.black));
        drawCellGraphic();
        JLabel imgLbl = new JLabel(new ImageIcon(img));
        imgLbl.setSize(FIELDSIZE / 2, FIELDSIZE / 2);
        add(imgLbl);
        setVisible(true);
    }

    public void drawCellGraphic() {

        switch (payload) {
            case DOT:
                loadImage("dotImg.gif");
                break;
            case PAC:
                loadImage("pacImg.gif");
                break;
            case GHOST:
                loadImage("ghostImg.gif");
                break;
            case EMPTY:
                loadImage("emptyImg.gif");
                break;
            default:
                break;
        }

    }

    public void loadImage(String imgName) {

        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
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

    @Override
    public String toString() {
        return "MazeGridItem{" + "payload=" + payload + '}';
    }
    
    
}
