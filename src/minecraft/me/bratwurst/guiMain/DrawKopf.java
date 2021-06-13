package me.bratwurst.guiMain;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;


public class DrawKopf {
    public static final Map<String, ResourceLocation> playerHeads = new HashMap<>();
    public static void loadPlayerHead(String name) {
        ResourceLocation resourcelocation = new ResourceLocation("heads/" + name);
        ThreadDownloadImageData t = new ThreadDownloadImageData(null, "https://minotar.net/skin/" + name, DefaultPlayerSkin.getDefaultSkin(UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8))), (IImageBuffer)new ImageBufferDownload());
        Minecraft.getMinecraft().getTextureManager().loadTexture(resourcelocation, (ITextureObject)t);
        playerHeads.put(name, resourcelocation);
    }

    public static void drawTexturedModalRect(double xCoord, double yCoord, double minU, double minV, double maxU, double maxV) {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(xCoord + 0.0D, yCoord + (float)maxV, 0.0D).tex(((float)(minU + 0.0D) * f), ((float)(minV + maxV) * f1)).endVertex();
        worldrenderer.pos(xCoord + (float)maxU, yCoord + (float)maxV, 0.0D).tex(((float)(minU + maxU) * f), ((float)(minV + maxV) * f1)).endVertex();
        worldrenderer.pos(xCoord + (float)maxU, yCoord + 0.0D, 0.0D).tex(((float)(minU + maxU) * f), ((float)(minV + 0.0D) * f1)).endVertex();
        worldrenderer.pos(xCoord + 0.0D, yCoord + 0.0D, 0.0D).tex(((float)(minU + 0.0D) * f), ((float)(minV + 0.0D) * f1)).endVertex();
        tessellator.draw();
    }
}
