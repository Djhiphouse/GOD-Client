package me.bratwurst.module.modules.World;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.network.Packet;
import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;


import javax.vecmath.Vector3d;
import java.util.ArrayList;

public class VentusRadio extends Module {
    ArrayList<Packet> Packets = new ArrayList<>();
    ArrayList<Vector3d> loc = new ArrayList<>();
    private Vector3d startVector3d;
    public static Setting mode1;

    public VentusRadio() {
        super("Radio", Category.WORLD);
        ArrayList<String> options = new ArrayList<>();
        options.add("1Live");


        Client.setmgr.rSetting(mode1 = new Setting("Radio sender", this, "1Live", options));
    }

    @EventTarget

    public void onUpdate(EventMotionUpdate e) {
        if (mode1.getValString().equalsIgnoreCase("1Live")) {

        }
    }

    public void einslive(WebSocket webSocket, Response response) {
        webSocket.send("Hello, it's SSaurel !");
        webSocket.send("What's up ?");
        webSocket.send(ByteString.decodeHex("deadbeef"));

    }
}
