package org.academiadecodigo.game;

public class GameStatus {

    public static int[] mistakesArray = new int[2];
    public static int[] pointsArray;


    private String p1Name= "PLAYER 1";
    private int p1Mistakes = 4;
    private int p1points = 100;
    private String p1Word = "P A L A V R A";
    private String p1Guesses = " A B C ";


    private int p2Mistakes = 3;

    private int p2points = 200;

    private String theme = " TEMA";
    private String messageToAll= "MESSAGE TO EVERYBODY";
    private int rounds = 1;






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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getMessageToAll() {
        return messageToAll;
    }

    public void setMessageToAll(String messageToAll) {
        this.messageToAll = messageToAll;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }


    //my idea is arraylist of chars
    //Game info


}
