
package com.randyowens.WormGame.domain;

public class Piece {
    // class variables
    // coordinates
    private int x;
    private int y;

    // default constructor
    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // getter method for int x
    public int getX() {
        return this.x;
    }

    // getter method for int y
    public int getY() {
        return this.y;
    }

    // method to determine if parameter object has same x and y values(coordinates)
    public boolean runsInto(Piece piece) {
        return this.x == piece.getX() && this.y == piece.getY();
    }

    // toString method to return coordinates pattern
    @Override
    public String toString() {
        return "(" + x + ", " + this.y + ")";
    }
}
