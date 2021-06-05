package me.bratwurst.module.modules.Crasher;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.util.EnumChatFormatting;

public class AntiBan extends Module {
    public AntiBan() {
        super("AntiBan", Category.EXPLOIT);
    }

    public static int Flagcounter = 0;
    public static int RotationsFlagPitch = 0;
    public static int Ongroundflyg = 0;
    public static int PingFlag = 0;
    public static int RotationsFlagYaw = 0;
    public static int PlayerAbilitiesFlag = 0;

    @EventTarget
    public void ProcessPacketEvent(ProcessPacketEvent e) {
        if (e.getPacket() instanceof S08PacketPlayerPosLook) {


            Flagcounter++;

                PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Fly Flag - " + Flagcounter);



            if (Flagcounter >= 81) {
                if (Client.getInstance().getModuleManager().getModuleByName("Glide").isEnabled()) {
                    Client.getInstance().getModuleManager().getModuleByName("Glide").toggle();
                } else if (Client.getInstance().getModuleManager().getModuleByName("LongJump").isEnabled()) {
                    Client.getInstance().getModuleManager().getModuleByName("LongJump").toggle();
                }
                if (!Client.getInstance().getModuleManager().getModuleByName("Glide").isEnabled() || !Client.getInstance().getModuleManager().getModuleByName("LongJump").isEnabled())
                    PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED + "Flag Wurde gecleart");
                Flagcounter = 0;
            }
        }

        if (!mc.thePlayer.onGround && Client.getInstance().getModuleManager().getModuleByName("Nofall").isEnabled()) {
            if (e.getPacket() instanceof C03PacketPlayer) {
                Ongroundflyg++;
            }
            if (Ongroundflyg >= 1){
                PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Nofall Flag - " + Ongroundflyg);
            }

            if (Ongroundflyg >= 45) {
                PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED + "GroundFlag: " + Ongroundflyg);
                if (Client.getInstance().getModuleManager().getModuleByName("Nofall").isEnabled()) {
                    Client.getInstance().getModuleManager().getModuleByName("Nofall").toggle();
                    Ongroundflyg = 0;
                }

            }

        }
        if (mc.thePlayer.moveForward != 0 && mc.thePlayer.rotationPitch >= 91 || mc.thePlayer.rotationPitch >= 181) {
            RotationsFlagPitch++;

                PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED +"RotationsFlagPitch: " + RotationsFlagYaw);
                if (RotationsFlagYaw >= 2) {
                    if (Client.getInstance().getModuleManager().getModuleByName("AntiAim").isEnabled()) {
                        if (Client.getInstance().getModuleManager().getModuleByName("AntiAim").isEnabled()) {
                            Client.getInstance().getModuleManager().getModuleByName("AntiAim").toggle();
                        } else if (Client.getInstance().getModuleManager().getModuleByName("NoHead").isEnabled()) {
                            Client.getInstance().getModuleManager().getModuleByName("NoHead").toggle();
                        }
                        PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED + "Flag Wurde gecleart");
                        RotationsFlagPitch = 0;


                }

            }
        }
        if (mc.thePlayer.moveForward != 0 && mc.thePlayer.rotationYawHead >= 90) {
            RotationsFlagYaw++;

                PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED +"RotationsFlagYaw: " + RotationsFlagYaw);
                if (RotationsFlagYaw >= 2) {
                    if (Client.getInstance().getModuleManager().getModuleByName("AntiAim").isEnabled() || Client.getInstance().getModuleManager().getModuleByName("NoHead").isEnabled()) {
                        if (Client.getInstance().getModuleManager().getModuleByName("AntiAim").isEnabled()) {
                            Client.getInstance().getModuleManager().getModuleByName("AntiAim").toggle();
                        } else if (Client.getInstance().getModuleManager().getModuleByName("NoHead").isEnabled()) {
                            Client.getInstance().getModuleManager().getModuleByName("NoHead").toggle();
                        }
                        RotationsFlagYaw = 0;
                    }
                }

            }


        if (e.getPacket() instanceof C00PacketKeepAlive) {
            try {
                mc.thePlayer.sendQueue.addToSendQueue(new C01PacketPing());
                mc.thePlayer.sendQueue.addToSendQueue(new C01PacketPing());
                mc.thePlayer.sendQueue.addToSendQueue(new C01PacketPing());
                mc.thePlayer.sendQueue.addToSendQueue(new C01PacketPing());
            } catch (Exception exception) {

                    PingFlag++;
                    if (PingFlag >= 15) {
                        PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED +"PingFlag: " + PingFlag);

                    }


            }
        }
        if (e.getPacket() instanceof C0FPacketConfirmTransaction) {
            final C0FPacketConfirmTransaction packetConfirmTransaction = (C0FPacketConfirmTransaction) e.getPacket();
            if (packetConfirmTransaction.getUid() < 0 && mc.thePlayer.ticksExisted % 32 != 0) {
                e.setCancelled(true);
            }
        }

        if (e.getPacket() instanceof C00PacketKeepAlive) {
            e.setCancelled(true);
        }
        if (Client.getInstance().getModuleManager().getModuleByName("Glide").isEnabled() || Client.getInstance().getModuleManager().getModuleByName("LongJump").isEnabled()) {
            if (e.getPacket() instanceof C13PacketPlayerAbilities) {
                final C13PacketPlayerAbilities C4 = (C13PacketPlayerAbilities) e.getPacket();
                C4.setAllowFlying(true);
                C4.setCreativeMode(true);
                C4.setFlying(true);
                C4.setInvulnerable(true);
                C4.isInvulnerable();
                e.setCancelled(true);
                PlayerAbilitiesFlag++;
                if (TimeHelper.hasPassed(200)) {
                   PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED + "PlayerAbilitiesFlag: " +PlayerAbilitiesFlag);
                   PlayerAbilitiesFlag = 0;
                   TimeHelper.reset();
                }
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Flagcounter = 0;
        RotationsFlagPitch = 0;
        Ongroundflyg = 0;
        PingFlag = 0;
        RotationsFlagYaw = 0;
        PlayerAbilitiesFlag = 0;
    }
}

