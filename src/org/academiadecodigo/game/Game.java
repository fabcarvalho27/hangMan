package org.academiadecodigo.game;

import org.academiadecodigo.Constants;

public class Game {

    //Properties
    private DatabaseManager database = new DatabaseManager();
    private GameStatus gameStatus = new GameStatus();

    private String theme;
    private int rounds;
    private int currentRound;
    private String[] gameWords;

    private Player player1;
    private Player player2;

    //Constructor
    public Game(Player player1, Player player2, String theme, int rounds) {

        this.player1 = player1;
        this.player2 = player2;
        this.theme = theme;
        this.rounds = rounds;
    }

    public Game(Player player1, String theme, int rounds) {

        this.player1 = player1;
        this.theme = theme;
        this.rounds = rounds;
    }

    //initialize game
    public void init() {

        gameWords = generateGameWords();

        player1.init();
        player2.init();

        //BLOCKING
        //player1.connect();
        //player2.connect();
        //BLOCKING
    }

    public void start() {

        while (!gameWinner()) {

            System.out.println("Inside Game");
            while (!roundWinner()) {

                System.out.println("Inside round");

                //MULTI THREAD
                analisePlayerGuess(player1, player1.guessLetter());
                analisePlayerGuess(player2, player2.guessLetter());
                //MULTI THREAD
            }
        }
        //TODO: waiting for start logic
    }


    //Methods
    private String[] generateGameWords() {

        return new String[]{
                "o",
                "p",
                "b",
        };

        //gameWords = new String[rounds];
        //return database.pickGameWords(theme, rounds);
    }


    private void analisePlayerGuess(Player player, String letter) {

        if (isLetterMatchingWord(letter)) {

            System.out.println("match");
            player.getCorrectGuesses().add(letter);
            player.incrementNumberGuessedLetters();

        } else {

            System.out.println("not match");

            player.getWrongGuesses().add(letter);
            player.incrementNumberMissedGuesses();
        }
    }

    //Utils methods
    private boolean isLetterMatchingWord(String playerGuess) {

        return playerGuess.matches(gameWords[currentRound]);
    }

    private boolean gameWinner() {

        return player1.getPoints() + player2.getPoints() == rounds;
    }

    private boolean roundWinner() {

        return playerWins() || playerLose();
    }

    private boolean playerLose() {
        return player1.getWrongGuesses().size() == Constants.MAX_NUMBER_WRONG_GUESSES ||
                player2.getWrongGuesses().size() == Constants.MAX_NUMBER_WRONG_GUESSES;
    }

    private boolean playerWins() {
        return player1.getCorrectGuesses().size() == gameWords[currentRound].length() ||
                player2.getCorrectGuesses().size() == gameWords[currentRound].length();
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
}
