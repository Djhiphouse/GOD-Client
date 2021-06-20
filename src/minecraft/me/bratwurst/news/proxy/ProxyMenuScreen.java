package me.bratwurst.news.proxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public final class ProxyMenuScreen
        extends GuiScreen {
    public static Proxy currentProxy = null;
    public static boolean useProxy = false;
    static GuiTextField ip;
    static GuiScreen before;
    private static boolean isRunning;
    private GuiButton button;
    private String status;
    public static String renderText;

    public ProxyMenuScreen(GuiScreen before) {
        ProxyMenuScreen.before = before;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0: {
                if (!isRunning) {
                    String[] split = ip.getText().split(":");
                    if (split.length == 2) {
                        currentProxy = ProxyMenuScreen.getProxyFromString(ip.getText());
                        this.status = "\u00a76Proxy used " + currentProxy.address().toString();
                        useProxy = true;
                        renderText = "\u00a7aSuccessful";
                        isRunning = true;
                        button.displayString = "\u00a7cDisconnect";
                        break;
                    }
                    this.status = "\u00a7cPlease use: <host>:<port>";
                    break;
                }
                isRunning = false;
                button.displayString = "\u00a7aConnect";
                currentProxy = null;
                useProxy = false;
                break;
            }
            case 1: {
                this.mc.displayGuiScreen(before);
            }
        }
    }

    public static Proxy getProxyFromString(String proxy) {
        return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxy.split(":")[0], (int)Integer.valueOf(proxy.split(":")[1])));
    }

    @Override
    public void drawScreen(int x, int y, float z) {
        new ScaledResolution(this.mc);
        this.drawDefaultBackground();
        GL11.glPushMatrix();
        GL11.glColor4d((double)1.0, (double)1.0, (double)1.0, (double)1.0);
        GL11.glScaled((double)4.0, (double)4.0, (double)4.0);
        ProxyMenuScreen.drawCenteredString(this.mc.fontRendererObj, renderText, this.width / 8, this.height / 4 - this.mc.fontRendererObj.FONT_HEIGHT, 0);
        GL11.glPopMatrix();
        ProxyMenuScreen.drawCenteredString(this.mc.fontRendererObj, this.status, this.width / 2, 20, -1);
        ip.drawTextBox();
        ProxyMenuScreen.drawCenteredString(this.mc.fontRendererObj, "\u00a77Proxy IP:Port", this.width / 2, 50, -1);
        super.drawScreen(x, y, z);
    }

    @Override
    public void initGui() {
        renderText = "";
        this.button = new GuiButton(0, this.width / 2 - 100, this.height / 3 + 40, 200, 20, !isRunning ? "\u00a7aConnect" : "\u00a7cDisconnect");
        this.buttonList.add(this.button);
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 3 + 66, 200, 20, "Back"));
        ip = new GuiTextField(this.height, this.mc.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        ip.setMaxStringLength(100);
        ip.setText("127.0.0.1:8080");
        this.status = "\u00a76Waiting...";
        ip.setFocused(true);
        Keyboard.enableRepeatEvents((boolean)true);
    }

    @Override
    protected void keyTyped(char character, int key) {
        try {
            super.keyTyped(character, key);
        }
        catch (IOException var4) {
            var4.printStackTrace();
        }
        if (character == '\r') {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
        ip.textboxKeyTyped(character, key);
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        try {
            super.mouseClicked(x, y, button);
        }
        catch (IOException var5) {
            var5.printStackTrace();
        }
        ip.mouseClicked(x, y, button);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    @Override
    public void updateScreen() {
        ip.updateCursorCounter();
    }

    static {
        renderText = "";
    }
}


