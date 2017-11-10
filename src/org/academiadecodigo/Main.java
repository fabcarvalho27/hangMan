package org.academiadecodigo;

import org.academiadecodigo.game.Game;
import org.academiadecodigo.game.Player;
import org.academiadecodigo.network.Server;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {


        Server server = null;
        try {
            server = new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.start();
/*

        Player player1 = new Player();
        Player player2 = new Player();

        Game game = new Game(player1,player2,"english",5);
*/
    }
}
