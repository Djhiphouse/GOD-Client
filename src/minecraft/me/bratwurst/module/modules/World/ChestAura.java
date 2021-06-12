package me.bratwurst.module.modules.World;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;

public class ChestAura extends Module {
    public static Setting mode1;

    public static Setting range, delay;
    public ChestAura() {
        super("ChestAura", Category.WORLD);
    }
    @Override
    public void setup() {
        Client.setmgr.rSetting(range = new Setting("Range", this, 4, 1, 6, false));

        Client.setmgr.rSetting(delay = new Setting("Delay", this, true));

    }

    protected int delayOver = 0;
    protected BlockPos openNext = null , pos = null;
    public ArrayList opened = new ArrayList();

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if(mc.thePlayer != null && mc.theWorld != null){
            if(!mc.thePlayer.isUsingItem() && !(mc.currentScreen instanceof GuiInventory)){
                if(openNext != null){
                    openChest(openNext);
                    openNext = null;
                    pos = null;
                }
                if(delayOver < delay.getValDouble()){
                    delayOver++;
                }else{
                    delayOver = 0;
                    for (double x = - range.getValDouble(); x < range.getValDouble(); x++ ){
                        for (double y = - range.getValDouble(); y < range.getValDouble(); y++ ){
                            for (double z = - range.getValDouble(); z < range.getValDouble(); z++ ){
                                final BlockPos pos = new BlockPos(mc.thePlayer.posX + x, mc.thePlayer.posY + y, mc.thePlayer.posZ + z);


                                if(!opened.contains(pos) && Block.getIdFromBlock(mc.theWorld.getBlockState(pos).getBlock()) == Block.getIdFromBlock(Blocks.chest) && Math.sqrt(mc.thePlayer.getDistanceSq(pos)) <= range.getValDouble()){
                                    this.pos = pos;
                                    mc.thePlayer.swingItem();
                                    openNext = pos;

                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @EventTarget
    public void EventMotionUpdate(EventMotionUpdate e){
        if(mc.thePlayer != null && mc.theWorld != null){
            if(this.pos != null){
               e.setPitch(pos.getY());
               e.setYaw(pos.getZ());
            }
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        delayOver = 0;
        pos = null;
        opened.clear();
        super.onDisable();
    }

    protected void openChest(BlockPos pos){
        mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(pos, (double) pos.getY() + 0.5D < mc.thePlayer.posY + 1.7D ? 1 : 0, mc.thePlayer.getCurrentEquippedItem(), 0.0F, 0.0F, 0.0F));
        mc.thePlayer.swingItem();
        opened.add(pos);
    }
}
