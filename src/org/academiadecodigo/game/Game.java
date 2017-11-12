package org.academiadecodigo.game;

import org.academiadecodigo.Constants;
import org.academiadecodigo.terminalGFX.TerminalGFX;

import java.io.IOException;

public class Game {

    //Properties
    private DatabaseManager database;
    private GameStatus gameStatus;
    private TerminalGFX GFX;

    private String theme;
    private int rounds;
    private int currentRound = 1;
    private String[] gameWords;
    private char[] roundWordInChars;

    private Player player1;
    private Player player2;

    //Constructor
    public Game(Player player1, Player player2, String theme, int rounds) {

        this.player1 = player1;
        this.player2 = player2;
        this.theme = theme;
        this.rounds = rounds;
        database = new DatabaseManager();
        gameStatus = new GameStatus();
        try {
            GFX = new TerminalGFX(gameStatus);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Game() {

    }

    //initialize game
    public void init() {

        gameWords = generateGameWords();
        for (String s :
                gameWords) {
            System.out.println("test game init() word: " + s);
        }

        //TODO: test game words. error

        player1.init(gameWords[currentRound - 1].length());
        player2.init(gameWords[currentRound - 1].length());
        gameStatus.setP1Name(player1.getName());
        gameStatus.setP2Name(player2.getName());
        gameStatus.setRounds(rounds);

    }

    public void start() {

        init();

        updateGameStatus();


        System.out.println("Start Game\n");
        sendClientScreen();

        while (!gameWinner()) {

            startRound();
        }

        System.out.println("##########Game Winner##########");

        if (isGameWinner(player1)) {
            System.out.println("Player 1 Wins GAME");
            player1.setGameWinner(true);
        } else {
            System.out.println("Player 2 Wins GAME");
            player2.setGameWinner(true);
        }
        //TODO: waiting for start logic
    }

    private void startRound() {

        System.out.println("Start Round\n");

        resetRoundVariables();
        initRoundWord();

        updateGameStatus();
        sendClientScreen();

        while (!roundWinner()) {

            //MULTI THREAD
            analisePlayerGuess(player1, player1.guessLetter());
            System.out.println("Player 1 Misses: " + player1.getNumberMissedGuesses());
            System.out.println("Player 1 Guesses: " + player1.getNumberGuessedLetters());
            System.out.println("Player 1 Round Points:" + player1.getGamePoints());
            System.out.println("Player 1 Game Points" + player1.getGamePoints());
            System.out.println("###########################\n");
            updateGameStatus();
            sendClientScreen();

            analisePlayerGuess(player2, player2.guessLetter());
            System.out.println("Player 2 Misses: " + player2.getNumberMissedGuesses());
            System.out.println("Player 2 Guesses: " + player2.getNumberGuessedLetters());
            System.out.println("Player 2 Round Points:" + player2.getGamePoints());
            System.out.println("Player 2 Game Points" + player2.getGamePoints());
            System.out.println("###########################\n");
            updateGameStatus();
            sendClientScreen();

            //MULTI THREAD
        }

        System.out.println("#######Round Winner###########");


        if (isRoundWinner(player1)) {

            System.out.println("\n" + player1.getName() + " Wins round " + currentRound);
            player1.setRoundWinner(true);
            player1.incrementGamePoints();
            System.out.println(player1.getGamePoints());

        } else {
            System.out.println("\n" + player2.getName() + "Wins round " + currentRound);
            player2.setRoundWinner(true);
            player2.incrementGamePoints();
            System.out.println(player2.getGamePoints());
        }

        sendRoundResultScreen();

        timer321();

        currentRound++;
        gameStatus.setCurrentsRound(currentRound);
    }

    private void timer321() {
        try {
            Thread.sleep(9000);
            gameStatus.setMessageToAll("3");
            sendClientScreen();

            Thread.sleep(2000);
            gameStatus.setMessageToAll("2");
            sendClientScreen();

            Thread.sleep(2000);
            gameStatus.setMessageToAll("1");
            sendClientScreen();

            Thread.sleep(2000);
            gameStatus.setMessageToAll("");
            sendClientScreen();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void sendRoundResultScreen() {
        String winnerMSG = "SCREEN: YOU WIN!";
        String loserMSG = "SCREEN: YOU LOOSE!";

        if (isRoundWinner(player1)||isRoundLooser(player2)) {

            System.out.println(player1.getName() + winnerMSG);
            System.out.println(player2.getName() + loserMSG);
            gameStatus.setP1Message(winnerMSG);
            gameStatus.setP2Message(loserMSG);
        } else {

            System.out.println(player2.getName() + winnerMSG);
            System.out.println(player1.getName() + loserMSG);
            gameStatus.setP2Message(winnerMSG);
            gameStatus.setP1Message(loserMSG);


        }
        sendClientScreen();
    }

    private void updateGameStatus() {

        //Player 1 update
        gameStatus.setP1Mistakes(player1.getNumberMissedGuesses());
        gameStatus.setP1points(player1.getGamePoints());
        gameStatus.setP1Word(new String(player1.getCorrectGuesses()));
        gameStatus.setP1Guesses(new String(player1.getWrongGuesses()));

        //Player 2 update
        gameStatus.setP2Mistakes(player2.getNumberMissedGuesses());
        gameStatus.setP2points(player2.getGamePoints());
        gameStatus.setP2Word(new String(player2.getCorrectGuesses()));
        gameStatus.setP2Guesses(new String(player2.getWrongGuesses()));

        //Game update
        gameStatus.setRounds(rounds);
        gameStatus.setCurrentsRound(currentRound);
    }

    private void sendClientScreen() {

        try {
            player1.getOut().println(GFX.p1Render());
            player2.getOut().println(GFX.p2Render());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void initRoundWord() {

        roundWordInChars = new char[gameWords[currentRound - 1].length()];

        roundWordInChars = gameWords[currentRound - 1].toCharArray();
    }

    private void resetRoundVariables() {
        player1.init(gameWords[currentRound - 1].length());
        player2.init(gameWords[currentRound - 1].length());

        gameStatus.setP1Message("");
        gameStatus.setP2Guesses("");
    }


    //Methods

    private String[] generateGameWords() {

        gameWords = new String[rounds];
        return database.pickGameWords(theme, rounds);
    }

    private void analisePlayerGuess(Player player, char letter) {

        boolean match = false;

        for (int i = 0; i < roundWordInChars.length; i++) {

            if (letter == roundWordInChars[i]) {

                System.out.println("match");
                player.getCorrectGuesses()[i] = letter;
                System.out.println("Correct guess letter:" + player.getCorrectGuesses()[i]);
                player.incrementNumberGuessedLetters();
                System.out.println("Number correct guesses: " + player.getNumberGuessedLetters());
                match = true;

            }
        }

        if (!match) {
            System.out.println("not match");
            player.getWrongGuesses()[player.getNumberMissedGuesses()] = letter;
            player.incrementNumberMissedGuesses();

        }

        System.out.println(new String(player.getCorrectGuesses()));

    }


//Utils methods

    private boolean gameWinner() {

        return player1.getGamePoints() + player2.getGamePoints() == rounds;

    }

    private boolean roundWinner() {

        return playerWins() || playerLose();
    }

    private boolean playerLose() {
        return player1.getNumberMissedGuesses() == Constants.MAX_NUMBER_WRONG_GUESSES ||
                player2.getNumberMissedGuesses() == Constants.MAX_NUMBER_WRONG_GUESSES;
    }

    private boolean playerWins() {
        return player1.getNumberGuessedLetters() == gameWords[currentRound - 1].length() ||
                player2.getNumberGuessedLetters() == gameWords[currentRound - 1].length();
    }

    private boolean isGameWinner(Player player) {
        System.out.println(player.getName() + ": has " + player.getGamePoints() + " points");
        return player.getGamePoints() > rounds / 2;
    }

    private boolean isRoundWinner(Player player) {
        return player.getNumberMissedGuesses() == gameWords[currentRound - 1].length();
    }

    private boolean isRoundLooser(Player player) {
        return player.getNumberMissedGuesses() == Constants.MAX_NUMBER_WRONG_GUESSES;
    }

    public static char[] initializeArray(char[] array) {

        for (int i = 0; i < array.length; i++) {
            array[i] = '_';
        }
        return array;
    }


//Getters and Setters

    public String[] getGameWords() {
        return gameWords;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
