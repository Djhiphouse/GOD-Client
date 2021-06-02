package me.bratwurst.cosmetics.profile;

public abstract class Profile {
    public boolean isEnabled;

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }
}

