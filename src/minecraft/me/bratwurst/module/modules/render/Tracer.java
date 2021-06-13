package me.bratwurst.module.modules.render;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMove;
import me.bratwurst.event.events.RenderEvent;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.awt.Color;
import java.util.*;


public class Tracer extends Module {


    public Tracer() {
        super("Tracer", Category.RENDER);
    }


    @EventTarget
    public void onPlayerUpdate(EventMove event) {
        System.out.println("onupdate");

        for (Entity e : this.mc.theWorld.loadedEntityList) {
            if (e instanceof EntityPlayer) {

                if (e instanceof EntityPlayer && e != mc.thePlayer) {
                   render(e);
                }


            }
        }

    }
    public void render(Entity entity) {
        final Color color = new Color(1);
        final double xPos = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * this.mc.timer.renderPartialTicks - RenderManager.renderPosX;
        final double yPos = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * this.mc.timer.renderPartialTicks - RenderManager.renderPosY;
        final double zPos = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * this.mc.timer.renderPartialTicks - RenderManager.renderPosZ;
        RanderTracer.drawTracerLine(xPos, yPos, zPos, (float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.45f,  1);
    }

}
