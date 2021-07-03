package me.bratwurst.utils;

import net.minecraft.item.ItemStack;

import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageHologram {

    public final ItemStack[] items;
    public int index = 0;

    public ImageHologram(double x, double y, double z, int size, Image image) {
        BufferedImage bufferedImage = convertImage(image, size);
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();
        items = new ItemStack[height];
        ColorManager.init();
        for(int imgy=0; imgy < height; imgy++) {
            String customname = "";
            for(int imgx=0; imgx < width; imgx++) {
                customname += ColorManager.getColor(new Color(bufferedImage.getRGB(imgx, imgy))) + "\u2588";//\u25A0
            }
            items[imgy] = Utils.createHologramm(customname, x, y + (0.2 * (height - imgy)), z);
        }
    }

    private BufferedImage convertImage(Image image, int size) {
        if(image.getWidth(null) == image.getHeight(null)) {
            return rescaleImage(image, size, size);
        } else if(image.getWidth(null) > image.getHeight(null)) {
            return rescaleImage(image, size, (int) (size / ((double) image.getWidth(null)) * image.getHeight(null)));
        } else {
            return rescaleImage(image, (int) (size / ((double) image.getHeight(null)) * image.getWidth(null)), size);
        }
    }

    public ItemStack next() {
        return items[index++];
    }

    public boolean hasNext() {
        return index < items.length;
    }

    private BufferedImage rescaleImage(Image image, int width, int height) {
        BufferedImage bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bimg.getGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        return bimg;
    }

	/*
	public static ItemStack getNextArmorStand() {
		if(index >= lines.length)
			return null;

		ItemStack item = Utils.createHologramm(text, x, y, z)
	}


	private static List<ArmorImage> images = new ArrayList<>();

	public static void removeAll() {
		for(ArmorImage ai : images) ai.remove();
	}

	public static List<ArmorImage> getAll() {
		return images;
	}

	public static ArmorImage getImage(String name) {
		for(ArmorImage ai : images) if(ai.getName().equals(name)) return ai;
		return null;
	}

	public static boolean isImageEntity(Entity entity) {
		for(ArmorImage ai : images) if(ai.containsEntity(entity)) return true;
		return false;
	}

	public static boolean spawn(String name, Location loc, Image image, int width, int height) {
		if(getImage(name) != null) return false;
		images.add(new ArmorImage(name, loc, image, width, height));
		return true;
	}

	private String name;
	private Location loc;

	private List<ArmorStand> armorstands;

	private ArmorImage(String name, Location loc, Image image, int width, int height) {
		loc = loc.clone();
		this.name = name;
		this.loc = loc;
		BufferedImage bimg = getScaledImage(image, width, height);

		armorstands = new ArrayList<>();

		for(int y=0; y < height; y++) {
			String customname = "";
			for(int x=0; x < width; x++) {
				Color color = new Color(bimg.getRGB(x, y));
				String c = ColorManager.getInstance().getColor(color);
				customname += c + "\u2588";//\u25A0
			}
			ArmorStand armorstand = loc.getWorld().spawn(loc.clone().add(0, 0.2 * (height - y), 0), ArmorStand.class);
			armorstand.setGravity(false);
			armorstand.setVisible(false);
			armorstand.setCustomNameVisible(true);
			armorstand.setCustomName(customname);
			armorstands.add(armorstand);
		}
	}

	public String getName() {
		return this.name;
	}

	public Location getLocation() {
		return this.loc;
	}

	public void remove() {
		for(ArmorStand armorstand : armorstands) armorstand.remove();
	}

	public boolean containsEntity(Entity entity) {
		return armorstands.contains(entity);
	}
	 */

}
