package me.bratwurst.news.crash;

import me.bratwurst.Client;
import me.bratwurst.manager.MsgManager;
import me.bratwurst.utils.MainUtil;
import me.bratwurst.utils.Msgtimer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C01PacketChatMessage;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MSGSpamScreen extends GuiScreen {
    public static Proxy currentProxy = null;
    public static boolean useProxy = false;
    static GuiTextField ip;
    static GuiScreen before;
    private static boolean isRunning;
    private GuiButton button;
    private String status;
    public static String renderText;
    public static String Msgtext = "";
    public static boolean msgspam = false;
    static Executor pool = Executors.newScheduledThreadPool(12);

    public MSGSpamScreen(GuiScreen before) {
        GuiCrashScreen.before = before;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                if (!isRunning) {
                    String[] split = ip.getText().split(":");
                    if (split.length == 2) {
                        currentProxy = GuiCrashScreen.getProxyFromString(ip.getText());
                        this.status = "\u00a76Proxy used " + currentProxy.address().toString();
                        useProxy = true;
                        renderText = "\u00a7cCrashed";
                        isRunning = true;
                        button.displayString = "\u00a7cDisconnect";
                        break;
                    }
                    this.status = "\u00a7cBitte Trage den Text ein denn du Sende möchtest.";
                    break;
                }
                isRunning = false;
                button.displayString = "\u00a7Start";
                currentProxy = null;
                useProxy = false;
                break;
            case 1:
                mc.displayGuiScreen(new GuiIngameMenu());
                break;

            case 2:
             Msgtext = ip.getText();
             if (!Client.getInstance().getModuleManager().getModuleByName("MsgSpammer").isEnabled()) {
                 Client.getInstance().getModuleManager().getModuleByName("MsgSpammer").toggle();
             }

             break;
            case 3:
               msgspam = false;
                if (Client.getInstance().getModuleManager().getModuleByName("MsgSpammer").isEnabled()) {
                    Client.getInstance().getModuleManager().getModuleByName("MsgSpammer").toggle();
                }

               break;


        }
    }

    public static Proxy getProxyFromString(String proxy) {
        return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxy.split(":")[0], (int) Integer.valueOf(proxy.split(":")[1])));
    }

    @Override
    public void drawScreen(int x, int y, float z) {





        new ScaledResolution(this.mc);
        this.drawDefaultBackground();
        GL11.glPushMatrix();
        GL11.glColor4d((double) 1.0, (double) 1.0, (double) 1.0, (double) 1.0);
        GL11.glScaled((double) 4.0, (double) 4.0, (double) 4.0);
        GuiCrashScreen.drawCenteredString(this.mc.fontRendererObj, renderText, this.width / 8, this.height / 4 - this.mc.fontRendererObj.FONT_HEIGHT, 0);
        GL11.glPopMatrix();
        GuiCrashScreen.drawCenteredString(this.mc.fontRendererObj, this.status, this.width / 2, 20, -1);
        ip.drawTextBox();
        GuiCrashScreen.drawCenteredString(this.mc.fontRendererObj, "\u00a77TEXT", this.width / 2, 50, -1);
        super.drawScreen(x, y, z);
    }
    private static ArrayList<String> MsgSpieler = new ArrayList<>();

    @Override
    public void initGui() {
        renderText = "";
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 3 + 100, 200, 20, "Back"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 3 + 66, 200, 20, "Stop"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 3 + 35, 200, 20, "Start"));
        ip = new GuiTextField(this.height, this.mc.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        ip.setMaxStringLength(100);
        ip.setText("");
        this.status = "\u00a7cSpam MSg Nachrichten!";
        ip.setFocused(true);
        Keyboard.enableRepeatEvents((boolean) true);
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



