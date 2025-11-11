package PaooGame.Animations;

import PaooGame.CustomExceptions.EmptyAnimationException;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    private ArrayList<BufferedImage> frames;//aici sunt puse imaginile din animatie
    private int currentFrame;
    private int frameDelay;  // cate tick-uri sa stea inainte de shimbarea imaginii in animatie
    private int frameCount;  // aici numara cate tick-uri au trecut de la ultima schimbare de imagine
    private boolean playedOnce; /*determina cand s-a terminat un ciclu de animatie
    (se va folosi in continuare la diferite animatii ce trebuie realizate o singura data)*/
    public Animation() {
        frames = new ArrayList<BufferedImage>();
        playedOnce = false;
        frameCount = 0;
        currentFrame = 0;
        frameDelay = 0;
    }

    /// Procesul de "Animatie" in sine(aici updateaza imaginea la fiecare update)
    public void tick() {
        if (frameDelay <= 0) return; // Nicio animatie daca delay-ul e negativ

        frameCount++;

        if (frameCount > frameDelay) {
            currentFrame++;
            frameCount = 0;
        }

        if (currentFrame >= frames.size()) {
            currentFrame = 0;
            playedOnce = true;
        }
    }

    public void addFrame(BufferedImage frame) {
        frames.add(frame);
    }

    public BufferedImage getCurrentFrame() {
        if (frames.isEmpty()) {
            throw new EmptyAnimationException("Animation has no frames configured");
        }
        return frames.get(currentFrame);
    }

    public void setFrameDelay(int ticks) {
        this.frameDelay = ticks;
    }

    public boolean hasPlayedOnce() {
        return playedOnce;
    }

    public void reset() {
        currentFrame = 0;
        frameCount = 0;
        playedOnce = false;
    }
}