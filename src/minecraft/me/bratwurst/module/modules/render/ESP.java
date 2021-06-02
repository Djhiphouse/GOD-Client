package me.bratwurst.module.modules.render;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.Event3D;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ESP extends Module {
    public static Setting minCps,  Aqua, Gelb, Blau,thickness,NoRotate,LegitAutoBlock;
    public ESP() {

        super("ESP", Category.RENDER);
    }
    @Override
    public void setup() {
        Client.setmgr.rSetting(thickness = new Setting("thickness", this, 1, 1, 5, false));
        Client.setmgr.rSetting(Aqua = new Setting("Aqua", this, false));
        Client.setmgr.rSetting(Gelb = new Setting("Gelb", this, false));
        Client.setmgr.rSetting(Blau = new Setting("Blau", this, false));
    }
    @EventTarget
    public void Event3D(Event3D event3D) {

        for (final EntityPlayer entity : this.mc.theWorld.playerEntities) {
            if (entity != mc.thePlayer) {

              float Breite = thickness.getValInt();
                GL11.glPushMatrix();
                GL11.glEnable(3042);
                GL11.glDisable(2929);
                GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                GlStateManager.enableBlend();
                GL11.glBlendFunc(770, 771);
                GL11.glDisable(3553);
                final float partialTicks = this.mc.timer.renderPartialTicks;
                final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks - this.mc.getRenderManager().renderPosX;
                final double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks - this.mc.getRenderManager().renderPosY;
                final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks - this.mc.getRenderManager().renderPosZ;
                final float DISTANCE = this.mc.thePlayer.getDistanceToEntity(entity);
                final float DISTANCE_SCALE = Math.min(DISTANCE * 0.15f, 2.5f);
                float SCALE = 0.035f;
                SCALE /= 2.0f;
                GlStateManager.translate((float) x, (float) y + entity.height + 0.5f - (entity.isChild() ? (entity.height / 2.0f) : 0.0f), (float) z);
                GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                GL11.glScalef(-SCALE, -SCALE, -SCALE);
                final Tessellator tesselator = Tessellator.getInstance();
                final WorldRenderer worldRenderer = tesselator.getWorldRenderer();
                Color color = new Color(0, 0, 0);
                if (entity.hurtTime > 0) {
                    color = new Color(Color.RED.getRGB());
                }else if (Aqua.getValBoolean()) {
                    color = new Color(Color.CYAN.getRGB());
                }else if (Gelb.getValBoolean()) {
                    color = new Color(Color.YELLOW.getRGB());
                }else if (Blau.getValBoolean()) {
                    color = new Color(Color.BLUE.getRGB());
                }else {
                    color = new Color(Color.BLUE.getRGB());
                }
                final Color gray = new Color(0, 0, 0);
                final double thickness = Breite + DISTANCE * 0.08f;
                final double xLeft = -30.0;
                final double xRight = 30.0;
                final double yUp = 20.0;
                final double yDown = 130.0;
                final double size = 10.0;
                this.drawVerticalLine(xLeft + size / 2.0 + 1.0, yUp + 1.0, size / 2.0, thickness, gray);
                this.drawHorizontalLine(xLeft + 1.0, yUp + size + 1.0, size, thickness, gray);
                this.drawVerticalLine(xLeft + size / 2.0, yUp, size / 2.0, thickness, color);
                this.drawHorizontalLine(xLeft, yUp + size, size, thickness, color);
                this.drawVerticalLine(xRight - size / 2.0 + 1.0, yUp + 1.0, size / 2.0, thickness, gray);
                this.drawHorizontalLine(xRight + 1.0, yUp + size + 1.0, size, thickness, gray);
                this.drawVerticalLine(xRight - size / 2.0, yUp, size / 2.0, thickness, color);
                this.drawHorizontalLine(xRight, yUp + size, size, thickness, color);
                this.drawVerticalLine(xLeft + size / 2.0 + 1.0, yDown + 1.0, size / 2.0, thickness, gray);
                this.drawHorizontalLine(xLeft + 1.0, yDown + 1.0 - size, size, thickness, gray);
                this.drawVerticalLine(xLeft + size / 2.0, yDown, size / 2.0, thickness, color);
                this.drawHorizontalLine(xLeft, yDown - size, size, thickness, color);
                this.drawVerticalLine(xRight - size / 2.0 + 1.0, yDown + 1.0, size / 2.0, thickness, gray);
                this.drawHorizontalLine(xRight + 1.0, yDown - size + 1.0, size, thickness, gray);
                this.drawVerticalLine(xRight - size / 2.0, yDown, size / 2.0, thickness, color);
                this.drawHorizontalLine(xRight, yDown - size, size, thickness, color);
                GL11.glEnable(3553);
                GL11.glEnable(2929);
                GlStateManager.disableBlend();
                GL11.glDisable(3042);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glNormal3f(1.0f, 1.0f, 1.0f);
                GL11.glPopMatrix();
            }
        }
    }

    private void drawVerticalLine(final double xPos, final double yPos, final double xSize, final double thickness, final Color color) {
        final Tessellator tesselator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tesselator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(xPos - xSize, yPos - thickness / 2.0, 0.0).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        worldRenderer.pos(xPos - xSize, yPos + thickness / 2.0, 0.0).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        worldRenderer.pos(xPos + xSize, yPos + thickness / 2.0, 0.0).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        worldRenderer.pos(xPos + xSize, yPos - thickness / 2.0, 0.0).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        tesselator.draw();
    }

    private void drawHorizontalLine(final double xPos, final double yPos, final double ySize, final double thickness, final Color color) {
        final Tessellator tesselator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tesselator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(xPos - thickness / 2.0, yPos - ySize, 0.0).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        worldRenderer.pos(xPos - thickness / 2.0, yPos + ySize, 0.0).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        worldRenderer.pos(xPos + thickness / 2.0, yPos + ySize, 0.0).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        worldRenderer.pos(xPos + thickness / 2.0, yPos - ySize, 0.0).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        tesselator.draw();
    }

}


