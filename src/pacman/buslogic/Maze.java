package pacman.buslogic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import pacman.buslogic.MazeItem.content;
import pacman.buslogic.MazeItem.direction;

public class Maze {

    public static final int SIZEX = 11;
    public static final int SIZEY = 13;
    public static final int DOTSCORE = 100;
    public static final int SCOREPERLIFE = 1000;
    private Game currentGame;
    private int sessionScore;
    private int pacPosX;
    private int pacPosY;
    private int ghostPosX;
    private int ghostPosY;

    MazeItem[][] realm;
    int touchedItems = 1;
    Stack itemStack = new Stack();
    MazeItem currentItem = null;
    public static Random randomizer = new Random();

    public Maze() {

        initialize();
    }
    
    public content[][] getContentData()
    {
        content[][] result=new content[SIZEY][SIZEX];
        for (int i=0; i<Maze.SIZEY;i++)
        {
            for (int j=0; j<Maze.SIZEX;j++)
            {
            result[i][j]=realm[i][j].getVisibleItemContent();
            }
        }
        return result;
    }

    public void initialize() {

        realm = new MazeItem[SIZEY][SIZEX];

        for (int i = 0; i < SIZEX; i++) {
            for (int j = 0; j < SIZEY; j++) {
                realm[j][i] = new MazeItem();
                realm[j][i].setPosX(i);
                realm[j][i].setPosY(j);
                realm[j][i].getItemContent().add(content.EMPTY);

            }

        }
        sessionScore = 0;
        touchedItems = 1;
        currentItem = realm[0][0];
        itemStack.clear();

        makeMazeExplorable();
        //print();
        seed();
//        System.out.println("seed complete");
        //print();
    }

    public void print() {
        for (MazeItem[] arr : realm) {
            for (MazeItem i : arr) {

                System.out.print(i.toString());

            }
            System.out.println();
        }
    }

    public void levelComplete() {
        currentGame.setLevel(currentGame.getLevel() + 1);
        currentGame.setScore(currentGame.getScore() + sessionScore);
        
            addLife(sessionScore/SCOREPERLIFE);
        
        reset();
//        System.out.println("level complete " + currentGame.toString());
    }

    public int nrDots() {
        int r = 0;
        for (MazeItem[] arr : realm) {
            for (MazeItem i : arr) {
                if (i.getItemContent().contains(content.DOT)) {
                    r++;
                }
            }
        }
        return r;
    }

    public direction getRandomDirection() {

        return direction.values()[(int) (Math.random() * (direction.values().length))];
    }

    public MazeItem getPac() {
        return getField(pacPosX, pacPosY);
    }

    public void movePac(direction to) {
        move(getPac(), to);
    }

    public MazeItem getGhost() {
        return getField(ghostPosX, ghostPosY);
    }

    public void moveGhost() {
        move(getGhost(), getRandomDirection());
    }

    public synchronized void move(MazeItem item, direction to) {
        if (validPosToMove(item, to)) {
            if (item.getVisibleItemContent() == content.PAC) {
                pacPosX = destX(item, to);
                pacPosY = destY(item, to);
            } else if (item.getVisibleItemContent() == content.GHOST) {
                ghostPosX = destX(item, to);
                ghostPosY = destY(item, to);

            }
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

    public boolean validPosToMove(MazeItem item, direction to) {
        if (!validPos(destX(item, to), destY(item, to))) {
            return false;
        } else if (item.hasWallOnSide(to)) {
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

    public void switchValues(MazeItem src, MazeItem dest) {
        dest.getItemContent().add(src.getVisibleItemContent());
        src.getItemContent().remove(src.getVisibleItemContent());
        src.getItemContent().add(content.EMPTY);
        dest.getItemContent().remove(content.EMPTY);

    }

    public void removeOneLife() {

        currentGame.setLife(currentGame.getLife() - 1);
        currentGame.setLosingLife(true);
        if (currentGame.getLife() == 0) {
            currentGame.setScore(currentGame.getScore() + sessionScore);
            currentGame.gameOver();
        }
//        System.out.println("life lost " + currentGame.toString());

    }

    public void addLife(int x) {
        currentGame.setLife(currentGame.getLife() + x);
    }

    public void addScore() {
        sessionScore += DOTSCORE;
//        System.out.println("new score " + sessionScore);

    }

    public MazeItem getField(int x, int y) {
        return realm[y][x];
    }

    public ArrayList getNeighborFieldsWithWalls(MazeItem item) {
        ArrayList<MazeItem> Neighbors = new ArrayList<>();

          if ((0 <= (item.getPosX()) - 1) && (realm[item.getPosY()][item.getPosX() - 1].HasAllWalls())) {
            Neighbors.add(realm[item.getPosY()][item.getPosX() - 1]);
        }

        if (((item.getPosX() + 1) < SIZEX) && (realm[item.getPosY()][item.getPosX() + 1].HasAllWalls())) {
            Neighbors.add(realm[item.getPosY()][item.getPosX() + 1]);
        }
    
        if ((0 <= (item.getPosY() - 1)) && (realm[item.getPosY() - 1][item.getPosX()].HasAllWalls())) {
            Neighbors.add(realm[item.getPosY() - 1][item.getPosX()]);
        }
   
        if (((item.getPosY() + 1) < SIZEY) && (realm[item.getPosY() + 1][item.getPosX()].HasAllWalls())) {
            Neighbors.add(realm[item.getPosY() + 1][item.getPosX()]);
        }

        return Neighbors;

    }

    public void makeMazeExplorable() {

        int realmSize = SIZEX * SIZEY;
        currentItem = realm[0][0];

        while (touchedItems < realmSize) {
            ArrayList<MazeItem> neighborFieldsWithWalls = getNeighborFieldsWithWalls(currentItem);
            if (neighborFieldsWithWalls.size() > 0) {
                int randomNum0ToWallNr = randomizer.nextInt(neighborFieldsWithWalls.size());
                MazeItem randomField = neighborFieldsWithWalls.toArray(new MazeItem[0])[randomNum0ToWallNr];
                currentItem.DeleteWallsInBetween(randomField);
                itemStack.push(currentItem);
                currentItem = randomField;
                touchedItems++;
            } else {
                currentItem = (MazeItem) itemStack.pop();
            }
        }
    }

    public void seedPac() {
        pacPosX = 0;
        pacPosY = 0;
        realm[pacPosY][pacPosX].getItemContent().add(content.PAC);
        realm[pacPosY][pacPosX].getItemContent().remove(content.EMPTY);
    }

    public void seedGhost() {
        ghostPosX = SIZEX / 2;
        ghostPosY = SIZEY / 2;
        realm[ghostPosY][ghostPosX].getItemContent().add(content.GHOST);
        realm[ghostPosY][ghostPosX].getItemContent().remove(content.EMPTY);
    }

    public void seedDots() {
        for (MazeItem[] arr : realm) {
            for (MazeItem i : arr) {
                if (!((i.getItemContent().contains(content.GHOST)) || (i.getItemContent().contains(content.PAC)))) {
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

    private void pacEat(MazeItem src, MazeItem dest) {
        src.getItemContent().remove(content.PAC);
        src.getItemContent().add(content.EMPTY);
        dest.getItemContent().add(content.PAC);
        dest.getItemContent().remove(content.DOT);
        addScore();
        if (nrDots() == 0) {
            levelComplete();
        }
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

        initialize();
    }

    public synchronized Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public int getSessionScore() {
        return sessionScore;
    }

}
