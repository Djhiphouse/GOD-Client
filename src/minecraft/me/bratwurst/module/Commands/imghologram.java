package me.bratwurst.module.Commands;

import me.bratwurst.event.EventTarget;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.ImageHologram;
import me.bratwurst.utils.MainUtil;
import me.bratwurst.utils.PlayerUtil;
import me.bratwurst.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class imghologram extends Command  {
    private ImageHologram hologram = null;

    public imghologram() {

        super("imghologram", "imghologram", "imghologram: Spwn ein bild");
    }

    @Override
    public void onCommand(String command, String[] args) {
        if (hologram != null) {
            resetHologram();
            MainUtil.SendClientMesage("�cHologram spawnen abgebrochen");
            return;
        }
        if (args.length != 2)
         return;

            if (!PlayerUtil.isCreative())
                return;

        int size;
        try {
            size = Integer.parseInt(args[1]);
            if (size < 10) {
                MainUtil.SendClientMesage("�cSize muss mindestens 10 sein");
                return;
            }
        } catch (NumberFormatException e) {
            MainUtil.SendClientMesage("�cSize muss eine Nummer sein");
            return;
        }

        BufferedImage image;
        try {
            image = ImageIO.read(new URL(args[0]));
        } catch (IOException e) {
            MainUtil.SendClientMesage("URL: " + args[0]);
            MainUtil.SendClientMesage("�cLaden vom Bild fehlgeschlagen");
            e.printStackTrace();
            return;
        }

        try {
            EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
            this.hologram = new ImageHologram(player.posX, player.posY, player.posZ, size, image);
        } catch (Exception e) {
            resetHologram();
            MainUtil.SendClientMesage("�cErstellen vom Hologram fehlgeschlagen");
            e.printStackTrace();
            return;
        }

        MainUtil.SendClientMesage("�aHologram platzieren");

        nextItem();
        return;
    }

   @EventTarget
    public void onPlayerBlockPlacement(BlockPos hitPos, EnumFacing side, ItemStack currentItem, float f, float f1, float f2) {
        if (hologram == null) {
            resetHologram();
            return;
        }
        if (hologram != null && currentItem != null && currentItem.getItem().equals(Items.armor_stand))
            if (hologram.hasNext()) {
                MainUtil.SendClientMesage("�aNoch �70 �aArmorStands zu platzieren");
                Utils.giveItem(Minecraft.getMinecraft().thePlayer.inventory.currentItem + 36, null);
                nextItem();
            } else {
                resetHologram();
                MainUtil.SendClientMesage("�aHologram fertiggestellt");
                return;
            }
    }

    private void resetHologram() {
        hologram = null;

    }

    private void nextItem() {
        MainUtil.SendClientMesage("�aNoch " +  (hologram.items.length - hologram.index) + "  + aArmorStands zu platzieren");
        Utils.giveItem(Minecraft.getMinecraft().thePlayer.inventory.currentItem + 36, hologram.next());
    }
}


