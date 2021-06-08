package me.bratwurst.module.modules.Crasher;

import de.Hero.settings.Setting;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C12PacketUpdateSign;

import java.util.ArrayList;

public class GodMode extends Module {
    private Object Packet;
    public static Setting mode1;

    public GodMode() {
        super("GodMode", Category.EXPLOIT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Vanilla");
        options.add("Normal");
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate event) {
        if (mode1.getValString().equalsIgnoreCase("Vanilla")) {
            GodModeVanilla();
        }

    }
    public void GodModeVanilla() {
        if (mc.thePlayer.getHealth() <= 0) {


            if (mc.currentScreen instanceof GuiGameOver) {
                mc.currentScreen = null;
            }
        }
    }
    public void GodMode() {

    }
}
