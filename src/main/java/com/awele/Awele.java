package com.awele;

public class Awele {

    public final Board board;
    public final Player player1, player2;
    public boolean player1Turn;

    public Awele(String player1Name, String player2Name) {
        this.board = new Board();
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.player1Turn = Math.random() < 0.5;
    }

    public void play(int choice) {
        Space spaceChosen = board.getSpace(choice);

        if (spaceChosen.isPlayer1Side() != this.player1Turn) {
            System.out.println("Not your side !");
            return;
        }

        if (spaceChosen.getSeeds() <= 0) {
            System.out.println("Empty space !");
            return;
        }

        if (simulatePlay(choice)) {
            System.out.println("You can't starve your opponent! Select another space.");
            return;
        }

        executeMove(choice);

        this.player1Turn = !this.player1Turn;
    }

    private boolean simulatePlay(int choice) {

        Board tempBoard = new Board(this.board); 
        int tempPosition = choice;
        int seedsInHand = tempBoard.getSpace(choice).removeSeeds();

        while (seedsInHand > 0) {
            tempPosition = (tempPosition + 1) % Board.SIZE;
            if (tempPosition != choice) {
                tempBoard.getSpace(tempPosition).addSeed();
                seedsInHand--;
            }
        }

        while (tempBoard.getSpace(tempPosition).isPlayer1Side() == !this.player1Turn) {
            Space actualSpace = tempBoard.getSpace(tempPosition);
            if (actualSpace.getSeeds() == 2 || actualSpace.getSeeds() == 3) {
                actualSpace.removeSeeds();
            } else {
                break;
            }
            tempPosition--;
            if (tempPosition < 0) {
                tempPosition = Board.SIZE - 1;
            }
        }
        return !tempBoard.hasSeeds(!this.player1Turn);
    }


    private void executeMove(int choice) {
        Space spaceChosen = board.getSpace(choice);
        int seedsInHand = spaceChosen.removeSeeds();
        int position = choice;

        while (seedsInHand > 0) {
            position = (position + 1) % Board.SIZE;
            if (position != choice) {
                board.getSpace(position).addSeed();
                seedsInHand--;
            }
        }

        int capturedSeeds = 0;
        while (board.getSpace(position).isPlayer1Side() == !this.player1Turn) {
            Space actualSpace = board.getSpace(position);
            if (actualSpace.getSeeds() == 2 || actualSpace.getSeeds() == 3) {
                capturedSeeds += actualSpace.getSeeds();
                actualSpace.removeSeeds();
            } else {
                break;
            }
            position--;
            if (position < 0) {
                position = Board.SIZE - 1;
            }
        }

        if (this.player1Turn) {
            this.player1.addCapturedSeeds(capturedSeeds);
        } else {
            this.player2.addCapturedSeeds(capturedSeeds);
        }
    }

    public void showScore() {
        System.out.println(this.player1.getName() + " : " + this.player1.getCapturedSeeds() + " seeds captured.");
        System.out.println(this.player2.getName() + " : " + this.player2.getCapturedSeeds() + " seeds captured.");
    }

    public void showBoard() {
        this.board.showBoard();
    }
}
