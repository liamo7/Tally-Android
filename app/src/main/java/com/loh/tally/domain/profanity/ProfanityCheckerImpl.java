package com.loh.tally.domain.profanity;

import com.loh.tally.ui.base.dagger.scope.ApplicationScope;

import javax.inject.Inject;

/**
 * File: ProfanityCheckerImpl.java
 * Date: 14/03/2017
 * Created By: Liam O'Hanlon
 * Description: Replaces profanity within text with symbols.
 */
@ApplicationScope
public class ProfanityCheckerImpl implements ProfanityChecker {

    // TODO: 14/03/2017 Not efficient, could use tree to check each starting character, etc

    private static final String REGEX_SPLIT = "([\\s -!$%^&*()_+|~=`{}\\[\\]:\";'<>?,.\\/])";
    private static final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
    private static final String SYMBOL = "*";

    @Inject
    public ProfanityCheckerImpl() {
    }

    /**
     * Replaces any profanity found within supplied text with a predefined symbol.
     */
    @Override
    public String replace(String text) {
        String[] tokens = split(text);
        for (int i = 0; i < tokens.length; i++) {
            for (String profanity : Profanity.PROFANITY) {
                if (tokens[i].equalsIgnoreCase(profanity)) {
                    String firstLetter = tokens[i].substring(0, 1);
                    String replaced = firstLetter + new String(new char[tokens[i].length() - 1]).replace("\0", SYMBOL);
                    tokens[i] = tokens[i].replace(tokens[i], replaced);
                    break;
                }
            }
        }

        return combine(tokens);
    }

    /**
     * Combines an array of {@link String} into a single {@link String}.
     */
    private String combine(String[] tokens) {
        StringBuilder builder = new StringBuilder(tokens.length);
        for (String token : tokens) {
            builder.append(token);
        }

        return builder.toString();
    }

    /**
     * Splits {@link String} into an Array of {@link String} keeping the delimiters as tokens.
     */
    private String[] split(String text) {
        return text.split(String.format(WITH_DELIMITER, REGEX_SPLIT));
    }
}
