package me.bratwurst.module.modules.movement;

import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.*;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.module.modules.combat.Aura;
import me.bratwurst.utils.MovementUtils;
import me.bratwurst.utils.StrafeUtil;
import me.bratwurst.utils.player.RotationUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class TargetStrafe extends Module {
    public static boolean flagged;
    public static double dir = 1;
    public TargetStrafe() {
        super("TargetStrafe", Category.MOVEMENT);
    }
    private int direction = -1;
    public boolean esp = true;
    public static double distance = 8;
    float speed = 0.099f;

    @EventTarget
    public void onPlayerUpdate(EventUpdate event) {
        if (Client.getInstance().getModuleManager().getModuleByName("Aura").isToggle()) {
            // mc.thePlayer.movementInput.setForward(0);
            //doStrafeAtSpeed(event, getSpeed() + speed);
            if (event.isPre()) {
                if (mc.thePlayer.isCollidedHorizontally) {
                    this.switchDirection();

                }

                if (mc.gameSettings.keyBindLeft.isPressed()) {
                    this.direction = 1;
                }

                if (mc.gameSettings.keyBindRight.isPressed()) {
                    this.direction = -1;
                }
            }
        }
    }
    private void switchDirection() {
        if (this.direction == 1) {
            this.direction = -1;
        } else {
            this.direction = 1;
        }

    }
    public static float getSpeed() {
        return (float) Math
                .sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ);
    }
    @EventTarget
    public void Move(MoveEvent event) {
        if (Client.getInstance().getModuleManager().getModuleByName("Aura").isToggle()) {
            if (canStrafe()) {
                strafe(event,2);
            }
        }
    }
    public void strafe(MoveEvent event, double moveSpeed) {
        EntityLivingBase target = Aura.target1;
        float[] rotations = RotationUtils.getRotationsEntity(target);
        if ((double)mc.thePlayer.getDistanceToEntity(target) <= 5) {
            MovementUtils.setSpeed(event, moveSpeed, rotations[0], (double)this.direction, 0.0D);
        } else {
            MovementUtils.setSpeed(event, moveSpeed, rotations[0], (double)this.direction, 1.0D);
        }
        event.setCancelled(true);
    }

    @EventTarget
    public final void onRender3D(Event3D event) {
        if (esp) {
            if (Aura.target1 instanceof EntityPlayer) {
                drawESP(Aura.target1, event.getPartialTicks(), distance);
            }
        }
    }
    public static boolean canStrafe() {
        return Client.getInstance().getModuleManager().getModuleByName("Aura").isEnabled() && Aura.target1 != null;
    }
    public double yLevel;
    boolean decreasing;

    private void drawESP(Entity entity, float partialTicks, double distance) {
        final float r = ((float) 1 / 255) * Color.WHITE.getRed();
        final float g = ((float) 1 / 255) * Color.WHITE.getGreen();
        final float b = ((float) 1 / 255) * Color.WHITE.getBlue();
        for (double il = 0; il < 0.05; il += 0.0006) {
            GL11.glPushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.disableDepth();
            GL11.glDepthMask(false);
            GL11.glLineWidth(.1f);
            GL11.glBegin(1);
            yLevel += decreasing ? -0.0001 : 0.0001;
            if (yLevel > 1.9) {
                decreasing = true;
            }
            if (yLevel <= 0) {
                decreasing = false;
            }
            final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks
                    - mc.getRenderManager().viewerPosX;
            double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks
                    - mc.getRenderManager().viewerPosY;
            final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks
                    - mc.getRenderManager().viewerPosZ;

            final double xD = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks
                    - mc.getRenderManager().viewerPosX;
            final double yD = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks
                    - mc.getRenderManager().viewerPosY;
            final double zD = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks
                    - mc.getRenderManager().viewerPosZ;

            final float rD = Color.black.getRGB();
            final float gD = Color.black.getRGB();
            final float bD = Color.black.getRGB();

            final double pix3 = Math.PI * 2.0D;
            y += yLevel;
            final double pix2 = Math.PI * 2D;
            for (int i = 0; i <= 90; ++i) {
                GL11.glColor3f(0, 0, 0);

                GL11.glVertex3d(x + distance * Math.cos(i * pix2 / 45.0), y + il,
                        z + distance * Math.sin(i * pix2 / 45.0));
                GL11.glVertex3d(x + distance * Math.cos(i * pix2 / 45.0), y + il - yLevel,
                        z + distance * Math.sin(i * pix2 / 45.0));
            }

            GL11.glEnd();
            GL11.glDepthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GL11.glPopMatrix();
        }

        for (double il = 0; il < 0.05; il += 0.0006) {
            GL11.glPushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.disableDepth();
            GL11.glDepthMask(false);
            GL11.glLineWidth(0.25f);
            GL11.glBegin(1);
            final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks
                    - mc.getRenderManager().viewerPosX;
            double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks
                    - mc.getRenderManager().viewerPosY;
            final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks
                    - mc.getRenderManager().viewerPosZ;

            y += yLevel;

            final double pix2 = Math.PI * 2D;

            float speed = 1200f;
            float baseHue = System.currentTimeMillis() % (int) speed;
            while (baseHue > speed) {
                baseHue -= speed;
            }
            baseHue /= speed;

            for (int i = 0; i <= 90; ++i) {
                float max = ((float) i) / 45F;
                float hue = max + baseHue;
                while (hue > 1) {
                    hue -= 1;
                }
                final float red = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getRed();
                final float green = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getGreen();
                final float blue = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getBlue();
                GL11.glColor3f(red, green, blue);
                GL11.glVertex3d(x + distance * Math.cos(i * pix2 / 45.0), y + il,
                        z + distance * Math.sin(i * pix2 / 45.0));
            }

            GL11.glEnd();
            GL11.glDepthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GL11.glPopMatrix();
        }

        for (double il = 0; il < 0.02; il += 0.0006) {
            GL11.glPushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.enableDepth();
            GL11.glDepthMask(false);
            GL11.glLineWidth(0.1f);
            GL11.glBegin(1);
            final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks
                    - mc.getRenderManager().viewerPosX;
            double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks
                    - mc.getRenderManager().viewerPosY;
            final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks
                    - mc.getRenderManager().viewerPosZ;
            final double pix2 = Math.PI * 2D;

            float speed = 1200f;
            float baseHue = System.currentTimeMillis() % (int) speed;
            while (baseHue > speed) {
                baseHue -= speed;
            }
            baseHue /= speed;

            for (int i = 0; i <= 90; ++i) {
                float max = ((float) i) / 45F;
                float hue = max + baseHue;
                while (hue > 1) {
                    hue -= 1;
                }
                final float red = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 0.7F)).getRed();
                final float green = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 0.7F)).getGreen();
                final float blue = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 0.7F)).getBlue();
                GL11.glColor3f(red, green, blue);
                GL11.glVertex3d(x + distance * Math.cos(i * pix2 / 45.0), y + il,
                        z + distance * Math.sin(i * pix2 / 45.0));
            }

            GL11.glEnd();
            GL11.glDepthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GL11.glPopMatrix();
        }
    }

    private void drawCircle(Entity entity, float partialTicks, double rad) {
        glPushMatrix();
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);
        glDepthMask(false);
        glLineWidth(1.0f);
        glBegin(GL_LINE_STRIP);

        final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks
                - mc.getRenderManager().viewerPosX;
        final double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks
                - mc.getRenderManager().viewerPosY;
        final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks
                - mc.getRenderManager().viewerPosZ;
        final double pix2 = Math.PI * 2.0D;

        for (int i = 0; i <= 90; ++i) {
            glColor3f(185, 8, 8);
            glVertex3d(x + rad * Math.cos(i * pix2 / 45.0), y, z + rad * Math.sin(i * pix2 / 45.0));
        }

        glEnd();
        glDepthMask(true);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        glPopMatrix();
    }
}
