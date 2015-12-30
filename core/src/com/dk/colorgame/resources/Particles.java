package com.dk.colorgame.resources;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.dk.colorgame.resources.loaders.AssetLoader;

/**
 * Created by Dawid Kotarba on 2015-05-03.
 */
public class Particles {

    private Particles() {
        // left blank
    }

    public static final String FOLDER_NAME = "particles/";

    public static final String PARTICLE_DYNAMITE = AssetLoader.lazyLoad(FOLDER_NAME + "dynamite.particle", ParticleEffect.class);
    public static final String PARTICLE_BOMB = AssetLoader.lazyLoad(FOLDER_NAME + "bomb.particle", ParticleEffect.class);
    public static final String PARTICLE_EXPL = AssetLoader.lazyLoad(FOLDER_NAME + "expl.particle", ParticleEffect.class);
    public static final String PARTICLE_EXPL_LARGE = AssetLoader.lazyLoad(FOLDER_NAME + "expl_large.particle", ParticleEffect.class);
    public static final String PARTICLE_EXPL_SMOKE_LARGE = AssetLoader.lazyLoad(FOLDER_NAME + "expl_large_smoke.particle", ParticleEffect.class);

    public static final String PARTICLE_MULTICOLOR = AssetLoader.lazyLoad(FOLDER_NAME + "multicolor.particle", ParticleEffect.class);
    public static final String PARTICLE_MULTICOLOR_SMALL = AssetLoader.lazyLoad(FOLDER_NAME + "multicolor_small.particle", ParticleEffect.class);

    public static final String PARTICLE_ELECTRIC = AssetLoader.lazyLoad(FOLDER_NAME + "electr.particle", ParticleEffect.class);
    public static final String PARTICLE_ELECTRIC_SMALL = AssetLoader.lazyLoad(FOLDER_NAME + "electr_small.particle", ParticleEffect.class);

    public static final String PARTICLE_SMOKE = AssetLoader.lazyLoad(FOLDER_NAME + "smoke.particle", ParticleEffect.class);
    public static final String PARTICLE_BACKGROUND = AssetLoader.lazyLoad(FOLDER_NAME + "background.particle", ParticleEffect.class);
    public static final String PARTICLE_MENU = AssetLoader.lazyLoad(FOLDER_NAME + "menu.particle", ParticleEffect.class);

    public static final String PARTICLE_STARS = AssetLoader.lazyLoad(FOLDER_NAME + "stars.particle", ParticleEffect.class);
    public static final String PARTICLE_STARS_SMALL = AssetLoader.lazyLoad(FOLDER_NAME + "stars_small.particle", ParticleEffect.class);

    public static final String PARTICLE_CYAN = AssetLoader.lazyLoad(FOLDER_NAME + "cyan.particle", ParticleEffect.class);
    public static final String PARTICLE_CYAN_SMALL = AssetLoader.lazyLoad(FOLDER_NAME + "cyan_small.particle", ParticleEffect.class);
    public static final String PARTICLE_CYAN_AWARD = AssetLoader.lazyLoad(FOLDER_NAME + "cyan_award.particle", ParticleEffect.class);

    public static final String PARTICLE_VIOLET = AssetLoader.lazyLoad(FOLDER_NAME + "violet.particle", ParticleEffect.class);
    public static final String PARTICLE_VIOLET_SMALL = AssetLoader.lazyLoad(FOLDER_NAME + "violet_small.particle", ParticleEffect.class);
    public static final String PARTICLE_VIOLET_AWARD = AssetLoader.lazyLoad(FOLDER_NAME + "violet_award.particle", ParticleEffect.class);

    public static final String PARTICLE_RED = AssetLoader.lazyLoad(FOLDER_NAME + "red.particle", ParticleEffect.class);
    public static final String PARTICLE_RED_SMALL = AssetLoader.lazyLoad(FOLDER_NAME + "red_small.particle", ParticleEffect.class);
    public static final String PARTICLE_RED_AWARD = AssetLoader.lazyLoad(FOLDER_NAME + "red_award.particle", ParticleEffect.class);

    public static final String PARTICLE_BLUE = AssetLoader.lazyLoad(FOLDER_NAME + "blue.particle", ParticleEffect.class);
    public static final String PARTICLE_BLUE_SMALL = AssetLoader.lazyLoad(FOLDER_NAME + "blue_small.particle", ParticleEffect.class);
    public static final String PARTICLE_BLUE_AWARD = AssetLoader.lazyLoad(FOLDER_NAME + "blue_award.particle", ParticleEffect.class);

    public static final String PARTICLE_GREEN = AssetLoader.lazyLoad(FOLDER_NAME + "green.particle", ParticleEffect.class);
    public static final String PARTICLE_GREEN_SMALL = AssetLoader.lazyLoad(FOLDER_NAME + "green_small.particle", ParticleEffect.class);
    public static final String PARTICLE_GREEN_AWARD = AssetLoader.lazyLoad(FOLDER_NAME + "green_award.particle", ParticleEffect.class);

    public static final String PARTICLE_YELLOW = AssetLoader.lazyLoad(FOLDER_NAME + "yellow.particle", ParticleEffect.class);
    public static final String PARTICLE_YELLOW_SMALL = AssetLoader.lazyLoad(FOLDER_NAME + "yellow_small.particle", ParticleEffect.class);
    public static final String PARTICLE_YELLOW_AWARD = AssetLoader.lazyLoad(FOLDER_NAME + "yellow_award.particle", ParticleEffect.class);

    public static final String PARTICLE_GRAY_AWARD = AssetLoader.lazyLoad(FOLDER_NAME + "gray_award.particle", ParticleEffect.class);

}
