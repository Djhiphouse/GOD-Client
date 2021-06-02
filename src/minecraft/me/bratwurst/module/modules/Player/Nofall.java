package me.bratwurst.module.modules.Player;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

import java.sql.Time;
import java.util.ArrayList;

public class Nofall extends Module {
    private int fallDistance = 3;
    public Setting Bypass, Speed, Speedup, Speeddown;
    public static Setting mode1;

    public Nofall() {

        super("Nofall", Category.PLAYER);
        ArrayList<String> options = new ArrayList<>();

        options.add("Vanilla");
        options.add("AAC");
        options.add("Jartex");
        options.add("AAC 3.3.1");
        options.add("Cubecraft");
        options.add("Intave");

        Client.setmgr.rSetting(mode1 = new Setting("Nofall Mode", this, "Vanilla", options));
    }

    @EventTarget

    public void onUpdate(EventUpdate event) {

        if (mode1.getValString().equalsIgnoreCase("Vanilla")) {
            Vanilla();

        }else  if (mode1.getValString().equalsIgnoreCase("AAC")) {
            AAC();

        }else  if (mode1.getValString().equalsIgnoreCase("Jartex")) {
           Jartex();

        }else  if (mode1.getValString().equalsIgnoreCase("AAC 3.3.1")) {
            AAC2();

        }else  if (mode1.getValString().equalsIgnoreCase("Cubecraft")) {
            Cubecraft();

        }else  if (mode1.getValString().equalsIgnoreCase("Intave")) {
            Intave();

        }
    }

    public void Vanilla() {
        if (!Client.getInstance().getModuleManager().getModuleByName("Glide").isToggle()) {
            if (mc.thePlayer.fallDistance > 2F) {
                Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
            }
        } else {

        }

    }
    public void AAC() {
        mc.thePlayer.motionZ = 0.0D;
        mc.thePlayer.motionX = 0.0D;
        mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY - 0.001D, mc.thePlayer.posZ, mc.thePlayer.onGround));
        mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer(true));
    }
    public static  int falldistance = 3;
    public void Jartex() {
        if (mc.thePlayer.fallDistance > fallDistance) {

            mc.thePlayer.motionX = 0.0D;

            mc.thePlayer.motionZ = 0.0D;
            mc.thePlayer.setSpeed(0.0D);

            mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer(true));
        }
    }
    public void AAC2() {
        if (mc.thePlayer.fallDistance >= fallDistance) {
            mc.thePlayer.motionY = -6;
        }
    }
    public void Cubecraft() {
        final Minecraft mc = Nofall.mc;
        if (mc.thePlayer.fallDistance > 3.5f) {
            final Minecraft mc2 = Nofall.mc;
            mc.thePlayer.capabilities.isFlying = true;
            final Minecraft mc3 = Nofall.mc;
            mc.thePlayer.onGround = true;
        }
    }
    public void  Intave() {
        final Minecraft mc = Nofall.mc;
        if (mc.thePlayer.fallDistance > 3.8f) {
            final Minecraft mc2 = Nofall.mc;
            mc.thePlayer.capabilities.isFlying = true;
            final Minecraft mc3 = Nofall.mc;
            mc.thePlayer.onGround = true;
            Nofall.mc.timer.timerSpeed = 1.05f;
            if (TimeHelper.hasReached(60L)) {
                final Minecraft mc4 = Nofall.mc;
                mc.thePlayer.onGround = true;
                TimeHelper.reset();
            }
            else {
                final Minecraft mc5 = Nofall.mc;
                mc.thePlayer.onGround = false;
            }
        }
        else {
            final Minecraft mc6 = Nofall.mc;
            mc.thePlayer.capabilities.isFlying = false;
        }
    }
}


