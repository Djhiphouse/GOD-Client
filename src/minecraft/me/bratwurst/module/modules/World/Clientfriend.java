package me.bratwurst.module.modules.World;

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

public class Clientfriend extends Module {

    public static ArrayList<EntityLivingBase> antibot = new ArrayList<>();
    public static Setting mode1;

    public Clientfriend() {
        super("Clientfriend", Category.WORLD);
        ArrayList<String> options = new ArrayList<>();


    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        for (Entity bot : mc.theWorld.loadedEntityList) {
            if (bot instanceof EntityPlayer) {
                if (bot != mc.thePlayer) {
                    System.out.println(bot);
                    if (bot.getDisplayName().equals("§a[§bGod§cOwner§a] §7God531")) {

<<<<<<< Updated upstream
=======
                        antibot.add((EntityLivingBase) bot);
>>>>>>> Stashed changes


                }
            }
        }

    }

    @Override
    public void onDisable() {
        super.onDisable();
        antibot.clear();
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }
}