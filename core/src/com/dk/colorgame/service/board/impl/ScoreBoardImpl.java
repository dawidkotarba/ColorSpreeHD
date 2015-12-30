package com.dk.colorgame.service.board.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dk.colorgame.constants.Constants;
import com.dk.colorgame.constants.Options;
import com.dk.colorgame.service.board.AbstractBoard;
import com.dk.colorgame.service.board.AwardBoard;
import com.dk.colorgame.service.board.BoardTimer;
import com.dk.colorgame.service.board.ScoreBoard;
import com.dk.colorgame.utils.GraphicUtils;

import static com.dk.colorgame.constants.GameConstants.PROPERTY_HIGH_PLAY_TIME;
import static com.dk.colorgame.constants.GameConstants.PROPERTY_HIGH_SWIPE_SCORE;
import static com.dk.colorgame.constants.GameConstants.PROPERTY_HIGH_SWIPE_TILES;
import static com.dk.colorgame.constants.GameConstants.PROPERTY_SCORE_BOARD_HIGH_SCORE;
import static com.dk.colorgame.constants.Strings.LABEL_HISCORE;
import static com.dk.colorgame.constants.Strings.LABEL_POINTS;
import static com.dk.colorgame.constants.Strings.LABEL_SCORE;
import static com.dk.colorgame.constants.Strings.LABEL_SECONDS;
import static com.dk.colorgame.constants.Strings.LABEL_SPACE;
import static com.dk.colorgame.constants.Strings.LABEL_TIME_LEFT;


/**
 * Created by Dawid Kotarba on 2015-05-03.
 */
public class ScoreBoardImpl extends AbstractBoard implements ScoreBoard {

    private final Preferences prefs;
    private final BoardTimer boardTimer;

    private int highScore;
    private int currentScore;

    private int highSwipeScore;
    private int currentSwipeScore;

    private int highSwipeTiles;
    private int currentSwipeTiles;

    private int highPlayTime;

    public ScoreBoardImpl(SpriteBatch spriteBatch, String fontName) {
        super(spriteBatch, fontName);
        prefs = Gdx.app.getPreferences(Options.PREFS_SCOREBOARD);

        boardTimer = new BoardTimerImpl();

        highScore = prefs.getInteger(PROPERTY_SCORE_BOARD_HIGH_SCORE);
        highSwipeScore = prefs.getInteger(PROPERTY_HIGH_SWIPE_SCORE);
        highSwipeTiles = prefs.getInteger(PROPERTY_HIGH_SWIPE_TILES);
        highPlayTime = prefs.getInteger(PROPERTY_HIGH_PLAY_TIME);

        currentSwipeScore = highSwipeScore;
        currentSwipeTiles = highSwipeTiles;
    }

    @Override
    public void reset() {
        boardTimer.reset();
        currentScore = 0;
    }

    @Override
    public void draw() {
        // start here to fix the bug with Google Play services sign in
        boardTimer.start();

        String scoreLine = LABEL_SCORE + LABEL_SPACE + currentScore + LABEL_POINTS + LABEL_SPACE + LABEL_HISCORE + LABEL_SPACE + fetchHighScore() + LABEL_POINTS;
        font.draw(spriteBatch, scoreLine, GraphicUtils.calculateCenterFontOffset(font, scoreLine), Constants.SCORES_Y_OFFSET);

        String timeLine = LABEL_TIME_LEFT + LABEL_SPACE + boardTimer.getBoardTimer() + LABEL_SECONDS;
        font.draw(spriteBatch, timeLine, GraphicUtils.calculateCenterFontOffset(font, timeLine), Constants.TIME_Y_OFFSET);
    }

    @Override
    public int fetchHighScore() {
        return prefs.getInteger(PROPERTY_SCORE_BOARD_HIGH_SCORE);
    }

    @Override
    public int fetchHighSwipeScore() {
        return prefs.getInteger(PROPERTY_HIGH_SWIPE_SCORE);
    }

    @Override
    public int fetchHighSwipeTiles() {
        return prefs.getInteger(PROPERTY_HIGH_SWIPE_TILES);
    }

    @Override
    public int fetchHighPlayTime() {
        return prefs.getInteger(PROPERTY_HIGH_PLAY_TIME);
    }

    @Override
    public boolean updateHighSwipeScore(int score) {
        if (score > currentSwipeScore) {
            currentSwipeScore = score;
            return true;
        }
        return false;
    }

    @Override
    public boolean updateHighSwipeTiles(int score) {
        if (score > currentSwipeTiles) {
            currentSwipeTiles = score;
            return true;
        }
        return false;
    }

    @Override
    public void saveIfHighScore() {

        boolean shallFlush = false;

        if (currentScore > highScore) {
            prefs.putInteger(PROPERTY_SCORE_BOARD_HIGH_SCORE, currentScore);
            highScore = currentScore;
            shallFlush = true;
        }

        if (currentSwipeScore > highSwipeScore) {
            prefs.putInteger(PROPERTY_HIGH_SWIPE_SCORE, currentSwipeScore);
            highSwipeScore = currentSwipeScore;
            shallFlush = true;
        }

        if (currentSwipeTiles > highSwipeTiles) {
            prefs.putInteger(PROPERTY_HIGH_SWIPE_TILES, currentSwipeTiles);
            highSwipeTiles = currentSwipeTiles;
            shallFlush = true;
        }

        if (boardTimer.getTotalPlayTimeSecs() > highPlayTime) {
            prefs.putInteger(PROPERTY_HIGH_PLAY_TIME, boardTimer.getTotalPlayTimeSecs());
            highPlayTime = boardTimer.getTotalPlayTimeSecs();
            shallFlush = true;
        }

        if (shallFlush) {
            prefs.flush();
        }
    }

    @Override
    public void resetRecords() {
        prefs.putInteger(PROPERTY_SCORE_BOARD_HIGH_SCORE, 0);
        highScore = 0;
        currentScore = 0;

        prefs.putInteger(PROPERTY_HIGH_SWIPE_SCORE, 0);
        highSwipeScore = 0;
        currentSwipeScore = 0;

        prefs.putInteger(PROPERTY_HIGH_SWIPE_TILES, 0);
        highSwipeTiles = 0;
        currentSwipeTiles = 0;

        prefs.putInteger(PROPERTY_HIGH_PLAY_TIME, 0);
        highPlayTime = 0;

        prefs.flush();
    }

    @Override
    public int getCurrentScore() {
        return currentScore;
    }

    @Override
    public void addScore(int score) {
        currentScore += score;
    }

    @Override
    public void addSeconds(int seconds) {
        boardTimer.addSeconds(seconds);
    }

    @Override
    public boolean isGameFinished() {
        return boardTimer.isGameFinished();
    }

    @Override
    public void setCounterBoard(AwardBoard counterBoard) {
        boardTimer.setCounterBoard(counterBoard);
    }

    @Override
    public int getGameplayTime() {
        return boardTimer.getTotalPlayTimeSecs();
    }

    @Override
    public int getCurrentSwipeTiles() {
        return currentSwipeTiles;
    }
}
