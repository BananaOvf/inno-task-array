package org.litvinov.taskarray.reader;

import org.litvinov.taskarray.exception.FileReadException;

import java.util.List;

public interface FileLinesReader {
    List<String> readLines(String fileName) throws FileReadException;
}
