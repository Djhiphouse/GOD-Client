package me.bratwurst;

import com.github.creeper123123321.viafabric.ViaFabric;
import com.thealtening.auth.TheAlteningAuthentication;
import com.thealtening.auth.service.AlteningServiceType;
import de.Hero.clickgui.ClickGUI;
import de.Hero.settings.SettingsManager;
import me.bratwurst.event.EventManager;
import me.bratwurst.manager.CommandManager;
import me.bratwurst.manager.ConfigManager;
import me.bratwurst.manager.HWIDcheck.HWIDcheck;
import me.bratwurst.manager.ModuleManager;
import me.bratwurst.manager.network.GodNetworkClient;
import me.bratwurst.news.picture.EZ_HologrammManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.util.EnumChatFormatting;

import javax.swing.*;
import java.io.File;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Client {

    public static File filePath = getWorkingPath("minecraft/God/Tools/");

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
    public static boolean java8 = true;
    public boolean Premium = false;
    public boolean Freund = false;
    public boolean Supporter = false;
    public boolean Moderrator = false;
    public boolean Dev = false;
    public static boolean Zensiert = false;
    public static String ZensiertMOTD = EnumChatFormatting.RED + "--------------" + EnumChatFormatting.DARK_RED + "ZENSIERT" + EnumChatFormatting.RED + "--------------";
    public static Client instance = new Client();
    public static String ipadresse;
    public static String ipNumber;
    public static String ipVersion;
    public static String countryName;
    public static String countryCode2;
    public static String isp;
    public static String responseCode;
    public static String responseMessage;
    public static Client getInstance() {
        return instance;
    }
    public static Minecraft mc = Minecraft.getMinecraft();
    private EZ_HologrammManager hologramManager = new EZ_HologrammManager();

    public final String CLIENT_NAME = "God", CLIENT_VERSION = "1.3", CLIENT_CODER = "Bratwust001";

    public final String CLIENT_PREFIX = EnumChatFormatting.AQUA + "[" + EnumChatFormatting.BLUE + "GOD" + EnumChatFormatting.AQUA + "]";


    public static String APIKey = "";

    public static boolean loadFile;

    public static TheAlteningAuthentication auth;

    public static ModuleManager moduleManager;
    public static CommandManager commandManager;
    public EventManager eventManager;
    public static SettingsManager setmgr;
    public static ClickGUI clickgui;
    public static final String hwid = HWIDcheck.getHwid();
    public static final GodNetworkClient networkClient = new GodNetworkClient();

    public EZ_HologrammManager getHologramManager() {
        return this.hologramManager;
    }
    public void start() {
        instance = this;
        init();
    }

    public void init() {

        setmgr = new SettingsManager();
        moduleManager = new ModuleManager();
        eventManager = new EventManager();
        clickgui = new ClickGUI();
        commandManager = new CommandManager();
        if (!Client.getInstance().getModuleManager().getModuleByName("HUD").isToggle()) {
            Client.getInstance().getModuleManager().getModuleByName("HUD").toggle();

        }


        try {
            new ViaFabric().onInitialize();
        } catch (Exception e) {
        }

        try {
            ConfigManager.createFile();
        } catch (Exception e) {

        }


        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            try {

                EntityPlayerSP thePlayer = Minecraft.getMinecraft().thePlayer;
                if (thePlayer == null) {

                    return;
                }

                UUID uuid = thePlayer.getUniqueID();
                if (uuid == null) {

                    return;
                }

                String hwid = Client.hwid;
                networkClient.getIrcClient().send("");

                Client.networkClient.setStatus(uuid, hwid)
                        .exceptionally(throwable -> {
                            throwable.printStackTrace();
                            return null;
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 5, 5, TimeUnit.SECONDS);

        networkClient.register(hwid).handle((v, throwable) -> {
            Client.networkClient.isBlocked(hwid).handle((blocked, getErr) -> {
                if (getErr != null) {
                    getErr.printStackTrace();
                    System.exit(0);
                    return blocked;
                }
                if(blocked){
                    System.exit(1);

                    JOptionPane.showMessageDialog(null, "Du bist Gebannt!", "Banned", JOptionPane.ERROR_MESSAGE);
                }
                return blocked;
            });
            throwable.printStackTrace();
            return v;
        });

    }
    public static File getWorkingPath(String applicationName) {
        String userHome = System.getProperty("user.home", ".");
        File workingDirectory = null;
        switch (Client.getPlatform()) {
            case LINUX: {
                workingDirectory = new File(userHome, '.' + applicationName + '/');
                break;
            }
            case UNKNOWN:
            case MACOS: {
                workingDirectory = new File(userHome, "Library/Application Support/" + applicationName);
                break;
            }
            case WINDOWS:
            case SOLARIS: {
                String applicationData = System.getenv("APPDATA");
                if (applicationData != null) {
                    workingDirectory = new File(applicationData, "." + applicationName + '/');
                    break;
                }
                workingDirectory = new File(userHome, '.' + applicationName + '/');
                break;
            }
            default: {
                workingDirectory = new File(userHome, applicationName + '/');
            }
        }
        if (!workingDirectory.exists() && !workingDirectory.mkdirs()) {
            throw new RuntimeException("The working directory could not be created: " + workingDirectory);
        }
        return workingDirectory;
    }

    public static OS getPlatform() {
        String osName = System.getProperty("os.name").toLowerCase();
        for (OS os : OS.values()) {
            if (os == OS.UNKNOWN) continue;
            for (String name : os.getOsNames()) {
                if (!osName.contains(name)) continue;
                return os;
            }
        }
        return OS.UNKNOWN;
    }

    public static enum OS {
        LINUX("linux", "unix"),
        SOLARIS("solaris", "sunos"),
        WINDOWS("win"),
        MACOS("mac"),
        UNKNOWN(new String[0]);

        private String[] osNames;

        private OS(String ... osNames) {
            this.osNames = osNames;
        }

        public String[] getOsNames() {
            return this.osNames;
        }
    }
    public void shutdown() {
        DiscordRP.getInstance().shutdown();
    }


    public String getCLIENT_NAME() {
        return CLIENT_NAME;
    }

    public String getCLIENT_VERSION() {
        return CLIENT_VERSION;
    }

    public String getCLIENT_CODER() {
        return CLIENT_CODER;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public String getCLIENT_PREFIX() {
        return CLIENT_PREFIX;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }


    public static void switchService(AlteningServiceType type) {
        auth = new TheAlteningAuthentication(type);
    }

    public static ItemStack createPotionItem(String s) {
        ItemStack itemStack = new ItemStack((Item) Items.potionitem, 1, 0);
        itemStack.setItemDamage(16384);
        try {
            itemStack.setTagCompound(JsonToNBT.getTagFromJson(s));
        } catch (NBTException nBTException) {
        }
        return itemStack;
    }

    public static ItemStack createItem(Item item, String s, int n, int n2) {
        ItemStack itemStack = new ItemStack(item, n, n2);
        try {
            itemStack.setTagCompound(JsonToNBT.getTagFromJson(s));
        } catch (NBTException nBTException) {
        }
        return itemStack;
    }


}
