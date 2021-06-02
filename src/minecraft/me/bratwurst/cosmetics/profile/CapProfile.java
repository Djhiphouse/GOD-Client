package me.bratwurst.cosmetics.profile;


import java.awt.Color;

public class CapProfile extends ColorProfile {
    public CapProfile(boolean isEnabled, Color color, boolean rainbow) {
        setEnabled(isEnabled);
        setColor(color);
        setRainbow(rainbow);
    }
}

