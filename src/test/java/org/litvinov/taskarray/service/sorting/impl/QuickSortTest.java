package org.litvinov.taskarray.service.sorting.impl;

import org.litvinov.taskarray.service.sorting.DoubleArraySorter;

class QuickSortTest extends AbstractSorterTest {
    @Override
    protected DoubleArraySorter getSorter() {
        return new QuickSort();
    }
}