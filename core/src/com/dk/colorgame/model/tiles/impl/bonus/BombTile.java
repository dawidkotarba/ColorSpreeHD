package com.dk.colorgame.model.tiles.impl.bonus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dk.colorgame.constants.Colors;
import com.dk.colorgame.constants.Constants;
import com.dk.colorgame.constants.Options;
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
 * Created by Dawid Kotarba on 2015-07-26.
 */
public class BombTile extends ParticleAbstractTile {

    private final MainBoard mainBoard;

    public BombTile(SpriteBatch spriteBatch, MainBoard mainBoard) {
        super(spriteBatch, GraphicUtils.loadAnimation(Animations.ANIMATION_TILE_BOMB, 1 / 20f), Textures.IMG_BOMB_ACTIVE_TILE, Particles.PARTICLE_BOMB, Particles.PARTICLE_EXPL_SMOKE_LARGE);
        this.mainBoard = mainBoard;
        animation.setPlayMode(Animation.PlayMode.LOOP);
        setTileType(TileTypesEnum.BOMB);
        setTileFeature(TileFeatureEnum.BONUS);
        color = Colors.GRAY;
        setDieSound((Sound) AssetLoader.get(Sounds.SOUND_EXPLOSION, Sound.class));
    }

    @Override
    public void activate() {
        super.activate();
        mainBoard.activateNeighbours(this);
        mainBoard.activateDiagonalNeighbours(this);
    }

    @Override
    public void die() {
        super.die();

        if (Options.vibration) {
            Gdx.input.vibrate(Constants.VIBRATION_TIME_LONG);
        }
    }

    @Override
    public String getTooltip() {
        return Strings.TOOLTIP_DESTROY_BLOCKS_AROUND;
    }
}