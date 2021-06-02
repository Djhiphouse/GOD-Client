package me.bratwurst.module.modules.combat;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;

public class InfiniteAura extends Module {
    public Setting Packets, Bypass, Range, MoveFix;
    public InfiniteAura() {
        super("Infiniteaura", Category.COMBAT);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        for(Entity e : mc.theWorld.loadedEntityList) {
            if (e instanceof EntityPlayer) {
                if (e != mc.thePlayer && e != null && e.getDistanceToEntity(mc.thePlayer) <= 30F) {
                    double x = mc.thePlayer.posX;
                    double y = mc.thePlayer.posY;
                    double z = mc.thePlayer.posZ;

                    double entityposx = e.posX;
                    double entityposy = e.posY;
                    double entityposZ = e.posZ;
                    PlayerUtils.sendMessage("Entity: " + e.getName());
                   for (int i = 0; i < 20; i++) {
                       Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(entityposx,entityposy,entityposZ, true));



                   }


                    mc.playerController.attackEntity(mc.thePlayer, e);
                    mc.thePlayer.swingItem();
                    mc.thePlayer.setPositionAndUpdate(x,y,z);
                    if (TimeHelper.hasReached(10)) {
                        mc.thePlayer.setPositionAndUpdate(x,y,z);
                        TimeHelper.reset();
                    }
                }
            }
        }

         double x = mc.thePlayer.posX;
         double y = mc.thePlayer.posY;
         double z = mc.thePlayer.posZ;

    }
}
