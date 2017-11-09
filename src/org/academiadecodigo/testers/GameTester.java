package org.academiadecodigo.testers;

import org.academiadecodigo.game.Game;
import org.academiadecodigo.game.Player;

public class GameTester {

    public static void main(String[] args) {
    Player p1 = new Player();
    Player p2 = new Player();

    Game game = new Game(p1,p2,"ingles");

    game.gameWords();
        System.out.println(game.gameWords()[0]);

    }


}
