package me.bratwurst.cosmetics.profile;

import java.awt.Color;

public class DragonWingsProfile extends ColorProfile {
    public boolean clear;

    public DragonWingsProfile(boolean isEnabled, Color color, boolean rainbow, boolean clear) {
        setEnabled(isEnabled);
        setColor(color);
        setRainbow(rainbow);
        setClear(clear);
    }

    public boolean isClear() {
        return this.clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }
}

