package com.dk.colorgame.view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Dawid Kotarba on 2015-07-25.
 */

public class ParticleEffectActor extends Actor {
    private ParticleEffect particleEffect;

    public ParticleEffectActor(ParticleEffect particleEffect) {
        super();
        this.particleEffect = particleEffect;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        particleEffect.getEmitters().first().setPosition(getX(), getY());
        particleEffect.update(Gdx.graphics.getDeltaTime());
        particleEffect.draw(batch);
    }
}