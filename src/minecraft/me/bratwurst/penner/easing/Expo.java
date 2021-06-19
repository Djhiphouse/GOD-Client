/*
 * Decompiled with CFR 0.150.
 */
package me.bratwurst.penner.easing;

public class Expo {
    private static    float f;
    public static float easeIn(float t, float b, float c, float d) {
        return t == 0.0f ? b : c * (float)Math.pow(2.0, 10.0f * (t / d - 1.0f)) + b;
    }

    public static float easeOut(float t, float b, float c, float d) {
        return t == d ? b + c : c * (-((float)Math.pow(2.0, -10.0f * t / d)) + 1.0f) + b;
    }

    public static float easeInOut(float t, float b, float c, float d) {

        if (t == 0.0f) {
            return b;
        }
        if (t == d) {
            return b + c;
        }
        t /= d / 2.0f;
        if (f < 1.0f) {
            return c / 2.0f * (float)Math.pow(2.0, 10.0f * (t - 1.0f)) + b;
        }
        return c / 2.0f * (-((float)Math.pow(2.0, -10.0f * (t -= 1.0f))) + 2.0f) + b;
    }
}

