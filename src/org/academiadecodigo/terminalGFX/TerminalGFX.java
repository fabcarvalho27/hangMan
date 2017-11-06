package org.academiadecodigo.terminalGFX;


import org.academiadecodigo.game.Game;

public class TerminalGFX {

    private String block = "#";
    private int width = 50;
    private int height = 20;
    private String[][] outputScreenArray = new String[width][height];


    public void run() {

        mountOutputScreen();

        printOutputScreenArray();

    }

/*
    public String giveScreenChar(GameStatus gameStatus) {

        String str = "";

        return str;
    }


    public String[][] giveScreenArray(GameStatus gameStatus) {

        return outputScreenArray; //TODO
    }
*/

    public void initializeArray(String[][] array) {

        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {

                array[i][j] = ".";
            }
        }
    }


    public void printArray(String[][] array) {

        String out = "";

        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {

                out = out + array[i][j];
            }
            out = out + "\n";
        }

        System.out.println(out);
    }


    public void printOutputScreenArray() {


        outputScreenArray[0][0] = "H";
        outputScreenArray[19][9] = "u";
        outputScreenArray[0][height - 1] = "<";
        outputScreenArray[width - 1][height - 1] = ">";

        printArray(outputScreenArray);
    }

/*
    public String outputScreen() {

        int width = 100;

        String result = repeatString(block, width) + "\n" +
                repeatString("\n", 10) +
                repeatString(block, width);

        return result;
    }
*/

    public String repeatString(String string, int times) {

        StringBuilder stringBuilder = new StringBuilder(times);

        for (int i = 0; i < times; ++i) {

            stringBuilder.append(string);
        }

        return stringBuilder.toString();
    }


    public String[][] dummy(int mistakes) {

        int dWidth = 3;
        int dHeigth = 4;

        String[][] dummy = new String[dWidth][dHeigth];
        initializeArray(dummy);


        switch (mistakes) {

            case 4:
                dummy[0][3] = "/";
                dummy[2][3] = "\\";  //legs

            case 3:
                dummy[0][1] = dummy[2][1] = "-";        //arms

            case 2:
                dummy[1][1] = dummy[1][2] = "|";        //torso

            case 1:
                dummy[1][0] = "O";                      //head

            case 0:

        }

        return dummy;

    }


    public String[][] insertDrawingIntoArray(String[][] drawing, String[][] array, int x, int y /*String sector*/) {

        for (int j = 0; j < drawing[0].length; j++) {
            for (int i = 0; i < drawing.length; i++) {

                array[x + i][y + j] = drawing[i][j];
            }
        }

        return array;
    }


    public void insertDrawingIntoOutputScreen(String[][] drawing, int x, int y) {

        this.outputScreenArray = insertDrawingIntoArray(drawing, outputScreenArray, x, y);

    }


    public void insertWordsIntoArray() {
    }


    public void mountOutputScreen() {

        initializeArray(outputScreenArray);

        insertDrawingIntoOutputScreen(dummy(0), 1, 1);
        insertDrawingIntoOutputScreen(dummy(2), 10, 10);

        insertDrawingIntoOutputScreen(dummy(3), 30, 10);

        insertDrawingIntoOutputScreen(dummy(4), 30, 1);
    }

}
