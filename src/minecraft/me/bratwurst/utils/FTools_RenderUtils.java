package me.bratwurst.utils;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class FTools_RenderUtils{

    public static void drawRectBorder(int left, int top, int right, int bottom, int color, int size) {
        Gui.drawRect(left - size, top - size, left, bottom + size, color);
        Gui.drawRect(right, top - size, right + size, bottom + size, color);
        Gui.drawRect(left, top - size, right, top, color);
        Gui.drawRect(left, bottom, right, bottom + size, color);
    }

    public static void drawBorderedRect(int x, int y, int x1, int y1, int size, int borderC, int insideC) {
        Gui.drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
        Gui.drawRect(x + size - 1, y + size, x1, y, borderC);
        Gui.drawRect(x, y + 1, x + size, y1, borderC);
        Gui.drawRect(x1, y1, x1 - size, y + size, borderC);
        Gui.drawRect(x + 1, y1 - size, x1 - 1, y1, borderC);
    }

    public static long drawRainbowRectBorder(int left, int top, int right, int bottom, int size) {
        long topCol = FTools_RenderUtils.renderRainbowRect(left, top - size, right, top, 2.0, 10L, 0L, RainbowDirection.RIGHT);
        long downCol = FTools_RenderUtils.renderRainbowRect(left - size, top - size, left, bottom + size, 2.0, 10L, 0L, RainbowDirection.DOWN);
        FTools_RenderUtils.renderRainbowRect(right, top - size, right + size, bottom + size, 2.0, 10L, topCol, RainbowDirection.DOWN);
        FTools_RenderUtils.renderRainbowRect(left, bottom, right, bottom + size, 2.0, 10L, downCol, RainbowDirection.RIGHT);
        return topCol;
    }

    public static void entityESPBox(Entity entity, int mode) {
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glLineWidth((float)2.0f);
        GlStateManager.color(0.0f, 1.0f, 0.0f, 0.5f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        if (mode == 0) {
            GL11.glColor4d((double)(1.0f - Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) / 40.0f), (double)(Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) / 40.0f), (double)0.0, (double)0.5);
        } else if (mode == 1) {
            GlStateManager.color(0.0f, 0.0f, 1.0f, 0.5f);
        } else if (mode == 2) {
            GlStateManager.color(1.0f, 1.0f, 0.0f, 0.5f);
        } else if (mode == 3) {
            GlStateManager.color(1.0f, 0.0f, 0.0f, 0.5f);
        } else if (mode == 4) {
            GlStateManager.color(0.0f, 1.0f, 0.0f, 0.5f);
        }
        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + (entity.posX - Minecraft.getMinecraft().getRenderManager().renderPosX), entity.getEntityBoundingBox().minY - entity.posY + (entity.posY - Minecraft.getMinecraft().getRenderManager().renderPosY), entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + (entity.posZ - Minecraft.getMinecraft().getRenderManager().renderPosZ), entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + (entity.posX - Minecraft.getMinecraft().getRenderManager().renderPosX), entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + (entity.posY - Minecraft.getMinecraft().getRenderManager().renderPosY), entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + (entity.posZ - Minecraft.getMinecraft().getRenderManager().renderPosZ)));
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
    }

    public static long renderRainbowRect(int left, int top, int right, int bottom, double time, long difference, long delay, RainbowDirection rainbowDirection) {
        long endDelay = 0L;
        switch (rainbowDirection) {
            case RIGHT: {
                for (int i = 0; i < right - left; ++i) {
                    endDelay = delay + (long)i * -difference;
                    Gui.drawRect(left + i, top, right, bottom, FTools_Colors.getRainbow(endDelay, time).getRGB());
                }
                break;
            }
            case LEFT: {
                for (int i = 0; i < right - left; ++i) {
                    endDelay = delay + (long)i * difference;
                    Gui.drawRect(left + i, top, right, bottom, FTools_Colors.getRainbow(endDelay, time).getRGB());
                }
                break;
            }
            case DOWN: {
                for (int i = 0; i < bottom - top; ++i) {
                    endDelay = delay + (long)i * -difference;
                    Gui.drawRect(left, top + i, right, bottom, FTools_Colors.getRainbow(endDelay, time).getRGB());
                }
                break;
            }
            case UP: {
                for (int i = 0; i < bottom - top; ++i) {
                    endDelay = delay + (long)i * difference;
                    Gui.drawRect(left, top + i, right, bottom, FTools_Colors.getRainbow(endDelay, time).getRGB());
                }
                break;
            }
        }
        return endDelay;
    }

    public static long drawLine(int fromX, int fromY, int toX, int toY, int steps, long startOffset) {
        double count = 0.0;
        double distX = toX - fromX;
        double distY = toY - fromY;
        double dist = Math.sqrt(distX * distX + distY * distY);
        int i = 0;
        while ((double)i < dist) {
            count += 1.0;
            i += steps;
        }
        double var10000 = dist / count;
        long offset = 0L;
        int i2 = 0;
        while ((double)i2 < count) {
            offset = startOffset + (long)(i2 * 50000000);
            Color rgb = FTools_RenderUtils.rainbowColor(offset, 1.0f);
            GL11.glColor4d((double)((double)rgb.getRed() / 255.0), (double)((double)rgb.getGreen() / 255.0), (double)((double)rgb.getBlue() / 255.0), (double)((double)rgb.getAlpha() / 255.0));
            double x = (double)fromX + (double)i2 * (distX / count);
            double y = (double)fromY + (double)i2 * (distY / count);
            double x1 = (double)fromX + (double)(i2 + 1) * (distX / count);
            double y1 = (double)fromY + (double)(i2 + 1) * (distY / count);
            GL11.glLineWidth((float)3.0f);
            GL11.glDisable((int)2884);
            GL11.glDisable((int)3553);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glBegin((int)1);
            GL11.glVertex2d((double)x, (double)y);
            GL11.glVertex2d((double)x1, (double)y1);
            GL11.glEnd();
            GL11.glEnable((int)3553);
            ++i2;
        }
        return offset;
    }

    public static Color rainbowColor(long offset, float fade) {
        float huge = (float)(System.nanoTime() + offset) / 1.0E10f % 1.0f;
        long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(huge, 1.0f, 1.0f)), 16);
        Color c = new Color((int)color);
        return new Color((float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f, fade);
    }

    public static enum RainbowDirection {
        LEFT,
        UP,
        RIGHT,
        DOWN;

    }
}

