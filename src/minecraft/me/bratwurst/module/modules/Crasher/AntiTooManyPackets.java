package me.bratwurst.module.modules.Crasher;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.event.events.WorldTickEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class AntiTooManyPackets extends Module {
    int sentThisSecond;
    int ticks;
    public AntiTooManyPackets() {
        super("AntiTooManyPackets", Category.EXPLOIT);
    }
    @EventTarget
    public void ProcessPacketEvent(ProcessPacketEvent e) {

            ++ticks;
            if (this.ticks % 20 == 0) {
                this.sentThisSecond = 0;
            }


            ++this.sentThisSecond;
            if (this.sentThisSecond > 170 && !(e.getPacket() instanceof C00PacketKeepAlive) && this.mc.theWorld != null && this.mc.thePlayer != null) {
                e.setCancelled(true);
                PlayerUtils.sendMessage(ChatFormatting.RED + "Packet: " + e.getPacket().getClass().getName() + " Du hast zu Viele Packete gesendet: " + this.sentThisSecond + "Packet in Sukunden");
            }
        }

    @Override
    public void onDisable() {
        super.onDisable();
        ticks = 0;
        sentThisSecond = 0;
    }
}


