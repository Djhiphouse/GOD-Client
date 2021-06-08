package me.bratwurst.module.modules.render;

import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.EventRender2D;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.module.modules.combat.Aura;
import me.bratwurst.utils.ColorUtils;
import me.bratwurst.utils.REderUtIl;

import me.bratwurst.utils.TimeHelper;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class TargetHud extends Module {
    private double healthBarWidth;
    private double hudHeight;
    private EntityOtherPlayerMP target1;
    public EntityLivingBase lastEnt;
    public float lastHealth;
    public float damageDelt;
    public TargetHud() {
        super("TargetHud", Category.RENDER);
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        PlayerUtils.sendMessage("oncalled");
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        final float scaledWidth = sr.getScaledWidth();
        final float scaledHeight = sr.getScaledHeight();
        if (Aura.target1 != null ) {
            if (Aura.target1 instanceof EntityOtherPlayerMP) {
                EntityPlayer target = (EntityPlayer) Aura.target1;
                float healthPercentage = target.getHealth() / target.getMaxHealth();
                float startX = 20;
                float renderX = (sr.getScaledWidth() / 2) + startX;
                float renderY = (sr.getScaledHeight() / 2) + 10;
                int maxX2 = 30;
                if (target.getCurrentArmor(3) != null) {
                    maxX2 += 15;
                }
                if (target.getCurrentArmor(2) != null) {
                    maxX2 += 15;
                }
                if (target.getCurrentArmor(1) != null) {
                    maxX2 += 15;
                }
                if (target.getCurrentArmor(0) != null) {
                    maxX2 += 15;
                }
                if (target.getHeldItem() != null) {
                    maxX2 += 15;
                }
                final int healthColor = ColorUtils.getHealthColor(target.getHealth(), target.getMaxHealth())
                        .getRGB();
                float maxX = Math.max(maxX2, Minecraft.fontRendererObj.getStringWidth(target.getName()) + 30);
                Gui.drawRect(renderX, renderY, renderX + maxX, renderY + 40, new Color(0, 0, 0, 0.3f).getRGB());
                Gui.drawRect(renderX, renderY + 38, renderX + (maxX * healthPercentage), renderY + 40, healthColor);
                Minecraft.fontRendererObj.drawStringWithShadow(target.getName(), renderX + 25, renderY + 7, -1);
                int xAdd = 0;
                double multiplier = 0.85;
                GlStateManager.pushMatrix();
                GlStateManager.scale(multiplier, multiplier, multiplier);
                PlayerUtils.sendMessage("Draw");
                if (target.getCurrentArmor(3) != null) {
                    mc.getRenderItem().renderItemAndEffectIntoGUI(target.getCurrentArmor(3), (int) ((((sr.getScaledWidth() / 2) + startX + 23) + xAdd) / multiplier), (int) (((sr.getScaledHeight() / 2) + 28) / multiplier));
                    xAdd += 15;
                }
                if (target.getCurrentArmor(2) != null) {
                    mc.getRenderItem().renderItemAndEffectIntoGUI(target.getCurrentArmor(2), (int) ((((sr.getScaledWidth() / 2) + startX + 23) + xAdd) / multiplier), (int) (((sr.getScaledHeight() / 2) + 28) / multiplier));
                    xAdd += 15;
                }
                if (target.getCurrentArmor(1) != null) {
                    mc.getRenderItem().renderItemAndEffectIntoGUI(target.getCurrentArmor(1), (int) ((((sr.getScaledWidth() / 2) + startX + 23) + xAdd) / multiplier), (int) (((sr.getScaledHeight() / 2) + 28) / multiplier));
                    xAdd += 15;
                }
                if (target.getCurrentArmor(0) != null) {
                    mc.getRenderItem().renderItemAndEffectIntoGUI(target.getCurrentArmor(0), (int) ((((sr.getScaledWidth() / 2) + startX + 23) + xAdd) / multiplier), (int) (((sr.getScaledHeight() / 2) + 28) / multiplier));
                    xAdd += 15;
                }
                if (target.getHeldItem() != null) {
                    mc.getRenderItem().renderItemAndEffectIntoGUI(target.getHeldItem(), (int) ((((sr.getScaledWidth() / 2) + startX + 23) + xAdd) / multiplier), (int) (((sr.getScaledHeight() / 2) + 28) / multiplier));
                }
                GlStateManager.popMatrix();
                GuiInventory.drawEntityOnScreen((int) renderX + 12, (int) renderY + 33, 15, target.rotationYaw, target.rotationPitch, target);
            } else {
                healthBarWidth = 92.0;
                this.hudHeight = 0.0;
                target1 = null;
            }
        }

    }
}
