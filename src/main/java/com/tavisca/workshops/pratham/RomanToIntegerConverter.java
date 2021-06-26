package com.tavisca.workshops.pratham;

import java.util.HashMap;

public class RomanToIntegerConverter
{
    public static HashMap<Character, Integer> romanLettersMap = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);

    }};

    public static int convert(String roman) {

        if(!isValid(roman)){
            throw new RuntimeException("Invalid Roman Format");
        }
        int out = 0;
        char lastchar = 'M';
        char[] a = roman.toCharArray();

        for (char character : a)
        {
            out += romanLettersMap.get(character);

            if (romanLettersMap.get(lastchar) < romanLettersMap.get(character))
            {
                out -= romanLettersMap.get(lastchar) * 2;
            }
            lastchar = character;
        }
        return out;
    }

    private static boolean isValid(String roman) {
        return roman.matches("^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

}
