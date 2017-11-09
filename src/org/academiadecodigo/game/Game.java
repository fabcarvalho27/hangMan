package org.academiadecodigo.game;

import org.academiadecodigo.Constants;

public class Game {

    private DatabaseManager database = new DatabaseManager();

    private String theme;
    private int rounds;
    private int currentRound;
    private String[] gameWords;
    private String[] correctGuesses;
    private String[] wrongGuesses;

    private Player player1;
    private Player player2;


    public Game(Player player1, Player player2, String theme, int rounds) {

        this.theme = theme;
        this.rounds = rounds;
    }

    public void init() {

    }

    public void start() {

        correctGuesses = new String[gameWords[currentRound].length()];
        wrongGuesses = new String[Constants.MAX_NUMBER_WRONG_GUESSES];

        while (!Winner()){



        }


    }


    private String[] gameWords() {

        gameWords = new String[rounds];
        return database.pickGameWords(theme, rounds);
    }


    private void checkPlayerGuess(Player player) {

        String playerGuess = player.playerGuess();

        if (matches(playerGuess)) {

            correctGuesses[player.getNumberGuessedLetters()] = playerGuess;
            player.incrementNumberGuessedLetters();

        } else {

            wrongGuesses[player.getNumberMissedGuesses()] = playerGuess;
            player.incrementNumberMissedGuesses();
        }
    }

    //Utils methods
    private boolean matches(String playerGuess) {

        return gameWords[currentRound].matches(playerGuess);
    }

    private boolean winner(){


    }


    //Getters and Setters

    public String[] getGameWords() {
        return gameWords;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public String[] getWrongGuesses() {
        return wrongGuesses;
    }
}
