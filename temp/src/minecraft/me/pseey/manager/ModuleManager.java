package me.pseey.manager;

import de.Hero.clickgui.ClickGUI;
import de.Hero.example.GUI;
import me.pseey.Client;
import me.pseey.module.Module;
import me.pseey.module.modules.combat.AntiBot;
import me.pseey.module.modules.combat.Aura;
import me.pseey.module.modules.movement.LongJump;
import me.pseey.module.modules.render.PlayerESP;

import java.util.ArrayList;

public class ModuleManager {
    public ArrayList<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<Module>();
        LoadModule();
    }

    public void LoadModule() {
        addModule(new GUI());
        addModule(new PlayerESP());
        addModule(new Aura());
        addModule(new AntiBot());
        addModule(new LongJump());
        System.err.println("Modules: " + modules.size());
    }

    private void addModule(Module module) {
        modules.add(module);
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        for (Module module : Client.getInstance().getModuleManager().getModules()) {
            if (module.getName() != null) {
                if (module.getName().equalsIgnoreCase(name))
                    return module;
            }
        }
        return null;
    }

    public <T extends Module> T getModule(Class<T> clazz) {
        return (T) modules.stream().filter(mod -> mod.getClass() == clazz).findFirst().orElse(null);
    }

    public ArrayList<Module> getEnabledModules() {
        ArrayList<Module> enabled = new ArrayList<>();
        for (Module m : modules) {
            if (m.isToggle()) {
                enabled.add(m);
            } else {
                enabled.remove(m);
            }
        }
        return enabled;
    }
}
