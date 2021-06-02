package me.bratwurst.module.modules.combat;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

import java.sql.Time;
import java.util.ArrayList;

public class Criticals extends Module {
    public static Setting mode1;

    public Criticals() {
        super("Criticals", Category.COMBAT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Jump");
        options.add("Packet");
        options.add("Legit");


        Client.setmgr.rSetting(mode1 = new Setting("Criticals Mode", this, "Jump", options));
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate event) {
        if (mode1.getValString().equalsIgnoreCase("Jump")) {
            Jump();
        } else if (mode1.getValString().equalsIgnoreCase("Packet")) {
            packet();
        } else if (mode1.getValString().equalsIgnoreCase("Legit")) {
            legit();
        }
    }

    public Minecraft Packetground = Minecraft.getMinecraft();
    public Minecraft Spieler = Minecraft.getMinecraft();

    public void Jump() {
        if (mc.thePlayer.onGround && Client.moduleManager.getModuleByName("Aura").isEnabled()) {
            mc.thePlayer.jump();
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer(true));
            PlayerUtils.sendMessage("Jumping");
        }


    }

    public void packet() {
        double x = mc.thePlayer.posX;
        double y = mc.thePlayer.posY;
        double z = mc.thePlayer.posZ;
        if (mc.thePlayer.onGround && Client.moduleManager.getModuleByName("Aura").isEnabled()) {
            mc.thePlayer.jump();
            if (TimeHelper.hasReached(200)) {
                mc.thePlayer.motionY = -10;
                TimeHelper.reset();
                PlayerUtils.sendMessage("Jumping");
            }




        } else {
            return;
        }


    }

    public void legit() {
        if (mc.thePlayer.onGround && Client.moduleManager.getModuleByName("Aura").isEnabled()) {
            mc.thePlayer.jump();
        }
    }
}
