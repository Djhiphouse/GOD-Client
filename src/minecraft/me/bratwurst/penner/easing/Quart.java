/*
 * Decompiled with CFR 0.150.
 */
package me.bratwurst.penner.easing;

public class Quart {
    private static float f;
    public static float easeIn(float t, float b, float c, float d) {
        return c * (t /= d) * t * t * t + b;
    }

    public static float easeOut(float t, float b, float c, float d) {
        t = t / d - 1.0f;
        return -c * (t * t * t * t - 1.0f) + b;
    }

    public static float easeInOut(float t, float b, float c, float d) {

        t /= d / 2.0f;
        if (f < 1.0f) {
            return c / 2.0f * t * t * t * t + b;
        }
        return -c / 2.0f * ((t -= 2.0f) * t * t * t - 2.0f) + b;
    }
}

