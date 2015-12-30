package com.dk.colorgame.view.menu.impl.records;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dk.colorgame.Game;
import com.dk.colorgame.constants.Constants;
import com.dk.colorgame.constants.MenuColors;
import com.dk.colorgame.enums.GameStateEnum;
import com.dk.colorgame.service.board.MainBoard;
import com.dk.colorgame.view.menu.AbstractMenu;

import static com.dk.colorgame.constants.Constants.LABEL_TITLE_MENU_Y;
import static com.dk.colorgame.constants.Strings.LABEL_AUTHOR;
import static com.dk.colorgame.constants.Strings.LABEL_BACK;
import static com.dk.colorgame.constants.Strings.LABEL_CREDITS;
import static com.dk.colorgame.constants.Strings.LABEL_DK;
import static com.dk.colorgame.constants.Strings.LABEL_RATE_ME;
import static com.dk.colorgame.constants.Strings.LABEL_ROBOT_FACTORY;
import static com.dk.colorgame.constants.Strings.LABEL_USED_RESOURCES;

/**
 * Created by Dawid Kotarba on 2015-06-15.
 */
public class CreditsMenu extends AbstractMenu {

    public CreditsMenu(SpriteBatch spriteBatch, Viewport viewport, MainBoard mainBoard) {
        super(spriteBatch, viewport, mainBoard);
        mainColor = MenuColors.CYAN;

        TextButton backBtn = createRectangleButton(LABEL_BACK, 100, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_RECORDS);
            }
        });

        addActor(backBtn);

        TextButton rateMeBtn = createRectangleButton(LABEL_RATE_ME, 250, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.net.openURI(Constants.GOOGLE_PLAY_URL);
            }
        });

        addActor(rateMeBtn);
    }

    @Override
    public void render(float delta) {
        drawMenuTiles();
        drawHeaderText(LABEL_CREDITS, LABEL_TITLE_MENU_Y);

        drawText(LABEL_AUTHOR, 950);
        drawText("Code: " + LABEL_DK, 880);
        drawText("Graphics: " + LABEL_DK, 810);
        drawText("Special thanks: " + "Marcin Rosiek", 740);

        drawText(LABEL_USED_RESOURCES, 600);
        drawText("Sounds: " + LABEL_ROBOT_FACTORY, 530);
        drawText("Font: HomeSpun by Brian Kent", 460);

        super.render(delta);
    }
}
