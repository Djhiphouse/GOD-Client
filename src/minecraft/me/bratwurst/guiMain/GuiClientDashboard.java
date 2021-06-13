package me.bratwurst.guiMain;
import me.bratwurst.cosmetics.CosmeticWings;
import me.bratwurst.manager.PartikelSystem.ParticleSystem;
import me.bratwurst.manager.network.GodNetworkClient;
import me.bratwurst.utils.DrawMenuLogoUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;
import java.util.UUID;

public class GuiClientDashboard extends GuiScreen {
    ParticleSystem partikelsystem = new ParticleSystem(1000,230);
    public void initGui() {

        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));

    }
    public static int clicked = 1;
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if(button.id == 200){
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
        final String Logo = "Dashboard";
        DrawMenuLogoUtil.drawString(3, Logo, this.width / 7, this.height / 20, Color.CYAN.getRGB());

//        //Daten
//        String name = "Name";
//        String username =  Minecraft.getMinecraft().getSession().getUsername();
//        this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, I18n.format(EnumChatFormatting.BLUE + name)));
//        this.buttonList.add(new GuiButton(113, this.width / 2 - 155 + 155, this.height / 6 + 48 - 6, 150, 20, I18n.format(EnumChatFormatting.GREEN + username)));

        //COSMETICS
        boolean wings = false;
        String status = "UNBEKANNT";
        String wing = "Wings";
        if(wings == true){
            status = EnumChatFormatting.GREEN + "ON";
        }
        if(wings == false){
            status = EnumChatFormatting.RED + "OFF";
        }

        this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 - 6 , 150, 20, I18n.format(EnumChatFormatting.AQUA + wing)));
        this.buttonList.add(new GuiButton(113, this.width / 2 - 155 + 155, this.height / 6 + 48 - 6 , 150, 20, I18n.format(status)));


        boolean hats = true;
        String statushat = "UNBEKANNT";
        String hat = "HUT";
        if(hats == true){
            statushat = EnumChatFormatting.GREEN + "ON";
        }
        if(hats == false){
            statushat = EnumChatFormatting.RED + "OFF";
        }


        this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 - 6 + 24+ 24, 150, 20, "HAT"));
        this.buttonList.add(new GuiButton(113, this.width / 2 - 155 + 155, this.height / 6 + 48 - 6 + 24 + 24, 150, 20, I18n.format(statushat)));




     super.drawScreen(mouseX, mouseY, partialTicks);
    }
    public void render() {
        partikelsystem.render();
        partikelsystem.tick(15);
    }
}
