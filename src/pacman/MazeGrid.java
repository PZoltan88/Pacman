/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import javax.swing.JPanel;
import pacman.Maze;

/**
 *
 * @author Kornél
 */
public class MazeGrid extends JPanel{
    private MazeGridItem[][] grid;
//http://stackoverflow.com/questions/15870608/creating-a-draw-rectangle-filled-with-black-color-function-in-java-for-a-grid
    public MazeGrid() {
        
        grid= new MazeGridItem[Maze.SIZEY][Maze.SIZEX];
    }
    
    
    
    public void draw()
    {
        for (MazeGridItem[] arr : grid)
        {
            for(MazeGridItem i : arr)
            {
                i.draw();
                add(i);
            }
        }
    }
    
}
