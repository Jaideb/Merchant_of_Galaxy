package com.tavisca.workshops.pratham;

import java.util.HashMap;

public class QueryWithMuch {

    HashMap<String, Character> wordToRomanMap;

    QueryWithMuch(HashMap<String, Character> wordToRomanMap)
    {
        this.wordToRomanMap = wordToRomanMap;
    }


    public String answerQuestionsForMuch(String[] words) {
        String romanLiterals = "";
        String response = "";
        for (String word : words) {
            romanLiterals += wordToRomanMap.get(word);
            response += word + " ";

        }

        int result = RomanToIntegerConverter.convert(romanLiterals);
        response += "is " + result;

        return response;
    }

}
