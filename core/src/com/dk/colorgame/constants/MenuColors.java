package com.dk.colorgame.constants;

import com.badlogic.gdx.graphics.Color;

import static com.dk.colorgame.utils.GraphicUtils.getColorFromRGB;

/**
 * Created by Dawid Kotarba on 2015-08-06.
 */
public class MenuColors {

    public final static Color BLUE = getColorFromRGB(4, 50, 119);
    public static final Color CYAN = getColorFromRGB(1, 97, 89);
    public static final Color RED = getColorFromRGB(73, 0, 5);
    public final static Color VIOLET = getColorFromRGB(73, 10, 77);
    public static final Color GREEN = getColorFromRGB(0, 70, 15);
    public static final Color YELLOW = getColorFromRGB(141, 108, 18);
    public static final Color GRAY = getColorFromRGB(100, 100, 100);

    private MenuColors() {
        // intentionally left blank
    }
}
