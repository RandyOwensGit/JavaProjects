package com.randyowens;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * main class to run the game and build it
 */

public class Main {

    // Builds game state and starts game
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Play play = new Play(scanner);
    }
}
