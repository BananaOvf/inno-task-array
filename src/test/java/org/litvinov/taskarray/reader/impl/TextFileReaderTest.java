package org.litvinov.taskarray.reader.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.litvinov.taskarray.exception.FileReadException;
import org.litvinov.taskarray.reader.FileLinesReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextFileReaderTest {

    private final FileLinesReader reader = new TextFileReader();
    @TempDir
    Path tempDir;

    @Test
    void readLinesShouldThrowForNullFileName() {
        // given
        String fileName = null;

        // when & then
        assertThrows(FileReadException.class, () -> reader.readLines(fileName));
    }

    @Test
    void readLinesShouldThrowForBlankFileName() {
        // given
        String fileName = "   ";

        // when & then
        assertThrows(FileReadException.class, () -> reader.readLines(fileName));
    }

    @Test
    void readLinesShouldThrowForNonExistentFile() {
        // given
        String fileName = tempDir.resolve("nonexistent.txt").toString();

        // when & then
        assertThrows(FileReadException.class, () -> reader.readLines(fileName));
    }

    @Test
    void readLinesShouldReturnAllLinesFromFile() throws IOException, FileReadException {
        // given
        Path file = tempDir.resolve("data.txt");
        Files.write(file, List.of("line1", "line2", ""));

        // when
        List<String> lines = reader.readLines(file.toString());

        // then
        assertNotNull(lines);
        assertEquals(3, lines.size());
        assertEquals("line1", lines.get(0));
        assertEquals("line2", lines.get(1));
        assertEquals("", lines.get(2));
    }

    @Test
    void readLinesShouldThrowIfFileIsDirectory() throws IOException {
        // given
        Path dir = tempDir.resolve("dir");
        Files.createDirectory(dir);

        // when & then
        assertThrows(FileReadException.class, () -> reader.readLines(dir.toString()));
    }
}