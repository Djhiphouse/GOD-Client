package me.bratwurst.module.modules.Crasher;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.Msgtimer;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.util.EnumChatFormatting;

public class PingSpoof extends Module {
    public Setting Ping;
    public static Setting mode1;

    public PingSpoof() {
        super("PingSpoof", Category.EXPLOIT);
    }

    @Override
    public void setup() {

        Client.setmgr.rSetting(Ping = new Setting(EnumChatFormatting.AQUA + "Ping:", this, 800, 1, 2000, true));

    }

    public static int uu = 400;

    @EventTarget
    public void ProcessPacketEvent(ProcessPacketEvent e) {
        this.setDisplayname(EnumChatFormatting.RED + " - Ping: " + Ping.getValInt());

            for (int i = 0; i < 2; i++) {
                if (e.getPacket() instanceof C00PacketKeepAlive) {

                    e.setCancelled(true);


                } else if (e.getPacket() instanceof C0FPacketConfirmTransaction) {
                    e.setCancelled(true);
                }


        }


    }

}

