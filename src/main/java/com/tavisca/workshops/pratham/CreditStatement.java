package com.tavisca.workshops.pratham;

public class CreditStatement {

    public String[] canParseCreditStatement(String inputs) {
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



}
