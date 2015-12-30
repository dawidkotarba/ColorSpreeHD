package com.dk.colorgame.model.tiles;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dk.colorgame.constants.GameConstants;
import com.dk.colorgame.constants.Strings;

/**
 * Created by Dawid Kotarba on 2015-07-26.
 */
public class ExtraTile extends ParticleAbstractTile {

    protected ExtraTile(SpriteBatch spriteBatch, Animation animation, String activeTextureResource, String particleActive, String particleDie) {
        super(spriteBatch, animation, activeTextureResource, particleActive, particleDie);
    }

    @Override
    public int getPointsMultiplier() {
        return GameConstants.GAME_SCORE_MULTIPLIER_EXTRA;
    }

    @Override
    public String getTooltip() {
        return Strings.TOOLTIP_POINTS_X + GameConstants.GAME_SCORE_MULTIPLIER_EXTRA;
    }
}
