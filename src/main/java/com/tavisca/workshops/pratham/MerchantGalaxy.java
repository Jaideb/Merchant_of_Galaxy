package com.tavisca.workshops.pratham;

import java.util.Arrays;
import java.util.HashMap;

public class MerchantGalaxy {

    private static final String RESPONSE_NO_IDEA = "I have no idea what you are talking about";
    public HashMap<String, Character> wordToRomanMap = new HashMap<>();
    public HashMap<String, Integer> metalToCreditsMap = new HashMap<>();
    private WordToRomanParser wordToRomanParser;
    private MetalCredit metalCredit;

    MerchantGalaxy()
    {
        wordToRomanParser = new WordToRomanParser(wordToRomanMap);
        metalCredit = new MetalCredit(metalToCreditsMap, wordToRomanMap);
    }

    public void wordRomanInput(String statement)
    {
        wordToRomanParser.wordToRomanParse(statement);
    }


    public void metalCreditInput(String statement)
    {
        metalCredit.handleMetalsCreditValuePerItem(statement);
    }

    public String[] parseMuchManyTokens(String s) {
        String[] tokens = s.split(" ");
        int indexOfIs = -1;
        int indexOfQuestionMark = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("is"))
                indexOfIs = i;
            else if (tokens[i].equals("?"))
                indexOfQuestionMark = i;
        }
        String[] wordsToken = new String[indexOfQuestionMark - indexOfIs - 1];
        for (int j = 0; j < wordsToken.length; j++)
            wordsToken[j] = tokens[j + indexOfIs + 1];
        return wordsToken;
    }

    public String query(String statement) throws Exception {
        String[] questions = parseMuchManyTokens(statement);

        if (statement.contains("much") && statement.contains("is")) {
            QueryWithMuch queryWithMuch = new QueryWithMuch(wordToRomanMap);
            return queryWithMuch.answerQuestionsForMuch(questions);
        } else if (statement.contains("many")) {
            QueryWithMany queryWithMany = new QueryWithMany(wordToRomanMap, metalToCreditsMap);
            return queryWithMany.answerQuestionsForMany(questions);
        } else if (!statement.contains("is") || !statement.contains("Credits") || !statement.contains("credits")) {
            return RESPONSE_NO_IDEA;
        } else {
            return "";
        }
    }


    public char getRomanLiteralForWord(String information) {
        return wordToRomanMap.get(information);
    }

    public int getValueForMetal(String metal) {
        return metalToCreditsMap.get(metal);

    }
}

