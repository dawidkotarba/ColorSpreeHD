package com.dk.colorgame.resources;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.dk.colorgame.resources.loaders.AssetLoader;

/**
 * Created by Dawid Kotarba on 2015-05-06.
 */
public class Fonts {

    private static final String FOLDER_NAME = "fonts/";

    public static final String HOMESPUN = AssetLoader.lazyLoad(FOLDER_NAME + "homespun.fnt", BitmapFont.class);
    public static final String HOMESPUN_BIG = AssetLoader.lazyLoad(FOLDER_NAME + "homespun_big.fnt", BitmapFont.class);

    private Fonts() {
        // intentionally left blank
    }
}
