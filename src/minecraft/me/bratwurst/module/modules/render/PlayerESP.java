package me.bratwurst.module.modules.render;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class PlayerESP extends Module {
    public Setting Packets, Bypass, Range, MoveFix;
    public static Setting mode1;

    public PlayerESP() {
        super("ShaderESP", Category.RENDER);
        ArrayList<String> options = new ArrayList<>();

    }

    public static boolean OnESP = false;

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {

    }


}
