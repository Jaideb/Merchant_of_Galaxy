package com.tavisca.workshops.pratham;

import java.util.HashMap;
import java.util.Set;

public class WordToRomanParser {

    public HashMap<String, Character> wordToRomanMap;

    WordToRomanParser(HashMap<String, Character> validRomanMap)
    {
        this.wordToRomanMap = validRomanMap;
    }


    public String[] wordToRomanParse(String sentence) {
        String wordsToRomanArray[] = sentence.split(" ");
        return new String[]{wordsToRomanArray[0], wordsToRomanArray[2]};
    }


    private boolean isValidLiteral(String literal) {
        Set<Character> literals = RomanToIntegerConverter.romanLettersMap.keySet();
        return literals.contains(literal);
    }


    private void handleWordToRomanStatements(String sentence) throws Exception {
        String[] words = wordToRomanParse(sentence);
        if (isValidLiteral(words[1]))
            wordToRomanMap.put(words[0], words[1].charAt(0));
        else
            throw new Exception("Invalid Word");
    }
}
