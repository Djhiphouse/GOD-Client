package me.bratwurst.module.modules.Player;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.ContainerUtil;
import me.bratwurst.utils.ItemUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.*;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class InventoryManager extends Module {

    public Setting dropDelayProperty, equipDelayProperty;

    private TimeHelper dropStopwatch = new TimeHelper();
    private TimeHelper equipStopwatch = new TimeHelper();
    public boolean cleaning;
    public boolean equipping;
    private boolean guiOpenedByMod;
    private final ArrayList<me.bratwurst.utils.Action> clickQueue;
    private boolean openedinv = true;

    public InventoryManager() {
        super("InventoryManager", Keyboard.KEY_NONE, Category.PLAYER);
        Client.instance.setmgr.rSetting(dropDelayProperty = new Setting("Cleaning Delay", this, 150, 0, 250, false));
        Client.instance.setmgr.rSetting(equipDelayProperty = new Setting("Equip Delay", this, 150, 0, 250, false));
        clickQueue = new ArrayList<>();
    }

    @Override
    public void onDisable() {
        openedinv = false;
        cleaning = false;
        equipping = false;
        guiOpenedByMod = false;
        clickQueue.clear();
        super.onDisable();
    }

    @Override
    public void onEnable() {
        openedinv = true;
        cleaning = false;
        equipping = false;
        guiOpenedByMod = false;
        clickQueue.clear();
        super.onEnable();
    }

    @EventTarget
    public void onPlayerUpdate(EventMotionUpdate event) {

        if (true && !(Minecraft.getMinecraft().currentScreen instanceof GuiInventory)
                && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative))
            return;

        if (!clickQueue.isEmpty()) {
            clickQueue.get(0).execute();
            clickQueue.remove(clickQueue.get(0));
        } else {
            if (!switchSwordSlot()) {
                boolean swappingSword = false;
                if (!equipArmor()) {
                    equipping = false;
                    if (!clean()) {
                        cleaning = false;
                    }
                }
            }
        }

        if (guiOpenedByMod && !cleaning && !equipping) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            guiOpenedByMod = false;
            for (KeyBinding keyBinding : new KeyBinding[] { Minecraft.getMinecraft().gameSettings.keyBindForward,
                    Minecraft.getMinecraft().gameSettings.keyBindBack,
                    Minecraft.getMinecraft().gameSettings.keyBindLeft,
                    Minecraft.getMinecraft().gameSettings.keyBindRight,
                    Minecraft.getMinecraft().gameSettings.keyBindJump,
                    Minecraft.getMinecraft().gameSettings.keyBindSprint }) {
                KeyBinding.setKeyBindState(keyBinding.getKeyCode(), Keyboard.isKeyDown(keyBinding.getKeyCode()));
            }
        }
    }

    private boolean switchSwordSlot() {
        if (true) {
            for (int i = 9; i < 45; i++) {
                if (i == 35 + 1)
                    continue;

                if (!Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i).getHasStack()
                        || !(Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i).getStack()
                        .getItem() instanceof ItemSword))
                    continue;

                ItemStack stackInSlot = Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i).getStack();

                if (!Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(35 + 1).getHasStack()) {
                    int finalI1 = i;
                    if (!openedinv) {
                        mc.thePlayer.sendQueue.addToSendQueue(
                                new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.OPEN_INVENTORY));
                    }
                    clickQueue.add(() -> Minecraft.getMinecraft().playerController.windowClick(
                            Minecraft.getMinecraft().thePlayer.inventoryContainer.windowId, finalI1, 0, 0,
                            Minecraft.getMinecraft().thePlayer));
                    clickQueue.add(() -> Minecraft.getMinecraft().playerController.windowClick(
                            Minecraft.getMinecraft().thePlayer.inventoryContainer.windowId, 35 + 1, 0, 0,
                            Minecraft.getMinecraft().thePlayer));

                    if (openedinv) {
                        mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(0));
                    }
                    return true;
                } else {
                    ItemStack stackInWantedSlot = Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(35 + 1)
                            .getStack();
                    if (ItemUtil.compareDamage(stackInSlot, stackInWantedSlot) == stackInSlot) {
                        int finalI = i;
                        mc.thePlayer.sendQueue.addToSendQueue(
                                new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.OPEN_INVENTORY));

                        clickQueue.add(() -> Minecraft.getMinecraft().playerController.windowClick(
                                Minecraft.getMinecraft().thePlayer.inventoryContainer.windowId, finalI, 0, 0,
                                Minecraft.getMinecraft().thePlayer));
                        clickQueue.add(() -> Minecraft.getMinecraft().playerController.windowClick(
                                Minecraft.getMinecraft().thePlayer.inventoryContainer.windowId, 35 + 1, 0, 0,
                                Minecraft.getMinecraft().thePlayer));
                        clickQueue.add(() -> Minecraft.getMinecraft().playerController.windowClick(
                                Minecraft.getMinecraft().thePlayer.inventoryContainer.windowId, finalI, 0, 0,
                                Minecraft.getMinecraft().thePlayer));

                        mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(0));

                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean equipArmor() {
        if (true) {
            for (int i = 9; i < 45; i++) {
                if (!Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i).getHasStack())
                    continue;

                ItemStack stackInSlot = Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i).getStack();

                if (!(stackInSlot.getItem() instanceof ItemArmor))
                    continue;

                if (ContainerUtil.getArmorItemsEquipSlot(stackInSlot, false) == -1)
                    continue;

                if (Minecraft.getMinecraft().thePlayer
                        .getEquipmentInSlot(ContainerUtil.getArmorItemsEquipSlot(stackInSlot, true)) == null) {
                    System.out.println("No stack in slot : " + stackInSlot.getUnlocalizedName());
                    if (equipStopwatch.hasReached(equipDelayProperty.getValInt())) {
                        int finalI = i;
                        mc.thePlayer.sendQueue.addToSendQueue(
                                new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.OPEN_INVENTORY));
                        clickQueue.add(() -> Minecraft.getMinecraft().playerController.windowClick(
                                Minecraft.getMinecraft().thePlayer.inventoryContainer.windowId, finalI, 0, 0,
                                Minecraft.getMinecraft().thePlayer));
                        clickQueue.add(() -> Minecraft.getMinecraft().playerController.windowClick(
                                Minecraft.getMinecraft().thePlayer.inventoryContainer.windowId,
                                ContainerUtil.getArmorItemsEquipSlot(stackInSlot, false), 0, 0,
                                Minecraft.getMinecraft().thePlayer));

                        mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(0));

                        return true;
                    }
                } else {
                    ItemStack stackInEquipmentSlot = Minecraft.getMinecraft().thePlayer
                            .getEquipmentInSlot(ContainerUtil.getArmorItemsEquipSlot(stackInSlot, true));
                    if (ItemUtil.compareProtection(stackInSlot, stackInEquipmentSlot) == stackInSlot) {
                        System.out.println("Stack in slot : " + stackInSlot.getUnlocalizedName());
                        if (equipStopwatch.hasReached(equipDelayProperty.getValInt())) {
                            int finalI1 = i;
                            mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer,
                                    C0BPacketEntityAction.Action.OPEN_INVENTORY));
                            clickQueue.add(() -> Minecraft.getMinecraft().playerController.windowClick(
                                    Minecraft.getMinecraft().thePlayer.inventoryContainer.windowId, finalI1, 0, 0,
                                    Minecraft.getMinecraft().thePlayer));
                            clickQueue.add(() -> Minecraft.getMinecraft().playerController.windowClick(
                                    Minecraft.getMinecraft().thePlayer.inventoryContainer.windowId,
                                    ContainerUtil.getArmorItemsEquipSlot(stackInSlot, false), 0, 0,
                                    Minecraft.getMinecraft().thePlayer));
                            clickQueue.add(() -> Minecraft.getMinecraft().playerController.windowClick(
                                    Minecraft.getMinecraft().thePlayer.inventoryContainer.windowId, finalI1, 0, 0,
                                    Minecraft.getMinecraft().thePlayer));

                            mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(0));

                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean clean() {
        if (true) {
            if (Minecraft.getMinecraft().thePlayer == null)
                return false;

            ArrayList<Integer> uselessItem = new ArrayList<Integer>();
            for (int i = 0; i < 45; i++) {

                if (!Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i).getHasStack())
                    continue;

                ItemStack stackInSlot = Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i).getStack();

                if (Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(0) == stackInSlot
                        || Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(1) == stackInSlot
                        || Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(2) == stackInSlot
                        || Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(3) == stackInSlot)
                    continue;

                if (isGarbo(i))
                    uselessItem.add(i);

            }

            if (uselessItem.size() > 0) {
                cleaning = true;
                if (dropStopwatch.hasReached(dropDelayProperty.getValInt())) {
                    if (!(mc.thePlayer.inventory.currentItem == uselessItem.get(0))) {

                        mc.thePlayer.sendQueue.addToSendQueue(
                                new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.OPEN_INVENTORY));

                        Minecraft.getMinecraft().playerController.windowClick(
                                Minecraft.getMinecraft().thePlayer.inventoryContainer.windowId, uselessItem.get(0), 1,
                                4, Minecraft.getMinecraft().thePlayer);

                        mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(0));

                    }
                    uselessItem.remove(0);
                    dropStopwatch.reset();
                }
                return true;
            }
        }
        return false;
    }

    private boolean isGarbo(int slot) {
        ItemStack stackInSlot = Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(slot).getStack();
        if (stackInSlot.getItem() instanceof ItemSword) {
            for (int i = 0; i < 44; i++) {
                if (i == slot)
                    continue;
                if (Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                    ItemStack stackAtIndex = Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i)
                            .getStack();
                    if (stackAtIndex.getItem() instanceof ItemSword) {
                        if (ItemUtil.compareDamage(stackInSlot, stackAtIndex) == stackAtIndex) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        if (stackInSlot.getItem() instanceof ItemAxe || stackInSlot.getItem() instanceof ItemBow
                || stackInSlot.getItem() instanceof ItemFishingRod || stackInSlot.getItem() instanceof ItemPickaxe
                || Item.getIdFromItem(stackInSlot.getItem()) == 346) {
            for (int i = 44; i > 0; i--) {
                if (i == slot)
                    continue;
                if (Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                    ItemStack stackAtIndex = Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i)
                            .getStack();
                    if ((stackAtIndex.getItem() instanceof ItemSword || stackAtIndex.getItem() instanceof ItemAxe
                            || stackAtIndex.getItem() instanceof ItemBow
                            || stackAtIndex.getItem() instanceof ItemFishingRod
                            || stackAtIndex.getItem() instanceof ItemAxe
                            || stackAtIndex.getItem() instanceof ItemPickaxe
                            || Item.getIdFromItem(stackAtIndex.getItem()) == 346)) {
                        if (Item.getIdFromItem(stackAtIndex.getItem()) == Item.getIdFromItem(stackInSlot.getItem())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        if (stackInSlot.hasDisplayName())
            return false;

        if (isBestArmorItem(stackInSlot))
            return false;

        if (stackInSlot.getItem() instanceof ItemFood)
            return false;
        if (stackInSlot.getItem() instanceof ItemEnderPearl)
            return false;
        if (stackInSlot.getItem() instanceof ItemBlock)
            return false;

        if (stackInSlot.getItem() instanceof ItemPotion) {
            return ItemUtil.isPotionNegative(stackInSlot);
        }

        if (stackInSlot.getItem() instanceof ItemTool) {
            return true;
        }

        if (Item.getIdFromItem(stackInSlot.getItem()) == 367)
            return true; // rotten flesh
        if (Item.getIdFromItem(stackInSlot.getItem()) == 259)
            return true; // flint & steel
        if (Item.getIdFromItem(stackInSlot.getItem()) == 262)
            return true; // arrow
        if (Item.getIdFromItem(stackInSlot.getItem()) == 264)
            return true; // diamond
        if (Item.getIdFromItem(stackInSlot.getItem()) == 265)
            return true; // iron
        if (Item.getIdFromItem(stackInSlot.getItem()) == 336)
            return true; // brick
        if (Item.getIdFromItem(stackInSlot.getItem()) == 266)
            return true; // gold ingot
        if (Item.getIdFromItem(stackInSlot.getItem()) == 345)
            return true; // compass
        if (Item.getIdFromItem(stackInSlot.getItem()) == 46)
            return true; // tnt
        if (Item.getIdFromItem(stackInSlot.getItem()) == 261)
            return true; // bow
        if (Item.getIdFromItem(stackInSlot.getItem()) == 262)
            return true; // arrow
        if (Item.getIdFromItem(stackInSlot.getItem()) == 116)
            return true; // enchanting table
        if (Item.getIdFromItem(stackInSlot.getItem()) == 54)
            return true;

        return true;
    }

    private boolean isBestTool(ItemStack itemStack) {
        return false;
    }

    private boolean isBestArmorItem(ItemStack armorStack) {
        if (armorStack.getItem() instanceof ItemArmor) {
            int equipSlot = ContainerUtil.getArmorItemsEquipSlot(armorStack, true);

            if (equipSlot == -1)
                return false;

            if (mc.thePlayer.getEquipmentInSlot(equipSlot) == null) {
                for (int slotNum = 44; slotNum > 0; slotNum--) {
                    if (!mc.thePlayer.inventoryContainer.getSlot(slotNum).getHasStack())
                        continue;

                    ItemStack stackInSlot = mc.thePlayer.inventoryContainer.getSlot(slotNum).getStack();

                    if (!(stackInSlot.getItem() instanceof ItemArmor))
                        continue;

                    if (ContainerUtil.getArmorItemsEquipSlot(stackInSlot, true) == equipSlot
                            && compareArmorItems(armorStack, stackInSlot) == stackInSlot)
                        return false;
                }
            } else {
                if (compareArmorItems(armorStack, mc.thePlayer.getEquipmentInSlot(equipSlot)) == mc.thePlayer
                        .getEquipmentInSlot(equipSlot))
                    return false;
            }

            return true;
        }
        return false;
    }

    private ItemStack compareArmorItems(ItemStack item1, ItemStack item2) {
        if (item1 == null || item2 == null)
            return null;

        if (!(item1.getItem() instanceof ItemArmor && item2.getItem() instanceof ItemArmor))
            return null;

        if (ContainerUtil.getArmorItemsEquipSlot(item1, true) != ContainerUtil.getArmorItemsEquipSlot(item2, true))
            return null;

        double item1Protection = ItemUtil.getArmorProtection(item1);
        double item2Protection = ItemUtil.getArmorProtection(item2);

        if (item1Protection != item2Protection) {
            if (item1Protection > item2Protection)
                return item1;
            else
                return item2;
        } else {
            if (item1.getMaxDamage() > item2.getMaxDamage())
                return item2;
            else
                return item1;
        }
    }

}
