package me.bratwurst.utils;

import me.bratwurst.event.Event;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.List;

public final class RenderGuiEvent extends Event {
    private final ScaledResolution scaledResolution;
    private final float partialTicks;

    public RenderGuiEvent(ScaledResolution scaledResolution, float partialTicks) {
        this.scaledResolution = scaledResolution;
        this.partialTicks = partialTicks;
    }

    public ScaledResolution getScaledResolution() {
        return this.scaledResolution;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }
}
