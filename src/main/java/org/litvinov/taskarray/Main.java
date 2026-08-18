package org.litvinov.taskarray;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.litvinov.taskarray.entity.DoubleArrayWrapper;
import org.litvinov.taskarray.exception.ArrayWrapperException;
import org.litvinov.taskarray.exception.FileReadException;
import org.litvinov.taskarray.exception.InvalidLineException;
import org.litvinov.taskarray.factory.DoubleArrayWrapperFactory;
import org.litvinov.taskarray.factory.impl.DoubleArrayWrapperFactoryImpl;
import org.litvinov.taskarray.parser.DoubleArrayParser;
import org.litvinov.taskarray.parser.impl.DoubleArrayParserImpl;
import org.litvinov.taskarray.reader.FileLinesReader;
import org.litvinov.taskarray.reader.impl.TextFileReader;
import org.litvinov.taskarray.service.sorting.DoubleArraySorter;
import org.litvinov.taskarray.service.sorting.impl.BubbleSort;
import org.litvinov.taskarray.service.sorting.impl.QuickSort;
import org.litvinov.taskarray.service.statistics.ArrayStatisticsService;
import org.litvinov.taskarray.service.statistics.impl.ArrayStatisticsServiceImpl;
import org.litvinov.taskarray.validator.ArrayLineValidator;
import org.litvinov.taskarray.validator.impl.ArrayLineValidatorImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        String filePath = "data/file.txt";

        FileLinesReader reader = new TextFileReader();
        ArrayLineValidator validator = new ArrayLineValidatorImpl();
        DoubleArrayParser parser = new DoubleArrayParserImpl(validator);
        DoubleArrayWrapperFactory factory = new DoubleArrayWrapperFactoryImpl();
        ArrayStatisticsService statistics = new ArrayStatisticsServiceImpl();
        DoubleArraySorter bubbleSort = new BubbleSort();
        DoubleArraySorter quickSort = new QuickSort();

        try {
            List<DoubleArrayWrapper> arrays = new ArrayList<>();
            List<String> invalidLines = new ArrayList<>();

            List<String> rawLines = reader.readLines(filePath);

            for (String line : rawLines) {
                try {
                    double[] parsed = parser.parse(line);
                    arrays.add(factory.create(parsed));
                } catch (InvalidLineException e) {
                    invalidLines.add(line);
                    logger.warn("Skipping invalid line: {}", line);
                    logger.debug("Invalid line details", e);
                }
            }

            for (DoubleArrayWrapper array : arrays) {

                OptionalDouble min = statistics.min(array);
                OptionalDouble max = statistics.max(array);
                OptionalDouble sum = statistics.sum(array);
                OptionalDouble avg = statistics.avg(array);

                logger.info("Array: {}", array);
                min.ifPresent(v -> logger.info("Min: {}", v));
                max.ifPresent(v -> logger.info("Max: {}", v));
                sum.ifPresent(v -> logger.info("Sum: {}", v));
                avg.ifPresent(v -> logger.info("Avg: {}", v));

                DoubleArrayWrapper arrayClone = factory.create(array.toArray());
                bubbleSort.sort(arrayClone);
                logger.info("After bubbleSort: {}", arrayClone);

                arrayClone = factory.create(array.toArray());
                quickSort.sort(arrayClone);
                logger.info("After quickSort: {}", arrayClone);
            }

            if (!invalidLines.isEmpty()) {
                logger.warn("Invalid lines: {}", invalidLines);
            }
        } catch (FileReadException e) {
            logger.error("Error reading file", e);
        } catch (ArrayWrapperException e) {
            logger.error("Array wrapper error", e);
        }
    }
}
