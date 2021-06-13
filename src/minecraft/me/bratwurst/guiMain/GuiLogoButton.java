package me.bratwurst.guiMain;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiLogoButton extends GuiButtonGod {
    ScaledResolutionGod scaledResolution;

    ResourceLocation location;

    boolean isHead;

    public GuiLogoButton(int buttonId, ScaledResolutionGod scaledResolutionGod, int x, int y, int widthIn, int heightIn, ResourceLocation location, boolean isHead) {
        super(buttonId, x, y, widthIn, heightIn, "");
        this.scaledResolution = scaledResolutionGod;
        this.location = location;
        this.isHead = isHead;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            boolean flag = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
            int i = 106;
            if (flag)
                i += this.height;
            mc.getTextureManager().bindTexture(this.location);
            if (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height) {
                if (this.isHead) {
                    GL11.glPushMatrix();
                    GL11.glScissor(0, (int)(575.0D / this.scaledResolution.getScale()) * this.scaledResolution.getScaleFactor(), (int)(62.0D / this.scaledResolution.getScale()) * this.scaledResolution.getScaleFactor() + 2, 100);
                    GL11.glEnable(3089);
                }
                Gui.drawScaledCustomSizeModalRect(this.xPosition, this.yPosition, 0.0F, 0.0F, this.width + 2, this.height + 2, this.width + 2, this.height + 2, (this.width + 2), (this.height + 2));
            } else {
                if (this.isHead) {
                    GL11.glPushMatrix();
                    GL11.glScissor(0, (int)(580.0D / this.scaledResolution.getScale()) * this.scaledResolution.getScaleFactor(), (int)(60.0D / this.scaledResolution.getScale()) * this.scaledResolution.getScaleFactor() + 2, 100);
                    GL11.glEnable(3089);
                }
                Gui.drawScaledCustomSizeModalRect(this.xPosition, this.yPosition, 0.0F, 0.0F, this.width, this.height, this.width, this.height, this.width, this.height);
            }
            if (this.isHead) {
                GL11.glDisable(3089);
                GL11.glPopMatrix();
            }
        }
    }
}
