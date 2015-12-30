package com.dk.colorgame.resources;

import com.badlogic.gdx.audio.Sound;
import com.dk.colorgame.resources.loaders.AssetLoader;

/**
 * Created by Dawid Kotarba on 2015-07-31.
 */
public class Sounds {

    private Sounds() {
        // left blank
    }

    private static final String FOLDER_NAME = "sounds/";

    public static String SOUND_TILE_ACTIVATE = AssetLoader.lazyLoad(FOLDER_NAME + "game/activate.mp3", Sound.class);
    public static String SOUND_EXPLOSION = AssetLoader.lazyLoad(FOLDER_NAME + "game/explosion.mp3", Sound.class);
    public static String SOUND_STAR = AssetLoader.lazyLoad(FOLDER_NAME + "game/star.mp3", Sound.class);
    public static String SOUND_AWARD1 = AssetLoader.lazyLoad(FOLDER_NAME + "game/award1.mp3", Sound.class);
    public static String SOUND_AWARD2 = AssetLoader.lazyLoad(FOLDER_NAME + "game/award2.mp3", Sound.class);
    public static String SOUND_AWARD3 = AssetLoader.lazyLoad(FOLDER_NAME + "game/award3.mp3", Sound.class);
    public static String SOUND_AWARD4 = AssetLoader.lazyLoad(FOLDER_NAME + "game/award4.mp3", Sound.class);
    public static String SOUND_CLICK = AssetLoader.lazyLoad(FOLDER_NAME + "game/click.mp3", Sound.class);
    public static String SOUND_COUNTER = AssetLoader.lazyLoad(FOLDER_NAME + "game/counter.mp3", Sound.class);
    public static String SOUND_ELECTR = AssetLoader.lazyLoad(FOLDER_NAME + "game/electr.mp3", Sound.class);
}
