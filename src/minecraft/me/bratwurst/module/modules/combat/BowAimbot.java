package me.bratwurst.module.modules.combat;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.manager.FreundManager;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.RotationUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;

import java.util.ArrayList;
import java.util.List;

public class BowAimbot extends Module {
    public static Setting Silent, maxCps, Range, FailHits, Rotate, AutoBlock, NoRotate, LegitAutoBlock, Movefix, Smoth,
            Throughwalls;
    public static EntityLivingBase target;
    public BowAimbot() {
        super("BowAimbot", Category.COMBAT);
    }
    @Override
    public void setup() {
        Client.setmgr.rSetting(Silent = new Setting("MinCPS", this, true));

    }
    @EventTarget
    public void onUpdate(EventUpdate event) {


            if(shouldAim()){
                if (target != null) {
                    float[] rotations = RotationUtils.getBowAngles(target);
                    boolean silent = Silent.getValBoolean();
                    if(silent){
                        event.setYaw(rotations[0]);
                        event.setPitch(rotations[1]);
                    }else{
                        mc.thePlayer.rotationYaw = rotations[0];
                        mc.thePlayer.rotationPitch = rotations[1];
                    }
                }
            }
        }

    public static boolean shouldAim(){
        if(mc.thePlayer.inventory.getCurrentItem() == null || !(mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemBow))
            return false;
        if(Client.getInstance().getModuleManager().getModuleByName("Fastbow").isEnabled() && mc.gameSettings.keyBindUseItem.pressed)
            return true;
        if(mc.thePlayer.isUsingItem())
            return true;
        return false;
    }
    private EntityLivingBase getTarg() {
        List<EntityLivingBase> loaded = new ArrayList();
        for (Object o : mc.theWorld.getLoadedEntityList()) {
            if (o instanceof EntityLivingBase) {
                EntityLivingBase ent = (EntityLivingBase) o;
                if (ent instanceof EntityPlayer && ent != mc.thePlayer && mc.thePlayer.canEntityBeSeen(ent) && mc.thePlayer.getDistanceToEntity(ent) < 65 && !FreundManager.getInstance().isFriend(ent.getName())) {
                    if (ent == Aura.target1) {
                        return ent;
                    }
                    loaded.add(ent);
                }
            }
        }
        if (loaded.isEmpty()) {
            return null;
        }
        loaded.sort((o1, o2) -> {
            float[] rot1 = RotationUtils.getRotations(o1);
            float[] rot2 = RotationUtils.getRotations(o2);
            return (int) ((RotationUtils.getDistanceBetweenAngles(mc.thePlayer.rotationYaw, rot1[0])
                    + RotationUtils.getDistanceBetweenAngles(mc.thePlayer.rotationPitch, rot1[1]))
                    - (RotationUtils.getDistanceBetweenAngles(mc.thePlayer.rotationYaw, rot2[0])
                    + RotationUtils.getDistanceBetweenAngles(mc.thePlayer.rotationPitch, rot2[1])));
        });
        EntityLivingBase target = loaded.get(0);
        return target;
    }
}
