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
import com.dk.colorgame.view.menu.AbstractMenu;

import static com.dk.colorgame.constants.Constants.LABEL_TITLE_MENU_Y;
import static com.dk.colorgame.constants.Strings.LABEL_BACK;
import static com.dk.colorgame.constants.Strings.LABEL_RESET_RECORDS;
import static com.dk.colorgame.constants.Strings.LABEL_RESET_RECORDS_CONFIRM;
import static com.dk.colorgame.constants.Strings.LABEL_YES;

/**
 * Created by Dawid Kotarba on 2015-08-07.
 */
public class RecordsResetMenu extends AbstractMenu {

    private final ScoreBoard scoreBoard;

    public RecordsResetMenu(SpriteBatch spriteBatch, Viewport viewport, MainBoard mainBoard) {
        super(spriteBatch, viewport, mainBoard);

        scoreBoard = mainBoard.getScoreBoard();
        mainColor = MenuColors.GREEN;

        TextButton yesBtn = createRectangleButton(LABEL_YES, 300, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                scoreBoard.resetRecords();
                Game.setGameState(GameStateEnum.MENU_RECORDS);
            }
        });

        addActor(yesBtn);

        TextButton backBtn = createRectangleButton(LABEL_BACK, 100, new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_RECORDS);
            }
        });

        addActor(backBtn);

    }

    @Override
    public void render(float delta) {
        drawMenuTiles();
        drawHeaderText(LABEL_RESET_RECORDS, LABEL_TITLE_MENU_Y);
        drawText(LABEL_RESET_RECORDS_CONFIRM, 600);
        super.render(delta);
    }
}
