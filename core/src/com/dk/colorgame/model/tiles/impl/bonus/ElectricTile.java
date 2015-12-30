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
import com.dk.colorgame.service.board.MainBoard;
import com.dk.colorgame.utils.GraphicUtils;

/**
 * Created by Dawid Kotarba on 2015-08-27.
 */
public class ElectricTile extends ParticleAbstractTile {

    private final MainBoard mainBoard;

    public ElectricTile(SpriteBatch spriteBatch, MainBoard mainBoard) {
        super(spriteBatch, GraphicUtils.loadAnimation(Animations.ANIMATION_TILE_ELECTRIC, 1 / 20f), Textures.IMG_ELECTRIC_ACTIVE_TILE, Particles.PARTICLE_ELECTRIC_SMALL, Particles.PARTICLE_ELECTRIC);
        this.mainBoard = mainBoard;
        setTileType(TileTypesEnum.ELECTRIC);
        setTileFeature(TileFeatureEnum.BONUS);
        color = Colors.GRAY;
        setDieSound((Sound) AssetLoader.get(Sounds.SOUND_ELECTR, Sound.class));
    }

    @Override
    public String getTooltip() {
        return Strings.TOOLTIP_DESTROY_RANDOM_BLOCKS;
    }

    @Override
    public void activate() {
        super.activate();
        mainBoard.activateRandomTiles(GameConstants.GAME_TILE_ELECTRIC_ACTIVATE);
    }
}
