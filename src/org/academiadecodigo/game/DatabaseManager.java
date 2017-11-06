package org.academiadecodigo.game;

import java.io.*;
import java.util.LinkedList;

public class DatabaseManager {


    private File file;
    private int numberOfWords;
    String[] wordList;

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

    public void countNumberOfSentences() {

        try {

            String line;
            String longlist = "";

            BufferedReader bReader = new BufferedReader(new FileReader(file));

            while ((line = bReader.readLine()) != null) {
            longlist = longlist.concat(line + " ");
            }

            bReader.close();
            System.out.println("Concatenated text: " + longList);

            sentenceList = longList.split("\\W+");

            numberOfWords = wordList.length;
            System.out.println("Number of words: " + numberOfWords);


        } catch (IOException e) {
            System.out.println("File reading problem");
        }
    }

    /*public void countNumberOfWords() {

        try {
            String line;
            String longList = "";

            BufferedReader bReader = new BufferedReader(new FileReader(file));

            while ((line = bReader.readLine()) != null) {
                longList = longList.concat(line + " ");
            }

            bReader.close();
            System.out.println("Concatenated text: " + longList);

            wordList = longList.split("\\W+");

            numberOfWords = wordList.length;
            System.out.println("Number of words: " + numberOfWords);


        } catch (IOException e) {
            System.out.println("File reading problem");
        }
    }*/

    public String selectRandomWord() {
        int index = (int) ((Math.random() * wordList.length));
        System.out.println("Index: " + index);

        String randomWord = wordList[index];
        System.out.println("Random word: " + randomWord);

        return randomWord;
    }


    /*public String selectRandomWord() {
        int index = (int) ((Math.random() * numberOfWords));
        String randomWord = wordList.get(index);
        //wordList.clear();
        return randomWord;
    }*/


//TODO: make it beautiful!! :)
//TODO: create .txt database files by theme

    public String pickSentence(String theme) {

        pickFilePath(theme);
        System.out.println("File Path: " + file);

        countNumberOfSentences();
        System.out.println("Number of sentences: " + numberOfWords);

        String sentence = selectRandomWord();
        System.out.println("Random sentence: " + sentence);
        return sentence;

    }

    public File pickSentencePath(String theme) {

        file = new File("resources/themes/" + theme + ".txt");
        return file;


    }


}






