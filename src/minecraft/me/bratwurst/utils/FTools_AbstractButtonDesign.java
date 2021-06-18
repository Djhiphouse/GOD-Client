package me.bratwurst.utils;


import net.minecraft.client.gui.GuiButton;

public abstract class FTools_AbstractButtonDesign{

    private final String name;

    public abstract void renderButton(ButtonInfo var1);

    public FTools_AbstractButtonDesign( String name) {
        if (name == null) {
            throw new NullPointerException("name is marked non-null but is null");
        }
        this.name = name;
    }


    public String getName() {
        return this.name;
    }

    public static class ButtonInfo {
        private final int width;
        private final int height;
        private final int x;
        private final int y;
        private final String message;
        private final boolean hovered;
        private final boolean active;
        private final boolean visible;
        private final GuiButton guiButton;
        private final int mouseX;
        private final int mouseY;

        public ButtonInfo(int width, int height, int x, int y, String message, boolean hovered, boolean active, boolean visible, GuiButton guiButton, int mouseX, int mouseY) {
            this.width = width;
            this.height = height;
            this.x = x;
            this.y = y;
            this.message = message;
            this.hovered = hovered;
            this.active = active;
            this.visible = visible;
            this.guiButton = guiButton;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public String getMessage() {
            return this.message;
        }

        public boolean isHovered() {
            return this.hovered;
        }

        public boolean isActive() {
            return this.active;
        }

        public boolean isVisible() {
            return this.visible;
        }

        public GuiButton getGuiButton() {
            return this.guiButton;
        }

        public int getMouseX() {
            return this.mouseX;
        }

        public int getMouseY() {
            return this.mouseY;
        }
    }
}

