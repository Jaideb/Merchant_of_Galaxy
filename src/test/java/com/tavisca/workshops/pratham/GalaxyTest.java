package com.tavisca.workshops.pratham;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GalaxyTest {
    MerchantGalaxy merchantGalaxy = new MerchantGalaxy();

    @Test
    public void canParseWordToRomanNumeralStatement() {
        WordToRomanParser wordToRomanParser = new WordToRomanParser(new HashMap<String, Character>());
        assertArrayEquals(new String[]{"glob", "I"}, wordToRomanParser.wordToRomanParse("glob is I"));
        assertArrayEquals(new String[]{"pork", "V"}, wordToRomanParser.wordToRomanParse("pork is V"));
        assertArrayEquals(new String[]{"pish", "X"}, wordToRomanParser.wordToRomanParse("pish is X"));
        assertArrayEquals(new String[]{"tegj", "L"}, wordToRomanParser.wordToRomanParse("tegj is L"));
    }

    @Test
    public void tokenizeCreditsStatements() {
        MetalCredit metalCredit = new MetalCredit(merchantGalaxy.metalToCreditsMap, merchantGalaxy.wordToRomanMap);
        assertArrayEquals(new String[]{"glob", "glob", "Silver", "34"}, metalCredit.parseCreditStatement("glob glob Silver is 34 Credits"));
        assertArrayEquals(new String[]{"glob", "prok", "Gold", "57800"}, metalCredit.parseCreditStatement("glob prok Gold is 57800 Credits"));
        assertArrayEquals(new String[]{"pish", "pish", "Iron", "3910"}, metalCredit.parseCreditStatement("pish pish Iron is 3910 Credits"));
    }

    @Test
    public void canParseMuchManyTokens() {
        assertArrayEquals(new String[]{"pish", "tegj", "glob", "glob"}, merchantGalaxy.parseMuchManyTokens("how much is pish tegj glob glob ?"));
        assertArrayEquals(new String[]{"glob", "prok", "Silver"}, merchantGalaxy.parseMuchManyTokens("how many Credits is glob prok Silver ?"));
        assertArrayEquals(new String[]{"glob", "prok", "Gold"}, merchantGalaxy.parseMuchManyTokens("how many Credits is glob prok Gold ?"));
        assertArrayEquals(new String[]{"glob", "prok", "Iron"}, merchantGalaxy.parseMuchManyTokens("how many Credits is glob prok Iron ?"));
    }

    @Test
    public void checkValidMap() {
        try {
            merchantGalaxy.wordRomanInput("glob is I");
            merchantGalaxy.wordRomanInput("prok is V");
            merchantGalaxy.wordRomanInput("pish is X");
            merchantGalaxy.wordRomanInput("tegj is L");

            assertEquals('I', merchantGalaxy.getRomanLiteralForWord("glob"));
            assertEquals('V', merchantGalaxy.getRomanLiteralForWord("prok"));
            assertEquals('X', merchantGalaxy.getRomanLiteralForWord("pish"));
            assertEquals('L', merchantGalaxy.getRomanLiteralForWord("tegj"));
        } catch (Exception e) {
            return;
        }
    }

    @Test
    public void checkCreditPerItem() throws Exception {
        checkValidMap();
        merchantGalaxy.metalCreditInput("glob glob Silver is 34 Credits");
        merchantGalaxy.metalCreditInput("glob prok Gold is 57800 Credits");
        merchantGalaxy.metalCreditInput("pish pish Iron is 3910 Credits");
        assertEquals(17, merchantGalaxy.getValueForMetal("Silver"));
        assertEquals(14450, merchantGalaxy.getValueForMetal("Gold"));
        assertEquals(195.5, merchantGalaxy.getValueForMetal("Iron"), 0.5);
    }

    @Test
    public void answerQuestions() throws Exception {
        checkValidMap();
        checkCreditPerItem();
        String response = merchantGalaxy.query("how much is pish tegj glob glob ?");
        String response1 = merchantGalaxy.query("how many Credits is glob prok Silver ?");
        String response2 = merchantGalaxy.query("how many Credits is glob prok Gold ?");
        //String response4 = merchantGalaxy.query("how many Credits is glob prok Iron ?");
        String response3=merchantGalaxy.query("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

        assertEquals("pish tegj glob glob is 42", response);
        assertEquals("glob prok Silver is 68 Credits", response1);
        assertEquals("glob prok Gold is 57800 Credits",response2);
        assertEquals("I have no idea what you are talking about",response3);
        //assertEquals("glob prok Iron is 782 Credits",response4);
    }
}
