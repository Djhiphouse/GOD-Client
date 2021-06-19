/*
 * Decompiled with CFR 0.150.
 */
package me.bratwurst.penner.easing;

public class Back {
  private static   float f;
  private static float ff;
    public static float easeIn(float t, float b, float c, float d) {
        float s = 1.70158f;
        return c * (t /= d) * t * ((s + 1.0f) * t - s) + b;
    }

    public static float easeIn(float t, float b, float c, float d, float s) {
        return c * (t /= d) * t * ((s + 1.0f) * t - s) + b;
    }

    public static float easeOut(float t, float b, float c, float d) {
        float s = 1.70158f;
        t = t / d - 1.0f;
        return c * (t * t * ((s + 1.0f) * t + s) + 1.0f) + b;
    }

    public static float easeOut(float t, float b, float c, float d, float s) {
        t = t / d - 1.0f;
        return c * (t * t * ((s + 1.0f) * t + s) + 1.0f) + b;
    }

    public static float easeInOut(float t, float b, float c, float d) {

        float s = 1.70158f;
        t /= d / 2.0f;
        if (f < 1.0f) {
            return c / 2.0f * (t * t * (((s *= 1.525f) + 1.0f) * t - s)) + b;
        }
        return c / 2.0f * ((t -= 2.0f) * t * (((s *= 1.525f) + 1.0f) * t + s) + 2.0f) + b;
    }

    public static float easeInOut(float t, float b, float c, float d, float s) {

        t /= d / 2.0f;
        if (f < 1.0f) {
            return c / 2.0f * (t * t * (((s *= 1.525f) + 1.0f) * t - s)) + b;
        }
        return c / 2.0f * ((t -= 2.0f) * t * (((s *= 1.525f) + 1.0f) * t + s) + 2.0f) + b;
    }
}

