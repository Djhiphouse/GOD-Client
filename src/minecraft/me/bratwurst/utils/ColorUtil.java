package me.bratwurst.utils;

import java.awt.Color;
import net.minecraft.client.Minecraft;

public class ColorUtil {
    public static Color RainbowEffect() {
        Color color = new Color(1f,1f,1f);
        return color;
    }

    public static void drawChromaString(String text, int x, int y, boolean shadow) {
        Minecraft mc = Minecraft.getMinecraft();
        int x2 = x;
        byte b;
        int i;
        char[] arrayOfChar;
        for (i = (arrayOfChar = text.toCharArray()).length, b = 0; b < i; ) {
            char textChar = arrayOfChar[b];
            long l = System.currentTimeMillis() - (x2 * 10 - y * 10);
            int j = Color.HSBtoRGB((float)(l % 2000L) / 2000.0F, 0.8F, 0.8F);
            String string = String.valueOf(textChar);
            mc.fontRendererObj.drawString(string, x2, y, j, shadow);
            x2 += mc.fontRendererObj.getCharWidth(textChar);
            b++;
        }
    }
}

