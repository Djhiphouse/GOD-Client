package net.minecraft.client.gui;

import me.bratwurst.AltManager.GuiAltManager;

import me.bratwurst.Client;
import me.bratwurst.manager.PartikelSystem.ParticleSystem;
import me.bratwurst.utils.DrawMenuLogoUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {
    private final Object threadLock = new Object();
    private String openGLWarning1;
ParticleSystem partikelsystem = new ParticleSystem(1000,230);
    private String openGLWarning2;

    private String openGLWarningLink;
    public static final String field_96138_a = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
    private int field_92024_r;
    private int field_92023_s;
    private int field_92022_t;
    private int field_92021_u;
    private int field_92020_v;
    private int field_92019_w;


    public GuiMainMenu() {
        this.openGLWarning2 = field_96138_a;
        this.openGLWarning1 = "";

        if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.areShadersSupported()) {
            this.openGLWarning1 = I18n.format("title.oldgl1", new Object[0]);
            this.openGLWarning2 = I18n.format("title.oldgl2", new Object[0]);
            this.openGLWarningLink = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
        }
    }


    public void initGui() {
        int i = 24;
        int j = this.height / 4 + 48;

        //    addButton();

        this.buttonList.add(new GuiButton(0, this.width / 9, this.height / 2, 98, 20, I18n.format("menu.options", new Object[0])));
        this.buttonList.add(new GuiButton(4, this.width / 9, this.height / 2 + i * 1, 98, 20, I18n.format("menu.quit", new Object[0])));
        this.buttonList.add(new GuiButton(1, this.width / 9, this.height / 2 - i * 3, 98, 20, I18n.format("menu.singleplayer", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 9, this.height / 2 - i * 2, 98, 20, I18n.format("menu.multiplayer", new Object[0])));
        this.buttonList.add(new GuiButton(14, this.width / 9, this.height / 2 - i * 1, 98, 20, I18n.format("AltManager", new Object[0])));


        synchronized (this.threadLock) {
            this.field_92023_s = this.fontRendererObj.getStringWidth(this.openGLWarning1);
            this.field_92024_r = this.fontRendererObj.getStringWidth(this.openGLWarning2);
            int k = Math.max(this.field_92023_s, this.field_92024_r);
            this.field_92022_t = (this.width - k) / 2;
            this.field_92021_u = ((GuiButton) this.buttonList.get(0)).yPosition - 24;
            this.field_92020_v = this.field_92022_t + k;
            this.field_92019_w = this.field_92021_u + 24;
        }

        this.mc.func_181537_a(false);
    }


    protected void actionPerformed(GuiButton button) throws IOException {

        if (button.id == 0) {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }
        if (button.id == 5) {
            this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
        }
        if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        }
        if (button.id == 2) {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }

        if (button.id == 14) {
            this.mc.displayGuiScreen(new GuiAltManager());
        }

        if (button.id == 4) {
            this.mc.shutdown();
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {


        this.mc.getTextureManager().bindTexture(new ResourceLocation("client/336293.png"));
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
        render();
        GlStateManager.color(1, 1, 1);
        int j = this.height / 4 + 48 + 10;
        //Hintergrund


        //backScreen
        drawRect(this.width / 9 - 15, this.height / 6 - 15, this.width - 50, this.height / 2 + this.height / 3, new Color(56, 56, 56, 255).getRGB());
        // MainMenu Logo (TEXT)

         final String Logo = "GOD";
        DrawMenuLogoUtil.drawString(5, Logo, this.width / 12, this.height / 12, Color.CYAN.getRGB());
        String s2;


        GlStateManager.pushMatrix();
        GlStateManager.translate(210, 280, 1);
        Gui.drawRect(width + 70, height + 30, width - 80, height - 30, new Color(0, 0, 0, 190).getRGB());
        mc.fontRendererObj.drawString("§3Range: " + "Nichts", width - 75, height - 25, -1);
        mc.fontRendererObj.drawStringWithShadow("§3Fall Distance: " + "Keine", width - 75, height + 20, -1);
        GlStateManager.color(1f, 1f, 1f);

        GlStateManager.popMatrix();

    //    Color color5 = new Color(0, 0, 0, 190);

        GlStateManager.pushMatrix();
        GlStateManager.translate(5, 60, 50);
      //  Gui.drawRect(70, 30, 80, 30, color5.getRGB());
        GlStateManager.popMatrix();

        String s = EnumChatFormatting.AQUA + "Module: " + EnumChatFormatting.BLUE + Client.getInstance().getModuleManager().getEnabledModules().size() + " / " + Client.getInstance().getModuleManager().modules.size();
        String s1;
        String s3;
        this.drawString(this.fontRendererObj, s, 2, this.height - 10, -1);
        s1 = EnumChatFormatting.AQUA + "Coded by " + EnumChatFormatting.BLUE + "Bratwurst001";
        this.drawString(this.fontRendererObj, s1, this.width - this.fontRendererObj.getStringWidth(s1) - 2, this.height - 10, -1);
        s2 = EnumChatFormatting.AQUA + "Version: " + EnumChatFormatting.BLUE + Client.getInstance().CLIENT_VERSION;
        this.drawString(this.fontRendererObj, s2, 2, 2, Color.WHITE.getRGB());


        String infos = EnumChatFormatting.AQUA + "Account - Infos:";
        this.drawString(this.fontRendererObj, infos, this.width - this.fontRendererObj.getStringWidth(infos) - 2, 2, -1);
        String username = EnumChatFormatting.AQUA + "Name: " + EnumChatFormatting.BLUE + Minecraft.getMinecraft().getSession().getUsername();
        this.drawString(this.fontRendererObj, username, this.width - this.fontRendererObj.getStringWidth(username) - 2, 15, Color.ORANGE.getRGB());
        this.drawString(this.fontRendererObj, s, 2, this.height - 10, -1);
        this.drawString(this.fontRendererObj, s1, this.width - this.fontRendererObj.getStringWidth(s1) - 2, this.height - 10, -1);


        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        synchronized (this.threadLock) {
            if (this.openGLWarning1.length() > 0 && mouseX >= this.field_92022_t && mouseX <= this.field_92020_v && mouseY >= this.field_92021_u && mouseY <= this.field_92019_w) {
                GuiConfirmOpenLink guiconfirmopenlink = new GuiConfirmOpenLink(this, this.openGLWarningLink, 13, true);
                guiconfirmopenlink.disableSecurityWarning();
                this.mc.displayGuiScreen(guiconfirmopenlink);
            }
        }
    }
    public void render() {
       partikelsystem.render();
       partikelsystem.tick(15);
    }
}