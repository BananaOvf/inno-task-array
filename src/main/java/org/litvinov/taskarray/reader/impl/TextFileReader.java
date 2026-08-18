package org.litvinov.taskarray.reader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.litvinov.taskarray.exception.FileReadException;
import org.litvinov.taskarray.reader.FileLinesReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class TextFileReader implements FileLinesReader {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<String> readLines(String fileName) throws FileReadException {
        if (fileName == null || fileName.isBlank()) {
            throw new FileReadException("File name must not be null or blank");
        }

        try {
            logger.info("Attempt to read lines from: {}", fileName);
            Path path = Path.of(fileName);
            return Files.readAllLines(path);
        } catch (IOException | InvalidPathException e) {
            logger.error("Failed to read file {}", fileName, e);
            throw new FileReadException("Failed to read file: " + fileName, e);
        }
    }
}
