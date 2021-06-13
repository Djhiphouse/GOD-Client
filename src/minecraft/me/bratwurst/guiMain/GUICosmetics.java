package me.bratwurst.guiMain;

import me.bratwurst.Client;
import me.bratwurst.manager.PartikelSystem.ParticleSystem;
import me.bratwurst.utils.DrawMenuLogoUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;

public class GUICosmetics extends GuiScreen {
    ParticleSystem partikelsystem = new ParticleSystem(1000, 370);

    public static ArrayList<String> ClosedPort = new ArrayList<>();
    public void initGui() {
        this.buttonList.add(new GuiButton(202, this.width / 2 - 113, this.height / 6 + 150, I18n.format(EnumChatFormatting.GREEN +"START", new Object[0])));
        this.buttonList.add(new GuiButton(200, this.width / 2 - 113, this.height / 6 + 180, I18n.format(EnumChatFormatting.RED +"BACK", new Object[0])));


    }


    @Override
    protected void actionPerformed(final GuiButton button) {
        if (button.id == 200) {

            this.mc.displayGuiScreen((GuiScreen) null);
            this.mc.setIngameFocus();

        }
        if (button.id == 202) {

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
        final String Logo = EnumChatFormatting.UNDERLINE + "" + EnumChatFormatting.RED + "Cosmetics " + Client.getInstance().CLIENT_VERSION;



        DrawMenuLogoUtil.drawString(3, Logo, this.width / 8 -12, this.height / 20, Color.CYAN.getRGB());





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
