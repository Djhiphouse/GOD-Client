package me.bratwurst.module.Commands;


import me.bratwurst.manager.Command;
import me.bratwurst.utils.MainUtil;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.EnumChatFormatting;

public class showNbt extends Command {
    public showNbt() {
        super("showNbt", "showNbt", "showNbt: lass dir die NBT-Daten anzeigen");
    }
    @Override
    public void onCommand(String command, String[] args) {
        try {
            if (mc.thePlayer.inventory.getCurrentItem() != null) {
                MainUtil.SendClientMesage(EnumChatFormatting.AQUA  + mc.thePlayer.inventory.getCurrentItem().getTagCompound().toString());
            } else {
                MainUtil.SendClientMesage("{b1337:RIP,Error:CannotFindItem}");
            }
        }
        catch (Exception e) {
            MainUtil.SendClientMesage("{b1337:RIP,Error:Exception}");
        }
    }
}
