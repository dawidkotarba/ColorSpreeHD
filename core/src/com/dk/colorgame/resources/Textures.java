package com.dk.colorgame.resources;

import com.badlogic.gdx.graphics.Texture;
import com.dk.colorgame.resources.loaders.AssetLoader;

/**
 * Created by Dawid Kotarba on 2015-04-28.
 */
public class Textures {

    private Textures() {
        // intentionally left blank
    }

    private static final String FOLDER_NAME = "textures/";

    // tiles
    public static final String IMG_RED_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_red.png", Texture.class);
    public static final String IMG_VIOLET_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_violet.png", Texture.class);
    public static final String IMG_CYAN_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_cyan.png", Texture.class);
    public static final String IMG_BLUE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_blue.png", Texture.class);
    public static final String IMG_GREEN_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_green.png", Texture.class);
    public static final String IMG_YELLOW_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_yellow.png", Texture.class);
    public static final String IMG_GRAY_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_gray.png", Texture.class);
    public static final String IMG_DYNAMITE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_dynamite.png", Texture.class);
    public static final String IMG_DYNAMITE_BLUE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_dynamite_blue.png", Texture.class);
    public static final String IMG_STAR_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_star.png", Texture.class);
    public static final String IMG_BOMB_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_bomb.png", Texture.class);
    public static final String IMG_ELECTRIC_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_electric.png", Texture.class);

    // active tiles
    public static final String IMG_RED_ACTIVE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_red_active.png", Texture.class);
    public static final String IMG_VIOLET_ACTIVE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_violet_active.png", Texture.class);
    public static final String IMG_CYAN_ACTIVE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_cyan_active.png", Texture.class);
    public static final String IMG_BLUE_ACTIVE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_blue_active.png", Texture.class);
    public static final String IMG_GREEN_ACTIVE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_green_active.png", Texture.class);
    public static final String IMG_YELLOW_ACTIVE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_yellow_active.png", Texture.class);
    public static final String IMG_GRAY_ACTIVE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_gray_active.png", Texture.class);
    public static final String IMG_DYNAMITE_ACTIVE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_dynamite_active.png", Texture.class);
    public static final String IMG_DYNAMITE_ACTIVE_BLUE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_dynamite_active_blue.png", Texture.class);
    public static final String IMG_DYNAMITE_ACTIVE_GREEN_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_dynamite_active_green.png", Texture.class);
    public static final String IMG_STAR_ACTIVE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_star_active.png", Texture.class);
    public static final String IMG_BOMB_ACTIVE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_bomb_active.png", Texture.class);
    public static final String IMG_ELECTRIC_ACTIVE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_electric_active.png", Texture.class);
    public static final String IMG_MULTICOLOR_ACTIVE_TILE = AssetLoader.lazyLoad(FOLDER_NAME + "tile_multicolor_active.png", Texture.class);

    // buttons
    public static final String IMG_BUTTON = AssetLoader.lazyLoad(FOLDER_NAME + "button.png", Texture.class);
    public static final String IMG_BUTTON_DOWN = AssetLoader.lazyLoad(FOLDER_NAME + "button_down.png", Texture.class);
    public static final String IMG_BUTTON_SQUARE = AssetLoader.lazyLoad(FOLDER_NAME + "button_square.png", Texture.class);
    public static final String IMG_BUTTON_SQUARE_DOWN = AssetLoader.lazyLoad(FOLDER_NAME + "button_square_down.png", Texture.class);

    // other
    public static final String IMG_LIGHT = AssetLoader.lazyLoad(FOLDER_NAME + "light.png", Texture.class);
}
