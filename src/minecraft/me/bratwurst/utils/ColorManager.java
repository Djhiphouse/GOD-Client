package me.bratwurst.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

import java.awt.Color;

public class ColorManager {

    private final static String COLOR_CODES = "0123456789abcdef";
    private static int[][] colors = null;

    public static void init() {
        if(colors != null)
            return;
        colors = new int[16][];
        java.awt.Color color;
        for(int i=0; i < COLOR_CODES.length(); i++) {
            color = new java.awt.Color(Minecraft.getMinecraft().fontRendererObj.getColorCode(COLOR_CODES.charAt(i)));
            colors[i] = new int[] {color.getRed(), color.getGreen(), color.getBlue()};
        }
    }

    public static String getColor(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        if(r == 0 && g == 0 && b == 0 && color.getAlpha() > 240)
            return "§";
        char closestMatch = 'f';
        int minMSE = Integer.MAX_VALUE;
        int mse;
        for(int i=0; i < colors.length; i++) {
            mse = computeMSE(colors[i][0], colors[i][1], colors[i][2], r, g, b);
            if (mse < minMSE) {
                minMSE = mse;
                closestMatch = COLOR_CODES.charAt(i);
            }
        }
        return "§" + closestMatch;
    }

    private static int computeMSE(int r, int g, int b, int pixR, int pixG, int pixB) {
        return (int) (((pixR - r) * (pixR - r) + (pixG - g) * (pixG - g) + (pixB - b)
                * (pixB - b)) / 3);
    }

}
