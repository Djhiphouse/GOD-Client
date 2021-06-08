package me.bratwurst.module.modules.render;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMove;
import me.bratwurst.event.events.RenderEvent;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.BlickWinkel3D;
import me.bratwurst.utils.Location3D;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.*;


public class Tracer extends Module {


    public Tracer() {
        super("Tracer", Category.RENDER);
    }


    @EventTarget
    public void onPlayerUpdate(EventMove event) {
        System.out.println("onupdate");

        for (final Object o : this.mc.theWorld.loadedEntityList) {
            if (o instanceof Entity) {
                final Entity e = (Entity) o;
                if (!(e instanceof EntityPlayer) || e == this.mc.thePlayer) {
                    continue;
                }
                tracer(e, Color.cyan);

            }
        }

    }

    public void tracer(final Entity e, final Color color) {
        GlStateManager.pushMatrix();
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glColor4f((float) color.getRed(), (float) color.getGreen(), (float) color.getBlue(), (float) color.getAlpha());
        GL11.glLineWidth(1.0f);
        GL11.glBegin(2);
        GL11.glVertex3d(0.0, this.mc.thePlayer.getEyeHeight(), 0.0);
        GL11.glVertex3d(e.posX - this.mc.thePlayer.posX, e.posY - this.mc.thePlayer.posY, e.posZ - this.mc.thePlayer.posZ);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GlStateManager.popMatrix();
    }
}
