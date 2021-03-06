package com.dk.colorgame.model.tiles.impl.extra;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dk.colorgame.constants.Colors;
import com.dk.colorgame.enums.TileFeatureEnum;
import com.dk.colorgame.enums.TileTypesEnum;
import com.dk.colorgame.model.tiles.ExtraTile;
import com.dk.colorgame.resources.Animations;
import com.dk.colorgame.resources.Particles;
import com.dk.colorgame.resources.Textures;
import com.dk.colorgame.utils.GraphicUtils;

/**
 * Created by Dawid Kotarba on 2015-07-26.
 */
public class YellowExtraTile extends ExtraTile {
    public YellowExtraTile(SpriteBatch spriteBatch) {
        super(spriteBatch, GraphicUtils.loadAnimation(Animations.ANIMATION_TILE_YELLOW, 1 / 20f), Textures.IMG_YELLOW_ACTIVE_TILE, Particles.PARTICLE_YELLOW_SMALL, Particles.PARTICLE_YELLOW);
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        setTileType(TileTypesEnum.YELLOW);
        setTileFeature(TileFeatureEnum.EXTRA);
        color = Colors.YELLOW;
    }
}
