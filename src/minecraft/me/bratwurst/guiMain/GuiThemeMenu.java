package me.bratwurst.guiMain;

import me.bratwurst.manager.PartikelSystem.ParticleSystem;
import me.bratwurst.utils.DrawMenuLogoUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class GuiThemeMenu extends GuiScreen {
    ParticleSystem partikelsystem = new ParticleSystem(1000,230);
    public static boolean shadetheme = true;
    public static boolean normal = false;
    public static boolean particle = false;
    public static boolean old = false;
    public static boolean old2 = false;
    public void initGui() {
        int i = 24;
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
        this.buttonList.add(new GuiButton(1000, this.width / 2 - 100, this.height / 6 + 48 - 6, I18n.format("Normal", new Object[0])));
        this.buttonList.add(new GuiButton(1001, this.width / 2 - 100, this.height / 6 + 48 +i - 6, I18n.format("Shader", new Object[0])));
        this.buttonList.add(new GuiButton(1002, this.width / 2 - 100, this.height / 6 + 48 +i *2  - 6, I18n.format("Particle", new Object[0])));
        this.buttonList.add(new GuiButton(1003, this.width / 2 - 100, this.height / 6 + 48 +i *3  - 6, I18n.format("Old", new Object[0])));
        this.buttonList.add(new GuiButton(1004, this.width / 2 - 100, this.height / 6 + 48 +i *4  - 6, I18n.format("Old2", new Object[0])));


    }
    public static int clicked = 1;
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if(button.id == 200){
            mc.displayGuiScreen(new GuiMainMenu());
        }
        if(button.id == 1000){
            shadetheme = false;
            normal = true;
            particle = false;
            old = false;
            old2 = false;
            mc.displayGuiScreen(new GuiMainMenu());
        }
        if(button.id == 1001){
            shadetheme = true;
            normal = false;
            particle = false;
            old = false;
            old2 = false;
            mc.displayGuiScreen(new GuiMainMenu());
        }
        if(button.id == 1002){
            shadetheme = false;
            normal = false;
            particle = true;
            old = false;
            old2 = false;
            mc.displayGuiScreen(new GuiMainMenu());
        }
        if(button.id == 1003){
            shadetheme = false;
            normal = false;
            particle = false;
            old = true;
            old2 = false;
            mc.displayGuiScreen(new GuiMainMenu());
        }
        if(button.id == 1004){
            shadetheme = false;
            normal = false;
            particle = false;
            old = false;
            old2 = true;
            mc.displayGuiScreen(new GuiMainMenu());
        }

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        //Hintergrund
        this.mc.getTextureManager().bindTexture(new ResourceLocation("client/336293.png"));
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
        render();
        //backScreen
        drawRect(this.width / 9 - 15, this.height / 6 - 25, this.width - 50, this.height / 2 + this.height / 3, new Color(56, 56, 56, 255).getRGB());
        final String Logo = "Theme";
        DrawMenuLogoUtil.drawString(3, Logo, this.width / 7, this.height / 20, Color.CYAN.getRGB());



        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    public void render() {
        partikelsystem.render();
        partikelsystem.tick(15);
    }
}
