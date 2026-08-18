package org.litvinov.taskarray.validator.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.litvinov.taskarray.util.ArrayFormatConstants;
import org.litvinov.taskarray.validator.ArrayLineValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayLineValidatorImpl implements ArrayLineValidator {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean isValid(String line) {
        logger.debug("Check if line is valid: \"{}\"", line);

        if (line == null) {
            return false;
        }

        if (line.isBlank()) {
            return true;
        }

        Pattern pattern = ArrayFormatConstants.VALID_LINE_PATTERN;
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
}
