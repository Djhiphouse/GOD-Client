package me.bratwurst.module.modules.movement;

import com.google.common.base.Stopwatch;
import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.module.modules.Crasher.AntiBan;
import me.bratwurst.module.modules.Player.AntiAim;
import me.bratwurst.module.modules.Player.Nofall;
import me.bratwurst.utils.*;

import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovementInput;

import javax.vecmath.Vector3d;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private int lastHDistance;
    private boolean isSpeeding;
    private double accelrate;

    private boolean speedTick;
    private boolean inair = false;
    private double posY;
    private boolean wet;
    private boolean reachmax = false;
    boolean done = false;
    boolean leftGround = false;
    private int airTicks;

    public Setting Nofall, Speed, off, Glide, boost, Ticks, JumpMotion;
    public static Setting mode1;

    public LongJump() {
        super("LongJump", Category.MOVEMENT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Bettermccentral");
        options.add("Redesky");
        options.add("Damage");
        options.add("OLDNCP");
        options.add("Custom");
        options.add("OldCubecraft");
        options.add("Spartan");
        options.add("Test");
        options.add("badRedesky");
        options.add("FastRedesky");
        options.add("Mineplexlow");
        options.add("Blockmcold");
        options.add("OPJUMP");
        options.add("Mineplex");


        Client.setmgr.rSetting(mode1 = new Setting(EnumChatFormatting.RED + "LongJump Mode", this, "Mccentral", options));
    }


    @Override
    public void setup() {

        Client.setmgr.rSetting(Nofall = new Setting(EnumChatFormatting.AQUA + "Nofall", this, true));
        Client.setmgr.rSetting(off = new Setting(EnumChatFormatting.AQUA + "off", this, true));
        Client.setmgr.rSetting(Glide = new Setting(EnumChatFormatting.AQUA + "Glide", this, true));
        Client.setmgr.rSetting(boost = new Setting(EnumChatFormatting.AQUA + "boost", this, 2, 1, 5, false));
        Client.setmgr.rSetting(Ticks = new Setting(EnumChatFormatting.AQUA + "Ticks", this, 35, 10, 100, true));
        Client.setmgr.rSetting(JumpMotion = new Setting(EnumChatFormatting.AQUA + "JumpMotion", this, 0.01, 0.650, 0.9, false));
    }

    public static int state = 0;

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        if (mode1.getValString().equalsIgnoreCase("Bettermccentral")) {
            BetterMccentral();
            this.setDisplayname(EnumChatFormatting.RED + " - Bettermccentral");
        } else if (mode1.getValString().equalsIgnoreCase("Redesky")) {
            Redesky();
            this.setDisplayname(EnumChatFormatting.RED + " - Redesky");
        } else if (mode1.getValString().equalsIgnoreCase("Damage")) {
            Schadenundfly();
            this.setDisplayname(EnumChatFormatting.RED + " - Damage");
        } else if (mode1.getValString().equalsIgnoreCase("OLDNCP")) {
            NCP();
            this.setDisplayname(EnumChatFormatting.RED + " - OLDNCP");
        } else if (mode1.getValString().equalsIgnoreCase("Custom")) {
            Custom();
            this.setDisplayname(EnumChatFormatting.RED + " - Custom");
        } else if (mode1.getValString().equalsIgnoreCase("OldCubecraft")) {
            oldCubecraft();
            this.setDisplayname(EnumChatFormatting.RED + " - OldCubecraft");
        } else if (mode1.getValString().equalsIgnoreCase("Spartan")) {
            Guardian();
            this.setDisplayname(EnumChatFormatting.RED + " - Spartan");
        } else if (mode1.getValString().equalsIgnoreCase("Test")) {
            Test();
            this.setDisplayname(EnumChatFormatting.RED + " - Test");
        } else if (mode1.getValString().equalsIgnoreCase("badRedesky")) {
            newredesky();
            this.setDisplayname(EnumChatFormatting.RED + " - badRedesky");
        } else if (mode1.getValString().equalsIgnoreCase("FastRedesky")) {
            FastRedesky(Ticks.getValInt());
            this.setDisplayname(EnumChatFormatting.RED + " - FastRedesky");
        } else if (mode1.getValString().equalsIgnoreCase("Mineplexlow")) {
            Mineplexlow(JumpMotion.getValDouble());
            this.setDisplayname(EnumChatFormatting.RED + " - Mineplexlow");
        }else if (mode1.getValString().equalsIgnoreCase("Blockmcold")) {
            Blockmc();
            this.setDisplayname(EnumChatFormatting.RED + " - Blockmcold");
        }else if (mode1.getValString().equalsIgnoreCase("OPJUMP")) {
            SpecLungjump();
            this.setDisplayname(EnumChatFormatting.RED + " - Spec");
        }else if (mode1.getValString().equalsIgnoreCase("Mineplex")) {
            mine();
            this.setDisplayname(EnumChatFormatting.RED + " - Mineplex");
        }


    }

    public static int Ground = 0;
    public static int tick = 0;
    public static boolean onground;
    public static int jumpmotion = 0;
    public int disableState, damageState;
    public static boolean maxhight = false;
    public static Boolean Damage = false;

    public void mine() {
        if (!mc.thePlayer.onGround && !BlockUtils.isOnGround(0.01) && air > 0) {
            air++;
            if(mc.thePlayer.isCollidedVertically){
                air = 0;
            }
            if(mc.thePlayer.isCollidedHorizontally && !collided){
                collided = !collided;
            }
            double speed = half?0.5- air / 100 : 0.658 - air / 100;
            mc.thePlayer.motionX = 0;
            mc.thePlayer.motionZ = 0;
            motionY -= 0.04000000000001;
            if(air > 24){
                motionY -= 0.02;
            }
            if(air == 12){
                motionY = -0.005;
            }
            if(speed < 0.3)
                speed = 0.3;
            if(collided)
                speed = 0.2873;
            mc.thePlayer.motionY = motionY;
           MovingUtil.setMotion(speed);
        } else {
            if (air > 0) {
                air = 0;
            }
        }

        if (mc.thePlayer.onGround && MovingUtil.isOnGround(0.01) && mc.thePlayer.isCollidedVertically && (mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0)) {
            Module longjump = Client.getInstance().getModuleManager().getModuleByName("LongJump");
            double groundspeed = 0;
            collided = mc.thePlayer.isCollidedHorizontally;
            groundTicks ++;

            mc.thePlayer.motionX *= groundspeed;
            mc.thePlayer.motionZ *= groundspeed;

            half = mc.thePlayer.posY != (int)mc.thePlayer.posY;
            mc.thePlayer.motionY = 0.4299999;
            air = 1;
            motionY = mc.thePlayer.motionY;
        }
    }

public  void SpecLungjump() {
//Damage methode By Bratwurst001
    if (tick == 0) {
        for (int i = 0; i < 10;i++){
            mc.thePlayer.motionX = 0;
            mc.thePlayer.motionZ = 0;
            mc.thePlayer.sendQueue.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX,mc.thePlayer.posY + 0.002,mc.thePlayer.posZ,false));
            mc.thePlayer.sendQueue.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX,mc.thePlayer.posY + 0.0982,mc.thePlayer.posZ,false));
            mc.thePlayer.sendQueue.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX,mc.thePlayer.posY + 0.3682,mc.thePlayer.posZ,false));


        }
        //auf den Boden setzten für den Damage
        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        if (mc.thePlayer.hurtTime < 0.1) {
            if (tick == 1){
                MainUtil.SendClientMesage(EnumChatFormatting.RED + "FLAG: " + "  Damege Flag: " + tick + " Gefahr: Kritik");
                toggle();
            }
        }

    }
    // + 1 damit die Damage methode nur 1 durch geht
     tick++;






if (mc.thePlayer.motionX != mc.thePlayer.motionX )
        MainUtil.SendClientMesage(EnumChatFormatting.RED + "FLAG: " + "  Cord X Flag: " + tick + " Gefahr: Kritik");
    if (mc.thePlayer.motionY != mc.thePlayer.motionY )
        MainUtil.SendClientMesage(EnumChatFormatting.RED + "FLAG: " + "  Cord Y Flag: " + tick + " Gefahr: Kritik");
    if (mc.thePlayer.motionZ != mc.thePlayer.motionZ )
        MainUtil.SendClientMesage(EnumChatFormatting.RED + "FLAG: " + "  Cord Z Flag: " + tick + " Gefahr: Kritik");

   //Damage wir auf true gesetzt
    if (mc.thePlayer.hurtTime > 0.1){
        Damage = true;
    }else {


    }




    //wenn der damage gegeben wurde
 if (Damage == true){


     mc.thePlayer.sendQueue.addToSendQueue(new C18PacketSpectate((UUID.randomUUID())));

     mc.timer.timerSpeed = MovingUtil.isOnGround(0.001) ? 0.75f : 0.6f;
     System.out.println();
     jumpmotion++;
     if (jumpmotion == 1) {
         mc.thePlayer.jump();

     }
     if (mc.thePlayer.onGround && maxhight == true){

         toggle();
         maxhight = false;
     }
     if (PlayerUtil.isMoving2()) {

         mc.thePlayer.jump();
         count++;
         if (maxhight == true) {
             if (MovingUtil.isOnGround( 5)){
                 mc.thePlayer.motionY = -0.1;
                 MovingUtil.setMotion(0.1);
             }else if (MovingUtil.isOnGround( 3)) {
                 mc.thePlayer.motionY = -0.3;
                 MovingUtil.setMotion(0.1);
             }
         }
         if (count == 1) {

             MovingUtil.setMotion(1.3);
         } else if (count == 2) {
             if (mc.thePlayer.moveForward > 0.5)
                 mc.thePlayer.motionY = 0.16;
             //   mc.thePlayer.setPositionAndUpdate(mc.thePlayer.posX,mc.thePlayer.posY + 0.1,mc.thePlayer.posZ);
             MovingUtil.setMotion(1.0);
             if (!MovingUtil.isOnGround(0.001)) {
                 count = 0;
             } else {
                 mc.timer.timerSpeed = 1;
             }
             if (MovingUtil.isOnGround( 7)){
                 maxhight = true;
             }else {

                 if ( maxhight == true){
                     if (MovingUtil.isOnGround( 0.2)){
                         toggle();
                         maxhight = false;
                     }

                 }
             }
         } else if (count >= 3) {
             MovingUtil.setMotion(0.9);
             count = 0;
         }
     } else {
         count = 0;
         // MovingUtil.setMotion(0);
     }
 }

}
    public void Test() {
        if (PlayerUtil.MovementInput() && this.mc.thePlayer.fallDistance < 1.0f) {
            float direction = this.mc.thePlayer.rotationYaw;
            float x = (float) Math.cos((double) (direction + 90.0f) * 3.141592653589793 / 180.0);
            float z = (float) Math.sin((double) (direction + 90.0f) * 3.141592653589793 / 180.0);
            if (this.mc.thePlayer.isCollidedVertically && PlayerUtil.MovementInput() && this.mc.gameSettings.keyBindJump.pressed) {
                this.mc.thePlayer.motionX = x * 0.29f;
                this.mc.thePlayer.motionZ = z * 0.29f;
            }
            if (this.mc.thePlayer.motionY == 0.33319999363422365 && PlayerUtil.MovementInput()) {
                this.mc.thePlayer.motionX = (double) x * 1.261;
                this.mc.thePlayer.motionZ = (double) z * 1.261;
            }
        }
    }
    public void Blockmc() {
        if ((mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0)) {
            mc.thePlayer.speedInAir = 1.2F * 0.1F;
            mc.timer.timerSpeed = 1F;
            if (!mc.thePlayer.onGround) {
                mc.timer.timerSpeed = 0.9F;
            }
        }
    }

    public static int Value = 0;
    public static boolean jumping = false;

    public void Mineplexlow(double Jumpspeed) {
        if (Value < 25 && mc.thePlayer.onGround) {
            jumping = true;
            if (mc.thePlayer.ticksExisted % 4 == 0) {
                mc.thePlayer.setSpeed(-0.35);
                Value++;
            } else if (mc.thePlayer.ticksExisted % 2 == 0) {
                mc.thePlayer.setSpeed(+0.35);
                Value++;
            }

        } else {

            if (jumping == true) {
                mc.thePlayer.jump();
                Value++;
                jumping = false;
            }

            if (Value <= 47) {
                if (!mc.thePlayer.onGround)
                    mc.thePlayer.setSpeed(Jumpspeed);

                Value++;
            } else {
                if (mc.thePlayer.onGround) {

                    this.toggle();
                }

            }

        }

    }

    public void newredesky() {
        if (!mc.thePlayer.onGround) {
            mc.timer.timerSpeed = 0.6f;
            if ((double) mc.thePlayer.fallDistance > 0.5) {
                this.leftGround = true;
            }
            if (mc.thePlayer.fallDistance >= 1.3f) {
                mc.thePlayer.speedInAir = 0.02f;
            } else {
                mc.thePlayer.speedInAir = 0.14f;
                mc.thePlayer.motionY *= (double) 1.15f;
            }
            return;
        }
        if ((this.done || this.leftGround) && off.getValBoolean()) {
            this.toggle();
            this.done = false;
            this.leftGround = false;
            mc.timer.timerSpeed = 1.0f;
        }
    }

    public int ticks2 = 0;

    public void FastRedesky(int ticks) {

        if (ticks2 < ticks) {
            this.mc.thePlayer.motionY *= 1.07;
            this.mc.thePlayer.motionX *= 0.99;
            this.mc.thePlayer.motionZ *= 0.99;
            this.mc.timer.timerSpeed = 2.0f;
            this.mc.thePlayer.jump();
            ticks2++;
        } else {
            toggle();
            ticks2 = 0;
        }

    }

    public void Guardian() {
        EntityPlayerSP player = this.mc.thePlayer;
        if (!PlayerUtil.MovementInput()) {
            return;
        }
        if (this.mc.thePlayer.onGround) {
            this.lastHDistance = 0;
        }
        float direction = this.mc.thePlayer.rotationYaw + (float) (this.mc.thePlayer.moveForward < 0.0f ? 180 : 0) + (this.mc.thePlayer.moveStrafing > 0.0f ? -90.0f * (this.mc.thePlayer.moveForward > 0.0f ? 0.5f : (this.mc.thePlayer.moveForward < 0.0f ? -0.5f : 1.0f)) : 0.0f) - (this.mc.thePlayer.moveStrafing < 0.0f ? -90.0f * (this.mc.thePlayer.moveForward > 0.0f ? 0.5f : (this.mc.thePlayer.moveForward < 0.0f ? -0.5f : 1.0f)) : 0.0f);
        float xDir = (float) Math.cos((double) (direction + 90.0f) * 3.141592653589793 / 180.0);
        float zDir = (float) Math.sin((double) (direction + 90.0f) * 3.141592653589793 / 180.0);
        if (!this.mc.thePlayer.isCollidedVertically) {
            this.isSpeeding = true;
            this.groundTicks = 0;
            if (!this.mc.thePlayer.isCollidedVertically) {
                if (this.mc.thePlayer.motionY == -0.07190068807140403) {
                    player.motionY *= 0.3499999940395355;
                } else if (this.mc.thePlayer.motionY == -0.10306193759436909) {
                    player.motionY *= 0.550000011920929;
                } else if (this.mc.thePlayer.motionY == -0.13395038817442878) {
                    player.motionY *= 0.6700000166893005;
                } else if (this.mc.thePlayer.motionY == -0.16635183030382) {
                    player.motionY *= 0.6899999976158142;
                } else if (this.mc.thePlayer.motionY == -0.19088711097794803) {
                    player.motionY *= 0.7099999785423279;
                } else if (this.mc.thePlayer.motionY == -0.21121925191528862) {
                    player.motionY *= 0.20000000298023224;
                } else if (this.mc.thePlayer.motionY == -0.11979897632390576) {
                    player.motionY *= 0.9300000071525574;
                } else if (this.mc.thePlayer.motionY == -0.18758479151225355) {
                    player.motionY *= 0.7200000286102295;
                } else if (this.mc.thePlayer.motionY == -0.21075983825251726) {
                    player.motionY *= 0.7599999904632568;
                }
                if (this.mc.thePlayer.motionY < -0.2 && this.mc.thePlayer.motionY > -0.24) {
                    player.motionY *= 0.7;
                }
                if (this.mc.thePlayer.motionY < -0.25 && this.mc.thePlayer.motionY > -0.32) {
                    player.motionY *= 0.8;
                }
                if (this.mc.thePlayer.motionY < -0.35 && this.mc.thePlayer.motionY > -0.8) {
                    player.motionY *= 0.98;
                }
                if (this.mc.thePlayer.motionY < -0.8 && this.mc.thePlayer.motionY > -1.6) {
                    player.motionY *= 0.99;
                }
            }
            this.mc.timer.timerSpeed = 0.8f;
            double[] speedVals = new double[]{0.420606, 0.417924, 0.415258, 0.412609, 0.409977, 0.407361, 0.404761, 0.402178, 0.399611, 0.39706, 0.394525, 0.392, 0.3894, 0.38644, 0.383655, 0.381105, 0.37867, 0.37625, 0.37384, 0.37145, 0.369, 0.3666, 0.3642, 0.3618, 0.35945, 0.357, 0.354, 0.351, 0.348, 0.345, 0.342, 0.339, 0.336, 0.333, 0.33, 0.327, 0.324, 0.321, 0.318, 0.315, 0.312, 0.309, 0.307, 0.305, 0.303, 0.3, 0.297, 0.295, 0.293, 0.291, 0.289, 0.287, 0.285, 0.283, 0.281, 0.279, 0.277, 0.275, 0.273, 0.271, 0.269, 0.267, 0.265, 0.263, 0.261, 0.259, 0.257, 0.255, 0.253, 0.251, 0.249, 0.247, 0.245, 0.243, 0.241, 0.239, 0.237};
            if (this.mc.gameSettings.keyBindForward.pressed) {
                try {
                    this.mc.thePlayer.motionX = (double) xDir * speedVals[this.airTicks - 1] * 3.0;
                    this.mc.thePlayer.motionZ = (double) zDir * speedVals[this.airTicks - 1] * 3.0;
                } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                }
            } else {
                this.mc.thePlayer.motionX = 0.0;
                this.mc.thePlayer.motionZ = 0.0;
            }
        } else {
            this.mc.timer.timerSpeed = 1.0f;
            this.airTicks = 0;
            player.motionX /= 13.0;
            player.motionZ /= 13.0;
            if (this.groundTicks == 1) {
                this.updatePosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ);
                this.updatePosition(this.mc.thePlayer.posX + 0.0624, this.mc.thePlayer.posY, this.mc.thePlayer.posZ);
                this.updatePosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 0.419, this.mc.thePlayer.posZ);
                this.updatePosition(this.mc.thePlayer.posX + 0.0624, this.mc.thePlayer.posY, this.mc.thePlayer.posZ);
                this.updatePosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 0.419, this.mc.thePlayer.posZ);
            } else if (this.groundTicks > 2) {
                this.groundTicks = 0;
                this.mc.thePlayer.motionX = (double) xDir * 0.3;
                this.mc.thePlayer.motionZ = (double) zDir * 0.3;
                this.mc.thePlayer.motionY = 0.42399999499320984;
            }
        }
    }

    public void oldCubecraft() {
        mc.timer.timerSpeed = MovingUtil.isOnGround(0.001) ? 0.75f : 0.6f;
        jumpmotion++;
        if (jumpmotion == 1) {
            mc.thePlayer.jump();
        }

        if (PlayerUtil.isMoving2()) {

            count++;
            if (count == 1) {

                MovingUtil.setMotion(1.9);
            } else if (count == 2) {
                MovingUtil.setMotion(0);
                if (!MovingUtil.isOnGround(0.001)) {
                    count = 0;
                } else {
                    mc.timer.timerSpeed = 1;
                }
            } else if (count >= 3) {
                MovingUtil.setMotion(0);
                count = 0;
            }
        } else {
            count = 0;
            MovingUtil.setMotion(0);
        }
    }

    public void Custom() {
        float x2 = 1f + MovingUtil.getSpeedEffect() * 0.45f;
        if ((mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0) && mc.thePlayer.onGround) {

            if (mc.thePlayer.onGround && state == 3) {
                toggle();
                return;
            }
            if (mc.thePlayer.onGround && state <= 3) {
                mc.thePlayer.jump();

                state = 1;
            }
            groundTicks++;
            mc.thePlayer.motionX *= 1;
            mc.thePlayer.motionZ *= 1;
            mc.thePlayer.jump();
        }
        if (mc.thePlayer.onGround && BlockUtils.isOnGround(0.01)) {
            air = 0;
        } else {
            mc.thePlayer.motionX *= 0;
            mc.thePlayer.motionZ *= 0;
            float speed = (((Number) boost.getValInt()).floatValue() + MovingUtil.getSpeedEffect() * 0.2f) - air / 25;
            mc.thePlayer.jumpMovementFactor = speed > 0.28f ? speed : 0.28f;
            air += x2 * ((Number) boost.getValInt()).floatValue() * 2;
        }
    }

    public void NCP() {
        mc.thePlayer.lastReportedPosY = 0;
        float x2 = 1f + MovingUtil.getSpeedEffect() * 0.45f;
        if ((mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0) && mc.thePlayer.onGround) {
            if (off.getValBoolean() && groundTicks > 50) {
                PlayerUtils.sendMessage(groundTicks);
                groundTicks = 0;
                this.toggle();
                return;
            }
            stage = 1;
            groundTicks++;

            mc.thePlayer.jump();

        }
        if (MovingUtil.isOnGround(0.01)) {
            air = 0;
        } else {
            if (mc.thePlayer.isCollidedVertically)
                stage = 0;
            if (stage > 0 && stage <= 3 && Glide.getValBoolean()) {
                //if(mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer,mc.thePlayer.boundingBox.expand(-0.3, -2, -0.3).expand(0.3, 0, 0.3)).isEmpty()){
                mc.thePlayer.onGround = true;
                //	}

                //mc.thePlayer.isCollidedVertically = false;
            }
            double speed = (0.75f + MovingUtil.getSpeedEffect() * 0.2f) - air / 25;
            if (speed < MovingUtil.defaultSpeed()) { // + (0.025*MoveUtils.getSpeedEffect())
                speed = MovingUtil.defaultSpeed();
            }
            if (Glide.getValBoolean()) {
                speed = (0.8f + MovingUtil.getSpeedEffect() * 0.2f) - air / 25;
                if (speed < MovingUtil.defaultSpeed()) { // + (0.025*MoveUtils.getSpeedEffect())
                    speed = MovingUtil.defaultSpeed();
                }
            }
            mc.thePlayer.jumpMovementFactor = 0;
            if (stage < 4 && Glide.getValBoolean())
                speed = MovingUtil.defaultSpeed();
            MovingUtil.setMotion(speed);
            if (Glide.getValBoolean()) {
                mc.thePlayer.motionY = getMotion(stage);
            } else {
                mc.thePlayer.motionY = getOldMotion(stage);
            }
            if (mc.thePlayer.onGround && state == 3) {
                toggle();
                return;
            }
            if (mc.thePlayer.onGround && state <= 3) {
                mc.thePlayer.jump();

                state = 1;
            }
        }

    }

    public void Schadenundfly() {
        if (mc.thePlayer.onGround) {
            if (disableState == 1) {
                toggle();
                AntiBan.Flagcounter = 0;
                return;
            }
            if (damageState == 0) {
                if (Client.getInstance().getModuleManager().getModuleByName("Nofall").isEnabled() && Nofall.getValBoolean())
                    Client.getInstance().getModuleManager().getModuleByName("Nofall").toggle();
                NetHandlerPlayClient netHandlerPlayClient = Minecraft.getMinecraft().getNetHandler();
                double x = mc.thePlayer.posX;
                double z = mc.thePlayer.posZ;
                double y = mc.thePlayer.posY;
                for (int i = 0; i < 80; ++i) {
                    netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 0.060100000351667404, z, false));
                    netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 01000237487257E-1, z, false));
                    netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 0.00499999888241191 + 1.0100003516674E-1, z, false));
                }
                netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer(true));
                damageState = 1;
            }
            mc.thePlayer.jump();
        } else {
            if (state < 17 && mc.thePlayer.hurtTime > 0.1) {
                Jump(0.1, -0.02, 500, true, 19.5F, 4F, 1.9f, 1);
                Jump(0.1, -0.02, 500, true, 19.5F, 4F, 1.3f, 1);
                Jump(0.1, -0.02, 500, true, 19.5F, 4F, 0.7f, 1);
                Jump(0.1, -0.02, 500, true, 19.5F, 4F, 0.8f, 1);
                Jump(0.1, -0.02, 500, true, 19.5F, 4F, 1f, 1);
                disableState = 1;
                DamageSource.hungerDamage = 0F;
                state++;
            } else {
                //    toggle();
            }
        }
    }


    private int toggleState = 0;
    public static final TimeHelper time = new TimeHelper();

    public void Jump(double hight, double move, int time, boolean jump, float movespeed, float strafing, float Timerspeed, double Pitchspeed) {
        if (jump == true) {
            mc.thePlayer.jump();
            mc.thePlayer.jump();
            mc.thePlayer.jump();
            mc.thePlayer.jump();
        }
        if (!mc.thePlayer.onGround && !Client.getInstance().getModuleManager().getModuleByName("Nofall").isEnabled() && Nofall.getValBoolean()) {
            Client.getInstance().getModuleManager().getModuleByName("Nofall").toggle();

        }


        //eigentlicher jump
        double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
        double pitch = mc.thePlayer.posY;
        double x = -Math.sin(yaw) * 0.5;
        double z = Math.cos(yaw) * 0.5;
        double y = pitch * 0.008;
        float timer = Timerspeed;


      /*
        mc.thePlayer.rotationPitchHead = 90;
        mc.thePlayer.rotationPitch = 90;
        mc.thePlayer.rotationYaw = 90;
*/
        mc.thePlayer.motionX = x;
        mc.thePlayer.motionZ = z;
        mc.thePlayer.motionY = y;
        mc.thePlayer.moveForward *= movespeed;
        mc.thePlayer.moveStrafing *= strafing;
        mc.timer.timerSpeed = timer;

        mc.timer.timerSpeed = timer;


        //
        //begrenzung
        double aktuelleY = mc.thePlayer.posY;
        if (aktuelleY >= aktuelleY + 9) {
            double yaww = Math.toRadians(mc.thePlayer.rotationYaw);
            double pitchc = mc.thePlayer.posY;
            double xx = -Math.sin(yaw) * hight;
            double zz = Math.cos(yaw) * hight;
            double yy = mc.thePlayer.motionY = -0.001;

            mc.thePlayer.motionX = xx;
            mc.thePlayer.motionZ = zz;
            mc.thePlayer.motionY = yy;
            mc.thePlayer.moveForward *= movespeed;
            mc.thePlayer.moveStrafing *= strafing;


        }
        if (mc.thePlayer.onGround && state == 3) {
            toggle();
            return;
        }
        if (mc.thePlayer.onGround && state <= 3) {
            mc.thePlayer.jump();

            state = 1;
        }


    }

    public static boolean Groundstand;

    public void Groundcheck() {


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

    public void Cubecraft() {
        mc.timer.timerSpeed = 0.3F;
        float x2 = 1.0F + getSpeedEffect() * 0.45F;
        if (mc.thePlayer.onGround) {
            double x = mc.thePlayer.posX, y = mc.thePlayer.posY, z = mc.thePlayer.posZ;
            for (int index = 0; index < 49; index++) {
                mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet) new C03PacketPlayer.C04PacketPlayerPosition(x, y + 0.0625D, z, false));
                mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet) new C03PacketPlayer.C04PacketPlayerPosition(x, y, z, false));
            }
            mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet) new C03PacketPlayer.C04PacketPlayerPosition(x, y, z, true));
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
            mc.thePlayer.jumpMovementFactor = (float) ((speed > 0.2800000011920929D) ? speed : 0.2800000011920929D);
            this.air = (float) (this.air + x2 * 1.0D * 2.0D);
        }
        this.stage = -999;
        if (mc.thePlayer.isCollidedVertically)
            this.stage = -2;
    }

    public void Mineplex() {
        mc.thePlayer.cameraYaw = 0.18181817F;
        if (!mc.thePlayer.onGround && this.air > 0.0F) {
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
            this.half = (mc.thePlayer.posY != (int) mc.thePlayer.posY);
            mc.thePlayer.motionY = 0.4299999D;
            this.air = 1.0F;
            this.motionY = mc.thePlayer.motionY;
        }
    }

    @EventTarget
    public int getSpeedEffect() {
        return mc.thePlayer.isPotionActive(Potion.moveSpeed) ? (mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1) : 0;
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
        double[] mot = {0.359D, 0.273D, 0.201D, 0.129D, 0.057D, -0.015D, -0.087D, -0.159D};
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

    @Override
    public void onDisable() {
        super.onDisable();
        mc.thePlayer.motionX = 0.0D;
        mc.thePlayer.motionZ = 0.0D;
        mc.thePlayer.setSpeed(0.0D);
        mc.timer.timerSpeed = 1.0F;
        mc.thePlayer.capabilities.allowFlying = false;

        damageState = 0;
        disableState = 0;
        Value = 0;
        mc.thePlayer.capabilities.isFlying = false;
        tick = 0;
        toggleState = 0;
        Ground = 0;
        state = 0;
        onground = false;
        groundTicks = 0;
        mc.timer.timerSpeed = 1f;
        maxhight = false;
        tick = 0;
        Damage = false;
        MovingUtil.setMotion(0.2);
        jumpmotion = 0;

    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (mode1.getValString().equalsIgnoreCase("Spartan")) {
            PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Bitte baue dich ein Paar Blöcke Hoch.");
        }
    }

    double getOldMotion(int stage) {
        boolean isMoving = (mc.thePlayer.moveStrafing != 0 || mc.thePlayer.moveForward != 0);
        double[] mot = {0.345, 0.2699, 0.183, 0.103, 0.024, -0.008, -0.04, -0.072, -0.104, -0.13, -0.019, -0.097};
        double[] notMoving = {0.345, 0.2699, 0.183, 0.103, 0.024, -0.008, -0.04, -0.072, -0.14, -0.17, -0.019, -0.13};
        stage--;
        if (stage >= 0 && stage < mot.length) {
            if ((mc.thePlayer.moveStrafing == 0 && mc.thePlayer.moveForward == 0) || mc.thePlayer.isCollidedHorizontally) {
                return notMoving[stage];
            }
            return mot[stage];
        } else {
            return mc.thePlayer.motionY;
        }
    }

    public void updatePosition(double x, double y, double z) {
        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y, z, this.mc.thePlayer.onGround));
    }
}
