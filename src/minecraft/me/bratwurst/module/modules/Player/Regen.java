package me.bratwurst.module.modules.Player;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;

import java.util.ArrayList;

public class Regen extends Module {
    public Setting Packets, Bypass, Range, MoveFix;
    public static Setting mode1;


    public Regen() {
        super("Regen", Category.PLAYER);
        ArrayList<String> options = new ArrayList<>();
        options.add("Normal");
        options.add("Packet");
        options.add("Burst");
        Client.setmgr.rSetting(mode1 = new Setting("Regen Mode", this, "Normal", options));
    }

    @Override
    public void setup() {
        Client.setmgr.rSetting(Packets = new Setting("Packets:", this, 10, 1, 25, true));
    }

    int packets = 3;

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        if (mode1.getValString().equalsIgnoreCase("Packet")) {
            packetRegen(Packets.getValInt());
        } else if (mode1.getValString().equalsIgnoreCase("Normal")) {
            Normal();
        }else if (mode1.getValString().equalsIgnoreCase("Burst")) {
            Burst(Packets.getValInt());
        }


    }

    private void packetRegen(int packets) {

        if (mc.thePlayer.onGround) {
            if (!mc.thePlayer.capabilities.isCreativeMode && mc.thePlayer.getFoodStats().getFoodLevel() > 17 && mc.thePlayer.getHealth() < 20.0F && mc.thePlayer.getHealth() != 0.0F && mc.thePlayer.onGround) {
                for (int i = 0; i < packets; ++i) {
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer());
                }
            }
        }

    }

    public void Normal() {
        if (mc.thePlayer.getActivePotionEffect(Potion.regeneration) != null && (mc.thePlayer.onGround && mc.thePlayer.getHealth() < mc.thePlayer.getMaxHealth())) {
            for (int i = 0; (float) i < mc.thePlayer.getMaxHealth() - mc.thePlayer.getHealth() && mc.thePlayer.getActivePotionEffect(Potion.regeneration) != null; ++i) {
                this.mc.getNetHandler().addToSendQueue(new C03PacketPlayer(true));
            }
        }
    }

    public void Burst(int bursttime) {
        if (mc.thePlayer.onGround) {
            if (TimeHelper.hasReached(300)) {
                if (!mc.thePlayer.capabilities.isCreativeMode && mc.thePlayer.getFoodStats().getFoodLevel() > 17 && mc.thePlayer.getHealth() < 20.0F && mc.thePlayer.getHealth() != 0.0F && mc.thePlayer.onGround) {
                    for (int i = 0; i < bursttime; ++i) {
                        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer());
                    }
                }
                TimeHelper.reset();
            }
        }

    }

}
