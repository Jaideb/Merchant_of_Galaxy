package com.tavisca.workshops.pratham;

import java.util.HashMap;

public class QueryWithMany {

    HashMap<String, Character> wordToRomanMap;
    HashMap<String, Integer> metalToCreditsMap;


    QueryWithMany(HashMap<String, Character> wordToRomanMap, HashMap<String, Integer> metalToCreditsMap)
    {
        this.wordToRomanMap = wordToRomanMap;
        this.metalToCreditsMap = metalToCreditsMap;
    }


    public String answerQuestionsForMany(String[] questions) {
        String roman = "";
        String response = "";

        int i;
        for (i = 0; i < questions.length - 1; i++) {
            roman += wordToRomanMap.get(questions[i]);
            response += questions[i] + " ";
        }
        response += questions[i] + " ";

        int tokenValue = RomanToIntegerConverter.convert(roman);
        int valueOfMetal = metalToCreditsMap.get(questions[questions.length - 1]);
        int calculateResult = tokenValue * valueOfMetal;

        response += "is " + calculateResult + " " + "Credits";

        return response;
    }

}
