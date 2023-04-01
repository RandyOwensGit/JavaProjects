
package com.randyowens.WormGame.domain;

import java.util.List;
import java.util.ArrayList;
import com.randyowens.WormGame.Direction;
import com.randyowens.WormGame.domain.Piece;

public class Worm {
    // class variables
    private boolean grown = false;
    private int x;
    private int y;
    private Direction direction;
    private Piece newPiece;

    // List to hold Piece objects as "pieces of the worm"
    private List<Piece> pieces;

    // default constructor
    public Worm(int originalX, int originalY, Direction originalDirection) {
        this.x = originalX;
        this.y = originalY;
        this.direction = originalDirection;

        this.pieces = new ArrayList<Piece>();
        this.pieces.add(new Piece(originalX, originalY));
    }

    // getter method for originalDirection
    public Direction getDirection() {
        return direction;
    }

    // getter method for pieces List
    public List<Piece> getPieces() {
        return this.pieces;
    }

    // returns the length of worm. Total amount of "pieces"
    public int getLength() {
        return this.pieces.size();
    }

    // method to set a new direction for object
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // getter to return the location of the head of the worm
    public Piece getHead() {
        return pieces.get(pieces.size() - 1);
    }

    // method to move the worm
    // Delete last piece of Worm and add new Piece based on coordinates of head piece of worm
    public void move() {
        // if the size of pieces is less than 3, call grow
        if(this.pieces.size() < 3) {
            grow();
            this.pieces.add(this.newPiece);
            this.newPiece = null;
            grown = false;
        } else {
            if(grown) {
                this.pieces.add(this.newPiece);
                grown = false;
            } else {
                grow();
                this.pieces.add(this.newPiece);
                this.newPiece = null;
                if(this.pieces.size() > 3) {
                    this.pieces.remove(0);
                }

                grown = false;
            }
        }
    }

    // method to grow the worm
    public void grow() {
        // values to hold last piece coordinates
        int x = this.pieces.get(this.pieces.size() - 1).getX();
        int y = this.pieces.get(this.pieces.size() - 1).getY();

        if(null != direction)
            // check for what direction the worm is moving
            switch (direction) {
                case LEFT:
                    x -= 1;
                    break;
                case DOWN:
                    y += 1;
                    break;
                case RIGHT:
                    x += 1;
                    break;
                case UP:
                    y -= 1;
                    break;
                default:
                    break;
            }

        this.newPiece = new Piece(x, y);
        grown =  true;
    }

    // method to check if any Piece of the worm runs into the parameter Piece (useful for apples)
    public boolean runsInto(Piece otherPiece) {
        // for each loop to iterate through pieces ArrayList
        for(Piece piece : pieces) {
            // check if these two pieces are the same object
            if(piece == otherPiece) {
                continue;
            }

            // compare the piece and otherPiece, x and y values
            if(piece.getX() == otherPiece.getX() && piece.getY() == otherPiece.getY()) {
                return true;
            }
        }

        // default return is false
        return false;
    }

    // method to check if any pieces of the Worm run into another Piece of the worm, itself
    public boolean runsIntoItself() {
        // If worm is to small to run into itself, return false
        if(pieces.size() == 1) {
            return false;
        }

        // saves reference to worm head piece
        Piece head = getHead();

        // for loop to iterate through pieces array
        for(int i = 0; i < pieces.size() - 1; i++) {
            // if statement that calls runsInto on head piece
            if(head.runsInto(pieces.get(i))) {
                return true;
            }
        }

        return false;
    }
}
