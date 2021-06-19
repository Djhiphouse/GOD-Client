/*
 * Decompiled with CFR 0.150.
 */
package me.bratwurst.penner.easing;

public class Sine {
    public static float easeIn(float t, float b, float c, float d) {
        return -c * (float)Math.cos((double)(t / d) * 1.5707963267948966) + c + b;
    }

    public static float easeOut(float t, float b, float c, float d) {
        return c * (float)Math.sin((double)(t / d) * 1.5707963267948966) + b;
    }

    public static float easeInOut(float t, float b, float c, float d) {
        return -c / 2.0f * ((float)Math.cos(Math.PI * (double)t / (double)d) - 1.0f) + b;
    }
}

