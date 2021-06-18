package me.bratwurst.manager;

import de.Hero.example.GUI;
import me.bratwurst.Client;
import me.bratwurst.module.Module;
import me.bratwurst.module.modules.Configs.*;
import me.bratwurst.module.modules.Crasher.*;
import me.bratwurst.module.modules.Player.*;
import me.bratwurst.module.modules.World.*;
import me.bratwurst.module.modules.combat.*;
import me.bratwurst.module.modules.cosmetics.Wings;
import me.bratwurst.module.modules.cosmetics.WitchHat;
import me.bratwurst.module.modules.Crasher.Crashskull;
import me.bratwurst.module.modules.fun.Parkour;
import me.bratwurst.module.modules.movement.*;
import me.bratwurst.module.modules.render.*;

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
        addModule(new TargetStrafe());
        addModule(new VeloCity());
        addModule(new HUD());
        addModule(new FullBright());
        addModule(new Glide());
        addModule(new Speed());
        addModule(new AutoPotion());
        addModule(new Chesteal());
        addModule(new FastPlace());
        addModule(new InventoryMove());
        addModule(new Nofall());
        addModule(new NoSlowdown());
        addModule(new Blink());
        addModule(new Regen());
        addModule(new SafeWalk());
        addModule(new Sprint());
        addModule(new Eagle());
        addModule(new Crasher());
        addModule(new PexExploit());
        addModule(new Jesus());
        addModule(new Bedfucker());
        addModule(new InventoryManager());
        addModule(new TriggerBot());
        addModule(new AutoRespawn());
        addModule(new clientspammer());
        addModule(new Griefergamesspam());
        addModule(new Hitanimation());
        addModule(new LabyFaker());
        addModule(new Sacffold());
        addModule(new FightBot());
        addModule(new AntiAim());
        addModule(new NoHead());
        addModule(new DeathTP());
        addModule(new Strafe());
        addModule(new ESP());
        addModule(new Disabler());
        addModule(new VClip());
        addModule(new InfiniteAura());
        addModule(new Consolenspammer());
        addModule(new AirJump());
        addModule(new FastEat());
        addModule(new Spieder());
        addModule(new Step());
        addModule(new Tracer());
        addModule(new Fastbow());
        addModule(new AutoTool());
        addModule(new Zensierung());
        addModule(new NoWalk());
        addModule(new PingSpoof());
     // addModule(new ChestESP());
        addModule(new MurderMysteryHack());
        addModule(new Mush());
        addModule(new Hypixel());
        addModule(new Rededark());
        addModule(new introuble());
        addModule(new Mineplex());
        addModule(new Cubecraft());
        addModule(new Fastuse());
        addModule(new Parkour());
        addModule(new TNTBlock());
        addModule(new HighJump());
        addModule(new FastLader());
        addModule(new BugUp());
        addModule(new Criticals());
        addModule(new Wings());
        //addModule(new WhitherEffect());
        addModule(new WitchHat());
        addModule(new HitBox());
        addModule(new NoHurtCam());
        addModule(new Clientfriend());
        addModule(new Teleport());
        addModule(new Superhit());
        addModule(new Crashskull());
        addModule(new Crashblock());
        addModule(new ExtraEchant());
        addModule(new NameProtect());
      //  addModule(new bowfly());
        addModule(new nuker());

        addModule(new MidClick());
        addModule(new AntiBan());
        addModule(new AntiExploitCrash());
        addModule(new AntiTooManyPackets());
      //  addModule(new Tracer());
      //  addModule(new BowAimbot());
        addModule(new Wtap());
        //addModule(new LSD());
          addModule(new FastBreak());

          addModule(new SigmaDelete());
          addModule(new StairSpeed());
          addModule(new GodMode());
          addModule(new StaffDetection());
          addModule(new ChestAura());
          addModule(new BlockOverlay());
          addModule(new AntiVanish());
          addModule(new Challenge());
          addModule(new PearlFly());
          addModule(new AutoSword());
         // addModule(new Opsign());
         addModule(new GtaDeadScreen());






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
    public static void setExtraInfo(String extraInfo) {
        extraInfo = extraInfo;
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
