package me.bratwurst.module.modules.Crasher;

import de.Hero.settings.Setting;
import io.netty.buffer.Unpooled;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.Random;

public class clientspammer extends Module {
    public Setting Delay, Bypass, Range, MoveFix;
    public static Setting mode1;

    public clientspammer() {
        super("clientspammer", Category.EXPLOIT);
        ArrayList<String> options = new ArrayList<>();


    }


    @EventTarget
    public void onUpdate(EventMotionUpdate e) {


    }

    @Override
    public void onEnable() {
        super.onEnable();
        Client.networkClient.getIp()
                .exceptionally(throwable -> {
                    throwable.printStackTrace();
                    return null;
                });
        PlayerUtils.sendMessage("send request");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}