package me.bratwurst.module.modules.render;


import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.Event2D;
import me.bratwurst.manager.ModuleManager;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.MainUtil;
import me.bratwurst.utils.PacketTimer;
import me.bratwurst.utils.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.net.ssl.SNIServerName;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.PublicKey;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.google.common.math.BigIntegerMath.factorial;
import static net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture;

public class HUD extends Module {
    public static String SeverNachricht = "";

    public HUD() {
        super("HUD", Category.RENDER);

    }

    static int x;

    static int y;

    public void sortModules(ArrayList<Module> arr) {


        for (int i = 0; i < arr.size(); i++) { // Loop though array

            int smallestIndex = getIndexOfSmallestNum(arr, i); // get the smallest number

            // Swap the current an smallest number
            Module current = arr.get(i);
            Module smallest = arr.get(smallestIndex);

            arr.set(i, smallest);
            arr.set(smallestIndex, current);

        }
    }

    public int getIndexOfSmallestNum(ArrayList<Module> arr, int start) {

        int smallestNumIndex = start;

        for (int i = start; i < arr.size(); i++) {
            if (arr.get(i).getName().length() > arr.get(smallestNumIndex).getName().length()) {
                smallestNumIndex = i;
            }
        }

        return smallestNumIndex;
    }

    @EventTarget
    public void onRender(Event2D e) {


        int count = 0;
        ArrayList<Module> modules = Client.instance.getModuleManager().getEnabledModules();

        sortModules(modules);

        ScaledResolution sr = new ScaledResolution(mc);
        for (Module m : modules) {
            String name = m.getName() + m.getDisplayname();
            double offset = count * (mc.fontRendererObj.FONT_HEIGHT + 6);
            net.minecraft.client.gui.Gui.drawRect(sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(name) - 10,
                    offset, sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(name) - 8,
                    6 + mc.fontRendererObj.FONT_HEIGHT + offset, 1);
            net.minecraft.client.gui.Gui.drawRect(sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(name) - 8,
                    offset, sr.getScaledWidth(), 6 + mc.fontRendererObj.FONT_HEIGHT + offset, 0x90000000);
            GlStateManager.enableBlend();
            mc.fontRendererObj.drawStringWithShadow(name,
                    sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(name) - 4, (float) (4 + offset), -1);
            count++;
            GlStateManager.disableBlend();
            mc.getTextureManager().bindTexture(new ResourceLocation("client/ingame2.png"));
            drawModalRectWithCustomSizedTexture(-55, -30, 0.0f, 0.0f, 130, 110, 160.0f, 140.0f);
        }

        drawArmor();

        int i = -5;
        int j = 122;
        Collection<PotionEffect> collection = mc.thePlayer.getActivePotionEffects();

        if (!collection.isEmpty()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            int l = 33;

            if (collection.size() > 5) {
                l = 132 / (collection.size() - 1);
            }

            for (PotionEffect potioneffect : mc.thePlayer.getActivePotionEffects()) {
                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(InventoryEffectRenderer.inventoryBackground);

                if (potion.hasStatusIcon()) {
                    int i1 = potion.getStatusIconIndex();
                    Gui.drawTexturedModalRect1(i + 6, j + 7, 0 + i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
                }

                String s1 = I18n.format(potion.getName(), new Object[0]);

                if (potioneffect.getAmplifier() == 1) {
                    s1 = s1 + " " + I18n.format("enchantment.level.2", new Object[0]);
                } else if (potioneffect.getAmplifier() == 2) {
                    s1 = s1 + " " + I18n.format("enchantment.level.3", new Object[0]);
                } else if (potioneffect.getAmplifier() == 3) {
                    s1 = s1 + " " + I18n.format("enchantment.level.4", new Object[0]);
                }

                mc.fontRendererObj.drawStringWithShadow(s1, (float) (i + 10 + 18), (float) (j + 6), 16777215);
                String s = Potion.getDurationString(potioneffect);
                mc.fontRendererObj.drawStringWithShadow(s, (float) (i + 10 + 18), (float) (j + 6 + 10), 8355711);
                j += l;
            }
        }
    }

    public static int hobbbit = 0;

    @EventTarget
    public void onRenderName(Event2D e) {
        Random rdm = new Random();
        int RandomClientID = rdm.nextInt(100);
        int number = 20;


            /*
            Dies ist wichtig um Asncy
            zu programmieren


             */
        Thread newThread = new Thread(() -> {

            try {
                if (hobbbit == 0) {
                    hobbbit++;
                    Socket connection = new Socket();
                    connection.connect(new InetSocketAddress("5.181.151.112", 8090));

                    byte[] sendbytes = "Client Connectet GOD CLIENT \n".getBytes();

                    connection.getOutputStream().write(sendbytes);
                    System.out.println("connection gesendet: " + sendbytes);
                    //  InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                    // BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    //bufferedReader.readLine();
                }
            } catch (Exception socket) {
                socket.printStackTrace();
                System.out.println("Fehler geschlagen bein senden");
            }
            while (true) {

                try {
                    if (PacketTimer.hasReached(5000)) {
                        System.out.println("Listen");
                        Socket connection = new Socket();
                        connection.connect(new InetSocketAddress("5.181.151.112", 8090));

                        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String Message = bufferedReader.readLine();
                        SeverNachricht = Message;

                       MainUtil.SendClientMesage("Server NAchricht : " + SeverNachricht);
                        System.out.println("Listen on Packets:");
                        if (SeverNachricht == "Hallo") {
                            PlayerUtil.SendPacketchat("Hallo Vom Server");

                        }
                        connection.close();
                        PacketTimer.reset();
                    }

                } catch (Exception exception) {

                }

            }


        });
        newThread.start();

    }

        public static void drawArmor () {
            for (int i = 0; i < Minecraft.getMinecraft().thePlayer.inventory.armorInventory.length; i++) {
                ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
                renderItemStack(i, itemStack);
            }

        }

        public static void renderItemStack ( int i, ItemStack is){
            if (is == null)
                return;
            GL11.glPushMatrix();
            GL11.glScaled(1.5, 1.5, 1.5);
            if (mc.currentScreen != null) {
                GL11.glTranslatef(0, -1000000, 0);
            }
            GL11.glTranslatef(0, 240, 0);
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableBlend();
            int yAdd = (-16 * i) + 48;
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            RenderHelper.enableGUIStandardItemLighting();
            mc.getRenderItem().renderItemAndEffectIntoGUI(is, x + 2, y + 31 + yAdd);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
            GL11.glPopMatrix();
        }

    }