package com.MathPuzzle;

import org.junit.Test;

import com.google.common.base.Strings;
import com.google.common.base.Strings;

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
public class BinaryReduceStepCounterTest {

    @Test
    public void binaryStringIntegerReduceToZeroStepsCounter() {
        assertEquals(7, solution("011100"));
        assertEquals(8, solution("011101"));
        assertEquals(1999, solution(largeBinaryStringBuilder(1000)));
        // TODO: the max binary size is taking a very long time to run. Need to look into some short math solution to speed this up so that this test can be run.
        //assertEquals(999999, solution(largeBinaryStringBuilder(1000000)));
    }

    private String largeBinaryStringBuilder(int number) {
        StringBuffer s = new StringBuffer();
        for (int i = 1; i <= number; i++) {
            s.append(1);
        }
        return s.toString();
    }


    public int solution(String S) {
        // write your code in Java SE 8
        //byte inputValueAsBytes = (byte) .Byte.parseByte(S,2);
        BigInteger inputValueAsBytes = new BigInteger(S, 2);

        int numberOfStepsToReduceToZero = 0;

        boolean foundSolution = false;

        int stackOverflowGuardCounter = 1;

        while (!foundSolution) {
            if ((!inputValueAsBytes.testBit(0))) {
                inputValueAsBytes = inputValueAsBytes.shiftRight(1);
            } else {
                inputValueAsBytes = inputValueAsBytes.subtract(BigInteger.ONE);
            }
            System.out.println(inputValueAsBytes);
            numberOfStepsToReduceToZero++;
            stackOverflowGuardCounter++;
            if (inputValueAsBytes == BigInteger.ZERO || stackOverflowGuardCounter == 1000000) {
                foundSolution = true;
            }
        }

        return numberOfStepsToReduceToZero;
    }
}
