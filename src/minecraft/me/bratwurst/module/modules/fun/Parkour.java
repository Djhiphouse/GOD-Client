package me.bratwurst.module.modules.fun;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;

public class Parkour extends Module {
    public Setting Bypass, Speed, Speedup, Speeddown;
    public static Setting mode1;

    public Parkour() {
        super("Parkour", Category.WORLD);
        ArrayList<String> options = new ArrayList<>();
        options.add("jump");
        options.add("Packet");

        Client.setmgr.rSetting(mode1 = new Setting("Jump Mode", this, "Vanilla", options));
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
       if (mode1.getValString().equalsIgnoreCase("jump")) {
           jump();
       }else if (mode1.getValString().equalsIgnoreCase("Packet")) {
           Packet();
       }
    }

    public void jump() {
        final Minecraft mc = Parkour.mc;
        final double posX = mc.thePlayer.posX;
        final Minecraft mc2 = Parkour.mc;
        final double y = mc.thePlayer.posY - 1.0;
        final Minecraft mc3 = Parkour.mc;
        final BlockPos pos = new BlockPos(posX, y, mc.thePlayer.posZ);
        if (Parkour.mc.theWorld.getBlockState(pos).getBlock() == Blocks.air) {
            final Minecraft mc4 = Parkour.mc;
            if (mc.thePlayer.onGround) {
                final Minecraft mc5 = Parkour.mc;
                mc.thePlayer.jump();
                final Minecraft mc6 = Parkour.mc;
                mc.thePlayer.jump();
            }
        }
    }

    public void Packet() {
        final Minecraft mc = Parkour.mc;
        final double posX = mc.thePlayer.posX;
        final Minecraft mc2 = Parkour.mc;
        final double y = mc.thePlayer.posY - 1.0;
        final Minecraft mc3 = Parkour.mc;
        final BlockPos pos = new BlockPos(posX, y, mc.thePlayer.posZ);
        if (Parkour.mc.theWorld.getBlockState(pos).getBlock() == Blocks.air) {
            final Minecraft mc4 = Parkour.mc;
            if (mc.thePlayer.onGround) {
                final Minecraft mc5 = Parkour.mc;
                mc.thePlayer.jump();
                final Minecraft mc6 = Parkour.mc;
                mc.thePlayer.motionY = 0.4300000007157268;
            }
        }
    }
}
