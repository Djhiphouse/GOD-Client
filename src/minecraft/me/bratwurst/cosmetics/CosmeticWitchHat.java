package me.bratwurst.cosmetics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class CosmeticWitchHat extends Cosmetic {
    public ModelRenderer witchHat;

    private final ResourceLocation witchTextures = new ResourceLocation("textures/entity/witch.png");

    public CosmeticWitchHat(RenderPlayer player) {
        super(player);
        this.witchHat = (new ModelRenderer((ModelBase)this)).setTextureSize(64, 128);
        this.witchHat.setRotationPoint(-5.0F, -10.03125F, -5.0F);
        this.witchHat.setTextureOffset(0, 64).addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);
        ModelRenderer modelrenderer = (new ModelRenderer((ModelBase)this)).setTextureSize(64, 128);
        modelrenderer.setRotationPoint(1.75F, -4.0F, 2.0F);
        modelrenderer.setTextureOffset(0, 76).addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
        modelrenderer.rotateAngleX = -0.05235988F;
        modelrenderer.rotateAngleZ = 0.02617994F;
        this.witchHat.addChild(modelrenderer);
        ModelRenderer modelrenderer1 = (new ModelRenderer((ModelBase)this)).setTextureSize(64, 128);
        modelrenderer1.setRotationPoint(1.75F, -4.0F, 2.0F);
        modelrenderer1.setTextureOffset(0, 87).addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
        modelrenderer1.rotateAngleX = -0.10471976F;
        modelrenderer1.rotateAngleZ = 0.05235988F;
        modelrenderer.addChild(modelrenderer1);
        ModelRenderer modelrenderer2 = (new ModelRenderer((ModelBase)this)).setTextureSize(64, 128);
        modelrenderer2.setRotationPoint(1.75F, -2.0F, 2.0F);
        modelrenderer2.setTextureOffset(0, 95).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
        modelrenderer2.rotateAngleX = -0.20943952F;
        modelrenderer2.rotateAngleZ = 0.10471976F;
        modelrenderer1.addChild(modelrenderer2);
        this.witchHat.isHidden = true;
        this.playerModel.bipedHead.addChild(this.witchHat);
    }

    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale) {


            GlStateManager.pushMatrix();
            float f = partialTicks;
            float f1 = getFirstRotationX(player, f);
            float f2 = getSecondRotationX(player, f);
            Minecraft.getMinecraft().getTextureManager().bindTexture(this.witchTextures);

            if (player.isSneaking())
                GlStateManager.translate(0.0F, 0.27F, 0.0F);
            GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
            this.witchHat.isHidden = false;
            this.witchHat.render(scale);
            this.witchHat.isHidden = true;
            GlStateManager.popMatrix();

    }

    private float getFirstRotationX(AbstractClientPlayer Player, float partialTicks) {
        float f = interpolateRotation(Player.prevRenderYawOffset, Player.renderYawOffset, partialTicks);
        float f1 = interpolateRotation(Player.prevRotationYawHead, Player.rotationYawHead, partialTicks);
        float f2 = f1 - f;
        if (Player.isRiding() && Player.ridingEntity instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)Player.ridingEntity;
            f = interpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partialTicks);
            f2 = f1 - f;
            float f3 = MathHelper.wrapAngleTo180_float(f2);
            if (f3 < -85.0F)
                f3 = -85.0F;
            if (f3 >= 85.0F)
                f3 = 85.0F;
            f = f1 - f3;
            if (f3 * f3 > 2500.0F) {
                float f4 = f + f3 * 0.2F;
            }

        }
        return f2;
    }

    private float getSecondRotationX(AbstractClientPlayer Player, float partialTicks) {
        return Player.prevRotationPitch + (Player.rotationPitch - Player.prevRotationPitch) * partialTicks;
    }

    private float interpolateRotation(float par1, float par2, float par3) {
        float f;
        for (f = par2 - par1; f < -180.0F; f += 360.0F);
        while (f >= 180.0F)
            f -= 360.0F;
        return par1 + par3 * f;
    }
}

