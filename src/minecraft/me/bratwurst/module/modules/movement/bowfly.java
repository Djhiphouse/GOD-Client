package me.bratwurst.module.modules.movement;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventState;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Timer;

public class bowfly extends Module {
    boolean enable;
    int bow;
    public static int yprocents = 160;
    public static int xzprocents = 190;
    public static float ymultiplier;
    public static float xzmulitplier;
    public static boolean boost = true;
    public bowfly() {
        super("bowfly", Category.MOVEMENT);
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
        ymultiplier = (float)(yprocents / 100);
        if (event.state == EventState.PRE && this.enable) {
            if (boost) {
                mc.timer.timerSpeed = 0.2F;
            }

            this.enable = false;
        }

        if (event.state == EventState.POST && !this.enable) {
            mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), EnumFacing.DOWN));
            mc.timer.timerSpeed = 1.0F;
            this.enable = true;
        }

        if (event.state == EventState.POST && !doesMove()) {
            mc.thePlayer.motionX = 0.0D;
            mc.thePlayer.motionZ = 0.0D;
        }

        if (event.state == EventState.POST && !boost) {
            if (mc.thePlayer.motionY <= 0.0D) {
                mc.timer.timerSpeed = 0.2F;
            } else {
                mc.timer.timerSpeed = 1.0F;
            }
        }

        if (event.state == EventState.POST) {
            ItemStack stack = mc.thePlayer.getCurrentEquippedItem();
            if (stack != null && stack.getItem() instanceof ItemBow) {
                ++this.bow;
                if (this.bow >= 4) {
                    mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), EnumFacing.DOWN));
                    this.bow = 0;
                } else {
                    mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
                }
            }
        }

        if (event.state == EventState.POST) {
            if (mc.thePlayer.rotationPitch == -90.0F) {
                xzmulitplier = 0.0F;
            } else {
                xzmulitplier = (float)(xzprocents / 100);
            }
        } else {
            xzmulitplier = (float)(xzprocents / 100);
        }

    }

    public static boolean doesMove() {
        return Minecraft.getMinecraft().gameSettings.keyBindForward.pressed || Minecraft.getMinecraft().gameSettings.keyBindRight.pressed || Minecraft.getMinecraft().gameSettings.keyBindBack.pressed || Minecraft.getMinecraft().gameSettings.keyBindLeft.pressed;
    }


    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
