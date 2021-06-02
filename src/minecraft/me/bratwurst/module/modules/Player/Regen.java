package me.bratwurst.module.modules.Player;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
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
        Client.setmgr.rSetting(mode1 = new Setting("Regen Mode", this, "Normal",options));
    }
    @Override
    public void setup() {
        Client.setmgr.rSetting(Packets = new Setting("Packets:", this, 5, 1, 200, true));
    }

    int packets = 3;

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        if (mode1.getValString().equalsIgnoreCase("Packet")){
            packetRegen(Packets.getValInt());
        }else if (mode1.getValString().equalsIgnoreCase("Normal")) {
            Normal();
        }


    }

    private void packetRegen(int packets) {

        if (mc.thePlayer.onGround) {
            for (int i = 0; i < packets; ++i) {
                mc.getNetHandler().addToSendQueueSilent(new C03PacketPlayer(true));
            }
        }

    }

    public void Normal() {
        if (mc.thePlayer.isPotionActive(Potion.regeneration)) {
            this.packetRegen((int) ((int) packets / 40.0D
                    * (double) mc.thePlayer.getActivePotionEffect(Potion.regeneration).getAmplifier()));
        }
    }

}
