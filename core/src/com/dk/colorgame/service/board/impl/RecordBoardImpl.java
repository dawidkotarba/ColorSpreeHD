package com.dk.colorgame.service.board.impl;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.dk.colorgame.constants.Constants.LABEL_RECORD_ALPHA_INCREASE;
import static com.dk.colorgame.constants.Constants.LABEL_RECORD_POSY_INCREASE;

/**
 * Created by Dawid Kotarba on 2015-06-11.
 */
public class RecordBoardImpl extends AwardBoardImpl {

    public RecordBoardImpl(SpriteBatch spriteBatch, String fontName, int posXOffset, int posY) {
        super(spriteBatch, fontName, posXOffset, posY);
    }

    @Override
    public void draw() {
        draw(0, LABEL_RECORD_POSY_INCREASE, LABEL_RECORD_ALPHA_INCREASE);
    }
}
