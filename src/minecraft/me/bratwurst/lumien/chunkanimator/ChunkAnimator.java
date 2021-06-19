/*
 * Decompiled with CFR 0.150.
 */
package me.bratwurst.lumien.chunkanimator;


import me.bratwurst.lumien.chunkanimator.handler.AnimationHandler;

public class ChunkAnimator {
    public static ChunkAnimator INSTANCE;
    public AnimationHandler animationHandler;
    public int mode = 0;
    public int animationDuration = 2000;
    public int easingFunction = 1;
    public boolean disableAroundPlayer = false;

    public ChunkAnimator() {
        INSTANCE = this;
    }

    public void onStart() {
        this.animationHandler = new AnimationHandler();
        this.syncWithModule();
    }

    public void syncWithModule() {
    }
}

