package me.bratwurst.module.modules.combat;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;

public class AntiBot extends Module {

    public static ArrayList<EntityLivingBase> antibot = new ArrayList<>();
    public static Setting mode1;

    public AntiBot() {
        super("AntiBot", Category.COMBAT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Hypixel");
        options.add("Mineplex");
        options.add("Timolia");
        Client.setmgr.rSetting(mode1 = new Setting("AntiBot Mode", this, "Checks", options));
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        for (Entity bot : mc.theWorld.loadedEntityList) {
            if (bot instanceof EntityPlayer) {
                if (bot != mc.thePlayer && bot != null) {
                   if (mode1.getValString().equalsIgnoreCase("Mineplex")) {
                       legit((EntityPlayer) bot);
                   }else if (mode1.getValString().equalsIgnoreCase("Hypixel")) {
                       Hypixel((EntityPlayer) bot);
                   }else if (mode1.getValString().equalsIgnoreCase("Timolia")) {
                      Timolia((EntityPlayer) bot);
                   }

                }
            }
        }

    }

    public void legit(EntityPlayer bot) {
      if (bot.getCustomNameTag() == ""  && mc.thePlayer.ticksExisted >= 40 && !antibot.contains(bot)) {
            antibot.add(bot);

        }
    }

    public void Hypixel(EntityPlayer bot) {
        if (bot.getCustomNameTag() == ""  && mc.thePlayer.ticksExisted >= 120 && !antibot.contains(bot)) {
            antibot.add(bot);

        }
    }

public void Timolia(EntityPlayer bot) {
    if (mc.thePlayer.ticksExisted >= 90 && !antibot.contains(bot) && !bot.getName().startsWith("&") || !bot.getName().startsWith("$") || !bot.getName().startsWith("#")) {
        antibot.add(bot);
    }
}
}
