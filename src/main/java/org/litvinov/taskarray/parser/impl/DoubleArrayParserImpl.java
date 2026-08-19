package org.litvinov.taskarray.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.litvinov.taskarray.exception.InvalidLineException;
import org.litvinov.taskarray.parser.DoubleArrayParser;
import org.litvinov.taskarray.util.ArrayFormatConstant;
import org.litvinov.taskarray.validator.ArrayLineValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

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

        if (!validator.isValid(line)) {
            throw new InvalidLineException("Invalid array line: " + line);
        }

        String normalizedLine = line.replace(',', '.');
        Matcher matcher = ArrayFormatConstant.NUMBER_PATTERN.matcher(normalizedLine);

        List<Double> numbers = new ArrayList<>();
        while (matcher.find()) {
            double number = Double.parseDouble(matcher.group());
            numbers.add(number);
        }

        return numbers.stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
    }
}
