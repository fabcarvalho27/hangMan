package org.academiadecodigo.testers;

import org.academiadecodigo.game.Game;
import org.academiadecodigo.game.Player;
import org.academiadecodigo.network.Server;

import java.io.IOException;

public class GameTester {

    public static void main(String[] args) {

        try {
            Server server = new Server();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
