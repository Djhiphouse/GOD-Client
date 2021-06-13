package me.bratwurst.module.modules.World;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.module.modules.Player.Eagle;
import me.bratwurst.module.modules.Player.SafeWalk;
import me.bratwurst.utils.MainUtil;
import me.bratwurst.utils.TimeHelper;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Sacffold extends Module {
    public static Setting mode1;

    public static Setting TowerMotion, NoSprint, Range, Fastbrige, Safewwalk, delay,tower,LegitTower;

    public Sacffold() {
        super("Scaffold", Category.WORLD);
    }

    public static float Yaw;
    public static float Pitch;

    @Override
    public void setup() {

        Client.setmgr.rSetting(NoSprint = new Setting("NoSprint", this, false));
        Client.setmgr.rSetting(Fastbrige = new Setting("Eagle", this, false));
        Client.setmgr.rSetting(delay = new Setting("delay", this, 25, 1, 1000, true));
        Client.setmgr.rSetting(TowerMotion = new Setting("TowerMotion", this, false));
        Client.setmgr.rSetting(LegitTower = new Setting("LegitTower", this, false));


    }

    @EventTarget
    public void onMotionUpdate(EventMotionUpdate event) {
        moveBlocksToHotbar();
        int Speed = delay.getValInt();
        BlockPos blockPos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1, mc.thePlayer.posZ);
        setBlockAndFacing(blockPos,event);




        if (NoSprint.getValBoolean()) {
            mc.thePlayer.setSprinting(false);

        } else {
            mc.thePlayer.setSprinting(true);
        }
         int d = 0;
        if (TowerMotion.getValBoolean()) {
            if (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()) {
                if (mc.thePlayer.onGround) {
                    mc.timer.timerSpeed = 0.8f;
                    if (d == 0) {
                       MainUtil.setpos(mc.thePlayer.posX,mc.thePlayer.posY + 1,mc.thePlayer.posZ ,false,false);
                       d++;
                    }else {
                        MainUtil.setpos(mc.thePlayer.posX,mc.thePlayer.posY + 0.1,mc.thePlayer.posZ ,false,false);
                    }
                }else {
                    mc.timer.timerSpeed = 1F;
                }

                placmentblock(blockPos, currentFacing);


            } else {

            }
            if (LegitTower.getValBoolean() && !TowerMotion.getValBoolean()) {
                mc.thePlayer.jump();
                placmentblock(blockPos, currentFacing);
            }
        }
        if (mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock) {
            if (event.getState() == EventMotionUpdate.State.PRE) {
                float[] rotate = getRotations(blockPos, currentFacing);
                event.setYaw(rotate[0]);
                event.setPitch(rotate[1]);


            } else if (event.getState() == EventMotionUpdate.State.POST && TimeHelper.hasReached(Speed)) {
                if (Fastbrige.getValBoolean()) {
                    if (this.mc.thePlayer != null && this.mc.theWorld != null) {
                        ItemStack i = this.mc.thePlayer.getCurrentEquippedItem();
                        BlockPos bP = new BlockPos(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 0.9D, this.mc.thePlayer.posZ);
                        if (i != null) {
                            if (i.getItem() instanceof ItemBlock) {
                                this.mc.gameSettings.keyBindSneak.pressed = false;
                                if (this.mc.theWorld.getBlockState(bP).getBlock() == Blocks.air) {
                                    this.mc.gameSettings.keyBindSneak.pressed = true;
                                    if (NoSprint.getValBoolean()) {
                                        Minecraft.getMinecraft().thePlayer.setSprinting(true);
                                        placmentblock(blockPos, currentFacing);
                                    } else {
                                        placmentblock(blockPos, currentFacing);
                                    }

                                }
                            }
                        }
                    }
                } else {
                    if (NoSprint.getValBoolean()) {
                        mc.thePlayer.setSprinting(false);
                        placmentblock(blockPos, currentFacing);
                    }
                    placmentblock(blockPos, currentFacing);
                }

                TimeHelper.reset();
            }


        }
        moveBlocksToHotbar();
    }

    public static float[] getRotations(BlockPos block, EnumFacing face) {
        double x = block.getX() + 0.5 - mc.thePlayer.posX + (double) face.getFrontOffsetX() / 2;
        double z = block.getZ() + 0.5 - mc.thePlayer.posZ + (double) face.getFrontOffsetZ() / 2;
        double d1 = mc.thePlayer.posY + mc.thePlayer.getEyeHeight() - (block.getY() + 0.5);
        double d3 = MathHelper.sqrt_double(x * x + z * z);
        float yaw = (float) (Math.atan2(z, x) * 20 / Math.PI) - 90;
        float pitch = (float) (Math.atan2(d1, d3) * 180 / Math.PI);
        if (yaw < 0.0F) {
            yaw += 360f;
        }
        return new float[]{yaw, pitch};
    }

    public void placmentblock(BlockPos pos, EnumFacing facing) {
        moveBlocksToHotbar();
        if (mc.thePlayer.getCurrentEquippedItem().getItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock && mc.playerController.onPlayerRightClick(mc.thePlayer, mc.theWorld, mc.thePlayer.getCurrentEquippedItem(), currentPos, currentFacing, new Vec3(currentPos.getX(), currentPos.getY(), currentPos.getZ()))) {
            mc.thePlayer.swingItem();
        }

    }

    private Vec3 getVec3(BlockData data) {
        BlockPos pos = data.getPosition();
        EnumFacing face = data.getFacing();
        double x = (double) pos.getX() + 0.5D;
        double y = (double) pos.getY() + 0.5D;
        double z = (double) pos.getZ() + 0.5D;
        x += (double) face.getFrontOffsetX() / 2.0D;
        z += (double) face.getFrontOffsetZ() / 2.0D;
        y += (double) face.getFrontOffsetY() / 2.0D;
        if (face != EnumFacing.UP && face != EnumFacing.DOWN) {
            y += this.randomNumber(0.50D, 0.5D);
        } else {
            x += this.randomNumber(0.50D, 0.50D);
            z += this.randomNumber(0.50D, 0.50D);
        }

        if (face == EnumFacing.WEST || face == EnumFacing.EAST) {
            z += this.randomNumber(0.50D, 0.50D);
        }

        if (face == EnumFacing.SOUTH || face == EnumFacing.NORTH) {
            x += this.randomNumber(0.50D, 0.50D);
        }

        return new Vec3(x, y, z);
    }

    private double randomNumber(double max, double min) {
        return Math.random() * (max - min) + min;
    }

    public static class BlockData {
        private BlockPos blockPos;
        private EnumFacing enumFacing;

        public BlockData(final BlockPos blockPos, final EnumFacing enumFacing) {
            this.blockPos = blockPos;
            this.enumFacing = enumFacing;
        }

        public EnumFacing getFacing() {
            return this.enumFacing;
        }

        public BlockPos getPosition() {
            return this.blockPos;
        }
    }

    private void moveBlocksToHotbar() {
        boolean added = false;
        if (!isHotbarFull()) {
            for (int k = 0; k < mc.thePlayer.inventory.mainInventory.length; ++k) {
                if (k > 8 && !added) {
                    final ItemStack itemStack = mc.thePlayer.inventory.mainInventory[k];

                }
            }
        }
    }


    public boolean isHotbarFull() {
        int count = 0;
        for (int k = 0; k < 9; ++k) {
            final ItemStack itemStack = mc.thePlayer.inventory.mainInventory[k];
            if (itemStack != null) {
                count++;
            }
        }
        return count == 8;
    }


    public static int randomNumber(int max, int min) {
        return Math.round(min + (float) Math.random() * ((max - min)));
    }

    public void setBlockAndFacing(BlockPos var1,EventMotionUpdate e) {
        int yaw = (int) (mc.thePlayer.rotationYaw + 180);

        e.setYaw(yaw);
        //if(!shouldDownwards()) {
        if (mc.theWorld.getBlockState(var1.add(0, -1, 0)).getBlock() != Blocks.air) {
            this.currentPos = var1.add(0, -1, 0);
            this.currentFacing = EnumFacing.UP;
        } else if (mc.theWorld.getBlockState(var1.add(-1, 0, 0)).getBlock() != Blocks.air) {
            this.currentPos = var1.add(-1, 0, 0);
            this.currentFacing = EnumFacing.EAST;
        } else if (mc.theWorld.getBlockState(var1.add(1, 0, 0)).getBlock() != Blocks.air) {
            this.currentPos = var1.add(1, 0, 0);
            this.currentFacing = EnumFacing.WEST;
        } else if (mc.theWorld.getBlockState(var1.add(0, 0, -1)).getBlock() != Blocks.air) {
            this.currentPos = var1.add(0, 0, -1);
            this.currentFacing = EnumFacing.SOUTH;
        } else if (mc.theWorld.getBlockState(var1.add(0, 0, 1)).getBlock() != Blocks.air) {
            this.currentPos = var1.add(0, 0, 1);
            this.currentFacing = EnumFacing.NORTH;
        } else {
            this.currentPos = null;
            this.currentFacing = null;
        }



        }


    public BlockPos currentPos;
    public EnumFacing currentFacing;
}


















  //if (Helper.mc().getCurrentServerData().serverIP.toLowerCase().contains("hypixel.net")