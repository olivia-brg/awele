package com.awele;

public class Board {
    public final int SIZE = 12;
    private final Space[] spaces;

    public Board() {
        spaces = new Space[this.SIZE];
        for (int i = 0; i < this.SIZE; i++) {
            spaces[i] = new Space(i);
        }
    }

    public Space getSpace(int index) {
        return spaces[index];
    }

    public void showBoard() {
        System.out.println("Board:");
        for (int i = 0; i < this.SIZE; i++) {
            System.out.println("Space " + i + " : " + spaces[i].getSeeds() + " ");
            if (i == 5) System.out.println();
        }
        System.out.println("\n");
    }

}
