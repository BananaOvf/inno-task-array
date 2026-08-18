package org.litvinov.taskarray.service.sorting;

import org.litvinov.taskarray.entity.DoubleArrayWrapper;
import org.litvinov.taskarray.exception.ArrayWrapperException;

public interface DoubleArraySorter {
    void sort(DoubleArrayWrapper array) throws ArrayWrapperException;
}
