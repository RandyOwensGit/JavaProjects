package com.randyowens;

import java.util.Random;

public class MagicSquareFactory {

    public MagicSquare createMagicSquare(int size) {

        MagicSquare square = new MagicSquare(size);

        // implement the creation of a magic square with the Siamese method algorithm here
        // Need to create 9 increments to be placed with value i

        // define start index
        int posY = 0; // row value - always start at 0
        int posX = size / 2; // column value - middle column

        // iterate through numbers to be placed
        for(int i = 1; i <= size * size; i++) {
            System.out.println("\n" + square);

            // place value into square
            square.placeValue(posX, posY, i);

            // use temp variables to calculate next position
            int tempY = 0;
            int tempX = 0;

            if(posY - 1 < 0) {
                // if y goes up out of bounds, go to bottom
                tempY = size - 1;
            } else {
                // move y up by 1
                tempY = posY - 1;
            }

            if(posX + 1 > size - 1) {
                // if x goes right out of bounds, go to first index
                tempX = 0;
            } else {
                // move x to the right 1 value
                tempX = posX + 1;
            }

            // use temp values to check if new position is already occupied
            if(square.readValue(tempX, tempY) > 0) {
                if(posY + 1 > size - 1) {
                    // y gets set to the top
                    tempY = 0;
                } else {
                    // move y "down 1"
                    // reset x because it doesnt need to move
                    tempY = posY + 1;
                    tempX = posX;
                }
            }

            // apply the temporary location to official location values
            posY = tempY;
            posX = tempX;

        }


        return square;
    }


}
