package com.dk.colorgame.view.menu.impl.records;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dk.colorgame.Game;
import com.dk.colorgame.constants.MenuColors;
import com.dk.colorgame.enums.GameStateEnum;
import com.dk.colorgame.service.board.MainBoard;
import com.dk.colorgame.service.board.ScoreBoard;
import com.dk.colorgame.utils.Utils;
import com.dk.colorgame.view.menu.AbstractMenu;

import static com.dk.colorgame.constants.Constants.LABEL_HI_SCORE_Y;
import static com.dk.colorgame.constants.Constants.LABEL_TITLE_MENU_Y;
import static com.dk.colorgame.constants.Strings.LABEL_BACK;
import static com.dk.colorgame.constants.Strings.LABEL_CREDITS;
import static com.dk.colorgame.constants.Strings.LABEL_POINTS;
import static com.dk.colorgame.constants.Strings.LABEL_REC_HIGH_PLAY_TIME;
import static com.dk.colorgame.constants.Strings.LABEL_REC_HIGH_SCORE;
import static com.dk.colorgame.constants.Strings.LABEL_REC_HIGH_SWIPE_SCORE;
import static com.dk.colorgame.constants.Strings.LABEL_REC_HIGH_SWIPE_TILES;
import static com.dk.colorgame.constants.Strings.LABEL_RESET_RECORDS;
import static com.dk.colorgame.constants.Strings.LABEL_SECONDS;
import static com.dk.colorgame.constants.Strings.LABEL_SPACE;
import static com.dk.colorgame.constants.Strings.LABEL_SUMMARY;

/**
 * Created by Dawid Kotarba on 2015-06-07.
 */
public class RecordsMenu extends AbstractMenu {

    private final ScoreBoard scoreBoard;

    public RecordsMenu(SpriteBatch spriteBatch, Viewport viewport, MainBoard mainBoard) {
        super(spriteBatch, viewport, mainBoard);

        mainColor = MenuColors.BLUE;
        scoreBoard = mainBoard.getScoreBoard();

        TextButton scoreReset = createRectangleButton(LABEL_RESET_RECORDS, 400, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_SCORE_RESET);
            }
        });

        addActor(scoreReset);

        TextButton creditsBtn = createRectangleButton(LABEL_CREDITS, 250, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_CREDITS);
            }
        });

        addActor(creditsBtn);

        TextButton backBtn = createRectangleButton(LABEL_BACK, 100, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_SCORES);
            }
        });

        addActor(backBtn);
    }

    private void showRecords() {
        drawHeaderText(LABEL_SUMMARY, LABEL_TITLE_MENU_Y);

        String hiScore = LABEL_REC_HIGH_SCORE + LABEL_SPACE + scoreBoard.fetchHighScore() + LABEL_POINTS;
        drawText(hiScore, LABEL_HI_SCORE_Y);

        String hiSwipeScore = LABEL_REC_HIGH_SWIPE_SCORE + LABEL_SPACE + scoreBoard.fetchHighSwipeScore() + LABEL_POINTS;
        drawText(hiSwipeScore, LABEL_HI_SCORE_Y - 100);

        String hiSwipeTiles = LABEL_REC_HIGH_SWIPE_TILES + LABEL_SPACE + scoreBoard.fetchHighSwipeTiles();
        drawText(hiSwipeTiles, LABEL_HI_SCORE_Y - 200);

        String hiPlayTime = LABEL_REC_HIGH_PLAY_TIME + LABEL_SPACE + Utils.getFormattedTimer(scoreBoard.fetchHighPlayTime()) + LABEL_SECONDS;
        drawText(hiPlayTime, LABEL_HI_SCORE_Y - 300);

    }

    @Override
    public void render(float delta) {
        drawMenuTiles();
        showRecords();
        super.render(delta);
    }
}
