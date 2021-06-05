package me.bratwurst.module.modules.World;

import de.Hero.clickgui.elements.ModuleButton;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMiddleClick;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.manager.FreundManager;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;

public class MidClick extends Module {
    private ModuleButton mb = null;
    public MidClick() {
        super("MidClick", Category.WORLD);
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {

        if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit instanceof EntityPlayer && !Client.getInstance().getModuleManager().getModuleByName("Aura").isEnabled() &&  !Client.getInstance().getModuleManager().getModuleByName("HitBox").isEnabled()&&  !Client.getInstance().getModuleManager().getModuleByName("TriggerBot").isEnabled()&&  !Client.getInstance().getModuleManager().getModuleByName("Infiniteaura").isEnabled()&&  !Client.getInstance().getModuleManager().getModuleByName("Teleport").isEnabled() &&  !Client.getInstance().getModuleManager().getModuleByName("Superhit").isEnabled()){
            String s = mc.objectMouseOver.entityHit.getName();
            if (!FreundManager.getInstance().isFriend(s) && mc.gameSettings.keyBindAttack.pressed ) {
                FreundManager.getInstance().addFriend(s);
                PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Du hast: "+s+" zu deinen Freund geaddet");
            }

            if (FreundManager.getInstance().isFriend(s) && mc.gameSettings.keyBindSneak.pressed ){
                FreundManager.getInstance().removeFriend(s);
                PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Du hast: "+s+" von deiner Freundesliste Remove");
            }
        }

    }

    @Override
    public void onDisable() {
        super.onDisable();
        for (Entity Midfreind : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if (Midfreind instanceof EntityPlayer && Midfreind != null) {
                if (Midfreind != mc.thePlayer) {
                   String fr =  Midfreind.getName();
                    FreundManager.getInstance().removeFriend(fr);
                }
            }
        }
    }
}
