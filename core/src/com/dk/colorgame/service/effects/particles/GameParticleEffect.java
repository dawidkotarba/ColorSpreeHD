package com.dk.colorgame.service.effects.particles;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dawid Kotarba on 31.10.2015.
 */
public class GameParticleEffect {

    private String effectName;
    private ParticleEffect particleEffect = new ParticleEffect();
    private Vector2 position = new Vector2();
    private float lightEffectScale;

    public GameParticleEffect(String effectName) {
        this.effectName = effectName;
    }

    public GameParticleEffect(String effectName, Vector2 position) {
        this.effectName = effectName;
        this.position = position;
    }

    public String getEffectName() {
        return effectName;
    }

    public ParticleEffect getParticleEffect() {
        return particleEffect;
    }

    public void setParticleEffect(ParticleEffect particleEffect) {
        this.particleEffect = particleEffect;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getLightEffectScale() {
        return lightEffectScale;
    }

    public void setLightEffectScale(float lightEffectScale) {
        this.lightEffectScale = lightEffectScale;
    }
}
