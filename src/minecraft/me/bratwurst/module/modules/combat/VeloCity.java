package me.bratwurst.module.modules.combat;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

import me.bratwurst.utils.MainUtil;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;

public class VeloCity extends Module {
    public Setting NCPPush, OldAAC, Intave, Spartan,Reverse;
    public static Setting mode1;

    public VeloCity() {
        super("Velocity", Category.COMBAT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Intave");
        options.add("Oldaac");
        options.add("Revesre");
        options.add("Jump");
        options.add("Hypixel");
        options.add("Mineplex");
        options.add("0knock");
        options.add("Dev");
        options.add("legit");
        options.add("Packet");

        Client.setmgr.rSetting(mode1 = new Setting("Velocity Mode", this, "Hypixel", options));
    }

    @Override
    public void setup() {
        Client.setmgr.rSetting(NCPPush = new Setting("NCPPush", this, false));

    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (mode1.getValString().equalsIgnoreCase("Oldaac")) {
            Oldaac();
        } else if (mode1.getValString().equalsIgnoreCase("Intave")) {
            Intave();
        } else if (mode1.getValString().equalsIgnoreCase("Revesre")) {
            Revesre();

        } else if (mode1.getValString().equalsIgnoreCase("Jump")) {
            Jump();
        }else if (mode1.getValString().equalsIgnoreCase("Hypixel")) {
            Hypixel();
        }else if (mode1.getValString().equalsIgnoreCase("Mineplex")) {
            Mineplex();
        }else if (mode1.getValString().equalsIgnoreCase("0knock")) {
            nullknock();
        }else if (mode1.getValString().equalsIgnoreCase("Dev")) {
            Dev();
        }else if (mode1.getValString().equalsIgnoreCase("legit")) {
            legit();

        }
    }

    public void Oldaac () {

        knock = false;
        if (mc.thePlayer.hurtTime == 9) {
            mc.thePlayer.motionX *= 0.5D;
            mc.thePlayer.motionZ *= 0.5D;
        }
        if (mc.thePlayer.hurtTime == 8) {
            mc.thePlayer.motionX *= 0.4D;
            mc.thePlayer.motionZ *= 0.4D;
        }
        if (mc.thePlayer.hurtTime == 7) {
            mc.thePlayer.motionX *= 0.7D;
            mc.thePlayer.motionZ *= 0.7D;
        }
        if (mc.thePlayer.hurtTime == 6) {
            mc.thePlayer.motionX *= 0.3D;
            mc.thePlayer.motionZ *= 0.3D;
        }
        if (mc.thePlayer.hurtTime == 5) {
            mc.thePlayer.motionX *= 0.1D;
            mc.thePlayer.motionZ *= 0.1D;
        }
    }
    public void Intave() {
        knock = false;
        if (mc.thePlayer.hurtTime == 9) {
            if (mc.thePlayer.onGround) {
                mc.thePlayer.capabilities.allowFlying = true;
                double velocity = mc.thePlayer.rotationYawHead;
                velocity = Math.toRadians(velocity);
                double motionX = Math.sin(velocity) * 0.053D;
                double motionZ = Math.sin(velocity) * 0.039D;
                double motionY = -Math.sin(velocity) * 0.1D;
                motionX = mc.thePlayer.motionX;
                motionZ = mc.thePlayer.motionZ;
            } else {
                mc.thePlayer.motionX = 0.0D;
                mc.thePlayer.motionY = 0.0D;
                mc.thePlayer.motionZ = 0.0D;
                mc.timer.timerSpeed = 1.0F;
            }
        } else if (mc.thePlayer.hurtTime == 8) {
            if (mc.thePlayer.onGround) {
                double motionX = 0.048D;
                double motionZ = 0.036D;
                mc.thePlayer.motionX = -motionX * 0.11D;
                mc.thePlayer.motionZ = -motionZ * 0.11D;
                mc.timer.timerSpeed = 0.9F;
            } else {
                mc.thePlayer.motionX = 0.0D;
                mc.thePlayer.motionY = 0.0D;
                mc.thePlayer.motionZ = 0.0D;
                mc.timer.timerSpeed = 1.0F;
            }
        }
    }
    public void Revesre() {
        knock = false;
        if (mc.thePlayer.hurtTime > 0.2) {
            mc.thePlayer.motionY = -0.13;
        }
    }
    public void Jump() {
        knock = false;
        if (mc.thePlayer.hurtTime > 0.2){
            if (mc.thePlayer.onGround) {
                mc.thePlayer.jump();
            }
        }
    }
    @EventTarget
    public void  Packetnoknock(ProcessPacketEvent e) {

        if (mode1.getValString().equalsIgnoreCase("Packet")) {
            Packet packet = e.getPacket();
            if (packet instanceof S12PacketEntityVelocity || packet instanceof S27PacketExplosion) {
                e.setCancelled(true);
            }
        }


    }
    public void Hypixel() {
        knock = false;
        if ( mc.thePlayer.hurtTime > 0) {
            this.mc.thePlayer.onGround = true;
        }
    }
    public void Mineplex() {
        knock = false;
        mc.thePlayer.motionX = 0;
        mc.thePlayer.motionZ = 0;
    }
    public int f = 1;
    public static  boolean knock = false;
    public void nullknock() {
       if (f != 0) {
           f++;
           knock = true;

       }
    }
    public void Dev() {
        knock = false;
        if ( !mc.thePlayer.onGround && mc.thePlayer.moveForward > 0) {
            if (mc.thePlayer.hurtResistantTime <= 19) {
                mc.thePlayer.motionX *= 0.0;
                mc.thePlayer.motionZ *= 0.0;
            }
        }
    }
public void legit(){
        if (mc.thePlayer.hurtTime > 0.1 && !mc.thePlayer.onGround){
            mc.thePlayer.setSprinting(false);
            mc.thePlayer.setSneaking(true);
        }else {
            mc.thePlayer.setSprinting(true);
            mc.thePlayer.setSneaking(false);
        }
}
    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        knock = false;
    }
}



