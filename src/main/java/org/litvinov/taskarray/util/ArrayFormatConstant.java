package org.litvinov.taskarray.util;

import java.util.regex.Pattern;

public final class ArrayFormatConstant {

    private static final String DELIMITER = ";";
    private static final String NUMBER_REGEX = "-?\\d+(?:[.,]\\d+)?";

    private static final String SPACES = "\\s*";
    private static final String QUOTED_DELIMITER = Pattern.quote(DELIMITER);
    private static final String ELEMENT = SPACES + "(?:" + NUMBER_REGEX + ")?" + SPACES;

    public static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER_REGEX);

    public static final Pattern VALID_LINE_PATTERN = Pattern.compile(
            "^" + ELEMENT + "(?:" + QUOTED_DELIMITER + ELEMENT + ")*$"
    );

    private ArrayFormatConstant() {
    }
}