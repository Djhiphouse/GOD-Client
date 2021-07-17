package me.bratwurst.utils;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

import java.awt.Color;

public class PrizonRenderUtils {

    public static void renderOne()
    {
        GL11.glPushAttrib(1048575);
        GL11.glDisable(3008);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(3.0F);
        GL11.glEnable(2848);
        GL11.glEnable(2960);
        GL11.glClear(1024);
        GL11.glClearStencil(15);
        GL11.glStencilFunc(512, 1, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1028, 6913);
    }
    public static void drawOutlinedBoundingBox(AxisAlignedBB aabb) {
        WorldRenderer worldRenderer = Tessellator.instance.getWorldRenderer();
        Tessellator tessellator = Tessellator.instance;
        worldRenderer.startDrawing(3);
        worldRenderer.addVertex(aabb.minX, aabb.minY, aabb.minZ);
        worldRenderer.addVertex(aabb.maxX, aabb.minY, aabb.minZ);
        worldRenderer.addVertex(aabb.maxX, aabb.minY, aabb.maxZ);
        worldRenderer.addVertex(aabb.minX, aabb.minY, aabb.maxZ);
        worldRenderer.addVertex(aabb.minX, aabb.minY, aabb.minZ);
        tessellator.draw();
        worldRenderer.startDrawing(3);
        worldRenderer.addVertex(aabb.minX, aabb.maxY, aabb.minZ);
        worldRenderer.addVertex(aabb.maxX, aabb.maxY, aabb.minZ);
        worldRenderer.addVertex(aabb.maxX, aabb.maxY, aabb.maxZ);
        worldRenderer.addVertex(aabb.minX, aabb.maxY, aabb.maxZ);
        worldRenderer.addVertex(aabb.minX, aabb.maxY, aabb.minZ);
        tessellator.draw();
        worldRenderer.startDrawing(1);
        worldRenderer.addVertex(aabb.minX, aabb.minY, aabb.minZ);
        worldRenderer.addVertex(aabb.minX, aabb.maxY, aabb.minZ);
        worldRenderer.addVertex(aabb.maxX, aabb.minY, aabb.minZ);
        worldRenderer.addVertex(aabb.maxX, aabb.maxY, aabb.minZ);
        worldRenderer.addVertex(aabb.maxX, aabb.minY, aabb.maxZ);
        worldRenderer.addVertex(aabb.maxX, aabb.maxY, aabb.maxZ);
        worldRenderer.addVertex(aabb.minX, aabb.minY, aabb.maxZ);
        worldRenderer.addVertex(aabb.minX, aabb.maxY, aabb.maxZ);
        tessellator.draw();
    }

}