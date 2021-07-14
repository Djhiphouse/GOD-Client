package me.bratwurst.module.modules.Crasher;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.manager.MsgManager;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.news.crash.MSGSpamScreen;
import me.bratwurst.utils.MainUtil;
import me.bratwurst.utils.Msgtimer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;

public class MSgSpammer extends Module {
    private static ArrayList<String> MsgSpieler = new ArrayList<>();

    public MSgSpammer() {
        super("MsgSpammer", Category.EXPLOIT);
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {


        for (Object o : mc.theWorld.loadedEntityList) {
            if (o instanceof EntityPlayer) {
                EntityPlayer msgtarget = (EntityPlayer) o;
                if (msgtarget != mc.thePlayer && msgtarget != null) {
                    if (Msgtimer.hasReached(1000)) {
                        String Target = msgtarget.getName();
                        if (!MsgManager.getInstance().istarget(Target)) {

                            if (MSGSpamScreen.Msgtext != "" || !MSGSpamScreen.Msgtext.equalsIgnoreCase("")) {
                                MsgManager.getInstance().addtarget(Target);
                                Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage("/msg " + msgtarget.getName() + " " + MSGSpamScreen.Msgtext));

                            } else {
                                MainUtil.SendClientMesage(EnumChatFormatting.RED + "Kein Text Gesetzt");
                                toggle();
                            }

                        } else {
                            return;
                        }


                        Msgtimer.reset();
                    }
                }

            }
        }


    }


    @Override
    public void onDisable() {
        super.onDisable();
        for (Object o : mc.theWorld.loadedEntityList) {
            if (o instanceof EntityPlayer) {
                EntityPlayer msgtarget = (EntityPlayer) o;
                MsgManager.getInstance().removetarget(msgtarget.getName());
            }
        }
        MSGSpamScreen.Msgtext = "";
    }
}
