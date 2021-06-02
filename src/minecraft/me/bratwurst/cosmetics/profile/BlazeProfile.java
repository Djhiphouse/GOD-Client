package me.bratwurst.cosmetics.profile;

import java.awt.Color;

public class BlazeProfile extends ColorProfile {
    public BlazeProfile(boolean isEnabled, Color color, boolean rainbow) {
        setEnabled(isEnabled);
        setColor(color);
        setRainbow(rainbow);
    }
}
