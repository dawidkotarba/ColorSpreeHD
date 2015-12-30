package com.dk.colorgame.service.board.impl;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dk.colorgame.service.board.AbstractBoard;
import com.dk.colorgame.service.board.AwardBoard;
import com.dk.colorgame.utils.GraphicUtils;

import static com.dk.colorgame.constants.Constants.LABEL_AWARD_ALPHA_INCREASE;
import static com.dk.colorgame.constants.Constants.LABEL_AWARD_INIT_ALPHA;
import static com.dk.colorgame.constants.Constants.LABEL_AWARD_POSX_INCREASE;

/**
 * Created by Dawid Kotarba on 2015-05-10.
 */
public class AwardBoardImpl extends AbstractBoard implements AwardBoard {

    private AwardText currentAward;
    private final int posXOffset;
    private final int posY;
    private int awardsPoints;

    public AwardBoardImpl(SpriteBatch spriteBatch, String fontName, int posXOffset, int posY) {
        super(spriteBatch, fontName);
        this.posXOffset = posXOffset;
        this.posY = posY;
    }

    @Override
    public void draw() {
        draw(LABEL_AWARD_POSX_INCREASE, 0, LABEL_AWARD_ALPHA_INCREASE);
    }

    void draw(float posXIncrease, float posYIncrease, float alphaIncrease) {
        cleanAward();

        if (currentAward == null) {
            return;
        }

        font.setColor(1, 1, 1, currentAward.alpha);
        font.draw(spriteBatch, currentAward.text, currentAward.posX, currentAward.posY);

        currentAward.alpha = currentAward.alpha + alphaIncrease;
        currentAward.posX = currentAward.posX + posXIncrease;
        currentAward.posY = currentAward.posY + posYIncrease;
    }

    private void cleanAward() {
        if (currentAward != null && currentAward.alpha <= 0) {
            currentAward = null;
        }
    }

    @Override
    public void add(String text, int points) {
        awardsPoints += points;

        if (currentAward == null) {
            currentAward = new AwardText(text, posXOffset, posY);
            font.setColor(1, 1, 1, LABEL_AWARD_INIT_ALPHA);
        }
    }

    @Override
    public void addPoints(int points) {
        awardsPoints += points;
    }

    @Override
    public boolean withdrawAwardPoint() {

        if (awardsPoints > 0) {
            awardsPoints--;
            return true;
        }
        return false;
    }

    class AwardText {

        private final String text;
        private final float posXOffset;
        private float posX;
        private float posY;
        private float alpha;

        public AwardText(String text, float posXOffset, float posY) {
            this.text = text;
            this.posXOffset = posXOffset;
            this.posX = calculatePosX();
            this.posY = posY;

            reset();
        }

        private float calculatePosX() {
            return GraphicUtils.calculateCenterFontOffset(font, text) + posXOffset;
        }

        public void reset() {
            alpha = LABEL_AWARD_INIT_ALPHA;
            this.posX = calculatePosX();
            posY = AwardBoardImpl.this.posY;
        }
    }
}