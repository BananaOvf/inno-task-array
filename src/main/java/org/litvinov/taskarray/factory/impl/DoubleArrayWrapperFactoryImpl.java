package org.litvinov.taskarray.factory.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.litvinov.taskarray.entity.DoubleArrayWrapper;
import org.litvinov.taskarray.exception.ArrayWrapperException;
import org.litvinov.taskarray.factory.DoubleArrayWrapperFactory;

public class DoubleArrayWrapperFactoryImpl implements DoubleArrayWrapperFactory {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public DoubleArrayWrapper create(double[] array) throws ArrayWrapperException {
        logger.debug("Create DoubleArrayWrapper");
        return new DoubleArrayWrapper(array);
    }
}
