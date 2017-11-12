package org.academiadecodigo.testers;

import org.academiadecodigo.game.DatabaseManager;

public class DataBaseTester {

    public static void main(String[] args) {

        DatabaseManager dataBase = new DatabaseManager();

        for (int i = 0; i < 6; i++) {

            dataBase.pickGameWords("worldcapitals", 5);
            System.out.println("");
        }

        for (int i = 0; i < 6; i++) {

            dataBase.pickGameWords("intellijunkies", 5);
            System.out.println("");
        }

    }
}
