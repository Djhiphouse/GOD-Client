package me.bratwurst.news.serverfinder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

public class ServerFinderScreen
        extends GuiScreen {
    private GuiMultiplayer prevMenu;
    private GuiTextField ipBox;
    private GuiTextField maxThreadsBox;
    private boolean running;
    private int checked;
    private int working;
    private boolean terminated;
    public int maxthreads = 999;
    public int fade = 0;

    public ServerFinderScreen(GuiMultiplayer prevMultiplayerMenu) {
        this.prevMenu = prevMultiplayerMenu;
    }

    @Override
    public void updateScreen() {
        this.ipBox.updateCursorCounter();
        ((GuiButton)this.buttonList.get((int)0)).enabled = this.ipBox.getText().trim().length() >= 7 && this.ipBox.getText().contains(".") && !this.ipBox.getText().contains(":") && StringUtils.countMatches((CharSequence)this.ipBox.getText(), (CharSequence)".") == 3 && ServerFinderScreen.isInteger(this.ipBox.getText().split("\\.", -1)[0]) && ServerFinderScreen.isInteger(this.ipBox.getText().split("\\.", -1)[1]) && ServerFinderScreen.isInteger(this.ipBox.getText().split("\\.", -1)[2]) && ServerFinderScreen.isInteger(this.ipBox.getText().split("\\.", -1)[3]) && !this.running && ServerFinderScreen.isInteger(this.maxThreadsBox.getText());
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents((boolean)true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, "Start"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, "Back"));
        this.buttonList.add(new GuiButton(2, 5, 6, 100, 20, "Resolve SRV"));
        this.ipBox = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 100, this.height / 4 + 34, 200, 20);
        this.ipBox.setMaxStringLength(15);
        this.ipBox.setFocused(true);
        this.maxThreadsBox = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 32, this.height / 4 + 58, 28, 12);
        this.maxThreadsBox.setMaxStringLength(50);
        this.maxThreadsBox.setFocused(false);
        this.maxThreadsBox.setText(Integer.toString(this.maxthreads));
        this.running = false;
        this.terminated = false;
    }

    @Override
    public void onGuiClosed() {
        this.terminated = true;
        if (ServerFinderScreen.isInteger(this.maxThreadsBox.getText())) {
            this.maxthreads = Integer.valueOf(this.maxThreadsBox.getText());
        }
        Keyboard.enableRepeatEvents((boolean)false);
    }

    @Override
    protected void actionPerformed(GuiButton clickedButton) {
        if (clickedButton.enabled) {
            if (clickedButton.id == 0) {
                if (ServerFinderScreen.isInteger(this.maxThreadsBox.getText())) {
                    this.maxthreads = Integer.valueOf(this.maxThreadsBox.getText());
                }
                this.running = true;
                new Thread("Server Finder"){

                    @Override
                    public void run() {
                        int[] ipParts = new int[4];
                        for (int i = 0; i < ServerFinderScreen.this.ipBox.getText().split("\\.").length; ++i) {
                            ipParts[i] = Integer.valueOf(ServerFinderScreen.this.ipBox.getText().split("\\.")[i]);
                        }
                        ArrayList pingers = new ArrayList();
                        block1: for (int i = 3; i >= 0; --i) {
                            for (int i2 = 0; i2 <= 255; ++i2) {
                                if (ServerFinderScreen.this.terminated) break block1;
                                int[] ipParts2 = (int[])ipParts.clone();
                                ipParts2[i] = i2;
                                String ip = ipParts2[0] + "." + ipParts2[1] + "." + ipParts2[2] + "." + ipParts2[3];
                                BetterServerPinger pinger = new BetterServerPinger();
                                pinger.ping(ip);
                                pingers.add(pinger);
                                while (pingers.size() >= ServerFinderScreen.this.maxthreads) {
                                    pingers = ServerFinderScreen.this.updatePingers(pingers);
                                }
                            }
                        }
                        while (pingers.size() > 0) {
                            pingers = ServerFinderScreen.this.updatePingers(pingers);
                        }
                    }
                }.start();
            } else if (clickedButton.id == 1) {
                this.mc.displayGuiScreen(new GuiMultiplayer(null));
            } else if (clickedButton.id == 2) {
                try {
                    ServerFinderScreen.setClipboardString(InetAddress.getByName(ServerAddress.func_78860_a(this.ipBox.getText()).getIP()).getHostAddress());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ArrayList<BetterServerPinger> updatePingers(ArrayList<BetterServerPinger> pingers) {
        for (int i = 0; i < pingers.size(); ++i) {
            if (pingers.get(i).isStillPinging()) continue;
            ++this.checked;
            if (pingers.get(i).isWorking()) {
                ++this.working;
                this.prevMenu.savedServerList.addServerData(new ServerData("\u00a7cServer: " + this.working, pingers.get((int)i).server.serverIP, false));
                this.prevMenu.savedServerList.saveServerList();
                this.prevMenu.serverListSelector.func_148195_a(this.prevMenu.savedServerList);
            }
            pingers.remove(i);
        }
        return pingers;
    }

    @Override
    protected void keyTyped(char par1, int par2) {
        this.ipBox.textboxKeyTyped(par1, par2);
        this.maxThreadsBox.textboxKeyTyped(par1, par2);
        if (par2 == 28 || par2 == 156) {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) throws IOException {
        super.mouseClicked(par1, par2, par3);
        this.ipBox.mouseClicked(par1, par2, par3);
        this.maxThreadsBox.mouseClicked(par1, par2, par3);
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        this.ipBox.drawTextBox();
        this.drawString(this.fontRendererObj, "IP", this.width / 2 - 100, this.height / 4 + 22, 0xA0A0A0);
        this.drawString(this.fontRendererObj, "Max. threads:", this.width / 2 - 100, this.height / 4 + 60, 0xA0A0A0);
        this.maxThreadsBox.drawTextBox();
        if (!((GuiButton)this.buttonList.get((int)0)).enabled) {
            if (this.ipBox.getText().length() == 0) {
                ServerFinderScreen.drawCenteredString(this.fontRendererObj, "\u00a74Please enter a IP!", this.width / 2, this.height / 4 + 73, 0xA0A0A0);
            } else if (this.ipBox.getText().contains(":")) {
                ServerFinderScreen.drawCenteredString(this.fontRendererObj, "\u00a74Port is invalid!", this.width / 2, this.height / 4 + 73, 0xA0A0A0);
            } else if (!ServerFinderScreen.isInteger(this.ipBox.getText().split("\\.", -1)[0])) {
                ServerFinderScreen.drawCenteredString(this.fontRendererObj, "\u00a74Host is invalid!", this.width / 2, this.height / 4 + 73, 0xA0A0A0);
            } else if (StringUtils.countMatches((CharSequence)this.ipBox.getText(), (CharSequence)".") >= 1 && !ServerFinderScreen.isInteger(this.ipBox.getText().split("\\.", -1)[1])) {
                ServerFinderScreen.drawCenteredString(this.fontRendererObj, "\u00a74Host is invalid!", this.width / 2, this.height / 4 + 73, 0xA0A0A0);
            } else if (StringUtils.countMatches((CharSequence)this.ipBox.getText(), (CharSequence)".") >= 2 && !ServerFinderScreen.isInteger(this.ipBox.getText().split("\\.", -1)[2])) {
                ServerFinderScreen.drawCenteredString(this.fontRendererObj, "\u00a74Host is invalid!", this.width / 2, this.height / 4 + 73, 0xA0A0A0);
            } else if (StringUtils.countMatches((CharSequence)this.ipBox.getText(), (CharSequence)".") >= 3 && !ServerFinderScreen.isInteger(this.ipBox.getText().split("\\.", -1)[3])) {
                ServerFinderScreen.drawCenteredString(this.fontRendererObj, "\u00a74Host is invalid!", this.width / 2, this.height / 4 + 73, 0xA0A0A0);
            } else if (StringUtils.countMatches((CharSequence)this.ipBox.getText(), (CharSequence)".") < 3) {
                ServerFinderScreen.drawCenteredString(this.fontRendererObj, "\u00a74IP to short!", this.width / 2, this.height / 4 + 73, 0xA0A0A0);
            } else if (StringUtils.countMatches((CharSequence)this.ipBox.getText(), (CharSequence)".") > 3) {
                ServerFinderScreen.drawCenteredString(this.fontRendererObj, "\u00a74IP to long!", this.width / 2, this.height / 4 + 73, 0xA0A0A0);
            } else if (!ServerFinderScreen.isInteger(this.maxThreadsBox.getText())) {
                ServerFinderScreen.drawCenteredString(this.fontRendererObj, "\u00a74Please enter a Number!", this.width / 2, this.height / 4 + 73, 0xA0A0A0);
            } else if (this.running) {
                if (this.checked == 1024) {
                    ServerFinderScreen.drawCenteredString(this.fontRendererObj, "\u00a72Finish!", this.width / 2, this.height / 4 + 73, 0xA0A0A0);
                } else {
                    ServerFinderScreen.drawCenteredString(this.fontRendererObj, "\u00a72Search...", this.width / 2, this.height / 4 + 73, 0xA0A0A0);
                }
            } else {
                ServerFinderScreen.drawCenteredString(this.fontRendererObj, "\u00a74Ein Fehler ist aufgeteten. Kontaktiere den Developer auf Skype", this.width / 2, this.height / 4 + 73, 0xA0A0A0);
            }
            this.drawString(this.fontRendererObj, "Checked: " + this.checked + " / 1024", this.width / 2 - 100, this.height / 4 + 84, 0xA0A0A0);
            this.drawString(this.fontRendererObj, "Working: " + this.working, this.width / 2 - 100, this.height / 4 + 94, 0xA0A0A0);
        }
        super.drawScreen(par1, par2, par3);
    }
}


