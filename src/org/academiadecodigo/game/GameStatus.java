package org.academiadecodigo.game;

public class GameStatus {

    public static int[] mistakesArray = new int[2];
    public static int[] pointsArray;


    private int p1Mistakes = 4;
    private int p2Mistakes = 3;
    private String word = "P A L A V R A";
    private String guesses = " A B C ";

    private int p1points = 100;
    private int p2points = 200;
    private int rounds = 1;






    public int getP1Mistakes() {
        return p1Mistakes;
    }

    public int getP2Mistakes() {
        return p2Mistakes;
    }

    public String getWord() {
        return word;
    }

    public String getGuesses() {
        return guesses;
    }

    public int getP1points() {
        return p1points;
    }

    public int getP2points() {
        return p2points;
    }

    public int getRounds() {
        return rounds;
    }


    //my idea is arraylist of chars
    //Game info


}
