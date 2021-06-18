package me.bratwurst.module.modules.render;

import de.Hero.clickgui.util.FontUtil;
import javafx.scene.effect.Effect;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.Event2D;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.RandomObjectArraylist;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class SuperHeroFX extends Module {
    private static transient RandomObjectArraylist<Integer> colors = new RandomObjectArraylist<Integer>(0xff0000, 0x00ff00, 0x0000ff, 0xffff00, 0xff00ff, 0x00ffff, 0xffffff);
    private static transient CopyOnWriteArrayList<fx> effects = new CopyOnWriteArrayList();
    private static transient RandomObjectArraylist<String> strings = new RandomObjectArraylist<String>("Boom", "Kapow");
    public SuperHeroFX() {
        super("SuperHeroFX", Category.RENDER);

    }

    @EventTarget
    public void onRender(Event2D e) {
        for (fx f : effects) {

            if (System.currentTimeMillis() > f.ttl && f.opacity <= 5) {
                effects.remove(f);
            } else {

                if (System.currentTimeMillis() > f.ttl) {
                    f.opacity -= f.opacity / 200;
                }

                f.x += (f.motX / 10);
                f.y += (f.motY / 10);
                f.z += (f.motZ / 10);
                f.motX -= f.motX / 20;
                f.motY -= f.motY / 20;
                f.motZ -= f.motZ / 20;

                String text = f.text;
                GlStateManager.pushMatrix();
                RenderHelper.enableStandardItemLighting();
                GlStateManager.enablePolygonOffset();
                GL11.glPolygonOffset(1.0f, -1100000.0f);
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                GlStateManager.enableBlend();
                double scale = f.scale;
                GlStateManager.scale(1 / scale, 1 / scale, 1 / scale);
                GlStateManager.rotate(180, 1, 0, 0);
                GlStateManager.enableTexture2D();
                GlStateManager.translate((f.x - mc.getRenderManager().renderPosX) * scale,
                        (f.y - mc.getRenderManager().renderPosY) * -scale, (f.z - mc.getRenderManager().renderPosZ) * -scale);
                GlStateManager.rotate((float) f.yaw, 0, 1, 0);
                GlStateManager.rotate((float) f.pitch, 1, 0, 0);
              //  FontRenderer fr = FontUtil.Superherofx;
                GlStateManager.color(1, 1, 1, (float) (f.opacity / 100));
             //   fr.drawString(text, (float) (f.x - (mc.fontRendererObj.getStringWidth(text) / 2)
                   //     - mc.getRenderManager().renderPosX), 0f, f.color);
                GlStateManager.rotate(180, 0, 1, 0);
                GlStateManager.color(1, 1, 1, 1);
                GlStateManager.color(1, 1, 1, (float) (f.opacity / 100));
             //   fr.drawString(text, (float) (f.x - (mc.fontRendererObj.getStringWidth(text) / 2)
                 //       - mc.getRenderManager().renderPosX), 0f, f.color);
                GlStateManager.color(1, 1, 1, 1);
                GlStateManager.disableTexture2D();
                GlStateManager.disableBlend();
                GlStateManager.enableDepth();
                GlStateManager.enableLighting();
                GlStateManager.disablePolygonOffset();
                RenderHelper.disableStandardItemLighting();
                GlStateManager.popMatrix();

            }

        }


    }


    public void animation(ProcessPacketEvent e) {
        if (e.getPacket() instanceof C0APacketAnimation) {
            try {
                if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {

                    for (short i = 0; i < 90; i++) {
                        effects.add(new fx((long) 4,
                                mc.objectMouseOver.entityHit.posX + new Random().nextInt(3) + new Random().nextDouble()
                                        - 2,
                                mc.objectMouseOver.entityHit.posY + new Random().nextInt(2) + new Random().nextDouble()
                                        - 0.5,
                                mc.objectMouseOver.entityHit.posZ + new Random().nextInt(3) + new Random().nextDouble()
                                        - 2,
                                new Random().nextInt(360), new Random().nextInt(180) - 90, 1 / 2,
                                strings.getRandomObject(), colors.getRandomObject()));
                    }

                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
    }

    public class fx {

        public fx(long ttl, double x, double y, double z, double yaw, double pitch, double scale, String text, int color) {
            this.ttl = System.currentTimeMillis() + ttl;
            this.x = x;
            this.y = y;
            this.z = z;
            this.motX = ((double) (new Random().nextInt(3))) - 1.0;
            this.motY = new Random().nextDouble();
            this.motZ = ((double) (new Random().nextInt(3))) - 1.0;
            this.yaw = yaw;
            this.pitch = pitch;
            this.scale = scale;
            this.text = text;
            this.color = color;
            this.opacity = 100;
        }

        public long ttl;
        public double x, y, z, yaw, pitch, scale, motX, motY, motZ, opacity;
        public String text;
        public int color;

    }




}
