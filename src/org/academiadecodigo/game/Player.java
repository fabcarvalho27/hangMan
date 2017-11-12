package org.academiadecodigo.game;

import org.academiadecodigo.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player implements Runnable {

    //Properties
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private String name;

    private int roundPoints = 0;
    private char[] correctGuesses;
    private char[] wrongGuesses;
    private int numberMissedGuesses;
    private int numberGuessedLetters;
    private boolean roundWinner;

    private int gamePoints = 0;
    private boolean gameWinner;

    //Constructor
    public Player(Socket socket, String name) {

        this.clientSocket = socket;
        this.name = name;

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            System.out.println("No output stream: " + e.getMessage());
        }
    }

    //Methods

    public void init() {

        numberGuessedLetters = 0;
        numberMissedGuesses = 0;
        wrongGuesses = new char[Constants.MAX_NUMBER_WRONG_GUESSES];
        correctGuesses = new char[20];
        gameWinner = false;
    }

    public char guessLetter() {

        String guess = "";
        try {
            guess = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

            if(guess.length()>1 || guess.equals("")){
                out.println("Not a valid Letter... Please try again");
                guessLetter();
            }

            System.out.println(name + "guess: " + guess);
            return guess.toCharArray()[0];

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

    public void incrementRoundPoints() {
        roundPoints++;
    }


    //Getters and Setters
    public int getGamePoints() {
        return gamePoints;
    }

    public void setGamePoints(int gamePoints) {
        this.gamePoints = gamePoints;
    }

    public boolean isGameWinner() {
        return gameWinner;
    }

    public void setGameWinner(boolean gameWinner) {
        this.gameWinner = gameWinner;
    }

    public void setNumberMissedGuesses(int numberMissedGuesses) {
        this.numberMissedGuesses = numberMissedGuesses;
    }

    public void setNumberGuessedLetters(int numberGuessedLetters) {
        this.numberGuessedLetters = numberGuessedLetters;
    }

    @Override
    public void run() {

    }

    public boolean isRoundWinner() {
        return roundWinner;
    }

    public void setRoundWinner(boolean roundWinner) {
        this.roundWinner = roundWinner;
    }

    public int getRoundPoints() {
        return roundPoints;
    }

    public void setRoundPoints(int roundPoints) {
        this.roundPoints = roundPoints;
    }

    public PrintWriter getOut() {
        return out;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char[] getCorrectGuesses() {
        return correctGuesses;
    }

    public void setCorrectGuesses(char[] correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

    public char[] getWrongGuesses() {
        return wrongGuesses;
    }

    public void setWrongGuesses(char[] wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }
}
