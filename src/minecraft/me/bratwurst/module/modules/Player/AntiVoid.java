package me.bratwurst.module.modules.Player;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;

public class AntiVoid extends Module {

    public Setting mode;
    private boolean motion;
    private BlockPos lastSafePos;
    public AntiVoid() {
        super("AntiVoid", Category.PLAYER);
        ArrayList<String> options2 = new ArrayList<String>();
        options2.add("Vanilla");
        options2.add("Hypixel");
        options2.add("Watchdog");
        options2.add("Redesky");
        options2.add("Latingamers");
        Client.setmgr.rSetting(mode = new Setting("Antivoid mode", this, "Hypixel", options2));
    }

    @EventTarget
    public void onSend(ProcessPacketEvent event) {
        if(this.mode.getValString().equalsIgnoreCase("Watchdog")) {
            this.setDisplayname("Hypixelold");
            if(!isBlockUnder()) {
                if (event.getPacket() instanceof C03PacketPlayer) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
       this.setDisplayname("Vanilla");
        if(this.mode.getValString().equalsIgnoreCase("Vanilla")) {
            if (mc.thePlayer.onGround) {
                lastSafePos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
            }
            if(!isBlockUnder()) {
                if(mc.thePlayer.fallDistance > 2.9f) {
                    if (motion) {

                        mc.thePlayer.setPosition(lastSafePos.getX(), lastSafePos.getY(), lastSafePos.getZ());
                        motion = false;
                    } else {
                        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(lastSafePos.getX(), lastSafePos.getY(), lastSafePos.getZ(), true));
                        mc.thePlayer.fallDistance = 0;
                    }
                } else {
                    motion = true;
                }
            } else {
                motion = true;
            }
        }
        if(this.mode.getValString().equalsIgnoreCase("Watchdog")) {
            this.setDisplayname("Hypixel2");
            if (mc.thePlayer.onGround) {
                lastSafePos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
            }
            if(!isBlockUnder()) {
                if(mc.thePlayer.fallDistance > 5.9f) {
                    if (motion) {

                        mc.thePlayer.setPosition(lastSafePos.getX(), lastSafePos.getY(), lastSafePos.getZ());
                        motion = false;
                    } else {
                        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(lastSafePos.getX(), lastSafePos.getY(), lastSafePos.getZ(), true));
                        mc.thePlayer.fallDistance = 0;
                    }
                } else {
                    motion = true;
                }
            } else {
                motion = true;
            }
        }
        if(this.mode.getValString().equalsIgnoreCase("Latingamers")) {
            this.setDisplayname(EnumChatFormatting.RED + "Latingamers");
            if (mc.thePlayer.fallDistance > 3.0f && !isBlockUnder()) {
                if(!mc.thePlayer.onGround) {
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX + 5.5f, mc.thePlayer.posY + mc.thePlayer.posY, mc.thePlayer.posZ + 5.5f, true));
                }
            }
        }
        if(event.isPre()) {
            if(this.mode.getValString().equalsIgnoreCase("Hypixel")) {
                if (mc.thePlayer.fallDistance > 3.0F && !isBlockUnder() && mc.thePlayer.ticksExisted % 3 == 0 && !Client.getInstance().getModuleManager().getModuleByName("Flight").isEnabled())
                    event.setY(event.getY() + 8.0D);

            }else if(this.mode.getValString().equalsIgnoreCase(EnumChatFormatting.RED +"Redesky")) {

            }
        }

    }
    public static boolean isBlockUnder() {
        if(Minecraft.getMinecraft().thePlayer.posY < 0)
            return false;
        for(int off = 0; off < (int)Minecraft.getMinecraft().thePlayer.posY+2; off += 2){
            AxisAlignedBB bb = Minecraft.getMinecraft().thePlayer.getEntityBoundingBox().offset(0, -off, 0);
            if(!Minecraft.getMinecraft().theWorld.getCollidingBoundingBoxes(Minecraft.getMinecraft().thePlayer, bb).isEmpty()){
                return true;
            }
        }
        return false;
    }

}
