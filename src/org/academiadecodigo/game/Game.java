package org.academiadecodigo.game;

import org.academiadecodigo.Constants;
import org.academiadecodigo.terminalGFX.TerminalGFX;

import java.io.IOException;

public class Game {

    //Properties
    private DatabaseManager database = new DatabaseManager();

    private String theme;
    private int rounds;
    private int currentRound;
    private String[] gameWords;

    private Player player1;
    private Player player2;


    private char p1Guess;
    private char[] p1WordShown;
    private GameStatus gameStatus;
    private TerminalGFX terminalGFX;


    //Constructor
    public Game(Player player1, Player player2, String theme, int rounds) {

        this.player1 = player1;
        this.player2 = player2;
        this.theme = theme;
        this.rounds = rounds;
    }

    public Game(Player player1, String theme, int rounds) {

        this.player1 = player1;
        this.player2 = player2;
        this.theme = theme;
        this.rounds = rounds;
    }

    //initialize game
    public void init() {

        gameStatus = new GameStatus();
        gameWords = generateGameWords();

        p1WordInit();




        try {
            terminalGFX = new TerminalGFX();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //player1.connect();
        //player2.connect();
    }



    public void start() throws IOException {

        int p1mistakes = 0;

        while (true) {

            System.out.println("inside game");
            System.out.println("word to be found: " + gameWords[0]);
            player1.outputMessage(terminalGFX.p1Render(gameStatus));

            while (true) {

                System.out.println("\n inside round\n");

                //BLOCK
                p1Guess = player1.guessLetter();
                //BLOCK
                System.out.println(p1Guess);

                p1WordShown = checkGuess(p1Guess, gameWords[0].toCharArray());


                System.out.println(p1WordShown);
                gameStatus.setP1Word(new String(p1WordShown));

                //player1.outputMessage(new String(p1WordShown));


                p1mistakes++;
                gameStatus.setP1Mistakes(p1mistakes);

                    player1.outputMessage(terminalGFX.p1Render(gameStatus));

                System.out.println("after render");

                //BLOCK
                //  analisePlayerGuess(player1, player1.guessLetter());
                //  analisePlayerGuess(player2, player2.guessLetter());


            }
        }
        //TODO: waiting for start logic
    }


    //Methods
    private String[] generateGameWords() {

        gameWords = new String[rounds];
        return database.pickGameWords(theme, rounds);
    }


    private void analisePlayerGuess(Player player, String letter) {

        if (isLetterMatchingWord(letter)) {

            player.getCorrectGuesses().add(letter);
            player.incrementNumberGuessedLetters();

        } else {

            player.getWrongGuesses()[player.getNumberMissedGuesses()] = letter;
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
        return player1.getWrongGuesses().length == Constants.MAX_NUMBER_WRONG_GUESSES ||
                player2.getWrongGuesses().length == Constants.MAX_NUMBER_WRONG_GUESSES;
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


    public char[] checkGuess(char guess, char[] wordToGuess) {

        char[] guessedArray = new char[wordToGuess.length];
        guessedArray = p1WordShown;

        for (int i = 0; i < wordToGuess.length; i++) {
            if (wordToGuess[i] == guess) {
                guessedArray[i] = guess;
            }
        }
        return guessedArray;
    }


    public char[] initializeArray(char[] array) {

        for (int i = 0; i < array.length; i++) {
            array[i] = '_';
        }
        return array;
    }


    private void p1WordInit() {
        p1WordShown = new char[gameWords[0].length()];
        p1WordShown = initializeArray(p1WordShown);
        gameStatus.setP1Word(new String(p1WordShown));
    }

}
