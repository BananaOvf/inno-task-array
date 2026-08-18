package org.litvinov.taskarray.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.litvinov.taskarray.exception.InvalidLineException;
import org.litvinov.taskarray.parser.DoubleArrayParser;
import org.litvinov.taskarray.util.ArrayFormatConstants;
import org.litvinov.taskarray.validator.ArrayLineValidator;

import java.util.Arrays;
import java.util.regex.Pattern;


public class DoubleArrayParserImpl implements DoubleArrayParser {
    private static final Logger logger = LogManager.getLogger();

    private final ArrayLineValidator validator;

    public DoubleArrayParserImpl(ArrayLineValidator validator) {
        this.validator = validator;
    }

    @Override
    public double[] parse(String line) throws InvalidLineException {
        logger.debug("Parsing line: \"{}\"", line);

        if (line == null) {
            throw new InvalidLineException("Line must not be null");
        }

        if (line.isBlank()) {
            return new double[0];
        }

        if (!validator.isValid(line)) {
            throw new InvalidLineException("Invalid array line: " + line);
        }

        String strippedLine = line.strip();
        Pattern delimiter = ArrayFormatConstants.DELIMITER_PATTERN;
        String[] tokens = delimiter.split(strippedLine);

        double[] result = new double[tokens.length];
        int index = 0;

        try {
            for (String token : tokens) {
                if (!token.isBlank()) {
                    String strippedToken = token.strip();
                    String normalizedToken = strippedToken.replace(',', '.');
                    result[index] = Double.parseDouble(normalizedToken);
                    index++;
                }
            }
        } catch (NumberFormatException e) {
            throw new InvalidLineException("Invalid number in line: " + line, e);
        }

        return Arrays.copyOf(result, index);
    }
}
