package com.dk.colorgame.constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Dawid Kotarba on 2015-06-15.
 */
public class Options {

    // Strings
    public static final String PREFS_PREFIX = "ColorSpree_";
    public static final String PREFS_OPTIONS = PREFS_PREFIX + "options";
    public static final String PREFS_SCOREBOARD = PREFS_PREFIX + "scoreBoard";
    public static final String PREFS_VOLUME = "volume";
    public static final String PREFS_SHAKE_EFFECT = "shakeEffect";
    public static final String PREFS_PARTICLES = "particles";
    public static final String PREFS_DRAW_FPS = "drawFps";
    public static final String PREFS_VIBRATION = "vib";
    public static final String PREFS_STATS = "stats";

    public static boolean vibration;
    public static boolean particleEffects;
    public static boolean drawFps;
    public static int volume;
    public static boolean shakeEffect;
    public static boolean stats;

    public static void init() {
        Preferences prefs = Gdx.app.getPreferences(PREFS_OPTIONS);

        if (!prefs.contains(PREFS_VIBRATION)) {
            prefs.putBoolean(PREFS_VIBRATION, true);
        }

        if (!prefs.contains(PREFS_PARTICLES)) {
            prefs.putBoolean(PREFS_PARTICLES, true);
        }

        if (!prefs.contains(PREFS_DRAW_FPS)) {
            prefs.putBoolean(PREFS_DRAW_FPS, false);
        }

        if (!prefs.contains(PREFS_SHAKE_EFFECT)) {
            prefs.putBoolean(PREFS_SHAKE_EFFECT, true);
        }

        if (!prefs.contains(PREFS_STATS)) {
            prefs.putBoolean(PREFS_STATS, true);
        }

        if (!prefs.contains(PREFS_VOLUME)) {
            prefs.putInteger(PREFS_VOLUME, Constants.SOUND_INIT_VOLUME);
        }

        prefs.flush();

        vibration = prefs.getBoolean(PREFS_VIBRATION, true);
        particleEffects = prefs.getBoolean(PREFS_PARTICLES, true);
        drawFps = prefs.getBoolean(PREFS_DRAW_FPS, false);
        shakeEffect = prefs.getBoolean(PREFS_SHAKE_EFFECT, true);
        stats = prefs.getBoolean(PREFS_STATS, true);
        volume = prefs.getInteger(PREFS_VOLUME, Constants.SOUND_INIT_VOLUME);
    }

    private Options() {
        // intentionally left blank
    }
}
