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

public class AdminNotification {
    private AdminNotificationType type;
    private String title;
    private String message;
    private long start;

    private long fadedIn;
    private long fadedOut;
    private long end;

    public AdminNotification(AdminNotificationType type, String title, String message, int length) {
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



        Color color = new Color(76, 74, 74,220);
        Color color1 = new Color(255,0,0,220);
        Color color2 = new Color(0, 255, 196,220);
        Color color3 = new Color(255, 182, 117, 255);

        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        //Der AdminKasten
        drawRect(GuiScreen.width / 3, 50,GuiScreen.width / 4 + GuiScreen.width / 3, 100, color.getRGB());
        //Der AdminKastenStrich Oben
        drawRect(GuiScreen.width / 3, 50, GuiScreen.width / 4 + GuiScreen.width / 3, 47, color1.getRGB());
        //Der AdminKastenStrich Unten
        drawRect(GuiScreen.width / 3, 97, GuiScreen.width / 4 + GuiScreen.width / 3, 100, color1.getRGB());
        //Der AdminKastenStrich Links
        drawRect(GuiScreen.width / 3, 50, GuiScreen.width / 3 + 2, 100, color1.getRGB());
        //Der AdminKastenStrich Rechts
        drawRect(GuiScreen.width / 4 + GuiScreen.width / 3, 50, GuiScreen.width / 4 + GuiScreen.width / 3 - 2, 100, color1.getRGB());


        fontRenderer.drawString(EnumChatFormatting.UNDERLINE + title, GuiScreen.width / 3 + 40, 57, color2.getRGB());
        fontRenderer.drawString(message, GuiScreen.width / 3 + 20, 74, color3.getRGB());
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


}
