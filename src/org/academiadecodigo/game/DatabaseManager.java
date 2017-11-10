package org.academiadecodigo.game;

import java.io.*;
import java.util.LinkedList;

public class DatabaseManager {


    private File file;
    private int numberOfWords;
    String[] wordList;
    String[] gameWords;
    LinkedList<String> sentenceList;

    public String[] pickGameWords(String theme, int gameRounds) {

        pickFilePath(theme);
        System.out.println("File Path: " + file);

        countNumberOfWords();
        System.out.println("Number of words: " + numberOfWords);

        String[] words = giveGameWords(gameRounds);
        for (int i = 0; i < words.length; i++) {
            System.out.println("Game word " + i + ": " + words[i]);
        }

        return words;

    }


    private File pickFilePath(String theme) {

        file = new File("resources/themes/" + theme + ".txt");
        return file;

    }


    private void countNumberOfWords() {

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
            System.out.println("File reading problem: " + e.getMessage());
        }
    }

    private String[] giveGameWords(int gameRounds) {

        gameWords = new String[gameRounds];
        gameWords[0] = wordList[(int) (Math.random() * wordList.length)];
        String randomWord;
        /*int index = 0;
        int previousIndex = 0;

        for (int i = 0; i < gameRounds; i++) {

            while(index == previousIndex){
                index = (int)(Math.random() * wordList.length);
            }
            gameWords[i] = wordList[index];
            previousIndex = index;
        }*/

        for (int i = 1; i < gameRounds; i++) {

            for (int j = 0; j < wordList.length; j++) {
                if ((randomWord = wordList[(int) (Math.random() * wordList.length)]) != gameWords[i - 1]) {
                    gameWords[i] = randomWord;
                    break;
                }
                continue;
            }
        }


        return gameWords;

    }


    //TODO: make it beautiful!! :)
//TODO: create .txt database files by theme


    public String pickRandomSentence(String theme) {

        pickFilePath(theme);
        //System.out.println("File Path: " + file);

        countNumberOfSentences();
        //System.out.println("Number of sentences: " + sentenceList.size());

        String sentence = selectRandomSentence();
        //System.out.println("Random sentence: " + sentence);
        return sentence;

    }


    public void countNumberOfSentences() {

        String line;
        sentenceList = new LinkedList<>();
        BufferedReader bReader = null;

        try {

            bReader = new BufferedReader(new FileReader(file));

            while ((line = bReader.readLine()) != null) {
                sentenceList.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File  problem: " + e.getMessage());
        } catch (IOException e) {
            ;
        }


    }

    public String selectRandomSentence() {

        int index = (int) ((Math.random() * sentenceList.size()));
        //System.out.println("Index: " + index);

        return sentenceList.get(index);
        //return randomSentence;
    }


}






