package me.bratwurst.cosmetics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public abstract class Cosmetic extends ModelBiped implements LayerRenderer<AbstractClientPlayer> {
    ModelBiped playerModel;

    public Cosmetic(RenderPlayer player) {
        this.playerModel = (ModelBiped)player.getMainModel();
    }

    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale) {
        if (Minecraft.getMinecraft().thePlayer != null &&
                player.hasPlayerInfo() && !player.isInvisible())
            render(player, limbSwing, limbSwingAmount, partialTicks, ageInTicks, headYaw, headPitch, scale);
    }

    public boolean shouldCombineTextures() {
        return false;
    }

    public abstract void render(AbstractClientPlayer paramAbstractClientPlayer, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7);
}

