package me.bratwurst.module.modules.render;

import me.bratwurst.Client;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

public class Zensierung extends Module {
    public Zensierung() {
        super("Zensierung", Category.RENDER);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Client.Zensiert = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Client.Zensiert = false;
    }
}
