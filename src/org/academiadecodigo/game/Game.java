package org.academiadecodigo.game;

import org.academiadecodigo.Constants;
import org.academiadecodigo.terminalGFX.TerminalGFX;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private volatile boolean acceptingGuesses;

    //Constructor
    public Game(Socket player1Socket, Socket player2Socket, String theme, int rounds) {

        this.player1 = new Player(player1Socket, "Eduardo", this);
        this.player2 = new Player(player2Socket, "Fabio", this);
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
        gameStatus.setTheme(theme);
        gameStatus.setMessageToAll("");

    }

    public void start() {

        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.submit(player1);
        threadPool.submit(player2);

        init();

        updateGameStatus();


        System.out.println("Start Game\n");
        sendClientsScreen();

        while (!gameWinner()) {
            acceptingGuesses = true;

            startRound();
        }

        System.out.println("##########Game Winner##########");

        if (isGameWinner(player1)) {
            System.out.println(player1.getName() + " Wins GAME");
            player1.setGameWinner(true);
            gameStatus.setMessageToAll(player1.getName() + " IS THE WINNER!!!" + " " + player2.getName() + " sucks..");
        } else {
            System.out.println(player2.getName() + " Wins GAME");
            player2.setGameWinner(true);
            gameStatus.setMessageToAll(player2.getName() + " IS THE WINNER!!!" + " " + player1.getName() + " sucks..");
        }

        sendClientsScreen();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("###GAME OVER");

        gameStatus.setMessageToAll("#### GAMEOVER ###");
        sendClientsScreen();


    }

    private void startRound() {

        time321();

        System.out.println("Start Round\n");

        resetRoundVariables();
        initRoundWord();

        updateGameStatus();
        sendClientsScreen();

        System.out.println("before round\n");

        while (!roundWinner()) {

            acceptingGuesses(true);

            System.out.println("accepting guesses: " + acceptingGuesses);

            // System.out.println("\ninside round");
            //System.out.println("Player wins:" + playerWins());
            //System.out.println("player looses: " + playerLose());
            // System.out.println("Round winner: " + roundWinner());

/*
            //MULTI THREAD
            //turn(player1);

            analisePlayerGuess(player1, player1.guessLetter());
            System.out.println("Player 1 Misses: " + player1.getNumberMissedGuesses());
            System.out.println("Player 1 Guesses: " + player1.getNumberGuessedLetters());
            System.out.println("Player 1 Round Points:" + player1.getGamePoints());
            System.out.println("Player 1 Game Points" + player1.getGamePoints());
            System.out.println("###########################\n");
            updateGameStatus();
            sendClientsScreen();

            //turn(player2);
            analisePlayerGuess(player2, player2.guessLetter());
            System.out.println("Player 2 Misses: " + player2.getNumberMissedGuesses());
            System.out.println("Player 2 Guesses: " + player2.getNumberGuessedLetters());
            System.out.println("Player 2 Round Points:" + player2.getGamePoints());
            System.out.println("Player 2 Game Points" + player2.getGamePoints());
            System.out.println("###########################\n");
            updateGameStatus();
            sendClientsScreen();

            //MULTI THREAD
            */


        }
        acceptingGuesses(false);

        System.out.println("#######Round Winner###########");
        String winRound = "You win this round";
        String looseRound = "You loose this round";


        if (isRoundWinner(player1)) {

            System.out.println("\n" + player1.getName() + " Wins round " + currentRound);
            player1.setRoundWinner(true);
            player1.incrementGamePoints();

            System.out.println(player1.getGamePoints());

            gameStatus.setMessageToAll(player1.getName() + " is wins round " + currentRound);
            gameStatus.setP1Message(winRound);
            gameStatus.setP2Message(looseRound);

        } else {
            System.out.println("\n" + player2.getName() + " Wins round " + currentRound);
            player2.setRoundWinner(true);
            player2.incrementGamePoints();

            System.out.println(player2.getGamePoints());

            gameStatus.setMessageToAll(player2.getName() + " wins round " + currentRound);
            gameStatus.setP1Message(looseRound);
            gameStatus.setP2Message(winRound);
        }

        try {

            sendRoundResultScreen();
            Thread.sleep(2000);

            gameStatus.setMessageToAll(" Word: " + gameWords[currentRound - 1]);
            sendClientsScreen();
            Thread.sleep(2000);

            gameStatus.setMessageToAll("");
            gameStatus.setP1Message("");
            gameStatus.setP2Message("");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//time321

        currentRound++;
        gameStatus.setCurrentsRound(currentRound);
    }

    private void turn(Player player) {

/*
        if(player == player1) {
            //gameStatus.setP1Message();
        }


        //sendClientsScreen();
        */
    }

    private void time321() {
        try {
            gameStatus.setTimeSlot("3");
            sendClientsScreen();
            Thread.sleep(1000)
            ;
            gameStatus.setTimeSlot("2");
            sendClientsScreen();
            Thread.sleep(1000);

            gameStatus.setTimeSlot("1");
            sendClientsScreen();
            Thread.sleep(1000);

            gameStatus.setTimeSlot("PLAY!!");
            gameStatus.setP1Message("");
            gameStatus.setP2Message("");
            sendClientsScreen();
            Thread.sleep(1000);

            gameStatus.setTimeSlot("");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void sendRoundResultScreen() {
        String winnerMSG = " YOU WIN!";
        String loserMSG = " YOU LOOSE!";

        if (isRoundWinner(player1) || isRoundLooser(player2)) {

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
        sendClientsScreen();
    }

    public void updateGameStatus() {

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

    public void sendClientsScreen() {

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
        gameStatus.setP2Message("");
    }


    //Methods

    private String[] generateGameWords() {

        gameWords = new String[rounds];
        return database.pickGameWords(theme, rounds);
    }

    public void analisePlayerGuess(Player player, char letter) {

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


    private void acceptingGuesses(boolean value) {
        acceptingGuesses = value;
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

    public boolean isAcceptingGuesses() {
        return acceptingGuesses;
    }
}
