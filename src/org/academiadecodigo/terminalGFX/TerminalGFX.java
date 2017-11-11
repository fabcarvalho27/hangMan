package org.academiadecodigo.terminalGFX;


import org.academiadecodigo.game.GameStatus;

import java.io.IOException;

public class TerminalGFX {

    private int width = 70;
    private int height = 30;
    private char[][] p1ScreenArray = new char[width][height];
    private char[][] p2ScreenArray = new char[width][height];

    private int topPadding = 11;
    private GameStatus gameStatus;

    private TextReader textReader;


    public TerminalGFX(GameStatus gameStatus) throws IOException {


        this.textReader = new TextReader();
       this.gameStatus = gameStatus;
        // mountp1Screen();
    }


    public void test() throws IOException {

        System.out.println(p1Render());
        }

    public void render() throws IOException {
        mountp1Screen();
        mountp2Screen();
    }

    public String p1Render() throws IOException {

        mountp1Screen();
        return arrayToString(p1ScreenArray);
    }

    public String p2Render() throws IOException {

        mountp2Screen();
        return arrayToString(p2ScreenArray);
    }

    public String p1Screen(){
        return arrayToString(p1ScreenArray);
    }

    public String p2Screen(){

        return arrayToString(p2ScreenArray);
    }

    private void mountp1Screen() throws IOException {

        initializeArray(p1ScreenArray);

        wordToScreen(p1ScreenArray, gameStatus.getP1Name(), width - 18, topPadding + 1);                //p1 name
        arrayToScreen(p1ScreenArray,dummy(gameStatus.getP1Mistakes()), width - 15, topPadding + 3);     //p1 hangman
        wordToScreen(p1ScreenArray, "P1Guesses: " + gameStatus.getP1Guesses(), 0, topPadding + 1);//p1 guesses
        wordToScreen(p1ScreenArray, gameStatus.getP1Word(), 12, topPadding + 10);                       //p1 word shown

        wordToScreen(p1ScreenArray, gameStatus.getP2Name(), width - 18, topPadding + 12);                      //oponent name
        arrayToScreen(p1ScreenArray,dummy(gameStatus.getP2Mistakes()), width - 15, topPadding + 14);     //oponent hangman

        arrayToScreen(p1ScreenArray,stringToArray(textReader.returnLogo()), 4, 0);                       //LOGO
        wordToScreen(p1ScreenArray, "Rounds: " + gameStatus.getRounds(), 0, topPadding + 3);       //rounds
        wordToScreen(p1ScreenArray, gameStatus.getMessageToAll(), 0, topPadding + 15);                   //message from game to all

        //arrayToScreen(stringToArray(Menus.menuEntrance),2,2);
        //arrayToScreen(stringToArray(DrawingsDepot.logo), 4, 0);
    }

    private void mountp2Screen() throws IOException {

        initializeArray(p2ScreenArray);

        wordToScreen(p2ScreenArray, gameStatus.getP2Name(), width - 18, topPadding + 1);
        arrayToScreen(p2ScreenArray, dummy(gameStatus.getP2Mistakes()), width - 15, topPadding + 3);
        wordToScreen(p2ScreenArray, "P2Guesses: " + gameStatus.getP2Guesses(), 0, topPadding + 1);
        wordToScreen(p2ScreenArray, gameStatus.getP2Word(), 12, topPadding + 10);

        wordToScreen(p2ScreenArray, gameStatus.getP1Name(), width - 18, topPadding + 12);
        arrayToScreen(p2ScreenArray, dummy(gameStatus.getP1Mistakes()), width - 15, topPadding + 14);

        arrayToScreen(p2ScreenArray, stringToArray(textReader.returnLogo()), 4, 0);
        wordToScreen(p2ScreenArray, "Rounds: " + gameStatus.getRounds(), 0, topPadding + 3);
        wordToScreen(p2ScreenArray, gameStatus.getMessageToAll(), 0, topPadding + 15);
    }


    private void initializeArray(char[][] array) {

        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {

                array[i][j] = ' ';
            }
        }
    }


    private void printArray(char[][] array) {

        String out = "";

        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {

                out = out + array[i][j];
            }
            out = out + "\n";
        }

        System.out.println(out);
    }


    private char[][] dummy(int mistakes) {

        int dWidth = 3;
        int dHeigth = 4;

        char[][] dummy = new char[dWidth][dHeigth];
        initializeArray(dummy);


        switch (mistakes) {

            case 4:
                dummy[0][3] = '/';
                dummy[2][3] = '\\';  //legs

            case 3:
                dummy[0][1] = dummy[2][1] = '-';        //arms

            case 2:
                dummy[1][1] = dummy[1][2] = '|';        //torso

            case 1:
                dummy[1][0] = 'O';                      //head

            case 0:

        }

        return dummy;

    }


    private void arrayToScreen(char[][] array, char[][] drawing, int x, int y /*String sector*/) {

        for (int j = 0; j < drawing[0].length; j++) {
            for (int i = 0; i < drawing.length; i++) {

                array[x + i][y + j] = drawing[i][j];
            }
        }

    }


    private void wordToScreen(char[][] ScreenArray, String word, int x, int y) {

        char[] array = word.toCharArray();

        for (int i = 0; i < array.length; i++) {

            ScreenArray[x + i][y] = array[i];
        }
    }


    public char[][] stringToArray(String string) {

        String[] lines = string.split("\n");

        char[][] chars = null;

/*
        for (int y = 1; y < lines.length; y++) {

            char[] lineChars = lines[y].toCharArray();

            if (chars == null) {

                chars = new char[lineChars.length][lines.length];
            }

            for (int x = 0; x < lineChars.length; x++) {

                chars[x][y] = lineChars[x];
            }

            int x = 0;
            for (char c : lineChars) {

                chars[x++][y] = c;
            }
        }

        System.out.println(chars.length + ", " + chars[0].length);
        for (int a = 0; a < lines.length; a++) {
            System.out.println(lines[a].toCharArray().length);
        }
*/
        for (int j = 1; j < lines.length; j++) {
            for (int i = 0; i < lines[1].toCharArray().length; i++) {
                if (chars == null) {

                    chars = new char[lines[j].toCharArray().length][lines.length];
                }
                chars[i][j] = lines[j].toCharArray()[i];
            }
        }
        return chars;
    }


    private String arrayToString(char[][] array) {

        String out = "";

        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {

                out = out + array[i][j];
            }
            out = out + "\n";
        }
        return out;

    }

    public char[][] getP1ScreenArray() {

        return p1ScreenArray;
    }
}


