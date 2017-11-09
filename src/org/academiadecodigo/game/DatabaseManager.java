package org.academiadecodigo.game;

import java.io.*;
import java.util.LinkedList;

public class DatabaseManager {


    private File file;
    private int numberOfWords;
    String[] wordList;
    LinkedList<String> sentenceList;

    public String pickRandomWord(String theme) {

        pickFilePath(theme);
        System.out.println("File Path: " + file);

        countNumberOfWords();
        System.out.println("Number of words: " + numberOfWords);

        String word = selectRandomWord();
        System.out.println("Random word: " + word);
        return word;

    }


    public File pickFilePath(String theme) {

        file = new File("resources/themes/" + theme + ".txt");
        return file;

    }


    public void countNumberOfWords() {

        try {
            String line;
            String longText = "";

            BufferedReader bReader = new BufferedReader(new FileReader(file));

            while ((line = bReader.readLine()) != null) {
                longText = longText.concat(line + " ");
            }

            bReader.close();
            System.out.println("Concatenated text: " + longText);

            wordList = longText.split("\\W+");

            numberOfWords = wordList.length;

        } catch (IOException e) {
            System.out.println("File reading problem");
        }
    }

    public String selectRandomWord() {

        int index = (int) ((Math.random() * wordList.length));
        System.out.println("Index: " + index);

        String randomWord = wordList[index];

        return randomWord;
    }


//TODO: make it beautiful!! :)
//TODO: create .txt database files by theme


    public String pickRandomSentence(String theme) {

        pickFilePath(theme);
        System.out.println("File Path: " + file);

        countNumberOfSentences();
        System.out.println("Number of sentences: " + sentenceList.size());

        String sentence = selectRandomSentence();
        System.out.println("Random sentence: " + sentence);
        return sentence;

    }


    public void countNumberOfSentences() {

        String line;
        sentenceList = new LinkedList<>();
        BufferedReader bReader = null;

        try {

            bReader = new BufferedReader(new FileReader(file));

        while((line = bReader.readLine()) != null){
            sentenceList.add(line);
        }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String selectRandomSentence() {

        int index = (int) ((Math.random() * sentenceList.size()));
        System.out.println("Index: " + index);

        String randomSentence = sentenceList.get(index);
        return randomSentence;
    }


}






