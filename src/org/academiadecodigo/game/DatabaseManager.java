package org.academiadecodigo.game;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class DatabaseManager {


    private File file;
    private int numberOfWords;
    private ArrayList<String> wordList;
    private LinkedList<String> sentenceList;


    public String[] pickGameWords(String theme, int gameRounds) {

        pickFilePath(theme);
        System.out.println("File Path: " + file);

        countNumberOfWords();
        System.out.println("Number of words in file: " + numberOfWords);

        String[] words = giveGameWords(gameRounds);
        System.out.println("Number words per round: " + words.length);
        for (int i = 0; i < words.length; i++) {
            System.out.println("Game word " + i + ": " + words[i]);
        }

        return words;
    }


    private void pickFilePath(String theme) {

        file = new File("resources/themes/" + theme + ".txt");
    }


    private void countNumberOfWords() {

        try {

            String line;
            wordList = new ArrayList<>();

            BufferedReader bReader = new BufferedReader(new FileReader(file));
            while ((line = bReader.readLine()) != null) {
                wordList.add(line);
            }

            numberOfWords = wordList.size();

        } catch (IOException e) {
            System.out.println("File reading problem: " + e.getMessage());
        }
    }

    private String[] giveGameWords(int gameRounds) {

        String[] gameWords = new String[gameRounds];
        int index;

        for (int i = 0; i < gameRounds; i++) {
            index = (int) (Math.random() * wordList.size());
            gameWords[i] = wordList.get(index);
            wordList.remove(index);
        }
        return gameWords;
    }
}



