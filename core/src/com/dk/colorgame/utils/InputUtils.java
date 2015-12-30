package com.dk.colorgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector3;
import com.dk.colorgame.model.TouchCoords;

/**
 * Created by Dawid Kotarba on 2015-05-02.
 */
public class InputUtils {

    private static InputMultiplexer inputMultiplexer;

    private InputUtils() {
        // intentionally left blank
    }

    public static TouchCoords getTouchCoordinates(int pointer) {
        return translateTouchCoordinates(Gdx.input.getX(pointer), Gdx.input.getY(pointer));
    }

    public static TouchCoords translateTouchCoordinates(float screenX, float screenY) {
        Vector3 worldCoordinates = GraphicUtils.getCamera().unproject(new Vector3(screenX, screenY, 0));
        return new TouchCoords((int) worldCoordinates.x, (int) worldCoordinates.y);
    }

    public static InputMultiplexer getInputMultiplexer() {
        if (inputMultiplexer == null) {
            inputMultiplexer = new InputMultiplexer();
            Gdx.input.setInputProcessor(inputMultiplexer);
        }

        return inputMultiplexer;
    }

    public static void dispose() {
        inputMultiplexer.clear();
        inputMultiplexer = null;
    }
}
