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
        mountp1Screen(p1ScreenArray);
        mountp2Screen();
    }

    public String p1Render() throws IOException {

        mountp1Screen(p1ScreenArray);
        return arrayToString(p1ScreenArray);
    }

    public String p2Render() throws IOException {

        mountp2Screen();
        return arrayToString(p2ScreenArray);
    }

    public String p1Screen() {
        return arrayToString(p1ScreenArray);
    }

    public String p2Screen() {

        return arrayToString(p2ScreenArray);
    }


    private void mountp1Screen(char[][] screenArray) throws IOException {

        initializeArray(screenArray);





        wordToScreen(screenArray, gameStatus.getMessageToAll(), 0, topPadding + 15);                   //message from game to all


        playerInfo(screenArray);

        opponentInfo(screenArray);
        header(screenArray);
        timerToScreen(screenArray);

    }

    private void playerInfo(char[][] screenArray) {

        wordToScreen(screenArray, gameStatus.getP1Name(), ScreenConstants.namePosX, ScreenConstants.namePosY);                //p1 name
        arrayToScreen(screenArray, dummy(gameStatus.getP1Mistakes()), ScreenConstants.dummyPosX, ScreenConstants.dummyPosY);     //p1 hangman
        wordToScreen(screenArray, "Guesses: " + gameStatus.getP1Guesses(), ScreenConstants.guessesPosX, ScreenConstants.guessesPosY);//p1 guesses
        wordToScreen(screenArray, gameStatus.getP1Word(), ScreenConstants.wordPosX, ScreenConstants.wordPosY);                       //p1 word shown

        wordToScreen(screenArray, gameStatus.getP1Message(), ScreenConstants.pMessagePosX, ScreenConstants.pMessagePosY);                               //message

    }

    private void opponentInfo(char[][] screenArray){

        wordToScreen(screenArray, gameStatus.getP2Name(), ScreenConstants.o, topPadding + 12);                //oponent name
        arrayToScreen(screenArray, dummy(gameStatus.getP2Mistakes()), width - 15, topPadding + 14);     //oponent hangman
    }

    private void header(char[][] screenArray) {

        try {

            arrayToScreen(screenArray, stringToArray(textReader.returnLogo()), ScreenConstants.logoPosX, ScreenConstants.logoPosY);                       //LOGO
            wordToScreen(screenArray, "Round: " + gameStatus.getCurrentsRound() + " of " + gameStatus.getRounds(), ScreenConstants.roundPosX, ScreenConstants.roundPosY);       //rounds
            wordToScreen(screenArray, "Theme: " + gameStatus.getTheme(), ScreenConstants.themePosX, ScreenConstants.themePosY);                              //theme

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mountp2Screen() throws IOException {

        initializeArray(p2ScreenArray);

        wordToScreen(p2ScreenArray, gameStatus.getP2Name(), width - 18, topPadding + 1);                   //name
        arrayToScreen(p2ScreenArray, dummy(gameStatus.getP2Mistakes()), width - 15, topPadding + 3);       //hangman
        wordToScreen(p2ScreenArray, "Guesses: " + gameStatus.getP2Guesses(), 0, topPadding + 2);      //guesses
        wordToScreen(p2ScreenArray, gameStatus.getP2Word(), 12, topPadding + 10);                              //wordshown
        wordToScreen(p2ScreenArray, gameStatus.getP2Message(), 0, height - 1);                                   //message

        wordToScreen(p2ScreenArray, gameStatus.getP1Name(), width - 18, topPadding + 12);
        arrayToScreen(p2ScreenArray, dummy(gameStatus.getP1Mistakes()), width - 15, topPadding + 14);

        arrayToScreen(p2ScreenArray, stringToArray(textReader.returnLogo()), 4, 0);
        wordToScreen(p2ScreenArray, "Round: " + gameStatus.getCurrentsRound() + " of " + gameStatus.getRounds(), 0, topPadding + 3);
        wordToScreen(p2ScreenArray, gameStatus.getMessageToAll(), 0, topPadding + 15);
        wordToScreen(p2ScreenArray, "Theme: " + gameStatus.getTheme(), 0, topPadding + 1);

        wordToScreen(p2ScreenArray, gameStatus.getTimeSlot(), 2, topPadding + 13);
        wordToScreen(p2ScreenArray, gameStatus.getTimeSlot(), 2, topPadding + 6);
        wordToScreen(p2ScreenArray, gameStatus.getTimeSlot(), 35, topPadding + 13);
        wordToScreen(p2ScreenArray, gameStatus.getTimeSlot(), 35, topPadding + 6);
    }


    private void timerToScreen(char[][] screenArray) {

        wordToScreen(screenArray, gameStatus.getTimeSlot(), ScreenConstants.timerLeft, ScreenConstants.timerDown);

        wordToScreen(screenArray, gameStatus.getTimeSlot(), ScreenConstants.timerLeft, ScreenConstants.timerUp);

        wordToScreen(screenArray, gameStatus.getTimeSlot(), ScreenConstants.timerRight, ScreenConstants.timerDown);

        wordToScreen(screenArray, gameStatus.getTimeSlot(), ScreenConstants.timerRight, ScreenConstants.timerUp);
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


