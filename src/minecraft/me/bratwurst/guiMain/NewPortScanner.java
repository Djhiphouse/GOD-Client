package me.bratwurst.guiMain;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;

public class NewPortScanner
        extends GuiScreen {
    private final GuiScreen prevGui;
    private GuiTextField hostField;
    private GuiTextField minPortField;
    private GuiTextField maxPortField;
    private GuiTextField threadsField;
    private GuiButton buttonToggle;
    private boolean running;
    private String status = "\u00a77Wating...";
    private String host;
    private int currentPort;
    private int maxPort;
    private int minPort;
    private int checkedPort;
    private final List<Integer> ports = new ArrayList<Integer>();

    public NewPortScanner(GuiScreen prevGui) {
        this.prevGui = prevGui;
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents((boolean)true);
        this.hostField = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        this.hostField.setFocused(true);
        this.hostField.setMaxStringLength(Integer.MAX_VALUE);
        this.hostField.setText("localhost");
        this.minPortField = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 100, 90, 90, 20);
        this.minPortField.setMaxStringLength(5);
        this.minPortField.setText(String.valueOf(1));
        this.maxPortField = new GuiTextField(2, this.fontRendererObj, this.width / 2 + 10, 90, 90, 20);
        this.maxPortField.setMaxStringLength(5);
        this.maxPortField.setText(String.valueOf(65535));
        this.threadsField = new GuiTextField(3, this.fontRendererObj, this.width / 2 - 100, 120, 200, 20);
        this.threadsField.setMaxStringLength(Integer.MAX_VALUE);
        this.threadsField.setText(String.valueOf(500));
        this.buttonToggle = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 95, this.running ? "Stop" : "Start");
        this.buttonList.add(this.buttonToggle);
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, "Back"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 155, "Export"));
        super.initGui();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        NewPortScanner.drawCenteredString(this.fontRendererObj, "Port Scanner", this.width / 2, 34, 0xFFFFFF);
        NewPortScanner.drawCenteredString(this.fontRendererObj, this.running ? "\u00a77" + this.checkedPort + " \u00a78/ \u00a77" + this.maxPort : (this.status == null ? "" : this.status), this.width / 2, this.height / 4 + 80, 0xFFFFFF);
        this.buttonToggle.displayString = this.running ? "Stop" : "Start";
        this.hostField.drawTextBox();
        this.minPortField.drawTextBox();
        this.maxPortField.drawTextBox();
        this.threadsField.drawTextBox();
        this.drawString(this.fontRendererObj, "\u00a7c\u00a7lPorts:", 2, 2, Color.WHITE.hashCode());
        List<Integer> list = this.ports;
        synchronized (list) {
            int i = 12;
            for (Integer integer : this.ports) {
                this.drawString(this.fontRendererObj, String.valueOf(integer), 2, i, Color.WHITE.hashCode());
                i += this.fontRendererObj.FONT_HEIGHT;
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0: {
                this.mc.displayGuiScreen(this.prevGui);
                break;
            }
            case 1: {
                if (this.running) {
                    this.running = false;
                } else {
                    int threads;
                    this.host = this.hostField.getText();
                    if (this.host.isEmpty()) {
                        this.status = "\u00a7cInvalid host";
                        return;
                    }
                    try {
                        this.minPort = Integer.parseInt(this.minPortField.getText());
                    }
                    catch (NumberFormatException e) {
                        this.status = "\u00a7cInvalid min port";
                        return;
                    }
                    try {
                        this.maxPort = Integer.parseInt(this.maxPortField.getText());
                    }
                    catch (NumberFormatException e) {
                        this.status = "\u00a7cInvalid max port";
                        return;
                    }
                    try {
                        threads = Integer.parseInt(this.threadsField.getText());
                    }
                    catch (NumberFormatException e) {
                        this.status = "\u00a7cInvalid threads";
                        return;
                    }
                    this.ports.clear();
                    this.currentPort = this.minPort - 1;
                    this.checkedPort = this.minPort;
                    for (int i = 0; i < threads; ++i) {
                        new Thread(() -> {
                            try {
                                while (this.running && this.currentPort < this.maxPort) {
                                    int port = ++this.currentPort;
                                    try {
                                        Socket socket = new Socket();
                                        socket.connect(new InetSocketAddress(this.host, port), 500);
                                        socket.close();
                                        List<Integer> list = this.ports;
                                        synchronized (list) {
                                            if (!this.ports.contains(port)) {
                                                this.ports.add(port);
                                            }
                                        }
                                    }
                                    catch (Exception exception) {
                                        // empty catch block
                                    }
                                    if (this.checkedPort >= port) continue;
                                    this.checkedPort = port;
                                }
                                this.running = false;
                                this.buttonToggle.displayString = "Start";
                            }
                            catch (Exception e) {
                                this.status = "\u00a7a\u00a7l" + e.getClass().getSimpleName() + ": \u00a7c" + e.getMessage();
                            }
                        }).start();
                    }
                    this.running = true;
                }
                this.buttonToggle.displayString = this.running ? "Stop" : "Start";
                break;
            }
            case 2: {
                File selectedFile = this.saveFileChooser();
                if (selectedFile == null || selectedFile.isDirectory()) {
                    return;
                }
                try {
                    if (!selectedFile.exists()) {
                        selectedFile.createNewFile();
                    }
                    FileWriter fileWriter = new FileWriter(selectedFile);
                    fileWriter.write("Portscan\r\n");
                    fileWriter.write("Host: " + this.host + "\r\n\r\n");
                    fileWriter.write("Ports (" + this.minPort + " - " + this.maxPort + "):\r\n");
                    for (Integer integer : this.ports) {
                        fileWriter.write(integer + "\r\n");
                    }
                    fileWriter.flush();
                    fileWriter.close();
                    JOptionPane.showMessageDialog(null, "Exported successful!", "Port Scanner", 1);
                    break;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.actionPerformed(button);
    }

    public File saveFileChooser() {
        if (this.mc.isFullScreen()) {
            this.mc.toggleFullscreen();
        }
        JFileChooser fileChooser = new JFileChooser();
        JFrame frame = new JFrame();
        fileChooser.setFileSelectionMode(0);
        frame.setVisible(true);
        frame.toFront();
        frame.setVisible(false);
        int action = fileChooser.showSaveDialog(frame);
        frame.dispose();
        return action == 0 ? fileChooser.getSelectedFile() : null;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (1 == keyCode) {
            this.mc.displayGuiScreen(this.prevGui);
            return;
        }
        if (this.running) {
            return;
        }
        if (this.hostField.isFocused()) {
            this.hostField.textboxKeyTyped(typedChar, keyCode);
        }
        if (this.minPortField.isFocused() && !Character.isLetter(typedChar)) {
            this.minPortField.textboxKeyTyped(typedChar, keyCode);
        }
        if (this.maxPortField.isFocused() && !Character.isLetter(typedChar)) {
            this.maxPortField.textboxKeyTyped(typedChar, keyCode);
        }
        if (this.threadsField.isFocused() && !Character.isLetter(typedChar)) {
            this.threadsField.textboxKeyTyped(typedChar, keyCode);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.hostField.mouseClicked(mouseX, mouseY, mouseButton);
        this.minPortField.mouseClicked(mouseX, mouseY, mouseButton);
        this.maxPortField.mouseClicked(mouseX, mouseY, mouseButton);
        this.threadsField.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void updateScreen() {
        this.hostField.updateCursorCounter();
        this.minPortField.updateCursorCounter();
        this.maxPortField.updateCursorCounter();
        this.threadsField.updateCursorCounter();
        super.updateScreen();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
        this.running = false;
        super.onGuiClosed();
    }
}


