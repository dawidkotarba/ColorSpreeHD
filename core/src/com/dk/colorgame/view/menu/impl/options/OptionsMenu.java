package com.dk.colorgame.view.menu.impl.options;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dk.colorgame.Game;
import com.dk.colorgame.constants.Strings;
import com.dk.colorgame.enums.GameStateEnum;
import com.dk.colorgame.service.board.MainBoard;
import com.dk.colorgame.view.menu.AbstractMenu;

import static com.dk.colorgame.constants.Constants.LABEL_TITLE_MENU_Y;
import static com.dk.colorgame.constants.Strings.LABEL_BACK;
import static com.dk.colorgame.constants.Strings.LABEL_OPTIONS;

/**
 * Created by Dawid Kotarba on 2015-06-15.
 */
public class OptionsMenu extends AbstractMenu {

    public OptionsMenu(SpriteBatch spriteBatch, Viewport viewport, MainBoard mainBoard) {
        super(spriteBatch, viewport, mainBoard);

        TextButton backBtn = createRectangleButton(LABEL_BACK, 100, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_MAIN);
            }
        });

        addActor(backBtn);

        TextButton audioOpts = createRectangleButton(Strings.LABEL_AUDIO, 250, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_OPTIONS_AUDIO);
            }
        });

        addActor(audioOpts);

        TextButton videoOpts = createRectangleButton(Strings.LABEL_VIDEO, 400, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_OPTIONS_VIDEO);
            }
        });

        addActor(videoOpts);

        TextButton gameOpts = createRectangleButton(Strings.LABEL_GAME, 550, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_OPTIONS_GAME);
            }
        });

        addActor(gameOpts);

    }

    @Override
    public void render(float delta) {
        drawMenuTiles();
        drawHeaderText(LABEL_OPTIONS, LABEL_TITLE_MENU_Y);
        super.render(delta);
    }
}
