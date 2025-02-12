package com.awele;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Player 1 : ");
            String name1 = scanner.nextLine();
            System.out.println("Player 2 : ");
            String name2 = scanner.nextLine();

            Awele game = new Awele(name1, name2);

            while (true) {
                game.showBoard();
                game.showScore();

                System.out.println((game.player1Turn ? game.player1.getName() : game.player2.getName()) + " turn");

                int choice;

                do {
                    String s = scanner.nextLine();
                    choice = Integer.parseInt(s);

                    System.out.println("\nSelect a number between 0 and 11.");
                } while (choice < 0 || choice > 11);

                game.play(choice);
            }
        }

    }
}