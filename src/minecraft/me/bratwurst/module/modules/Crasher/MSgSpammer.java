package me.bratwurst.module.modules.Crasher;

import com.mojang.authlib.GameProfile;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.manager.MsgManager;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.news.crash.MSGSpamScreen;
import me.bratwurst.utils.MainUtil;
import me.bratwurst.utils.Msgtimer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.Collection;

public class MSgSpammer extends Module {
    private static ArrayList<String> MsgSpieler = new ArrayList<>();

    public MSgSpammer() {
        super("MsgSpammer", Category.EXPLOIT);
    }

    @EventTarget
    public void onUpdate(ProcessPacketEvent e) {
        if (!Msgtimer.hasReached(1280))
            return;
        if (MSGSpamScreen.Msgtext == null || MSGSpamScreen.Msgtext.equals("")) {
            MainUtil.SendClientMesage(EnumChatFormatting.RED + "Kein Text Gesetzt");
            toggle();
            return;
        }
        Minecraft minecraft = Minecraft.getMinecraft();
        NetHandlerPlayClient netHandler = minecraft.getNetHandler();
        Collection<NetworkPlayerInfo> playerInfoMap = netHandler.getPlayerInfoMap();
        playerInfoMap
                .stream()
                .map(NetworkPlayerInfo::getGameProfile)
                .filter(s -> !s.getId().equals(mc.thePlayer.getUniqueID()))
                .map(GameProfile::getName)
                .filter(s -> !MsgManager.getInstance().istarget(s))
                .findFirst()
                .ifPresent(s -> {
                    MsgManager.getInstance().addtarget(s);
                    String messageIn = "/msg " + s + " " + MSGSpamScreen.Msgtext;
                    System.out.println(messageIn);
                    C01PacketChatMessage chatMessage = new C01PacketChatMessage(messageIn);
                    Minecraft.getMinecraft().thePlayer.sendQueue
                            .addToSendQueue(chatMessage);
                });
        if (e.getPacket() instanceof S02PacketChat) {
            final S02PacketChat packet = ((S02PacketChat) e.getPacket());
            final String text = packet.getChatComponent().getUnformattedText();
            if (text.startsWith("Bitte unterlasse das Spammen von Commands!")) {
                toggle();
            }
        }
        Msgtimer.reset();
    }


    @Override
    public void onDisable() {
        super.onDisable();
        MsgManager.getInstance().getHashSet().clear();
        MSGSpamScreen.Msgtext = "";
    }
}
