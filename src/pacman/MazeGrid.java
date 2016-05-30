/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.GridLayout;
import javax.swing.JPanel;
import pacman.Maze;
import pacman.MazeItem.content;

/**
 *
 * @author Kornél
 */
public class MazeGrid extends JPanel{
    private MazeGridItem[][] grid;
    
//http://stackoverflow.com/questions/15870608/creating-a-draw-rectangle-filled-with-black-color-function-in-java-for-a-grid
    /*
    public MazeGrid() {
        
        grid= new MazeGridItem[Maze.SIZEY][Maze.SIZEX];
        setLayout(new GridLayout(Maze.SIZEX, Maze.SIZEY));
        draw();
    }
    */
    public MazeGrid(Maze model)
    {
        grid= new MazeGridItem[Maze.SIZEY][Maze.SIZEX];
        setLayout(new GridLayout(Maze.SIZEX, Maze.SIZEY));
        draw(model);
    }
    
    
    
    public void draw(Maze model)
    {
        /*
        for (MazeGridItem[] arr : grid)
        {
            for(MazeGridItem i : arr)
            {
                i = new MazeGridItem();
                //i.draw();
                add(i);
            }
        }
                */
        for (int i=0; i<Maze.SIZEY;i++)
        {
            for (int j=0; j<Maze.SIZEX;j++)
            {
                int topBorder= model.getField(j, j).HasNorthWall() ? 1 :0 ;
                int bottomBorder= model.getField(j, j).HasSouthWall() ? 1 :0 ;
                int rightBorder= model.getField(j, j).HasEastWall() ? 1 :0 ;
                int leftBorder= model.getField(j, j).HasWestWall() ? 1 :0 ;
                content payload = model.getField(j, j).getVisibleItemContent();
                grid[i][j]= new MazeGridItem(topBorder, bottomBorder, rightBorder, leftBorder, payload);
                add(grid[i][j]);
            }
        }
    }
    
}
