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
import static com.dk.colorgame.constants.Options.drawFps;
import static com.dk.colorgame.constants.Options.particleEffects;
import static com.dk.colorgame.constants.Strings.LABEL_BACK;
import static com.dk.colorgame.constants.Strings.LABEL_COLON;
import static com.dk.colorgame.constants.Strings.LABEL_DRAW_FPS;
import static com.dk.colorgame.constants.Strings.LABEL_OPTIONS_VIDEO;
import static com.dk.colorgame.constants.Strings.LABEL_PARTICLES;
import static com.dk.colorgame.constants.Strings.LABEL_SPACE;

/**
 * Created by Dawid Kotarba on 2015-08-09.
 */
public class VideoMenu extends AbstractMenu {

    private final Preferences prefs;

    public VideoMenu(SpriteBatch spriteBatch, Viewport viewport, MainBoard mainBoard) {
        super(spriteBatch, viewport, mainBoard);

        mainColor = MenuColors.VIOLET;

        prefs = Gdx.app.getPreferences(Options.PREFS_OPTIONS);

        TextButton backBtn = createRectangleButton(LABEL_BACK, 100, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_OPTIONS);
            }
        });

        addActor(backBtn);

        createOnOffBtn(Options.particleEffects, LABEL_TITLE_MENU_Y - 500, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switchParticles();
            }
        });

        createOnOffBtn(Options.drawFps, LABEL_TITLE_MENU_Y - 750, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switchDrawFps();
            }
        });
    }

    @Override
    public void render(float delta) {
        drawMenuTiles();
        drawHeaderText(LABEL_OPTIONS_VIDEO, LABEL_TITLE_MENU_Y);
        drawValues();
        super.render(delta);
    }

    private void switchParticles() {
        particleEffects = !particleEffects;
        prefs.putBoolean(Options.PREFS_PARTICLES, particleEffects);
        prefs.flush();
    }

    private void switchDrawFps() {
        drawFps = !drawFps;
        prefs.putBoolean(Options.PREFS_DRAW_FPS, drawFps);
        prefs.flush();
    }

    private void drawValues() {
        String particlesLabel = LABEL_PARTICLES + LABEL_COLON + LABEL_SPACE + showOnOff(particleEffects);
        font.draw(spriteBatch, particlesLabel, GraphicUtils.calculateCenterFontOffset(font, particlesLabel), LABEL_TITLE_MENU_Y - 350);

        String drawFpsLabel = LABEL_DRAW_FPS + LABEL_COLON + LABEL_SPACE + showOnOff(drawFps);
        font.draw(spriteBatch, drawFpsLabel, GraphicUtils.calculateCenterFontOffset(font, drawFpsLabel), LABEL_TITLE_MENU_Y - 600);
    }

}
