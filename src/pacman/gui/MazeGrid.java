package pacman.gui;

import java.awt.GridLayout;
import javax.swing.JPanel;
import pacman.buslogic.Maze;
import pacman.buslogic.Maze;
import pacman.buslogic.MazeItem.content;

public class MazeGrid extends JPanel{
    private MazeGridItem[][] grid;
    private content[][] currentMaze;
    private content[][] prevMaze;
    private boolean firstDraw;
            

    public MazeGrid(Maze model)
    {
        currentMaze=model.getContentData();
        prevMaze=model.getContentData();
        grid= new MazeGridItem[Maze.SIZEY][Maze.SIZEX];
        setLayout(new GridLayout(Maze.SIZEY, Maze.SIZEX));
         
        drawNew(model);
        
//        System.out.println("draw complete");
    }
    
    
    
    public void draw(Maze model)
    {
        currentMaze=model.getContentData();
        
        if (firstDraw)
        {
            removeAll();
            drawNew(model);
            
            setVisible(true);
            revalidate();
            repaint();
        }
        else{
        for (int i=0; i<Maze.SIZEY;i++)
        {
            for (int j=0; j<Maze.SIZEX;j++)
            {
                if(prevMaze[i][j]!=currentMaze[i][j])
                {
//                int topBorder= model.getField(j, i).HasNorthWall() ? 1 :0 ;
//                int bottomBorder= model.getField(j, i).HasSouthWall() ? 1 :0 ;
//                int rightBorder= model.getField(j, i).HasEastWall() ? 1 :0 ;
//                int leftBorder= model.getField(j, i).HasWestWall() ? 1 :0 ;
                content payload = model.getField(j, i).getVisibleItemContent();
                grid[i][j].redraw(payload);
                }
            }
        }
        }
//        System.out.println("");
        prevMaze=currentMaze;
//        System.out.println("draw complete");
        firstDraw=false;
    }
    
    public void drawNew(Maze model)
    {
        firstDraw=true;
                
        for (int i=0; i<Maze.SIZEY;i++)
        {
            for (int j=0; j<Maze.SIZEX;j++)
            {
                int topBorder= model.getField(j, i).HasNorthWall() ? 1 :0 ;
                int bottomBorder= model.getField(j, i).HasSouthWall() ? 1 :0 ;
                int rightBorder= model.getField(j, i).HasEastWall() ? 1 :0 ;
                int leftBorder= model.getField(j, i).HasWestWall() ? 1 :0 ;
                content payload = model.getField(j, i).getVisibleItemContent();
                grid[i][j]= new MazeGridItem(topBorder, bottomBorder, rightBorder, leftBorder, payload);
                add(grid[i][j]);
            }
        }
//        System.out.println("draw complete");
    }
    
    public void print()
    {
        for (MazeGridItem[] arr : grid) {
            for (MazeGridItem i : arr) {
                
                    System.out.print(i.toString());
                
            }
            System.out.println();
        } 
    }
}
