/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import pacman.MazeItem.content;
import pacman.MazeItem.direction;

/**
 *
 * @author Kornél
 */
public class Maze {

    public final int SIZEX = 15;
    public final int SIZEY = 20;
    public final int DOTSCORE = 100;
    public final int SCOREPERLIFE = 10000;
    private Game currentGame;
    int pacPosX;
    int pacPosY;

    MazeItem[][] realm;
    //Level mazeLevel;
    int touchedItems = 1;
    Stack itemStack = new Stack();
    MazeItem currentItem = null;
    public static Random randomizer = new Random();

    public Maze() {

        Initialize();
    }

    public void Initialize() {

        realm = new MazeItem[SIZEY][SIZEX];

        for (int i = 0; i < SIZEX; i++) {
            for (int j = 0; j < SIZEY; j++) {
                realm[j][i] = new MazeItem();
                realm[j][i].setPosX(i);
                realm[j][i].setPosY(j);
                realm[j][i].getItemContent().add(content.EMPTY);

            }

        }
        touchedItems = 1;
        currentItem = realm[0][0];
        itemStack.clear();
        seed();
    }

    
    public direction getRandomDirection() {
        
        return direction.values()[(int)(Math.random()*(direction.values().length))];
    }

    public MazeItem getPac() {
        MazeItem result = new MazeItem();
        for (MazeItem[] arr : realm) {
            for (MazeItem i : arr) {
                if (i.getItemContent().contains(content.PAC)) {
                    result = i;
                }
            }
        }
        return result;
    }

    public void movePac(direction to) {
        move(getPac(), to);
    }

    public void moveGhost() {
        for (MazeItem[] arr : realm) {
            for (MazeItem i : arr) {
                if (i.getItemContent().contains(content.GHOST)) {
                    move(i, getRandomDirection());
                }
            }
        }
    }

    public void move(MazeItem item, direction to) {
        if (validPos(destX(item, to), destY(item, to))) {
            itemInteract(item, getField(destX(item, to), destY(item, to)));
        }
    }

    public int destX(MazeItem item, direction to) {
        if (to == direction.EAST) {
            return item.getPosX() + 1;
        } else if (to == direction.WEST) {
            return item.getPosX() - 1;
        }
        return item.getPosX();
    }

    public int destY(MazeItem item, direction to) {
        if (to == direction.SOUTH) {
            return item.getPosY() + 1;
        } else if (to == direction.NORTH) {
            return item.getPosY() - 1;
        }
        return item.getPosY();
    }

    public boolean validPos(int x, int y) {
        if ((x > SIZEX - 1) || (y > SIZEY - 1) || (x < 0) || (y < 0)) {
            return false;
        }
        return true;
    }

    public void itemInteract(MazeItem src, MazeItem dest) {
        if (dest.getVisibleItemContent() == content.EMPTY) {
            switchValues(src, dest);
        } else if (src.getVisibleItemContent() == content.PAC) {
            if (dest.getVisibleItemContent() == content.DOT) {
                pacEat(src, dest);
            } else if (dest.getVisibleItemContent() == content.GHOST) {
                pacDie(src, dest);
            }
        } else if (src.getVisibleItemContent() == content.GHOST) {
            if (dest.getVisibleItemContent() == content.DOT) {
                ghostShift(src, dest);
            } else if (dest.getVisibleItemContent() == content.PAC) {
                pacDie(dest, src);
            }
        }
    }

    public void switchValues(MazeItem a, MazeItem b) {
        MazeItem tmp = a;
        a = b;
        b = tmp;
    }
    
    public void removeOneLife() {

        currentGame.setLife(currentGame.getLife() - 1);
        if (currentGame.getLife() == 0) {
            currentGame.gameOver();
        }

    }

    public void addOneLife() {
        currentGame.setLife(currentGame.getLife() + 1);
    }

    public void addScore() {
        currentGame.setScore(currentGame.getScore() + DOTSCORE);
        if (currentGame.getScore() % SCOREPERLIFE == 0) {
            addOneLife();
        }
    }

    
    public MazeItem getField(int x, int y) {
        return realm[y][x];
    }

    public ArrayList GetNeighborFieldsWithWalls(MazeItem item) {
        ArrayList<MazeItem> Neighbors = new ArrayList<>();

        // has left neighbor?
        if ((0 <= (item.getPosX()) - 1) && (realm[item.getPosY()][item.getPosX() - 1].HasAllWalls())) {
            Neighbors.add(realm[item.getPosY()][item.getPosX() - 1]);
        }
        // has right neighbor?
        if ((item.getPosX()) + 1 < SIZEX && (realm[item.getPosY()][item.getPosX() + 1].HasAllWalls())) {
            Neighbors.add(realm[item.getPosY()][item.getPosX() + 1]);
        }
        // has top neighbor?
        if (0 <= (item.getPosY()) - 1 && (realm[item.getPosY() - 1][item.getPosX()].HasAllWalls())) {
            Neighbors.add(realm[item.getPosY() - 1][item.getPosX()]);
        }
        // has bottom neighbor?
        if ((item.getPosY()) + 1 < SIZEY && (realm[item.getPosY() + 1][item.getPosX()].HasAllWalls())) {
            Neighbors.add(realm[item.getPosY() + 1][item.getPosX()]);
        }

        return Neighbors;

    }

    /// <summary>
    /// Main logic to make the maze explorable - every field of the maze should be accessible by Pac
    /// </summary>
    public void MakeMazeExplorable() {

        int realmSize = SIZEX * SIZEY;
        currentItem = realm[0][0];

        while (touchedItems < realmSize) {
            ArrayList<MazeItem> neighborFieldsWithWalls = GetNeighborFieldsWithWalls(currentItem);
            if (neighborFieldsWithWalls.size() > 0) {
                int randomNum0ToWallNr = randomizer.nextInt(neighborFieldsWithWalls.size());
                MazeItem randomField = neighborFieldsWithWalls.toArray(new MazeItem[0])[randomNum0ToWallNr];
                //randomField.DeleteWallsInBetween(currentItem);
                currentItem.DeleteWallsInBetween(randomField);
                itemStack.push(currentItem);
                currentItem = randomField;
                touchedItems++;
            } else {
                //!!!!InvalidOperationException - "Stack empty" unhandled
                currentItem = (MazeItem) itemStack.pop();
            }
        }
    }

    public void seedPac() {
        realm[0][0].getItemContent().add(content.PAC);
    }

    public void seedGhost() {
        realm[SIZEY / 2][SIZEX / 2].getItemContent().add(content.GHOST);
    }

    public void seedDots() {
        for (MazeItem[] arr : realm) {
            for (MazeItem i : arr) {
                if (!((i.getItemContent().contains(content.GHOST)) && (i.getItemContent().contains(content.PAC)))) {
                    i.getItemContent().add(content.DOT);
                }

            }
        }
    }

    public void seed() {
        seedPac();
        seedGhost();
        seedDots();
    }

        //public virtual void Serialize();
    //public virtual void Deserialize();
    private void pacEat(MazeItem src, MazeItem dest) {
        src.getItemContent().remove(content.PAC);
        dest.getItemContent().add(content.PAC);
        dest.getItemContent().remove(content.PAC);
        addScore();
    }

    private void pacDie(MazeItem a, MazeItem b) {
        removeOneLife();
        reset();
    }

    private void ghostShift(MazeItem src, MazeItem dest) {
        src.getItemContent().remove(content.GHOST);
        dest.getItemContent().add(content.GHOST);
    }

    private void reset() {
        Initialize();
    }
}
