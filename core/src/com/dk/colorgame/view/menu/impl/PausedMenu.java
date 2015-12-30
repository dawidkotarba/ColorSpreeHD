package com.dk.colorgame.view.menu.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dk.colorgame.Game;
import com.dk.colorgame.constants.Constants;
import com.dk.colorgame.enums.GameStateEnum;
import com.dk.colorgame.service.board.MainBoard;
import com.dk.colorgame.view.menu.AbstractMenu;

import static com.dk.colorgame.constants.Constants.LABEL_TITLE_MENU_Y;
import static com.dk.colorgame.constants.Strings.LABEL_GAME_PAUSED;
import static com.dk.colorgame.constants.Strings.LABEL_RATE_ME;
import static com.dk.colorgame.constants.Strings.LABEL_RESET;
import static com.dk.colorgame.constants.Strings.LABEL_RESUME;

/**
 * Created by Dawid Kotarba on 2015-06-08.
 */
public class PausedMenu extends AbstractMenu {

    public PausedMenu(SpriteBatch spriteBatch, Viewport viewport, final MainBoard mainBoard) {
        super(spriteBatch, viewport, mainBoard);

        TextButton resumeBtn = createRectangleButton(LABEL_RESUME, 700, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.STARTED);
            }
        });

        addActor(resumeBtn);

        TextButton resetBtn = createRectangleButton(LABEL_RESET, 550, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainBoard.reset();
                Game.setGameState(GameStateEnum.STARTED);
            }
        });

        addActor(resetBtn);

        TextButton rateMeBtn = createRectangleButton(LABEL_RATE_ME, 400, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.net.openURI(Constants.GOOGLE_PLAY_URL);
            }
        });

        addActor(rateMeBtn);

        createMainMenuBtn(250);

        createExitBtn(100);
    }

    @Override
    public void render(float delta) {
        drawMenuTiles();
        drawHeaderText(LABEL_GAME_PAUSED, LABEL_TITLE_MENU_Y);
        super.render(delta);
    }
}
