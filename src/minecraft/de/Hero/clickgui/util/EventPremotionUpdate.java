package de.Hero.clickgui.util;

public class EventPremotionUpdate {

        private float yaw;

        private float pitch;

        private boolean onGroand;

        private double posY;

        public EventPremotionUpdate(double posY, float yaw, float pitch, boolean onGroand) {
            this.yaw = yaw;
            this.pitch = pitch;
            this.onGroand = onGroand;
            this.posY = posY;
        }

        public float getYaw() {
            return this.yaw;
        }

        public void setYaw(float yaw) {
            this.yaw = yaw;
        }

        public float getPitch() {
            return this.pitch;
        }

        public void setPitch(float pitch) {
            this.pitch = pitch;
        }

        public boolean isOnGroand() {
            return this.onGroand;
        }

        public void setOnGroand(boolean onGroand) {
            this.onGroand = onGroand;
        }

        public double getPosY() {
            return this.posY;
        }

        public void setPosY(double posY) {
            this.posY = posY;
        }
    }


