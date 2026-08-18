package org.litvinov.taskarray.entity;

import org.litvinov.taskarray.exception.ArrayWrapperException;

import java.util.Arrays;

public final class DoubleArrayWrapper {
    private final double[] array;

    public DoubleArrayWrapper(double[] array) throws ArrayWrapperException {
        if (array == null) {
            throw new ArrayWrapperException("Source array must not be null");
        }
        this.array = array.clone();
    }

    public double get(int index) throws ArrayWrapperException {
        if (index < 0 || index >= array.length) {
            throw new ArrayWrapperException("Index " + index + " is out of bounds of the array");
        }

        return array[index];
    }

    public void set(int index, double value) throws ArrayWrapperException {
        if (index < 0 || index >= array.length) {
            throw new ArrayWrapperException("Index " + index + " is out of bounds of the array");
        }

        array[index] = value;
    }

    public int size() {
        return array.length;
    }

    public boolean isEmpty() {
        return array.length == 0;
    }

    public double[] toArray() {
        return array.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DoubleArrayWrapper that = (DoubleArrayWrapper) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        String className = getClass().getSimpleName();
        return className + "{data=" + Arrays.toString(array) + "}";
    }
}
