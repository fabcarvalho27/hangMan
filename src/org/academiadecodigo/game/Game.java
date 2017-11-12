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
    private int currentRound;
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
        for
            //TODO: test game words. error

        player1.init();
        player2.init();
        gameStatus.setP1Name(player1.getName());
        gameStatus.setP2Name(player2.getName());

    }

    public void start() {

        init();

        updateGameStatus();


        System.out.println("Start Game\n");
        updateClients();

        while (!gameWinner()) {

            System.out.println("Start Round\n");

            startRound();
        }

        System.out.println("Game Winner");

        if (isWinner(player1)) {
            System.out.println("Player 1 Wins");
            player1.setGameWinner(true);
        } else {
            System.out.println("Player 2 Wins");
            player2.setGameWinner(true);
        }
        //TODO: waiting for start logic
    }

    private void updateGameStatus() {

        //Player 1 update
        gameStatus.setP1Mistakes(player1.getNumberMissedGuesses());
        gameStatus.setP1points(player1.getRoundPoints());
        gameStatus.setP1Word("TEST");
        gameStatus.setP1Guesses("TEST");

        //Player 2 update
        gameStatus.setP2Mistakes(player2.getNumberMissedGuesses());
        gameStatus.setP2points(player2.getRoundPoints());
        gameStatus.setP2Word("TEST");
        gameStatus.setP2Guesses("TEST");

        //Game update
        gameStatus.setRounds(rounds);
        gameStatus.setCurrentsRound(currentRound);
    }

    private void updateClients() {

        try {
            player1.getOut().println(GFX.p1Render());
            player2.getOut().println(GFX.p2Render());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void startRound() {

        resetRoundVariables();
        initRoundWord();

        while (!roundWinner()) {

            //MULTI THREAD
            analisePlayerGuess(player1, player1.guessLetter());
            System.out.println("Player 1 Misses: " + player1.getNumberMissedGuesses());
            System.out.println("Player 1 Guesses: " + player1.getNumberGuessedLetters());
            System.out.println("Player 1 Round Points:" + player1.getRoundPoints());
            System.out.println("Player 1 Game Points" + player1.getGamePoints());
            System.out.println("###########################\n");

            analisePlayerGuess(player2, player2.guessLetter());
            System.out.println("Player 2 Misses: " + player2.getNumberMissedGuesses());
            System.out.println("Player 2 Guesses: " + player2.getNumberGuessedLetters());
            System.out.println("Player 2 Round Points:" + player2.getRoundPoints());
            System.out.println("Player 2 Game Points" + player2.getGamePoints());
            System.out.println("###########################\n");

            //player1.getOut().write("hg\n");
            //player1.getOut().flush();
            //player2.getOut().write("hrthreah\n");
            //player2.getOut().flush();
            //MULTI THREAD
        }

        if (isRoundWinner(player1)) {
            System.out.println("\nPlayer 1 Wins round " + currentRound);
            player1.incrementRoundPoints();
        } else {
            System.out.println("\nPLayer 2 Wins round " + currentRound);
            player2.incrementRoundPoints();
        }

        currentRound++;
    }

    private void initRoundWord() {

        roundWordInChars = new char[gameWords[currentRound].length()];

        roundWordInChars = gameWords[currentRound].toCharArray();
    }

    private void resetRoundVariables() {
        player1.init();
        player2.init();
    }

    private boolean isRoundWinner(Player player) {
        return player.getNumberGuessedLetters() == gameWords[currentRound].length();
    }


    //Methods

    private String[] generateGameWords() {

        gameWords = new String[rounds];
        return database.pickGameWords(theme, rounds);
    }


    private void analisePlayerGuess(Player player, char letter) {

        for (int i = 0; i < roundWordInChars.length; i++) {

            if (letter == roundWordInChars[i]) {

                System.out.println("match");
                player.getCorrectGuesses()[i] = letter;
                player.incrementNumberGuessedLetters();


            } else {

                System.out.println("not match");

                player.getWrongGuesses()[i] = letter;
                player.incrementNumberMissedGuesses();
            }
        }
    }

    //Utils methods

    private boolean gameWinner() {

        return player1.getGamePoints() + player2.getGamePoints() == rounds;
    }

    private boolean roundWinner() {

        return playerWins() || playerLose();
    }

    private boolean playerLose() {
        return player1.getWrongGuesses().length == Constants.MAX_NUMBER_WRONG_GUESSES ||
                player2.getWrongGuesses().length == Constants.MAX_NUMBER_WRONG_GUESSES;
    }

    private boolean playerWins() {
        return player1.getCorrectGuesses().length == gameWords[currentRound].length() ||
                player2.getCorrectGuesses().length == gameWords[currentRound].length();
    }

    private boolean isWinner(Player player) {
        return rounds / player.getGamePoints() < 2.5;
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
