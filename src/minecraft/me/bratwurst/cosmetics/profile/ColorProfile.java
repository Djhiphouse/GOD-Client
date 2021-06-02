package me.bratwurst.cosmetics.profile;
import java.awt.Color;

public abstract class ColorProfile extends Profile {
    public Color color;

    public boolean rainbow;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean isRainbow() {
        return this.rainbow;
    }

    public void setRainbow(boolean rainbow) {
        this.rainbow = rainbow;
    }
}
