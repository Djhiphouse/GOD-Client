package me.bratwurst.utils;

import java.awt.Color;

public class FTools_Colors {
    public static Color getRainbow(long delay, double time) {
        double rainbowState = Math.ceil(((double)System.currentTimeMillis() * time + (double)delay) / 20.0);
        return Color.getHSBColor((float)((rainbowState %= 360.0) / 360.0), 0.8f, 0.7f);
    }
}
