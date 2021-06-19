/*
 * Decompiled with CFR 0.150.
 */
package me.bratwurst.lumien.chunkanimator.handler;

import java.util.WeakHashMap;
import me.bratwurst.lumien.chunkanimator.ChunkAnimator;
import me.bratwurst.penner.easing.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;


public class AnimationHandler {
    WeakHashMap<RenderChunk, AnimationData> timeStamps = new WeakHashMap();

    public void preRender(RenderChunk renderChunk) {
        if (this.timeStamps.containsKey(renderChunk)) {
            int animationDuration;
            long timeDif;
            AnimationData animationData = this.timeStamps.get(renderChunk);
            long time = animationData.timeStamp;
            int mode = ChunkAnimator.INSTANCE.mode;
            if (time == -1L) {
                animationData.timeStamp = time = System.currentTimeMillis();
                if (mode == 4) {
                    int difZ;
                    BlockPos zeroedCenteredChunkPos;
                    BlockPos zeroedPlayerPosition = Minecraft.getMinecraft().thePlayer.getPosition();
                    BlockPos dif = (zeroedPlayerPosition = zeroedPlayerPosition.add(0, -zeroedPlayerPosition.getY(), 0)).subtract(zeroedCenteredChunkPos = renderChunk.getPosition().add(8, -renderChunk.getPosition().getY(), 8));
                    int difX = Math.abs(dif.getX());
                    EnumFacing chunkFacing = difX > (difZ = Math.abs(dif.getZ())) ? (dif.getX() > 0 ? EnumFacing.EAST : EnumFacing.WEST) : (dif.getZ() > 0 ? EnumFacing.SOUTH : EnumFacing.NORTH);
                    animationData.chunkFacing = chunkFacing;
                }
            }
            if ((timeDif = System.currentTimeMillis() - time) < (long)(animationDuration = ChunkAnimator.INSTANCE.animationDuration)) {
                int chunkY = renderChunk.getPosition().getY();
                if (mode == 2) {
                    mode = (double)chunkY < Minecraft.getMinecraft().theWorld.getHorizon() ? 0 : 1;
                }
                if (mode == 4) {
                    mode = 3;
                }
                switch (mode) {
                    case 0: {
                        GlStateManager.translate(0.0f, (float)(-chunkY) + this.getFunctionValue(timeDif, 0.0f, chunkY, animationDuration), 0.0f);
                        break;
                    }
                    case 1: {
                        GlStateManager.translate(0.0f, (float)(256 - chunkY) - this.getFunctionValue(timeDif, 0.0f, 256 - chunkY, animationDuration), 0.0f);
                        break;
                    }
                    case 3: {
                        EnumFacing chunkFacing = animationData.chunkFacing;
                        if (chunkFacing == null) break;
                        Vec3i vec = chunkFacing.getDirectionVec();
                        double mod = -(200.0 - 200.0 / (double)animationDuration * (double)timeDif);
                        mod = -(200.0f - this.getFunctionValue(timeDif, 0.0f, 200.0f, animationDuration));
                        GlStateManager.translate((double)vec.getX() * mod, 0.0, (double)vec.getZ() * mod);
                    }
                }
            } else {
                this.timeStamps.remove(renderChunk);
            }
        }
    }

    private float getFunctionValue(float t, float b, float c, float d) {
        switch (ChunkAnimator.INSTANCE.easingFunction) {
            case 0: {
                return Linear.easeOut(t, b, c, d);
            }
            case 1: {
                return Quad.easeOut(t, b, c, d);
            }
            case 2: {
                return Cubic.easeOut(t, b, c, d);
            }
            case 3: {
                return Quart.easeOut(t, b, c, d);
            }
            case 4: {
                return Quint.easeOut(t, b, c, d);
            }
            case 5: {
                return Expo.easeOut(t, b, c, d);
            }
            case 6: {
                return Sine.easeOut(t, b, c, d);
            }
            case 7: {
                return Circ.easeOut(t, b, c, d);
            }
            case 8: {
                return Back.easeOut(t, b, c, d);
            }
            case 9: {
                return Bounce.easeOut(t, b, c, d);
            }
            case 10: {
                return Elastic.easeOut(t, b, c, d);
            }
        }
        return Sine.easeOut(t, b, c, d);
    }

    public void setOrigin(RenderChunk renderChunk, BlockPos position) {
        if (Minecraft.getMinecraft().thePlayer != null) {
            boolean flag = true;
            BlockPos zeroedPlayerPosition = Minecraft.getMinecraft().thePlayer.getPosition();
            zeroedPlayerPosition = zeroedPlayerPosition.add(0, -zeroedPlayerPosition.getY(), 0);
            BlockPos zeroedCenteredChunkPos = position.add(8, -position.getY(), 8);
            if (ChunkAnimator.INSTANCE.disableAroundPlayer) {
                boolean bl = flag = zeroedPlayerPosition.distanceSq(zeroedCenteredChunkPos) > 4096.0;
            }
            if (flag) {
                EnumFacing chunkFacing = null;
                if (ChunkAnimator.INSTANCE.mode == 3) {
                    int difZ;
                    BlockPos dif = zeroedPlayerPosition.subtract(zeroedCenteredChunkPos);
                    int difX = Math.abs(dif.getX());
                    chunkFacing = difX > (difZ = Math.abs(dif.getZ())) ? (dif.getX() > 0 ? EnumFacing.EAST : EnumFacing.WEST) : (dif.getZ() > 0 ? EnumFacing.SOUTH : EnumFacing.NORTH);
                }
                AnimationData animationData = new AnimationData(-1L, chunkFacing);
                this.timeStamps.put(renderChunk, animationData);
            } else if (this.timeStamps.containsKey(renderChunk)) {
                this.timeStamps.remove(renderChunk);
            }
        }
    }

    private class AnimationData {
        public long timeStamp;
        public EnumFacing chunkFacing;

        public AnimationData(long timeStamp, EnumFacing chunkFacing) {
            this.timeStamp = timeStamp;
            this.chunkFacing = chunkFacing;
        }
    }
}

