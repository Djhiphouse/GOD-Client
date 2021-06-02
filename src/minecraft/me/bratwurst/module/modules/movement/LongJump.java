package me.bratwurst.module.modules.movement;

import com.google.common.base.Stopwatch;
import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovementInput;

import javax.vecmath.Vector3d;
import java.util.ArrayList;

public class LongJump extends Module {

    private int counter;

    private float air;

    private float groundTicks;



    double motionY;

    int count;

    boolean collided;

    boolean half;

    protected double motionVa;

    private boolean jump;

    double speed = 0.0D;

    int delay2 = 0;
    protected boolean boosted;
    protected double startY;




    private int stage;
    ArrayList<Packet> Packets = new ArrayList<>();
    ArrayList<Vector3d> loc = new ArrayList<>();
    private Vector3d startVector3d;
    public double x, y, z;

    public Setting Bypass, Speed, veryBlock, Speeddown;
    public static Setting mode1;

    public LongJump() {
        super("LongJump", Category.MOVEMENT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Mccentral");
        options.add("Vanilla");
        options.add("Bettermccentral");
        options.add("Mineplex");
        options.add("Hypixel");
        options.add("Hypixelold");
        options.add("Cubecraft");
        options.add("newCubecraft");
        options.add("Redesky");



        Client.setmgr.rSetting(mode1 = new Setting("LongJump Mode", this, "Mccentral", options));
    }



    @EventTarget
    public void onUpdate(EventMotionUpdate event) {
        if (mode1.getValString().equalsIgnoreCase("Mccentral")) {
            Mccentral();
        }else if (mode1.getValString().equalsIgnoreCase("Bettermccentral")) {
            BetterMccentral();
        }else if (mode1.getValString().equalsIgnoreCase("Vanilla")) {
            vanilla();
        }else if (mode1.getValString().equalsIgnoreCase("Mineplex")) {
           Mineplex();
        }else if (mode1.getValString().equalsIgnoreCase("Hypixel")) {
            Hypixel();
        }else if (mode1.getValString().equalsIgnoreCase("Cubecraft")) {
           Cubecraft();
        }else if (mode1.getValString().equalsIgnoreCase("newCubecraft")) {
            cubecraft2();
        }else if (mode1.getValString().equalsIgnoreCase("Hypixelold")) {
           Hypixel2();
        }else if (mode1.getValString().equalsIgnoreCase("Redesky")) {
            Redesky();
        }

    }

    public void Mccentral() {
        if (mc.thePlayer.onGround) ;
        if (mc.thePlayer.onGround) {
            mc.thePlayer.jump();
            mc.timer.timerSpeed = 1.0F;
        } else {
            mc.timer.timerSpeed = 1.05F;
            double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
            double x = -Math.sin(yaw) * 2.4;
            double z = Math.cos(yaw) * 2.4;
            mc.thePlayer.motionX = x;
            mc.thePlayer.motionZ = z;
            mc.thePlayer.moveForward *= 19.0F;
            mc.thePlayer.moveStrafing *= 4.0F;
        }
    }
    public void BetterMccentral() {
        double jump1 = 1.5;
        if (mc.thePlayer.onGround) ;
        if (mc.thePlayer.onGround) {
            mc.thePlayer.jump();
            mc.timer.timerSpeed = 1.0F;
        } else {
            mc.timer.timerSpeed = 1.05F;
            double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
            double x = -Math.sin(yaw) * jump1;
            double z = Math.cos(yaw) * jump1;
            mc.thePlayer.motionX = x;
            mc.thePlayer.motionZ = z;
            mc.thePlayer.moveForward *= 19.0F;
            mc.thePlayer.moveStrafing *= 4.0F;
        }
    }
    public void vanilla() {
        mc.thePlayer.motionY = 0.09;
        double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
        double x = -Math.sin(yaw) * 0.5;
        double z = Math.cos(yaw) * 0.5;
        mc.thePlayer.motionX = x;
        mc.thePlayer.motionZ = z;
        mc.thePlayer.moveForward *= 19.0F;
        mc.thePlayer.moveStrafing *= 4.0F;
    }
    public void cubecraft2() {
        if (mc.thePlayer.moveForward != 0) {
            (mc.getMinecraft()).gameSettings.keyBindLeft.pressed = false;
            (mc.getMinecraft()).gameSettings.keyBindRight.pressed = false;
            (mc.getMinecraft()).gameSettings.keyBindJump.pressed = false;
            (mc.getMinecraft()).gameSettings.keyBindBack.pressed = false;
        }
        double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
        double x = -Math.sin(yaw) * 1.5D;
        double z = Math.cos(yaw) * 1.5D;
        if (mc.thePlayer.onGround) {
            mc.thePlayer.jump();
        } else if (mc.thePlayer.fallDistance > 0.3D) {
            mc.timer.timerSpeed = 0.5F;
            mc.thePlayer.setPosition(mc.thePlayer.posX + x, mc.thePlayer.posY - 0.1D, mc.thePlayer.posZ + z);
            mc.thePlayer.onGround = true;
            mc.thePlayer.onGround = false;
        }
    }
public void  Hypixel() {
    float x2 = 0.3F + getSpeedEffect() * 0.45F;
    if (mc.thePlayer.moveForward != 0 && mc.thePlayer.onGround) {
        setMotion(0.15D);
        this.stage = 1;
        mc.thePlayer.jump();
    }
    if (this.counter > 10)
        this.air = x2 * 20.0F;
    if (mc.thePlayer.onGround) {
        this.air = 0.0F;
    } else {
        double speed = 0.95D + getSpeedEffect() * 0.2D - (this.air / 30.0F);
        if (speed < mc.thePlayer.getBaseMoveSpeed() - 0.05D)
            speed = mc.thePlayer.getBaseMoveSpeed() - 0.05D;
        setMotion(speed);
        this.air += x2;
        this.counter++;
    }
}
public void Hypixel2() {
   if (!mc.thePlayer.onGround) {
        if (mc.thePlayer.isCollidedVertically)
            this.stage = 0;
        mc.thePlayer.motionY = getMotion(this.stage);
        if (this.stage > 0)
            this.stage++;
    }
}
public void Cubecraft() {
    mc.timer.timerSpeed = 0.3F;
    float x2 = 1.0F + getSpeedEffect() * 0.45F;
    if (mc.thePlayer.onGround) {
        double x = mc.thePlayer.posX, y = mc.thePlayer.posY, z = mc.thePlayer.posZ;
        for (int index = 0; index < 49; index++) {
            mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(x, y + 0.0625D, z, false));
            mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(x, y, z, false));
        }
        mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(x, y, z, true));
        if (this.groundTicks > 0.0F) {
            this.groundTicks = 0.0F;
            return;
        }
        this.groundTicks++;
        mc.thePlayer.motionX *= 1.0D;
        mc.thePlayer.motionZ *= 1.0D;
        mc.thePlayer.motionY = 0.5D;
    }
    if (mc.thePlayer.onGround) {
        this.air = 0.0F;
    } else {
        mc.thePlayer.motionX *= 0.0D;
        mc.thePlayer.motionZ *= 0.0D;
        double speed = 3.0D + (getSpeedEffect() * 0.2F) - (this.air / 25.0F);
        mc.thePlayer.jumpMovementFactor = (float)((speed > 0.2800000011920929D) ? speed : 0.2800000011920929D);
        this.air = (float)(this.air + x2 * 1.0D * 2.0D);
    }
    this.stage = -999;
    if (mc.thePlayer.isCollidedVertically)
        this.stage = -2;
  }

public void Mineplex() {
    mc.thePlayer.cameraYaw = 0.18181817F;
    if (!mc.thePlayer.onGround &&  this.air > 0.0F) {
        this.air++;
        if (mc.thePlayer.isCollidedVertically)
            this.air = 0.0F;
        if (mc.thePlayer.isCollidedHorizontally && !this.collided)
            this.collided = !this.collided;
        double speed = this.half ? (0.5D - (this.air / 100.0F)) : (0.658D - (this.air / 100.0F));
        mc.thePlayer.motionX = 0.0D;
        mc.thePlayer.motionZ = 0.0D;
        this.motionY -= 0.04000000000001D;
        if (this.air > 24.0F)
            this.motionY -= 0.02D;
        if (this.air == 12.0F)
            this.motionY = -0.005D;
        if (speed < 0.3D)
            speed = 0.3D;
        if (this.collided)
            speed = 0.2873D;
        mc.thePlayer.motionY = this.motionY;
        setMotion(speed);
    } else if (this.air > 0.0F) {
        this.air = 0.0F;
    }
    if (mc.thePlayer.onGround && mc.thePlayer.isCollidedVertically && (mc.thePlayer.moveForward != 0.0F || mc.thePlayer.moveStrafing != 0.0F)) {
        double groundspeed = 0.0D;
        this.collided = mc.thePlayer.isCollidedHorizontally;
        this.groundTicks++;
        mc.thePlayer.motionX *= groundspeed;
        mc.thePlayer.motionZ *= groundspeed;
        this.half = (mc.thePlayer.posY != (int)mc.thePlayer.posY);
        mc.thePlayer.motionY = 0.4299999D;
        this.air = 1.0F;
        this.motionY = mc.thePlayer.motionY;
    }
}

    public int getSpeedEffect() {
        return mc.thePlayer.isPotionActive(Potion.moveSpeed) ? (mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1) : 0;
    }
    @Override
    public void onDisable() {
        super.onDisable();
        mc.thePlayer.motionX = 0.0D;
        mc.thePlayer.motionZ = 0.0D;
        mc.thePlayer.setSpeed(0.0D);
        mc.timer.timerSpeed = 1.0F;
        mc.thePlayer.capabilities.allowFlying = false;

        mc.thePlayer.capabilities.isFlying = false;

    }

    public void setMotion(double speed) {
        double forward = MovementInput.moveForward;
        double strafe = MovementInput.moveStrafe;
        float yaw = mc.thePlayer.rotationYaw;
        if (forward == 0.0D && strafe == 0.0D) {
            mc.thePlayer.motionX = 0.0D;
            mc.thePlayer.motionZ = 0.0D;
        } else {
            if (forward != 0.0D) {
                if (strafe > 0.0D) {
                    yaw += ((forward > 0.0D) ? -45 : 45);
                } else if (strafe < 0.0D) {
                    yaw += ((forward > 0.0D) ? 45 : -45);
                }
                strafe = 0.0D;
                if (forward > 0.0D) {
                    forward = 1.0D;
                } else if (forward < 0.0D) {
                    forward = -1.0D;
                }
            }
            mc.thePlayer.motionX = forward * speed * Math.cos(Math.toRadians((yaw + 90.0F))) + strafe * speed * Math.sin(Math.toRadians((yaw + 90.0F)));
            mc.thePlayer.motionZ = forward * speed * Math.sin(Math.toRadians((yaw + 90.0F))) - strafe * speed * Math.cos(Math.toRadians((yaw + 90.0F)));
        }
    }
    double getMotion(int stage) {
        double[] mot = { 0.359D, 0.273D, 0.201D, 0.129D, 0.057D, -0.015D, -0.087D, -0.159D };
        stage--;
        if (stage >= 0 && stage < mot.length)
            return mot[stage];
        return mc.thePlayer.motionY;
    }
    public void Redesky() {
        mc.thePlayer.capabilities.isFlying = true;
        mc.thePlayer.capabilities.allowFlying = true;
       Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX * mc.thePlayer.motionZ * mc.thePlayer.motionZ);
    }


}


