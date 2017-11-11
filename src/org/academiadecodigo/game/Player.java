package org.academiadecodigo.game;

import org.academiadecodigo.Constants;

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

    private int points = 0;
    private List<String> correctGuesses;
    private List<String> wrongGuesses;
    private int numberMissedGuesses;
    private int numberGuessedLetters;

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


    //Getters and Setters
    public int getPoints() {
        return points;
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

    @Override
    public void run() {

    }
}
