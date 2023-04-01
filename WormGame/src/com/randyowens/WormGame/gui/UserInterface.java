package com.randyowens.WormGame.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import com.randyowens.WormGame.game.WormGame;
import com.randyowens.WormGame.gui.KeyboardListener;
import com.randyowens.WormGame.gui.Updatable;
import com.randyowens.WormGame.gui.DrawingBoard;

public class UserInterface implements Runnable {

    private JFrame frame;
    private WormGame game;
    private int sideLength;

    private DrawingBoard drawingBoard;

    public UserInterface(WormGame game, int sideLength) {
        this.game = game;
        this.sideLength = sideLength;
    }

    @Override
    public void run() {
        frame = new JFrame("Worm Game");
        int width = (game.getWidth() + 1) * sideLength + 10;
        int height = (game.getHeight() + 2) * sideLength + 10;

        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.setBackground(Color.BLACK);

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        // Create drawing board first which then is added into container-object.
        this.drawingBoard = new DrawingBoard(game, sideLength);
        drawingBoard.setBackground(Color.BLACK);
        container.add(drawingBoard);

        // After this, create keyboard listener which is added into frame-object
        KeyboardListener keyboardListener = new KeyboardListener(game.getWorm());
        frame.addKeyListener(keyboardListener);
    }

    public Updatable getUpdatable() {
        return this.drawingBoard;
    }


    public JFrame getFrame() {
        return frame;
    }
}
