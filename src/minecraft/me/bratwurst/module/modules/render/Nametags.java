package me.bratwurst.module.modules.render;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class Nametags extends Module {

    public static Setting mode1;
    public static EntityLivingBase target1;
    public static Setting minCps, size, Reach, Throughwalls, Rotate,ShowHealth,HealthMode,background,Movefix,Smoth;
    public Nametags() {
        super("Nametags", Category.RENDER);
    }
    @Override
    public void setup() {

        Client.setmgr.rSetting(size = new Setting("size",  this, 1, 1,5, true));
        Client.setmgr.rSetting(Reach = new Setting("Reach", this, 100, 10,500, false));
        Client.setmgr.rSetting(Throughwalls = new Setting("Throughwalls", this, false));
        Client.setmgr.rSetting(ShowHealth = new Setting("ShowHealth", this, false));
        Client.setmgr.rSetting(HealthMode = new Setting("HealthMode", this, false));
        Client.setmgr.rSetting(background = new Setting("background", this, false));

    }


    @EventTarget
    public void ProcessPacketEvent(ProcessPacketEvent e) {
        PlayerUtils.sendMessage("Call");
        if(mc.thePlayer != null && mc.theWorld != null){
            for(EntityPlayer entity : mc.theWorld.playerEntities){
                if(mc.thePlayer.getDistanceToEntity(entity) > Reach.getValInt())
                    continue;
                if(entity != mc.thePlayer){
                    if(!entity.canEntityBeSeen(mc.thePlayer)){
                        continue;
                    }else {
                        String health = null;

                        health = entity.getHealth() + "";

                    }

                    String name = null;
                    if(ShowHealth.getValBoolean()){
                        if(entity.isEntityAlive()){
                            name = entity.getName() + " $" + entity.getHealth();
                        }
                    }else{
                        name = entity.getName();

                    }

                    GL11.glPushMatrix();
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glNormal3f(0,1,0);
                    GlStateManager.disableBlend();
                    GlStateManager.enableBlend();
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);

                    float pI = mc.timer.renderPartialTicks;
                    double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * pI - RenderManager.renderPosX;
                    double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * pI - RenderManager.renderPosY + 1.2;
                    double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * pI - RenderManager.renderPosZ;

                    float d = mc.thePlayer.getDistanceToEntity(entity);
                    float s  = Math.min(Math.max(1.2f * (d*0.15F), 1.25F), 16F) * ((float) size.getValDouble()/100);

                    GlStateManager.translate((float) x, (float) y + entity.height + 0.5F - (entity.height / 2), (float) z);
                    GL11.glNormal3f(0,1,0);
                    GlStateManager.rotate(- mc.getRenderManager().playerViewY, 0,1,0);
                    GlStateManager.rotate(mc.getRenderManager().playerViewX, 1,0,0);

                    GL11.glScalef(-s,-s,s);
                    FontRenderer font = Minecraft.fontRendererObj;

                    float string_width = font.getStringWidth(name) / 2;

                    if(this.background.getValBoolean()){
                        Gui.drawRect(-font.getStringWidth(name)/ 2 - 2, -2, string_width + 2, 9, Integer.MIN_VALUE);

                        GL11.glEnable(GL11.GL_TEXTURE_2D);

                        font.drawString(name, -string_width, 0, -1);

                        GL11.glEnable(GL11.GL_DEPTH_TEST);
                        GlStateManager.disableBlend();
                        GL11.glDisable(GL11.GL_BLEND);
                        GL11.glColor4f(1,1,1,1);
                        GL11.glNormal3f(1,1,1);
                        GL11.glPopMatrix();


                    }


                }
            }
        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
