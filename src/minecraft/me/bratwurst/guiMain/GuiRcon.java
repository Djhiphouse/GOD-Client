package me.bratwurst.guiMain;


import java.io.IOException;

import me.bratwurst.news.newutils.NewStringList;
import me.bratwurst.news.rcon.RconClient;
import me.bratwurst.utils.ShaderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;

public class GuiRcon
        extends GuiScreen {
    public  static ShaderUtils shaderUtilLoader = new ShaderUtils("#ifdef GL_ES\nprecision mediump float;\n#endif\n\nuniform float time;\nuniform vec2 mouse;\nuniform vec2 resolution;\n\nvec2 hash(vec2 p)\n{\n    mat2 m = mat2(  13.85, 47.77,\n                    99.41, 88.48\n                );\n\n    return fract(sin(m*p) * 46738.29);\n}\n\nfloat voronoi(vec2 p)\n{\n    vec2 g = floor(p);\n    vec2 f = fract(p);\n\n    float distanceToClosestFeaturePoint = 1.0;\n    for(int y = -1; y <= 1; y++)\n    {\n        for(int x = -1; x <= 5; x++)\n        {\n            vec2 latticePoint = vec2(x, y);\n            float currentDistance = distance(latticePoint + hash(g+latticePoint), f);\n            distanceToClosestFeaturePoint = min(distanceToClosestFeaturePoint, currentDistance);\n        }\n    }\n\n    return distanceToClosestFeaturePoint;\n}\n\nvoid main( void )\n{\n    vec2 uv = ( gl_FragCoord.xy / resolution.xy ) * 2.0 - 1.0;\n    uv.x *= resolution.x / resolution.y;\n\n    float offset = voronoi(uv*10.0 + vec2(time));\n    float t = 1.0/abs(((uv.x + sin(uv.y + time)) + offset) * 30.0);\n\n    float r = voronoi( uv * 1.0 ) * 10.0;\n    vec3 finalColor = vec3(10.0 * uv.y, 2.0, 1.0 * r) * t;\n    \n    gl_FragColor = vec4(finalColor, 1.0 );\n}");
    private GuiTextField ipBox;
    private GuiTextField passwordBox;
    private GuiTextField portBox;
    private GuiTextField commandBox;
    private NewStringList list;
    public String status = "";

    private GuiScreen parent;

    @Override
    public void updateScreen() {
        this.passwordBox.updateCursorCounter();
        this.ipBox.updateCursorCounter();
        this.portBox.updateCursorCounter();
        this.commandBox.updateCursorCounter();
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents((boolean)true);
        this.buttonList.clear();
        this.list = new NewStringList(this.width / 2, 0, this.width - 20, 10, this.height + 1);
        this.buttonList.add(new GuiButton(0, 5, this.height / 4 + 105 + 12, "Send"));
        this.buttonList.add(new GuiButton(1, 5, this.height / 4 + 130 + 12, "Back"));
        this.passwordBox = new GuiTextField(1, this.fontRendererObj, 5, 100, 200, 20);
        this.passwordBox.setFocused(false);
        this.passwordBox.setText("hallo123");
        this.ipBox = new GuiTextField(1, this.fontRendererObj, 5, 60, 200, 20);
        this.ipBox.setFocused(false);
        this.ipBox.setText("127.0.0.1");
        this.portBox = new GuiTextField(1, this.fontRendererObj, 5, 140, 200, 20);
        this.portBox.setFocused(false);
        this.portBox.setText("25565");
        this.commandBox = new GuiTextField(1, this.fontRendererObj, 5, 180, 200, 20);
        this.commandBox.setFocused(false);
        this.commandBox.setText("say Hallo by " + Minecraft.getMinecraft().getSession().getUsername());
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    @Override
    protected void actionPerformed(GuiButton clickedButton) {
        if (clickedButton.id == 1) {
            this.mc.displayGuiScreen(this.parent);
        } else if (clickedButton.id == 0) {
            if (!(this.passwordBox.getText().isEmpty() || this.ipBox.getText().isEmpty() || this.portBox.getText().isEmpty() || this.commandBox.getText().isEmpty())) {
                try (RconClient client = RconClient.open(this.ipBox.getText(), Integer.valueOf(this.portBox.getText()), this.passwordBox.getText());){
                    client.sendCommand(this.commandBox.getText());
                    this.list.addString(this.commandBox.getText());
                }
                catch (Exception e) {
                    this.status = "\u00a7cError!";
                }
            } else {
                this.status = "\u00a7cError!";
            }
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        this.list.handleMouseInput();
        super.handleMouseInput();
    }

    @Override
    protected void keyTyped(char par1, int par2) {
        this.passwordBox.textboxKeyTyped(par1, par2);
        this.ipBox.textboxKeyTyped(par1, par2);
        this.portBox.textboxKeyTyped(par1, par2);
        this.commandBox.textboxKeyTyped(par1, par2);
        if (par2 == 28 || par2 == 156) {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) throws IOException {
        super.mouseClicked(par1, par2, par3);
        this.passwordBox.mouseClicked(par1, par2, par3);
        this.ipBox.mouseClicked(par1, par2, par3);
        this.portBox.mouseClicked(par1, par2, par3);
        this.commandBox.mouseClicked(par1, par2, par3);
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        this.drawString(this.fontRendererObj, "\u00a7b\u00a7lRcon " + this.status, 5, 20, 0xFFFFFF);
        this.drawString(this.fontRendererObj, "IP", 5, 47, 0xA0A0A0);
        this.drawString(this.fontRendererObj, "Password", 5, 87, 0xA0A0A0);
        this.drawString(this.fontRendererObj, "Port", 5, 127, 0xA0A0A0);
        this.drawString(this.fontRendererObj, "Command", 5, 167, 0xA0A0A0);
        this.ipBox.drawTextBox();
        this.passwordBox.drawTextBox();
        this.portBox.drawTextBox();
        this.commandBox.drawTextBox();
        this.list.drawList(par1, par2);
        super.drawScreen(par1, par2, par3);
    }

    public GuiRcon( GuiScreen parent) {
        if (parent == null) {
            throw new NullPointerException("parent is marked non-null but is null");
        }
        this.parent = parent;
    }
    public static void renderShader() {
        shaderUtilLoader.renderFirst();
        shaderUtilLoader.addDefaultUniforms();
        shaderUtilLoader.renderSecond();
    }
}

