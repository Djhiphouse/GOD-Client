package net.minecraft.client.gui;

import me.bratwurst.AltManager.GuiAltManager;

import me.bratwurst.Client;
import me.bratwurst.guiMain.*;
import me.bratwurst.manager.PartikelSystem.ParticleSystem;
import me.bratwurst.utils.DrawMenuLogoUtil;
import me.bratwurst.utils.ShaderLoaderUtils;
import me.bratwurst.utils.ShaderUtils;
import me.bratwurst.utils.WbUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {
    private static final ResourceLocation youtube = new ResourceLocation("client/logo/youtube.png");
    private static final ResourceLocation discord = new ResourceLocation("client/logo/discord.png");
    private static final ResourceLocation settings = new ResourceLocation("client/logo/settingslogo.png");
    private final Object threadLock = new Object();
    private String openGLWarning1;
ParticleSystem partikelsystem = new ParticleSystem(1000,230);
   public  static ShaderUtils shaderUtilLoader = new ShaderUtils("#ifdef GL_ES\nprecision mediump float;\n#endif\n\nuniform float time;\nuniform vec2 mouse;\nuniform vec2 resolution;\n\nvec2 hash(vec2 p)\n{\n    mat2 m = mat2(  13.85, 47.77,\n                    99.41, 88.48\n                );\n\n    return fract(sin(m*p) * 46738.29);\n}\n\nfloat voronoi(vec2 p)\n{\n    vec2 g = floor(p);\n    vec2 f = fract(p);\n\n    float distanceToClosestFeaturePoint = 1.0;\n    for(int y = -1; y <= 1; y++)\n    {\n        for(int x = -1; x <= 5; x++)\n        {\n            vec2 latticePoint = vec2(x, y);\n            float currentDistance = distance(latticePoint + hash(g+latticePoint), f);\n            distanceToClosestFeaturePoint = min(distanceToClosestFeaturePoint, currentDistance);\n        }\n    }\n\n    return distanceToClosestFeaturePoint;\n}\n\nvoid main( void )\n{\n    vec2 uv = ( gl_FragCoord.xy / resolution.xy ) * 2.0 - 1.0;\n    uv.x *= resolution.x / resolution.y;\n\n    float offset = voronoi(uv*10.0 + vec2(time));\n    float t = 1.0/abs(((uv.x + sin(uv.y + time)) + offset) * 30.0);\n\n    float r = voronoi( uv * 1.0 ) * 10.0;\n    vec3 finalColor = vec3(10.0 * uv.y, 2.0, 1.0 * r) * t;\n    \n    gl_FragColor = vec4(finalColor, 1.0 );\n}");
    private String openGLWarning2;

    private String openGLWarningLink;
    public static final String field_96138_a = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
    private int field_92024_r;
    private int field_92023_s;
    private int field_92022_t;
    private int field_92021_u;
    private int field_92020_v;
    private int field_92019_w;


    public GuiMainMenu() {
        this.openGLWarning2 = field_96138_a;
        this.openGLWarning1 = "";

        if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.areShadersSupported()) {
            this.openGLWarning1 = I18n.format("title.oldgl1", new Object[0]);
            this.openGLWarning2 = I18n.format("title.oldgl2", new Object[0]);
            this.openGLWarningLink = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
        }
    }


    public void initGui() {
        int i = 24;
        int j = this.height / 4 + 48;
        ScaledResolutionGod scaledResolution = new ScaledResolutionGod(this.mc);

        //    addButton();

        this.buttonList.add(new GuiButton(0, 0, this.height / 2, this.width /7, 20, I18n.format("menu.options", new Object[0])));
        this.buttonList.add(new GuiButton(4, 0 , this.height / 2 + i * 1, this.width /7, 20, I18n.format("menu.quit", new Object[0])));
        this.buttonList.add(new GuiButton(1, 0, this.height / 2 - i * 3, this.width /7, 20, I18n.format("menu.singleplayer", new Object[0])));
        this.buttonList.add(new GuiButton(2, 0, this.height / 2 - i * 2, this.width /7, 20, I18n.format("menu.multiplayer", new Object[0])));
        this.buttonList.add(new GuiButton(14, 0, this.height / 2 - i * 1, this.width /7, 20, I18n.format("AltManager", new Object[0])));

        this.buttonList.add(new GuiLogoButton(9992 , scaledResolution, this.width - 100, this.height /6 , 35, 35, youtube, false));
        this.buttonList.add(new GuiLogoButton(9993 , scaledResolution, this.width - 100, this.height /6 +i *2, 35, 35, discord, false));
        this.buttonList.add(new GuiLogoButton(9994 , scaledResolution, this.width - 100, this.height /6 +i *4, 35, 35, settings, false));
        this.buttonList.add(new GuiLogoButton(9995, scaledResolution, 5,  5, 32, 32, loadPlayerHead(Minecraft.getMinecraft().getSession().getUsername()), true));
        synchronized (this.threadLock) {
            this.field_92023_s = this.fontRendererObj.getStringWidth(this.openGLWarning1);
            this.field_92024_r = this.fontRendererObj.getStringWidth(this.openGLWarning2);
            int k = Math.max(this.field_92023_s, this.field_92024_r);
            this.field_92022_t = (this.width - k) / 2;
            this.field_92021_u = ((GuiButton) this.buttonList.get(0)).yPosition - 24;
            this.field_92020_v = this.field_92022_t + k;
            this.field_92019_w = this.field_92021_u + 24;
        }

        this.mc.func_181537_a(false);
    }


    protected void actionPerformed(GuiButton button) throws IOException {

        if (button.id == 0) {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }
        if (button.id == 5) {
            this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
        }
        if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        }
        if (button.id == 2) {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }

        if (button.id == 14) {
            this.mc.displayGuiScreen(new GuiAltManager());
        }

        if (button.id == 4) {
            this.mc.shutdown();
        }

        if (button.id == 9992) {
                try {
                    WbUtils.openWebsite(new URI("https://www.google.de"));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
        }
        if (button.id == 9993) {
            try {
                WbUtils.openWebsite(new URI("https://www.google.de"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        if (button.id == 9994) {
            this.mc.displayGuiScreen(new GuiClientSettings());
        }
        if (button.id == 9995) {
            this.mc.displayGuiScreen(new GuiClientDashboard());
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        //Hintergrund

       this.mc.getTextureManager().bindTexture(new ResourceLocation("client/336293.png"));
       Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);

        if (GuiClientSettings.shader == true){
           renderShader();
        }else if (GuiClientSettings.particle == true) {
            render();
        }else if (GuiClientSettings.shader == false){
            DrawMenuLogoUtil.drawString(5, null, this.width / 12, this.height / 24, Color.CYAN.getRGB());
        }
        GlStateManager.color(1, 1, 1);
        int j = this.height / 4 + 48 + 10;
        //Hintergrund

        //backScreen
       // drawRect(this.width / 9 -5, this.height / 6 -3, this.width - 100, this.height / 2 + this.height / 3 -23, new Color(56, 56, 56, 255).getRGB());
        ///backScreen
        //COLOR new Color(56, 56, 56, 0).getRGB()
        drawRect( -50,  this.height, this.width /7 , 0, new Color(56, 55, 55, 134).getRGB());


        // MainMenu Logo
//        mc.getTextureManager().bindTexture(new ResourceLocation("client/log.png"));
//        drawModalRectWithCustomSizedTexture(this.width /2 - 50, this.height /2 - 100, 0.0f, 0.0f, 250, 200, 250.0f, 300.0f);
      DrawMenuLogoUtil.drawString(3, "GOD", 6, 2, Color.CYAN.getRGB());
//
// /  final String Logo = "GOD";
      //  DrawMenuLogoUtil.drawString(5, Logo, this.width / 12, this.height / 24, Color.CYAN.getRGB());




        String username = EnumChatFormatting.AQUA + "Name: " + EnumChatFormatting.BLUE + Minecraft.getMinecraft().getSession().getUsername();

        this.drawString(this.fontRendererObj, username, 2, this.height - 10, -1);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    private void drawPlayerHead(String name, double x, double y, double size) {
        GL11.glPushMatrix();
        GL11.glScaled(size, size, size);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(loadPlayerHead(name));
        DrawKopf.drawTexturedModalRect(x / size, (y - 3.0D) / size, 32.0D, 32.0D, 32.0D, 32.0D);
        DrawKopf.drawTexturedModalRect(x / size, (y - 3.0D) / size, 160.0D, 32.0D, 32.0D, 32.0D);
        GL11.glPopMatrix();
    }

    public static ResourceLocation loadPlayerHead(String name) {
        ResourceLocation resourcelocation = new ResourceLocation("avatar/" + name);
        ThreadDownloadImageData t = new ThreadDownloadImageData(null, "https://minotar.net/avatar/" + name, DefaultPlayerSkin.getDefaultSkin(UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8))), (IImageBuffer)new ImageBufferDownload());
        Minecraft.getMinecraft().getTextureManager().loadTexture(resourcelocation, (ITextureObject)t);
        return resourcelocation;
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        synchronized (this.threadLock) {
            if (this.openGLWarning1.length() > 0 && mouseX >= this.field_92022_t && mouseX <= this.field_92020_v && mouseY >= this.field_92021_u && mouseY <= this.field_92019_w) {
                GuiConfirmOpenLink guiconfirmopenlink = new GuiConfirmOpenLink(this, this.openGLWarningLink, 13, true);
                guiconfirmopenlink.disableSecurityWarning();
                this.mc.displayGuiScreen(guiconfirmopenlink);
            }
        }
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