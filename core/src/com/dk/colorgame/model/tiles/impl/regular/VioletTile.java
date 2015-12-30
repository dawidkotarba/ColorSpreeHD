package com.dk.colorgame.model.tiles.impl.regular;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dk.colorgame.constants.Colors;
import com.dk.colorgame.enums.TileFeatureEnum;
import com.dk.colorgame.enums.TileTypesEnum;
import com.dk.colorgame.model.tiles.AbstractTile;
import com.dk.colorgame.resources.Textures;

/**
 * Created by Dawid Kotarba on 2015-05-01.
 */
public class VioletTile extends AbstractTile {
    public VioletTile(SpriteBatch spriteBatch) {
        super(spriteBatch, Textures.IMG_VIOLET_TILE, Textures.IMG_VIOLET_ACTIVE_TILE);
        setTileType(TileTypesEnum.VIOLET);
        setTileFeature(TileFeatureEnum.REGULAR);
        color = Colors.VIOLET;
    }
}
