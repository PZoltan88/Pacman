/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Kornél
 */
public class Game {
    private int level;
    private int difficulty;
    private int score;
    private int life;

    public Game() {
        level=1;
        difficulty=1;
        score=0;
        life=3;
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
        
    }

    @Override
    public String toString() {
        return "Game{" + "level=" + level + ", difficulty=" + difficulty + ", score=" + score + ", life=" + life + '}';
    }
    
    
}
