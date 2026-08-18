package org.litvinov.taskarray.service.statistics.impl;

import org.junit.jupiter.api.Test;
import org.litvinov.taskarray.entity.DoubleArrayWrapper;
import org.litvinov.taskarray.exception.ArrayWrapperException;
import org.litvinov.taskarray.service.statistics.ArrayStatisticsService;

import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStatisticsServiceImplTest {

    private static final double[] EMPTY_ARRAY = {};
    private static final double[] SINGLE_ELEMENT_ARRAY = {7.5};
    private static final double[] VALID_ARRAY = {5.0, -2.0, 3.0, -8.0};
    private static final double[] SUM_ARRAY = {1.0, 2.0, 3.5};
    private static final double[] AVG_ARRAY = {1.0, 2.0, 3.0, 4.0};
    private static final double[] NEGATIVE_ARRAY = {-1.0, -2.0, -3.0};

    private final ArrayStatisticsService service = new ArrayStatisticsServiceImpl();

    @Test
    void minShouldThrowForNull() {
        // given
        DoubleArrayWrapper array = null;

        // when & then
        assertThrows(ArrayWrapperException.class, () -> service.min(array));
    }

    @Test
    void minShouldReturnEmptyForEmptyArray() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(EMPTY_ARRAY);

        // when
        OptionalDouble result = service.min(array);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void minShouldReturnMinimumValue() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(VALID_ARRAY);

        // when
        OptionalDouble result = service.min(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(-8.0, result.getAsDouble(), 0.0001);
    }

    @Test
    void maxShouldThrowForNull() {
        // given
        DoubleArrayWrapper array = null;

        // when & then
        assertThrows(ArrayWrapperException.class, () -> service.max(array));
    }

    @Test
    void maxShouldReturnEmptyForEmptyArray() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(EMPTY_ARRAY);

        // when
        OptionalDouble result = service.max(array);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void maxShouldReturnMaximumValue() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(VALID_ARRAY);

        // when
        OptionalDouble result = service.max(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(5.0, result.getAsDouble(), 0.0001);
    }

    @Test
    void sumShouldThrowForNull() {
        // given
        DoubleArrayWrapper array = null;

        // when & then
        assertThrows(ArrayWrapperException.class, () -> service.sum(array));
    }

    @Test
    void sumShouldReturnEmptyForEmptyArray() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(EMPTY_ARRAY);

        // when
        OptionalDouble result = service.sum(array);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void sumShouldReturnCorrectSum() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(SUM_ARRAY);

        // when
        OptionalDouble result = service.sum(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(6.5, result.getAsDouble(), 0.0001);
    }

    @Test
    void avgShouldThrowForNull() {
        // given
        DoubleArrayWrapper array = null;

        // when & then
        assertThrows(ArrayWrapperException.class, () -> service.avg(array));
    }

    @Test
    void avgShouldReturnEmptyForEmptyArray() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(new double[]{});

        // when
        OptionalDouble result = service.avg(array);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void avgShouldReturnCorrectAverage() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(AVG_ARRAY);

        // when
        OptionalDouble result = service.avg(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(2.5, result.getAsDouble(), 0.0001);
    }

    @Test
    void minShouldReturnSingleElement() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(SINGLE_ELEMENT_ARRAY);

        // when
        OptionalDouble result = service.min(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(7.5, result.getAsDouble(), 0.0001);
    }

    @Test
    void maxShouldReturnSingleElement() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(SINGLE_ELEMENT_ARRAY);

        // when
        OptionalDouble result = service.max(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(7.5, result.getAsDouble(), 0.0001);
    }

    @Test
    void sumShouldReturnSingleElement() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(SINGLE_ELEMENT_ARRAY);

        // when
        OptionalDouble result = service.sum(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(7.5, result.getAsDouble(), 0.0001);
    }

    @Test
    void avgShouldReturnSingleElement() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(SINGLE_ELEMENT_ARRAY);

        // when
        OptionalDouble result = service.avg(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(7.5, result.getAsDouble(), 0.0001);
    }

    @Test
    void sumShouldHandleNegativeNumbers() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(NEGATIVE_ARRAY);

        // when
        OptionalDouble result = service.sum(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(-6.0, result.getAsDouble(), 0.0001);
    }

    @Test
    void avgShouldHandleNegativeNumbers() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(NEGATIVE_ARRAY);

        // when
        OptionalDouble result = service.avg(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(-2.0, result.getAsDouble(), 0.0001);
    }
}