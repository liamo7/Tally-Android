package com.loh.tally;

import com.loh.tally.domain.profanity.ProfanityChecker;
import com.loh.tally.domain.profanity.ProfanityCheckerImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * File: ProfanityCheckerUnitTest.java
 * Date: 17/03/2017
 * Created By: Liam O'Hanlon
 */
public class ProfanityCheckerUnitTest {

    private static final String TEST_STRING_WITH_PROFANITY = "Fuck, Shit!!! Prick fucking idiot. Just testing.";
    private static final String TEST_STRING_EXPECTED = "F***, S***!!! P**** f****** idiot. Just testing.";

    private ProfanityChecker profanityChecker;

    @Before
    public void setup() {
        profanityChecker = new ProfanityCheckerImpl();
    }

    @Test
    public void test_with_profanity() {
        String actual = profanityChecker.replace(TEST_STRING_WITH_PROFANITY);
        Assert.assertEquals(TEST_STRING_EXPECTED, actual);
    }

    @Test
    public void test_replaced_words_equal_3() {
        String testcase = "Fuck";
        String expected = "F***";
        String actual = profanityChecker.replace(testcase);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_with_symbols_after() {
        String testcase = "Fuck!!**£";
        String expected = "F***!!**£";
        String actual = profanityChecker.replace(testcase);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_with_symbols_before() {
        String testcase = "Fuck!!**£";
        String expected = "F***!!**£";
        String actual = profanityChecker.replace(testcase);
        Assert.assertEquals(expected, actual);
    }

}
