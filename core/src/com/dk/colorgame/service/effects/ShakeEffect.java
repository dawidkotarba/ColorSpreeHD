package com.dk.colorgame.service.effects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.dk.colorgame.constants.Options;
import com.dk.colorgame.utils.Utils;

import java.util.Random;

public class ShakeEffect {
    private float[] samples;
    private Random rand;
    private float internalTimer = 0;
    private float shakeDuration = 0;

    private int duration = 5;
    private int frequency; // = 35;
    private float amplitude; // = 20;
    private boolean falloff = true;

    private int sampleCount;

    public ShakeEffect(int frequency, float amplitude) {
        this.frequency = frequency;
        this.amplitude = amplitude;
        sampleCount = duration * frequency;
        rand = Utils.getRandom();

        samples = new float[sampleCount];
        for (int i = 0; i < sampleCount; i++) {
            samples[i] = rand.nextFloat() * 2f - 1f;
        }
    }

    public void update(float dt, Camera camera, Vector2 center) {

        if (!Options.shakeEffect) {
            return;
        }

        internalTimer += dt;
        if (internalTimer > duration) internalTimer -= duration;
        if (shakeDuration > 0) {
            shakeDuration -= dt;
            float shakeTime = (internalTimer * frequency);
            int first = (int) shakeTime;
            int second = (first + 1) % sampleCount;
            int third = (first + 2) % sampleCount;
            float deltaT = shakeTime - (int) shakeTime;
            float deltaX = samples[first] * deltaT + samples[second] * (1f - deltaT);
            float deltaY = samples[second] * deltaT + samples[third] * (1f - deltaT);

            camera.position.x = center.x + deltaX * amplitude * (falloff ? Math.min(shakeDuration, 1f) : 1f);
            camera.position.y = center.y + deltaY * amplitude * (falloff ? Math.min(shakeDuration, 1f) : 1f);
            camera.update();
        }
    }

    public void shake(float duration) {
        if (!Options.shakeEffect) {
            return;
        }

        shakeDuration = duration;
    }
}