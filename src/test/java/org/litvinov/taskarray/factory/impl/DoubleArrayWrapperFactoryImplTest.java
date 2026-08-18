package org.litvinov.taskarray.factory.impl;

import org.junit.jupiter.api.Test;
import org.litvinov.taskarray.entity.DoubleArrayWrapper;
import org.litvinov.taskarray.exception.ArrayWrapperException;
import org.litvinov.taskarray.factory.DoubleArrayWrapperFactory;

import static org.junit.jupiter.api.Assertions.*;

class DoubleArrayWrapperFactoryImplTest {

    private static final double[] VALID_ARRAY = {1.0, 2.0, 3.5};
    private static final double[] NULL_ARRAY = null;

    private final DoubleArrayWrapperFactory factory = new DoubleArrayWrapperFactoryImpl();

    @Test
    void createShouldThrowWhenArrayIsNull() {
        // given & when & then
        assertThrows(ArrayWrapperException.class, () -> factory.create(NULL_ARRAY));
    }

    @Test
    void createShouldReturnWrapperWithSameElements() throws ArrayWrapperException {
        // given & when
        DoubleArrayWrapper wrapper = factory.create(VALID_ARRAY);

        // then
        assertNotNull(wrapper);
        assertArrayEquals(VALID_ARRAY, wrapper.toArray(), 0.0001);
    }

    @Test
    void createShouldReturnDifferentWrapperInstanceForSameArray() throws ArrayWrapperException {
        // given & when
        DoubleArrayWrapper wrapper1 = factory.create(VALID_ARRAY);
        DoubleArrayWrapper wrapper2 = factory.create(VALID_ARRAY);

        // then
        assertNotSame(wrapper1, wrapper2);
        assertEquals(wrapper1, wrapper2);
    }
}