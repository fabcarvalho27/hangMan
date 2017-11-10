package org.academiadecodigo.game;

import org.academiadecodigo.Constants;

import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player {

    //Properties
    private Socket socket;

    private int points;
    private List<String> correctGuesses;
    private String[] wrongGuesses;
    private int numberMissedGuesses;
    private int numberGuessedLetters;

    //Constructor
    public Player() {

    }

    //Methods

    public void init() {

        correctGuesses = new LinkedList<>();
        wrongGuesses = new String[Constants.MAX_NUMBER_WRONG_GUESSES];
    }

    public String guessLetter() {

        throw new UnsupportedOperationException();
    }

    public Socket connect() {

        throw new UnsupportedOperationException();
    }


    //Utils methods
    public int getNumberMissedGuesses() {
        return numberMissedGuesses;
    }

    public int getNumberGuessedLetters() {
        return numberGuessedLetters;
    }

    public void incrementNumberMissedGuesses() {
        numberMissedGuesses++;
    }

    public void incrementNumberGuessedLetters() {
        numberGuessedLetters++;
    }


    //Getters and Setters
    public int getPoints() {
        return points;
    }

    public String[] getWrongGuesses() {
        return wrongGuesses;
    }

    public void setWrongGuesses(String[] wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }

    public List<String> getCorrectGuesses() {
        return correctGuesses;
    }

    public void setCorrectGuesses(List<String> correctGuesses) {
        this.correctGuesses = correctGuesses;
    }
}
