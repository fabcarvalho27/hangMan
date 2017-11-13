package org.academiadecodigo.game;

import org.academiadecodigo.Constants;
import org.academiadecodigo.terminalGFX.TerminalGFX;

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

    private char[] correctGuesses;
    private char[] wrongGuesses;
    private int numberMissedGuesses;
    private int numberGuessedLetters;
    private boolean roundWinner;

    private int gamePoints = 0;
    private boolean gameWinner;

    private Game game;
    private volatile boolean gotName;

    //Constructor
    public Player(Socket socket, Game game) {

        this.clientSocket = socket;
        //this.name = name;
        this.game = game;

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            System.out.println("No output stream: " + e.getMessage());
        }
    }

    //Methods

    public void init(int currentRoundWordLenght) {

        numberGuessedLetters = 0;
        numberMissedGuesses = 0;
        wrongGuesses = new char[Constants.MAX_NUMBER_WRONG_GUESSES];

        roundWinner = false;
        initCorrectGuesses(currentRoundWordLenght);

        gameWinner = false;
    }


    public char guessLetter() {

        String guess = "";
        try {
            guess = in.readLine().toUpperCase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String notValid = "Not a valid Letter... Please try again";
        String alreadyChosen = "Letter already chosen... Please try again";

        if (invalideLetterSize(guess) || !valideLetters(guess)) {

            setPlayerMessage(notValid);
            game.sendClientsScreen();
            return guessLetter();
        }

        if (letterAlreadyChosen(guess)) {

            setPlayerMessage(alreadyChosen);
            game.sendClientsScreen();
            return guessLetter();
        }

        setPlayerMessage("");
        game.sendClientsScreen();
        System.out.println(name + "guess: " + guess);

        return guess.charAt(0);

    }


    public void setPlayerMessage(String message) {
        if (this == game.getPlayer1()) {
            game.getGameStatus().setP1Message(message);
        } else {
            game.getGameStatus().setP2Message(message);
        }
    }


    //Utils methods
    private boolean letterAlreadyChosen(String letter) {

        for (int i = 0; i < correctGuesses.length; ) {
            System.out.println("Correct Guess: " + correctGuesses[i]);
            System.out.println("Letter: " + letter.charAt(0));
            return correctGuesses[i] == letter.charAt(0);
        }

        return false;
    }

    private boolean invalideLetterSize(String letter) {

        return letter.length() > 1 || letter.equals(" ") || letter.equals("");
    }

    private boolean valideLetters(String letter) {

        return letter.matches("\\p{L}");
    }

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

    public void incrementGamePoints() {
        gamePoints++;
    }


    public void initCorrectGuesses(int currentRoundWordLenght) {

        correctGuesses = initializeArray(new char[currentRoundWordLenght], '_');
    }

    public char[] initializeArray(char[] array, char character) {

        for (int i = 0; i < array.length; i++) {
            array[i] = character;
        }
        return array;
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

    public boolean isRoundWinner() {
        return roundWinner;
    }

    public void setRoundWinner(boolean roundWinner) {
        this.roundWinner = roundWinner;
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


    @Override
    public void run() {

        getOut().println("Whats your name?");

        //BLOCK
        try {
            name = in.readLine();
            gotName = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //BLOCK

        getOut().println("\n Name inserted.\nWaiting for the other oponent..");


        while (true) {

            while (game.isAcceptingGuesses()) {

                //BLOCK
                game.analisePlayerGuess(this, guessLetter());
                //BLOCK

                game.updateGameStatus();
                game.sendClientsScreen();
            }

        }
    }

    public boolean gotName() {
        {
            return gotName;
        }
    }

}
