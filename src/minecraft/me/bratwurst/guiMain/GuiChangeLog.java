package me.bratwurst.guiMain;

import me.bratwurst.Client;
import me.bratwurst.cosmetics.CosmeticWings;
import me.bratwurst.manager.PartikelSystem.ParticleSystem;
import me.bratwurst.utils.DrawMenuLogoUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class GuiChangeLog extends GuiScreen {
    ParticleSystem partikelsystem = new ParticleSystem(1000, 370);

    public void initGui() {

        this.buttonList.add(new GuiButton(200, this.width / 2 - 113, this.height / 6 + 180, I18n.format(EnumChatFormatting.RED +"BACK", new Object[0])));

    }

    public static int clicked = 1;

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 200) {
            mc.displayGuiScreen(new GuiMainMenu());
        }

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        //Hintergrund
        this.mc.getTextureManager().bindTexture(new ResourceLocation("client/336293.png"));
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
        render();
        //backScreen
        drawRect(this.width / 9 - 15, this.height / 6 - 25, this.width - 50, this.height / 2 + this.height / 3, new Color(14, 14, 14, 79).getRGB());
        final String Logo = EnumChatFormatting.UNDERLINE + "" + EnumChatFormatting.RED + "ChangeLog " + Client.getInstance().CLIENT_VERSION;
        final String New = EnumChatFormatting.GREEN + "- New CheastAura ";
        final String New2 = EnumChatFormatting.GREEN + "- New Infiniteaura";
        final String New3 = EnumChatFormatting.GREEN + "- New Longjump(" + EnumChatFormatting.DARK_RED + "Redesky"+EnumChatFormatting.GREEN + ")";
        final String New4 = EnumChatFormatting.GREEN + "- New ShderBackground ";
        final String New5 = EnumChatFormatting.GREEN + "- New Design";
        final String New6 = EnumChatFormatting.GREEN + "- New Velocity(" + EnumChatFormatting.DARK_RED + "Packet"+EnumChatFormatting.GREEN + ")";
        final String New7 = EnumChatFormatting.GREEN + "- New WTap";
        final String New8 = EnumChatFormatting.GREEN + "- New CrasHead";
        final String New9 = EnumChatFormatting.GREEN + "- New CrashBlock";
        final String New10 = EnumChatFormatting.GREEN + "- New AntiExploitCrash";
        final String New11 = EnumChatFormatting.GREEN + "- New AntiVanish";
        final String New12 = EnumChatFormatting.GREEN + "- New AntiBan";
        final String New13 = EnumChatFormatting.GREEN + "- New AntiToManyPackets";
        final String New14 = EnumChatFormatting.GREEN + "- New GodMode";
        final String New15 = EnumChatFormatting.GREEN + "- New ClickNuker";
        final String New16 = EnumChatFormatting.GREEN + "- New StaffDetection";
        int i = 15;

        //LOGO
        DrawMenuLogoUtil.drawString(3, Logo, this.width / 11 , this.height / 20, Color.CYAN.getRGB());

        //REIHE 1
        DrawMenuLogoUtil.drawString(1, New, this.width / 5, this.height / 4 + i *1, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New2, this.width / 5, this.height / 4 + i *2, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New3, this.width / 5, this.height / 4 + i *3, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New4, this.width / 5, this.height / 4 + i *4, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New5, this.width / 5, this.height / 4 + i *5, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New6, this.width / 5, this.height / 4 + i *6, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New7, this.width / 5, this.height / 4 + i *7, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New16, this.width / 5, this.height / 4 + i *8, Color.CYAN.getRGB());

        //REIHE 2
        int b = 15;
        DrawMenuLogoUtil.drawString(1, New8, this.width / 2 + b, this.height / 4 + i *1, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New9, this.width / 2 + b, this.height / 4 + i *2, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New10, this.width / 2 + b, this.height / 4 + i *3, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New11, this.width / 2 + b, this.height / 4 + i *4, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New12, this.width / 2 + b, this.height / 4 + i *5, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New13, this.width / 2 + b, this.height / 4 + i *6, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New14, this.width / 2 + b, this.height / 4 + i *7, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New15, this.width / 2 + b, this.height / 4 + i *8, Color.CYAN.getRGB());




//        String uuidname = "UUID";
//        String uuid = EnumChatFormatting.GOLD + Minecraft.getMinecraft().thePlayer.getUniqueID().toString();
//        if(uuid == null){
//            uuid = EnumChatFormatting.RED + "UNBEKANNT!";
//        }
//        this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 - 6 + 24+ 24 + 24, 150, 20, I18n.format(EnumChatFormatting.BLUE + uuidname)));
//        this.buttonList.add(new GuiButton(113, this.width / 2 - 155 + 155, this.height / 6 + 48 - 6 + 24 + 24 + 24, 150, 20, I18n.format(uuid)));
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void render() {
        partikelsystem.render();
        partikelsystem.tick(15);
    }
}
