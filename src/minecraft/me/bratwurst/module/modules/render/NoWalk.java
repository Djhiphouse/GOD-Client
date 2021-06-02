package me.bratwurst.module.modules.render;

import com.google.common.base.Stopwatch;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.module.modules.combat.Aura;
import me.bratwurst.utils.*;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class NoWalk extends Module {



    public NoWalk() {
        super("NoWalk", Category.RENDER);
    }
    private static final Color COLOR = new Color(0, 0, 0, 180);

    private EntityOtherPlayerMP target;
    private double healthBarWidth;
    private double hudHeight;
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {

    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.gameSettings.viewBobbing = true;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.gameSettings.viewBobbing = false;
    }
}
