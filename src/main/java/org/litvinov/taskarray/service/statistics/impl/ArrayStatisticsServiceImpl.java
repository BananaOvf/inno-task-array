package org.litvinov.taskarray.service.statistics.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.litvinov.taskarray.entity.DoubleArrayWrapper;
import org.litvinov.taskarray.exception.ArrayWrapperException;
import org.litvinov.taskarray.service.statistics.ArrayStatisticsService;

import java.util.OptionalDouble;

public class ArrayStatisticsServiceImpl implements ArrayStatisticsService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public OptionalDouble min(DoubleArrayWrapper array) throws ArrayWrapperException {
        logger.debug("Finding minimum in {}", array);

        if (array == null) {
            throw new ArrayWrapperException("Array must not be null");
        }

        if (array.isEmpty()) {
            return OptionalDouble.empty();
        }

        double min = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            double current = array.get(i);
            if (current < min) {
                min = current;
            }
        }
        return OptionalDouble.of(min);
    }

    @Override
    public OptionalDouble max(DoubleArrayWrapper array) throws ArrayWrapperException {
        logger.debug("Finding maximum in {}", array);

        if (array == null) {
            throw new ArrayWrapperException("Array must not be null");
        }

        if (array.isEmpty()) {
            return OptionalDouble.empty();
        }

        double max = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            double current = array.get(i);
            if (current > max) {
                max = current;
            }
        }
        return OptionalDouble.of(max);
    }

    @Override
    public OptionalDouble sum(DoubleArrayWrapper array) throws ArrayWrapperException {
        logger.debug("Finding sum in {}", array);

        if (array == null) {
            throw new ArrayWrapperException("Array must not be null");
        }

        if (array.isEmpty()) {
            return OptionalDouble.empty();
        }

        double sum = sumValue(array);
        return OptionalDouble.of(sum);
    }

    @Override
    public OptionalDouble avg(DoubleArrayWrapper array) throws ArrayWrapperException {
        logger.debug("Finding average in {}", array);

        if (array == null) {
            throw new ArrayWrapperException("Array must not be null");
        }

        if (array.isEmpty()) {
            return OptionalDouble.empty();
        }

        double average = sumValue(array) / array.size();
        return OptionalDouble.of(average);
    }

    private double sumValue(DoubleArrayWrapper array) throws ArrayWrapperException {
        double sum = 0;
        for (int i = 0; i < array.size(); i++) {
            sum += array.get(i);
        }
        return sum;
    }
}
