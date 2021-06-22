package me.bratwurst.news.newutils;

import java.awt.*;

public class NewColors {
    public static Color getRainbow(long delay, double time) {
        double rainbowState = Math.ceil(((double)System.currentTimeMillis() * time + (double)delay) / 20.0);
        return Color.getHSBColor((float)((rainbowState %= 360.0) / 360.0), 0.8f, 0.7f);
    }
}
