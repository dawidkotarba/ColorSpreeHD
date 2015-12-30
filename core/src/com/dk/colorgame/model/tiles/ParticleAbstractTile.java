package com.dk.colorgame.model.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dk.colorgame.Game;
import com.dk.colorgame.constants.Constants;
import com.dk.colorgame.constants.Options;
import com.dk.colorgame.resources.Particles;
import com.dk.colorgame.service.effects.particles.GameParticleEffect;

import java.util.Iterator;

/**
 * Created by Dawid Kotarba on 2015-06-16.
 */
public class ParticleAbstractTile extends AbstractTile {

    private ParticleEffect particleEffect;
    private boolean shallDrawParticle;
    private final String particleActive;
    private final String particleDie;

    protected ParticleAbstractTile(SpriteBatch spriteBatch, Animation animation, String activeTextureResource, String particleActive, String particleDie) {
        super(spriteBatch, animation, activeTextureResource);
        this.particleActive = particleActive;
        this.particleDie = particleDie;
        initParticles();
    }

    private void initParticles() {
        particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal(particleActive), Gdx.files.internal(Particles.FOLDER_NAME));
    }

    @Override
    public void draw() {
        super.draw();

        if (shallDrawParticle && Options.particleEffects) {
            drawParticleEffect(position.x + Constants.PARTICLE_X_OFFSET, position.y + Constants.PARTICLE_Y_OFFSET);
        } else {
            particleEffect.reset();
        }
    }

    @Override
    public void activate() {
        super.activate();
        shallDrawParticle = true;
    }

    @Override
    public void deactivate() {
        super.deactivate();
        shallDrawParticle = false;
    }

    private void drawParticleEffect(float screenX, float screenY) {
        Array<ParticleEmitter> emitters = particleEffect.getEmitters();

        Iterator<ParticleEmitter> emitterIt = emitters.iterator();

        while (emitterIt.hasNext()) {
            emitterIt.next().setPosition(screenX, screenY);
        }

        particleEffect.update(Gdx.graphics.getDeltaTime());
        particleEffect.draw(spriteBatch);

        if (particleEffect.isComplete()) {
            shallDrawParticle = false;
        }
    }

    @Override
    public void die() {
        super.die();

        if (Options.particleEffects) {
            GameParticleEffect gameParticleEffect = new GameParticleEffect(particleDie, new Vector2(position.x + Constants.PARTICLE_X_OFFSET, position.y + Constants.PARTICLE_Y_OFFSET));
            gameParticleEffect.setLightEffectScale(2);
            Game.getForegroundParticleSpawner().addEffect(gameParticleEffect);
        }

        particleEffect.dispose();
    }
}
