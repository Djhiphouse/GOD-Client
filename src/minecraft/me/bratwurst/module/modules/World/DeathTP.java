package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.Vec3;

public class DeathTP extends Module {
    private Vec3 vec3;
    public DeathTP() {
        super("DeathTP", Category.WORLD);
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        if (mc.thePlayer.deathTime > 0) {
            vec3 = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
        }
        if (this.vec3 != null) {
            mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.onGround));
            mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.vec3.xCoord, this.vec3.yCoord, this.vec3.zCoord, mc.thePlayer.onGround));
            this.vec3 = null;
        }


    }

}
