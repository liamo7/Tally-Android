package com.loh.tally.domain.profanity;

import java.util.Arrays;

import timber.log.Timber;

/**
 * File: ProfanityCheckerImpl.java
 * Date: 14/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class ProfanityCheckerImpl implements ProfanityChecker {

    // // TODO: 14/03/2017 Not efficent, could use tree to check each starting character, etc

    private static final String REGEX_SPLIT = "([\\s -!$%^&*()_+|~=`{}\\[\\]:\";'<>?,.\\/])";
    private static final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

    public ProfanityCheckerImpl() {

    }

    @Override
    public String check(String text) {
        // split text into tokens
        String[] tokens = split(text);
        Timber.d(Arrays.asList(tokens).toString());
        // check each token against array
        for (int i = 0; i < tokens.length; i++) {
            for (String profanity : Profanity.PROFANITY) {
                if (tokens[i].equalsIgnoreCase(profanity)) {
                    String firstLetter = tokens[i].substring(0, 1);
                    String replaced = firstLetter + new String(new char[tokens[i].length() - 1]).replace("\0", "*");
                    tokens[i] = tokens[i].replace(tokens[i], replaced);
                    break;
                }
            }
        }

        return combine(tokens);
    }

    private String combine(String[] tokens) {
        StringBuilder builder = new StringBuilder(tokens.length);
        for (String token : tokens) {
            builder.append(token);
        }

        return builder.toString();
    }

    private String[] split(String text) {
        return text.split(String.format(WITH_DELIMITER, REGEX_SPLIT));
    }
}
