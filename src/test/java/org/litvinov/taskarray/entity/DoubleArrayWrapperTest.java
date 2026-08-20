package org.litvinov.taskarray.entity;

import org.junit.jupiter.api.Test;
import org.litvinov.taskarray.exception.ArrayWrapperException;

import static org.junit.jupiter.api.Assertions.*;

class DoubleArrayWrapperTest {

    private static final double[] VALID_ARRAY_1 = {1.0, 2.5, -3.0};
    private static final double[] VALID_ARRAY_2 = {1.0, 2.0, 3.0};
    private static final double[] EMPTY_ARRAY = {};

    @Test
    void constructorShouldThrowWhenArrayIsNull() {
        // given
        double[] nullArray = null;

        // when & then
        assertThrows(ArrayWrapperException.class, () -> new DoubleArrayWrapper(nullArray));
    }

    @Test
    void constructorShouldCreateWrapperFromValidArray() throws ArrayWrapperException {
        // given & when
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);

        // then
        assertNotNull(wrapper);
        assertEquals(3, wrapper.size());
        assertFalse(wrapper.isEmpty());
    }

    @Test
    void constructorShouldCreateWrapperFromEmptyArray() throws ArrayWrapperException {
        // given & when
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(EMPTY_ARRAY);

        // then
        assertNotNull(wrapper);
        assertEquals(0, wrapper.size());
        assertTrue(wrapper.isEmpty());
    }

    @Test
    void getShouldReturnElementByValidIndex() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);

        // when
        double value = wrapper.get(1);

        // then
        assertEquals(2.5, value, 0.0001);
    }

    @Test
    void getShouldThrowWhenIndexIsNegative() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);

        // when & then
        assertThrows(ArrayWrapperException.class, () -> wrapper.get(-1));
    }

    @Test
    void getShouldThrowWhenIndexIsOutOfBounds() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);

        // when & then
        assertThrows(ArrayWrapperException.class, () -> wrapper.get(VALID_ARRAY_1.length));
    }

    @Test
    void setShouldSetElementByValidIndex() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);

        // when
        wrapper.set(0, 10.0);

        // then
        assertEquals(10.0, wrapper.get(0), 0.0001);
    }

    @Test
    void setShouldThrowWhenIndexIsNegative() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);

        // when & then
        assertThrows(ArrayWrapperException.class, () -> wrapper.set(-1, 1.0));
    }

    @Test
    void setShouldThrowWhenIndexIsOutOfBounds() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);

        // when & then
        assertThrows(ArrayWrapperException.class, () -> wrapper.set(VALID_ARRAY_1.length, 1.0));
    }

    @Test
    void setShouldSetLastElementByValidIndex() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);

        // when
        wrapper.set(VALID_ARRAY_1.length - 1, 10.0);

        // then
        assertEquals(10.0, wrapper.get(VALID_ARRAY_1.length - 1), 0.0001);
    }

    @Test
    void toArrayShouldReturnCopyNotOriginal() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);
        double[] returned = wrapper.toArray();

        // when
        returned[0] = 999.0;

        // then
        assertNotEquals(999.0, wrapper.get(0), 0.0001);
        assertEquals(1.0, wrapper.get(0), 0.0001);
    }

    @Test
    void getShouldReturnLastElementByValidIndex() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);

        // when
        double value = wrapper.get(VALID_ARRAY_1.length - 1);

        // then
        assertEquals(-3.0, value, 0.0001);
    }

    @Test
    void equalsShouldReturnTrueForSameObject() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);

        // when
        boolean result = wrapper.equals(wrapper);

        // then
        assertTrue(result);
    }

    @Test
    void equalsShouldReturnFalseForNull() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);

        // when
        boolean result = wrapper.equals(null);

        // then
        assertFalse(result);
    }

    @Test
    void equalsShouldReturnFalseForDifferentClass() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);
        Object other = new Object();

        // when
        boolean result = wrapper.equals(other);

        // then
        assertFalse(result);
    }

    @Test
    void equalsShouldReturnTrueForEqualArrays() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper1 = new DoubleArrayWrapper(VALID_ARRAY_1);
        DoubleArrayWrapper wrapper2 = new DoubleArrayWrapper(VALID_ARRAY_1.clone());

        // when
        boolean result = wrapper1.equals(wrapper2);

        // then
        assertTrue(result);
    }

    @Test
    void equalsShouldReturnFalseForDifferentArrays() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper1 = new DoubleArrayWrapper(VALID_ARRAY_1);
        DoubleArrayWrapper wrapper2 = new DoubleArrayWrapper(VALID_ARRAY_2);

        // when
        boolean result = wrapper1.equals(wrapper2);

        // then
        assertFalse(result);
    }

    @Test
    void hashCodeShouldBeEqualForEqualObjects() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper1 = new DoubleArrayWrapper(VALID_ARRAY_1);
        DoubleArrayWrapper wrapper2 = new DoubleArrayWrapper(VALID_ARRAY_1.clone());

        // when
        int hash1 = wrapper1.hashCode();
        int hash2 = wrapper2.hashCode();

        // then
        assertEquals(hash1, hash2);
    }

    @Test
    void toStringShouldContainClassName() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);

        // when
        String result = wrapper.toString();

        // then
        assertTrue(result.contains("DoubleArrayWrapper"));
    }

    @Test
    void constructorShouldCopySourceArray() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_2);

        // when
        VALID_ARRAY_2[0] = 99.0;
        VALID_ARRAY_2[1] = 88.0;

        // then
        assertEquals(1.0, wrapper.get(0), 0.0001);
        assertEquals(2.0, wrapper.get(1), 0.0001);
        assertEquals(3.0, wrapper.get(2), 0.0001);
    }

    @Test
    void toArrayShouldReturnNewArrayInstance() throws ArrayWrapperException {
        // given
        DoubleArrayWrapper wrapper = new DoubleArrayWrapper(VALID_ARRAY_1);

        // when
        double[] firstCall = wrapper.toArray();
        double[] secondCall = wrapper.toArray();

        // then
        assertNotSame(firstCall, secondCall);
        assertNotSame(firstCall, wrapper.toArray());
    }
}