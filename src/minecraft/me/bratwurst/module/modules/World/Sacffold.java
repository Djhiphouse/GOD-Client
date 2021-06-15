package me.bratwurst.module.modules.World;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.MainUtil;
import me.bratwurst.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;

import java.util.ArrayList;

public class Sacffold extends Module {
    public static Setting mode1;
    public static Setting Towerob;
    public static Setting modemove;
    public static Setting AirSprint, NoSprint, Range, Fastbrige, Boost, delay,BoostMode,Timer;

    public Sacffold() {
        super("Scaffold", Category.WORLD);
        ArrayList<String> options2 = new ArrayList<>();
        Client.setmgr.rSetting(modemove = new Setting(EnumChatFormatting.RED + "Move options", this, "Boost", options2));
        options2.add("Boost");
        options2.add("Timer");
        options2.add("Normal");
        ArrayList<String> options = new ArrayList<>();
        options.add("NCP");
        options.add("AAC");
        options.add("Spartan");
        ArrayList<String> Tower = new ArrayList<>();

        Client.setmgr.rSetting(Towerob = new Setting(EnumChatFormatting.RED + "Tower options", this, "TowerMotion", Tower));
        Tower.add("TowerMotion");
        Tower.add("LegitTower");




        Client.setmgr.rSetting(mode1 = new Setting(EnumChatFormatting.RED + "AC options", this, "AAC", options));

    }


    public static float Yaw;
    public static float Pitch;

    @Override
    public void setup() {
        ArrayList<String> nix = new ArrayList<>();
        Client.setmgr.rSetting(delay = new Setting(EnumChatFormatting.AQUA +"Delay", this, 25, 1, 1000, true));
        Client.setmgr.rSetting(NoSprint = new Setting(EnumChatFormatting.AQUA + "NoSprint", this, false));
        Client.setmgr.rSetting(Fastbrige = new Setting(EnumChatFormatting.AQUA +"Eagle", this, false));


        Client.setmgr.rSetting(BoostMode = new Setting(EnumChatFormatting.BLUE + "Movement Mode", this, "",nix));
        Client.setmgr.rSetting(Timer = new Setting(EnumChatFormatting.YELLOW + "Timer", this, 1.2, 1, 2, true));
        Client.setmgr.rSetting(AirSprint = new Setting(EnumChatFormatting.YELLOW + "AirSprint", this, false));

    }
    public boolean Spartan = false;
public static float pistsch = 85.3f;
    @EventTarget
    public void onMotionUpdate(EventMotionUpdate event) {
    if (mode1.getValString().equalsIgnoreCase("NCP")) {
        pistsch = 85.3f;
        this.setDisplayname(EnumChatFormatting.AQUA + " - NCP");
    }

        if (mode1.getValString().equalsIgnoreCase("Spartan")){
            pistsch = 85.3f;
            Spartan = true;
            this.setDisplayname(EnumChatFormatting.YELLOW + " - Spartan");
        }else {
            Spartan = false;
        }

        if (mode1.getValString().equalsIgnoreCase("AAC")){
            pistsch = 85.3f;
            this.setDisplayname(EnumChatFormatting.BLUE + " - AAC");
        }


        moveBlocksToHotbar();
        int Speed = delay.getValInt();
        BlockPos blockPos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1, mc.thePlayer.posZ);

        setBlockAndFacing(blockPos,event);
        if (event.isPre()) {
            event.setYaw(mc.thePlayer.rotationYaw + 180);
            event.setPitch(pistsch);


        }



        if (NoSprint.getValBoolean()) {
            if (AirSprint.getValBoolean()){
                if (!mc.thePlayer.onGround){
                    if (mc.gameSettings.keyBindJump.pressed)
                        mc.thePlayer.setSprinting(true);
                }else {
                    if (mc.gameSettings.keyBindJump.pressed)
                        mc.thePlayer.setSprinting(false);
                }

            }else {
                mc.thePlayer.setSprinting(false);
            }


        } else {

            mc.thePlayer.setSprinting(true);
        }

        if (modemove.getValString().equalsIgnoreCase("Normal")) {

            mc.timer.timerSpeed = 1;
        }
        if (modemove.getValString().equalsIgnoreCase("Boost")) {
            double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
            double y = mc.thePlayer.posY;
            double x = mc.thePlayer.posX;
            double z = mc.thePlayer.posZ;
            if (mc.thePlayer.moveForward != 0) {
                if (me.pseey.utils.TimeHelper.hasReached(500)){
                    MainUtil.SendClientMesage("Boost");
                    mc.thePlayer.setPositionAndUpdate(x + -Math.sin(yaw) * +1, mc.thePlayer.posY, z + Math.cos(yaw) * +1);
                    me.pseey.utils.TimeHelper.reset();
                }
            }



        }
        if (modemove.getValString().equalsIgnoreCase("Timer")) {
           mc.timer.timerSpeed = Timer.getValInt();

        }else {
            mc.timer.timerSpeed = 1;
        }
         int d = 0;
        if (Towerob.getValString().equalsIgnoreCase("TowerMotion")) {
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
            if (Towerob.getValString().equalsIgnoreCase("LegitTower")) {
                mc.thePlayer.jump();
                event.setPitch(90f);
                placmentblock(blockPos, currentFacing);
            }
        }

            //    float[] rotate = getRotations(blockPos, currentFacing);
            //   event.setYaw(rotate[0]);
            //    event.setPitch(rotate[1]);


            if (event.getState() == EventMotionUpdate.State.POST && TimeHelper.hasReached(Speed) || Spartan == true) {
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


        //if(!shouldDownwards()) {
         if (this.mc.theWorld.getBlockState(var1.add(0, -1, 0)).getBlock() != Blocks.air) {
            currentPos = var1.add(0, -1, 0);
            currentFacing = EnumFacing.UP;
             e.setYaw(yaw);
        } else if (this.mc.theWorld.getBlockState(var1.add(-1, 0, 0)).getBlock() != Blocks.air) {
            currentPos = var1.add(-1, 0, 0);
            currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(var1.add(1, 0, 0)).getBlock() != Blocks.air) {
            currentPos = var1.add(1, 0, 0);
            currentFacing = EnumFacing.WEST;
        } else if (this.mc.theWorld.getBlockState(var1.add(0, 0, -1)).getBlock() != Blocks.air) {

            currentPos = var1.add(0, 0, -1);
            currentFacing = EnumFacing.SOUTH;


        } else if (this.mc.theWorld.getBlockState(var1.add(0, 0, 1)).getBlock() != Blocks.air) {

            currentPos = var1.add(0, 0, 1);
            currentFacing = EnumFacing.NORTH;

        } else if (this.mc.theWorld.getBlockState(var1.add(-1, 0, -1)).getBlock() != Blocks.air) {
            currentPos = var1.add(-1, 0, -1);
            currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(var1.add(-1, 0, 1)).getBlock() != Blocks.air) {
            currentPos = var1.add(-1, 0, 1);
            currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(var1.add(1, 0, -1)).getBlock() != Blocks.air) {
            currentPos = var1.add(1, 0, -1);
            currentFacing = EnumFacing.WEST;
        } else if (this.mc.theWorld.getBlockState(var1.add(1, 0, 1)).getBlock() != Blocks.air) {
            currentPos = var1.add(1, 0, 1);
            currentFacing = EnumFacing.WEST;
        } else if (this.mc.theWorld.getBlockState(var1.add(-1, -1, 0)).getBlock() != Blocks.air) {
            currentPos = var1.add(-1, -1, 0);
            currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(var1.add(1, -1, 0)).getBlock() != Blocks.air) {
            currentPos = var1.add(1, -1, 0);
            currentFacing = EnumFacing.WEST;
        } else if (this.mc.theWorld.getBlockState(var1.add(0, -1, -1)).getBlock() != Blocks.air) {
            currentPos = var1.add(0, -1, -1);
            currentFacing = EnumFacing.SOUTH;

        } else if (this.mc.theWorld.getBlockState(var1.add(0, -1, 1)).getBlock() != Blocks.air) {
            currentPos = var1.add(0, -1, 1);
            currentFacing = EnumFacing.NORTH;

        } else if (this.mc.theWorld.getBlockState(var1.add(-1, -1, -1)).getBlock() != Blocks.air) {
            currentPos = var1.add(-1, -1, -1);
            currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(var1.add(-1, -1, 1)).getBlock() != Blocks.air) {
            currentPos = var1.add(-1, -1, 1);
            currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(var1.add(1, -1, -1)).getBlock() != Blocks.air) {
            currentPos = var1.add(1, -1, -1);
            currentFacing = EnumFacing.WEST;
        } else if (this.mc.theWorld.getBlockState(var1.add(1, -1, 1)).getBlock() != Blocks.air) {
            currentPos = var1.add(1, -1, 1);
            currentFacing = EnumFacing.WEST;
        } else if (this.mc.theWorld.getBlockState(var1.add(-2, 0, 0)).getBlock() != Blocks.air) {
            currentPos = var1.add(-2, 0, 0);
            currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(var1.add(2, 0, 0)).getBlock() != Blocks.air) {
            currentPos = var1.add(2, 0, 0);
            currentFacing = EnumFacing.WEST;
        } else if (this.mc.theWorld.getBlockState(var1.add(0, 0, -2)).getBlock() != Blocks.air) {
            currentPos = var1.add(0, 0, -2);
            currentFacing = EnumFacing.SOUTH;

        } else if (this.mc.theWorld.getBlockState(var1.add(0, 0, 2)).getBlock() != Blocks.air) {
            currentPos = var1.add(0, 0, 2);
            currentFacing = EnumFacing.NORTH;

        } else if (this.mc.theWorld.getBlockState(var1.add(-2, 0, -2)).getBlock() != Blocks.air) {
            currentPos = var1.add(-2, 0, -2);
            currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(var1.add(-2, 0, 2)).getBlock() != Blocks.air) {
            currentPos = var1.add(-2, 0, 2);
            currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(var1.add(2, 0, -2)).getBlock() != Blocks.air) {
            currentPos = var1.add(2, 0, -2);
            currentFacing = EnumFacing.WEST;
        } else if (this.mc.theWorld.getBlockState(var1.add(2, 0, 2)).getBlock() != Blocks.air) {
            currentPos = var1.add(2, 0, 2);
            currentFacing = EnumFacing.WEST;
        } else if (this.mc.theWorld.getBlockState(var1.add(0, 1, 0)).getBlock() != Blocks.air) {
            currentPos = var1.add(0, 1, 0);
            currentFacing = EnumFacing.DOWN;

        } else if (this.mc.theWorld.getBlockState(var1.add(-1, 1, 0)).getBlock() != Blocks.air) {
            currentPos = var1.add(-1, 1, 0);
            currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(var1.add(1, 1, 0)).getBlock() != Blocks.air) {
            currentPos = var1.add(1, 1, 0);
            currentFacing = EnumFacing.WEST;
        } else if (this.mc.theWorld.getBlockState(var1.add(0, 1, -1)).getBlock() != Blocks.air) {
            currentPos = var1.add(0, 1, -1);
            currentFacing = EnumFacing.SOUTH;
        } else if (this.mc.theWorld.getBlockState(var1.add(0, 1, 1)).getBlock() != Blocks.air) {
            currentPos = var1.add(0, 1, 1);
            currentFacing = EnumFacing.NORTH;

        } else if (this.mc.theWorld.getBlockState(var1.add(-1, 1, -1)).getBlock() != Blocks.air) {
            currentPos = var1.add(-1, 1, -1);
            currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(var1.add(-1, 1, 1)).getBlock() != Blocks.air) {
            currentPos = var1.add(-1, 1, 1);
            currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(var1.add(1, 1, -1)).getBlock() != Blocks.air) {
            currentPos = var1.add(1, 1, -1);
            currentFacing = EnumFacing.WEST;
        } else if (this.mc.theWorld.getBlockState(var1.add(1, 1, 1)).getBlock() != Blocks.air) {
            currentPos = var1.add(1, 1, 1);
            currentFacing = EnumFacing.WEST;
        }



        }


    public BlockPos currentPos;
    public EnumFacing currentFacing;

    @Override
    public void onDisable() {
        super.onDisable();
        mc.timer.timerSpeed = 1F;
    }
}


















  //if (Helper.mc().getCurrentServerData().serverIP.toLowerCase().contains("hypixel.net")