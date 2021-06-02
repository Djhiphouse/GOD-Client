package me.bratwurst.module.modules.World;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.pseey.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.util.EnumChatFormatting;

public class MurderMysteryHack extends Module {
    public static Setting mode1;

    public Setting Speed, Murderleak, TeleportDelay, MoveFix;
    public MurderMysteryHack() {
        super("MurderMysteryHack", Category.WORLD);
    }

    @Override
    public void setup() {
        Client.setmgr.rSetting(Murderleak = new Setting("Murderleak", this, false));



    }
    @EventTarget
    public void onUpdate(EventMotionUpdate event) {
        for (Entity entity : Minecraft.getMinecraft().theWorld.loadedEntityList) {

            if (entity instanceof EntityPlayer) {
                EntityPlayer spieler = (EntityPlayer) entity;
                if (spieler.getHeldItem().getItem().equals(Items.iron_sword) || spieler.getHeldItem().getItem().equals(Items.feather) || spieler.getHeldItem().getItem().equals(Items.blaze_rod) || spieler.getHeldItem().getItem().equals(Items.stick) || spieler.getHeldItem().getItem().equals(Items.fish) || spieler.getHeldItem().getItem().equals(Items.golden_carrot)) {
                    PlayerUtils.sendMessage(EnumChatFormatting.AQUA +"Spieler: "+ EnumChatFormatting.YELLOW +  spieler.getName() + EnumChatFormatting.AQUA + "Ist ein Traitor");
                    if (Murderleak.getValBoolean()) {
                        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage("Der Traitor ist: " + spieler.getName()));
                    }

                }
            }
        }
    }
}