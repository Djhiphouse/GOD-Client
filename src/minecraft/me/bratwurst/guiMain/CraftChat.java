package me.bratwurst.guiMain;

import me.bratwurst.Client;
import me.bratwurst.utils.DrawMenuLogoUtil;
import me.bratwurst.utils.MainUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;

public class CraftChat extends GuiScreen {
    public static Proxy currentProxy = null;
    public static boolean useProxy = false;
    public static ArrayList<String> nick = new ArrayList<>();
    public static String nickname = Minecraft.getMinecraft().session.getUsername();
    static GuiTextField ip;
    static GuiTextField nickfeld;
    static GuiScreen before;
    private static boolean isRunning;
    private GuiButton button;
    private String status;
    public static String renderText;
    public  static  ArrayList<String> list = new ArrayList<>();
    public static int i = 0;


    public CraftChat(GuiScreen before) {
        CraftChat.before = before;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {

            case 1:
                mc.displayGuiScreen(new GuiIngameMenu());
                break;
            case 7:
                Client.networkClient.getIrcClient().send("7hhefwiu5325he25986736674&?3636453245648267356");

                break;
            case 5:
                String Name = Minecraft.getMinecraft().session.getUsername();
                String Nachricht = ip.getText();
                try {
                    if (!Nachricht.equals("") && !Nachricht.matches("\\s+")) {
                       if(Name.equalsIgnoreCase("DerEchteGian") || Name.equalsIgnoreCase("Ente1") ||Name.equalsIgnoreCase("WOLLROCK") ||Name.equalsIgnoreCase("PowerBurst") ||Name.equalsIgnoreCase("Bratwurst001") ||Name.equalsIgnoreCase("Jxnnik25") ||Name.equalsIgnoreCase("Schwitziges") ||Name.equalsIgnoreCase("Freddy1994Phil") ||Name.equalsIgnoreCase("Skill_Ben") ||Name.equalsIgnoreCase("flokellner24") ||Name.equalsIgnoreCase("Nummbrs1") ||Name.equalsIgnoreCase("Milchschnite22") ||Name.equalsIgnoreCase("Insane89m") ||Name.equalsIgnoreCase("PixleSiuox") ||Name.equalsIgnoreCase("NYzio")) {
                           Client.networkClient.getIrcClient().send( "Porn " + nickname + " " +  Nachricht);

                           i = i+24;

                       }


                    }


                } catch (Exception e) {
                    e.printStackTrace();

                }

         ip.setText("");
        }
    }




    @Override
    public void drawScreen(int x, int y, float z) {
        new ScaledResolution(this.mc);
        this.drawDefaultBackground();

        drawRect(this.width / 9 + 100 , this.height / 6 , this.width - 100, this.height / 2 + this.height / 3, new Color(12, 12, 12, 211).getRGB());

        synchronized (list) {
            int i = 12;
            for (String string : list) {
                i++;
                this.drawString(this.fontRendererObj, String.valueOf(EntityPlayer.replaceColorCodes(string)), this.width /7 +100, 100+ i, Color.WHITE.hashCode());
                i += this.fontRendererObj.FONT_HEIGHT ;

            }
            if (list.size() > 24){
                list.clear();
            }
        }


        drawRect( -50,  this.height, this.width /7 , 0, new Color(16, 109, 120, 143).getRGB());
        GL11.glPushMatrix();
        GL11.glColor4d((double) 1.0, (double) 1.0, (double) 1.0, (double) 1.0);
        GL11.glScaled((double) 4.0, (double) 4.0, (double) 4.0);
        CraftChat.drawCenteredString(this.mc.fontRendererObj, renderText, this.width / 8, this.height / 4 - this.mc.fontRendererObj.FONT_HEIGHT, 0);
        GL11.glPopMatrix();
        CraftChat.drawCenteredString(this.mc.fontRendererObj, this.status, this.width / 2, 20, -1);
        ip.drawTextBox();
      //  nickfeld.drawTextBox();
        CraftChat.drawCenteredString(this.mc.fontRendererObj, "\u00a77Nachricht", this.width / 2, this.height / 2 + 100 - 14, -1);
      //  DrawMenuLogoUtil.drawString(2,"test" ,100,this.height / 3 + i, new Color(252, 0, 0, 188).getRGB());

        super.drawScreen(x, y, z);
    }

    @Override
    public void initGui() {
        renderText = "";
        int i = 24;

        this.buttonList.add(new GuiButton(5, this.width / 2 - 100,  this.height /2 + 100 +24 , I18n.format("Send", new Object[0])));
      //  this.buttonList.add(new GuiButton(6, 0, this.height / 2 - i * 3, this.width /7, 20, I18n.format("Nick", new Object[0])));
        this.buttonList.add(new GuiButton(7, 0, this.height / 2 - i * 2, this.width /7, 20, I18n.format("Clear", new Object[0])));

        this.buttonList.add(new GuiButton(200, 0 , this.height / 2 + i * 3, this.width /7, 20, I18n.format("Done", new Object[0])));
        GlStateManager.pushMatrix();
        GlStateManager.scale(1,1000,1);
       // nickfeld = new GuiTextField(this.height, this.mc.fontRendererObj, 0, this.height / 2 - i * 3 - 24, this.width /7, 20);
        //  nickfeld.setMaxStringLength(16);
        //  nickfeld.setText("");
        ip = new GuiTextField(this.height, this.mc.fontRendererObj, this.width / 2 -100, this.height /2 + 100, 200, 20);
        ip.setMaxStringLength(100);
        ip.setText("");
        this.status = "\u00a7cCraftChat!";
        ip.setFocused(true);
        //    nickfeld.setFocused(true);
        Keyboard.enableRepeatEvents((boolean) true);
        GlStateManager.popMatrix();
    }

    @Override
    protected void keyTyped(char character, int key) {
        try {
            super.keyTyped(character, key);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
        if (character == '\r') {
            this.actionPerformed((GuiButton) this.buttonList.get(0));
        }
        ip.textboxKeyTyped(character, key);
        // nickfeld.textboxKeyTyped(character, key);
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        try {
            super.mouseClicked(x, y, button);
        } catch (IOException var5) {
            var5.printStackTrace();
        }
        ip.mouseClicked(x, y, button);
        //  nickfeld.mouseClicked(x, y, button);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean) false);
    }

    @Override
    public void updateScreen() {
        ip.updateCursorCounter();
        //  nickfeld.updateCursorCounter();
    }

    static {
        renderText = "";
    }
}



