package com.deanin.utils;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MathHelpersTests {
    @Test
    public void test_clampsFloat_ToValue() {
        float expected = 0.5f;
        float actual = MathHelpers.clampFloat(expected, 0.0f, 1.0f);
        assertEquals(expected, actual);
    }

    @Test
    public void test_clampsFloat_ToMin() {
        float expected = 0.0f;
        float actual = MathHelpers.clampFloat(-0.5f, 0.0f, 1.0f);
        assertEquals(expected, actual);
    }

    @Test
    public void test_clampsFloat_ToMax() {
        float expected = 1.0f;
        float actual = MathHelpers.clampFloat(1.5f, 0.0f, 1.0f);
        assertEquals(expected, actual);
    }

    @Test
    public void test_clampsInt_ToValue() {
        int expected = 1;
        int actual = MathHelpers.clampInt(expected, 0, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void test_clampsInt_ToMin() {
        int expected = 0;
        int actual = MathHelpers.clampInt(-1, 0, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void test_clampsInt_ToMax() {
        int expected = 2;
        int actual = MathHelpers.clampInt(3, 0, 2);
        assertEquals(expected, actual);
    }

}
