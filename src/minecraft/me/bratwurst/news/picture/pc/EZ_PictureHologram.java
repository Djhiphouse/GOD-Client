package me.bratwurst.news.picture.pc;


import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.server.S09PacketHeldItemChange;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;


public class EZ_PictureHologram {
    public static EZ_PictureHologram client;
    public double x = -1.0;
    public double y = -1.0;
    public double z = -1.0;
    public ItemStack[] stands;
    public int counter = 0;
    public EZ_PictureHologramLoader is;

    public void init() {
        client = this;
    }

    public void setLoc() {
        this.x = Minecraft.getMinecraft().thePlayer.posX;
        this.y = Minecraft.getMinecraft().thePlayer.posY;
        this.z = Minecraft.getMinecraft().thePlayer.posZ;
    }

    public void setup(String link, int height, int widht) {
        try {
            this.is = new EZ_PictureHologramLoader(ImageIO.read(new URL(link)), height, '\u25fc');
        }
        catch (MalformedURLException malformedURLException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        if (this.is == null) {
            return;
        }
        this.stands = this.is.getArmorStands(this.x, this.y, this.z);
        this.counter = 0;
        this.giveNextArmorstand();
    }

    public void giveNextArmorstand() {
        if (this.stands != null) {

            System.out.println(Arrays.toString(this.stands));
            System.out.println(this.stands[this.counter].getDisplayName());
            System.out.println(this.stands[this.counter+1].getDisplayName());

            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, this.stands[this.counter++]));

            PlayerUtils.sendMessage("Du musst noch " + (this.is.pictures - this.counter + 1) + " Holograme platzieren");
            

            if (this.counter >= this.stands.length) {
                this.counter = 0;
                this.stands = null;
            }


        }else{
            PlayerUtils.sendMessage("Du hast das Hologram erstellt!");
        }

    }
}

