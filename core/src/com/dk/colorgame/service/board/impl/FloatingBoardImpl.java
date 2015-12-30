package com.dk.colorgame.service.board.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dk.colorgame.model.tiles.Tile;
import com.dk.colorgame.service.PointsCalculator;
import com.dk.colorgame.service.board.AbstractBoard;
import com.dk.colorgame.service.board.FloatingBoard;
import com.dk.colorgame.utils.InputUtils;

import java.util.List;

import static com.dk.colorgame.constants.Constants.FLOATING_SCORES_X_OFFSET;
import static com.dk.colorgame.constants.Constants.FLOATING_SCORES_X_PARTICLE_OFFSET;
import static com.dk.colorgame.constants.Constants.FLOATING_SCORES_Y_OFFSET;
import static com.dk.colorgame.constants.Constants.FLOATING_SCORES_Y_PARTICLE_OFFSET;
import static com.dk.colorgame.constants.Strings.LABEL_PLUS;
import static com.dk.colorgame.constants.Strings.LABEL_POINTS;
import static com.dk.colorgame.constants.Strings.LABEL_SECONDS;
import static com.dk.colorgame.constants.Strings.LABEL_SPACE;

/**
 * Created by Dawid Kotarba on 2015-06-21.
 */
public class FloatingBoardImpl extends AbstractBoard implements FloatingBoard {

    public FloatingBoardImpl(SpriteBatch spriteBatch, String fontName) {
        super(spriteBatch, fontName);
    }

    private Vector2 getFloatingBoardCoords() {
        return new Vector2(InputUtils.getTouchCoordinates(0).getX() + FLOATING_SCORES_X_OFFSET, InputUtils.getTouchCoordinates(0).getY() + FLOATING_SCORES_Y_OFFSET);
    }

    @Override
    public void draw(Tile firstTouched, Tile lastTouched, List<Tile> activeTiles) {

        float x = getFloatingBoardCoords().x;
        float y = getFloatingBoardCoords().y;


        if (lastTouched != null && Gdx.input.isTouched(0) && PointsCalculator.calculateAddedPoints(activeTiles, firstTouched) == 0) {
            font.draw(spriteBatch, lastTouched.getTooltip(), x + FLOATING_SCORES_X_PARTICLE_OFFSET, y + FLOATING_SCORES_Y_PARTICLE_OFFSET);

        } else if (lastTouched != null && PointsCalculator.calculateAddedPoints(activeTiles, firstTouched) > 1) {

            int unrealizedPoints = PointsCalculator.calculateAddedPoints(activeTiles, firstTouched);
            int unrealizedSeconds = PointsCalculator.calculateAddedSeconds(activeTiles, firstTouched);

            if (unrealizedSeconds > 0) {
                font.draw(spriteBatch, LABEL_PLUS + unrealizedPoints + LABEL_POINTS + LABEL_SPACE + unrealizedSeconds + LABEL_SECONDS, x + FLOATING_SCORES_X_PARTICLE_OFFSET, y + FLOATING_SCORES_Y_PARTICLE_OFFSET);
            } else {
                font.draw(spriteBatch, LABEL_PLUS + unrealizedPoints + LABEL_POINTS + LABEL_SPACE, x + FLOATING_SCORES_X_PARTICLE_OFFSET, y + FLOATING_SCORES_Y_PARTICLE_OFFSET);
            }
        }
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported in this implementation");
    }
}
