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
import com.dk.colorgame.enums.GameStateEnum;
import com.dk.colorgame.service.board.MainBoard;
import com.dk.colorgame.utils.GraphicUtils;
import com.dk.colorgame.view.menu.AbstractMenu;

import static com.dk.colorgame.constants.Constants.LABEL_TITLE_MENU_Y;
import static com.dk.colorgame.constants.Options.shakeEffect;
import static com.dk.colorgame.constants.Options.stats;
import static com.dk.colorgame.constants.Options.vibration;
import static com.dk.colorgame.constants.Strings.LABEL_BACK;
import static com.dk.colorgame.constants.Strings.LABEL_COLON;
import static com.dk.colorgame.constants.Strings.LABEL_GAME;
import static com.dk.colorgame.constants.Strings.LABEL_SEND_STATS;
import static com.dk.colorgame.constants.Strings.LABEL_SHAKE_EFFECT;
import static com.dk.colorgame.constants.Strings.LABEL_SPACE;
import static com.dk.colorgame.constants.Strings.LABEL_VIBRATION;

/**
 * Created by Dawid Kotarba on 2015-08-12.
 */
public class GameMenu extends AbstractMenu {

    private final Preferences prefs;

    private final int VIBRATION_POS = LABEL_TITLE_MENU_Y - 350;
    private final int SHAKE_EFFECT_POS = LABEL_TITLE_MENU_Y - 600;
    private final int STATS_POS = LABEL_TITLE_MENU_Y - 850;
    private final int LABEL_OFFSET = 150;

    public GameMenu(SpriteBatch spriteBatch, Viewport viewport, MainBoard mainBoard) {
        super(spriteBatch, viewport, mainBoard);
        mainColor = MenuColors.YELLOW;

        prefs = Gdx.app.getPreferences(Options.PREFS_OPTIONS);

        createOnOffBtn(Options.vibration, VIBRATION_POS, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switchVibration();
            }
        });

        createOnOffBtn(Options.shakeEffect, SHAKE_EFFECT_POS, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switchShakeEffect();
            }
        });

        createOnOffBtn(Options.stats, STATS_POS, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switchStats();
            }
        });


        TextButton backBtn = createRectangleButton(LABEL_BACK, 100, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_OPTIONS);
            }
        });

        addActor(backBtn);
    }

    private void drawValues() {
        String vibrationLabel = LABEL_VIBRATION + LABEL_COLON + LABEL_SPACE + showOnOff(vibration);
        font.draw(spriteBatch, vibrationLabel, GraphicUtils.calculateCenterFontOffset(font, vibrationLabel), VIBRATION_POS + LABEL_OFFSET);

        String shakeEffectLabel = LABEL_SHAKE_EFFECT + LABEL_COLON + LABEL_SPACE + showOnOff(shakeEffect);
        font.draw(spriteBatch, shakeEffectLabel, GraphicUtils.calculateCenterFontOffset(font, shakeEffectLabel), SHAKE_EFFECT_POS + LABEL_OFFSET);

        String statsLabel = LABEL_SEND_STATS + LABEL_COLON + LABEL_SPACE + showOnOff(stats);
        font.draw(spriteBatch, statsLabel, GraphicUtils.calculateCenterFontOffset(font, statsLabel), STATS_POS + LABEL_OFFSET);
    }

    private void switchVibration() {
        vibration = !vibration;
        prefs.putBoolean(Options.PREFS_VIBRATION, vibration);
        prefs.flush();
    }

    private void switchShakeEffect() {
        shakeEffect = !shakeEffect;
        prefs.putBoolean(Options.PREFS_SHAKE_EFFECT, vibration);
        prefs.flush();
    }

    private void switchStats() {
        stats = !stats;
        prefs.putBoolean(Options.PREFS_STATS, stats);
        prefs.flush();
    }

    @Override
    public void render(float delta) {
        drawMenuTiles();
        drawHeaderText(LABEL_GAME, LABEL_TITLE_MENU_Y);
        drawValues();
        super.render(delta);
    }
}
