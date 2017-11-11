package org.academiadecodigo.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Player implements Runnable {

    //Properties
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private int gamePoints;
    private int roundPoints = 0;
    private List<String> correctGuesses;
    private List<String> wrongGuesses;
    private int numberMissedGuesses;
    private int numberGuessedLetters;
    private boolean gameWinner;
    private boolean roundWinner;

    //Constructor
    public Player(Socket socket) {

        this.clientSocket = socket;

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            System.out.println("No output stream: " + e.getMessage());
        }
    }

    //Methods

    public void init() {

        correctGuesses = new LinkedList<>();
        wrongGuesses = new LinkedList<>();
        gameWinner = false;
        gamePoints = 0;
    }

    public String guessLetter() {

        try {
            String guessed = in.readLine();
            System.out.println(guessed);
            return guessed;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    public void incrementRoundPoints(){
        roundPoints++;
    }


    //Getters and Setters
    public int getGamePoints() {
        return gamePoints;
    }

    public void setGamePoints(int gamePoints) {
        this.gamePoints = gamePoints;
    }

    public List<String> getWrongGuesses() {
        return wrongGuesses;
    }

    public void setWrongGuesses(List<String> wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }

    public List<String> getCorrectGuesses() {
        return correctGuesses;
    }

    public void setCorrectGuesses(List<String> correctGuesses) {
        this.correctGuesses = correctGuesses;
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
}
