package org.litvinov.taskarray.service.sorting.impl;

import org.litvinov.taskarray.entity.DoubleArrayWrapper;
import org.litvinov.taskarray.exception.ArrayWrapperException;
import org.litvinov.taskarray.service.sorting.DoubleArraySorter;

public class BubbleSort implements DoubleArraySorter {
    @Override
    public void sort(DoubleArrayWrapper array) throws ArrayWrapperException {
        if (array == null) {
            throw new ArrayWrapperException("Array must not be null");
        }

        if (array.isEmpty()) {
            return;
        }

        int n = array.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    double temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
    }
}
