/*
 * Decompiled with CFR 0.150.
 */
package me.bratwurst.penner.easing;

public class Elastic {
    public static float easeIn(float t, float b, float c, float d) {
        if (t == 0.0f) {
            return b;
        }
        if ((t /= d) == 1.0f) {
            return b + c;
        }
        float p = d * 0.3f;
        float a = c;
        float s = p / 4.0f;
        return -(a * (float)Math.pow(2.0, 10.0f * (t -= 1.0f)) * (float)Math.sin((t * d - s) * ((float)Math.PI * 2) / p)) + b;
    }

    public static float easeIn(float t, float b, float c, float d, float a, float p) {
        float s;
        if (t == 0.0f) {
            return b;
        }
        if ((t /= d) == 1.0f) {
            return b + c;
        }
        if (a < Math.abs(c)) {
            a = c;
            s = p / 4.0f;
        } else {
            s = p / ((float)Math.PI * 2) * (float)Math.asin(c / a);
        }
        return -(a * (float)Math.pow(2.0, 10.0f * (t -= 1.0f)) * (float)Math.sin((double)(t * d - s) * (Math.PI * 2) / (double)p)) + b;
    }

    public static float easeOut(float t, float b, float c, float d) {
        if (t == 0.0f) {
            return b;
        }
        if ((t /= d) == 1.0f) {
            return b + c;
        }
        float p = d * 0.3f;
        float a = c;
        float s = p / 4.0f;
        return a * (float)Math.pow(2.0, -10.0f * t) * (float)Math.sin((t * d - s) * ((float)Math.PI * 2) / p) + c + b;
    }

    public static float easeOut(float t, float b, float c, float d, float a, float p) {
        float s;
        if (t == 0.0f) {
            return b;
        }
        if ((t /= d) == 1.0f) {
            return b + c;
        }
        if (a < Math.abs(c)) {
            a = c;
            s = p / 4.0f;
        } else {
            s = p / ((float)Math.PI * 2) * (float)Math.asin(c / a);
        }
        return a * (float)Math.pow(2.0, -10.0f * t) * (float)Math.sin((t * d - s) * ((float)Math.PI * 2) / p) + c + b;
    }

    public static float easeInOut(float t, float b, float c, float d) {
        if (t == 0.0f) {
            return b;
        }
        if ((t /= d / 2.0f) == 2.0f) {
            return b + c;
        }
        float p = d * 0.45000002f;
        float a = c;
        float s = p / 4.0f;
        if (t < 1.0f) {
            return -0.5f * (a * (float)Math.pow(2.0, 10.0f * (t -= 1.0f)) * (float)Math.sin((t * d - s) * ((float)Math.PI * 2) / p)) + b;
        }
        return a * (float)Math.pow(2.0, -10.0f * (t -= 1.0f)) * (float)Math.sin((t * d - s) * ((float)Math.PI * 2) / p) * 0.5f + c + b;
    }

    public static float easeInOut(float t, float b, float c, float d, float a, float p) {
        float s;
        if (t == 0.0f) {
            return b;
        }
        if ((t /= d / 2.0f) == 2.0f) {
            return b + c;
        }
        if (a < Math.abs(c)) {
            a = c;
            s = p / 4.0f;
        } else {
            s = p / ((float)Math.PI * 2) * (float)Math.asin(c / a);
        }
        if (t < 1.0f) {
            return -0.5f * (a * (float)Math.pow(2.0, 10.0f * (t -= 1.0f)) * (float)Math.sin((t * d - s) * ((float)Math.PI * 2) / p)) + b;
        }
        return a * (float)Math.pow(2.0, -10.0f * (t -= 1.0f)) * (float)Math.sin((t * d - s) * ((float)Math.PI * 2) / p) * 0.5f + c + b;
    }
}

