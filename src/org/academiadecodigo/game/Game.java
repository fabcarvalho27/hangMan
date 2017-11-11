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

        System.out.println("Start Game\n");

        while (!gameWinner()) {

            System.out.println("Start Round\n");

            player1.init();
            player2.init();

            //TODO: working on player init status
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

    private void startRound() {

        while (!roundWinner()) {

            //MULTI THREAD
            analisePlayerGuess(player1, player1.guessLetter());
            player1.getOut().write("fode-te\n");
            player1.getOut().flush();
            player2.getOut().write("o outro fodeu-se\n");
            player2.getOut().flush();
            analisePlayerGuess(player2, player2.guessLetter());
            player1.getOut().write("hg\n");
            player1.getOut().flush();
            player2.getOut().write("hrthreah\n");
            player2.getOut().flush();
            //MULTI THREAD
        }
        if(isRoundWinner(player1)){
            System.out.println("Player 1 Wins round " + currentRound);
            player1.incrementRoundPoints();
        } else{
            System.out.println("PLayer 2 Wins round " + currentRound);
            player2.incrementRoundPoints();
        }
    }

    private boolean isRoundWinner(Player player) {
        return player.getRoundPoints() == gameWords[currentRound].length();
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

        return player1.getGamePoints() + player2.getGamePoints() == rounds;
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
}
