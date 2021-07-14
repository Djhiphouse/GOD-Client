package me.bratwurst.module.modules.combat;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

import java.util.Random;

public class WaoponAmibot extends Module {
    public static Setting Silent;
    public static float yaw;
    public float pitch;

    public WaoponAmibot() {
        super("WaoponAmibot", Category.COMBAT);
    }

    @Override
    public void setup() {
        Client.setmgr.rSetting(Silent = new Setting(EnumChatFormatting.AQUA + "Silent", this, false));


    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        for (Object o : mc.theWorld.loadedEntityList) {
            if (o instanceof EntityPlayer) {
                EntityPlayer target = (EntityPlayer) o;
                if (target != mc.thePlayer && target != null) {
                    float[] rotatee = Aacrotate((EntityPlayer) target);
                    yaw = rotatee[0];
                    pitch = rotatee[1];
                    mc.thePlayer.rotationYawHead = rotatee[0];
                    if (Silent.getValBoolean()) {

                    } else {
                        float[] rotate = Aacrotate((EntityPlayer) target);
                        yaw = rotate[0];
                        pitch = rotate[1];
                        mc.thePlayer.rotationYawHead = rotate[0];
                        mc.thePlayer.rotationYaw = yaw;
                        mc.thePlayer.rotationPitchHead = pitch;
                        mc.thePlayer.rotationPitch = pitch;
                    }
                }
            }
        }


    }

    public static float[] Aacrotate(EntityPlayer e) {

        Random rdm = new Random();

        double d0 = e.posX - mc.thePlayer.posX;
        double d1 = e.posY + 1.6 - (mc.thePlayer.posY + (double) mc.thePlayer.getEyeHeight());
        double d2 = e.posZ - mc.thePlayer.posZ;
        double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        float f = (float) (MathHelper.func_181159_b(d2, d0) * 180 / Math.PI) - 90.0F;
        float f1 = (float) (-(MathHelper.func_181159_b(d1, d3) * 180 / Math.PI));
        float yaw, pitch;
        yaw = f;
        pitch = f1;


        return new float[]{updateRotation(mc.thePlayer.rotationYaw, yaw + 1 , 180f),
                updateRotation(mc.thePlayer.rotationPitch, pitch + 1, 180f)};
    }


    private static float updateRotation(float p_75652_1_, float p_75652_2_, float p_75652_3_) {
        float f = MathHelper.wrapAngleTo180_float(p_75652_2_ - p_75652_1_);

        if (f > p_75652_3_) {
            f = p_75652_3_;
        }

        if (f < -p_75652_3_) {
            f = -p_75652_3_;
        }

        return p_75652_1_ + f;

    }
}
