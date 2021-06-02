package me.bratwurst.AltManager;

import de.Hero.clickgui.util.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuiAltManager extends GuiScreen {
    private GuiButton login;
    private GuiButton remove;
    private GuiButton rename;
    private AltLoginThread loginThread;
    private int offset;
    public Alt selectedAlt;
    private String status;

    public GuiAltManager() {
        this.selectedAlt = null;
        this.status = EnumChatFormatting.GRAY + "Idle...";
    }

    public void drawD(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution s1 = new ScaledResolution(this.mc);
        GlStateManager.enableAlpha();
        GlStateManager.disableCull();
        GL11.glBegin(7);
        GL11.glVertex2f(-1.0F, -1.0F);
        GL11.glVertex2f(-1.0F, 1.0F);
        GL11.glVertex2f(1.0F, 1.0F);
        GL11.glVertex2f(1.0F, -1.0F);
        GL11.glEnd();
        GL20.glUseProgram(0);
    }

    public void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0: {

                break;
            }
            case 1: {
                (this.loginThread = new AltLoginThread(selectedAlt)).start();
                break;
            }
            case 2: {
                if (this.loginThread != null) {
                    this.loginThread = null;
                }
                AltManager.registry.remove(this.selectedAlt);
                this.status = "§aRemoved.";
                this.selectedAlt = null;
                break;
            }
            case 3: {
                this.mc.displayGuiScreen(new GuiAddAlt(this));
                break;
            }
            case 4: {
                this.mc.displayGuiScreen(new GuiAltLogin(this));
                break;
            }
            case 5: {
                final ArrayList<Alt> registry = AltManager.registry;
                final Random random = new Random();
                if (registry != null && AltManager.registry.size() != 0) {
                    final Alt randomAlt = registry.get(random.nextInt(AltManager.registry.size()));
                    (this.loginThread = new AltLoginThread(randomAlt)).start();
                } else {
                    status = "You hava no Alts";
                }
                break;
            }
            case 6: {
                this.mc.displayGuiScreen(new GuiRenameAlt(this));
                break;
            }
            case 7: {
                this.mc.displayGuiScreen(null);
                break;
            }
            case 8: {
                AltManager.registry.clear();
                this.status = EnumChatFormatting.RED + "Reloaded!";
                break;
            }
            case 9: {
                mc.displayGuiScreen(new GuiTheAltening(this));
            }
        }
    }

    public static String byPseey = "by Pseey";

    public static void rectangle(double left, double top, double right, double bottom, int color) {
        Gui.drawRect(left, top, right, bottom, color);
    }

    private ResourceLocation background = new ResourceLocation("client/AltManager.jpg");
    int lastOffset = 0;


    @Override
    public void drawScreen(final int par1, final int par2, final float par3) {
        drawD(par1, par2, par3);
        if (Mouse.hasWheel()) {
            final int wheel = Mouse.getDWheel();
            if (wheel < 0) {
                this.offset += 26;
                if (this.offset < 0) {
                    this.offset = 0;
                }
            } else if (wheel > 0) {
                this.offset -= 26;
                if (this.offset < 0) {
                    this.offset = 0;
                }
            }
        }

        ScaledResolution s = new ScaledResolution(mc);
        /* DO NOT REMOVE											*/
        GL11.glPushMatrix();
        /* copyright HeroCode 2017									*/
        GL11.glTranslated(s.getScaledWidth(), s.getScaledHeight(), 0);
        GL11.glScaled(0.5, 0.5, 0.5);

        FontUtil.drawStringWithShadow(byPseey, -Minecraft.getMinecraft().fontRendererObj.getStringWidth(byPseey), -Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT, 0xff11F86B);
        /*															*/
        GL11.glPopMatrix();

        if (lastOffset != offset) {
            int diff = offset - lastOffset;
            lastOffset += diff / 4;
        }
        ScaledResolution res = new ScaledResolution(mc);
        int w = res.getScaledWidth();
        int h = res.getScaledHeight();
        mc.getTextureManager().bindTexture(new ResourceLocation("client/altmanager/Backhub.png"));
        drawScaledCustomSizeModalRect(0, 0, 0, 0, w + 2, h, w + 2, h, w + 2, h);

        rectangle(0, 0, res.getScaledWidth(), res.

                getScaledHeight(), new

                Color(0, 0, 0, 60).

                getRGB());
        mc.fontRendererObj.drawString(this.mc.session.getUsername(), 10, 10, 14540253);
        final StringBuilder sb = new StringBuilder(EnumChatFormatting.AQUA +"Alt Manager - ");
        mc.fontRendererObj.drawString(sb.append(AltManager.registry.size()).

                append(" Alts").

                toString(), this.width / 2, 5, -1);
        mc.fontRendererObj.drawString((this.loginThread == null) ? this.status : this.loginThread.getStatus(), this.width / 2, 15, -1);
        Gui.drawShadowedRect(50.0f, 33.0f, this.width - 50, this.height - 50, Integer.MIN_VALUE);
        GL11.glPushMatrix();
        this.

                prepareScissorBox(0.0f, 33.0f, this.width, this.height - 50);
        GL11.glEnable(3089);
        int y = 38;
        for (
                final Alt alt :

                getAlts()) {
            if (isAltInArea(y)) {
                String name;
                if (alt.getMask().equals("")) {
                    name = alt.getUsername();
                } else {
                    name = alt.getMask();
                }
                String pass;
                if (alt.getPassword().equals("")) {
                    pass = EnumChatFormatting.RED + "Cracked";
                } else {
                    pass = alt.getPassword().replaceAll(".", "*");
                }
                if (alt == this.selectedAlt) {
                    if (this.isMouseOverAlt(par1, par2, y - this.offset) && Mouse.isButtonDown(0)) {
                        Gui.drawShadowedRect(52.0f, y - this.lastOffset - 4, this.width - 52, y - this.lastOffset + 20, new Color(0, 0, 0, 60).getRGB());
                    } else if (this.isMouseOverAlt(par1, par2, y - this.lastOffset)) {
                        Gui.drawShadowedRect(52.0f, y - this.lastOffset - 4, this.width - 52, y - this.lastOffset + 20, new Color(0, 0, 0, 60).getRGB());
                    } else {
                        Gui.drawShadowedRect(52.0f, y - this.lastOffset - 4, this.width - 52, y - this.lastOffset + 20, new Color(0, 0, 0, 60).getRGB());
                    }
                } else if (this.isMouseOverAlt(par1, par2, y - this.offset) && Mouse.isButtonDown(0)) {
                    Gui.drawShadowedRect(52.0f, y - this.lastOffset - 4, this.width - 52, y - this.lastOffset + 20, -new Color(0, 0, 0, 60).getRGB());
                } else if (this.isMouseOverAlt(par1, par2, y - this.lastOffset)) {
                    Gui.drawShadowedRect(52.0f, y - this.lastOffset - 4, this.width - 52, y - this.lastOffset + 20, new Color(0, 0, 0, 60).getRGB());
                }
                mc.fontRendererObj.drawString(name, this.width / 2, y - this.lastOffset, -1);
                mc.fontRendererObj.drawString(pass, this.width / 2, y - this.lastOffset + 10, Colors.getColor(110));
                y += 26;
            }
        }
        GL11.glDisable(3089);
        GL11.glPopMatrix();

        super.

                drawScreen(par1, par2, par3);
        if (this.selectedAlt == null) {
            this.login.enabled = false;
            this.remove.enabled = false;
            this.rename.enabled = false;
        } else {
            this.login.enabled = true;
            this.remove.enabled = true;
            this.rename.enabled = true;
        }
        if (Keyboard.isKeyDown(200)) {
            this.offset -= 26;
        } else if (Keyboard.isKeyDown(208)) {
            this.offset += 26;
        }
        if (this.offset < 0) {
            this.offset = 0;
        }

    }

    @Override
    public void initGui() {
        this.buttonList.add(this.login = new GuiButton(1, this.width / 2 - 122, this.height - 48, 100, 20, EnumChatFormatting.AQUA +"Login"));
        this.buttonList.add(this.remove = new GuiButton(2, this.width / 2 - 40, this.height - 24, 70, 20, EnumChatFormatting.AQUA +"Remove"));
        this.buttonList.add(new GuiButton(3, this.width / 2 + 4 + 86, this.height - 48, 100, 20, EnumChatFormatting.AQUA +"Add"));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 16, this.height - 48, 100, 20, EnumChatFormatting.AQUA +"Direct Login"));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 122, this.height - 24, 78, 20, EnumChatFormatting.AQUA +"Random"));
        this.buttonList.add(this.rename = new GuiButton(6, this.width / 2 + 38, this.height - 24, 70, 20, EnumChatFormatting.AQUA +"Edit"));
        this.buttonList.add(new GuiButton(7, this.width / 2 - 190, this.height - 24, 60, 20, EnumChatFormatting.AQUA +"Back"));
        this.buttonList.add(new GuiButton(8, this.width / 2 - 190, this.height - 48, 60, 20, EnumChatFormatting.AQUA +"Reload"));
        this.buttonList.add(new GuiButton(9, this.width / 2 + 150, this.height - 24, 70, 20, EnumChatFormatting.AQUA +"Altening"));
        this.login.enabled = false;
        this.remove.enabled = false;
        this.rename.enabled = false;
    }

    @Override
    protected void keyTyped(final char par1, final int par2) {
        try {
            super.keyTyped(par1, par2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isAltInArea(final int y) {
        return y - this.offset <= this.height - 50;
    }

    private boolean isMouseOverAlt(final int x, final int y, final int y1) {
        return x >= 52 && y >= y1 - 4 && x <= this.width - 52 && y <= y1 + 20 && x >= 0 && y >= 33 && x <= this.width && y <= this.height - 50;
    }

    @Override
    protected void mouseClicked(final int par1, final int par2, final int par3) {
        if (this.offset < 0) {
            this.offset = 0;
        }
        int y = 38 - this.offset;
        for (final Alt alt : getAlts()) {
            if (isMouseOverAlt(par1, par2, y)) {
                if (alt == this.selectedAlt) {
                    (this.loginThread = new AltLoginThread(selectedAlt)).start();
                    return;
                }
                this.selectedAlt = alt;
            }
            y += 26;
        }
        try {
            super.mouseClicked(par1, par2, par3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Alt> getAlts() {
        List<Alt> altList = new ArrayList<>();
        for (final Alt alt : AltManager.registry) {
            altList.add(alt);
        }
        return altList;
    }

    public void prepareScissorBox(final float x, final float y, final float x2, final float y2) {
        final ScaledResolution scale = new ScaledResolution(this.mc);
        final int factor = scale.getScaleFactor();
        GL11.glScissor((int) (x * factor), (int) ((scale.getScaledHeight() - y2) * factor), (int) ((x2 - x) * factor), (int) ((y2 - y) * factor));
    }

}
