package me.bratwurst.utils;

import com.sun.security.ntlm.Client;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

import java.awt.*;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

public class PlayerUtil {
    protected static Minecraft mc = Minecraft.getMinecraft();
    private static final Potion[] blockedEffects;
    public EntityPlayer p;

    static {
        blockedEffects = new Potion[]{Potion.hunger, Potion.moveSlowdown, Potion.digSlowdown, Potion.harm, Potion.confusion, Potion.blindness, Potion.weakness, Potion.wither, Potion.poison};
    }

    public PlayerUtil() {
        this.p = mc.thePlayer;
    }

    public static void sendChat(String s) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(s);
    }

    public static void placeStackInHotbar(ItemStack itm) {
        if (mc.playerController.isInCreativeMode()) {
            mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, itm));
        }

    }

    public static void toFwd(double speed) {
        float yaw = PlayerUtil.mc.thePlayer.rotationYaw * 0.017453292f;
        PlayerUtil.mc.thePlayer.motionX -= (double) MathHelper.sin(yaw) * speed;
        PlayerUtil.mc.thePlayer.motionZ += (double) MathHelper.cos(yaw) * speed;
        double guardianboost = 0.5F;
    }

    public static void addChatMessageWithoutPrefix(String message) {
        mc.thePlayer.addChatMessage(new ChatComponentText(message));
    }

    public static boolean isCreative() {
        return mc.thePlayer.capabilities.isCreativeMode;
    }

    public static void copy(String s) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(s), (ClipboardOwner) null);
    }


    public static boolean isInLiquid() {
        return mc.thePlayer.isInsideOfMaterial(Material.lava) || mc.thePlayer.isInWater();
    }

    public static boolean isMoving2() {
        return ((mc.thePlayer.moveForward != 0.0F || mc.thePlayer.moveStrafing != 0.0F));
    }

    public static boolean MovementInput() {
        if (!(PlayerUtil.mc.gameSettings.keyBindForward.pressed || PlayerUtil.mc.gameSettings.keyBindLeft.pressed || PlayerUtil.mc.gameSettings.keyBindRight.pressed || PlayerUtil.mc.gameSettings.keyBindBack.pressed)) {
            return false;
        }
        return true;
    }
    public static float[] getFacePosEntityRemote(EntityLivingBase facing, Entity en) {
        if (en == null) {
            return new float[] { facing.rotationYawHead, facing.rotationPitch };
        }
        return getFacePosRemote(new Vec3(facing.posX, facing.posY + en.getEyeHeight(), facing.posZ),
                new Vec3(en.posX, en.posY + en.getEyeHeight(), en.posZ));
    }

    public static float[] getFacePosRemote(Vec3 src, Vec3 dest) {
        double diffX = dest.xCoord - src.xCoord;
        double diffY = dest.yCoord - (src.yCoord);
        double diffZ = dest.zCoord - src.zCoord;
        double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
        float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
        return new float[] {MathHelper.wrapAngleTo180_float(yaw),
                MathHelper.wrapAngleTo180_float(pitch) };
    }
    public static ArrayList<BlockPos> Block(Entity en) {
        BlockPos pos1 = new BlockPos(en.boundingBox.minX, en.boundingBox.minY - 0.01D, en.boundingBox.minZ);
        BlockPos pos2 = new BlockPos(en.boundingBox.maxX, en.boundingBox.minY - 0.01D, en.boundingBox.maxZ);
        Iterable<BlockPos.MutableBlockPos> collisionBlocks = BlockPos.getAllInBoxMutable(pos1, pos2);
        ArrayList<BlockPos> returnList = new ArrayList();
        for (BlockPos pos3 : collisionBlocks) {
            returnList.add(pos3);
        }
        return returnList;
    }

    public static boolean Blockpos(Entity en) {
        ArrayList<BlockPos> poses = Block(en);
        for (BlockPos pos : poses) {
            Block block = mc.theWorld.getBlockState(pos).getBlock();
            if ((!(block.getMaterial() instanceof MaterialTransparent)) && (block.getMaterial() != Material.air) && (!(block instanceof BlockLiquid)) && (block.isFullCube())) {
                return true;
            }
        }
        return false;
    }
}
