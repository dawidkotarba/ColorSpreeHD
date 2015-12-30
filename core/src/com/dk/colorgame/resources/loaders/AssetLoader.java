package com.dk.colorgame.resources.loaders;

import com.badlogic.gdx.assets.AssetManager;
import com.dk.colorgame.Game;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dawid Kotarba on 2015-08-01.
 */
public class AssetLoader {

    private static AssetManager manager;
    private static Map<String, Class> lazyLoadedResources;

    public synchronized static Object get(String path, Class claz) {
        if (manager == null) {
            manager = new AssetManager();
        }

        if (!manager.isLoaded(path)) {
            manager.load(path, claz);
        }

        manager.finishLoading();
        return manager.get(path, claz);
    }

    public static void dispose() {
        if (manager != null) {
            manager.clear(); // clear, not dispose
        }

        manager = null;
        lazyLoadedResources = null;
    }

    public synchronized static String lazyLoad(String path, Class claz) {
        if (manager == null) {
            manager = new AssetManager();
        }

        if (lazyLoadedResources == null) {
            lazyLoadedResources = new HashMap<>();
        }

        lazyLoadedResources.put(path, claz);

        return path;
    }

    public static void processLazyLoad() {
        if (Game.isWorldCreated()) {

            if (lazyLoadedResources == null) {
                lazyLoadedResources = new HashMap<>();
            }

            for (Map.Entry<String, Class> entry : lazyLoadedResources.entrySet()) {
                if (!manager.isLoaded(entry.getKey())) {
                    manager.load(entry.getKey(), entry.getValue());
                }
            }

        }
        lazyLoadedResources.clear();
    }

    public static int getProgress() {
        return (int) (manager.getProgress() * 100);
    }

    public static boolean update() {
        return manager.update();
    }
}
