package org.litvinov.taskarray.service.statistics;

import org.litvinov.taskarray.entity.DoubleArrayWrapper;
import org.litvinov.taskarray.exception.ArrayWrapperException;

import java.util.OptionalDouble;

public interface ArrayStatisticsService {
    OptionalDouble min(DoubleArrayWrapper array) throws ArrayWrapperException;

    OptionalDouble max(DoubleArrayWrapper array) throws ArrayWrapperException;

    OptionalDouble sum(DoubleArrayWrapper array) throws ArrayWrapperException;

    OptionalDouble avg(DoubleArrayWrapper array) throws ArrayWrapperException;
}
