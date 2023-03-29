package com.randyowens;

import java.util.Random;

/**
 * Class handles the main logic behind game and board
 */
// Builds the game board
public class Game {
    // Class variables
    private String[][] gameBoard;
    private int gameBoardSize;

    // default constructor
    public Game(int mineCount, int gameBoardSize) {
        // intialize gameBoard as a 9x9
        gameBoard = new String[gameBoardSize][gameBoardSize];

        this.gameBoardSize = gameBoardSize;

        // Build initial game board method
        buildGameBoard(mineCount);
    }

    // getter for gameBoardSize
    public int getGameBoardSize() {
        return gameBoardSize;
    }

    // Builds Game Board with empty tiles, mines, and hint values
    public void buildGameBoard(int mineCount) {
        // First create empty board
        emptyBoard();

        // Generate mines for board
        generateMines(mineCount);

        // Display hint tiles
        createHints();
    }

    // Method to create a 9x9 field with parameter as the mine count
    private void emptyBoard() {
        // initialize game board into all non-mine tiles "."
        for(int i = 0; i < gameBoardSize; i++) {
            for(int j = 0; j < gameBoardSize; j++) {
                gameBoard[i][j] = ".";
            }
        }
    }

    // method to add mines to the board
    private void generateMines(int mineCount) {
        // generate mine placements
        int minesAdded = 0;
        Random random = new Random();

        // add mines until parameter is reached
        while (minesAdded < mineCount) {
            // generate placement of mine
            int column = random.nextInt(gameBoardSize);
            int row = random.nextInt(gameBoardSize);

            // replace . with X at position generated
            // first check if X is already there and don't add to minesAdded
            if (gameBoard[column][row].equals("X")) {
                continue;
            }

            // add mine
            gameBoard[column][row] = "X";
            minesAdded++;
        }
    }

    // Method to build hints around mines
    private void createHints() {
        // Iterate through gameBoard until mine is found
        for(int i = 0; i < gameBoardSize; i++) {
            for(int j = 0; j < gameBoardSize; j++) {
                // if current tile is a mine
                if(gameBoard[i][j].equals("X")) {
                    // Build smaller grid of adjacent tiles around the current tile to be increased
                    createTileHintValues(i, j);
                }
            }
        }
    }

    // iterate over in-bound tiles adjacent to parameter tile (new 3x3 grid)
    private void createTileHintValues(int y, int x) {
        // iterate over y-1 to y+1
        // "Creating" new grid to be iterated over 3x3
        for(int i = y - 1; i <= y + 1; i++) {

            // only continue nested conditionals if it is in bounds
            if(i >= 0 && i < gameBoardSize) {

                // Continue "new grid" to be iterated over on x axis
                for(int j = x - 1; j <= x + 1; j++) {
                    // determine that it is in bounds
                    if(j >= 0 && j < gameBoardSize) {
                        // Update this tile
                        updateTileValue(i, j);
                    }
                }
            }
        }

    }

    // method to check tile and increase if needed
    private void updateTileValue(int i, int j) {
        // check if tile is already a mine
        if(gameBoard[i][j].equals("X")) {
            // end
            return;
        }

        // if tile is not already numeric value
        if(gameBoard[i][j].equals(".")) {
            // change to 1 value
            gameBoard[i][j] = "1";

            // increase tile value
        } else {
            gameBoard[i][j] = String.valueOf(Integer.parseInt(gameBoard[i][j]) + 1);
        }
    }

    public String[][] getBoard() {
        return gameBoard;
    }

    // format game board as string
    public String getBoardAsString() {
        String board = "";
        for(int i = 0; i < gameBoardSize; i++) {
            for(int j = 0; j < gameBoardSize; j++) {
                board += gameBoard[i][j];
            }
            board += "\n";
        }

        return board;
    }
}
