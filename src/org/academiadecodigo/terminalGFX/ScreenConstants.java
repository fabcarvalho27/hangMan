package org.academiadecodigo.terminalGFX;

/**
 * Created by codecadet on 15/11/2017.
 */
public abstract class ScreenConstants {

    //general info
    private static int width = 70;
    private static int height = 30;
    private static int topPadding = 11;


    //player info
    public static int namePosX = width - 18;
    public static int namePosY = topPadding + 1;

    public static int dummyPosX = width - 15;
    public static int dummyPosY = topPadding + 3;

    public static int guessesPosX = 0;
    public static int guessesPosY = topPadding + 2;

    public static int wordPosX = 10;
    public static int wordPosY = topPadding + 10;


    //opponent info
    public static int oponentPosX = width - 18;
    public static int oponentPosY = topPadding + 12;

    public static int oponentDummyPosX = width - 15;
    public static int oponentDummyPoxY = topPadding + 14;


    //header info
    public static int logoPosX = 4;
    public static int logoPosY = 0;

    public static int themePosX = 0;
    public static int themePosY = topPadding + 1;

    public static int roundPosX = 0;
    public static int roundPosY = topPadding + 3;


    //messages info
    public static int gameMessagePosX = 0;
    public static int gameMessagePosY = topPadding + 15;

    public static int pMessagePosX = 0;
    public static int pMessagePosY = height - 1;


    //timer
    public static int timerLeft = 8;
    public static int timerRight = timerLeft + 33;
    public static int timerUp = topPadding + 6;
    public static int timerDown = timerUp + 7;

}
