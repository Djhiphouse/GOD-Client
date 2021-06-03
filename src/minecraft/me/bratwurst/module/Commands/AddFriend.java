package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.manager.FreundManager;
import me.bratwurst.utils.player.PlayerUtils;

import java.util.Arrays;
import java.util.HashSet;

public class AddFriend extends Command {
    private final HashSet<String> hashSet = new HashSet<>();
    private static final AddFriend instance = new AddFriend();
    public AddFriend() {
        super("Freund", "Freund", "Freund");
    }

    @Override
    public void onCommand(String command, String[] args) {
        System.out.println(Arrays.toString(args));
        System.out.println("Args0: " + args[0]);

        System.out.println(Arrays.toString(args));
        System.out.println("Args0: " + args[0]);

        if (args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "add":
<<<<<<< HEAD

=======
>>>>>>> parent of edbe718d (Updates)
                    PlayerUtils.sendMessage("Du hast " + args[1] + " zu deinen Freunden hinzugefügt!");
                    FreundManager.getInstance().addFriend("§a[§bGod§cOwner§a]§7 " + args[1]);
                    FreundManager.getInstance().addFriend("§a[§bGod§cDev§a] §7"+ args[1]);
                    FreundManager.getInstance().addFriend("§b[§aGod§eSup§b] §7" + args[1]);
                    FreundManager.getInstance().addFriend("§b[§3GodPartner§b] §7" + args[1]);
                    FreundManager.getInstance().addFriend("§b[§eGodFreund§b] §7" + args[1]);
                    FreundManager.getInstance().addFriend("§b[§4GodUser§b] §7" + args[1]);
                    FreundManager.getInstance().addFriend(args[1]);
                    break;
                case "remove":
                    PlayerUtils.sendMessage("Du hast " + args[1] + "  von deinen Freunden entfernt!");
                    FreundManager.getInstance().removeFriend("§a[§bGod§cOwner§a] §7" + args[1]);
                    FreundManager.getInstance().removeFriend("§a[§bGod§cDev§a] §7"+ args[1]);
                    FreundManager.getInstance().removeFriend("§b[§aGod§eSup§b] §7" + args[1]);
                    FreundManager.getInstance().removeFriend("§b[§3GodPartner§b] §7" + args[1]);
                    FreundManager.getInstance().removeFriend("§b[§eGodFreund§b] §7" + args[1]);
                    FreundManager.getInstance().removeFriend("§b[§4GodUser§b] §7" + args[1]);
                    FreundManager.getInstance().removeFriend(args[1]);
<<<<<<< HEAD

                    for (Entity TargetPlayer : Minecraft.getMinecraft().theWorld.loadedEntityList) {
                        if (TargetPlayer instanceof EntityPlayer) {
                            if (TargetPlayer != null && TargetPlayer != mc.thePlayer ) {
                                if(TargetPlayer.getDistanceToEntity(mc.thePlayer) <= 5) {
                                    String Freund = TargetPlayer.getName();
                                    FreundManager.getInstance().addFriend(Freund);
                                    PlayerUtils.sendMessage("Du hast " + TargetPlayer.getName() + " Zu deinen freunden geadded!");
                                }else{
                                    PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Du musst in der nähe von dem Spieler sein (5 Blöcke)");
                                }
                            }
                        }
                    }
                    /*
                    Entity e = mc.theWorld.getPlayerEntityByName(args[1]);

                    if(e == null){
                        for(Entity p : mc.theWorld.getLoadedEntityList()){
                            if(p.getName().startsWith("§") && p.getName().endsWith("§7" + args[1])){
                                System.out.println(p.getName());
                                e = p;
                            }
                        }
                    }

 */
//                    if(e == null) {
//                        PlayerUtils.sendMessage("Dieser User ist nicht online");
//                        return;
//                    }else if (e instanceof EntityPlayer){
//                        FreundManager.getInstance().addFriend(e.getUniqueID().toString());
//                        PlayerUtils.sendMessage("Du hast " + e.getName() + " Zu deinen freunden geadded!");
//                    }else{
//                        PlayerUtils.sendMessage("Dieser User ist nicht online");
//                        System.out.println(e.getDisplayName());
//                    }


                    break;
                case "remove":
                    Entity er = mc.theWorld.getPlayerEntityByName(args[1]);
                    if(er == null){
                        PlayerUtils.sendMessage("Dieser User ist nicht online");
                        return;
                    }else if (er instanceof EntityPlayer){
                        PlayerUtils.sendMessage("Du hast " + er.getName() + "  von deinen Freunden entfernt!");
                        FreundManager.getInstance().removeFriend(er.getUniqueID().toString());
                    }else{
                        PlayerUtils.sendMessage("Dieser User ist nicht Online!");
                    }
=======
>>>>>>> parent of edbe718d (Updates)

                    break;
                default:
                    PlayerUtils.sendMessage("#addfriend [add/remove] [name]");
            }
        }

    }
}
