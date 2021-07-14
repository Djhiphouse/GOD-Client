package me.bratwurst.module.modules.World;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSoup;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;

public class AutoSoup extends Module {
    public static Setting health;
    public AutoSoup() {
        super("AutoSoup", Category.WORLD);
    }
    @Override
    public void setup() {
        Client.setmgr.rSetting(health = new Setting(EnumChatFormatting.AQUA + "health", this, 14, 8, 17, false));


    }
    @EventTarget
    public  void OnUpdate(EventUpdate e) {
        final EntityPlayerSP player = mc.thePlayer;
        player.rotationPitch += (float)1.0E-4;
        if (player.getHealth() != player.getMaxHealth() && player.getHealth() < health.getValDouble() * 2 && doesNextSlotHaveSoup() && player.hurtTime >= 9) {
            player.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(player.posX, player.posY, player.posZ, player.rotationYawHead, 90.0f, player.onGround));
            player.sendQueue.addToSendQueue(new C09PacketHeldItemChange(getSlotWithSoup()));
            mc.playerController.sendUseItem(player, mc.theWorld, player.inventory.getStackInSlot(getSlotWithSoup()));
            new BlockPos(0, 0, 0);
            player.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.DROP_ALL_ITEMS, BlockPos.ORIGIN, EnumFacing.DOWN));
            player.sendQueue.addToSendQueue(new C09PacketHeldItemChange(player.inventory.currentItem));
            mc.playerController.onStoppedUsingItem(player);
        }

    }
    public boolean doesNextSlotHaveSoup() {
        final EntityPlayerSP player = mc.thePlayer;
        for (int i = 0; i < 9; ++i) {
            if (player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).getItem() instanceof ItemSoup) {
                return true;
            }
        }
        return false;
    }

    public int getSlotWithPot() {
        final EntityPlayerSP player = mc.thePlayer;
        for (int i = 0; i < 9; ++i) {
            if (player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).getItem() instanceof ItemPotion) {
                return i;
            }
        }
        return 0;
    }

    public int getSlotWithSoup() {
        final EntityPlayerSP player = mc.thePlayer;
        for (int i = 0; i < 9; ++i) {
            if (player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).getItem() instanceof ItemSoup) {
                return i;
            }
        }
        return 0;
    }
}
