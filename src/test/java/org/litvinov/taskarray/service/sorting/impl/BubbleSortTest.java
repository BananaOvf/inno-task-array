package org.litvinov.taskarray.service.sorting.impl;

import org.litvinov.taskarray.service.sorting.DoubleArraySorter;

class BubbleSortTest extends AbstractSorterTest {
    @Override
    protected DoubleArraySorter getSorter() {
        return new BubbleSort();
    }
}