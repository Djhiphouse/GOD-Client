package me.bratwurst.module.modules.Crasher;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C01PacketChatMessage;

import java.util.ArrayList;
import java.util.Random;

public class Griefergamesspam extends Module {
    public ArrayList<String> Playername = new ArrayList<>();
    public Setting Delay, Bypass, Range, MoveFix;
    public static Setting mode1;

    public Griefergamesspam() {
        super("Griefergamesspam", Category.EXPLOIT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Fakemoney");
        options.add("EventSpammer");
        options.add("Tpsfalle");
        options.add("Geld-Gammler");
        options.add("Msg-Spammer");
        Client.setmgr.rSetting(mode1 = new Setting("SpamMode Mode", this, "Fakemoney", options));

    }

    @Override
    public void setup() {

        Client.setmgr.rSetting(Delay = new Setting("Delay", this, 1300, 400, 2250, true));

    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        Random rdm = new Random();
        int zufall = rdm.nextInt();
        if (mode1.getValString().equalsIgnoreCase("Fakemoney")) {
            Fakemoney(Delay.getValInt());
        } else if (mode1.getValString().equalsIgnoreCase("EventSpammer")) {
            Eventspammer(Delay.getValInt());
        } else if (mode1.getValString().equalsIgnoreCase("Tpsfalle")) {
            Tpsfalle(Delay.getValInt());
        } else if (mode1.getValString().equalsIgnoreCase("Geld-Gammler")) {
            needmoney(Delay.getValInt());
        }else if (mode1.getValString().equalsIgnoreCase("Msg-Spammer")) {
            MsgSpammer(Delay.getValInt());
        }
    }

    Random rdm = new Random();


    public void Fakemoney(int delay) {
        Random rdm = new Random();
        int zufall = rdm.nextInt();
        String Fakemoneymessage = "&ahat dir " + zufall + " gegeben";

        delay = Delay.getValInt();
        if (TimeHelper.hasReached(delay)) {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(Fakemoneymessage));
            TimeHelper.reset();
        }
    }



    public void Eventspammer(int delay) {
        Random rdm = new Random();
        int zufall = rdm.nextInt();
        String Eventmessage = zufall + "&4Event bei Meinem Plot Heftige drops und Money drops kommt schnell " + zufall;
        delay = Delay.getValInt();
        if (TimeHelper.hasReached(delay)) {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(Eventmessage));
            TimeHelper.reset();
        }
    }



    public void Tpsfalle(int delay) {
        Random rdm = new Random();
        int zufall = rdm.nextInt();
        String Tpatext = zufall + "&4Drope gerade ein Paar Drachen eier auf meinem Plot kommt alle!!!! no joke" + zufall;


        delay = Delay.getValInt();
        if (TimeHelper.hasReached(delay)) {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(Tpatext));
            TimeHelper.reset();
        }
    }



    public void needmoney(int delay) {
        Random rdm = new Random();
        int zufall = rdm.nextInt();

        String needdmoney = zufall + "Kann mir bitte jemand Geld geben ich habe nix wurde gescammt!!" + zufall;
        delay = Delay.getValInt();
        if (TimeHelper.hasReached(delay)) {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(needdmoney));
            TimeHelper.reset();
        }
    }

    public void MsgSpammer(int delay) {
        Random rdm = new Random();
        int zufall = rdm.nextInt();
        String Spamm = "Hi du pisser" + zufall;
        delay = Delay.getValInt();
        for (Object o : mc.theWorld.loadedEntityList) {
             EntityPlayer target = (EntityPlayer) o;
                 if (TimeHelper.hasReached(delay)) {
                     if (target != mc.thePlayer){
                         Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/msg "+target.getName() + " "+Spamm));
                         PlayerUtils.sendMessage("Name: " + target.getName());
                         TimeHelper.reset();
                     }


                }
            }

        }


    public static long randommoney(double min, double max) {
        return (long) ((Math.random() * (1000 / min - 1000 / max + 1)) + 1000 / max);
    }

}