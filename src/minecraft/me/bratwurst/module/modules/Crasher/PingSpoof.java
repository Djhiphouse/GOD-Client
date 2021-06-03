package me.bratwurst.module.modules.Crasher;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.network.play.client.C00PacketKeepAlive;

public class PingSpoof extends Module {
    public Setting Ping;
    public static Setting mode1;

    public PingSpoof() {
        super("PingSpoof", Category.EXPLOIT);
    }

    @Override
    public void setup() {

        Client.setmgr.rSetting(Ping = new Setting("Ping:", this, 800, 1, 2000, false));

    }

    @EventTarget
    public void ProcessPacketEvent(ProcessPacketEvent e) {


        if (e.getPacket() instanceof C00PacketKeepAlive) {
            e.setCancelled(true);
        }

    }
}
