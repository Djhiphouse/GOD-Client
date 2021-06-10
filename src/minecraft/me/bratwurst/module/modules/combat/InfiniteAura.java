package me.bratwurst.module.modules.combat;

import de.Hero.clickgui.elements.ModuleButton;
import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.manager.pathfinding.CustomVec3;
import me.bratwurst.manager.pathfinding.PathfindingUtils;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.*;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class InfiniteAura extends Module {
    public static Setting mode1;
    private ModuleButton mb = null;
    public static EntityLivingBase target1;
    public static Setting minCps, Target, Range, Delay, APS;
    private static EntityLivingBase en;
    boolean attack = true;
    double x;
    double y;
    double z;
    double xPreEn;
    double yPreEn;
    double zPreEn;
    double xPre;
    double yPre;
    double zPre;
    int stage = 0;
    ArrayList<Vec3> positions = new ArrayList<Vec3>();
    ArrayList<Vec3> positionsBack = new ArrayList<Vec3>();
    public static final double maxXZTP = 90.5;
    public static final int maxYTP = 90;

    public ArrayList<CustomVec3> path = new ArrayList<>();

    public InfiniteAura() {
        super("InfiniteAura", Category.COMBAT);
    }

    @Override
    public void setup() {

        Client.setmgr.rSetting(Target = new Setting("Targets", this, 2, 1, 20, true));
        Client.setmgr.rSetting(Range = new Setting("Range", this, 75, 50, 200, true));
        Client.setmgr.rSetting(APS = new Setting("APS", this, 1500, 800, 2000, true));


    }
public static float hits;
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        for (Object o : mc.theWorld.loadedEntityList) {
            if (o instanceof EntityPlayer) {
                EntityPlayer target = (EntityPlayer) o;
                if (target != mc.thePlayer && target != null) {
                   hits =  target.getDistanceToEntity(mc.thePlayer);
                       PlayerUtils.sendMessage(target.getName());
                       if (TimeHelper.hasReached(APS.getValInt())) {

                           teleportAndAttack(target);
                           TimeHelper.reset();
                       }





                }
            }
        }

    }

    public void teleportAndAttack(Entity target) {
        CustomVec3 from = new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
        CustomVec3 to = new CustomVec3(target.posX, target.posY, target.posZ);

        path = PathfindingUtils.computePath(from, to);

        for (CustomVec3 paths : path)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(paths.getX(), paths.getY(), paths.getZ(), true));

        // CLIENT SIDED


        // SERVER SIDED
        // mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());

          if (hits >= hits-1) {
              mc.playerController.attackEntity(mc.thePlayer,target);
              mc.thePlayer.swingItem();
              hits = 0;
          }




        Collections.reverse(path);

        for (CustomVec3 paths : path)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(paths.getX(), paths.getY(), paths.getZ(), true));
    }


    public void teleport(int x, int y, int z) {
        CustomVec3 from = new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
        CustomVec3 to = new CustomVec3(x, y, z);

        path = PathfindingUtils.computePath(from, to);

        for (CustomVec3 paths : path)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(paths.getX(), paths.getY(), paths.getZ(), true));

        mc.thePlayer.setPosition(x, y, z);
    }

}