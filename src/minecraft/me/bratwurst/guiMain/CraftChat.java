package me.bratwurst.guiMain;

import me.bratwurst.Client;
import me.bratwurst.utils.DrawMenuLogoUtil;
import me.bratwurst.utils.MainUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
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
    static GuiTextField ip;
    static GuiScreen before;
    private static boolean isRunning;
    private GuiButton button;
    private String status;
    public static String renderText;
    public  static  String MessageTeam = "";
    public static int i = 0;


    public CraftChat(GuiScreen before) {
        CraftChat.before = before;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:

                break;
            case 1:
                mc.displayGuiScreen(new GuiIngameMenu());
                break;

            case 5:
                String Nachricht = ip.getText();
                try {
                    if (Nachricht != null) {
                        Client.networkClient.getIrcClient().send(Minecraft.getMinecraft().session.getUsername() + " " +  Nachricht);
                        i = i+24;
                    }


                } catch (Exception e) {
                    e.printStackTrace();

                }

         ip.setText("");
        }
    }




    @Override
    public void drawScreen(int x, int y, float z) {
        ArrayList<String>list = new ArrayList<>();
        list.add(MessageTeam);
        new ScaledResolution(this.mc);
        this.drawDefaultBackground();

        drawRect(this.width / 9 + 100 , this.height / 6 , this.width - 100, this.height / 2 + this.height / 3, new Color(12, 12, 12, 211).getRGB());

        synchronized (list) {
            int i = 12;
            for (String string : list) {
                i++;
                this.drawString(this.fontRendererObj, String.valueOf(string), this.width / 3, 145+ i, Color.WHITE.hashCode());
                i += this.fontRendererObj.FONT_HEIGHT ;

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
        CraftChat.drawCenteredString(this.mc.fontRendererObj, "\u00a77Nachricht", this.width / 4, 385, -1);
      //  DrawMenuLogoUtil.drawString(2,"test" ,100,this.height / 3 + i, new Color(252, 0, 0, 188).getRGB());

        super.drawScreen(x, y, z);
    }

    @Override
    public void initGui() {
        renderText = "";
        int i = 24;

        this.buttonList.add(new GuiButton(5, this.width / 5 + 20,  435, I18n.format("Send", new Object[0])));
        this.buttonList.add(new GuiButton(6, 0, this.height / 2 - i * 3, this.width /7, 20, I18n.format("Nick", new Object[0])));

        this.buttonList.add(new GuiButton(200, 0 , this.height / 2 + i * 3, this.width /7, 20, I18n.format("Done", new Object[0])));
        GlStateManager.pushMatrix();
        GlStateManager.scale(1,1000,1);
        ip = new GuiTextField(this.height, this.mc.fontRendererObj, this.width / 5 + 20, 400, 200, 20);
        ip.setMaxStringLength(100);
        ip.setText("");
        this.status = "\u00a7cCraftChat!";
        ip.setFocused(true);
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
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        try {
            super.mouseClicked(x, y, button);
        } catch (IOException var5) {
            var5.printStackTrace();
        }
        ip.mouseClicked(x, y, button);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean) false);
    }

    @Override
    public void updateScreen() {
        ip.updateCursorCounter();
    }

    static {
        renderText = "";
    }
}



