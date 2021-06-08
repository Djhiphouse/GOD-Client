package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class SigmaDelete extends Module {
    public SigmaDelete() {
        super("SigmaDelete", Category.WORLD);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {

    }

    @Override
    public void onEnable() {
        super.onEnable();
        File sigmaDataDir = new File(FileUtils.getUserDirectoryPath() + "/AppData/Roaming/.minecraft/sigma");
        File sigmaVerDir = new File(FileUtils.getUserDirectoryPath() + "/AppData/Roaming/.minecraft/versions/Sigma");
        File sigmaNewVerDir = new File(FileUtils.getUserDirectoryPath() + "/AppData/Roaming/.minecraft/versions/Sigma5");
        try {
            PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED + "Sigma wurde gelöscht zu 33%");
            FileUtils.deleteDirectory(sigmaDataDir);
            PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED + "Sigma wurde gelöscht zu 66%");
            FileUtils.deleteDirectory(sigmaVerDir);
            PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED + "Sigma wurde gelöscht zu 99%");
            FileUtils.deleteDirectory(sigmaNewVerDir);
            PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED + "Sigma wurde gelöscht zu 100%");

        } catch (Exception ex) {
            ex.printStackTrace();
            PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Sigma konnte nicht gelöscht werden");
        }
        super.onEnable();
        toggle();
    }
}

