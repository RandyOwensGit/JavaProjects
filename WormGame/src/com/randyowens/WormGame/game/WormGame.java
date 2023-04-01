package com.randyowens.WormGame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import com.randyowens.WormGame.Direction;
import com.randyowens.WormGame.domain.Apple;
import com.randyowens.WormGame.domain.Piece;
import com.randyowens.WormGame.domain.Worm;
import com.randyowens.WormGame.gui.Updatable;

public class WormGame extends Timer implements ActionListener {

    // instance variables
    private int width;
    private int height;
    private int score;
    private boolean continues;

    // Object instantiation
    private Updatable updatable;
    private Worm worm;
    private Apple apple;
    private Random random = new Random();

    public WormGame(int width, int height) {
        super(1000, null);

        worm = new Worm(width / 2, height / 2, Direction.DOWN);

        int x = width / 2;
        int y = height / 2;
        while(x == width / 2 && y == height / 2) {
            x = random.nextInt(width);
            y = random.nextInt(height);
        }

        this.apple = new Apple(x, y);

        this.width = width;
        this.height = height;
        score = 0;
        this.continues = true;

        addActionListener(this);
        setInitialDelay(2000);

    }

    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    // getter method for Worm object
    public Worm getWorm() {
        return worm;
    }

    // setter method for Worm object
    public void setWorm(Worm worm) {
        this.worm = worm;
    }

    // getter method for Apple object
    public Apple getApple() {
        return apple;
    }

    // setter method for Apple object
    public void setApple(Apple apple) {
        this.apple = apple;
    }

    // getter method for score
    public int getScore() {
        return score;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            return;
        }

        // moves worm
        worm.move();

        // if worm runs into the apple, grow worm, delete apple, generate new apple
        if(worm.runsInto(apple)) {
            worm.grow();

            score++;

            newApple();
        }

        // if worm runs into self set continues to false
        if(worm.runsIntoItself()) {
            continues = false;
        }

        // Check if worm hit the border
        // Piece to reference head of worm
        Piece head = worm.getHead();

        // check if head of worm has gone past 0 for length and width
        // also check if head has coordinates beyond width and height value
        if(head.getX() < 0 || head.getY() < 0 || head.getX() > width || head.getY() > height) {
            continues = false;
        }

        // call method update of the Updatable interface
        updatable.update();

        // call setDelay which updates the game every 1000ms (1 second)
        setDelay(1000 / worm.getLength());
    }

    // Method to create a new apple
    public void newApple() {
        apple = new Apple(random.nextInt(width), random.nextInt(height));
    }
}
