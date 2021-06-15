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

    @Override
    public void setup() {

        Client.setmgr.rSetting(Delay = new Setting(EnumChatFormatting.AQUA +"Delay", this, 1300, 400, 2250, true));

    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        Onspam(1300);
    }


    public void Onspam(int delay) {
        Random rdm = new Random();
       int zufall = rdm.nextInt();
        String Clientmessage = Client.getInstance().CLIENT_NAME + " | Version | " + Client.getInstance().getCLIENT_VERSION() + " by " + Client.getInstance().getCLIENT_CODER() + " :  " + zufall;

        delay = Delay.getValInt();
        if (TimeHelper.hasReached(delay)){
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(Clientmessage));
            TimeHelper.reset();
        }
    }
}