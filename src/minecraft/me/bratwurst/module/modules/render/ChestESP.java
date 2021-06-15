package me.bratwurst.module.modules.render;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;

import java.awt.*;

public class ChestESP extends Module {
    public ChestESP() {
        super("ChestESP", Category.RENDER);
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
                    for (TileEntity tileEntity : mc.theWorld.loadedTileEntityList) {


                if (!(tileEntity instanceof TileEntityChest))
                    continue;

                Color chestColor = new Color(-1);


            }
        }
    }
