package me.pseey;

import com.thealtening.auth.TheAlteningAuthentication;
import com.thealtening.auth.service.AlteningServiceType;
import de.Hero.clickgui.ClickGUI;
import de.Hero.settings.SettingsManager;
import me.bratwurst.DiscordRP;
import me.pseey.event.EventManager;
import me.pseey.manager.ModuleManager;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.Display;

public class Client {

    /**
     * Main events you may need:
     * <p>
     * Minecraft:
     * - EventKeyboard
     * - EventMiddleClick
     * - EventTick
     * <p>
     * EntityPlayerSP:
     * - EventUpdate
     * - EventPreMotionUpdates
     * - EventPostMotionUpdates
     * <p>
     * GuiIngame:
     * - EventRender2D
     * <p>
     * EntityRenderer:
     * - EventRender3D
     */

    public static Client instance = new Client();

    public final String CLIENT_NAME = "God";
    public final String CLIENT_VERSION = "0.2";
    public final String CLIENT_PREFIX = EnumChatFormatting.AQUA + "["+EnumChatFormatting.BLUE + "God"+EnumChatFormatting.AQUA+"]";
    public final String CLIENT_NAMEB = CLIENT_NAME + " " + CLIENT_VERSION;

    public static String APIKey = "";

    public static boolean loadFile;

    public static TheAlteningAuthentication auth;

    public ModuleManager moduleManager;
    public EventManager eventManager;
    public static SettingsManager setmgr;
    public static ClickGUI clickgui;

    public void start() {
        instance = this;
        Display.setTitle(CLIENT_NAMEB);
        init();
    }

    public void init() {
        setmgr = new SettingsManager();
        moduleManager = new ModuleManager();
        eventManager = new EventManager();
        clickgui = new ClickGUI();
        DiscordRP.getInstance().startup();
    }

    public void shutdown() {
    }

    public static Client getInstance() {
        return instance;
    }

    public String getCLIENT_NAME() {
        return CLIENT_NAME;
    }

    public String getCLIENT_NAMEB() {
        return CLIENT_NAMEB;
    }

    public String getCLIENT_VERSION() {
        return CLIENT_VERSION;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public String getCLIENT_PREFIX() {
        return CLIENT_PREFIX;
    }

    public static void switchService(AlteningServiceType type) {
        auth = new TheAlteningAuthentication(type);
    }

}
