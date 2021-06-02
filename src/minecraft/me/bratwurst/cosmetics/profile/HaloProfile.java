package me.bratwurst.cosmetics.profile;

import java.awt.Color;

public class HaloProfile extends ColorProfile {
    public HaloProfile(boolean isEnabled, Color color, boolean rainbow) {
        setEnabled(isEnabled);
        setColor(color);
        setRainbow(rainbow);
    }
}

