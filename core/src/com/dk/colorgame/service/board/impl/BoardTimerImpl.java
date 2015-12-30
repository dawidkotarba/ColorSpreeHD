package com.dk.colorgame.service.board.impl;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.dk.colorgame.Game;
import com.dk.colorgame.constants.GameConstants;
import com.dk.colorgame.constants.Strings;
import com.dk.colorgame.enums.GameStateEnum;
import com.dk.colorgame.resources.Sounds;
import com.dk.colorgame.resources.loaders.AssetLoader;
import com.dk.colorgame.service.board.AwardBoard;
import com.dk.colorgame.service.board.BoardTimer;
import com.dk.colorgame.utils.SoundUtils;
import com.dk.colorgame.utils.Utils;

/**
 * Created by Dawid Kotarba on 2015-05-07.
 */
public class BoardTimerImpl implements BoardTimer {

    private int secondsLeft;
    private int totalPlayTimeSecs;
    private AwardBoard counterBoard;
    private Sound counter;
    private boolean timerStarted;

    public BoardTimerImpl() {
        counter = (Sound) AssetLoader.get(Sounds.SOUND_COUNTER, Sound.class);
        init();
    }

    private void init() {
        totalPlayTimeSecs = 0;
        secondsLeft = GameConstants.GAME_INITIAL_TIME_SECS;
    }

    @Override
    public void reset() {
        init();
    }

    @Override
    public void start() {
        if (timerStarted) {
            return;
        }

        com.badlogic.gdx.utils.Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                countDown();
            }
        }, GameConstants.GAME_START_DELAY, 1);

        timerStarted = true;
    }

    private void countDown() {
        if (secondsLeft <= 0 || Game.getGameState() != GameStateEnum.STARTED) {
            return;
        }
        totalPlayTimeSecs++;
        secondsLeft -= 1;

        showCounterMessages();
    }

    private void showCounterMessages() {
        if (counterBoard != null) {

            RecordBoardImpl messageBoard = (RecordBoardImpl) counterBoard;

            if (secondsLeft == 10) {
                messageBoard.add(Strings.COUNTER_10_LEFT, 0);
                SoundUtils.playSound(counter);
            } else if (secondsLeft == 20) {
                messageBoard.add(Strings.COUNTER_20_LEFT, 0);
                SoundUtils.playSound(counter);
            } else if (secondsLeft == 30) {
                messageBoard.add(Strings.COUNTER_30_LEFT, 0);
                SoundUtils.playSound(counter);
            }
        }
    }

    @Override
    public void addSeconds(int seconds) {
        secondsLeft += seconds;
    }

    @Override
    public String getBoardTimer() {
        return Utils.getFormattedTimer(secondsLeft);
    }

    @Override
    public boolean isGameFinished() {
        return secondsLeft <= 0;
    }

    @Override
    public int getTotalPlayTimeSecs() {
        return totalPlayTimeSecs;
    }

    @Override
    public void setCounterBoard(AwardBoard counterBoard) {
        this.counterBoard = counterBoard;
    }
}
