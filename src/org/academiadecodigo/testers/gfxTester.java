package org.academiadecodigo.testers;

import org.academiadecodigo.game.GameStatus;
import org.academiadecodigo.terminalGFX.TerminalGFX;

import java.io.IOException;

/**
 * Created by codecadet on 09/11/2017.
 */
public class gfxTester {

    public static void main(String[] args) throws IOException {

        GameStatus gameStatus = new GameStatus();
        TerminalGFX terminalGFX = new TerminalGFX(gameStatus);

        terminalGFX.test();
    }
}
