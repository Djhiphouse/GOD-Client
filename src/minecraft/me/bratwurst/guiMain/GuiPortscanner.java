package me.bratwurst.guiMain;

import me.bratwurst.Client;
import me.bratwurst.cosmetics.CosmeticWings;
import me.bratwurst.manager.PartikelSystem.ParticleSystem;
import me.bratwurst.utils.DrawMenuLogoUtil;
import me.bratwurst.utils.TPSUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GuiPortscanner extends GuiScreen {
    ParticleSystem partikelsystem = new ParticleSystem(1000, 370);
    public GuiScreen parentScreen;
    DecimalFormat df = new DecimalFormat("#.##");
    public static ArrayList<String> ClosedPort = new ArrayList<>();
    public void initGui() {
        this.buttonList.add(new GuiButton(202, this.width / 2 - 113, this.height / 6 + 150, I18n.format(EnumChatFormatting.GREEN +"START", new Object[0])));
        this.buttonList.add(new GuiButton(200, this.width / 2 - 113, this.height / 6 + 180, I18n.format(EnumChatFormatting.RED +"BACK", new Object[0])));


    }

public static String Errorcode;
public static  boolean Packetjoin = false;
    public static int ValuePacket = 0;
    public static int FailedPacket = 0;
    @Override
    protected void actionPerformed(final GuiButton button) {
        if (button.id == 200) {
            ValuePacket = 0;
            this.mc.displayGuiScreen(this.parentScreen);
            this.mc.setIngameFocus();

        }
        if (button.id == 202) {
            while (ValuePacket <= 20) {
                ValuePacket++;
                ValuePacket++;
                ValuePacket++;
                ValuePacket++;
                ValuePacket++;
                ValuePacket++;
                ValuePacket++;
                ValuePacket++;
                ValuePacket++;
                ValuePacket++;
                ValuePacket++;
                try {
                    if (ValuePacket <= 1 )
                    Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketPing());
                    if (ValuePacket >= 5 )
                        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C00PacketKeepAlive());
                }catch (Exception E) {
                   FailedPacket++;
                   Errorcode = E.toString();
                }

            }

        }

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (Packetjoin == true) {
            if (ValuePacket <= 500) {
                ValuePacket++;
                try {
                    Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketPing());
                }catch (Error e) {
                       FailedPacket++;

                }

            }
        }
        this.drawDefaultBackground();
        //Hintergrund
        this.mc.getTextureManager().bindTexture(new ResourceLocation("client/336293.png"));
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
        render();
        //backScreen
        drawRect(this.width / 9 - 15, this.height / 6 - 25, this.width - 50, this.height / 2 + this.height / 3, new Color(14, 14, 14, 79).getRGB());
        final String Logo = EnumChatFormatting.UNDERLINE + "" + EnumChatFormatting.RED + "ServerPinger " + Client.getInstance().CLIENT_VERSION;
        final String New = EnumChatFormatting.GREEN + "ServerInfos:";
        final String Packets = EnumChatFormatting.GREEN + "Packets send: " + EnumChatFormatting.DARK_RED + ValuePacket + "\n";
        final String FailedPacketsend = EnumChatFormatting.GREEN + "Packets lost: " + EnumChatFormatting.DARK_RED + FailedPacket + "\n";
        final String ServerAdresse = EnumChatFormatting.BLUE + "Adresse: " + EnumChatFormatting.AQUA + Minecraft.getMinecraft().getNetHandler().getNetworkManager().getRemoteAddress() + "\n";
        final String Serverport = EnumChatFormatting.BLUE + "Population: " + EnumChatFormatting.AQUA + Minecraft.getMinecraft().getCurrentServerData().playerList + "\n";
        final String ServerPing = EnumChatFormatting.BLUE + "ServerPing: " + EnumChatFormatting.AQUA + Minecraft.getMinecraft().getCurrentServerData().pingToServer + "\n";
        final String ServerBrand = EnumChatFormatting.BLUE + "Version: " + EnumChatFormatting.AQUA + Minecraft.getMinecraft().thePlayer.getClientBrand() + "\n";
        final String ServerTPS = EnumChatFormatting.BLUE + "ServerTPS: " + EnumChatFormatting.YELLOW + this.df.format(TPSUtils.tps) + "\n";
        DrawMenuLogoUtil.drawString(3, Logo, this.width / 11 , this.height / 20, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, New, this.width / 9, this.height / 4 - 5, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, ServerAdresse, this.width / 9, this.height / 4 + 15 , Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, Serverport, this.width / 9, this.height / 4 + 30, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, ServerPing, this.width / 9, this.height / 4 + 45, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, ServerBrand, this.width / 9, this.height / 4 + 60, Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, ServerTPS, this.width / 9, this.height / 4 + 75, Color.CYAN.getRGB());
        //TPS CORDS (X) this.width / 2-50, (Y) this.height / 2 - 15
        DrawMenuLogoUtil.drawString(1, Packets, this.width / 2+ 25, this.height / 2 + 7 , Color.CYAN.getRGB());
        DrawMenuLogoUtil.drawString(1, FailedPacketsend, this.width / 2+25, this.height / 2+ 20, Color.CYAN.getRGB());




//        String uuidname = "UUID";
//        String uuid = EnumChatFormatting.GOLD + Minecraft.getMinecraft().thePlayer.getUniqueID().toString();
//        if(uuid == null){
//            uuid = EnumChatFormatting.RED + "UNBEKANNT!";
//        }
//        this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 - 6 + 24+ 24 + 24, 150, 20, I18n.format(EnumChatFormatting.BLUE + uuidname)));
//        this.buttonList.add(new GuiButton(113, this.width / 2 - 155 + 155, this.height / 6 + 48 - 6 + 24 + 24 + 24, 150, 20, I18n.format(uuid)));
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (int m = 0; m < Client.getInstance().getModuleManager().getEnabledModules().size(); m++) {
            String menable = Client.getInstance().getModuleManager().getEnabledModules().toString();
            System.out.println(menable);
        }
    }

    public void render() {

        partikelsystem.render();
        partikelsystem.tick(15);
    }
}
