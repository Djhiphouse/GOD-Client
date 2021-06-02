package me.bratwurst.cosmetics.profile;

import java.awt.Color;

public class WitchHatProfile extends ColorProfile {
    public WitchHatProfile(boolean isEnabled, Color color, boolean rainbow) {
        setEnabled(isEnabled);
        setColor(color);
        setRainbow(rainbow);
    }
}
