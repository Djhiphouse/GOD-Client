package me.bratwurst.module.modules.Player;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C03PacketPlayer;

public class GodMode extends Module {
    public GodMode() {
        super("GodMode", Category.EXPLOIT);
    }
    @EventTarget

    public void onUpdate(ProcessPacketEvent e) {
        NetHandlerPlayClient netHandlerPlayClient = Minecraft.getMinecraft().getNetHandler();
mc.thePlayer.setPositionAndUpdate(mc.thePlayer.posX, mc.thePlayer.posY =+ 2, mc.thePlayer.posZ);
        if(e.getPacket() instanceof C03PacketPlayer) {
            e.setCancelled(true);
        }
    }
}
