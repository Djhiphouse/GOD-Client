package me.bratwurst.guiMain;

import me.bratwurst.manager.PartikelSystem.ParticleSystem;
import me.bratwurst.utils.DrawMenuLogoUtil;
import me.bratwurst.utils.ShaderUtils;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class GuiParticleSetting extends GuiScreen {
    public static boolean shader = true;
    public  static ShaderUtils shaderUtilLoader = new ShaderUtils("#ifdef GL_ES\nprecision mediump float;\n#endif\n\nuniform float time;\nuniform vec2 mouse;\nuniform vec2 resolution;\n\nvec2 hash(vec2 p)\n{\n    mat2 m = mat2(  13.85, 47.77,\n                    99.41, 88.48\n                );\n\n    return fract(sin(m*p) * 46738.29);\n}\n\nfloat voronoi(vec2 p)\n{\n    vec2 g = floor(p);\n    vec2 f = fract(p);\n\n    float distanceToClosestFeaturePoint = 1.0;\n    for(int y = -1; y <= 1; y++)\n    {\n        for(int x = -1; x <= 5; x++)\n        {\n            vec2 latticePoint = vec2(x, y);\n            float currentDistance = distance(latticePoint + hash(g+latticePoint), f);\n            distanceToClosestFeaturePoint = min(distanceToClosestFeaturePoint, currentDistance);\n        }\n    }\n\n    return distanceToClosestFeaturePoint;\n}\n\nvoid main( void )\n{\n    vec2 uv = ( gl_FragCoord.xy / resolution.xy ) * 2.0 - 1.0;\n    uv.x *= resolution.x / resolution.y;\n\n    float offset = voronoi(uv*10.0 + vec2(time));\n    float t = 1.0/abs(((uv.x + sin(uv.y + time)) + offset) * 30.0);\n\n    float r = voronoi( uv * 1.0 ) * 10.0;\n    vec3 finalColor = vec3(10.0 * uv.y, 2.0, 1.0 * r) * t;\n    \n    gl_FragColor = vec4(finalColor, 1.0 );\n}");
    ParticleSystem partikelsystem = new ParticleSystem(1000,230);
    public void initGui() {


        this.buttonList.add(new GuiButton(112, this.width / 2 - 155 , this.height / 6 + 48 - 6, 150, 20, I18n.format(EnumChatFormatting.GREEN + "ON")));
        this.buttonList.add(new GuiButton(111, this.width / 2 - 155 +155, this.height / 6 + 48 - 6, 150, 20, I18n.format(EnumChatFormatting.RED + "OFF")));

        this.buttonList.add(new GuiButton(113, this.width / 2 - 155 , this.height / 3 + 48 - 6, 150, 20, I18n.format(EnumChatFormatting.GREEN + "ON")));
        this.buttonList.add(new GuiButton(114, this.width / 2 - 155 +155, this.height / 3 + 48 - 6, 150, 20, I18n.format(EnumChatFormatting.RED + "OFF")));
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));

    }
    public static int clicked = 1;
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if(button.id == 200){
            mc.displayGuiScreen(new GuiClientSettings());
        }
        if(button.id == 111){
            GuiClientSettings.particle = false;
            mc.displayGuiScreen(new GuiMainMenu());
        }
        if(button.id == 112){
            GuiClientSettings.particle = true;
            mc.displayGuiScreen(new GuiMainMenu());
        }
        if(button.id == 113){
            GuiClientSettings.shader = true;
            mc.displayGuiScreen(new GuiMainMenu());
        }
        if(button.id == 114){
            GuiClientSettings.shader = false;
            mc.displayGuiScreen(new GuiMainMenu());
        }

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        //Hintergrund
        this.mc.getTextureManager().bindTexture(new ResourceLocation("client/336293.png"));
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
        render();
        //backScreen
        drawRect(this.width / 9 - 15, this.height / 6 - 25, this.width - 50, this.height / 2 + this.height / 3, new Color(56, 56, 56, 255).getRGB());
        final String Logo = "Particle";
        DrawMenuLogoUtil.drawString(3, Logo, this.width / 7, this.height / 20, Color.CYAN.getRGB());

        render();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    public void render() {
        partikelsystem.render();
        partikelsystem.tick(15);
    }
    public static void renderShader() {
        shaderUtilLoader.renderFirst();
        shaderUtilLoader.addDefaultUniforms();
        shaderUtilLoader.renderSecond();
    }
}

