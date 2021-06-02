package me.bratwurst.module.modules.render;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventManager;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.renderer.ItemRenderer;

import java.util.ArrayList;

public class Hitanimation extends Module {
    public static Setting mode1;
    public static Setting x, y, z, Swing, Swing2,Slush;
    public Hitanimation() {
        super("BlockAnimation", Category.RENDER);
        ArrayList<String> options = new ArrayList<>();
        options.add("1.7");
        options.add("BIND");
        options.add("Skidma");
        options.add("Leaked");
        options.add("Slide");
        options.add("Stab");
        options.add("Spin");
        options.add("Astolfo");
        options.add("Virtue");
        options.add("Slap");
        options.add("Sink");
        options.add("ETB");
        options.add("Exhibobo");
        options.add("Wax");
        options.add("Table");
        options.add("Remix");
        Client.instance.setmgr.rSetting(new Setting("BlockAnimation", this, "Slide", options));
        Client.instance.setmgr.rSetting(new Setting("BlockAnimation Speed", this, 3, 1, 50, true));

    }



    @EventTarget
    public void onUpdate(EventUpdate event) {

    }



}
