package me.bratwurst.module.modules.Player;


import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

public class SafeWalk extends Module {
    public SafeWalk() {
        super("Safewalk", Category.PLAYER);
    }
    public static  Boolean Safewalk = false;
    @Override
    public void onEnable() {
        Safewalk = true;

    }

    @Override
    public void onDisable() {
        Safewalk = false;

    }
}
