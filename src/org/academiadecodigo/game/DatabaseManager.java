package org.academiadecodigo.game;

import java.io.*;
import java.util.LinkedList;

public class DatabaseManager {


    private File file;
    private int numberOfWords;
    private LinkedList<String> wordList;

    public String pickRandomWord(String theme) {

        pickFilePath(theme);
        System.out.println("File Path: " + file);

        countNumberOfWords();
        System.out.println("Number of words: " + numberOfWords);

        System.out.println("Random word: " + selectRandomWord());
        return selectRandomWord();

    }

    public String pickRandomSentence(String theme) {

        pickFilePath(theme);
        System.out.println("File Path: " + file);

        countNumberOfWords();
        System.out.println("Number of sentences: " + numberOfWords);

        System.out.println("Random sentence: " + selectRandomWord());
        return selectRandomWord();

    }


    public File pickFilePath(String theme) {

        file = new File("resources/themes/" + theme + ".txt");
        //return String.valueOf(file);
        return file;


    }

    public void countNumberOfWords() {

        try {
            numberOfWords = 0;
            String line;
            this.wordList = new LinkedList<>();

            BufferedReader bReader = new BufferedReader(new FileReader(file));

            while ((line = bReader.readLine()) != null) {
                wordList.add(line);
                numberOfWords++;
            }
            bReader.close();
        } catch (IOException e) {
            System.out.println("File reading problem");
        }
    }

    public String selectRandomWord() {
        int index = (int) ((Math.random() * numberOfWords));
        String randomWord = wordList.get(index);
        //wordList.clear();
        return randomWord;
    }


//TODO: make it beautiful!! :)
//TODO: create .txt database files by theme
}
