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

import javax.swing.*;
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
        options.add("Jartex");
        Client.setmgr.rSetting(mode1 = new Setting(EnumChatFormatting.AQUA + "AntiBot Mode", this, "Checks", options));
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        for (Entity bot : mc.theWorld.loadedEntityList) {
            if (bot instanceof EntityPlayer) {
                if (bot != mc.thePlayer && bot != null) {
                    if (mode1.getValString().equalsIgnoreCase("Mineplex")) {
                        legit((EntityPlayer) bot);
                        this.setDisplayname(EnumChatFormatting.YELLOW + " - Mineplex");
                    } else if (mode1.getValString().equalsIgnoreCase("Hypixel")) {
                        Hypixel((EntityPlayer) bot);
                        this.setDisplayname(EnumChatFormatting.RED + " - Hypixel");
                    } else if (mode1.getValString().equalsIgnoreCase("Timolia")) {
                        Timolia((EntityPlayer) bot);
                        this.setDisplayname(EnumChatFormatting.BLUE + " - Timolia");
                    } else if (mode1.getValString().equalsIgnoreCase("Jartex")) {
                        Jartex((EntityPlayer) bot);
                        this.setDisplayname(EnumChatFormatting.AQUA + " - Jartex");
                    }

                }
            }
        }

    }

    public void legit(EntityPlayer bot) {
        if (bot.getCustomNameTag() == "" && mc.thePlayer.ticksExisted >= 40 && !antibot.contains(bot)) {
            antibot.add(bot);

        }
    }

    public void Hypixel(EntityPlayer bot) {
        if (bot.getCustomNameTag() == "" && mc.thePlayer.ticksExisted >= 120 && !antibot.contains(bot)) {
            antibot.add(bot);

        }
    }

    public void Timolia(EntityPlayer bot) {
        if (!antibot.contains(bot) && !bot.getName().startsWith("&")) {
            antibot.add(bot);
        }
    }

    public static int Groundticks = Aura.Groundticks;
    public static int Airticks = Aura.Airticks;

    public void Jartex(EntityPlayer bot) {
        if (bot.onGround ) {
            Groundticks++;
        }else if (bot.isAirBorne) {
            Airticks++;
        }
        if (bot.getCustomNameTag() == "" && mc.thePlayer.ticksExisted >= 40 && Groundticks > 410 && mc.thePlayer.ticksExisted >= 80 && !antibot.contains(bot)) {
            antibot.add(bot);
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
