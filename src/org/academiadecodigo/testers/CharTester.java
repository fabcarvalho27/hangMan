package org.academiadecodigo.testers;

public class CharTester {

    public static void main(String[] args) {

        String randomWord = "randosdfsdfsdfsdfsmdddddaaaaa";

        char[] charArray = randomWord.toCharArray();

        char ch = ';';



        GuessLetter guessLetter = new GuessLetter();

        guessLetter.guessLetter(ch, charArray);

        guessLetter.guessLetter('d', charArray);

    }


    public static class GuessLetter {

        private char[] guesses;


        public char[] guessLetter(char guessedLetter, char[] wordToGuess) {

        char[] guessedArray = new char[wordToGuess.length];



            for (int i = 0; i < wordToGuess.length; i++) {
                if (wordToGuess[i] == guessedLetter) {
                    guessedArray[i] = guessedLetter;
                }
            }

            System.out.println(guessedArray);
            return guessedArray;
        }




    }


}