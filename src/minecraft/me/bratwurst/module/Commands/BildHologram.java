package me.bratwurst.module.Commands;

import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import me.bratwurst.news.picture.pc.EZ_PictureHologram;
import me.bratwurst.utils.player.PlayerUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BildHologram extends Command {
    public BildHologram() {
        super("bholo", "BildHologram", "Ein Hologram mit einem Bild.");
    }
    @Override
    public void onCommand(String command, String[] args){
        if (args.length == 0) {

            PlayerUtils.sendMessage("\u00a7b#hologram <x> <y> <z> <text>");
            PlayerUtils.sendMessage("\u00a7b#hologram list");
            PlayerUtils.sendMessage("\u00a7b#hologram load <width> <height> <url>");

        } else if (args[0].equalsIgnoreCase("list")) {
            Client.getInstance().getHologramManager().listHolograms();
        } else if (args[0].equalsIgnoreCase("load")) {
            new EZ_PictureHologram().init();
            try {
                BufferedImage img = ImageIO.read(new URL(args[3]));
                EZ_PictureHologram.client.setLoc();
                EZ_PictureHologram.client.setup(args[3], Integer.parseInt(args[2].replace("%", String.valueOf(img.getHeight()))), Integer.parseInt(args[1].replace("%", String.valueOf(img.getWidth()))));
            }
            catch (MalformedURLException img) {
            }
            catch (IOException img) {}
        } else {
            try {
                String text2 = "";
                for (int i = 3; i < args.length; i++) {
                    text2 = String.valueOf(text2) + " " + args[i].trim();
                }
                Client.getInstance().getHologramManager().spawnHologram(text2, args[0], args[1], args[2]);
            }
            catch (Exception e) {

                PlayerUtils.sendMessage("\u00a7b#hologram <x> <y> <z> <text>");
                PlayerUtils.sendMessage("\u00a7b#hologram list");
                PlayerUtils.sendMessage("\u00a7b#hologram load <width> <height> <url>");
                PlayerUtils.sendMessage("\u00a7b#hologram pos <text>");

            }
        }
    }
}
