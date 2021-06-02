package me.bratwurst.cosmetics.profile;

public class CapeProfile extends Profile {
    String location;

    int frames;

    public CapeProfile(boolean isEnaled, String location, int frames) {
        setEnabled(isEnaled);
        this.location = location;
        this.frames = frames;
    }

    public String getLocation() {
        return this.location;
    }

    public int getFrames() {
        return this.frames;
    }
}

