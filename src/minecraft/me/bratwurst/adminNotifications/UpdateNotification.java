package me.bratwurst.adminNotifications;

import me.bratwurst.utils.NotificationType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class UpdateNotification {
    private UpdateNotificationType type;
    private String title;
    private String message;
    private long start;

    private long fadedIn;
    private long fadedOut;
    private long end;

    public UpdateNotification(UpdateNotificationType type, String title, String message, int length) {
        this.type = type;
        this.title = title;
        this.message = message;

        fadedIn = 2000 * length;
        fadedOut = fadedIn + 5000 * length;
        end = fadedOut + fadedIn;
    }
    public void show(){
        start = System.currentTimeMillis();
    }

    public boolean isShow(){
        return getTime() <= end;
    }

    private long getTime(){
        return System.currentTimeMillis() - start;
    }


    public void render(){
        double offset = 0;
        int width = 120;
        int height = 30;
        long time = getTime();

        if(time < fadedIn){
            offset = Math.tanh(time / (double) (fadedIn) * 3.0) * width;
        }else if( time > fadedOut){
            offset = (Math.tanh(3.0 - (time - fadedOut) / (double) (end - fadedOut) * 3.0) * width);
        }else {
            offset = width;
        }

        Color color = new Color(0,0,0,220);
        Color color1;

        if(type == UpdateNotificationType.UPDATE) {
            color1 = new Color(0, 245, 210);
        }else {
            color1 = new Color(255, 0, 0);
        }
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;

        drawRect(GuiScreen.width - offset, GuiScreen.height - 150 - height, GuiScreen.width, GuiScreen.height - 150, color.getRGB());
        GL11.glLineWidth(2.0F);
        drawRect(GL11.GL_LINE_LOOP ,GuiScreen.width - offset, GuiScreen.height - 150 - height, GuiScreen.width, GuiScreen.height - 150, color1.getRGB());

        fontRenderer.drawString(title, GuiScreen.width - offset + 6, GuiScreen.height - 148 - height, -1);
        fontRenderer.drawString(message, GuiScreen.width - offset + 6, GuiScreen.height - 165, -1);
    }
    public static void drawRect(double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, top, 0.0D).endVertex();
        worldrenderer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    public static void drawRect(int mode, double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(mode, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, top, 0.0D).endVertex();
        worldrenderer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

}

