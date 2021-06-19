package me.bratwurst.news.newutils;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class NewStringList {
    public int x;
    public int y;
    public int width;
    public int height;
    public int heightBetweenStrings;
    public int scroll;
    private ArrayList<String> strings;
    public boolean visible = true;
    public boolean drawBackground = true;
    private boolean hovered;

    public NewStringList(int x, int y, int width, int height, int heightBetweenStrings, ArrayList<String> strings) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.heightBetweenStrings = heightBetweenStrings;
        this.strings = strings;
    }

    public NewStringList(int x, int y, int width, int heightBetweenStrings, int height) {
        this(x, y, width, height, heightBetweenStrings, new ArrayList<String>());
    }

    public void addString(String string) {
        this.strings.add(string);
    }

    public void setStrings(ArrayList<String> strings) {
        this.strings.clear();
        this.strings.addAll(strings);
    }

    public void addStrings(ArrayList<String> strings) {
        this.strings.addAll(strings);
    }

    public void clearStringList() {
        this.strings.clear();
    }

    public void removeString(String string) {
        this.strings.remove(string);
    }

    public ArrayList<String> getStrings() {
        return this.strings;
    }

    public void handleMouseInput() {
        if (!this.visible) {
            return;
        }
        int i = Mouse.getEventDWheel();
        if (i >= 1) {
            i = -1;
        } else if (i <= -1) {
            i = 1;
        }
        if (this.getListHeight() <= this.height) {
            return;
        }
        if (this.scroll + i <= 0) {
            this.scroll = 0;
            return;
        }
        if (this.scroll + i >= this.getListHeight() - this.height) {
            this.scroll = this.getListHeight() - this.height;
            return;
        }
        this.scroll += i * 15;
    }

    public void drawList(int mouseX, int mouseY) {
        int i;
        boolean bl = this.hovered = mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y && mouseY < this.y + this.height;
        if (!this.visible) {
            return;
        }
        ArrayList<String> l = new ArrayList<String>();
        for (i = 0; i < this.strings.size(); ++i) {
            List<IChatComponent> list = GuiUtilRenderComponents.func_178908_a(new ChatComponentText(this.strings.get(i)), this.width, Minecraft.fontRendererObj, false, true);
            for (int j = 0; j < list.size(); ++j) {
                ChatComponentText objectline = (ChatComponentText)list.get(j);
                String text = objectline.getFormattedText();
                l.add(text);
            }
        }
        this.strings.clear();
        this.strings.addAll(l);
        if (this.drawBackground) {
            Gui.drawRect(this.x + 1, this.y + 1, this.x + this.width - 1, this.y + this.height - 1, Integer.MIN_VALUE);
            this.scissorBox(this.x, this.y, this.x + this.width, this.y + this.height);
        }
        i = 0;
        while (i < this.strings.size()) {
            FontRenderer fontRendererObj = Minecraft.fontRendererObj;
            String text = this.strings.get(i);
            float x = this.x + 2;
            int n = this.y + 2;
            int n2 = i++;
            FontRenderer fontRendererObj2 = Minecraft.fontRendererObj;
            fontRendererObj.drawStringWithShadow(text, x, n + n2 * (Minecraft.fontRendererObj.FONT_HEIGHT + this.heightBetweenStrings) - this.scroll, 0xFFFFFF);
        }
        if (this.drawBackground) {
            this.disableScissors();
        }
        int size = this.strings.size();
        FontRenderer fontRendererObj3 = Minecraft.fontRendererObj;
        int stringHeight = size * (Minecraft.fontRendererObj.FONT_HEIGHT + this.heightBetweenStrings);
        int maxY = stringHeight - this.height;
        if (maxY > 0) {
            int sliderHeight = (int)((double)this.height / (double)stringHeight * (double)this.height);
            int sliderWidth = 3;
            double d = (double)this.y + (double)(this.height - sliderHeight) * ((double)this.scroll / (double)maxY);
        }
    }

    public void scissorBox(int x, int y, int xend, int yend) {
        int width = xend - x;
        int height = yend - y;
        ScaledResolution sr = new ScaledResolution();
        int factor = sr.getScaleFactor();
        int bottomY = sr.getScaledHeight() - yend;
        GL11.glScissor((int)(x * factor), (int)(bottomY * factor), (int)(width * factor), (int)(height * factor));
        GL11.glEnable((int)3089);
    }

    public void disableScissors() {
        GL11.glDisable((int)3089);
    }

    public int getListHeight() {
        int size = this.strings.size();
        FontRenderer fontRendererObj = Minecraft.fontRendererObj;
        return size * (Minecraft.fontRendererObj.FONT_HEIGHT + this.heightBetweenStrings);
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0).endVertex();
        worldrenderer.pos(right, bottom, 0.0).endVertex();
        worldrenderer.pos(right, top, 0.0).endVertex();
        worldrenderer.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}


