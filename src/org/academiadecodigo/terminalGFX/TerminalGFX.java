package org.academiadecodigo.terminalGFX;


import org.academiadecodigo.game.GameStatus;

import java.io.IOException;

public class TerminalGFX {

    private int width = 70;
    private int height = 30;
    private char[][] outputScreenArray = new char[width][height];
    private int topPadding = 11;

    private TextReader textReader;
    // List<String> lines = new ArrayList<String>();


    public TerminalGFX() throws IOException {

        this.textReader = new TextReader();
        mountOutputScreen();

    }


    public void test() throws IOException {
        //System.out.println(DrawingsDepot.logo);
        render();
    }


    private void mountOutputScreen() throws IOException {

        initializeArray(outputScreenArray);
        wordToScreen("Player 2", width - 18, topPadding + 12);
        arrayToScreen(dummy(GameStatus.p2Mistakes), width - 15, topPadding + 14);

        wordToScreen("Player 1", width - 18, topPadding + 1);
        arrayToScreen(dummy(GameStatus.p1Mistakes), width - 15, topPadding + 3);

        wordToScreen("Guesses: " + GameStatus.guesses, 0, topPadding + 1);
        wordToScreen("Rounds: " + GameStatus.rounds, 0, topPadding + 3);
        wordToScreen(GameStatus.word, 12, topPadding + 10);

        //arrayToScreen(stringToArray(Menus.menuEntrance),2,2);
        //arrayToScreen(stringToArray(DrawingsDepot.logo), 4, 0);

        //stringToArray(DrawingsDepot.logo);
        arrayToScreen(stringToArray(textReader.returnLogo()), 4, 0);

    }


    public String render() {

        return arrayToString(getOutputScreenArray());

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


    private char[][] insertDrawingIntoArray(char[][] drawing, char[][] array, int x, int y /*String sector*/) {

        for (int j = 0; j < drawing[0].length; j++) {
            for (int i = 0; i < drawing.length; i++) {

                array[x + i][y + j] = drawing[i][j];
            }
        }

        return array;
    }


    private void arrayToScreen(char[][] drawing, int x, int y) {

        this.outputScreenArray = insertDrawingIntoArray(drawing, outputScreenArray, x, y);

    }


    private void wordToScreen(String word, int x, int y) {

        char[] array = word.toCharArray();

        for (int i = 0; i < array.length; i++) {

            this.outputScreenArray[x + i][y] = array[i];
        }
    }


    //TODO
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

    public char[][] getOutputScreenArray() {

        return outputScreenArray;
    }
}


