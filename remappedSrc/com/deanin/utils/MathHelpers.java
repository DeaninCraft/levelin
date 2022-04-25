package com.deanin.utils;

public class MathHelpers {
    public static float clampFloat(float value, float _min, float _max) {

        return Math.max(_min, Math.min(_max, value));
    }
    public static int clampInt(int value, int _min, int _max) {

        return Math.max(_min, Math.min(_max, value));
    }
}
