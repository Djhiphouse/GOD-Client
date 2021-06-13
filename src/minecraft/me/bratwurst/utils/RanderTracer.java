package me.bratwurst.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import org.lwjgl.opengl.GL11;

import java.awt.Color;

public class RanderTracer
{
    public static int rainbow(final int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbowState %= 360.0;
        return java.awt.Color.getHSBColor((float)(rainbowState / 360.0), 0.8f, 0.7f).getRGB();
    }







    public static void drawSolidEntityESP(final double x, final double y, final double z, final double width, final double height, final float red, final float green, final float blue, final float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);

        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawEntityESP(final double x, final double y, final double z, final double width, final double height, final float red, final float green, final float blue, final float alpha, final float lineRed, final float lineGreen, final float lineBlue, final float lineAlpha, final float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);

        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);

        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawTracerLine(final double x, final double y, final double z, final float red, final float green, final float blue, final float alpha, final float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(2);
        GL11.glVertex3d(0.0, 0.0 + Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0.0);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        fix();
    }

    public static void drawCircle(final int x, final int y, final double r, final int c) {
        final float f = (c >> 24 & 0xFF) / 255.0f;
        final float f2 = (c >> 16 & 0xFF) / 255.0f;
        final float f3 = (c >> 8 & 0xFF) / 255.0f;
        final float f4 = (c & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(2);
        for (int i = 0; i <= 360; ++i) {
            final double x2 = Math.sin(i * 3.141592653589793 / 180.0) * r;
            final double y2 = Math.cos(i * 3.141592653589793 / 180.0) * r;
            GL11.glVertex2d(x + x2, y + y2);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static void drawFilledCircle(final int x, final int y, final double r, final int c) {
        final float f = (c >> 24 & 0xFF) / 255.0f;
        final float f2 = (c >> 16 & 0xFF) / 255.0f;
        final float f3 = (c >> 8 & 0xFF) / 255.0f;
        final float f4 = (c & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(6);
        for (int i = 0; i <= 360; ++i) {
            final double x2 = Math.sin(i * 3.141592653589793 / 180.0) * r;
            final double y2 = Math.cos(i * 3.141592653589793 / 180.0) * r;
            GL11.glVertex2d(x + x2, y + y2);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static void dr(double i, double j, double k, double l, final int i1) {
        if (i < k) {
            final double j2 = i;
            i = k;
            k = j2;
        }
        if (j < l) {
            final double k2 = j;
            j = l;
            l = k2;
        }
        final float f = (i1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (i1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (i1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (i1 & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f2, f3, f4, f);

        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static void pre3D() {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        GL11.glDepthMask(false);
        GL11.glHint(3154, 4354);
    }

    public static void post3D() {
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void blockESPBox(final BlockPos blockPos, final boolean rainbow) {

        final double x = blockPos.getX() - RenderManager.renderPosX;
        final double y = blockPos.getY() - RenderManager.renderPosY;
        final double z = blockPos.getZ() - RenderManager.renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);

        GL11.glColor4d(0.0, 1.0, 0.0, 0.15000000596046448);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d(0.0, 0.0, 1.0, 0.5);

        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }

    public static void itemESP(final EntityItem e) {

        final double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * Minecraft.getMinecraft().timer.renderPartialTicks - RenderManager.renderPosX;
        final double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * Minecraft.getMinecraft().timer.renderPartialTicks - RenderManager.renderPosY;
        final double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * Minecraft.getMinecraft().timer.renderPartialTicks - RenderManager.renderPosZ;

    }

    public static void esp(final EntityPlayer e, final boolean rainbow) {
        final Minecraft mc = Minecraft.getMinecraft();
        fix();

        final double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
        final double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
        final double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
        GL11.glPushMatrix();
        GL11.glTranslated(x, y - 0.2, z);
        GL11.glScalef(0.03f, 0.03f, 0.03f);

        GlStateManager.disableDepth();
        if (rainbow) {
            Gui.drawRect(-20, -1, -26, 75, java.awt.Color.BLACK.getRGB());
            Gui.drawRect(-21, 0, -25, 74, rainbow(300));
            Gui.drawRect(20, -1, 26, 75, java.awt.Color.BLACK.getRGB());
            Gui.drawRect(21, 0, 25, 74, rainbow(300));
            Gui.drawRect(-20, -1, 21, 5, java.awt.Color.BLACK.getRGB());
            Gui.drawRect(-21, 0, 24, 4, rainbow(300));
            Gui.drawRect(-20, 70, 21, 75, java.awt.Color.BLACK.getRGB());
            Gui.drawRect(-21, 71, 25, 74, rainbow(300));
        }

        GlStateManager.enableDepth();
        GL11.glPopMatrix();
        fix();
    }

    public static void espBox(final EntityPlayer entity) {
        final Minecraft mc = Minecraft.getMinecraft();

        final double xPos = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
        final double yPos = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
        final double zPos = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
        Gui.drawRect(0, 0, 0, 0, 0);

        Gui.drawRect(0, 0, 0, 0, 0);
    }

    public static void renderNameTag(final EntityPlayer entity, final String tag, final double pX, double pY, final double pZ) {
        final FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        pY += 0.9;
        float distance = Minecraft.getMinecraft().thePlayer.getDistanceToEntity((Entity)entity) / 4.0f;
        if (distance < 1.1f) {
            distance = 1.6f;
        }
        float scale = (float)(distance * 2.5);
        scale /= 100.0f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)pX, (float)pY + 1.5f, (float)pZ);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);

        GL11.glScalef(-scale, -scale, scale);
        GLUtil.setGLCap(2896, false);
        GLUtil.setGLCap(2929, false);
        final int width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(tag) / 2;
        GLUtil.setGLCap(3042, true);
        Gui.drawRect(-width - 1, -1, width + 1, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT, 1275068416);
        fr.drawString(String.valueOf(tag) + " �8� �e" + Math.round(entity.getHealth() / 2.0f) + " �c\u2764 ", (float)(-width), 0.0f, 16777215, true);
        GLUtil.revertAllCaps();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }

    public static void drawBorderedRect(final float x, final float y, final float x2, final float y2, final float l1, final int col1, final int col2) {
        Gui.drawRect((int)x, (int)y, (int)x2, (int)y2, col2);
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }

    public static void fix() {
        Gui.drawRect(0, 0, 0, 0, -1);
    }
}
