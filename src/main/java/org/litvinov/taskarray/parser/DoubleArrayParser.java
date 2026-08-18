package org.litvinov.taskarray.parser;

import org.litvinov.taskarray.exception.InvalidLineException;

public interface DoubleArrayParser {
    double[] parse(String line) throws InvalidLineException;
}
