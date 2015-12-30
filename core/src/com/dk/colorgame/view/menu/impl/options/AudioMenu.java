package com.dk.colorgame.view.menu.impl.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dk.colorgame.Game;
import com.dk.colorgame.constants.MenuColors;
import com.dk.colorgame.constants.Options;
import com.dk.colorgame.constants.Strings;
import com.dk.colorgame.enums.GameStateEnum;
import com.dk.colorgame.service.board.MainBoard;
import com.dk.colorgame.utils.GraphicUtils;
import com.dk.colorgame.view.menu.AbstractMenu;

import static com.dk.colorgame.constants.Constants.LABEL_TITLE_MENU_Y;
import static com.dk.colorgame.constants.Strings.LABEL_BACK;
import static com.dk.colorgame.constants.Strings.LABEL_COLON;
import static com.dk.colorgame.constants.Strings.LABEL_OPTIONS_AUDIO;
import static com.dk.colorgame.constants.Strings.LABEL_VOLUME;

/**
 * Created by Dawid Kotarba on 2015-08-09.
 */
public class AudioMenu extends AbstractMenu {

    private final Preferences prefs;

    public AudioMenu(SpriteBatch spriteBatch, Viewport viewport, MainBoard mainBoard) {
        super(spriteBatch, viewport, mainBoard);
        mainColor = MenuColors.BLUE;
        prefs = Gdx.app.getPreferences(Options.PREFS_OPTIONS);

        TextButton backBtn = createRectangleButton(LABEL_BACK, 100, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_OPTIONS);
            }
        });

        addActor(backBtn);

        int btnCenter = (int) GraphicUtils.calculateCenterFontOffset(font, LABEL_VOLUME + LABEL_COLON);

        TextButton volumeDownBtn = createSquareButton(Strings.LABEL_MINUS, btnCenter - 150, LABEL_TITLE_MENU_Y - 540, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                volumeDown();
            }
        });

        addActor(volumeDownBtn);

        TextButton volumeUpBtn = createSquareButton(Strings.LABEL_PLUS, btnCenter + 200, LABEL_TITLE_MENU_Y - 540, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                volumeUp();
            }
        });

        addActor(volumeUpBtn);
    }

    @Override
    public void render(float delta) {
        drawMenuTiles();
        drawHeaderText(LABEL_OPTIONS_AUDIO, LABEL_TITLE_MENU_Y);

        String volumeValLabel = prefs.getInteger(Options.PREFS_VOLUME) * 10 + Strings.LABEL_PERCENT;
        font.draw(spriteBatch, LABEL_VOLUME + LABEL_COLON, GraphicUtils.calculateCenterFontOffset(font, LABEL_VOLUME + LABEL_COLON), LABEL_TITLE_MENU_Y - 450);
        font.draw(spriteBatch, volumeValLabel, GraphicUtils.calculateCenterFontOffset(font, volumeValLabel), LABEL_TITLE_MENU_Y - 500);

        super.render(delta);
    }

    private void volumeUp() {
        int volume = prefs.getInteger(Options.PREFS_VOLUME);

        if (volume < 10) {
            int newVolume = volume + 1;
            prefs.putInteger(Options.PREFS_VOLUME, newVolume);
            Options.volume = newVolume;
            prefs.flush();
        }
    }

    private void volumeDown() {
        int volume = prefs.getInteger(Options.PREFS_VOLUME);

        if (volume > 0) {
            int newVolume = volume - 1;
            prefs.putInteger(Options.PREFS_VOLUME, newVolume);
            Options.volume = newVolume;
            prefs.flush();
        }
    }
}
