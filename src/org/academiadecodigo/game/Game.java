package org.academiadecodigo.game;

import org.academiadecodigo.Constants;

public class Game {

    private DatabaseManager database = new DatabaseManager();
    private String[] words;
    private String[] correctGuesses;
    private String[] wrongGuesses = new String[Constants.MAX_NUMBER_WRONG_GUESSES];
    private int currentRound;
    private String theme;

    private Player player1;
    private Player player2;


    public Game(Player player1, Player player2, String theme) {
        this.theme = theme;


    }

    public void start() {

        throw new UnsupportedOperationException();

    }


    public String[] gameWords() {

        words = new String[Constants.TOTAL_ROUNDS];

        for (int i = 0; i < words.length; i++) {
            words[i] = database.pickRandomWord(theme);

        }

        return words;

    }


    private void checkPlayerGuess(Player player) {

        //String[] playerGuess = player.playerGuess();

        if (words[currentRound].matches(player.playerGuess())) {

            //correctGuesses[] = player.playerGuess();
            player.incrementNumberGuessedLetters();

        } else {

            wrongGuesses[player.getNumberMissedGuesses()] = player.playerGuess();
            player.incrementNumberMissedGuesses();

        }

        //Todo: finnish this method receiving String[] from database

    }
}
