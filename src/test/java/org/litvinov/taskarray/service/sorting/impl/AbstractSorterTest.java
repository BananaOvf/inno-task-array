package org.litvinov.taskarray.service.sorting.impl;

import org.junit.jupiter.api.Test;
import org.litvinov.taskarray.entity.DoubleArrayWrapper;
import org.litvinov.taskarray.exception.ArrayWrapperException;
import org.litvinov.taskarray.service.sorting.DoubleArraySorter;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractSorterTest {

    protected static final double[] EMPTY_ARRAY = {};
    protected static final double[] SINGLE_ELEMENT_ARRAY = {5.0};
    protected static final double[] SORTED_ARRAY = {1.0, 2.0, 3.0};
    protected static final double[] REVERSE_ARRAY = {3.0, 2.0, 1.0};
    protected static final double[] DUPLICATES_ARRAY = {2.0, 1.0, 2.0, 1.0};
    protected static final double[] SORTED_DUPLICATES_ARRAY = {1.0, 1.0, 2.0, 2.0};
    protected static final double[] NEGATIVE_ARRAY = {-1.0, -5.0, 0.0, 3.0, -2.0};
    protected static final double[] SORTED_NEGATIVE_ARRAY = {-5.0, -2.0, -1.0, 0.0, 3.0};
    protected static final double[] DECIMAL_ARRAY = {1.5, 1.2, 2.0, 0.5};
    protected static final double[] SORTED_DECIMAL_ARRAY = {0.5, 1.2, 1.5, 2.0};
    protected static final double[] ALL_EQUAL_ARRAY = {5.0, 5.0, 5.0, 5.0};

    protected abstract DoubleArraySorter getSorter();

    @Test
    void sortShouldThrowWhenArrayIsNull() {
        // given
        DoubleArrayWrapper array = null;

        // when & then
        assertThrows(ArrayWrapperException.class, () -> getSorter().sort(array));
    }

    @Test
    void sortShouldDoNothingForEmptyArray() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(EMPTY_ARRAY);

        // when
        getSorter().sort(array);

        // then
        assertTrue(array.isEmpty());
    }

    @Test
    void sortShouldDoNothingForSingleElementArray() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(SINGLE_ELEMENT_ARRAY);

        // when
        getSorter().sort(array);

        // then
        assertArrayEquals(SINGLE_ELEMENT_ARRAY, array.toArray(), 0.0001);
    }

    @Test
    void sortShouldKeepSortedArrayUnchanged() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(SORTED_ARRAY);

        // when
        getSorter().sort(array);

        // then
        assertArrayEquals(SORTED_ARRAY, array.toArray(), 0.0001);
    }

    @Test
    void sortShouldSortReverseOrderArray() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(REVERSE_ARRAY);

        // when
        getSorter().sort(array);

        // then
        assertArrayEquals(SORTED_ARRAY, array.toArray(), 0.0001);
    }

    @Test
    void sortShouldSortArrayWithDuplicates() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(DUPLICATES_ARRAY);

        // when
        getSorter().sort(array);

        // then
        assertArrayEquals(SORTED_DUPLICATES_ARRAY, array.toArray(), 0.0001);
    }

    @Test
    void sortShouldSortArrayWithNegativeNumbers() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(NEGATIVE_ARRAY);

        // when
        getSorter().sort(array);

        // then
        assertArrayEquals(SORTED_NEGATIVE_ARRAY, array.toArray(), 0.0001);
    }

    @Test
    void sortShouldSortArrayWithDecimalValues() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(DECIMAL_ARRAY);

        // when
        getSorter().sort(array);

        // then
        assertArrayEquals(SORTED_DECIMAL_ARRAY, array.toArray(), 0.0001);
    }

    @Test
    void sortShouldHandleArrayWithAllEqualElements() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper array = new DoubleArrayWrapper(ALL_EQUAL_ARRAY);

        // when
        getSorter().sort(array);

        // then
        assertArrayEquals(ALL_EQUAL_ARRAY, array.toArray(), 0.0001);
    }
}