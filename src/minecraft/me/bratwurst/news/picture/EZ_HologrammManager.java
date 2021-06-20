package me.bratwurst.news.picture;
import java.util.ArrayList;

import me.bratwurst.news.newutils.EZ_MinecraftInterface;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class EZ_HologrammManager
        implements EZ_MinecraftInterface {
    public ArrayList<EZ_Hologram> spawnedHolograms = new ArrayList();

    public void spawnHologram(String text2, String x, String y, String z) {
        x = x.replace("~", "" + Minecraft.getMinecraft().thePlayer.posX);
        y = y.replace("~", "" + Minecraft.getMinecraft().thePlayer.posY);
        z = z.replace("~", "" + Minecraft.getMinecraft().thePlayer.posZ);
        double xPos = Double.valueOf(x);
        double yPos = Double.valueOf(y);
        double zPos = Double.valueOf(z);
        EZ_Hologram hd = new EZ_Hologram(text2, xPos, yPos, zPos);
        hd.spawnHologram();
        this.spawnedHolograms.add(hd);
    }

    public String getPosFromHologram(String text2) {
        return String.valueOf(this.getHologramByText(text2).getX()) + " " + this.getHologramByText(text2).getY() + " " + this.getHologramByText(text2).getZ();
    }

    public EZ_Hologram getHologramByText(String text2) {
        for (EZ_Hologram ho : this.spawnedHolograms) {
            if (!ho.getText().equalsIgnoreCase(text2)) continue;
            return ho;
        }
        return null;
    }

    public void listHolograms() {
        for (EZ_Hologram hd : this.spawnedHolograms) {

            PlayerUtils.sendMessage("\u00a7bName\u00bb \u00a73" + hd.getText());
            PlayerUtils.sendMessage("\u00a7bX\u00bb \u00a73" + hd.getX());
            PlayerUtils.sendMessage("\u00a7bY\u00bb \u00a73" + hd.getY());
            PlayerUtils.sendMessage("\u00a7bZ\u00bb \u00a73" + hd.getZ());

        }
    }
}


