package me.bratwurst.utils;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.ResourceLocation;

public class ServerDataFeaturedUtils extends ServerData {

    public static final ResourceLocation STAR_ICON = new ResourceLocation("textures/misc/star.png");

    public ServerDataFeaturedUtils(String serverName, String serverIP) {
        super(serverName, serverIP, false);
    }

}
