package me.bratwurst.module.modules.Player;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

import java.util.Random;

public class AntiAim extends Module {
    public static Setting mode1;
    public static EntityLivingBase target1;
    public static Setting minCps, maxCps, Range, SpinSpeed, SpinHead;
    public static Boolean noraote = false;
    public static Boolean noraote2 = false;
    public static float yaw;
    public float pitch;
    public boolean Attack = false;
    public boolean rotation = false, bocking = false;
    public AntiAim() {
        super("AntiAim", Category.PLAYER);
    }

    public static int Rotationmove = 20;
    public static  int Headrotate = 20;

    @Override
    public void setup() {

        Client.setmgr.rSetting(SpinSpeed = new Setting("SpinSpeed", this, 1, 1, 80, true));
        Client.setmgr.rSetting(SpinHead = new Setting("SpinHead", this, 1, 1, 80, true));

    }
    @EventTarget

    public void onUpdate(EventMotionUpdate e) {
        Rotationmove+= SpinSpeed.getValInt();
        Headrotate += SpinHead.getValInt();
        float[] rotate = Aacrotate();
        yaw = rotate[0];
        pitch = rotate[1];
        mc.thePlayer.rotationYawHead = rotate[0];
        e.setYaw(yaw);
        e.setPitch(pitch);

    }

    public static float[] Aacrotate() {

        Random rdm = new Random();

        double d0 = mc.thePlayer.posX;
        double d1 = mc.thePlayer.posY + (double) mc.thePlayer.getEyeHeight();
        double d2 =mc.thePlayer.posZ;
        double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        float f = (float) (MathHelper.func_181159_b(d2, d0) * Rotationmove / Math.PI) - 90.0F;
        float f1 = (float) (-(MathHelper.func_181159_b(d1, d3) * Headrotate / Math.PI));
        float yaw, pitch;
        yaw = f;
        pitch = f1;

            return new float[] { updateRotation(mc.thePlayer.rotationYaw, yaw + 1, 180f),
                    updateRotation(mc.thePlayer.rotationPitch, pitch + 1, 180f) };


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
