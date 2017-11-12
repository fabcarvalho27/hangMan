package org.academiadecodigo.testers;

import org.academiadecodigo.game.DatabaseManager;

public class DataBaseTester {

    public static void main(String[] args) {

        DatabaseManager dataBase = new DatabaseManager();

        System.out.println("Database test: " + dataBase.pickGameWords("capitais",5)[0]);

        /*for (int i = 0; i < 6; i++) {

            dataBase.pickGameWords("capitais", 2);
            System.out.println("");
        }

        for (int i = 0; i < 6; i++) {

            dataBase.pickGameWords("english", 7);
            System.out.println("");
        }

       /* for (int i = 0; i < 6; i++) {

            dataBase.pickRandomSentence("frases");
            System.out.println("");
        }*/


    }
}
