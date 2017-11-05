package org.academiadecodigo.game;

public class DataBaseTester {

    public static void main(String[] args) {

        DatabaseManager dataBase = new DatabaseManager();

       /* for (int i = 0; i < 6 ; i++) {

            dataBase.pickRandomWord("portugues");
            System.out.println("");
        }

        for (int i = 0; i < 6 ; i++) {

            dataBase.pickRandomWord("english");
            System.out.println("");
        }*/

        for (int i = 0; i < 6 ; i++) {

            dataBase.pickRandomSentence("frases");
            System.out.println("");
        }

    }
}
