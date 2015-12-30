package com.dk.colorgame.model.tiles.impl.bonus;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dk.colorgame.constants.Colors;
import com.dk.colorgame.constants.GameConstants;
import com.dk.colorgame.constants.Strings;
import com.dk.colorgame.enums.TileFeatureEnum;
import com.dk.colorgame.enums.TileTypesEnum;
import com.dk.colorgame.model.tiles.ParticleAbstractTile;
import com.dk.colorgame.resources.Animations;
import com.dk.colorgame.resources.Particles;
import com.dk.colorgame.resources.Sounds;
import com.dk.colorgame.resources.Textures;
import com.dk.colorgame.resources.loaders.AssetLoader;
import com.dk.colorgame.utils.GraphicUtils;

/**
 * Created by Dawid Kotarba on 2015-05-23.
 */
public class StarTile extends ParticleAbstractTile {

    public StarTile(SpriteBatch spriteBatch) {
        super(spriteBatch, GraphicUtils.loadAnimation(Animations.ANIMATION_TILE_STAR, 1 / 20f), Textures.IMG_STAR_ACTIVE_TILE, Particles.PARTICLE_STARS_SMALL, Particles.PARTICLE_STARS);
        setTileType(TileTypesEnum.STAR);
        setTileFeature(TileFeatureEnum.BONUS);
        color = Colors.GRAY;
        setDieSound((Sound) AssetLoader.get(Sounds.SOUND_STAR, Sound.class));
    }

    @Override
    public int getPointsMultiplier() {
        return GameConstants.GAME_SCORE_MULTIPLIER_STAR;
    }

    @Override
    public String getTooltip() {
        return Strings.TOOLTIP_POINTS_X + GameConstants.GAME_SCORE_MULTIPLIER_STAR;
    }
}
