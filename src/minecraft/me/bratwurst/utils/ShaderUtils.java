package me.bratwurst.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ShaderUtils {
    private static final String VERTEX_SHADER = "#version 130\n\nvoid main() {\n    gl_TexCoord[0] = gl_MultiTexCoord0;\n    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n}";
    private Minecraft mc = Minecraft.getMinecraft();
    private int program = GL20.glCreateProgram();
    private long startTime = System.currentTimeMillis();

    public ShaderUtils(String fragment) {
        this.initShader(fragment);
    }

    private void initShader(String frag) {
        int vertex = GL20.glCreateShader(35633);
        int fragment = GL20.glCreateShader(35632);
        GL20.glShaderSource(vertex, VERTEX_SHADER);
        GL20.glShaderSource(fragment, frag);
        GL20.glValidateProgram(this.program);
        GL20.glCompileShader(vertex);
        GL20.glCompileShader(fragment);
        GL20.glAttachShader(this.program, vertex);
        GL20.glAttachShader(this.program, fragment);
        GL20.glLinkProgram(this.program);
    }

    public void renderFirst() {
        GL11.glClear(16640);
        GL20.glUseProgram(this.program);
    }

    public void renderSecond() {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        ScaledResolution sr = new ScaledResolution(this.mc);
        GL11.glBegin(7);
        GL11.glTexCoord2d(0.0, 1.0);
        GL11.glVertex2d(0.0, 0.0);
        GL11.glTexCoord2d(0.0, 0.0);
        GL11.glVertex2d(0.0, sr.getScaledHeight());
        GL11.glTexCoord2d(1.0, 0.0);
        GL11.glVertex2d(sr.getScaledWidth(), sr.getScaledHeight());
        GL11.glTexCoord2d(1.0, 1.0);
        GL11.glVertex2d(sr.getScaledWidth(), 0.0);
        GL11.glEnd();
        GL20.glUseProgram(0);
    }

    public void bind() {
        GL20.glUseProgram(this.program);
    }

    public int getProgram() {
        return this.program;
    }

    public ShaderUtils uniform1i(String loc, int i) {
        GL20.glUniform1i(GL20.glGetUniformLocation(this.program, loc), i);
        return this;
    }

    public ShaderUtils uniform2i(String loc, int i, int i1) {
        GL20.glUniform2i(GL20.glGetUniformLocation(this.program, loc), i, i1);
        return this;
    }

    public ShaderUtils uniform3i(String loc, int i, int i1, int i2) {
        GL20.glUniform3i(GL20.glGetUniformLocation(this.program, loc), i, i1, i2);
        return this;
    }

    public ShaderUtils uniform4i(String loc, int i, int i1, int i2, int i3) {
        GL20.glUniform4i(GL20.glGetUniformLocation(this.program, loc), i, i1, i2, i3);
        return this;
    }

    public ShaderUtils uniform1f(String loc, float f) {
        GL20.glUniform1f(GL20.glGetUniformLocation(this.program, loc), f);
        return this;
    }

    public ShaderUtils uniform2f(String loc, float f, float f1) {
        GL20.glUniform2f(GL20.glGetUniformLocation(this.program, loc), f, f1);
        return this;
    }

    public ShaderUtils uniform3f(String loc, float f, float f1, float f2) {
        GL20.glUniform3f(GL20.glGetUniformLocation(this.program, loc), f, f1, f2);
        return this;
    }

    public ShaderUtils uniform4f(String loc, float f, float f1, float f2, float f3) {
        GL20.glUniform4f(GL20.glGetUniformLocation(this.program, loc), f, f1, f2, f3);
        return this;
    }

    public ShaderUtils uniform1b(String loc, boolean b) {
        GL20.glUniform1i(GL20.glGetUniformLocation(this.program, loc), b ? 1 : 0);
        return this;
    }

    public void addDefaultUniforms() {
        GL20.glUniform2f(GL20.glGetUniformLocation(this.program, "resolution"), this.mc.displayWidth, this.mc.displayHeight);
        float time = (float)(System.currentTimeMillis() - this.startTime) / 1000.0f;
        GL20.glUniform1f(GL20.glGetUniformLocation(this.program, "time"), time);
    }
}

