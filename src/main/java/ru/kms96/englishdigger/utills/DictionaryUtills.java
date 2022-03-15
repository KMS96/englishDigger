package ru.kms96.englishdigger.utills;

import java.util.ArrayList;
import java.util.List;

public class DictionaryUtills {

    private static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static List<String> generateEnglishAlphabet() {
        List<String> lettersList = new ArrayList<>();

        char[] alphabet = ENGLISH_ALPHABET.toCharArray();
        for(char l : alphabet) {
            lettersList.add(String.valueOf(l));
        }

        return lettersList;
    }
}
