package com.dk.colorgame.view.menu.impl;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dk.colorgame.constants.MenuColors;
import com.dk.colorgame.service.board.MainBoard;
import com.dk.colorgame.service.board.ScoreBoard;
import com.dk.colorgame.view.menu.AbstractMenu;

import static com.dk.colorgame.constants.Constants.LABEL_CUR_SCORE_Y;
import static com.dk.colorgame.constants.Constants.LABEL_TITLE_MENU_Y;
import static com.dk.colorgame.constants.Strings.LABEL_CURRENT_SCORE;
import static com.dk.colorgame.constants.Strings.LABEL_GAME_OVER;
import static com.dk.colorgame.constants.Strings.LABEL_HISCORE;
import static com.dk.colorgame.constants.Strings.LABEL_SPACE;

/**
 * Created by Dawid Kotarba on 2015-06-07.
 */
public class GameOverMenu extends AbstractMenu {

    private final ScoreBoard scoreBoard;

    public GameOverMenu(SpriteBatch spriteBatch, Viewport viewport, MainBoard mainBoard) {
        super(spriteBatch, viewport, mainBoard);
        mainColor = MenuColors.RED;

        scoreBoard = mainBoard.getScoreBoard();

        createPlayBtn(600);

        createMainMenuBtn(400);

        createExitBtn(200);
    }

    @Override
    public void render(float delta) {
        drawMenuTiles();
        showGameOverScreen();
        super.render(delta);
    }

    private void showGameOverScreen() {
        scoreBoard.saveIfHighScore();
        drawHeaderText(LABEL_GAME_OVER, LABEL_TITLE_MENU_Y);

        drawText(LABEL_CURRENT_SCORE + LABEL_SPACE + scoreBoard.getCurrentScore(), LABEL_CUR_SCORE_Y);
        drawText(LABEL_HISCORE + LABEL_SPACE + scoreBoard.fetchHighScore(), LABEL_CUR_SCORE_Y - 100);
    }
}
