package com.awele;

public class Board {
    public static final int SIZE = 12;
    private final Space[] spaces;

    public Board() {
        spaces = new Space[Board.SIZE];
        for (int i = 0; i < Board.SIZE; i++) {
            spaces[i] = new Space(i);
        }
    }

    public Board(Board other) {
        spaces = new Space[Board.SIZE];
        for (int i = 0; i < Board.SIZE; i++) {
            spaces[i] = new Space(i, other.spaces[i]);
        }
    }

    public boolean hasSeeds(boolean isPlayer1) {
        int start = isPlayer1 ? 0 : Board.SIZE / 2;
        int end = isPlayer1 ? (Board.SIZE / 2) - 1 : Board.SIZE - 1;
        for (int i = start; i < end; i++) {
            if (spaces[i].getSeeds() > 0) {
                return true;
            }
        }
        return false;
    }

    public Space getSpace(int index) {
        return spaces[index];
    }

    public void showBoard() {
        System.out.println("Board:");
        for (int i = 0; i < Board.SIZE; i++) {
            System.out.println("Space " + i + " : " + spaces[i].getSeeds() + " ");
            if (i == 5) System.out.println();
        }
        System.out.println("\n");
    }

}
