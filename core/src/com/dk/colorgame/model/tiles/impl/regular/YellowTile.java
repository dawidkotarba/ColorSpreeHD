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
public class YellowTile extends AbstractTile {
    public YellowTile(SpriteBatch spriteBatch) {
        super(spriteBatch, Textures.IMG_YELLOW_TILE, Textures.IMG_YELLOW_ACTIVE_TILE);
        setTileType(TileTypesEnum.YELLOW);
        setTileFeature(TileFeatureEnum.REGULAR);
        color = Colors.YELLOW;
    }
}
