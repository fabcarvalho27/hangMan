package org.academiadecodigo.game;

import java.io.File;
import java.io.InputStreamReader;

public class DatabaseManager {

    InputStreamReader inputStreamReader;
    File file;


    public String pickFilePath(String theme) {

        switch (theme) {
            case x:
                return;

            case y:

                return;

            case z:

                return "path";

            default:

                return "default path";

        }
    }

    public String pickRandomWord(String theme) {

        pickFilePath(theme);
        selectRandomLine();


    }

    public void selectRandomLine() {

    }

    //TODO: make it beautiful!! :)
    //TODO: create .txt database files by theme
}
