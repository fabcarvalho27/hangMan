package org.academiadecodigo.game;

import org.academiadecodigo.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
    private Socket playerSocket;
    private BufferedReader in;
    private PrintWriter out;

    //Constructor
    public Player(Socket playerSocket) {
        this.playerSocket=playerSocket;
        try {
            in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
            out = new PrintWriter(playerSocket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Methods

    public void init() {

        correctGuesses = new LinkedList<>();
        wrongGuesses = new String[Constants.MAX_NUMBER_WRONG_GUESSES];
    }


    public void outputMessage(String output){
        out.println(output);
    }


    public char guessLetter() {

        try {
        char c = (char) in.read();
           return c;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
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

    public Socket getPlayerSocket() {
        return playerSocket;
    }
}
