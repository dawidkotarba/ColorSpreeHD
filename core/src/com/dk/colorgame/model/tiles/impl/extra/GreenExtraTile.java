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
public class GreenExtraTile extends ExtraTile {
    public GreenExtraTile(SpriteBatch spriteBatch) {
        super(spriteBatch, GraphicUtils.loadAnimation(Animations.ANIMATION_TILE_GREEN, 1 / 20f), Textures.IMG_GREEN_ACTIVE_TILE, Particles.PARTICLE_GREEN_SMALL, Particles.PARTICLE_GREEN);
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        setTileType(TileTypesEnum.GREEN);
        setTileFeature(TileFeatureEnum.EXTRA);
        color = Colors.GREEN;
    }
}
