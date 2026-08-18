package org.litvinov.taskarray.service.sorting.impl;

import org.litvinov.taskarray.entity.DoubleArrayWrapper;
import org.litvinov.taskarray.exception.ArrayWrapperException;
import org.litvinov.taskarray.service.sorting.DoubleArraySorter;

import java.util.ArrayDeque;
import java.util.Deque;

public class QuickSort implements DoubleArraySorter {

    @Override
    public void sort(DoubleArrayWrapper array) throws ArrayWrapperException {
        if (array == null) {
            throw new ArrayWrapperException("Array must not be null");
        }

        if (array.isEmpty()) {
            return;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        stack.push(array.size() - 1);

        while (!stack.isEmpty()) {
            int high = stack.pop();
            int low = stack.pop();

            if (low < high) {
                int partitionIndex = partition(array, low, high);

                stack.push(low);
                stack.push(partitionIndex - 1);

                stack.push(partitionIndex + 1);
                stack.push(high);
            }
        }
    }

    private int partition(DoubleArrayWrapper array, int low, int high) throws ArrayWrapperException {
        int middleIndex = low + (high - low) / 2;
        double pivot = array.get(middleIndex);

        swap(array, middleIndex, high);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array.get(j) <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(DoubleArrayWrapper array, int index1, int index2) throws ArrayWrapperException {
        double temp = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);
    }
}