package me.bratwurst.module.modules.combat;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class Wtap extends Module {
    public Wtap() {
        super("Wtap", Category.COMBAT);
    }

    @EventTarget
    public void ProcessPacketEvent(ProcessPacketEvent e) {

            Packet packet = e.getPacket();

            if (packet instanceof C02PacketUseEntity) {

                C02PacketUseEntity attack = (C02PacketUseEntity) packet;

                if (attack.getAction() == C02PacketUseEntity.Action.ATTACK && mc.thePlayer.getFoodStats().getFoodLevel() > 6) {

                    boolean continueSprint = mc.thePlayer.isSprinting();

                    //mc.thePlayer.setSprinting(false);
                    mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SPRINTING));
                    mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SPRINTING));
                    mc.thePlayer.setSprinting(continueSprint);

                }

            }

        }
    }
