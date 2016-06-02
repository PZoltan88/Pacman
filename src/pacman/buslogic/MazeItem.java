package pacman.buslogic;

import java.util.*;
import java.util.EnumSet;
import javax.swing.DefaultListModel;

public class MazeItem
    {
        public enum direction
        {
            NORTH ,
            SOUTH ,
            EAST ,
            WEST 

        }

        public enum content
        {
            EMPTY,
            DOT,
            GHOST,
            PAC
        }
        private List<content> itemContent;

        private boolean hasNorthWall;
        private boolean hasSouthWall;
        private boolean hasEastWall;
        private boolean hasWestWall;

        private int posX;
        private int posY;
 
        public MazeItem()
        {
            this(true, true, true, true, 0,0);
        }

        public MazeItem(boolean hasNorthWall, boolean hasSouthWall, boolean hasEastWall, boolean hasWestWall, int posX, int posY)
        {
            this.hasNorthWall = hasNorthWall;
            this.hasSouthWall = hasSouthWall;
            this.hasEastWall = hasEastWall;
            this.hasWestWall = hasWestWall;
            this.posX = posX;
            this.posY = posY;
            itemContent = new ArrayList<content>();
        }

       

        public direction GetWallDirectionBetween(MazeItem item)
        {
            if (item.posY == this.posY)
            {
                if (item.posX < this.posX)
                    return direction.WEST;
                else
                    return direction.EAST;
            }
            else // columns are the same
            {
                if (item.posY < this.posY)
                    return direction.NORTH;
                else
                    return direction.SOUTH;
            }
        }
        
        public void DeleteWallsInBetween(MazeItem item)
        {
            switch (GetWallDirectionBetween(item))
            {
                case NORTH:
                    this.hasNorthWall = false;
                    item.hasSouthWall = false;
                    break;

                case SOUTH:
                    this.hasSouthWall = false;
                    item.hasNorthWall = false;
                    break;

                case EAST:
                    this.hasEastWall = false;
                    item.hasWestWall = false;
                    break;

                case WEST:
                    this.hasWestWall = false;
                    item.hasEastWall = false;
                    break;
            }
        }

        public boolean HasAllWalls()
        {
            return (this.hasEastWall && this.hasNorthWall && this.hasSouthWall && this.hasWestWall);
        }

    public List<content> getItemContent() {
        return itemContent;
    }

    public content getVisibleItemContent()
    {
        
        if (itemContent.contains(content.GHOST)) {return content.GHOST;}
        else if (itemContent.contains(content.PAC)) {return content.PAC;}
        else if (itemContent.contains(content.DOT)) {return content.DOT;}
        
        return content.EMPTY;
    }
           

    public boolean hasWallOnSide(direction to)
    {
        switch (to)
            {
                case NORTH:
                    return hasNorthWall;

                case SOUTH:
                    return hasSouthWall;

                case EAST:
                    return hasEastWall;
 
                case WEST:
                    return hasWestWall;
 
                default:
                    return true;
            }
        
    }
    public boolean HasNorthWall() {
        return hasNorthWall;
    }

    public void setHasNorthWall(boolean hasNorthWall) {
        this.hasNorthWall = hasNorthWall;
    }

    public boolean HasSouthWall() {
        return hasSouthWall;
    }

    public void setHasSouthWall(boolean hasSouthWall) {
        this.hasSouthWall = hasSouthWall;
    }

    public boolean HasEastWall() {
        return hasEastWall;
    }

    public void setHasEastWall(boolean hasEastWall) {
        this.hasEastWall = hasEastWall;
    }

    public boolean HasWestWall() {
        return hasWestWall;
    }

    public void setHasWestWall(boolean hasWestWall) {
        this.hasWestWall = hasWestWall;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public String toString() {
        return "MazeItem{" + "hasNorthWall=" + hasNorthWall + ", hasSouthWall=" + hasSouthWall + ", hasEastWall=" + hasEastWall + ", hasWestWall=" + hasWestWall + ", posX=" + posX + ", posY=" + posY + '}';
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MazeItem other = (MazeItem) obj;
        if (!Objects.equals(this.itemContent, other.itemContent)) {
            return false;
        }
        if (this.hasNorthWall != other.hasNorthWall) {
            return false;
        }
        if (this.hasSouthWall != other.hasSouthWall) {
            return false;
        }
        if (this.hasEastWall != other.hasEastWall) {
            return false;
        }
        if (this.hasWestWall != other.hasWestWall) {
            return false;
        }
        return true;
    }
    
    

    
    
    

        
         
    }

