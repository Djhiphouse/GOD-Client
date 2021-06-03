package me.bratwurst.module.modules.combat;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

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
        options.add("TESTEN");
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
                   }else if (mode1.getValString().equalsIgnoreCase("TESTEN")) {
                       Test((EntityPlayer) bot);
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
    if ( !antibot.contains(bot) && !bot.getName().startsWith("&")) {
        antibot.add(bot);
    }
}
public static int Groundticks = 0;
    public static int Airticks = 0;
public void Test(EntityPlayer bot) {


        if (bot.onGround ) {
            Groundticks++;
        }else if (bot.isAirBorne) {
            Airticks++;
        }
        if (Aura.target1.getDistanceToEntity(mc.thePlayer) <= 5) {
            PlayerUtils.sendMessage(EnumChatFormatting.AQUA+ "--------------------------------------------------------------------------------------------------------------------");
            PlayerUtils.sendMessage("UUid: " + Aura.target1.getUniqueID().toString() + "Name: " + Aura.target1.getName() +  " Coustumname: " + Aura.target1.getCustomNameTag() + " Groundticks:  " + Groundticks + " Airticks: " + Airticks + " Ticksexited: " + Aura.target1.ticksExisted + " leben: " + Aura.target1.getHealth() + " Inventotysize: " + Aura.target1.getInventory().length + " falldistance: " + Aura.target1.fallDistance);

        }



}
    @Override
    public void onDisable() {
        super.onDisable();
        antibot.clear();
        Airticks = 0;
        Groundticks = 0;
    }
}
