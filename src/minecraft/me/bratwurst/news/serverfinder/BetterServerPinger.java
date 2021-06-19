package me.bratwurst.news.serverfinder;

import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.OldServerPinger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterServerPinger {
    private static final AtomicInteger threadNumber = new AtomicInteger(0);
    public static final Logger logger = LogManager.getLogger();
    public ServerData server;
    private boolean done = false;
    private boolean failed = false;

    public void ping(String ip) {
        this.ping(ip, 25565);
    }

    public void ping(final String ip, final int port) {
        this.server = new ServerData("", ip + ":" + port, false);
        new Thread("" + threadNumber.incrementAndGet()){

            @Override
            public void run() {
                OldServerPinger pinger = new OldServerPinger();
                try {
                    logger.info("Pinging " + ip + ":" + port + "...");
                    pinger.ping(BetterServerPinger.this.server);
                    logger.info("Ping successful: " + ip + ":" + port);
                }
                catch (UnknownHostException e) {
                    logger.info("Unknown host: " + ip + ":" + port);
                    BetterServerPinger.this.failed = true;
                }
                catch (Exception e2) {
                    logger.info("Ping failed: " + ip + ":" + port);
                    BetterServerPinger.this.failed = true;
                }
                pinger.clearPendingNetworks();
                BetterServerPinger.this.done = true;
            }
        }.start();
    }

    public boolean isStillPinging() {
        return !this.done;
    }

    public boolean isWorking() {
        return !this.failed;
    }

    public boolean isOtherVersion() {
        return this.server.version != 47;
    }
}


