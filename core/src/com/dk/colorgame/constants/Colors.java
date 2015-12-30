package com.dk.colorgame.constants;

import com.badlogic.gdx.graphics.Color;

import static com.dk.colorgame.utils.GraphicUtils.getColorFromRGB;

/**
 * Created by Dawid Kotarba on 2015-06-17.
 */
public class Colors {

    public static final Color VIOLET = getColorFromRGB(147, 20, 155);
    public static final Color BLUE = getColorFromRGB(8, 84, 199);
    public static final Color GREEN = getColorFromRGB(0, 140, 30);
    public static final Color YELLOW = getColorFromRGB(236, 181, 30);
    public static final Color RED = getColorFromRGB(183, 0, 14);
    public static final Color CYAN = getColorFromRGB(2, 195, 179);
    public static final Color GRAY = getColorFromRGB(130, 130, 130);

    private Colors(){
        // intentionally left blank
    }
}
