package com.deanin.utils;

public class MathHelpers {
    public static float clamp(float value, float _min, float _max) {

        return Math.max(_min, Math.min(_max, value));
    }
}
