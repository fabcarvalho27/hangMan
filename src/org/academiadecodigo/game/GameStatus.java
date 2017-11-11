package org.academiadecodigo.game;

public class GameStatus {

    public static int[] mistakesArray = new int[2];
    public static int[] pointsArray;


    private String p1Name = "PLAYER 1";
    private int p1Mistakes = 4;
    private int p1points = 100;
    private String p1Word = "P A L A V R A p1";
    private String p1Guesses = " A B C  do p1";

    private String p2Name = " PLAYER 2";
    private int p2Mistakes = 3;
    private int p2points = 200;
    private String p2Word = "P A L A V R A  p2";
    private String p2Guesses = "D E F do p2";

    private String theme = " TEMA";
    private String messageToAll = "MESSAGE TO EVERYBODY";
    private int rounds = 1;


    private String gameMode;
    private int currentsRound;

    //Player info
    private int points;
    private String[] playerNames;
    private int numberMissedGueses;



    public String getGameMode() {
        return gameMode;
    }

    public int getP1Mistakes() {
        return p1Mistakes;
    }

    public int getP2Mistakes() {
        return p2Mistakes;
    }

    public String getP1Name() {
        return p1Name;
    }

    public void setP1Name(String p1Name) {
        this.p1Name = p1Name;
    }

    public int getP1points() {
        return p1points;
    }

    public void setP1points(int p1points) {
        this.p1points = p1points;
    }

    public String getP1Word() {
        return p1Word;
    }

    public void setP1Word(String p1Word) {
        this.p1Word = p1Word;
    }

    public String getP1Guesses() {
        return p1Guesses;
    }

    public void setP1Guesses(String p1Guesses) {
        this.p1Guesses = p1Guesses;
    }

    public String getMessageToAll() {
        return messageToAll;
    }

    public void setMessageToAll(String messageToAll) {
        this.messageToAll = messageToAll;
    }

    public String getP2Name() {
        return p2Name;
    }

    public void setP2Name(String p2Name) {
        this.p2Name = p2Name;
    }

    public int getP2points() {
        return p2points;
    }

    public void setP2points(int p2points) {
        this.p2points = p2points;
    }

    public String getP2Word() {
        return p2Word;
    }

    public void setP2Word(String p2Word) {
        this.p2Word = p2Word;
    }

    public String getP2Guesses() {
        return p2Guesses;
    }

    public void setP2Guesses(String p2Guesses) {
        this.p2Guesses = p2Guesses;
    }


    //my idea is arraylist of chars
    //Game info

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
