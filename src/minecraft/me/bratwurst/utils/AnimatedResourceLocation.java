package me.bratwurst.utils;


import net.minecraft.util.ResourceLocation;

public class AnimatedResourceLocation {
    private String location;

    protected int frames;

    protected int fpt;

    protected int currentTick = 0;

    protected int currentFrame = 0;

    protected ResourceLocation[] textures;

    public AnimatedResourceLocation(String location, int frames, int fpt) {
        this.location = location;
        this.frames = frames;
        this.fpt = fpt;
        this.textures = new ResourceLocation[frames];
        for (int i = 0; i < frames; i++)
            this.textures[i] = new ResourceLocation(String.valueOf(location) + "/" + i + ".png");
    }

    public void update() {
        if (this.currentTick > this.fpt) {
            this.currentTick = 0;
            this.currentFrame++;
            if (this.currentFrame > this.frames - 1)
                this.currentFrame = 0;
        }
        this.currentTick++;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getFrames() {
        return this.frames;
    }

    public ResourceLocation getTexture() {
        return this.textures[this.currentFrame];
    }

    public int getCurrentFrame() {
        return this.currentFrame;
    }
}

