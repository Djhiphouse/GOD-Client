package me.bratwurst.module.modules.Player;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;

public class AutpGapple extends Module {
    public static Setting mode1;
    public static EntityLivingBase target1;
    public static Setting minCps, instanthealth, Starthealth, Rotate, Movefix;

    public AutpGapple() {
        super("AutpGapple", Category.PLAYER);
    }

    @Override
    public void setup() {

        Client.setmgr.rSetting(instanthealth = new Setting("instanthealth", this, 10, 10, 35, false));
        Client.setmgr.rSetting(Starthealth = new Setting("Starthealth", this, 15, 10, 20, false));

    }

    protected int lastslot = 0;
    protected boolean sendingpacket = false, switcheditem = false;

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {

        if (mc.thePlayer != null && mc.theWorld != null) {
            int gAppleSlot = findGoldenApple();
            if (gAppleSlot != -1) {
                if (mc.thePlayer.getHealth() < Starthealth.getValInt()) {
                    if (mc.thePlayer.inventory.currentItem != gAppleSlot) {
                        lastslot = mc.thePlayer.inventory.currentItem;
                        mc.thePlayer.onGround = true;
                        mc.gameSettings.keyBindJump.pressed = false;
                        mc.thePlayer.inventory.currentItem = gAppleSlot;
                        switcheditem = true;
                        mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(mc.thePlayer), 255, mc.thePlayer.getHeldItem(), 0, 0, 0));
                        if (mc.thePlayer.isEating()) {
                            if (sendingpacket) {
                                for (int i = 0; i < instanthealth.getValInt(); i++) {
                                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
                                    sendingpacket = false;
                                }
                            } else {
                                sendingpacket = true;
                            }

                        } else {
                            if (switcheditem) {
                                mc.thePlayer.inventory.currentItem = lastslot;
                                switcheditem = false;
                            }
                        }


                    }
                }
            }
        }
    }

    @EventTarget
    public void Event3d() {
        int dAppleSlot = findGoldenApple();
        if (dAppleSlot != -1) {
        int appleAmount = mc.thePlayer.inventory.getStackInSlot(dAppleSlot).stackSize;
        }
    }

    protected int findGoldenApple() {
        for (int i = 0; i < 9; i++) {
            if (mc.thePlayer.inventoryContainer.getSlot(36 + i).getHasStack() && mc.thePlayer.inventoryContainer.getSlot(36 + i).getStack().getItem() instanceof ItemAppleGold) {
                return i;
            }
        }

        return 0;
    }

}
