package me.bratwurst.module.modules.Crasher;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.MainUtil;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S38PacketPlayerListItem;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class AntiVanish extends Module {
    EntityPlayer en;
    private ArrayList<UUID> vanished;
    public AntiVanish() {
        super("AntiVanish", Category.EXPLOIT);
    }


    @EventTarget
    public void ProcessPacketEvent(ProcessPacketEvent e) {
        if (this.mc.theWorld != null && e.getPacket() instanceof S38PacketPlayerListItem) {
            final S38PacketPlayerListItem listItem = (S38PacketPlayerListItem)e.getPacket();
            if (listItem.func_179768_b() == S38PacketPlayerListItem.Action.UPDATE_LATENCY) {
                for (final Object o : listItem.func_179767_a()) {
                    final S38PacketPlayerListItem.AddPlayerData data = (S38PacketPlayerListItem.AddPlayerData)o;
                    if (this.mc.getNetHandler().getPlayerInfo(data.getProfile().getId()) == null && !this.checkList(data.getProfile().getId())) {
                        final String name = this.getName(en,data.getProfile().getId());
                        if (!Objects.isNull(name)) {
                            MainUtil.SendClientMesage("�cDer Spieler �e" + name + " �chat sich gevanished.");
                        }
                        else {
                            MainUtil.SendClientMesage("�cEin Spieler hat sich gevanished.");
                        }
                    }
                }
            }
        }
      }

 @EventTarget
    public void preTick(EntityPlayer e) {
        try {
            if (!this.mc.isSingleplayer()) {
                for (final UUID uuid : this.vanished) {
                    if (this.mc.getNetHandler().getPlayerInfo(uuid) != null) {
                        if (Objects.isNull(this.getName(e,uuid))) {
                            PlayerUtils.sendMessage("�aDer Spieler �e" + this.getName(e,uuid) + " �ahat sich entvanished.");
                        }
                        else {
                            PlayerUtils.sendMessage("�aEin Spieler hat sich entvanished.");
                        }
                        this.vanished.remove(uuid);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }

    public String getName(EntityPlayer e,UUID uuid) {
        return e.getName();
    }
 @EventTarget
    private boolean checkList(final UUID uuid) {
        if (this.vanished.contains(uuid)) {
            this.vanished.remove(uuid);
            return true;
        }
        this.vanished.add(uuid);
        return false;
    }

}
