package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.utils.PlayerUtil;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.item.ItemStack;

public class copyitemnbt extends Command {
    public copyitemnbt() {
        super("copynbt", "copyitemnbt", "copyitemnbt: Kopiere denn nbttag dess# items");
    }

    @Override
    public void onCommand(String command, String[] args) {
        try {
            ItemStack itemStack = this.mc.thePlayer.getHeldItem();
            PlayerUtil.copy(itemStack.getTagCompound().toString());
            PlayerUtils.sendMessage("NBT Daten wurde erfolgreich kopiert.");
        } catch (Exception exception) {
            PlayerUtils.sendMessage("NBT Daten nicht kopieren.");
        }
    }
}