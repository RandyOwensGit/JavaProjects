
package com.randyowens.WormGame.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import com.randyowens.WormGame.domain.Apple;
import com.randyowens.WormGame.domain.Piece;
import com.randyowens.WormGame.game.WormGame;
import com.randyowens.WormGame.gui.Updatable;

public class DrawingBoard extends JPanel implements Updatable {
    // instance variables
    private int pieceLength;

    // instance object references
    private WormGame game;

    // default constructor
    public DrawingBoard(WormGame game, int pieceLength) {
        this.game = game;
        this.pieceLength = pieceLength;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        // for each loop to iterate through all the Pieces of Worm
        for(Piece piece : this.game.getWorm().getPieces()) {
            // SetColor and use fill3DRect to create the Piece
            g.setColor(Color.GRAY);
            g.fill3DRect(piece.getX()*pieceLength, piece.getY()*pieceLength, pieceLength, pieceLength, true);
        }

        // text to display current amount of apples eaten
        g.setColor(Color.GREEN);
        g.drawString("Score: " + Integer.toString(game.getScore()), 75, 75);

        // Create Apple component
        // Set a reference to apple from game object
        Apple apple = this.game.getApple();
        // set color and use fillOval to create apple shape
        g.setColor(Color.red);
        g.fillOval(apple.getX()*pieceLength, apple.getY()*pieceLength, pieceLength, pieceLength);
    }

    @Override
    public void update() {
        super.repaint();
    }
}
