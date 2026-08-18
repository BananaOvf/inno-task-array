package org.litvinov.taskarray.util;

import java.util.regex.Pattern;

public final class ArrayFormatConstants {

    public static final String DELIMITER = ";";
    public static final String NUMBER_PATTERN = "-?\\d+(?:[.,]\\d+)?";

    private static final String SPACES = "\\s*";
    private static final String QUOTED_DELIMITER = Pattern.quote(DELIMITER);
    private static final String SEPARATOR = SPACES + QUOTED_DELIMITER + SPACES;

    public static final Pattern DELIMITER_PATTERN = Pattern.compile(SEPARATOR);

    public static final Pattern VALID_LINE_PATTERN = Pattern.compile(
            "^" + SPACES +
                    "(?:" + QUOTED_DELIMITER + SPACES + ")?" +
                    NUMBER_PATTERN +
                    "(?:" + SEPARATOR + NUMBER_PATTERN + ")*" +
                    SPACES + QUOTED_DELIMITER + "?" + SPACES + "$"
    );

    private ArrayFormatConstants() {
    }
}