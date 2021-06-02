package me.bratwurst.module.modules.movement;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.opengl.GL11;

public class HighJump extends Module {
    public HighJump() {
        super("HighJump", Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (HighJump.mc.gameSettings.keyBindJump.pressed) {
            return;
        }
        final Minecraft mc = HighJump.mc;
        if (mc.thePlayer.hurtTime != 0) {
            final Minecraft mc2 = HighJump.mc;
            if (mc.thePlayer.onGround) {
                final Minecraft mc3 = HighJump.mc;
                mc.thePlayer.motionY = 1.1;
                for (int i = 0; i < 70; ++i) {
                    Minecraft.getMinecraft();
                    final NetHandlerPlayClient sendQueue = mc.thePlayer.sendQueue;
                    Minecraft.getMinecraft();
                    final double posX = mc.thePlayer.posX;
                    Minecraft.getMinecraft();
                    final double posY = mc.thePlayer.posY + 0.06;
                    Minecraft.getMinecraft();
                    sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(posX, posY, mc.thePlayer.posZ, false));
                    Minecraft.getMinecraft();
                    final NetHandlerPlayClient sendQueue2 = mc.thePlayer.sendQueue;
                    Minecraft.getMinecraft();
                    final double posX2 = mc.thePlayer.posX;
                    Minecraft.getMinecraft();
                    final double posY2 = mc.thePlayer.posY;
                    Minecraft.getMinecraft();
                    sendQueue2.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(posX2, posY2, mc.thePlayer.posZ, false));
                }
                Minecraft.getMinecraft();
                final NetHandlerPlayClient sendQueue3 = mc.thePlayer.sendQueue;
                Minecraft.getMinecraft();
                final double posX3 = mc.thePlayer.posX;
                Minecraft.getMinecraft();
                final double posY3 = mc.thePlayer.posY + 0.1;
                Minecraft.getMinecraft();
                sendQueue3.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(posX3, posY3, mc.thePlayer.posZ, false));
            }
        }
    }
}