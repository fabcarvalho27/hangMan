package org.academiadecodigo.game;

public class GameStatus {

    //Game info
    private String gameMode;
    private String theme;
    private int rounds;
    private int currentsRound;
    private String word;

    //Player info
    private int points;
    private String[] playerNames;
    private int numberMissedGueses;

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getCurrentsRound() {
        return currentsRound;
    }

    public void setCurrentsRound(int currentsRound) {
        this.currentsRound = currentsRound;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

    public int getNumberMissedGueses() {
        return numberMissedGueses;
    }

    public void setNumberMissedGueses(int numberMissedGueses) {
        this.numberMissedGueses = numberMissedGueses;
    }
}
