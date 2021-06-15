package me.bratwurst.cosmetics;

import me.bratwurst.Client;
import me.bratwurst.cosmetics.profile.DragonWingsProfile;
import me.bratwurst.module.Commands.CosmeticsCommand;
import me.bratwurst.utils.ColorUtil;
;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.util.ResourceLocation;

public class CosmeticWings extends Cosmetic {

    private ModelRenderer wing;

    private ModelRenderer wingTip;

    private RenderPlayer playerRenderer;

    public CosmeticWings(RenderPlayer player) {
        super(player);
        this.playerRenderer = player;
        this.textureWidth = 256;
        this.textureHeight = 256;
        setTextureOffset("wing.skin", -56, 88);
        setTextureOffset("wing.bone", 112, 88);
        setTextureOffset("wingtip.skin", -56, 144);
        setTextureOffset("wingtip.bone", 112, 136);
        this.wing = new ModelRenderer((ModelBase)this, "wing");
        this.wing.setTextureSize(256, 256);
        this.wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
        this.wing.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
        this.wing.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
        this.wing.isHidden = true;
        this.wingTip = new ModelRenderer((ModelBase)this, "wingtip");
        this.wingTip.setTextureSize(256, 256);
        this.wingTip.setRotationPoint(-56.0F, 0.0F, 0.0F);
        this.wingTip.isHidden = true;
        this.wingTip.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
        this.wingTip.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
        this.wing.addChild(this.wingTip);
    }

    public boolean shouldCombineTextures() {
        return true;
    }

    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale) {
        if(CosmeticsCommand.cos != true && CosmeticsCommand.win != true){
            return;
        }

            GlStateManager.pushMatrix();
            float f1 = ageInTicks / 75.0F;
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("client/cosmetics/dragon.png"));

            GlStateManager.scale(0.16D, 0.16D, 0.16D);
            GlStateManager.translate(0.0D, -0.3D, 1.1D);
            GlStateManager.rotate(50.0F, -50.0F, 0.0F, 0.0F);
            GlStateManager.blendFunc(1, 1);

            if (player.isSneaking())
                GlStateManager.translate(0.0F, 0.142F, 1.2F);
            for (int i = 0; i < 2; i++) {
                float f6 = f1 * 9.141593F * 1.2F;
                this.wing.rotateAngleX = 0.125F - (float)Math.cos(f6) * 0.2F;
                this.wing.rotateAngleY = 0.25F;
                this.wing.rotateAngleZ = (float)(Math.sin(f6) + 1.225D) * 0.45F;
                this.wingTip.rotateAngleZ = -((float)(Math.sin((f6 + 2.0F)) + 0.5D)) * 0.95F;
                this.wing.isHidden = false;
                this.wingTip.isHidden = false;
                this.wing.render(scale);
                this.wing.isHidden = true;
                this.wingTip.isHidden = true;
                if (i == 0)
                    GlStateManager.scale(-1.0F, 1.0F, 1.0F);
            }
            GlStateManager.blendFunc(0, 0);
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();

    }
}

