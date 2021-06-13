package me.bratwurst.module.modules.World;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

import java.util.ArrayList;
import java.util.Random;

public class Challenge extends Module {
    int lastfov;
    Random r;
    public static Setting mode1;

    public Challenge() {
        super("Challenge", Category.WORLD);
        ArrayList<String> options = new ArrayList<>();
        options.add("No-Forward");
        options.add("No-Backward");
        options.add("No-Right");
        options.add("No-Left");
        options.add("No-Jump");
        options.add("Fuck-Forward");
        options.add("Fuck-Backward");
        options.add("Fuck-Right");
        options.add("Fuck-Left");
        options.add("SuperSecretSpam");
        options.add("FOV-Fucker");
        options.add("Size-Fucker");
        options.add("FPS-Fucker");
        options.add("Dropper");
        options.add("Jumper");
        Client.setmgr.rSetting(mode1 = new Setting("Challenge Mode", this, "Normal", options));
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate event) {
        if (mode1.getValString().equalsIgnoreCase("No-Forward")) {
            noForward();
        } else if (mode1.getValString().equalsIgnoreCase("No-Backward")) {
            noBackward();
        } else if (mode1.getValString().equalsIgnoreCase("No-Right")) {
            noRight();
        } else if (mode1.getValString().equalsIgnoreCase("No-Left")) {
            noLeft();
        } else if (mode1.getValString().equalsIgnoreCase("No-Jump")) {
            noJump();
        } else if (mode1.getValString().equalsIgnoreCase("Fuck-Forward")) {
            fuckForward();
        } else if (mode1.getValString().equalsIgnoreCase("Fuck-Backward")) {
            fuckBackward();
        } else if (mode1.getValString().equalsIgnoreCase("Fuck-Right")) {
            fuckRight();
        } else if (mode1.getValString().equalsIgnoreCase("Fuck-Left")) {
            fuckLeft();
        } else if (mode1.getValString().equalsIgnoreCase("SuperSecretSpam")) {
            superSecretSpam();
        } else if (mode1.getValString().equalsIgnoreCase("FOV-Fucker")) {
            fovFucker();
        } else if (mode1.getValString().equalsIgnoreCase("Size-Fucker")) {
            sizeFucker();
        } else if (mode1.getValString().equalsIgnoreCase("FPS-Fucker")) {
            fpsFucker();
        } else if (mode1.getValString().equalsIgnoreCase("Dropper")) {
            dropper();
        } else if (mode1.getValString().equalsIgnoreCase("Jumper")) {
            jumper();
        }
    }

    public void fuckForward() {
        this.mc.gameSettings.keyBindForward.pressed = true;
    }

    public void fuckBackward() {
        this.mc.gameSettings.keyBindBack.pressed = true;
    }

    public void fuckRight() {
        this.mc.gameSettings.keyBindRight.pressed = true;
    }

    public void fuckLeft() {
        this.mc.gameSettings.keyBindLeft.pressed = true;
    }

    public void fpsFucker() {
        this.mc.gameSettings.limitFramerate = this.r.nextInt(100);
    }

    public void jumper() {
        if (this.mc.thePlayer.onGround) {
            this.mc.thePlayer.jump();
        }
    }

    public void dropper() {
        this.mc.thePlayer.dropItem(this.mc.thePlayer.getHeldItem().getItem(), 1);
    }

    public void sizeFucker() {
        this.mc.gameSettings.guiScale = this.r.nextInt(3);
    }

    public void fovFucker() {
        this.mc.gameSettings.fovSetting = (float) this.r.nextInt(100);
    }

    public void superSecretSpam() {
        this.mc.entityRenderer.activateNextShader();
    }

    public void noJump() {
        if (this.mc.gameSettings.keyBindJump.pressed) {
            this.mc.gameSettings.keyBindJump.pressed = false;
        }
    }

    public void noLeft() {
        if (this.mc.gameSettings.keyBindLeft.pressed) {
            this.mc.gameSettings.keyBindLeft.pressed = false;
        }
    }

    public void noRight() {
        if (this.mc.gameSettings.keyBindRight.pressed) {
            this.mc.gameSettings.keyBindRight.pressed = false;
        }
    }

    public void noBackward() {
        if (this.mc.gameSettings.keyBindBack.pressed) {
            this.mc.gameSettings.keyBindBack.pressed = false;
        }
    }

    public void noForward() {
        if (this.mc.gameSettings.keyBindForward.pressed) {
            this.mc.thePlayer.setSprinting(false);
            this.mc.gameSettings.keyBindForward.pressed = false;
        }
    }
}
