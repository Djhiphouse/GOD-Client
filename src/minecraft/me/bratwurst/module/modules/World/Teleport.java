package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Teleport extends Module {
    public Teleport() {
        super("Teleport", Category.WORLD);
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        for (Entity Target : Minecraft.getMinecraft().theWorld.loadedEntityList) {
                   if (Target != null) {
                    if (mc.gameSettings.keyBindAttack.pressed) {
                        double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
                        double x;
                        double z;
                        int distance = (int) mc.thePlayer.getDistanceToEntity(Target);
                        int step = -1;
                        System.out.println(distance);
                        z = mc.thePlayer.posZ;
                        x = mc.thePlayer.posX;
                        for (int i = 0; i < distance; i++) {
                            if (Target.getDistanceToEntity(mc.thePlayer ) >= 0.2) {

                                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x + -Math.sin(yaw) * i, mc.thePlayer.posY, z + Math.cos(yaw) * i, mc.thePlayer.onGround));
                                x += -Math.sin(yaw) * step;
                                z += Math.cos(yaw) * step;
                                mc.thePlayer.setPosition(mc.thePlayer.posX + -Math.sin(yaw) * distance, mc.thePlayer.posY, mc.thePlayer.posZ + Math.cos(yaw) * distance);
                                mc.gameSettings.keyBindAttack.pressed = false;
                                if (Target.getDistanceToEntity(mc.thePlayer) <= 5) {
                                    mc.playerController.attackEntity(mc.thePlayer, Target);
                                    mc.thePlayer.swingItem();
                                }
                            }else {
                                for (int o = 0; o < 20; o++) {
                                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x + -Math.sin(yaw) * i, mc.thePlayer.posY, z + Math.cos(yaw) * i, mc.thePlayer.onGround));
                                    x += -Math.sin(yaw) * step;
                                    z += Math.cos(yaw) * step;
                                    mc.thePlayer.setPosition(mc.thePlayer.posX + -Math.sin(yaw) * distance, mc.thePlayer.posY, mc.thePlayer.posZ + Math.cos(yaw) * distance);
                                    mc.gameSettings.keyBindAttack.pressed = false;
                                    if (Target.getDistanceToEntity(mc.thePlayer) <= 5) {
                                        mc.playerController.attackEntity(mc.thePlayer, Target);
                                        mc.thePlayer.swingItem();
                                    }
                                    mc.playerController.attackEntity(mc.thePlayer, Target);
                                    mc.thePlayer.swingItem();
                                    return;
                                }
                            }
                        }

                    }
                }
            }
        }

    public void onbacktp() {

        for (Entity Target : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if (Target instanceof EntityPlayer) {
                if (Target != null && Target != mc.thePlayer) {
                    mc.playerController.attackEntity(mc.thePlayer, Target);
                    mc.thePlayer.swingItem();
                    if (mc.gameSettings.keyBindAttack.pressed) {
                        double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
                        double x;
                        double z;
                        int distance = (int) mc.thePlayer.getDistanceToEntity(Target);
                        int step = 1;

                        z = mc.thePlayer.posZ;
                        x = mc.thePlayer.posX;
                        for (int i = 0; i < distance; i++) {
                            if (Target.getDistanceToEntity(mc.thePlayer) >= 0.2) {
                                mc.playerController.attackEntity(mc.thePlayer, Target);
                                mc.thePlayer.swingItem();
                                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x + -Math.sin(yaw) * i, mc.thePlayer.posY, z + Math.cos(yaw) * i, mc.thePlayer.onGround));
                                x += -Math.sin(yaw) * step;
                                z += Math.cos(yaw) * step;
                                mc.thePlayer.setPosition(mc.thePlayer.posX + -Math.sin(yaw) * distance, mc.thePlayer.posY, mc.thePlayer.posZ + Math.cos(yaw) * distance);
                                mc.gameSettings.keyBindAttack.pressed = false;

                            }
                        }

                    }
                }
            }
        }

    }
}
