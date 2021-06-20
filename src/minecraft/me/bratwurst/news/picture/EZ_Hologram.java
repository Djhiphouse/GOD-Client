package me.bratwurst.news.picture;

import me.bratwurst.news.newutils.EZ_MinecraftInterface;
import me.bratwurst.news.newutils.EZ_TimeCalculator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmorStand;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;


public class EZ_Hologram
        implements EZ_MinecraftInterface {
    private String text;
    private String date;
    private double x;
    private double y;
    private double z;
    private ItemStack item;

    public EZ_Hologram(String text2, double x, double y, double z) {
        this.setText(text2);
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        this.setDate(String.valueOf(EZ_TimeCalculator.getCurrentDate()) + " | " + EZ_TimeCalculator.getCurrentTime());
    }

    public void spawnHologram() {
        this.item = new ItemStack(Items.armor_stand);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound entityTag = new NBTTagCompound();
        entityTag.setString("CustomName", this.text);
        entityTag.setInteger("CustomNameVisible", 1);
        entityTag.setInteger("Invisible", 1);
        NBTTagList Pos2 = new NBTTagList();
        Pos2.appendTag(new NBTTagDouble(this.x));
        Pos2.appendTag(new NBTTagDouble(this.y));
        Pos2.appendTag(new NBTTagDouble(this.z));
        entityTag.setTag("Pos", Pos2);
        base.setTag("EntityTag", entityTag);
        this.item.setTagCompound(base);
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, this.item));
        this.item.setTagCompound(null);
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public String getDate() {
        return this.date;
    }

    public String getText() {
        return this.text;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setText(String text2) {
        this.text = text2;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
}


