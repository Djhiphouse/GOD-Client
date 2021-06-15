package me.bratwurst.module.modules.Crasher;




import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;

public class StaffDetection extends Module {
    public static Setting mode1;
    public static EntityLivingBase target1;
    public static Setting minCps, AutoPanic, Range, FailHits, Rotate, AutoBlock, NoRotate, LegitAutoBlock, Movefix, Smoth,
            AutoLeave;

    public StaffDetection() {
        super("StaffDetection", Category.EXPLOIT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Neruxvace");
        options.add("Mineplex");
        options.add("Mush");
        options.add("mccentral");
        options.add("Rededark");
        Client.setmgr.rSetting(mode1 = new Setting(EnumChatFormatting.RED +"Server", this, "Neruxvace", options));
    }

    @Override
    public void setup() {
        Client.setmgr.rSetting(AutoPanic = new Setting(EnumChatFormatting.AQUA +"AutoPanic", this, false));
        Client.setmgr.rSetting(AutoLeave = new Setting(EnumChatFormatting.AQUA +"AutoLeave", this, false));
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (mode1.getValString().equalsIgnoreCase("Neruxvace")) {
            Neruxvace();
            this.setDisplayname(EnumChatFormatting.RED + " - Neruxvace");
        } else if (mode1.getValString().equalsIgnoreCase("Mush")) {
            Mush();
            this.setDisplayname(EnumChatFormatting.RED + " - Mush");
        }


    }

    public void Neruxvace() {
        for (Entity e : mc.theWorld.loadedEntityList) {
            if (e instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) e;
                switch (player.getName()) {
                    case "LeReavan":
                    case "verbxnnt":
                    case "Sqltyy":
                    case "pyxw":
                    case "DarkAkaashi":
                    case "acid_qv":
                    case "Forumbeitrag":
                    case "MrsBaumMaedl":
                    case "zSyon":
                    case "Shinqx":
                    case "BustedDE":
                    case "ByLuqa":
                    case "Gekuschelt":
                    case "Stydt":
                    case "Hoffnungslose":
                    case "Shieva":
                    case "hqnabi":
                    case "KimCraft04":
                    case "FelixTheTopp":
                    case "EinsTim":
                    case "OcelotTV":
                    case "DiesesMLennG":
                    case "funpower9":
                    case "4w3s":
                    case "maybecode":
                    case "TorbusEntwicklus":
                    case "ALZlper":
                    case "xkuyax":
                    case "MCMDEV":
                    case "eaCatherine":
                    case "CodeSegmxnt":
                    case "lvkaas":
                    case "ChickenPat":
                    case "Noaaah":


                        if (TimeHelper.hasReached(2000)) {
                            PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED + "Supporter wurde erkannt");
                            if (AutoLeave.getValBoolean()) {
                                mc.thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage("/leave"));
                            }
                            if (AutoPanic.getValBoolean()) {
                                for (Module m : Client.moduleManager.getEnabledModules()) {
                                    if (!m.getName().equalsIgnoreCase("StaffDetection")) {
                                        m.toggle();
                                    }

                                }


                            }
                            TimeHelper.reset();
                        }
                        break;
                }
            }
        }
    }

    public void Mineplex() {
        for (Entity e : mc.theWorld.loadedEntityList) {
            if (e instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) e;
                switch (player.getName()) {
                    //hier die namen rein bzw: case "Bratwurst001":
                    case "w":
                    case "ww":
                    case "www":
                    case "wwww":
                    case "wwwww":


                        if (TimeHelper.hasReached(2000)) {
                            PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED + "Supporter wurde erkannt");
                            if (AutoLeave.getValBoolean()) {
                                mc.thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage("/leave"));
                            }
                            if (AutoPanic.getValBoolean()) {
                                for (Module m : Client.moduleManager.getEnabledModules()) {
                                    if (!m.getName().equalsIgnoreCase("StaffDetection")) {
                                        m.toggle();
                                    }

                                }


                            }
                            TimeHelper.reset();
                        }
                        break;
                }
            }
        }

    }


    public void Mush() {
        for (Entity e : mc.theWorld.loadedEntityList) {
            if (e instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) e;
                switch (player.getName()) {
                    //hier die namen rein bzw: case "Bratwurst001":
                    case "TheGlockz":
                    case "Trial":
                    case "mathy9":
                    case "NannoFromNowhere":
                    case "zStrikee":
                    case "Zaard":
                    case "WksS":
                    case "todeee":
                    case "thiagodropa":
                    case "staay":
                    case "Repy_":
                    case "nolanzinhuu":
                    case "mxthsz_":
                    case "MatheusBat":
                    case "Jvfmjamba":
                    case "sevenho":
                    case "goltz":
                    case "Anjeel":
                    case "angel_try":
                    case "WGBrasil":
                    case "Start_":
                    case "Poderosoftw":
                    case "mariaum":
                    case "KuroNoDensetsu":
                    case "ISlowneR":
                    case "FEIJAOO":
                    case "Feelipee":



                        if (TimeHelper.hasReached(2000)) {
                            PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED + "Supporter wurde erkannt");
                            if (AutoLeave.getValBoolean()) {
                                mc.thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage("/leave"));
                            }
                            if (AutoPanic.getValBoolean()) {
                                for (Module m : Client.moduleManager.getEnabledModules()) {
                                    if (!m.getName().equalsIgnoreCase("StaffDetection")) {

                                        m.toggle();
                                    }

                                }


                            }
                            TimeHelper.reset();
                        }
                        break;
                }
            }
        }

    }


}

