package pacman;

public class Game {
    private int level;
    private int difficulty;
    private int score;
    private int life;
    private int extraLifeGiven;
    private boolean gameActive;
    private boolean losingLife;

    public Game() {
        initValues();
    }
    
    public void initValues()
    {
        level=1;
        difficulty=1;
        score=0;
        life=1;
        gameActive=true;
        losingLife=false;
        extraLifeGiven=0;
    }

    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    
    public void gameOver()
    {
        gameActive=false;
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public boolean isLosingLife() {
        return losingLife;
    }

    public void setLosingLife(boolean losingLife) {
        this.losingLife = losingLife;
    }

    @Override
    public String toString() {
        return "Game{" + "level=" + level + ", difficulty=" + difficulty + ", score=" + score + ", life=" + life + ", gameActive=" + gameActive + ", losingLife=" + losingLife + '}';
    }

    public void setGameActive(boolean gameActive) {
        this.gameActive = gameActive;
    }
    
    

    
    
    
}
