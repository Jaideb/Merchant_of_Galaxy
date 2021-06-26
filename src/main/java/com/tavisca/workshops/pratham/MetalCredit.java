package com.tavisca.workshops.pratham;

import java.util.Arrays;
import java.util.HashMap;

public class MetalCredit {
    private HashMap<String, Integer> metalToCreditsMap;
    private HashMap<String, Character> wordToRomanMap;

    MetalCredit(HashMap<String, Integer> metalToCreditsMap, HashMap<String, Character> wordToRomanMap)
    {
        this.metalToCreditsMap = metalToCreditsMap;
        this.wordToRomanMap = wordToRomanMap;
    }


    public String[] parseCreditStatement(String inputs) {
        String words[] = inputs.split(" ");

        int indexOfIs = -1;
        int indexOfNumber = -1;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("is"))
                indexOfIs = i;
            else if (words[i].equals("Credits"))
                indexOfNumber = i - 1;
        }

        //Inserting the tokens into new array.
        String[] tokens = new String[indexOfIs + 1];

        for (int i = 0; i < tokens.length - 1; i++)
            tokens[i] = words[i];
        tokens[tokens.length - 1] = words[indexOfNumber];

        return tokens;
    }


    private int convertWordsToInteger(String[] words) {
        String roman = "";
        for (String word : words) {
            roman += wordToRomanMap.get(word);
        }
        return RomanToIntegerConverter.convert(roman);

    }

    public void handleMetalsCreditValuePerItem(String metal) {
        String[] storeCreditValue = parseCreditStatement(metal);
        String metals = storeCreditValue[storeCreditValue.length - 2];
        String creditValue = storeCreditValue[storeCreditValue.length - 1];

        String[] words = Arrays.copyOf(storeCreditValue, storeCreditValue.length - 2);
        int romanToNumeric = convertWordsToInteger(words);
        int valueForMetal = Integer.parseInt(creditValue) / romanToNumeric;

        metalToCreditsMap.put(metals, valueForMetal);

    }
}
