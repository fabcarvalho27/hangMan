package org.academiadecodigo.testers;

import org.academiadecodigo.game.Game;
import org.academiadecodigo.game.Player;
import org.academiadecodigo.network.Server;

import java.io.IOException;

public class GameTester {

    public static void main(String[] args) {

        try {
            Server server = new Server();
            Game game = new Game(new Player(server.getClient1Socket(), "P1"), new Player(server.getClient2Socket(), "P2"), "english", 5);

            game.init();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
