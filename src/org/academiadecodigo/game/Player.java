package org.academiadecodigo.game;

import java.net.Socket;

public class Player {

    private Socket socket;

    private int points;
    private int numberMissedGuesses;
    private int numberGuessedLetters;

    public Player(/*Socket socket*/) {

        this.socket = socket;
    }


    public String playerGuess() {

        throw new UnsupportedOperationException();

    }


    public int getNumberMissedGuesses() {
        return numberMissedGuesses;
    }

    public void incrementNumberMissedGuesses() {
        numberMissedGuesses++;
    }

    public void incrementNumberGuessedLetters() {
        numberGuessedLetters++;
    }
}
