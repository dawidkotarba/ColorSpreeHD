package com.dk.colorgame.model.tiles.impl.regular;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dk.colorgame.constants.Colors;
import com.dk.colorgame.enums.TileFeatureEnum;
import com.dk.colorgame.enums.TileTypesEnum;
import com.dk.colorgame.model.tiles.AbstractTile;
import com.dk.colorgame.resources.Textures;

/**
 * Created by Dawid Kotarba on 2015-04-28.
 */
public class GreenTile extends AbstractTile {
    public GreenTile(SpriteBatch spriteBatch) {
        super(spriteBatch, Textures.IMG_GREEN_TILE, Textures.IMG_GREEN_ACTIVE_TILE);
        setTileType(TileTypesEnum.GREEN);
        setTileFeature(TileFeatureEnum.REGULAR);
        color = Colors.GREEN;
    }
}