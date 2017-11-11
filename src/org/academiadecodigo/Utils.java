package org.academiadecodigo;

/**
 * Created by codecadet on 11/11/2017.
 */
public abstract class Utils {


    public static char[] initializeArray(char[] array) {

        for (int i = 0; i < array.length; i++) {
            array[i] = '_';
        }
        return array;
    }


    public static boolean isValidChar (char charToBeTested) {

        //converted to String so to test with regex
        String charToString = String.valueOf(charToBeTested);

        // regex \p{L} matches a single code point in the category “letter”
        if (charToString.matches("\\p{L}")) {
            System.out.println("Valid character");
            return true;
        }

        System.out.println("Invalid character");
        return false;
    }
}
