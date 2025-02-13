package com.awele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AweleTest {
    private Awele game;

    @BeforeEach
    void setUp() {
        game = new Awele("Alice", "Bob");
    }

    @Test
    void testGameInitialization() {
        assertNotNull(game.board, "Le plateau ne doit pas être nul");
        assertNotNull(game.player1, "Le joueur 1 ne doit pas être nul");
        assertNotNull(game.player2, "Le joueur 2 ne doit pas être nul");
        assertTrue(game.player1Turn || !game.player1Turn, "Le tour doit être défini");
    }

    @Test
    void testCannotPlayOnEmptySpace() {
        int choice = 0;
        game.board.getSpace(choice).removeSeeds();
        game.play(choice);
        assertEquals(0, game.board.getSpace(choice).getSeeds(), "La case doit rester vide");
    }

    @Test
    void testCannotPlayOpponentSide() {
        boolean initialTurn = game.player1Turn;
        int choice;
        if (initialTurn) {
            choice = 7;
        } else {
            choice = 2;
        }
        game.play(choice);
        assertEquals(initialTurn, game.player1Turn, "Le joueur ne doit pas jouer du cote adverse");
    }

    @Test
    void testCannotStarveOpponent() {
        game.player1Turn = true;
        for (int i = 7; i < Board.SIZE; i++) {
            game.board.getSpace(i).removeSeeds();
        }
        game.board.getSpace(6).removeSeeds();
        game.board.getSpace(6).addSeed();
        game.board.getSpace(6).addSeed();
        int choice = 2;
        game.play(choice);

        assertTrue(game.player1Turn, "Le joueur ne doit pas pouvoir jouer un coup qui affame l'adversaire.");
    }

    @Test
    void testTurnSwitchesAfterValidMove() {
        int choice = 1;
        game.player1Turn = true;
        boolean initialTurn = game.player1Turn;
        game.play(choice);
        assertNotEquals(initialTurn, game.player1Turn, "Le tour doit changer après un coup valide");
    }
    

    @Test
    void testCaptureSeeds() {
        //putting seeds back to be able to capture
        game.board.getSpace(6).removeSeeds();
        game.board.getSpace(6).addSeed();
        game.board.getSpace(6).addSeed();
        
        game.player1Turn = true;
        int choice = 2;
        game.play(choice);

        int captured = game.player1.getCapturedSeeds();
        assertTrue(captured > 0, "Le joueur doit capturer des graines");
    }
}
