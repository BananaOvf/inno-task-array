package org.litvinov.taskarray.util;

import java.util.regex.Pattern;

public final class ArrayFormatConstant {

    private static final String DELIMITER = ";";
    private static final String NUMBER_REGEX = "-?\\d+(?:[.,]\\d+)?";

    private static final String SPACES = "\\s*";
    private static final String QUOTED_DELIMITER = Pattern.quote(DELIMITER);
    private static final String SEPARATOR = SPACES + QUOTED_DELIMITER + SPACES;

    public static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER_REGEX);

    public static final Pattern VALID_LINE_PATTERN = Pattern.compile(
            "^" + SPACES +
                    "(?:" + QUOTED_DELIMITER + SPACES + ")?" +
                    NUMBER_REGEX +
                    "(?:" + SEPARATOR + NUMBER_REGEX + ")*" +
                    SPACES + QUOTED_DELIMITER + "?" + SPACES + "$"
    );

    private ArrayFormatConstant() {
    }
}