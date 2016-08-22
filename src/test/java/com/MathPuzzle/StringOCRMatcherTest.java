package com.MathPuzzle;

import com.google.common.base.Strings;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by matthew2chambers on 20/08/2016.
 */
public class StringOCRMatcherTest {

    private static final String WILDCARD = "?";

    @Test
    public void compareStrings() {
        assertTrue("These strings ARE a match.", solution("A2Le","2pL1"));
        assertTrue("These strings ARE a match.", solution("A2Le","AmpLe"));
        assertTrue("These strings ARE a match.", solution("AmpLe","2pL1"));
        assertTrue("These strings ARE a match.", solution("a10","10a"));
        assertFalse("These strings should NOT have matched.", solution("A2Le","3pL1"));
        assertFalse("These strings should NOT have matched.", solution("A2Le",""));
    }

    public boolean solution(String S, String T) {
        // write your code in Java SE 8
        // Theory:
        // String a = "A2Le", b = "2pL1", c = "AmpLe";
        // A(?)(?)Le
        // (?)(?)pL(?)
        //String S.sp
        String aWithWildcardsAsFlatString = replaceNumbersWithRepatitionsOfWildcard(S);
        String bWithWildcardsAsFlatString = replaceNumbersWithRepatitionsOfWildcard(T);

        if (aWithWildcardsAsFlatString.length() != bWithWildcardsAsFlatString.length()) {
          return false;
        }

        List<String> aWithWildcards = Arrays.asList(aWithWildcardsAsFlatString.split(""));
        List<String> bWithWildcards = Arrays.asList(bWithWildcardsAsFlatString.split(""));

        int index = aWithWildcardsAsFlatString.indexOf(WILDCARD);
        while(index >= 0) {
            bWithWildcards.set(index,WILDCARD);
            index = aWithWildcardsAsFlatString.indexOf(WILDCARD, index+1);
        }

        int index2 = bWithWildcardsAsFlatString.indexOf(WILDCARD);
        while(index2 >= 0) {
            aWithWildcards.set(index2,WILDCARD);
            index2 = bWithWildcardsAsFlatString.indexOf(WILDCARD, index2+1);
        }

        System.out.println(String.join("",aWithWildcards));
        System.out.println(String.join("",bWithWildcards));
        System.out.println(String.join("",aWithWildcards).equals(String.join("",bWithWildcards)));

        return String.join("",aWithWildcards).equals(String.join("",bWithWildcards));

    }

    private String replaceNumbersWithRepatitionsOfWildcard(String a) {

        Matcher m = Pattern.compile("\\d+").matcher(a);
        while (m.find()) {
            int numberFound = Integer.valueOf(m.group());
            a = a.replace(String.valueOf(numberFound), Strings.repeat(WILDCARD,numberFound));
        }

        return a;
    }

}
