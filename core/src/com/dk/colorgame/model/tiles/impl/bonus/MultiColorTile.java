package com.dk.colorgame.model.tiles.impl.bonus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dk.colorgame.constants.Colors;
import com.dk.colorgame.constants.Strings;
import com.dk.colorgame.enums.TileFeatureEnum;
import com.dk.colorgame.enums.TileTypesEnum;
import com.dk.colorgame.model.tiles.ParticleAbstractTile;
import com.dk.colorgame.resources.Animations;
import com.dk.colorgame.resources.Particles;
import com.dk.colorgame.resources.Textures;
import com.dk.colorgame.utils.GraphicUtils;

/**
 * Created by Dawid Kotarba on 2015-05-23.
 */
public class MultiColorTile extends ParticleAbstractTile {

    public MultiColorTile(SpriteBatch spriteBatch) {
        super(spriteBatch, GraphicUtils.loadAnimation(Animations.ANIMATION_TILE_MULTICOLOR, 1 / 20f), Textures.IMG_MULTICOLOR_ACTIVE_TILE, Particles.PARTICLE_MULTICOLOR_SMALL, Particles.PARTICLE_MULTICOLOR);
        setTileType(TileTypesEnum.MULTICOLOR);
        setTileFeature(TileFeatureEnum.BONUS);
        color = Colors.GRAY;
    }

    @Override
    public String getTooltip() {
        return Strings.TOOLTIP_MULTICOLOR_TILE;
    }
}
