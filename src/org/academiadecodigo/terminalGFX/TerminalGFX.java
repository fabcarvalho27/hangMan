package org.academiadecodigo.terminalGFX;


import org.academiadecodigo.game.GameStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalGFX {

    private String block = "#";
    private int width = 70;
    private int height = 20;
    private String[][] outputScreenArray = new String[width][height];

    private TextReader textReader;
    List<String> lines = new ArrayList<String>();

    public TerminalGFX() throws IOException {

        this.textReader = new TextReader();
        mountOutputScreen();

    }


    public void test() throws IOException {
        //printOutputScreenArray();
        //printArray(stringToArray(textReader.returnLogo()));
        //printArray(stringToArray("12\n98\00"));
        // printArray(stringToArray(" 2\n98\n 0"));
        //printArray(stringToArray(textReader.returnLogo()));
        System.out.println(render());

        stupidMethod();
    }

    public void stupidMethod() throws FileNotFoundException {

        Scanner sc = new Scanner(new File("/Users/codecadet/workspace/projects/hangMan/src/org/academiadecodigo/terminalGFX/logo"));

        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        String[] arr = lines.toArray(new String[0]);
        System.out.println(arr[1]);
    }


    private void mountOutputScreen() throws IOException {

        initializeArray(outputScreenArray);
        wordToScreen("Player 2", width - 12, 12);
        arrayToScreen(dummy(GameStatus.p2Mistakes), width - 10, 14);

        wordToScreen("Player 1", width - 12, 1);
        arrayToScreen(dummy(GameStatus.p1Mistakes), width - 10, 3);

        wordToScreen("Guesses: " + GameStatus.guesses, 0, 1);
        wordToScreen("Rounds: " + GameStatus.rounds, 0, 3);
        wordToScreen(GameStatus.word, 5, 10);

        // arrayToScreen(stringToArray("12\n98\n00"),0,0);
        //arrayToScreen(dummy(4),0,0);
        // arrayToScreen(stringToArray("12\n98\n00"),3,3);
        // arrayToScreen(stringToArray(textReader.returnLogo()),3,3);


    }


    public String[][] getOutputScreenArray() {

        return outputScreenArray;
    }


    public String render() {

        return arrayToString(getOutputScreenArray());

    }


    private void initializeArray(String[][] array) {

        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {

                array[i][j] = ".";
            }
        }
    }


    private void printArray(String[][] array) {

        String out = "";

        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {

                out = out + array[i][j];
            }
            out = out + "\n";
        }

        System.out.println(out);
    }


    private String[][] dummy(int mistakes) {

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


    private String[][] insertDrawingIntoArray(String[][] drawing, String[][] array, int x, int y /*String sector*/) {

        for (int j = 0; j < drawing[0].length; j++) {
            for (int i = 0; i < drawing.length; i++) {

                array[x + i][y + j] = drawing[i][j];
            }
        }

        return array;
    }


    private void arrayToScreen(String[][] drawing, int x, int y) {

        this.outputScreenArray = insertDrawingIntoArray(drawing, outputScreenArray, x, y);

    }


    private void wordToScreen(String word, int x, int y) {

        String[] array = word.split("");

        for (int i = 0; i < array.length; i++) {

            outputScreenArray[x + i][y] = array[i];
        }
    }


    //TODO
    public String[][] stringToArray(String string) {
        System.out.println(string);

        String[] lines = string.split("\n");

        System.out.println("*" + lines[0] + "*");


        String[][] result = new String[lines[0].split("").length][lines.length];


        for (int j = 0; j < lines.length; j++) {
            for (int i = 0; i < lines[0].split("").length; i++) {
                result[i][j] = lines[j].split("")[i];
            }
        }

        return result;


    }


    private String arrayToString(String[][] array) {

        String out = "";

        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {

                out = out + array[i][j];
            }
            out = out + "\n";
        }
        return out;

    }


    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return height;
    }


    private void printOutputScreenArray() {
        printArray(outputScreenArray);
    }


}


