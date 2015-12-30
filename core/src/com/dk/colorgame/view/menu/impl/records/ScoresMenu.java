package com.dk.colorgame.view.menu.impl.records;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dk.colorgame.Game;
import com.dk.colorgame.enums.GameStateEnum;
import com.dk.colorgame.service.board.MainBoard;
import com.dk.colorgame.view.menu.AbstractMenu;

import static com.dk.colorgame.constants.Constants.LABEL_TITLE_MENU_Y;
import static com.dk.colorgame.constants.Strings.LABEL_ACHIEVEMENTS;
import static com.dk.colorgame.constants.Strings.LABEL_BACK;
import static com.dk.colorgame.constants.Strings.LABEL_SCORES;
import static com.dk.colorgame.constants.Strings.LABEL_SCORE_LEADERBOARD;
import static com.dk.colorgame.constants.Strings.LABEL_SUMMARY;
import static com.dk.colorgame.constants.Strings.LABEL_TIME_LEADERBOARD;

/**
 * Created by Dawid Kotarba on 2015-08-21.
 */
public class ScoresMenu extends AbstractMenu {
    public ScoresMenu(SpriteBatch spriteBatch, Viewport viewport, MainBoard mainBoard) {
        super(spriteBatch, viewport, mainBoard);

        TextButton backBtn = createRectangleButton(LABEL_BACK, 100, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_MAIN);
            }
        });

        addActor(backBtn);

        TextButton achievementsBtn = createRectangleButton(LABEL_ACHIEVEMENTS, 250, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.getActionResolver().showAchievements();
            }
        });

        addActor(achievementsBtn);

        TextButton timeLeaderBoardBtn = createRectangleButton(LABEL_TIME_LEADERBOARD, 400, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.getActionResolver().showTimeLeaderboard();
            }
        });

        addActor(timeLeaderBoardBtn);

        TextButton scoreLeaderBoardBtn = createRectangleButton(LABEL_SCORE_LEADERBOARD, 550, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.getActionResolver().showScoreLeaderboard();
            }
        });

        addActor(scoreLeaderBoardBtn);

        TextButton recordsBtn = createRectangleButton(LABEL_SUMMARY, 700, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_RECORDS);
            }
        });

        addActor(recordsBtn);
    }

    @Override
    public void render(float delta) {
        drawMenuTiles();
        drawHeaderText(LABEL_SCORES, LABEL_TITLE_MENU_Y);
        super.render(delta);
    }
}
