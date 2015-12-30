package com.dk.colorgame.resources;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.dk.colorgame.resources.loaders.AssetLoader;

/**
 * Created by Dawid Kotarba on 2015-07-26.
 */
public class Animations {

    private Animations() {
        // intentionally left blank
    }

    private static final String FOLDER_NAME = "animations/";

    public static final String ANIMATION_TILE_BLUE = AssetLoader.lazyLoad(FOLDER_NAME + "blue_tile/anim.txt", TextureAtlas.class);
    public static final String ANIMATION_TILE_CYAN = AssetLoader.lazyLoad(FOLDER_NAME + "cyan_tile/anim.txt", TextureAtlas.class);
    public static final String ANIMATION_TILE_GREEN = AssetLoader.lazyLoad(FOLDER_NAME + "green_tile/anim.txt", TextureAtlas.class);
    public static final String ANIMATION_TILE_RED = AssetLoader.lazyLoad(FOLDER_NAME + "red_tile/anim.txt", TextureAtlas.class);
    public static final String ANIMATION_TILE_VIOLET = AssetLoader.lazyLoad(FOLDER_NAME + "violet_tile/anim.txt", TextureAtlas.class);
    public static final String ANIMATION_TILE_YELLOW = AssetLoader.lazyLoad(FOLDER_NAME + "yellow_tile/anim.txt", TextureAtlas.class);
    public static final String ANIMATION_TILE_DYNAMITE_RED = AssetLoader.lazyLoad(FOLDER_NAME + "red_dyn_tile/anim.txt", TextureAtlas.class);
    public static final String ANIMATION_TILE_DYNAMITE_BLUE = AssetLoader.lazyLoad(FOLDER_NAME + "blue_dyn_tile/anim.txt", TextureAtlas.class);
    public static final String ANIMATION_TILE_DYNAMITE_GREEN = AssetLoader.lazyLoad(FOLDER_NAME + "green_dyn_tile/anim.txt", TextureAtlas.class);
    public static final String ANIMATION_TILE_BOMB = AssetLoader.lazyLoad(FOLDER_NAME + "bomb_tile/anim.txt", TextureAtlas.class);
    public static final String ANIMATION_TILE_STAR = AssetLoader.lazyLoad(FOLDER_NAME + "star_tile/anim.txt", TextureAtlas.class);
    public static final String ANIMATION_TILE_ELECTRIC = AssetLoader.lazyLoad(FOLDER_NAME + "electric_tile/anim.txt", TextureAtlas.class);
    public static final String ANIMATION_TILE_MULTICOLOR = AssetLoader.lazyLoad(FOLDER_NAME + "multicolor_tile/anim.txt", TextureAtlas.class);
}
