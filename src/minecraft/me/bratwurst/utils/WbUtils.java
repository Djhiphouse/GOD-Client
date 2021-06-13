package me.bratwurst.utils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class WbUtils {
    public static void openWebsite(URI uri) {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void drawStringShadow(double Scale, String text, float x, float y, int color) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(Scale, Scale, Scale);
        (Minecraft.getMinecraft()).fontRendererObj.drawStringWithShadow(text, x, y, color);
        GlStateManager.popMatrix();
    }

    public static void drawString(double Scale, String text, float x, float y, int color) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(Scale, Scale, Scale);
        (Minecraft.getMinecraft()).fontRendererObj.drawString(text, x, y, color, false);
        GlStateManager.popMatrix();
    }
}
