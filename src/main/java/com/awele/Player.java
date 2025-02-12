package com.awele;

public class Player {
    private final String name;

    private int capturedSeeds;

    public Player(String name, int id) {
        this.name = name;
        this.capturedSeeds = 0;
    }
    
    public String getName() {
        return name;
    }

    public int getCapturedSeeds() {
        return capturedSeeds;
    }

    public void addCapturedSeeds(int capturedSeeds) {
        this.capturedSeeds += capturedSeeds;
    }
}
