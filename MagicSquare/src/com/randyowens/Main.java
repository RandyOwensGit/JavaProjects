package com.randyowens;

import java.util.Scanner;

/**
 * Create a magic square from user entered odd number
 * Each row, column, & diagnal will total the same value
 * Uses siamese method (algorithm?)
 * value corresponds to its place in the centered square numbers order
 */

public class Main {

    public static void main(String[] args) {
        // Test the MagicSquare class here

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a size for the Magic Square (Must be odd): ");
        int size = Integer.parseInt(scanner.nextLine());

        MagicSquareFactory msFactory = new MagicSquareFactory();
        System.out.println(msFactory.createMagicSquare(size));

    }

}
