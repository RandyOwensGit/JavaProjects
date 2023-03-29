package com.randyowens;

import java.util.Scanner;

/**
 * This Class handles playing the game
 * - looping through input, displaying board, win condition
 */

public class Play {
    // instance variables to control game state
    private Scanner scanner;
    private int mineCount;
    private Game game;
    private TextUI textUI;

    private boolean gameOver;

    private int tilesRequired;
    private int tilesSearched;

    // default constructor
    public Play(Scanner scanner) {
        this.scanner = scanner;
        this.tilesSearched = 0;
        startGame();
    }

    // method to start the game
    public void startGame() {
        // Build TextUI Object for getting and displaying input
        textUI = new TextUI(scanner);
        mineCount = textUI.getMineCountFromUser();

        // Create Game board
        game = new Game(mineCount, 9);

        // set searched tiles required for game win condition
        this.tilesRequired = (game.getGameBoardSize() * game.getGameBoardSize()) - mineCount;

        // perform first move here in case user has selected a mine
        System.out.println(textUI.getFormattedBoard());
        firstMovePlayable();

        // run game until user has located all the mines without marking non-mine cells
        while (true) {
            //print board
            System.out.println(textUI.getFormattedBoard());

            // update game by getting user move
            String[] move = textUI.getMove();
            userMove(move);

            // if user move landed on mine game is over with loss
            if (gameOver) {
                break;
            }

            // check if all mines are marked
            // user wins and game is over
            if (isAllMinesMarked() || (tilesSearched == tilesRequired)) {
                textUI.winOrLose(true);
                break;
            }
        }
    }

    // method to run the first move by user
    // if user first move is on mine, regenerate mine field until valid move
    private void firstMovePlayable() {
        String[] move = textUI.getMove();
        while (true) {
            if (game.getBoard()[Integer.parseInt(move[1]) - 1][Integer.parseInt(move[0]) - 1].equals("X")) {
                // generate new mine field and rebuild
                game.buildGameBoard(mineCount);
            } else {
                userMove(move);
                break;
            }
        }
    }

    // method to update game state - parameter takes user move coordinates
    public void userMove(String[] move) {
        // get user input move
        int y = Integer.parseInt(move[1]) - 1;
        int x = Integer.parseInt(move[0]) - 1;

        // if move was 'free' or 'mine'
        if (move[2].equals("free")) {
            freeMove(y, x);
        } else {
            markMove(y, x);
        }
    }

    // game over method - display board and end program
    private void gameOver() {
        // Display Board with all accurate locations
        for(int i = 0; i < game.getGameBoardSize(); i++) {
            for(int j = 0; j < game.getGameBoardSize(); j++) {
                if(textUI.getUserBoard()[i][j].equals(".") || textUI.getUserBoard()[i][j].equals("*")) {
                    textUI.getUserBoard()[i][j] = game.getBoard()[i][j];
                }
            }
        }

        textUI.winOrLose(false);
        gameOver = true;
    }

    // check if all mines are marked for win condition
    private boolean isAllMinesMarked() {
        for(int i = 0; i < game.getGameBoardSize(); i++) {
            for(int j = 0; j < game.getGameBoardSize(); j++) {
                // if game board tile is a mine and userboard tile is not a mark then return
                if (game.getBoard()[i][j].equals("X") && !textUI.getUserBoard()[i][j].equals("*")) {
                    return false;
                }
            }
        }

        return true;
    }

    // update userBoard at coordinates
    private boolean freeMove(int y, int x) {
        // player chose mine tile, resulting in a game over loss
        if (game.getBoard()[y][x].equals("X")) {
            gameOver();

            // if player chose a hint tile only display that tile
        } else if(game.getBoard()[y][x].matches("[0-9]")) {
            displayHintTile(y, x);

            // if player chose an empty tile, display all surrounding tiles
        } else {
            // Call method to implement flood fill algorithm
            floodFillTiles(new boolean[9][9], y, x);
        }

        return true;
    }

    // use flood fill algorithm to recursively fill out all adjacent empty tiles
    private void floodFillTiles(boolean[][] visited, int y, int x) {
        // if outside of boundaries of board then return
        if (y < 0 || y >= game.getGameBoardSize() || x < 0 || x >= game.getGameBoardSize()) {
            return;
        }

        // if tile has been visited already then return, if not set to true in location
        if (visited[y][x]) {
            return;
        }
        visited[y][x] = true;

        // if tile is a number, then display number to userBoard and return
        if (game.getBoard()[y][x].matches("[0-9]")) {
            displayHintTile(y, x);
            return;
        }

        // if tile is '.' then change tile to '/' mark
        if (game.getBoard()[y][x].equals(".")) {
            textUI.getUserBoard()[y][x] = "/";
            this.tilesSearched++;
        }

        // create recursive calls moving in all 4 cardinal directions
        floodFillTiles(visited, y+1, x); // move up
        floodFillTiles(visited, y-1, x); // move down
        floodFillTiles(visited, y, x+1); // move right
        floodFillTiles(visited, y, x-1); // move left
        floodFillTiles(visited, y-1, x-1); // move top left
        floodFillTiles(visited, y-1, x+1); // move top right
        floodFillTiles(visited, y+1, x-1); // move bottom left
        floodFillTiles(visited, y+1, x+1); // move bottom right
    }

    // display the single tile numeric value of hint tile
    private void displayHintTile(int y, int x) {
        // if tile has not already been displayed
        if(!textUI.getUserBoard()[y][x].matches("[0-9]")) {
            textUI.getUserBoard()[y][x] = game.getBoard()[y][x];
            tilesSearched++;
        }
    }

    // user move was a 'mark'
    private void markMove(int y, int x) {
        // check if tile is already marked, if so then unmark it
        if (textUI.getUserBoard()[y][x].equals("*")) {
            textUI.getUserBoard()[y][x] = ".";
        } else {
            textUI.getUserBoard()[y][x] = "*";
        }
    }

}
