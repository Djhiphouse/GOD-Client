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
import net.minecraft.util.EnumChatFormatting;

public class PingSpoof extends Module {
    public Setting Ping;
    public static Setting mode1;

    public PingSpoof() {
        super("PingSpoof", Category.EXPLOIT);
    }

    @Override
    public void setup() {

        Client.setmgr.rSetting(Ping = new Setting(EnumChatFormatting.AQUA +"Ping:", this, 800, 1, 2000, true));

    }

    @EventTarget
    public void ProcessPacketEvent(ProcessPacketEvent e) {
   this.setDisplayname(EnumChatFormatting.RED + " - Ping: " + Ping.getValInt());

        if (e.getPacket() instanceof C00PacketKeepAlive) {

            mc.getNetHandler().getNetworkManager().sendPacket(new C00PacketKeepAlive(Integer.MAX_VALUE));


        }


    }

}

