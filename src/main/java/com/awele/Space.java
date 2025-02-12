package com.awele;

public class Space {
    private final int SIDE_SIZE = 6;
    private final int STARTING_SEEDS = 4;
    private final boolean player1Side;

    private int seeds;

    public Space(int index) {
        this.player1Side = (index < SIDE_SIZE);
        this.seeds = this.STARTING_SEEDS;
    }

    public int getSeeds() {
        return seeds;
    }

    public void addSeeds() {
        this.seeds++;
    }

    public int removeSeeds() {
        int tmp = seeds;
        this.seeds = 0;
        return tmp;
    }

    // public int getIndex() {
    //     return index;
    // }

    public boolean isPlayer1Side() {
        return player1Side;
    }

}
