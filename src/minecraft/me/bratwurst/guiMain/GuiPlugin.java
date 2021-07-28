package me.bratwurst.guiMain;

import me.bratwurst.manager.PartikelSystem.ParticleSystem;
import me.bratwurst.utils.DrawMenuLogoUtil;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class GuiPlugin extends GuiScreen {
    ParticleSystem partikelsystem = new ParticleSystem(1000,230);
    public static boolean particle = false;
    public static boolean shader = true;

    public void initGui() {


        this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, I18n.format("Change RootPW")));
        this.buttonList.add(new GuiButton(110, this.width / 2 - 155 + 155, this.height / 6 + 48 - 6, 150, 20, I18n.format("Gib Jemanden OP")));
        this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 +24 - 6, 150, 20, I18n.format("HACK MSG")));
        this.buttonList.add(new GuiButton(110, this.width / 2 - 155 + 155, this.height / 6 + 48+24 - 6, 150, 20, I18n.format("VANISH")));
        this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 + 48 - 6, 150, 20, I18n.format("Gib Dir Gamemode")));
        this.buttonList.add(new GuiButton(110, this.width / 2 - 155 + 155, this.height / 6 + 48 + 48- 6, 150, 20, I18n.format("Freeze alle Spieler")));
        this.buttonList.add(new GuiButton(111, this.width / 2 - 155, this.height / 6 + 48 + 48 + 24- 6, 150, 20, I18n.format("Gib Dir Pex *")));
        this.buttonList.add(new GuiButton(112, this.width / 2 - 155 + 155, this.height / 6 + 48 + 48 + 24 - 6, 150, 20, I18n.format("Gib Dir Luckperms *")));
        this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 + 48 + 48- 6, 150, 20, I18n.format("Setze alle Spieler in GM 3")));
        this.buttonList.add(new GuiButton(110, this.width / 2 - 155 + 155, this.height / 6 + 48 + 48 + 48- 6, 150, 20, I18n.format("Zeig Alle Plugins")));
        this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 +48 + 48 + 24- 6, 150, 20, I18n.format("Consolen Command")));
        this.buttonList.add(new GuiButton(110, this.width / 2 - 155 + 155, this.height / 6 + 48 +48 + 24+ 48 - 6, 150, 20, I18n.format("Fake Hacker")));
        this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 +48 + 48 + 48- 6, 150, 20, I18n.format("Ip von einem Spieler")));
        this.buttonList.add(new GuiButton(200, this.width / 2 - 155 + 155, this.height / 6 +48 +48 + 48 + 48 - 6, 150,20,I18n.format("gui.done", new Object[0])));


    }
    public static int clicked = 1;
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 200) {
            mc.displayGuiScreen(new GuiIngameMenu());
        }
        if (button.id == 110) {
            mc.displayGuiScreen(new GuiForceOp(this));
        }
        if (button.id == 111) {
            mc.displayGuiScreen(new GuiPex(this));
        }
        if (button.id == 112) {
            mc.displayGuiScreen(new GuiLuckperms(this));
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
        drawRect(this.width / 9 - 15, this.height / 6 - 25, this.width - 50, this.height / 2 + this.height / 3, new Color(40, 40, 40, 138).getRGB());

        final String Logo = "Plugin Steuerung";
        DrawMenuLogoUtil.drawString(3, Logo, this.width / 7, this.height / 20, Color.RED.getRGB());

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    public void render() {
        partikelsystem.render();
        partikelsystem.tick(15);
    }
}
