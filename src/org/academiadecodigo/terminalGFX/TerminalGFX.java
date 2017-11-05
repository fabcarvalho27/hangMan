package org.academiadecodigo.terminalGFX;


public class TerminalGFX {

    private String block = "#";
    private int width = 20;
    private int height = 10;
    private String[][] outputScreenArray = new String[width][height];


    public void run() {

        //System.out.println(outputScreen());
        printOutputScreenArray();
        System.out.println("");

        printArray(dummy());

    }


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

        initializeArray(outputScreenArray);

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


    public String[][] dummy() {

        int dWidth = 3;
        int dHeigth = 4;

        String[][] dummy = new String[dWidth][dHeigth];
        initializeArray(dummy);

        dummy[1][0] = "O";                      //head
        dummy[1][1] = dummy[1][2] = "|";        //torso
        dummy[0][1] = dummy[2][1] = "-";        //arms
        dummy[0][3] = "/";
        dummy[2][3] = "\\";  //legs

        return dummy;

    }


    public void insertDrawingIntoArray(String[][] drawing, String[][] array /*, String sector*/) {


    }


}
