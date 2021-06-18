package me.bratwurst.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class FTools_RainbowButtons
        extends FTools_AbstractButtonDesign{

    public FTools_RainbowButtons() {
        super("Rainbow");
    }

    @Override
    public void renderButton(FTools_AbstractButtonDesign.ButtonInfo buttonInfo) {
        if (!buttonInfo.isVisible()) {
            return;
        }
        FontRenderer fontRenderer = Minecraft.fontRendererObj;
        int x = buttonInfo.getX();
        int y = buttonInfo.getY();
        int width = buttonInfo.getWidth();
        int height = buttonInfo.getHeight();
        int background = buttonInfo.isActive() ? (buttonInfo.getGuiButton().isMouseOver() ? -872415232 : Integer.MIN_VALUE) : -868467652;
        Gui.drawRect(x, y, x + width, y + height, background);
        FTools_RenderUtils.drawRainbowRectBorder(x, y, x + width, y + height, 1);
        buttonInfo.getGuiButton().mousePressed(Minecraft.getMinecraft(), buttonInfo.getMouseX(), buttonInfo.getMouseY());
        Gui.drawCenteredString(fontRenderer, buttonInfo.getMessage(), x + width / 2, y + (height - fontRenderer.FONT_HEIGHT) / 2 + 1, -1);
    }
}

