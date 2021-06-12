package me.bratwurst.manager.PartikelSystem;

import me.bratwurst.utils.Color;
import me.bratwurst.utils.MathUtil;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.nio.file.AtomicMoveNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class ParticleSystem {
    private static final float SPEED = 0.08f;
    private List<Particle> particleList = new ArrayList<>();

    private int dist;

    public ParticleSystem(int initAmount, int dist) {

        addParticles(initAmount);

        this.dist = dist;


    }

    public void addParticles(int amount) {
        for (int i = 0; i < amount; i++) {
            particleList.add(Particle.generateParticle());
        }
    }


    public void tick(int delta) {

        for (Particle particle : particleList) {
            particle.tick(delta, SPEED);
        }
    }

    public void render() {
        for (Particle particle : particleList) {
            GL11.glColor4f(0f, 210f, 189f, particle.getAlpha() / 255.0f);
            GL11.glPointSize(particle.getSize());
            GL11.glBegin(GL11.GL_POINTS);

            GL11.glVertex2f(particle.getX(), particle.getY());
            GL11.glEnd();


            float nearestDistance = 0;
            Particle nearestParticle = null;

            for (Particle particle1 : particleList) {
                float distance = particle.getDistanceTo(particle1);
                if (distance <= dist && (nearestDistance <= 0 || distance <= nearestDistance)) {

                    nearestDistance = distance;
                    nearestParticle = particle1;

                }
            }

            if (nearestParticle != null) {



                drawLine(particle.getX(),
                        particle.getY(),
                        nearestParticle.getX(),
                        nearestParticle.getY(),
                         0f,
                         213f,
                         190f);
            }

        }
    }


    private void drawLine(float x, float y, float x1, float y1, float r, float g, float b) {

        GL11.glColor3f(r, g, b );
        GL11.glLineWidth(1.1f);
        GL11.glBegin(GL11.GL_LINES);

        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x1, y1);
        GL11.glEnd();

    }

}