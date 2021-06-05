package me.bratwurst.module.modules.movement;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Timer;

import java.util.ArrayList;

public class Speed extends Module {
    private double lastDist;
    private boolean wasinairaac;
    public Setting Speed, Bypass, TeleportDelay, TPRange;
    public static Setting mode1;

    public Speed() {
        super("Speed", Category.MOVEMENT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Onground");
        options.add("NCPbhop");
        options.add("Teleport");
        options.add("AAC 3.3.1");
        options.add("AAC 3.3.10");
        options.add("AACYPort");
        options.add("AACBhop");
        options.add("Hypixel");
        options.add("StrafeHop");
        options.add("MC-CentralFast");


        Client.setmgr.rSetting(mode1 = new Setting("Speed Mode", this, "NCPbhop", options));
    }

    @Override
    public void setup() {
        Client.setmgr.rSetting(Bypass = new Setting("Bypass", this, false));
        Client.setmgr.rSetting(Speed = new Setting("Speed", this, 1, 1, 5, false));
        Client.setmgr.rSetting(TeleportDelay = new Setting("TPDelay", this, 1000, 65, 2000, true));
        Client.setmgr.rSetting(TPRange = new Setting("TPrange", this, 1, 1, 4, true));


    }

    @EventTarget
    public void onUpdate(EventUpdate event) {

        if (mode1.getValString().equalsIgnoreCase("Onground")) {
            onground(Speed.getValInt());
        } else if (mode1.getValString().equalsIgnoreCase("NCPbhop")) {
            NCp();
        } else if (mode1.getValString().equalsIgnoreCase("Teleport")) {
            Teleport(TeleportDelay.getValInt(),TPRange.getValInt());
        } else if (mode1.getValString().equalsIgnoreCase("AAC 3.3.1")) {
            AAC3311();
        } else if (mode1.getValString().equalsIgnoreCase("AAC 3.3.10")) {
            AAC3310();
        } else if (mode1.getValString().equalsIgnoreCase("AACYPort")) {
            AACYPort();
        } else if (mode1.getValString().equalsIgnoreCase("AACBhop")) {
            AACBhop();
        } else if (mode1.getValString().equalsIgnoreCase("Hypixel")) {
            Hypixel();
        } else if (mode1.getValString().equalsIgnoreCase("StrafeHop")) {
            StrafeHop();
        } else if (mode1.getValString().equalsIgnoreCase("MC-CentralFast")) {
            Mccentralfast();
        }
    }


    private void StrafeHop() {
        if (this.mc.thePlayer.moveForward > 0.0F)
            if (this.mc.thePlayer.onGround) {
                this.mc.thePlayer.jump();
            } else if (this.mc.thePlayer.fallDistance > 0.25D) {
                this.mc.thePlayer.motionY = -63.0D;
                this.mc.thePlayer.moveStrafing *= 2.0F;
            }
    }

    private void Hypixel() {
        if (this.mc.thePlayer.moveForward > 0.0F && this.mc.thePlayer.onGround) {
            this.mc.thePlayer.jump();
            this.mc.thePlayer.motionY = 0.4D;
            mc.timer.timerSpeed = 1.3F;
            this.mc.thePlayer.motionX *= 1.1D;
            this.mc.thePlayer.motionZ *= 1.1D;

        }
    }

    public void onground(int Delay) {
        if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() && mc.thePlayer.onGround) {
            double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
            double pitch = Math.toRadians(mc.thePlayer.rotationPitch);
            double x = -Math.sin(yaw) * 1;
            double z = Math.cos(yaw) * 1;
            double y = -Math.sin(pitch) * 0.1;

            mc.thePlayer.motionX = x;
            mc.thePlayer.motionZ = z;
            mc.thePlayer.motionY = y;
            mc.thePlayer.moveForward *= 19.0F;
            mc.thePlayer.moveStrafing *= 4.0F;

        }
    }

    private void AAC3310() {

        if (mc.gameSettings.keyBindForward.pressed && !this.mc.thePlayer.isCollidedHorizontally) {

            mc.gameSettings.keyBindJump.pressed = false;
            if (this.mc.thePlayer.onGround) {
                this.mc.thePlayer.jump();
                this.mc.thePlayer.motionY = 0.41D;
                mc.timer.timerSpeed = 1.0F;
            } else {
                mc.timer.timerSpeed = 1.043F;
                this.mc.thePlayer.motionX *= 1.00600004196167D;
                this.mc.thePlayer.motionZ *= 1.00600004196167D;
            }
        }
    }

    private void AACYPort() {
        BlockPos bp = new BlockPos(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 2.0D, this.mc.thePlayer.posZ);

        if (mc.gameSettings.keyBindForward.pressed && !this.mc.thePlayer.isInWater()) {
            BlockPos below = new BlockPos(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 1.0D, this.mc.thePlayer.posZ);
            if (this.mc.theWorld.getBlockState(bp).getBlock() == Blocks.air) {
                if (this.mc.thePlayer.isCollidedVertically) {
                    this.mc.thePlayer.jump();
                } else if (!(this.mc.theWorld.getBlockState(below).getBlock() instanceof net.minecraft.block.BlockAir)) {
                    this.mc.thePlayer.motionY = -0.2149999886751175D;
                }
            } else {
                this.mc.thePlayer.motionX *= 1.7D;
                this.mc.thePlayer.motionZ *= 1.7D;
                this.mc.thePlayer.jump();
                this.mc.thePlayer.motionY--;
            }
        }
    }

    private void AACBhop() {

        if (!mc.gameSettings.keyBindJump.pressed) {
            if (this.mc.thePlayer.moveForward > 0.0F)
                return;
            this.mc.thePlayer.setSprinting(true);
            if (this.mc.thePlayer.hurtTime != 0)
                return;
            this.mc.thePlayer.setSpeed(
                    Math.sqrt(this.mc.thePlayer.motionX * this.mc.thePlayer.motionX + this.mc.thePlayer.motionZ * this.mc.thePlayer.motionZ) * 1.0D);
            if (this.mc.thePlayer.onGround) {
                this.mc.thePlayer.jump();
                this.mc.thePlayer.motionY = 0.386D;
                this.mc.thePlayer.motionX *= 1.008D;
                this.mc.thePlayer.motionZ *= 1.008D;
            } else {
                this.mc.thePlayer.jumpMovementFactor = 0.0263F;
            }
        } else {
            if (!this.mc.thePlayer.onGround)
                if (TimeHelper.hasReached(1L)) {
                    this.mc.thePlayer.motionX *= -1.0D;
                    this.mc.thePlayer.motionZ *= -1.0D;
                    return;
                }
            if (this.mc.thePlayer.onGround)
                this.wasinairaac = false;
        }
    }

    public void NCp() {
        if (this.mc.thePlayer != null && this.mc.theWorld != null) {
            if (mc.gameSettings.keyBindForward.pressed && !this.mc.thePlayer.isCollidedHorizontally) {
                mc.gameSettings.keyBindJump.pressed = false;
                if (this.mc.thePlayer.onGround) {
                    this.mc.thePlayer.jump();
                    mc.timer.timerSpeed = 1.05F;
                    this.mc.thePlayer.motionX *= 1.0707999467849731D;
                    this.mc.thePlayer.motionZ *= 1.0707999467849731D;
                    this.mc.thePlayer.moveStrafing *= 4.0F;
                } else {
                    this.mc.thePlayer.jumpMovementFactor = 0.0465F;
                }
            }
        }
    }

    public void Teleport(int Tpdelay, int Tprange) {
        double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
        double x;
        double z;
        int step = -1;
        z = mc.thePlayer.posZ;
        x = mc.thePlayer.posX;
        if (TimeHelper.hasReached(Tpdelay) && mc.thePlayer.isCollidedVertically && !mc.thePlayer.isCollidedHorizontally && mc.thePlayer.onGround) {
            for (int i = 0; i < 1; i++) {


                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x + -Math.sin(yaw) * i, mc.thePlayer.posY, z + Math.cos(yaw) * i, mc.thePlayer.onGround));
                x += -Math.sin(yaw) * Tprange;
                z += Math.cos(yaw) * Tprange;
                mc.thePlayer.setPosition(mc.thePlayer.posX + -Math.sin(yaw) * Tprange, mc.thePlayer.posY, mc.thePlayer.posZ + Math.cos(yaw) * Tprange);
                mc.gameSettings.keyBindAttack.pressed = false;


            }
            TimeHelper.reset();
        }
    }


    public void Timer(int Speed1) {
        Speed1 = Speed.getValInt();
        mc.timer.timerSpeed = Speed1;
    }

    public void AAC3311() {
        if (!mc.thePlayer.isInWater()) {

            if (mc.gameSettings.keyBindForward.pressed) {
                mc.timer.timerSpeed = 8.0F;
                this.mc.thePlayer.setSpeed(0.01D);
                if (this.mc.thePlayer.ticksExisted % 20 == 0)
                    this.mc.thePlayer.setSpeed(0.001D);
            } else {
                mc.timer.timerSpeed = 1.0F;
            }

            mc.gameSettings.keyBindJump.pressed = false;
        } else {
            mc.timer.timerSpeed = 1.0F;
        }

    }

    public void Mccentralfast() {
        final Minecraft mc = me.bratwurst.module.modules.movement.Speed.mc;
        if (!mc.thePlayer.isOnLadder()) {
            final Minecraft mc2 = me.bratwurst.module.modules.movement.Speed.mc;
            if (mc.thePlayer.moveForward > 0.0f) {
                final Minecraft mc3 = me.bratwurst.module.modules.movement.Speed.mc;
                if (mc.thePlayer.onGround) {
                    me.bratwurst.module.modules.movement.Speed.mc.gameSettings.keyBindSprint.pressed = true;
                    final Minecraft mc4 = me.bratwurst.module.modules.movement.Speed.mc;
                    mc.thePlayer.setSprinting(true);
                    final Minecraft mc5 = me.bratwurst.module.modules.movement.Speed.mc;
                    mc.thePlayer.jump();
                } else {
                    final Minecraft mc6 = me.bratwurst.module.modules.movement.Speed.mc;
                    final EntityPlayerSP thePlayer = mc.thePlayer;
                    thePlayer.motionX *= 1.0;
                }
                final Minecraft mc7 = me.bratwurst.module.modules.movement.Speed.mc;
                final EntityPlayerSP thePlayer2 = mc.thePlayer;
                thePlayer2.motionX *= 1.022;
                final Minecraft mc8 = me.bratwurst.module.modules.movement.Speed.mc;
                final EntityPlayerSP thePlayer3 = mc.thePlayer;
                thePlayer3.motionZ *= 1.022;
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.timer.timerSpeed = 1;
    }
}
