package me.bratwurst.news.picture.pc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMouse;
import me.pseey.event.events.EventUpdate;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmorStand;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class EZ_PictureHologramLoader {
    public int pictures;
    private String[] lines;
    private final Color[] colors = new Color[]{new Color(0, 0, 0), new Color(0, 0, 170), new Color(0, 170, 0), new Color(0, 170, 170), new Color(170, 0, 0), new Color(170, 0, 170), new Color(255, 170, 0), new Color(170, 170, 170), new Color(85, 85, 85), new Color(85, 85, 255), new Color(85, 255, 85), new Color(85, 255, 255), new Color(255, 85, 85), new Color(255, 85, 255), new Color(255, 255, 85), new Color(255, 255, 255)};
    private static final char TRANSPARENT_CHAR = ' ';

    public EZ_PictureHologramLoader(BufferedImage image, int height, char imgChar) {
        this.pictures = height - 1;
        EZ_PictureHologramColor[][] chatColors = this.toChatColorArray(image, height);
        this.lines = this.toImgMessage(chatColors, imgChar);
    }

    public EZ_PictureHologramLoader(EZ_PictureHologramColor[][] chatColors, char imgChar) {
        this.lines = this.toImgMessage(chatColors, imgChar);
    }

    private EZ_PictureHologramColor[][] toChatColorArray(BufferedImage image, int height) {
        double ratio = image.getHeight() / image.getWidth();
        int width = (int)((double)height / ratio);
        if (width > 10) {
            width = 10;
        }
        BufferedImage resized = this.resizeImage(image, (int)((double)height / ratio), height);
        EZ_PictureHologramColor[][] chatImg = new EZ_PictureHologramColor[resized.getWidth()][resized.getHeight()];
        for (int x = 0; x < resized.getWidth(); ++x) {
            for (int y = 0; y < resized.getHeight(); ++y) {
                EZ_PictureHologramColor closest;
                int rgb = resized.getRGB(x, y);
                chatImg[x][y] = closest = this.getClosestChatColor(new Color(rgb, true));
            }
        }
        return chatImg;
    }

    private String[] toImgMessage(EZ_PictureHologramColor[][] colors, char imgchar) {
        String[] lines2 = new String[colors[0].length];
        for (int y = 0; y < colors[0].length; y++) {
            String line = "";
            for (int x = 0; x < colors.length; x++) {
                EZ_PictureHologramColor color;
                line = String.valueOf(line) + ((color = colors[x][y]) != null ? String.valueOf(colors[x][y].toString()) + imgchar : Character.valueOf(' '));
            }
            lines2[y] = String.valueOf(line) + (Object)((Object)EZ_PictureHologramColor.RESET);
        }
        return lines2;
    }

    private BufferedImage resizeImage(BufferedImage src, int width, int height) {
        int finalw = width;
        int finalh = height;
        double factor = 1.0;
        if (src.getWidth() > src.getHeight()) {
            factor = (double)src.getHeight() / (double)src.getWidth();
            finalh = (int)((double)finalw * factor);
        } else {
            factor = (double)src.getWidth() / (double)src.getHeight();
            finalw = (int)((double)finalh * factor);
        }
        BufferedImage resizedImg = new BufferedImage(finalw, finalh, 3);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(src, 0, 0, finalw, finalh, null);
        g2.dispose();
        return resizedImg;
    }

    private double getDistance(Color c1, Color c2) {
        double rmean = (double)(c1.getRed() + c2.getRed()) / 2.0;
        double r = c1.getRed() - c2.getRed();
        double g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        double weightR = 2.0 + rmean / 256.0;
        double weightG = 4.0;
        double weightB = 2.0 + (255.0 - rmean) / 256.0;
        return weightR * r * r + weightG * g * g + weightB * (double)b * (double)b;
    }

    private boolean areIdentical(Color c1, Color c2) {
        return Math.abs(c1.getRed() - c2.getRed()) <= 5 && Math.abs(c1.getGreen() - c2.getGreen()) <= 5 && Math.abs(c1.getBlue() - c2.getBlue()) <= 5;
    }

    private EZ_PictureHologramColor getClosestChatColor(Color color) {
        int i;
        if (color.getAlpha() < 128) {
            return null;
        }
        int index2 = 0;
        double best = -1.0;
        for (i = 0; i < this.colors.length; i++) {
            if (!this.areIdentical(this.colors[i], color)) continue;
            return EZ_PictureHologramColor.values()[i];
        }
        for (i = 0; i < this.colors.length; i++) {
            double distance = this.getDistance(color, this.colors[i]);
            if (!(distance < best) && best != -1.0) continue;
            best = distance;
            index2 = i;
        }
        return EZ_PictureHologramColor.values()[index2];
    }

    private String center(String s2, int length) {
        if (s2.length() > length) {
            return s2.substring(0, length);
        }
        if (s2.length() == length) {
            return s2;
        }
        int leftPadding = (length - s2.length()) / 2;
        StringBuilder leftBuilder = new StringBuilder();
        for (int i = 0; i < leftPadding; i++) {
            leftBuilder.append(" ");
        }
        return String.valueOf(leftBuilder.toString()) + s2;
    }

    public String[] getLines() {
        return this.lines;
    }

    public ItemStack[] getArmorStands(double x, double y, double z) {

        ItemStack[] itemStacks = new ItemStack[this.getLines().length];
        List<String> lines2 = Arrays.asList(this.getLines());
        Collections.reverse(lines2);
        for (int i = 0; i < getLines().length; i++) {
            ItemStack item = new ItemStack(Items.armor_stand);
            NBTTagCompound base = new NBTTagCompound();
            NBTTagCompound entityTag = new NBTTagCompound();
            NBTTagList pos = new NBTTagList();
            pos.appendTag(new NBTTagDouble(x));
            pos.appendTag(new NBTTagDouble(y + 0.2 * (double) i));
            pos.appendTag(new NBTTagDouble(z));
            entityTag.setTag("Pos", pos);

            entityTag.setString("CustomName", getLines()[i]);
            entityTag.setInteger("CustomNameVisible", 1);
            entityTag.setInteger("Invisible", 1);
            entityTag.setInteger("NoGravity", 1);
            base.setTag("EntityTag", entityTag);
            item.setTagCompound(base);


            item.setStackDisplayName("Hologram: #" + i);

            itemStacks[i] = item;

            System.out.println(i);
        }

        return itemStacks;
    }
    @EventTarget
    public void onUpdate(EventMouse e){
        System.out.println("TEst4");
            ItemStack i = Minecraft.getMinecraft().thePlayer.getItemInUse();

                EZ_PictureHologram.client.giveNextArmorstand();
                System.out.println("TEst4");

    }
}