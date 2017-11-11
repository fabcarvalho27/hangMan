package org.academiadecodigo;

import org.academiadecodigo.game.Game;
import org.academiadecodigo.game.Player;
import org.academiadecodigo.network.Server;

import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        //Player player1 = new Player();
        //Player player2 = new Player();

        //Game game = new Game(player1,player2,"english",5);

        try {
            Server server = new Server();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
