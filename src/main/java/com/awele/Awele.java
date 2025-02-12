package com.awele;

public class Awele {

    public final Board board;
    public final Player player1, player2;
    public boolean player1Turn;

    public Awele(String player1Name, String player2Name) {
        this.board = new Board();
        this.player1 = new Player(player1Name, 1);
        this.player2 = new Player(player2Name, 2);
        this.player1Turn = Math.random() < 0.5;
    }

    public void play(int choice) {
        // int index = (player1Turn ? choice : choice + 6);
        Space spaceChosen = board.getSpace(choice);

        if (spaceChosen.isPlayer1Side() != this.player1Turn) {
            System.out.println("Not your side !");
            return;
        }

        if (spaceChosen.getSeeds() <= 0) {
            System.out.println("Empty space !");
            return;
        }

        int seedsInHand = spaceChosen.removeSeeds();
        int position = choice;

        while (seedsInHand > 0) {
            position = (position++) % board.SIZE;
            board.getSpace(position).addSeeds();
            seedsInHand--;
        }

        int capturedSeeds = 0;

        while (board.getSpace(position).isPlayer1Side() == this.player1Turn) {
            Space actualSpace = board.getSpace(position);

            if (actualSpace.getSeeds() == 2 || actualSpace.getSeeds() == 3) {
                capturedSeeds += actualSpace.getSeeds();
                actualSpace.removeSeeds();
            } else {
                break;
            }
            position--;
            if (position < 0) {
                position = board.SIZE - 1;
            }
        }

        if (this.player1Turn) {
            this.player1.addCapturedSeeds(capturedSeeds); 
        }else {
            this.player2.addCapturedSeeds(capturedSeeds);
        }

        this.player1Turn = !this.player1Turn;
    }

    public void showScore() {
        System.out.println(this.player1.getName() + " : " + this.player1.getCapturedSeeds() + " seeds captured.");
        System.out.println(this.player2.getName() + " : " + this.player2.getCapturedSeeds() + " seeds captured.");
    }

    public void showBoard() {
        this.board.showBoard();
    }
}
