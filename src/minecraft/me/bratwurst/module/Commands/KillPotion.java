package me.bratwurst.module.Commands;

import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.PlayerUtil;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;

public class KillPotion extends Command {
    public KillPotion() {
        super("KillPotion", "KillPotion", "Killpotion: Fuck die owner ab");
    }
    @Override
    public void onCommand(String command, String[] args) {
        this.mc.getNetHandler().addToSendQueue((Packet)new C10PacketCreativeInventoryAction(36, Client.createPotionItem("{HideFlags:63,display:{Name:\"\"},CustomPotionEffects:[{Id:6,Amplifier:125,Duration:40000}]}")));
        PlayerUtils.sendMessage("wurde erfolgreich erstellt!");
    }
    }



