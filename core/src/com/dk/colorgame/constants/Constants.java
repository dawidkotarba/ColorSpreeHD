package com.dk.colorgame.constants;

import com.dk.colorgame.enums.EnvironmentsEnum;

/**
 * Created by Dawid Kotarba on 2015-04-29.
 */
public class Constants {

    // environment
    public static final EnvironmentsEnum ENVIRONMENT = EnvironmentsEnum.DEV;

    public static final boolean SHOW_ADS = false;
    public static final String ANDROID_TAG = "ColorSpree";
    public static final String DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";
    public static final String GOOGLE_PLAY_URL = "https://play.google.com/store/apps/details?id=com.dk.colorgame.android";
    public static final int SOUND_INIT_VOLUME = 3;
    public static final int SECONDS_IN_MINUTE = 60;
    public static final int SCREEN_WIDTH = 720;
    public static final int SCREEN_HEIGHT = 1280;
    public static final int BOARD_COLS = 6;
    public static final int BOARD_MENU_COLS = 10;
    public static final int BOARD_ROWS = 10;
    public static final int BOARD_Y_OFFSET = 210;
    public static final int SCORES_Y_OFFSET = 180;
    public static final int TIME_Y_OFFSET = 110;
    public static final int FLOATING_SCORES_X_OFFSET = 0;
    public static final int FLOATING_SCORES_Y_OFFSET = 100;
    public static final int FLOATING_SCORES_X_PARTICLE_OFFSET = 20;
    public static final int FLOATING_SCORES_Y_PARTICLE_OFFSET = 20;
    public static final int PARTICLE_X_OFFSET = 50;
    public static final int PARTICLE_Y_OFFSET = 60;
    public static final float TILE_INIT_SIZE = 0.7f;
    public static final float TILE_INIT_RESET_SIZE = 0.2f;
    public static final float TILE_ACTIVE_SIZE = 1.15f;
    public static final int TILE_FALL_DOWN_SPEED = 12;
    public static final int TILE_DYING_ROTATION = 5;
    public static final float TILE_DYING_FADE_AWAY = 0.05f;
    public static final float TILE_DYING_SCALE = 0.07f;
    public static final float TILE_EXTRA_DYING_SCALE = 0.4f;
    public static final float TILE_CREATION_SCALE = 0.03f;
    public static final int TILE_ACTIVATION_ROTATE = 2;
    public static final int TILE_LIGHT_OFFSET = -80;
    public static final float TILE_LIGHT_MULTIPLIER = 1;
    public static final int LABEL_CUR_SCORE_Y = 920;
    public static final int LABEL_HI_SCORE_Y = 900;
    public static final int LABEL_AWARD_X_OFFSET = 100;
    public static final int LABEL_AWARD_Y = 700;
    public static final int LABEL_RECORD_Y = 300;
    public static final int LABEL_AWARD_INIT_ALPHA = 1;
    public static final float LABEL_AWARD_ALPHA_INCREASE = -0.01f;
    public static final float LABEL_RECORD_ALPHA_INCREASE = -0.007f;
    public static final int LABEL_AWARD_POSX_INCREASE = -2;
    public static final int LABEL_RECORD_POSY_INCREASE = 1;
    public static final int EFFECT_SHAKE_TIME = 1;
    public static final int LABEL_TITLE_Y = 1030;
    public static final int LABEL_TITLE_MENU_Y = 1140;
    public static final float BACKGROUND_COLOR_INCREASE = -0.01f;
    public static final float BACKGROUND_COLORIZE_INCREASE = 0.01f;
    public static final int VIBRATION_TIME_LONG = 500;
    public static final int VIBRATION_TIME_SHORT = 100;

    private Constants() {
        // intentionally left blank
    }
}
