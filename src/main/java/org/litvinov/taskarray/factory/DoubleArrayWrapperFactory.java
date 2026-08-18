package org.litvinov.taskarray.factory;

import org.litvinov.taskarray.entity.DoubleArrayWrapper;
import org.litvinov.taskarray.exception.ArrayWrapperException;

public interface DoubleArrayWrapperFactory {
    DoubleArrayWrapper create(double[] array) throws ArrayWrapperException;
}
