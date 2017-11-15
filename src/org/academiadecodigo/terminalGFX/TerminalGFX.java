package org.academiadecodigo.terminalGFX;


import org.academiadecodigo.game.GameStatus;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TerminalGFX {

    private char[][] p1ScreenArray = new char[ScreenConstants.width][ScreenConstants.height];
    private char[][] p2ScreenArray = new char[ScreenConstants.width][ScreenConstants.height];

    private GameStatus gameStatus;
    private TextReader textReader;

    //const
    public TerminalGFX(GameStatus gameStatus) {

        try {
            this.textReader = new TextReader();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.gameStatus = gameStatus;
    }


    //render
    public void mountPlayerScreens() {
        mountPlayerScreen(p1ScreenArray);
        mountPlayerScreen(p2ScreenArray);
    }

    public String p1Render() {

        mountPlayerScreen(p1ScreenArray);
        return playerScreenStr(p1ScreenArray);
    }

    public String p2Render() {

        mountPlayerScreen(p2ScreenArray);
        return arrayToString(p2ScreenArray);
    }

    public String playerScreenStr(char[][] screenArray) {
        return arrayToString(screenArray);
    }


    //Screen Mount

    private void mountPlayerScreen(char[][] screenArray) {

        initializeArray(screenArray);

        header(screenArray);

        playerInfo(screenArray);
        opponentInfo(screenArray);

        messages(screenArray);
        timerToScreen(screenArray);

    }

    private void playerInfo(char[][] screenArray) {

        wordToScreen(screenArray, gameStatus.getP1Name(), ScreenConstants.namePosX, ScreenConstants.namePosY);                                 //p name
        arrayToScreen(screenArray, dummy(gameStatus.getP1Mistakes()), ScreenConstants.dummyPosX, ScreenConstants.dummyPosY);                   //p hangman
        wordToScreen(screenArray, "Guesses: " + gameStatus.getP1Guesses(), ScreenConstants.guessesPosX, ScreenConstants.guessesPosY);   //p guesses
        wordToScreen(screenArray, gameStatus.getP1Word(), ScreenConstants.wordPosX, ScreenConstants.wordPosY);                                 //p word shown


    }

    private void opponentInfo(char[][] screenArray) {

        wordToScreen(screenArray, gameStatus.getP2Name(), ScreenConstants.oponentNamePosX, ScreenConstants.oponentNamePosY);                //oponent name
        arrayToScreen(screenArray, dummy(gameStatus.getP2Mistakes()), ScreenConstants.oponentDummyPosX, ScreenConstants.oponentDummyPoxY);     //oponent hangman
    }

    private void header(char[][] screenArray) {

        try {

            arrayToScreen(screenArray, stringToArray(textReader.returnLogo()), ScreenConstants.logoPosX, ScreenConstants.logoPosY);                                              //LOGO
            wordToScreen(screenArray, "Round: " + gameStatus.getCurrentsRound() + " of " + gameStatus.getRounds(), ScreenConstants.roundPosX, ScreenConstants.roundPosY);  //rounds
            wordToScreen(screenArray, "Theme: " + gameStatus.getTheme(), ScreenConstants.themePosX, ScreenConstants.themePosY);                                            //theme

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void messages(char[][] screenArray) {
        wordToScreen(screenArray, gameStatus.getP1Message(), ScreenConstants.pMessagePosX, ScreenConstants.pMessagePosY);          //message to player
        wordToScreen(screenArray, gameStatus.getMessageToAll(), ScreenConstants.gameMessagePosX, ScreenConstants.gameMessagePosY); //message to  All
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


    //test stuff
    public void test() {
        System.out.println(p1Render());
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


    //drawings to screen
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


    private void arrayToScreen(char[][] array, char[][] drawing, int x, int y /*TODO String sector*/) {

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

}


