package me.bratwurst.module.modules.World;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.manager.pathfinding.CustomVec3;
import me.bratwurst.manager.pathfinding.PathfindingUtils;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;

public class Teleport extends Module {
    public  static int damageState = 0;
    public Setting Bypass, Speed, mccSpeed, Speeddown, Zonecraft;
    public static Setting mode1;
    public ArrayList<CustomVec3> path = new ArrayList<>();

    public Teleport() {
        super("Teleport", Category.WORLD);
    }
    @Override
    public void setup() {


        Client.setmgr.rSetting(Zonecraft = new Setting(EnumChatFormatting.AQUA +"Zonecraft", this, false));
    }
public  static   int  x = 0;
    public  static    int y = 0;
    public  static    int z = 0;
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {





            if(mc.gameSettings.keyBindAttack.pressed) {
            if (Zonecraft.getValBoolean()) {
                if (damageState == 0) {
                    NetHandlerPlayClient netHandlerPlayClient = Minecraft.getMinecraft().getNetHandler();
                    double xx = mc.thePlayer.posX;
                    double zz = mc.thePlayer.posZ;
                    double yy = mc.thePlayer.posY;
                    for (int i = 0; i < 80; ++i) {
                        netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(xx, yy + 0.060100000351667404, zz, false));
                        netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(xx, yy + 01000237487257E-1, zz, false));
                        netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(xx, yy + 0.00499999888241191 + 1.0100003516674E-1, zz, false));
                    }
                    netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer(true));
                    damageState = 1;

                }else {
                    BlockPos pos = mc.objectMouseOver.getBlockPos();
                    if(pos != null) {
                        if (mc.thePlayer.hurtTime < 0){
                            Zonecrafttp(pos.getX(), pos.getY(), pos.getZ());
                        }
                    }

                }



                mc.gameSettings.keyBindAttack.pressed = false;
              }


        }
        if(mc.gameSettings.keyBindAttack.pressed) {
            BlockPos pos = mc.objectMouseOver.getBlockPos();
            if(pos != null)
                teleport(pos.getX(), pos.getY(), pos.getZ());
            mc.gameSettings.keyBindAttack.pressed = false;
        }
    }

    public void teleport(int x, int y, int z) {
        CustomVec3 from = new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
        CustomVec3 to = new CustomVec3(x, y, z);

        path = PathfindingUtils.computePath(from, to);

        for(CustomVec3 paths : path)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(paths.getX(), paths.getY(), paths.getZ(), true));

        mc.thePlayer.setPosition(x, y, z);
    }
    public  void Zonecrafttp(int x, int y, int z) {
        CustomVec3 from = new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
        CustomVec3 to = new CustomVec3(x, y, z);

        path = PathfindingUtils.computePath(from, to);

        for(CustomVec3 paths : path)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(paths.getX(), paths.getY(), paths.getZ(), true));

        mc.thePlayer.setPosition(x, y, z);
    }
}
