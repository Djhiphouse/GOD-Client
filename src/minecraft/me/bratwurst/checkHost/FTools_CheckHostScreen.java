package me.bratwurst.checkHost;

import java.awt.Color;
import java.io.IOException;
import java.util.Map;

import me.bratwurst.checkHost.results.FTools_PingResult;
import me.bratwurst.checkHost.results.FTools_TcpResult;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class FTools_CheckHostScreen
        extends GuiScreen {
    private GuiScreen parent;
    private String pingedServer = "";
    private FTools_ServerInfos.Hosting hosting;
    private GuiTextField ipField;
    private FTools_Result<Map<FTools_CheckHostServer, FTools_PingResult>> pingResult;
    private FTools_Result<Map<FTools_CheckHostServer, FTools_TcpResult>> tcpResult;
    private String i = "";
    private String type = "";
    private int reloadtime = 0;

    public FTools_CheckHostScreen(String ip, String type, GuiScreen parent) {
        this.setParent(parent);
        this.type = type;
        this.i = ip;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(1, this.width - 144 + 74, 54, 60, 20, "Back"));
        this.buttonList.add(new GuiButton(2, this.width - 144 + 10, 54, 60, 20, "Ping"));
        this.ipField = new GuiTextField(3, this.fontRendererObj, this.width - 134, 30, 124, 20);
        this.ipField.setText(this.i);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.ipField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        this.ipField.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 1: {
                this.mc.displayGuiScreen(this.getParent());
                break;
            }
            case 2: {
                if (this.type.equals("Ping")) {
                    new Thread(new Runnable(){

                        @Override
                        public void run() {
                            FTools_CheckHostScreen.this.pingedServer = FTools_CheckHostScreen.this.ipField.getText();
                            FTools_CheckHostScreen.this.hosting = new FTools_ServerInfos.Hosting(FTools_CheckHostScreen.this.pingedServer);
                            try {
                                if (!FTools_CheckHostScreen.this.pingedServer.isEmpty()) {
                                    FTools_CheckHostScreen.this.pingResult = FTools_CheckHostAPI.createPingRequest(FTools_CheckHostScreen.this.pingedServer, 100);
                                }
                                FTools_CheckHostScreen.this.tcpResult = null;
                                FTools_CheckHostScreen.this.pingResult.update();
                            }
                            catch (Exception exception) {
                                // empty catch block
                            }
                        }
                    }).start();
                    break;
                }
                new Thread(new Runnable(){

                    @Override
                    public void run() {
                        FTools_CheckHostScreen.this.pingedServer = FTools_CheckHostScreen.this.ipField.getText();
                        FTools_CheckHostScreen.this.hosting = new FTools_ServerInfos.Hosting(FTools_CheckHostScreen.this.pingedServer);
                        try {
                            if (!FTools_CheckHostScreen.this.pingedServer.isEmpty()) {
                                FTools_CheckHostScreen.this.tcpResult = FTools_CheckHostAPI.createTcpRequest(FTools_CheckHostScreen.this.pingedServer, 100);
                            }
                            FTools_CheckHostScreen.this.pingResult = null;
                            FTools_CheckHostScreen.this.tcpResult.update();
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                    }
                }).start();
            }
        }
        super.actionPerformed(button);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.ipField.updateCursorCounter();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float delta) {
        block22: {
            this.drawDefaultBackground();
            this.ipField.drawTextBox();
            FTools_CheckHostScreen.drawCenteredString(this.fontRendererObj, "\u00a7b\u00a7lCheck Host \u00a77(\u00a7c" + this.type + "\u00a77)", this.width / 2, 10, -1);
            try {
                if (this.pingedServer.isEmpty()) break block22;
                Gui.drawRect(8, 29, 400, 30, Color.GRAY.getRGB());
                Gui.drawRect(8, 291, 400, 290, Color.GRAY.getRGB());
                for (int i = 0; i < 26; ++i) {
                    Gui.drawRect(10, 30 + i * 10, 400, 30 + (i + 1) * 10, Integer.MIN_VALUE);
                    Gui.drawRect(8, 30 + i * 10, 9, 30 + (i + 1) * 10, Color.GRAY.getRGB());
                    Gui.drawRect(399, 30 + i * 10, 400, 30 + (i + 1) * 10, Color.GRAY.getRGB());
                    Gui.drawRect(135, 30 + i * 10, 136, 30 + (i + 1) * 10, Color.GRAY.getRGB());
                    Gui.drawRect(240, 30 + i * 10, 241, 30 + (i + 1) * 10, Color.GRAY.getRGB());
                }
                if (this.pingResult != null) {
                    int pingResultY = 30;
                    for (FTools_CheckHostServer s : this.pingResult.getServers()) {
                        if (this.reloadtime < 600) {
                            ++this.reloadtime;
                        } else {
                            this.pingResult.update();
                            this.reloadtime = 0;
                        }
                        FTools_PingResult t = this.pingResult.getResult().get(s);
                        this.drawString(this.fontRendererObj, "\u00a72Location", 10, 20, -1);
                        this.drawString(this.fontRendererObj, "\u00a7b" + s.getCountry() + ", " + s.getCity(), 10, pingResultY + 1, -1);
                        try {
                            double var12 = 1337.0;
                            double var16 = 0.0;
                            double var14 = 0.0;
                            int var18 = 0;
                            int var19 = 0;
                            for (int i = 0; i < t.getPingEntries().size(); ++i) {
                                if (t.getPingEntries().get(0).getStatus().equals("OK")) {
                                    ++var18;
                                }
                                double var22 = t.getPingEntries().get(1).getPing();
                                var22 *= 10000.0;
                                if ((var22 = (double)((int)var22) / 10.0) < var12) {
                                    var12 = (int)var22;
                                }
                                if (var22 > var14) {
                                    var14 = (int)var22;
                                }
                                var16 += var22;
                                var16 /= (double)(++var19);
                                var16 = Double.max(var16, 1.0);
                            }
                            this.drawString(this.fontRendererObj, "\u00a72Result", 137, 20, -1);
                            this.drawString(this.fontRendererObj, (var18 == var19 ? "\u00a7a" : "\u00a7c") + var18 + "/" + var19, 137, pingResultY + 1, -1);
                            this.drawString(this.fontRendererObj, "\u00a72rtt min/avg/max", 242, 20, -1);
                            if (var18 != 0) {
                                String r = (var16 <= 25.0 ? "\u00a7a" : (var16 <= 50.0 ? "\u00a7e" : (var16 <= 100.0 ? "\u00a7c" : "\u00a74"))) + String.format("%.2f", var12) + "/" + String.format("%.2f", var16) + "/" + String.format("%.2f", var14);
                                this.drawString(this.fontRendererObj, r, 242, pingResultY + 1, -1);
                            } else {
                                this.drawString(this.fontRendererObj, "\u00a74Timeout", 242, pingResultY + 1, -1);
                            }
                        }
                        catch (Exception e) {
                            this.drawString(this.fontRendererObj, "\u00a77Checking...", 137, pingResultY + 1, -1);
                        }
                        pingResultY += 10;
                    }
                    break block22;
                }
                int tcpResultY = 30;
                for (FTools_CheckHostServer s : this.tcpResult.getServers()) {
                    if (this.reloadtime < 600) {
                        ++this.reloadtime;
                    } else {
                        this.tcpResult.update();
                        this.reloadtime = 0;
                    }
                    FTools_TcpResult r = this.tcpResult.getResult().get(s);
                    this.drawString(this.fontRendererObj, "\u00a72Location", 10, 20, -1);
                    this.drawString(this.fontRendererObj, "\u00a7b" + s.getCountry() + ", " + s.getCity(), 10, tcpResultY + 1, -1);
                    try {
                        this.drawString(this.fontRendererObj, "\u00a72Result", 137, 20, -1);
                        if (r.isSuccessful()) {
                            this.drawString(this.fontRendererObj, "\u00a7aConnected", 137, tcpResultY + 1, -1);
                        } else {
                            this.drawString(this.fontRendererObj, "\u00a74" + r.getError(), 137, tcpResultY + 1, -1);
                        }
                        double var12 = r.getPing();
                        var12 *= 10000.0;
                        var12 = (double)((int)var12) / 10.0;
                        this.drawString(this.fontRendererObj, "\u00a72Time", 242, 20, -1);
                        this.drawString(this.fontRendererObj, (var12 <= 25.0 ? "\u00a7a" : (var12 <= 50.0 ? "\u00a7e" : (var12 <= 100.0 ? "\u00a7c" : "\u00a74"))) + var12 + "ms", 242, tcpResultY + 1, -1);
                    }
                    catch (Exception e) {
                        this.drawString(this.fontRendererObj, "\u00a77Checking...", 137, tcpResultY + 1, -1);
                    }
                    tcpResultY += 10;
                }
            }
            catch (Exception e) {

            }
        }
        super.drawScreen(mouseX, mouseY, delta);
    }

    public GuiScreen getParent() {
        return this.parent;
    }

    public void setParent(GuiScreen parent) {
        this.parent = parent;
    }
}


