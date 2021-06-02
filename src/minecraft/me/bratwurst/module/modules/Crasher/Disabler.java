package me.bratwurst.module.modules.Crasher;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;

import java.util.ArrayList;

public class Disabler extends Module {
    public static Setting mode1;
    ArrayList<Packet> Packets = new ArrayList<>();
    public Disabler() {
        super("Disabler", Category.EXPLOIT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Introuble");
        options.add("Ghostlie.live");
        options.add("Hypixel");
        options.add("KAURI");
        Client.instance.setmgr.rSetting(new Setting("Disable Anticheat", this, "Slide", options));
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
  if (mode1.getValString().equalsIgnoreCase("Ghostlie.live")) {
      ghostlie();
  }else if (mode1.getValString().equalsIgnoreCase("Hypixel")) {
      Watchdog();

        }
    }



    public void ghostlie() {
        if (TimeHelper.hasReached(200)) {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C0CPacketInput(999, 999, true, true));
            TimeHelper.reset();
        }

    }
    public void Watchdog() {
        if (Client.moduleManager.getModuleByName("Glide").isToggle()) {
            PlayerCapabilities playerCapabilities = new PlayerCapabilities();
            playerCapabilities.isFlying = true;
            playerCapabilities.allowFlying = true;
            playerCapabilities.setFlySpeed((float)2.0D);
            mc.getNetHandler().addToSendQueueSilent(new C13PacketPlayerAbilities(playerCapabilities));
        }

    }
    public void ProcessPacketEvent(ProcessPacketEvent e){

        for (Packet p : Packets) {
            if(p instanceof C0FPacketConfirmTransaction){
                e.setCancelled(true);
            }

        }
        Packets.clear();
    }
}
