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
                Player playerTurn = game.player1Turn ? game.player1 : game.player2;

                game.showBoard();
                game.showScore();
                System.out.println(playerTurn.getName() + " turn");

                int choice;

                do {
                    String s = scanner.nextLine();
                    choice = Integer.parseInt(s);
                } while (choice < 0 || choice > 11);

                game.play(choice);
            }
        }

    }
}