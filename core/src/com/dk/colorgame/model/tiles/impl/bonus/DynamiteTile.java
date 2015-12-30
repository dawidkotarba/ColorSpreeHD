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
 * Created by Dawid Kotarba on 2015-05-03.
 */
public class DynamiteTile extends ParticleAbstractTile {

    private final MainBoard mainBoard;

    public DynamiteTile(SpriteBatch spriteBatch, MainBoard mainBoard) {
        super(spriteBatch, GraphicUtils.loadAnimation(Animations.ANIMATION_TILE_DYNAMITE_RED, 1 / 20f), Textures.IMG_DYNAMITE_ACTIVE_TILE, Particles.PARTICLE_DYNAMITE, Particles.PARTICLE_EXPL);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        this.mainBoard = mainBoard;
        setTileType(TileTypesEnum.DYNAMITE);
        setTileFeature(TileFeatureEnum.BONUS);
        color = Colors.GRAY;
        setDieSound((Sound) AssetLoader.get(Sounds.SOUND_EXPLOSION, Sound.class));
    }

    @Override
    public void activate() {
        super.activate();
        mainBoard.activateNeighbours(this);
    }

    @Override
    public void die() {
        super.die();

        if (Options.vibration) {
            Gdx.input.vibrate(Constants.VIBRATION_TIME_SHORT);
        }
    }

    @Override
    public String getTooltip() {
        return Strings.TOOLTIP_DESTROY_BLOCKS_AROUND;
    }
}
