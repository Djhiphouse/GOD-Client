package me.bratwurst;

import java.util.Timer;
import java.util.TimerTask;

import me.bratwurst.module.modules.combat.Aura;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.multiplayer.ServerData;

public class DiscordRP {

	private static final Minecraft mc = Minecraft.getMinecraft();

	private static final DiscordRP INSTANCE = new DiscordRP();

	public static final DiscordRP getInstance() {
		return INSTANCE;
	}

	private boolean running = true;
	private long created = 0;

	public void startup() {

		this.created = System.currentTimeMillis();

		DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {

			@Override
			public void apply(DiscordUser user) {
				
				new Timer().schedule(new TimerTask() {
					public void run() {

						ServerData serverData = mc.getCurrentServerData();
						if (serverData == null || mc.currentScreen instanceof GuiMainMenu) {
							update("Name: " + mc.getSession().getUsername(), "Module: " + Client.getInstance().getModuleManager().getEnabledModules().size() + " / " + Client.getInstance().getModuleManager().modules.size());
						}

						else {
							update("Name: " + mc.getSession().getUsername(), "IP: " + mc.getCurrentServerData().serverIP);
						}

					}
				}, 0, 2000L);
			}

		}).build();

		DiscordRPC.discordInitialize("852954950962380900", handlers, true);

		new Thread("Discord RPC Callback") {

			@Override
			public void run() {

				while (running) {
					DiscordRPC.discordRunCallbacks();
				}
			}
		}.start();
	}

	public void shutdown() {
		running = false;
		DiscordRPC.discordShutdown();
	}

	public void update(String firstline, String secondline) {
		DiscordRichPresence.Builder builder = new DiscordRichPresence.Builder(secondline);
		builder.setDetails(firstline);
		builder.setStartTimestamps(created);
		builder.setBigImage("1024", Client.getInstance().getCLIENT_NAME() + " " + Client.getInstance().getCLIENT_VERSION());
		builder.setSmallImage("512", "Made by " + Client.getInstance().getCLIENT_CODER());

		DiscordRPC.discordUpdatePresence(builder.build());
	}
}
