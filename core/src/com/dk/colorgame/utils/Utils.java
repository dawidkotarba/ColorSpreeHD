package com.dk.colorgame.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dk.colorgame.constants.Strings;
import com.dk.colorgame.resources.loaders.AssetLoader;

import java.util.Random;

import static com.dk.colorgame.constants.Constants.SECONDS_IN_MINUTE;
import static com.dk.colorgame.constants.Strings.LABEL_COLON;

/**
 * Created by Dawid Kotarba on 2015-05-03.
 */
public class Utils {

    private static final Random random = new Random();

    private Utils() {
        // intentionally left blank
    }

    public static boolean testLuck(int luckPercentage) {
        return random.nextInt(100) <= luckPercentage;
    }

    public static Random getRandom() {
        return random;
    }

    public static String getFormattedTimer(int seconds) {

        class TimeFormatterHelper {

            public String normalizeSecondsOutput(int seconds) {
                if (seconds < 10) {
                    return "0" + Integer.toString(seconds);
                }

                return Integer.toString(seconds);
            }

            public int getMinutesLeftWithoutSeconds(int seconds) {
                return seconds / SECONDS_IN_MINUTE;
            }

            public int getSecondsLeftWithoutMinutes(int seconds) {
                return seconds % SECONDS_IN_MINUTE;
            }

        }

        StringBuilder sb = new StringBuilder();

        TimeFormatterHelper timeFormatterHelper = new TimeFormatterHelper();

        String minutesStr = Integer.toString(timeFormatterHelper.getMinutesLeftWithoutSeconds(seconds));
        String secondsStr = timeFormatterHelper.normalizeSecondsOutput(timeFormatterHelper.getSecondsLeftWithoutMinutes(seconds));
        sb.append(minutesStr).append(LABEL_COLON).append(secondsStr);
        return sb.toString();
    }

    public static void drawLoadingProgress(SpriteBatch spriteBatch, BitmapFont font) {
        if (!AssetLoader.update()) {
            font.draw(spriteBatch, "Loading: " + AssetLoader.getProgress() + Strings.LABEL_PERCENT, GraphicUtils.getWorldWidth() - 300, GraphicUtils.getWorldHeight() - 10);
        }
    }

}
