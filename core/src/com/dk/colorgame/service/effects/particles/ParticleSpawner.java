package com.dk.colorgame.service.effects.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.dk.colorgame.resources.loaders.AssetLoader;
import com.dk.colorgame.utils.GraphicUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Dawid Kotarba on 2015-06-14.
 */
public class ParticleSpawner {

    private final Set<GameParticleEffect> particleEffects = new HashSet<>();

    public void draw(SpriteBatch spriteBatch) {

        synchronized (particleEffects) {

            for (GameParticleEffect effect : particleEffects) {
                drawParticleEffect(effect, spriteBatch);
            }

            removeFinishedParticleEffects();
        }
    }

    public void addEffect(GameParticleEffect gameParticleEffect) {

        synchronized (particleEffects) {
            for (GameParticleEffect effect : particleEffects) {
                if (effect.getEffectName().equals(gameParticleEffect.getEffectName())) {
                    return;
                }
            }
        }

        ParticleEffect particleEffect = (ParticleEffect) AssetLoader.get(gameParticleEffect.getEffectName(), ParticleEffect.class);
        particleEffect.reset();
        gameParticleEffect.setParticleEffect(particleEffect);

        synchronized (particleEffects) {
            particleEffects.add(gameParticleEffect);
        }
    }

    private void drawParticleEffect(GameParticleEffect gameParticleEffect, SpriteBatch spriteBatch) {
        ParticleEffect particleEffect = gameParticleEffect.getParticleEffect();
        Array<ParticleEmitter> emitters = particleEffect.getEmitters();
        Iterator<ParticleEmitter> emitterIt = emitters.iterator();

        while (emitterIt.hasNext()) {
            ParticleEmitter emitter = emitterIt.next();

            emitter.setPosition(gameParticleEffect.getPosition().x, gameParticleEffect.getPosition().y);

            if (gameParticleEffect.getLightEffectScale() > 0) {
                float lightIntensity = 1 - emitter.getPercentComplete();
                GraphicUtils.renderLight(spriteBatch, gameParticleEffect.getPosition().x - 130, gameParticleEffect.getPosition().y - 130, lightIntensity, false, gameParticleEffect.getLightEffectScale());
            }
        }

        particleEffect.update(Gdx.graphics.getDeltaTime());
        particleEffect.draw(spriteBatch);
    }

    private void removeFinishedParticleEffects() {
        synchronized (particleEffects) {

            List<GameParticleEffect> effectsToRemove = new ArrayList<>();

            for (GameParticleEffect gameParticleEffect : particleEffects) {
                if (gameParticleEffect.getParticleEffect().isComplete()) {
                    effectsToRemove.add(gameParticleEffect);
                }
            }

            particleEffects.removeAll(effectsToRemove);
        }
    }

    public void dispose() {
        synchronized (particleEffects) {
            for (GameParticleEffect gameParticleEffect : particleEffects) {
                gameParticleEffect.getParticleEffect().dispose();
            }
            particleEffects.clear();
        }
    }
}
