/*
 * Decompiled with CFR 0.150.
 */
package me.bratwurst.penner.easing;

public class Bounce {
    private static float f;
    public static float easeIn(float t, float b, float c, float d) {
        return c - Bounce.easeOut(d - t, 0.0f, c, d) + b;
    }

    public static float easeOut(float t, float b, float c, float d) {

        t /= d;
        if (f < 0.36363637f) {
            return c * (7.5625f * t * t) + b;
        }
        if (t < 0.72727275f) {
            return c * (7.5625f * (t -= 0.54545456f) * t + 0.75f) + b;
        }
        if ((double)t < 0.9090909090909091) {
            return c * (7.5625f * (t -= 0.8181818f) * t + 0.9375f) + b;
        }
        return c * (7.5625f * (t -= 0.95454544f) * t + 0.984375f) + b;
    }

    public static float easeInOut(float t, float b, float c, float d) {
        if (t < d / 2.0f) {
            return Bounce.easeIn(t * 2.0f, 0.0f, c, d) * 0.5f + b;
        }
        return Bounce.easeOut(t * 2.0f - d, 0.0f, c, d) * 0.5f + c * 0.5f + b;
    }
}

