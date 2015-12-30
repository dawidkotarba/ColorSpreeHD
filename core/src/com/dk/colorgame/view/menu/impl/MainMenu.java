package com.dk.colorgame.view.menu.impl;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dk.colorgame.Game;
import com.dk.colorgame.constants.Options;
import com.dk.colorgame.enums.GameStateEnum;
import com.dk.colorgame.resources.Particles;
import com.dk.colorgame.service.board.MainBoard;
import com.dk.colorgame.view.menu.AbstractMenu;

import static com.dk.colorgame.constants.Strings.LABEL_OPTIONS;
import static com.dk.colorgame.constants.Strings.LABEL_SCORES;
import static com.dk.colorgame.constants.Strings.LABEL_TITLE;

/**
 * Created by Dawid Kotarba on 2015-05-24.
 */
public class MainMenu extends AbstractMenu {

    public MainMenu(SpriteBatch spriteBatch, Viewport viewport, final MainBoard mainBoard) {
        super(spriteBatch, viewport, mainBoard);

        if (Options.particleEffects) {
            addActor(createBackgroundParticleActor(Particles.PARTICLE_MENU));
        }

        tiles = createHeaderTiles();

        createExitBtn(100);

        TextButton optionsBtn = createRectangleButton(LABEL_OPTIONS, 250, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_OPTIONS);
            }
        });

        addActor(optionsBtn);


        TextButton scoreMenuBtn = createRectangleButton(LABEL_SCORES, 400, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_SCORES);
            }
        });

        addActor(scoreMenuBtn);

        createPlayBtn(550);
    }

    @Override
    public void render(float delta) {
        drawHeaderTiles();
        drawHeaderText(LABEL_TITLE);
        super.render(delta);
    }

}
