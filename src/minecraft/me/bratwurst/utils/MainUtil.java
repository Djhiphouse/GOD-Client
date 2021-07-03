package me.bratwurst.utils;

import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.potion.Potion;
import net.minecraft.util.*;
import net.minecraft.world.WorldSettings;

import java.awt.*;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

public class MainUtil extends Utils {
    protected static Minecraft mc = Minecraft.getMinecraft();
    private static final Potion[] blockedEffects;
    public EntityPlayer p;

    static {
        blockedEffects = new Potion[]{Potion.hunger, Potion.moveSlowdown, Potion.digSlowdown, Potion.harm, Potion.confusion, Potion.blindness, Potion.weakness, Potion.wither, Potion.poison};
    }

    public static void sendPacketSilent(Packet packet) {
        mc.getNetHandler().getNetworkManager().sendPacket(packet);
    }

    public static void sendPacket(Packet packet) {
        mc.thePlayer.sendQueue.addToSendQueue(packet);
    }
    public MainUtil() {
        this.p = mc.thePlayer;
    }


    public  static void Kopieren(String s) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(s), (ClipboardOwner) null);
    }


    public static void setpos(double x, double y, double z, boolean packet, boolean groundpacket) {
        if (packet == true)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX + x,mc.thePlayer.posY + y,mc.thePlayer.posZ+z,groundpacket));

        if (packet == false)
            mc.thePlayer.setPosition(x,y,z);
    }




    public static void setMoveSpeed(int Speed, boolean bodencheck,boolean Bleibamboden) {
        if (bodencheck == true || Bleibamboden == true) {
            if (!mc.thePlayer.onGround) {
                mc.thePlayer.motionY =- 0.7;
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
            }
            if (mc.thePlayer.onGround) {

                mc.timer.timerSpeed = Speed;
            } else {
                if (bodencheck == false) {
                    mc.timer.timerSpeed = Speed;
                }
            }


        }else {
            mc.timer.timerSpeed = Speed;
        }


    }

    public static void ChatPacket(String Nachricht, int delay, boolean Time) {
        if (Time == true) {
            if (TimeHelper.hasReached(delay)) {
                Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(Nachricht));

                TimeHelper.reset();

            }
        } else {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(Nachricht));
        }

    }
    public void positionupdate(double x,double y,double z,boolean packet,boolean groundpacket) {
        if (packet == true)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX + x,mc.thePlayer.posY + y,mc.thePlayer.posZ+z,groundpacket));

        if (packet == false)
            mc.thePlayer.setPositionAndUpdate(x,y,z);
    }
    public static void SetFakegm(int gamemode) {
        if (gamemode == 0) {
            Minecraft.getMinecraft().thePlayer.setGameType(WorldSettings.GameType.SURVIVAL);
        } else if (gamemode == 1) {
            Minecraft.getMinecraft().thePlayer.setGameType(WorldSettings.GameType.CREATIVE);
        } else if (gamemode == 2) {
            Minecraft.getMinecraft().thePlayer.setGameType(WorldSettings.GameType.ADVENTURE);
        } else if (gamemode == 3) {
            Minecraft.getMinecraft().thePlayer.setGameType(WorldSettings.GameType.SPECTATOR);
        }


    }

    public static void SendClientMesage(String Nachricht) {
        String msg = Nachricht.toString();
        Minecraft.getMinecraft().thePlayer.addChatComponentMessage((IChatComponent) new ChatComponentText(msg));
    }

    public static void Jump(boolean setJump, boolean Sprungcheck) {

        if (Sprungcheck == true) {
            if (Minecraft.getMinecraft().thePlayer.onGround) {
                if (setJump == true) {
                    Minecraft.getMinecraft().thePlayer.jump();
                }

            }
        } else {

        }
    }

}
