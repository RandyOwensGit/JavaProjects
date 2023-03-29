package com.randyowens;

import java.util.Scanner;

/*
 * Handle displaying and accepting input from the user
 */

public class TextUI {
    private Scanner scanner;
    private StringBuilder header;
    private StringBuilder emptyLine;

    private String[][] userBoard;

    // default constructor - build the board header and footers
    public TextUI(Scanner scanner) {
        this.scanner = scanner;
        this.header = new StringBuilder(" |");
        this.emptyLine = new StringBuilder("-|");
        this.userBoard = new String[9][9];
        hideBoard();
        buildHeader(userBoard.length);
        hideBoard();
    }

    public void setUserBoard(String[][] userBoard) {
        this.userBoard = userBoard;
    }

    public String[][] getUserBoard() {
        return userBoard;
    }

    // Collect mine count for game from user
    public int getMineCountFromUser() {
        System.out.println("How many mines do you want on the field? ");
        int mineCount = Integer.parseInt(scanner.nextLine());

        return mineCount;
    }

    // build header during initialization of Object
    public void buildHeader(int boardSize) {
        for(int i = 1; i <= boardSize; i++) {
            header.append(i);
        }
        header.append("|");

        for(int i = 0; i < boardSize; i++) {
            emptyLine.append("-");
        }
        emptyLine.append("|");
    }

    // method to hide entire game board
    public void hideBoard() {
        // iterate through user board setting all tiles to "."
        for(int i = 0; i < userBoard.length; i++) {
            for(int j = 0; j < userBoard.length; j++) {
                userBoard[i][j] = ".";
            }
        }
    }

    // method to ask user for coordinates and get input
    public String[] getMove() {
        String[] userInput = new String[] {};
        while (true) {
            // get input
            System.out.println("Set/unset mines marks or claim a cell as free: ");
            System.out.println("Input Examples: 3 4 free | 5 6 mine | 8 8 free");
            String input = this.scanner.nextLine();
            userInput = input.split(" ");


            int moveX = Integer.parseInt(userInput[0]);
            int moveY = Integer.parseInt(userInput[1]);

            // validate input
            if(moveX >= 0 && moveX <= userBoard.length && moveY >= 0 && moveY <= userBoard.length) {
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }

        return userInput;
    }

    // Format the board for displaying properly
    public String getFormattedBoard() {
        // Build header
        StringBuilder formattedBoard = new StringBuilder();
        formattedBoard.append(header);
        formattedBoard.append("\n" + emptyLine);

        // Build board body
        for(int i = 0; i < userBoard.length; i++) {
            formattedBoard.append("\n" + (i + 1) + "|");
            for(int j = 0; j < userBoard.length; j++) {
                formattedBoard.append(userBoard[i][j]);
            }
            formattedBoard.append("|");
        }
        // -- body finished

        // add footer
        formattedBoard.append("\n" + emptyLine);

        return formattedBoard.toString();
    }

    // win or lose method call
    protected void winOrLose(Boolean isWin) {
        if(isWin) {
            // print board with win message
            System.out.println(this.getFormattedBoard());
            System.out.println("Congratulations! You found all mines!");

        } else {
            // print board with all mines exposed and loss message

            System.out.println(this.getFormattedBoard());
            System.out.println("You stepped on a mine and failed!");
        }
    }
}
