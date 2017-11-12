package org.academiadecodigo.terminalGFX;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 09/11/2017.
 */
public class TextReader {


    public TextReader() throws FileNotFoundException {

    }

    public TextReader(String filepath) throws FileNotFoundException {

        this.filepath = filepath;
    }


    private List<String> sentenceList = new LinkedList<>();
    private String line;
    private String filepath = "";
    private BufferedReader bReader;


    public String readText(String filepath) throws IOException {

        bReader = new BufferedReader(new FileReader(filepath));

        String line = null;
        String str = "";

        while ((line = bReader.readLine()) != null) {
            str = str + "\n" + line;
        }

        bReader.close();
        return str;
    }

    public String returnLogo() throws IOException {
        return readText("resources/TerminalGFX/logo");
    }


}
